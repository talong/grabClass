查看线程状态jstack -l 6368 >> D:/temp/temp.dump

注：每次请求都是在stu_ref_course表为空表时发送的。
Tomcat 默认为bio模式，    <Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" redirectPort="8443" />
此时默认可接受的连接数是100（来源：http://tomcat.apache.org/tomcat-7.0-doc/config/http.html的acceptCount），可手动设置：
<Connector port="8080" protocol="HTTP/1.1" connectionTimeout="20000" acceptCount="500" maxThreads="400" redirectPort="8443" /> 此时我在welcome.jsp设置1000一定报Connection refused: connect(看来线程发送http使用的util工具类HttpRequest执行有先后，HttpRequest应该不存在线程安全问题，因为都是局部变量，相当于ThreadLocal)

但是我需要5000的并发量，所以考虑了Tomc nio模式：<Connector connectionTimeout="20000" port="8080" protocol="org.apache.coyote.http11.Http11NioProtocol" redirectPort="8443"/>
4000时正常，6000时一定出现Connection refused: connect

增加 线程池   <Executor name="tomcatThreadPool" namePrefix="catalina-exec-"
        maxThreads="500" minSpareThreads="25"/>并注册到Connector中，
查看http://tomcat.apache.org/tomcat-7.0-doc/config/executor.html中发现maxQueueSize默认值是Integer.MAX_VALUE，所以如此配置预期在内存分配够用的情况下不会出现Connection refused: connect

测试6000正常，10000在没有出现outofmemory的情况下出现Connection refused: connect

设置maxQueueSize官方解释：(int) The maximum number of runnable tasks that can queue up awaiting execution before we reject them. Default value is Integer.MAX_VALUE，这里应该不是指的tomcat的连接数？
增加maxThreads到1000反而出现更多Connection refused: connect，暂时只能支持到6000左右的http并发请求。


结合Tomcat管理页面查看http://localhost:8080/manager/status
JVM

Free memory: 160.09 MB Total memory: 365.00 MB Max memory: 1799.00 MB

Memory Pool	    Type	             Initial	Total	    Maximum	    Used
PS Eden Space	Heap memory	         32.50 MB	247.00 MB	601.00 MB	116.81 MB (19%)
PS Old Gen	    Heap memory	         84.00 MB	84.00 MB	1349.00 MB	54.09 MB (4%)
PS Survivor Space	Heap memory	 5.00 MB	34.00 MB	34.00 MB	33.99 MB (99%)
Code Cache	    Non-heap memory	     2.43 MB	2.56 MB	    48.00 MB	2.47 MB (5%)
PS Perm Gen	    Non-heap memory	     21.00 MB	29.00 MB	82.00 MB	28.77 MB (35%)
