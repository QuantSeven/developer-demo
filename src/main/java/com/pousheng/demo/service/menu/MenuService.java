package com.pousheng.demo.service.menu;

import java.util.List;

import com.pousheng.demo.model.menu.Menu;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.PageRequest;

public interface MenuService {

	int remove(String menuId);

	void modify(Menu menu);

	Menu get(String menuId);

	Menu create(Menu menu);

	List<Menu> read();

	DataGrid getDatagrid(PageRequest pageRequest);
}
