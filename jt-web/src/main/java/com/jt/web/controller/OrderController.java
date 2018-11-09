package com.jt.web.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.po.Cart;
import com.jt.common.po.Order;
import com.jt.common.vo.SysResult;
import com.jt.web.service.CartService;
import com.jt.web.service.OrderService;
import com.jt.web.thread.UserThreadLocal;
@RequestMapping("/order")
@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	@Autowired
	private CartService cartService;

	@RequestMapping("/create")
	public String create(Model model){
		Long userId = UserThreadLocal.get().getId();
		List<Cart> cartList = cartService.findCartByUserId(userId);
		model.addAttribute("carts", cartList);
		System.out.println("a"+model);
		return "order-cart";
	}
	/**
	 * 实现订单的入库操作
	 */
	@RequestMapping("/submit")
	@ResponseBody
	public SysResult saveOrder(Order order){
		try {
			Long userId = UserThreadLocal.get().getId();
			order.setUserId(userId);
			String orderId = orderService.saveOrder(order);
			System.out.println("123="+orderId);
			if(!StringUtils.isEmpty(orderId)){
				return SysResult.oK(orderId);
			}	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "订单提交失败");
	}

	@RequestMapping("/success")
	public String findOrderById(String id,Model model){
		Order order = orderService.findOrderById(id);
		model.addAttribute("order",order);
		return "success";
	}
}
