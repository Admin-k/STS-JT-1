package com.jt.config;

import javax.servlet.Servlet;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebServletConfig {

	/**
	 * 	由于伪静态实现servlet配置.
	 * 	添加2个请求路径
	 * 		1.拦截后缀型请求   *.html
	 * 		2.添加拦截前缀/service/*
	 * 	以下配置相当于修改了web.xml中的servlet配置
	 * 	
	 * @param dispatcherServlet
	 * @return
	 * 
	 * SpringBoot的伪静态默认是关闭的
	 * 需要手动开启configurer
	 */
	@Bean
	public ServletRegistrationBean<Servlet> servletConfig(DispatcherServlet dispatcherServlet){
		ServletRegistrationBean<Servlet> servletBean = 
				new ServletRegistrationBean<>(dispatcherServlet);
		servletBean.getUrlMappings().clear();				//清空之前的默认配置
		servletBean.addUrlMappings("/service/*","*.html");	//添加2个拦截路径
		return servletBean;
	}
}
