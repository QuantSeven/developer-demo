package com.pousheng.demo.dao.menu;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.pousheng.demo.model.menu.Menu;

import framework.generic.mybatis.dao.GenericDao;
import framework.generic.mybatis.paginator.domain.PageBounds;

public interface MenuDao extends GenericDao<Menu, String> {

	Menu findByPk(@Param("menuId") String menuId);

	List<Menu> findByParentId(@Param("parentId") String parentId);

	int deleteByPk(@Param("menuId") String menuId);

	List<Menu> findPageByParentId(Object object, PageBounds pageBounds);

}
