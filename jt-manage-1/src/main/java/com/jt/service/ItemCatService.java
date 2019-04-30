package com.jt.service;

import java.util.List;

import com.jt.vo.EasyUITree;

public interface ItemCatService {

	String findItemCatNameById(Long itemCatId);

	List<EasyUITree> findItemCatList(Long parentId);
	
	//添加redis缓存处理操作
	List<EasyUITree> findItemCatCacheList(Long parentId);

}
