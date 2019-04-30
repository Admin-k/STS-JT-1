package com.jt.intercepter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.jt.pojo.User;
import com.jt.util.ObjectMapperUtil;
import com.jt.util.UserThreadLocal;

import redis.clients.jedis.JedisCluster;
/**
 * @author PC-拦截器
 * 完成用户校验,如果没有登录
 * 
 * */
@Component
public class UserInterceptor implements HandlerInterceptor{
	@Autowired
	private JedisCluster jedis;
	/**
	 * 用户拦截器实现步骤
	 * 	1.首先获取用户的cookie数据
	 * 	2.判断用户是否登录
	 * 		如果用户没有登录,则重定向到用户登录页面
	 * 		如果用户已经登录:
	 * 			则判读redis中是否有数据
	 * 				有:表示用户已经登录成功,放行
	 * 				无:
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//1.获取cookie数据
		String token=null;
		Cookie[] cookies=request.getCookies();
		for (Cookie cookie : cookies) {
			if ("JT_TICKET".equals(cookie.getName())) {
				token=cookie.getValue();
				break;
			}
			
		}
		if (!StringUtils.isEmpty(token)) {
			//判断jedis中是否存在缓存数据
			System.out.println("token的值为:"+token);
			String userJSON=jedis.get(token);
			
			if (!StringUtils.isEmpty(userJSON)) {
			//程序执行到这里表示用户已经登录
				System.out.println("userJSON的值为:"+userJSON);
			User user = ObjectMapperUtil.toObject(userJSON, User.class);
			UserThreadLocal.set(user);
			//System.out.println("拦截器启动,将数据传入thredLocal");
				return true;
			}
		}
		//程序执行到此位置,表示没有登录
		response.sendRedirect("/user/login.html");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		UserThreadLocal.remove();	//删除线程数据
		//System.out.println("用户本次调用完成,清除数据!!!!!!!");
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
