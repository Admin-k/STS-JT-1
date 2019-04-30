package com.jt.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;

public interface ItemMapper extends BaseMapper<Item>{
	/**
	 * 关于Mybatis取值传参问题
	 * 规定:Mybatis不允许多值传参.只能将多值转化为单值.
	 * 1.把参数封装为POJO对象
	 * 2.封装为Map集合
	 * 3.封装为array/list
	 * @param start
	 * @param rows
	 * @return
	 */
	List<Item> findItemListByPage(@Param("start")Integer start,@Param("rows")Integer rows);
	
}
