package com.jt.manage.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Table;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
@Table(name="tb_item_param")
public class ItemParam implements Serializable{
	private static final long serialVersionUID = -4839922125289846394L;
	
	private Integer id;
	private Integer item_cat_id;
	private Text param_data;
	private Date updated;
	private Date created;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getItem_cat_id() {
		return item_cat_id;
	}
	public void setItem_cat_id(Integer item_cat_id) {
		this.item_cat_id = item_cat_id;
	}
	public Text getParam_data() {
		return param_data;
	}
	public void setParam_data(Text param_data) {
		this.param_data = param_data;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
