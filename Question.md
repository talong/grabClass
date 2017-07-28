遗留问题：
1.引入logback依赖，但logback.xml不起作用
2.根据官网文档http://docs.spring.io/spring-data/redis/docs/1.8.4.RELEASE/reference/html/#redis.repositories改写redis为通用数据层


解决情况：
1.原因 ：activemq-all 中包含sl4f与引入的sl4f依赖冲突导致，换成activemq-core即可
2.已修改为使用CrudRepository