package com.jt.controller.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jt.pojo.ItemCat;


@RestController
public class WebJSONPController {
	//跨域访问数据封装格式 callback();
	//获取数据之后转化为json串    json
	@RequestMapping("/web/testJSONP")
	public JSONPObject getJSONP(String callback) {
		ItemCat itemCat=new ItemCat();
		itemCat.setId(1000L);
		return new JSONPObject(callback,itemCat);
	}

}
