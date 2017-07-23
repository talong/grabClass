package edu.school.exceptionAndHandler;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

/**
 * PersistenceExceptionTranslationPostProcessor是bean的后置处理器，
 * 它会在在@Repository注解的类上添加一个通知器，来捕获任何平台相关的异常并以Spring
 * 非检查型数据访问异常的形式重新抛出
 * @author ThinkPad
 *
 */
public class RepositoryExceptions {

	@Bean
	public BeanPostProcessor persistenceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
