package com.pousheng.demo.dao.menu;

import java.util.List;

import com.pousheng.demo.model.menu.Menu;

public interface MenuDao {

	int deleteByPK(String menuId);

	Menu findByPK(String menuId);

	List<Menu> findByParentId(String parentId);

	List<Menu> read();

	Menu insert(Menu menu);

	Menu update(Menu menu);

	int deleteByPk(String menuId);
}
