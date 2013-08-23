package com.pousheng.demo.dao.group;

import org.apache.ibatis.annotations.Param;

import com.pousheng.demo.model.group.Group;

import framework.generic.mybatis.dao.GenericDao;

public interface GroupDao extends GenericDao<Group, String> {

	Group findByPk(@Param("groupId") String groupId);

	int deleteByPk(@Param("groupId") String groupId);

}
