# grabClass
选课项目 Spring + Spring MVC + Mybatis(Embedded database\Mysql) 注解+Java实现方式，无XML

###1. 包含的技术

Maven 3.0
Spring 4.1.6.RELEASE
HSQLDB 2.3.2
H2 1.4.187
Derby 10.11.1.1
Mysql 5.1.32
Servlet3.1.0
Mybatis 3.2.8
Mapper 3.2.4  https://github.com/abel533/Mapper

注意：本Demo使用了Servlet3.1.0，因为Servlet3规范以下的Servlet容器不支持使用Java方式的DispatcherServlet配置，Tomcat版本>=7。

###2. 如何本地运行本Demo

$ git clone https://github.com/talong/grabClass.git
$ mvn tomcat7:run

Access http://localhost:8080/grabclass

###3. 可运行如下命令将项目从Maven项目转换为普通Java项目

$ mvn eclipse:eclipse


