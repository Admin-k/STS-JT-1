package com.jt.pojo;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;
@TableName("tb_user")
@Data
@Accessors(chain=true)
public class User extends BasePojo{
	@TableId(type=IdType.AUTO)	//主键自增
	private Long id;			//用户id号
	private String username;	//用户名
	private String password ;	//用户密码
	private String phone ;		//电话
	private String email;		//邮件
	
}
