package com.jt.manage.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;

@Table(name="tb_item_cat")
public class ItemCat extends BasePojo{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;			//商品分类Id
	
	//@Column(name="parent_id") //开启驼峰映射规则，该注解可以省略；若未开启，必须加上
	private Long parentId;		//商品分类父级Id
	private String name;		//商品分类名称
	private Integer status;		//商品分类状态
	private Integer sortOrder;	//排序号
	private Boolean isParent;	//是否为父级，使用该属性判断是否为一二级分类
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(Integer sortOrder) {
		this.sortOrder = sortOrder;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
}
