


package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 *   关于框架编码的说明:
	 * 	使用旧的SSM框架时3-4版本:
	 * 	1.如果返回数据为String.则将
	 * 	数据通过@ResponseBody返回时.采用ISO-8859-1编码格式
	 *     所以返回数据必定乱码.
	 *  2.如果返回数据为对象时(pojo/vo),采用UTF-8格式编码
	 *  
	 *      当springBoot时.返回的数据都是UTF-8
	 *      
	 *  POST乱码问题:response.setContentType("text/html;charset=utf-8");
	 *  @RequestMapping(value="/queryItemName",
		produces="text/html;charset=utf-8")
		public String findItemCatNameById(Long itemCatId) {
			
			return itemCatService.findItemCatNameById(itemCatId);
		}
	 *  
	 * @param itemCatId
	 * @return
	 */
	
	//实现商品分类信息查询
	@RequestMapping(value="/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		
		return itemCatService.findItemCatNameById(itemCatId);
	}
	
	//实现商品分类信息树形结构展现  ?id=350
	/**
	 * defaultValue:如果没有传递参数,则设定默认值
	 * name:参数名称
	 * required:true/false 是否必须传递参数
	 * value:表示参数名称
	 * @param parentId
	 * @return
	 */
	@RequestMapping("/list")
	public List<EasyUITree> findItemCatList
	(@RequestParam(name="id",defaultValue="0") Long parentId){
		//1.获取一级商品分类信息
		return itemCatService.findItemCatList(parentId);
	}
}
