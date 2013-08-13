package com.pousheng.demo.model.user;

import framework.generic.mybatis.annotation.Column;
import framework.generic.mybatis.annotation.Table;
import framework.generic.mybatis.db.PersistentModel;

@Table(name="T_USER")
public class User implements PersistentModel {

	private static final long serialVersionUID = -9218985453073185112L;

	@Column(name="USER_ID",pk=true)
	private String userId;
	@Column(name="USER_NAME")
	private String userName;
	@Column(name="PASSWORD")
	private String password;
	@Column(name = "EMAIL")
	private String email;
	@Column(name="PHONE")
	private String phone;
	@Column(name="ADDRESS")
	private String address;
	@Column(name="STATUS")
	private Integer status;
	@Column(name="DESCRIPTION")
	private String description;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
