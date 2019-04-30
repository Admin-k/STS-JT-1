package com.jt.util;
//该对象保存 的是用户信息

import com.jt.pojo.User;

public class UserThreadLocal {
	private static ThreadLocal<User> thread=
									new ThreadLocal<>();
	public static void set(User user) {
		thread.set(user);
	}
	
	public static User get() {
		return thread.get();
	}
	
	public static void remove() {
		thread.remove();
	}
}
