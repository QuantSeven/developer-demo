package com.pousheng.demo.model.group;

import java.util.HashSet;
import java.util.Set;

import com.pousheng.demo.model.user.User;

import framework.generic.mybatis.annotation.Column;
import framework.generic.mybatis.annotation.Table;
import framework.generic.mybatis.model.PersistentModel;

@Table(name = "t_group")
public class Group implements PersistentModel {

	private static final long serialVersionUID = 1L;

	@Column(name = "GROUP_ID", pk = true)
	private String groupId;
	@Column(name = "GROUP_NAME")
	private String groupName;
	@Column(name = "STATUS")
	private Boolean status;
	@Column(name = "DESCRIPTION")
	private String description;

	private Set<User> users = new HashSet<User>(0);

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
}
