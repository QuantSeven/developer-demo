package com.pousheng.demo.model.menu;

import framework.generic.mybatis.annotation.Column;
import framework.generic.mybatis.annotation.Table;
import framework.generic.mybatis.model.PersistentModel;

@Table(name = "COM_MENU")
public class Menu implements PersistentModel {

	private static final long serialVersionUID = -4828210984734239758L;
	@Column(name = "MENU_ID", pk = true)
	private String menuId;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PARENT_ID")
	private String parentId;
	@Column(name = "HREF")
	private String href;
	@Column(name = "ICON")
	private String icon;
	@Column(name = "ICON_CLASS")
	private String iconClass;
	@Column(name = "SORT")
	private Integer sort;
	@Column(name = "CREATE_DATE")
	private java.sql.Timestamp createDate;
	@Column(name = "CREATE_USER")
	private String createUser;
	@Column(name = "VISABLE")
	private Integer visable;
	@Column(name = "LEVER")
	private Integer lever;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getIconClass() {
		return iconClass;
	}

	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public java.sql.Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.sql.Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Integer getVisable() {
		return visable;
	}

	public void setVisable(Integer visable) {
		this.visable = visable;
	}

	public Integer getLever() {
		return lever;
	}

	public void setLever(Integer lever) {
		this.lever = lever;
	}
}
