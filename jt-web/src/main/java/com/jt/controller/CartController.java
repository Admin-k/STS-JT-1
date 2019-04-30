package com.jt.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jt.pojo.Cart;
import com.jt.service.CartService;
import com.jt.util.UserThreadLocal;
import com.jt.vo.SysResult;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Reference(timeout=3000,check=false)
	private CartService cartService;
	
	//实现购物车列表数据展现
	@RequestMapping("/show")
	public String show(Model model) {
		//获取当前用户信息
		Long userId = UserThreadLocal.get().getId();	//暂时写死,后期维护
		//先获取购物车信息
		List<Cart> cartList = 
				cartService.findCartList(userId);
		model.addAttribute("cartList", cartList);
		return "cart";
	}
	
	//实现购物车商品数量的修改
	//如果使用restFul风格有多个参数,并且和pojo属性一致.则可以使用
	//pojo对象接收
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateNum(Cart cart) {
		try {
			//Long userId = 7L;
			cart.setUserId(UserThreadLocal.get().getId());
			cartService.updateCartNum(cart);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	@RequestMapping("/add/{itemId}")
	public String saveCart(Cart cart) {
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";	
		//重定向:跳转到购物车页面
	}
		
	
}
