# grabClass 项目结构及各package包含功能    
================================   
	  - src/main/java  
	   | -edu.school  
	     | -config                   //存放各种容器启动需要加载的配置文件  
	       | +db                     //数据源配置  
	       | +mbatis                 //mybatis配置  
	       | +others                 //ActiveMQ Redis Propery配置  
	       | -SpringRootConfig.java      
	       | -SpringWebConfig.java    
	     | +dao                      //数据层  
	     | +domain  
	     | +exceptionAndHandler      //自定义异常    
	     | +mapper                   //使用第三方依赖访问mybatis，算作service层的接口    
	     | +messaging                //JMS收发消息中间类    
	     | +modle    
	     | +service                  //业务层  
	     | +servlet3  
	     | +utils  
	     | -web                        
	       | +api                    //api接口  
	       | +controller               
	       | +thread                 //并发处理  
	  + src/test/java  
	  - src/main/resources    
	   | -db  
	     | +sql                      //嵌入式数据库加载的sql  
	   | -properties  
	     | +activeMQ                 //activeMQ的propery文件  
	  - pom.xml  
	  - ProjectStructure.MD  
	  - Question.md  
	  - README.md  
       