package edu.school.config.db;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@Profile("h2")   //激活h2时才会创建该类中的Bean
public class H2DataSource {

	//h2默认生成的访问连接jdbc:h2:mem:testdb，可以参看http://www.mkyong.com/spring/spring-embedded-database-examples/
	@Bean
	public DataSource dataSource() {
        //嵌入式数据库
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2).addScript("db/sql/create-db.sql").addScript("db/sql/insert-data.sql").build();
		return db;

	}

	// Start WebServer, access 默认http://localhost:8082
	// 通过Server.createWebServer("-webPort", "8182")可修改访问端口号等
	// 参考http://www.programcreek.com/java-api-examples/index.php?class=org.h2.tools.Server&method=createWebServer
	// ,http://www.programcreek.com/java-api-examples/index.php?source_dir=h2-bitmap-master/h2/src/test/org/h2/test/server/TestWeb.java
	@Bean(initMethod = "start", destroyMethod = "stop")
	public Server startDBManager() throws SQLException {
		return Server.createWebServer("-webPort", "8182");
	}

}