package com.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/*
 * 如果程序启动报错数据源相关的错误,则springboot会根据pom.xml中的配置
 * 去加载数据源,但是jt-web只是引入jar包,不需要数据源
 * 
 * 1.解决策略:在yml中添加数据源配置
 * 2.在soringboot启动时不加载数据源配置
 * */
@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)//括号里不加载数据源的配置
public class SpringBootRun {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootRun.class, args);
	}
}
