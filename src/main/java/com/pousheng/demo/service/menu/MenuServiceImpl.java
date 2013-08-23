package com.pousheng.demo.service.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pousheng.demo.dao.menu.MenuDao;
import com.pousheng.demo.model.menu.Menu;
import com.pousheng.demo.web.ui.DataGrid;
import com.pousheng.demo.web.ui.PageRequest;

import framework.generic.mybatis.paginator.domain.Order;
import framework.generic.mybatis.paginator.domain.PageBounds;
import framework.generic.mybatis.paginator.domain.PageList;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;

	@Resource
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public int remove(String id) {
		return menuDao.deleteByPk(id);
	}

	@Override
	public void modify(Menu menu) {
		menuDao.updateEntity(menu);
	}

	@Override
	public Menu get(String menuId) {
		return menuDao.findByPk(menuId);
	}

	@Override
	public Menu create(Menu menu) {
		menuDao.insertEntity(menu);
		return menu;
	}

	@Override
	public DataGrid getDatagrid(PageRequest pageRequest) {
		PageBounds pageBounds = new PageBounds(pageRequest.getPageNo(), pageRequest.getPageSize(),Order.formString(pageRequest.getSortString()));
		List<Menu> menus = menuDao.findPageByParentId(pageRequest.getParameter(), pageBounds);
		PageList<Menu> pageList = (PageList<Menu>) menus;
		return new DataGrid(pageList.getPaginator().getTotalCount(), menus);
	}

	@Override
	public List<Menu> read() {
		return menuDao.findAll();
	}
}
