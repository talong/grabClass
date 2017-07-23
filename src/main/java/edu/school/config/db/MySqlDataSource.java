package edu.school.config.db;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("mysql")  //QA 生产环境激活
public class MySqlDataSource {

	@Bean
	public DataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/mytest");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUsername("root");
		dataSource.setPassword("410yzg");
		dataSource.setInitialSize(20);
		dataSource.setMaxTotal(50);
		return dataSource;
		
	}
	
	
}
