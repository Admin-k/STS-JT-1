package com.jt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 *  1.关于POJO中属性使用包装类型的原因
 * 	Integer age 可以接收null
 * 	int     age 如果参数为null,则为默认值 为0
 * 
 *  因为现在使用Mapper工具做面向对象的数据库操作.
 * Mapper工具中要求,操作不为null的数据.
 *   将不为null的数据当做参数使用.
 * 
 *  例子:以查询为例
 * select * from user id=100 and age=0 结果有误.
 * @author ta
 *
 *
 *	2.关于企业开发中的钱数据类型问题:
 *	在实际开发中很少使用dubbo.
 *	理由如下
 *		1.数据库总占用的空间较大.
 *		2.double计算时有精度问题. 
 *		0.0000000002 +
 *		0.9999999998 = 理论值  1.0
 *					    实际值: 0.999999999
 *		3.使用double的计算速度慢.
 *	实际开发的使用:
 *		一般会将数据扩大100倍.用户展现时缩小100倍.
 *	
 */
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性
@TableName("tb_item")
@Data
@Accessors(chain=true)
public class Item extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;				//商品id
	private String title;			//商品标题
	private String sellPoint;		//商品卖点信息
	private Long   price;			//商品价格 Long > dubbo
	private Integer num;			//商品数量
	private String barcode;			//条形码
	private String image;			//商品图片信息   1.jpg,2.jpg,3.jpg
	private Long   cid;				//表示商品的分类id
	private Integer status;			//1正常，2下架
	
	//为了满足页面调用需求,添加get方法
	public String[] getImages(){
		
		return image.split(",");
	}
}
