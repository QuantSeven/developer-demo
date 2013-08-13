package com.pousheng.demo.web.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.pousheng.demo.constant.MessageStatus;

public class Json implements Serializable {
	private static final long serialVersionUID = -32336840010189686L;

	private String message = ""; // 返回消息
	private Integer statusCode = MessageStatus.OK; // 状态 默认成功
	private String forwardUrl = ""; // 跳转url
	private Object object = null; // 实体对象
	@SuppressWarnings("rawtypes")
	private List<?> dataList = new ArrayList(0);// 实体对象列表

	public Json() {

	}

	public Json(String message) {
		this.message = message;
	}

	public Json(String forwardUrl, String message) {
		this.message = message;
		this.forwardUrl = forwardUrl;
	}

	public Json(String message, Integer statusCode) {
		this.message = message;
		this.statusCode = statusCode;
	}

	public Json(String forwardUrl, String message, Integer statusCode) {
		this.message = message;
		this.forwardUrl = forwardUrl;
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public List<?> getDataList() {
		return dataList;
	}

	public void setDataList(List<?> dataList) {
		this.dataList = dataList;
	}

}
