package com.pousheng.demo.dao.menu;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pousheng.demo.model.menu.Menu;

import framework.generic.mybatis.dao.GenericDao;

public interface MenuDao extends GenericDao<Menu, String> {

	Menu findByPK(@Param("menuId") String menuId);

	List<Menu> findByParentId(@Param("parentId") String parentId);

	int deleteByPk(@Param("menuId") String menuId);

}
