package com.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private CartMapper cartMapper;

	@Override
	public List<Cart> findCartList(Long userId) {
		
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id", userId);
		return cartMapper.selectList(queryWrapper);
	}

	/**
	 * 修改商品数量 
	 * entity: 修改的值
	 * updateWrapper where条件 user_id=xxxx and item_id=xxx
	 */
	@Override
	@Transactional
	public void updateCartNum(Cart cart) {
		Cart cartDB = new Cart();
		cartDB.setNum(cart.getNum())//修改数量
			  .setUpdated(new Date());
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<>();
		updateWrapper.eq("user_id", cart.getUserId())
					 .eq("item_id", cart.getItemId());
		cartMapper.update(cartDB,updateWrapper);
	}

	/**
	 * 能否直接入库???? 不能,同样的购物行为只有一条记录
	 * user_id item_id
	 * 1.如果当前商品数据库中已经存在,则数量update
	 * 2.如果当前商品数据库中没有,则insert
	 * 
	 */
	@Override
	@Transactional
	public void saveCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("item_id", cart.getItemId())
					.eq("user_id", cart.getUserId());
		Cart cartDB = cartMapper.selectOne(queryWrapper);
		
		if(cartDB==null) {
			//表示用户第一次购买
			cart.setCreated(new Date())
				.setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			//表示用户已经购买过了  update
			int num = cart.getNum() + cartDB.getNum();
			cartDB.setNum(num);
			cartDB.setUpdated(new Date());
			cartMapper.updateById(cartDB);
		}
	}
	
	
	
	
	
}
