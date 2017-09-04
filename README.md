# grabClass
===============================  
抢课项目 Spring + Spring MVC + Mybatis(Embedded database\Mysql) 注解+Java实现方式，无XML

###1. 包含的技术

*Maven 3.0  
*Spring 4.1.6.RELEASE   改为4.2.9.RELEASE   因为Spring Data Redis 1.x binaries requires JDK level 6.0 and above, and Spring Framework 4.2.9.RELEASE and above.  
*spring-data-redis 1.7.11.RELEASE 注意：redis-server>= 2.8Redis Repositories requires at least Redis Server version 2.8.0.
*jedis 2.9.0 
*HSQLDB 2.3.2 (嵌入式数据库 用于开发阶段)  
*H2 1.4.187 (嵌入式数据库 用于开发阶段)  
*Derby 10.11.1.1 (嵌入式数据库 用于开发阶段)  
*Mysql 5.1.32  
*Servlet3.1.0 改为 3.0.1 因为在eclipse中Run on Server(Tomcat7)时提示error: The server does not support version 3.1 of the J2EE Web   module specification,似乎是当前没有Server支持3.1(https://stackoverflow.com/questions/29997868/error-the-server-does-not-support-version-3-1-of-the-j2ee-web-module-specificat)注意： project -> Properties -> Project Facets then set Dynamic Web Module to 3.0这里改为3.0  
*Mybatis 3.2.8  
*Mapper 3.2.4(支持单表操作，不支持通用的多表联合查询)  https://github.com/abel533/Mapper  
*activemq-core 5.7.0 


待实现：    
*webSocket  
*vue实现与大白机器人智能对话   
*整合rpc（使用springBoot启动另一个项目提供rpc服务）主要是为了理解douboo   
*编写一个使用代理模式的程序代码（远程代理、保护代理、智能引用代理）   
*java基础   
*添加日志框架，例如logback    
*Java调用R语言实现的泊松分布计算



注意：本Demo使用了Servlet3.0.1，因为Servlet3规范以下的Servlet容器不支持使用Java方式的DispatcherServlet配置，Tomcat版本>=7。

###2. 如何本地运行本Demo    
安装activemq并运行（activemq start）  
安装redis-server并运行   
$ git clone https://github.com/talong/grabClass.git  
$ mvn tomcat7:run

Access http://localhost:8080/grabclass

###3. 可运行如下命令将项目从Maven项目转换为普通Java项目

$ mvn eclipse:eclipse


