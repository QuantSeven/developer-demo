package com.pousheng.demo.dao.menu;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.pousheng.demo.model.menu.Menu;

import framework.generic.mybatis.db.DbSqlSessionDaoSupport;
import framework.generic.mybatis.page.Pagination;

@Repository("menuDao")
public class MenuDaoImpl extends DbSqlSessionDaoSupport implements MenuDao {

	@Override
	public int deleteByPK(String menuId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("menuId", menuId);
		return getSqlSession().delete("deleteByPk", paramMap);
	}

	@Override
	public Menu findByPK(String menuId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("menuId", menuId);
		return getSqlSession().selectOne("selectByPK", paramMap);
	}

	@Override
	public List<Menu> findByParentId(String parentId) {
		Map<String, Object> paramMap = Maps.newConcurrentMap();
		paramMap.put("parentId", parentId);
		return getSqlSession().selectList("selectByParentId", paramMap);
	}

	@Override
	public List<Menu> read() {
		return getSqlSession().selectPage("selectByPage", new Pagination());
	}

	@Override
	public Menu insert(Menu menu) {
		return null;
	}

	@Override
	public Menu update(Menu menu) {
		return null;
	}

	@Override
	public int deleteByPk(String menuId) {
		return 0;
	}

}
