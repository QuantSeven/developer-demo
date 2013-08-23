package com.pousheng.demo.web.ui;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PageRequest implements Serializable {

	private static final long serialVersionUID = -3451629128571049965L;
	// -- 公共变量 --//
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	// -- 分页参数 --//
	/** 当前页 */
	protected int pageNo = 0;
	/** 每页显示几条 */
	protected int pageSize = 10;

	/** 排序字段 */
	protected String orderBy = null;

	/** 排序类型 */
	protected String order = DESC;

	/** 分页以外的条件参数 */
	protected Object parameter = new Object();

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

	public String getSortString() {
		return this.orderBy + "." + this.getOrder();
	}
}
