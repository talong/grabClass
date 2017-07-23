package edu.school.config.mbatis;

import java.util.Properties;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.mapper.MapperScannerConfigurer;

@Configuration
public class MybatisConfig {

	@Bean
	public SqlSessionFactoryBean sessionFactory(DataSource dataSource){
		
		SqlSessionFactoryBean sfb = new SqlSessionFactoryBean();
		sfb.setDataSource(dataSource);
		//sfb.setMapperLocations();
		Properties props = new Properties();
		props.setProperty("dialect", "org.hibernate.dialect.HSQLDialect");
		sfb.setConfigurationProperties(props);
		return sfb;
		
	}
	
	@Bean
	public MapperScannerConfigurer msc(){
		
		MapperScannerConfigurer sfb = new MapperScannerConfigurer();
		sfb.setBasePackage("edu.school.mapper");
		Properties props = new Properties();
		props.setProperty("mappers", "tk.mybatis.mapper.common.Mapper");
		sfb.setProperties(props);
		return sfb;
		
	}
	
}
