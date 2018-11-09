package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Cart;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;
import com.jt.web.thread.UserThreadLocal;
@RequestMapping("/cart")
@Controller
public class CartController {
	@Autowired
	private CartService cartService;
	@RequestMapping("/show")
	public String show(Model model){
		//根据userId查询购物车信息
		Long userId = UserThreadLocal.get().getId();
		List<Cart> cartList = cartService.findCartByUserId(userId);
		//将数据添加到request域中
		model.addAttribute("cartList", cartList);
		return "cart";
	}

	@RequestMapping("/add/{itemId}")
	public String saveCart(@PathVariable Long itemId,Cart cart){
		Long userId = UserThreadLocal.get().getId();
		cart.setItemId(itemId);
		cart.setUserId(userId);
		cartService.saveCart(cart);
		return "redirect:/cart/show.html";
	}
	//购物数量的修改
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(@PathVariable Long itemId,@PathVariable Integer num){

		try {
			Long userId = UserThreadLocal.get().getId();
			Cart cart = new Cart();
			cart.setItemId(itemId);
			cart.setUserId(userId);
			cart.setNum(num);
			cartService.updateCart(cart);
			SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "商品修改失败");
	}

	@RequestMapping("/delete/{itemId}")
	public String deleteCartByUI(@PathVariable Long itemId){
		Cart cart = new Cart();
		cart.setItemId(itemId);
		cartService.deleteCartByUI(cart);
		return "redirect:/cart/show.html";	

	}
}
