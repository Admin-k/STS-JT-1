package com.jt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	/*
	 * param :用户输入内容
	 * type :  数据类型 1.username 2.phone 3.email
	 * sql: select count(*) from tb_user where username="张三";
     *经过分析:  首先应该将1 2 3转化为具体的数据库字段.
	 * */
	@Override
	public Boolean findCheckUser(String param, Integer type) {
		String cloum=null;
		switch (type) {
		case 1:
			cloum="username";
			break;
		case 2:
			cloum="phone";
			break;
		case 3:
			cloum="email";
			break;
		}
		//根据数据库的结果分析,如果结果为0,返回false,否则返回true
		//根据数据库结果分析如果总数为0 返回false
	    //否则返回true
		//QueryWrapper是mybatis_plus自带的条件构造器,自动判断单表数据的信息
		
		//创建条件构造器对象
	     QueryWrapper<User> queryWrapper =new QueryWrapper<>();
	     //进行eq条件判断
	     System.out.println(queryWrapper.eq(cloum, param));
	     //接收数据条数
	     int count = userMapper.selectCount(queryWrapper);
	     System.out.println(count);
	     
	     return count==0?false:true;	
	}
	
}
