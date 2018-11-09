package com.jt.manage.vo;

import java.io.Serializable;

public class EasyUITree implements Serializable{
	private static final long serialVersionUID = 7700390744918422193L;
	private Long id;		//树形结构的Id
	private String Text;	//树形结构的名称
	private String state;	//节点状态     open/closed
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return Text;
	}
	public void setText(String text) {
		Text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
