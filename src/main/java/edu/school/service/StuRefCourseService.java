package edu.school.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.school.dao.CourseDao;
import edu.school.dao.StuRefCourseDao;
import edu.school.dao.StudentDao;
import edu.school.domain.Course;
import edu.school.domain.StuRefCourse;
import edu.school.domain.Student;
import edu.school.exceptionAndHandler.CourseNotFoundException;
import edu.school.exceptionAndHandler.StudentNotFoundException;

@Service   //实现具体的业务逻辑
public class StuRefCourseService {

    @Autowired
    private StuRefCourseDao stuRefCourseDao;
    
    @Autowired
    private StudentDao studentDao;
    
    @Autowired
    private CourseDao courseDao;
    
    /**
     * 具体的选课逻辑实现
     * 一个学生是否能选该门课存在多个条件判断，例如：a.选课数量是否超过限制  b.当前所选课是否与已选课冲突  c.当前课程是否已满，将这些判断并发执行
     * (这三个判断有一个不满足就可以不用执行其他两个线程及后续执行，使用线程的中断吗？)，
     * 全部执行完才能执行后续选课的写操作，这里可以采用CountDownLatch使写操作在其他线程执行完后再执行。由于并发执行，需要采用事务隔离级别
     * Serializable (串行化)来避免脏读、不可重复读、幻读的发生。（这里是一个事务）
     * @param stu_id
     * @param Course_id
     * @return
     */
    public String grabCourse(int stu_id, int course_id) {
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
    		grab_course_num_already = stuRefCourseDao.getGrabCourseNum(stu_id, course_id);
    		
    		if(grab_course_num > grab_course_num_already){
    			
    			//当前课程是否已满
    			int course_limit_num = 0;
    			Course course = courseDao.queryById(Integer.toString(course_id));
    			if(course != null) {
    				course_limit_num = course.getNumber();
    				int course_already_num = 0;
    				course_already_num = stuRefCourseDao.getCourseNum(course_id);
    				if(course_already_num < course_limit_num){
    					
    					//当前所选课是否与已选课冲突
    					String time_for_class = course.getTime_for_class();
    					int time_exist = 0;
    					time_exist = stuRefCourseDao.getCountTime(stu_id, time_for_class);
    					
    					if(time_exist == 0){
    						//进行选课
    						StuRefCourse src = new StuRefCourse();
    						src.setCourse_id(course_id);
    						src.setStu_id(stu_id);
    						int value = stuRefCourseDao.insert(src);
    					}
    				}
    			}else{
    				throw new CourseNotFoundException(stu_id);
    			}
    			
    		}
    		
    	}
    	
    	
    	return "";
    }
    
}
