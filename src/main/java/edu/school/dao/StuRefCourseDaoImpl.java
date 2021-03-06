package edu.school.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Repository;

import tk.mybatis.mapper.entity.Example;
import edu.school.domain.StuRefCourse;
import edu.school.mapper.StuRefCourseMapper;

@Repository
public class StuRefCourseDaoImpl implements StuRefCourseDao {

	@Autowired
    private StuRefCourseMapper stuRefCourseMapper;

	@Override
	public int insert(StuRefCourse stuRefCourse) {
		int value = stuRefCourseMapper.insert(stuRefCourse);
		return value;
	}

	@Override
	public int getGrabCourseNum(int stu_id, int course_id) {
		Example example = new Example(StuRefCourse.class);

        example.createCriteria().andEqualTo("stu_id", stu_id).andEqualTo("course_id", course_id);
		return stuRefCourseMapper.selectCountByExample(example);
	}
	
	@Override
	public StuRefCourse getStuRefCourse(StuRefCourse stuRefCourse) {
		return stuRefCourseMapper.selectOne(stuRefCourse);
	}

	
	@Override
	public int getCourseNum(int course_id) {
		StuRefCourse src = new StuRefCourse();
		src.setCourse_id(course_id);
		return stuRefCourseMapper.selectCount(src);
	}

	@Override
	public int getCountTime(int stu_id, String time_for_class) {
		
		return 0;
	}

	@Override
	public int getGrabCourseNum(int stu_id) {
		StuRefCourse src = new StuRefCourse();
		src.setStu_id(stu_id);
		return stuRefCourseMapper.selectCount(src);
	}


}