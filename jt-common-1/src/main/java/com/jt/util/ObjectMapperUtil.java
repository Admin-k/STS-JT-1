package com.jt.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 该工具类解决对象转化中的try-catch问题
 * 1.对象转JSON toJSON
 * 2.JSON转对象    toObject
 * @author ta
 */
public class ObjectMapperUtil {
	//定义成员变量时不允许修改
	//private static final int aaa = 200;
	//是否有线程安全性问题  答案 不会滴 调用的是方法.
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static String toJSON(Object obj) {
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			//如果报错需要转化为运行时异常
			throw new RuntimeException();
		}
		return json;
	}
	
	//用户传入class 返回该类型的对象
	public static <T> T toObject(String json,Class<T> target) {
		T t = null;
		try {
			t = mapper.readValue(json, target);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return t;
	}
}