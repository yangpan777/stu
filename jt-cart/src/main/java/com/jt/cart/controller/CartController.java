package com.jt.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.service.CartService;
import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;
@RequestMapping("/cart")
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	private static ObjectMapper objectMapper = new ObjectMapper();

	@RequestMapping("/query/{userId}")
	@ResponseBody
	public SysResult findCartByUserId(@PathVariable Long userId){
		try {
			List<Cart> cartList = cartService.findCartByUserId(userId);
			return SysResult.oK(cartList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车数据查询失败");
	}
	@RequestMapping("/save")
	@ResponseBody
	public SysResult saveCart(Cart cart){
		try {
			cartService.saveCart(cart);
			return SysResult.oK(cart);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车添加失败");
	}
	@RequestMapping("/update/num")
	@ResponseBody
	public SysResult updateCartNum(String cartJSON){
		try {
			Cart cart = objectMapper.readValue(cartJSON, Cart.class);
			cartService.updateCartNum(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品更新失败");
	}
	@RequestMapping("/delete")
	public SysResult deleteCartByUI(String cartJSON){
		try {
			Cart cart = objectMapper.readValue(cartJSON, Cart.class);
			cartService.deleteCartByUI(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品删除失败");
	}
}
