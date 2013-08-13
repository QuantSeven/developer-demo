package com.pousheng.demo.service.menu;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pousheng.demo.dao.menu.MenuDao;
import com.pousheng.demo.model.menu.Menu;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private MenuDao menuDao;

	/** 通过spring注入MenuDao */
	@Resource
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@Override
	public Menu save(Menu menu) {
		return menuDao.insert(menu);
	}

	@Override
	public Menu update(Menu menu) {
		return menuDao.update(menu);
	}

	@Override
	public int delete(Menu menu) {
		return 0;
	}

	@Override
	public int deleteByPK(String menuid) {
		return menuDao.deleteByPK(menuid);
	}

	@Override
	public List<Menu> findAll() {
		return menuDao.read();
	}

	@Override
	public Menu findByPK(String menuid) {
		return menuDao.findByPK(menuid);
	}

	@Override
	public List<Menu> findByParentId(String parentId) {
		return menuDao.findByParentId(parentId);
	}
}
