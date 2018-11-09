package com.jt.web.thread;

import com.jt.common.po.User;

public class UserThreadLocal {
	private static ThreadLocal<User> userThread = new ThreadLocal<>();
	public static void set(User user){
		userThread.set(user);
	}
	public static User get(){
		return userThread.get();
	}
	//由于gc没有权限回收ThreadLocal.所以调用ThreadLocal时需要
	//额外的注意内存泄漏问题
	public static void remove(){
		userThread.remove();
	}
}
