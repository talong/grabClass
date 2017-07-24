package edu.school.servlet3;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import edu.school.config.SpringRootConfig;
import edu.school.config.SpringWebConfig;

public class MyWebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { SpringRootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { SpringWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}


	/**
	 * ����Ŀ��������DispatcherServlet�ĳ�ʼ�����������弤���profile
	 * ���ڼ��ɲ������ϣ�����ʹ��@ActiveProfilesע������ʹ�õ�profile
	 */
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		super.onStartup(servletContext);
		servletContext.setInitParameter("spring.profiles.default", "mysql");
		servletContext.setInitParameter("spring.profiles.active", "mysql");
	}
	
}