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
import edu.school.exceptionAndHandler.DuplicateException;
import edu.school.exceptionAndHandler.GrabCourseNumLimitException;
import edu.school.exceptionAndHandler.NoPlaceException;
import edu.school.exceptionAndHandler.StudentNotFoundException;

@Service   //ʵ�־����ҵ���߼�
public class StuRefCourseService {

    @Autowired
    private StuRefCourseDao stuRefCourseDao;
    
    @Autowired
    private StudentDao studentDao;
    
    @Autowired
    private CourseDao courseDao;
    
    /**
     * �����ѡ���߼�ʵ��
     * һ��ѧ���Ƿ���ѡ���ſδ��ڶ�������жϣ����磺a.ѡ�������Ƿ񳬹�����  b.��ǰ��ѡ���Ƿ�����ѡ�γ�ͻ  c.��ǰ�γ��Ƿ�����������Щ�жϲ���ִ��
     * (�������ж���һ��������Ϳ��Բ���ִ�����������̼߳�����ִ�У�ʹ���̵߳��ж���)��
     * ȫ��ִ�������ִ�к���ѡ�ε�д������������Բ���CountDownLatchʹд�����������߳�ִ�������ִ�С����ڲ���ִ�У���Ҫ����������뼶��
     * Serializable (���л�)����������������ظ������ö��ķ�������������һ������
     * 
     * ��ǰ�����ڲ�������»�������ݴ��ң�ԭ�򣺿γ̵������ǹ��������ѧ����ѡ�������ǹ������  ��ʹ��synchronized����������Ϊͬ��
     * @param stu_id
     * @param Course_id
     * @return
     */
    public synchronized void grabCourse(int stu_id, int course_id) {
    	System.out.println("stu_id=" + stu_id + ",course_id=" + course_id);
    	//��ǰ����ѡ������
    	int grab_course_num = 0;
    	Student stu = studentDao.queryById(stu_id);
    	if(stu != null) {
    		grab_course_num = stu.getNumber();
    	}else{
    		throw new StudentNotFoundException(stu_id);
    	}
    	
    	
    	if(grab_course_num != 0){
    		//��ǰ��ѡ������
    		int grab_course_num_already = 0;
    		grab_course_num_already = stuRefCourseDao.getGrabCourseNum(stu_id);
    		
    		if(grab_course_num > grab_course_num_already){
    			
    			//��ǰ�γ��Ƿ�����
    			int course_limit_num = 0;
    			Course course = courseDao.queryById(course_id);
    			if(course != null) {
    				course_limit_num = course.getNumber();
    				int course_already_num = 0;
    				course_already_num = stuRefCourseDao.getCourseNum(course_id);
    				if(course_already_num < course_limit_num){
    					
    					//��ǰ��ѡ���Ƿ�����ѡ�γ�ͻ
    					String time_for_class = course.getTime_for_class();
    					int time_exist = 0;
    					//��Ҫ������ϲ�ѯ����δʵ��
    					time_exist = stuRefCourseDao.getCountTime(stu_id, time_for_class);
    					int isMoreThenOne = 0;
    					//����ѡͬһ�ſζ���һ��
    					isMoreThenOne = stuRefCourseDao.getGrabCourseNum(stu_id, course_id);
    					
    					
    					if(time_exist == 0 && isMoreThenOne == 0){
    						//����ѡ��
    						StuRefCourse src = new StuRefCourse();
    						src.setCourse_id(course_id);
    						src.setStu_id(stu_id);
    						//System.out.println("stu_id=" + stu_id + ",course_id=" + course_id);
    						int value = stuRefCourseDao.insert(src);
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
    	
    }
    
}
