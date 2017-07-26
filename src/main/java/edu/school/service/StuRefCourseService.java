package edu.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import edu.school.dao.CourseDao;
import edu.school.dao.StuRefCourseDao;
import edu.school.dao.StuRefCourseRepository;
import edu.school.dao.StudentDao;
import edu.school.domain.Course;
import edu.school.domain.StuRefCourse;
import edu.school.domain.Student;
import edu.school.exceptionAndHandler.CourseNotFoundException;
import edu.school.exceptionAndHandler.DuplicateException;
import edu.school.exceptionAndHandler.GrabCourseNumLimitException;
import edu.school.exceptionAndHandler.NoPlaceException;
import edu.school.exceptionAndHandler.StudentNotFoundException;

@Service   //实现具体的业务逻辑
public class StuRefCourseService {

    @Autowired
    private StuRefCourseDao stuRefCourseDao;
    
    @Autowired
    private StudentDao studentDao;
    
    @Autowired
    private CourseDao courseDao;
    
    //@Autowired
	//private RedisTemplate<String, StuRefCourse> redis;
    @Autowired
    private StuRefCourseRepository redisRepository;
    
    @Autowired(required = false) RedisConnectionFactory redisConnectionFactory;
    
    /**
     * 具体的选课逻辑实现
     * 一个学生是否能选该门课存在多个条件判断，例如：a.选课数量是否超过限制  b.当前所选课是否与已选课冲突  c.当前课程是否已满，将这些判断并发执行
     * (这三个判断有一个不满足就可以不用执行其他两个线程及后续执行，使用线程的中断吗？)，
     * 全部执行完才能执行后续选课的写操作，这里可以采用CountDownLatch使写操作在其他线程执行完后再执行。由于并发执行，需要采用事务隔离级别
     * Serializable (串行化)来避免脏读、不可重复读、幻读的发生。（这里是一个事务）
     * 
     * 当前方法在并发情况下会出现数据错乱，原因：课程的数量是共享变量、学生可选课数量是共享变量  故使用synchronized将方法限制为同步
     * 将insert对象写入redis
     * @param stu_id
     * @param Course_id
     * @return
     */
    public synchronized StuRefCourse grabCourse(int stu_id, int course_id) {
    	
    	StuRefCourse src = new StuRefCourse();
    	//当前允许选课数量
    	int grab_course_num = 0;
    	Student stu = studentDao.queryById(stu_id);
    	if(stu != null) {
    		grab_course_num = stu.getNumber();
    	}else{
    		throw new StudentNotFoundException(stu_id);
    	}
    	
    	
    	if(grab_course_num != 0){
    		//当前已选课数量
    		int grab_course_num_already = 0;
    		grab_course_num_already = stuRefCourseDao.getGrabCourseNum(stu_id);
    		
    		if(grab_course_num > grab_course_num_already){
    			
    			//当前课程是否已满
    			int course_limit_num = 0;
    			Course course = courseDao.queryById(course_id);
    			if(course != null) {
    				course_limit_num = course.getNumber();
    				int course_already_num = 0;
    				course_already_num = stuRefCourseDao.getCourseNum(course_id);
    				if(course_already_num < course_limit_num){
    					
    					//当前所选课是否与已选课冲突
    					String time_for_class = course.getTime_for_class();
    					int time_exist = 0;
    					//需要多表联合查询，暂未实现
    					time_exist = stuRefCourseDao.getCountTime(stu_id, time_for_class);
    					int isMoreThenOne = 0;
    					//不能选同一门课多于一次
    					isMoreThenOne = stuRefCourseDao.getGrabCourseNum(stu_id, course_id);
    					
    					
    					if(time_exist == 0 && isMoreThenOne == 0){
    						//进行选课
    						src.setCourse_id(course_id);
    						src.setStu_id(stu_id);
    						//System.out.println("stu_id=" + stu_id + ",course_id=" + course_id);
    						int value = stuRefCourseDao.insert(src);
    						
    						
    						if(value == 1) {
    							src = stuRefCourseDao.getStuRefCourse(src);
    							System.out.println("stu_id=" + stu_id + ",course_id=" + course_id);
    							try{
    								//防止缓存操作失败影响正常流程
    								//redis.opsForValue().set(src.getSrcKey(), src);
    								redisRepository.save(src);
    								src = stuRefCourseDao.getStuRefCourse(src);
    								//StuRefCourse src_redis = redis.opsForValue().get(src.getSrcKey());
    								StuRefCourse src_redis = redisRepository.findOne(src.getId().toString());//使用缓存注解后无法使用
    								System.out.println("redis=" + src_redis.toString());
    								
    								
    							}catch(Exception e) {
    								e.printStackTrace();
    							}
    							
    						}
    						
    						
    					}else{
    						throw new DuplicateException(stu_id, course_id);
    					}
    				}else{
    					throw new NoPlaceException(course_id);
    				}
    			}else{
    				throw new CourseNotFoundException(stu_id);
    			}
    			
    		}else{
    			throw new GrabCourseNumLimitException();
    		}
    		
    	}
    	return src;
    }
    
}
