package com.jt.controller;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.User;
import com.jt.service.DoubboUserService;
import com.jt.vo.SysResult;

import redis.clients.jedis.JedisCluster;


@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private JedisCluster jedisCluster;
	//消费者负责调用接口
	@Reference(timeout=3000,check=false)	//为接口创建代理对象
	private DoubboUserService  userService;
	
	
	//实现页面的通用跳转
	@RequestMapping("/{moduleName}")
	public String moduleName(@PathVariable String moduleName ) {
		
		return moduleName;
	}
	
	//实现用户新增
	@RequestMapping("/doRegister")
	@ResponseBody
	public SysResult saveUser(User user) {
		try {
			userService.saveUser(user);
			return SysResult.ok();		
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	
	//实现用户单点登录
	/**实现用户单点登录.
	 * 
	 * @param user
	 * @return
	 * 
	  * 返回值要求:
	 * 		如果用户名或密码错误.则token为null
	  *  	如果用户填写正确, token中有数据
	  * 业务流程:
	 * 	1.判断数据是否有效
	 *  2.如果有效则应该将数据保存到cookie中
	 *  
	  * 关于Cookie补充知识:
	 * 	1.setPath("/")
	 * 		只要是jt项目中的页面都可以访问这个cookie
	 * 	 .setPath("/sso/")
	 * 		只有位于sso/下的页面才可以访问这个cookie
	 * 	 
	 */
	@RequestMapping("/doLogin")
	@ResponseBody
	public SysResult login(User user,HttpServletResponse response) {
		try {
			String token=userService.findUserById(user);
			//下行代表表示用户名和密码正确
			if (!StringUtils.isEmpty(token)) {
				
				Cookie cookie=new Cookie("JT_TICKET", token);
				cookie.setMaxAge(7*24*60*60);
				cookie.setPath("/");//一般默认写/
				response.addCookie(cookie);
				
				return SysResult.ok();	
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.fail();
	}
	
	//实现用户登出操作  删除redis:token/cookie
		/**
		 * 1.先获取用户浏览器端的cookie数据    JT_TICKET
		 * 2.根据token数据删除redis
		 * 3.删除cookie
		 * @return
		 */
		@RequestMapping("logout")
		public String logout(HttpServletRequest request,
				HttpServletResponse response) {
			
			String token = null;
			//1.获取cookie数据
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie : cookies) {
				
				if("JT_TICKET".equals(cookie.getName())) {
					token = cookie.getValue();
					break;	//结束循环
				}
			}
			//2.删除redis数据
			jedisCluster.del(token);
			
			//3.删除cookie  0:立即删除   -1:关闭会话时删除
			Cookie cookie = new Cookie("JT_TICKET","");
			cookie.setMaxAge(0);
			cookie.setPath("/");
			response.addCookie(cookie);
			//重定向到系统首页
			return "redirect:/index.html";
		}
}
