package com.pousheng.demo.service.menu;

import java.util.List;

import com.pousheng.demo.model.menu.Menu;

public interface MenuService {
	
	Menu save(Menu menu);
	
	Menu update(Menu menu);
	
	int delete(Menu menu);

	int deleteByPK(String menuId);
	
	List<Menu> findAll();
	
	Menu findByPK(String menuId);

	List<Menu> findByParentId(String parentId);
	
}
