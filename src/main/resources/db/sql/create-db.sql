--DROP TABLE users IF EXISTS;

--学生表
CREATE TABLE student (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30) NOT NULL COMMENT '姓名',
  student_no VARCHAR(10) NOT NULL COMMENT '学号',
  number INTEGER NOT NULL COMMENT '可选修课程数'
);

--课程信息表
CREATE TABLE course (
  id INTEGER PRIMARY KEY,
  name VARCHAR(30) NOT NULL COMMENT '课程名称',
  time_for_class VARCHAR(30) NOT NULL COMMENT '授课时间段，如：周五|08：00-09：00',
  number INTEGER NOT NULL COMMENT '最大选课人数'
);

--选课表
CREATE TABLE stu_ref_course (
  id INTEGER PRIMARY KEY,
  stu_id INTEGER NOT NULL COMMENT '学生id',
  course_id INTEGER NOT NULL COMMENT '课程id'
);