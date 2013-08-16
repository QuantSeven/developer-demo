package com.pousheng.demo.service.menu;

import java.util.List;

import com.pousheng.demo.model.menu.Menu;
import com.pousheng.demo.web.ui.DataGrid;
import framework.generic.mybatis.page.Pagination;

public interface MenuService {

	int deleteByPk(String id);

	void update(Menu menu);

	Menu findByPK(String menuId);

	Menu insert(Menu menu);

	DataGrid getPage(Pagination pagination);

	List<Menu> findAll();
}
