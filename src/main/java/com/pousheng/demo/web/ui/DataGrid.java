package com.pousheng.demo.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataGrid implements Serializable {

	private static final long serialVersionUID = -7659036762912339409L;

	private int totalCount = 0;
	@SuppressWarnings("rawtypes")
	private List<?> dataList = new ArrayList();

	public DataGrid() {

	}

	public DataGrid(int totalCount, List<?> dataList) {
		this.totalCount = totalCount;
		this.dataList = dataList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

}
