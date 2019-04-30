package com.jt.vo;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain=true)
public class EasyUIList {
	private Integer total;	//记录总数
	private List<?> rows;	//保存商品信息
	
	public EasyUIList(){
		
	}
	
	public EasyUIList(Integer total, List<?> rows) {
		super();
		this.total = total;
		this.rows = rows;
	}
}
