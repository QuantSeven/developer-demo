package com.pousheng.demo.web.controller.base;

import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.pousheng.demo.constant.MessageStatus;
import com.pousheng.demo.web.ui.Json;

import framework.generic.utils.ip.IpUtil;

/**
 * 所有控制器的基类，提供了国际化的消息处理，ajax返回消息(如有需要可以在此基础上扩展所有公共的方法)
 * <p>
 * 项目名称：bpms
 * </p>
 * <p>
 * 版权：2013-广州扬基信息科技有限公司
 * </p>
 * 
 * @version 1.0, 2013-1-8 上午10:15:38
 * @author quanyongan
 */
@Controller
public class AbstractController {

	// 首先注入Spring容器中的ReloadableResourceBundleMessageSource资源文件读取类
	@Resource
	protected ReloadableResourceBundleMessageSource messageSource;

	@Resource
	protected SessionLocaleResolver localeResolver;

	protected Locale getLocale() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
	}

	/**
	 * 获取用户的IP
	 */
	public String getIp() {
		return IpUtil.getClientRealIP(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
	}

	/**
	 * 获取国际化文件的内容
	 * 
	 * @param code
	 *            国际化文件的代码
	 * @return 国际化的内容
	 */
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, getLocale());
	}

	/**
	 * 获取国际化文件的内容
	 * 
	 * @param code
	 *            国际化文件的代码
	 * @param args
	 *            参数
	 * @return 国际化的内容
	 */
	public String getMessage(String code, Object[] args) {
		return messageSource.getMessage(code, args, getLocale());
	}

	/**
	 * 获取国际化文件的内容
	 * 
	 * @param code
	 *            国际化文件的代码
	 * @param args
	 *            参数
	 * @param defaultMessage
	 *            默认的信息
	 * @return 国际化的内容
	 */
	public String getMessage(String code, Object[] args, String defaultMessage) {
		return messageSource.getMessage(code, args, defaultMessage, getLocale());
	}

	/**
	 * Ajax请求操作完成
	 * 
	 * @param statusCode
	 *            状态代码
	 * @param message
	 *            信息
	 * @return Json
	 */
	public Json done(Integer statusCode, String message) {
		return new Json(message, statusCode);
	}

	/**
	 * Ajax请求操作完成
	 * 
	 * @param forwardUrl
	 *            跳转的URL
	 * @param statusCode
	 *            状态代码
	 * @param message
	 *            信息
	 * @return Json
	 */
	public Json done(String forwardUrl, String message, Integer statusCode) {
		return new Json(forwardUrl, message, statusCode);
	}

	/**
	 * 成功消息
	 * 
	 * @param message
	 *            信息
	 * @return Json
	 */
	public Json success(String message) {
		return done(MessageStatus.OK, message);
	}

	/**
	 * 成功消息
	 * 
	 * @param forwardUrl
	 *            跳转的URL
	 * @param message
	 *            信息
	 * @return Json
	 */
	public Json success(String forwardUrl, String message) {
		return done(forwardUrl, message, MessageStatus.OK);
	}

	/**
	 * 错误消息
	 * 
	 * @param message
	 *            信息
	 * @return Json
	 */
	public Json error(String message) {
		return done(MessageStatus.ERROR, message);
	}
	/**
	 * 错误消息
	 * 
	 * @param forwardUrl
	 *            跳转的URL
	 * @param message
	 *            信息
	 * @return Json
	 */
	public Json error(String forwardUrl, String message) {
		return done(forwardUrl, message, MessageStatus.ERROR);
	}

	/**
	 * 
	 * @Title: setLocale
	 * @Description: 设置语言
	 * @param request
	 * @param response
	 * @param languageCode
	 */
	protected void setLocale(HttpServletRequest request, HttpServletResponse response, String languageCode) {
		String language = languageCode.substring(0, languageCode.indexOf("_"));
		String country = languageCode.substring(languageCode.indexOf("_") + 1);
		Locale newLocale = new Locale(language, country);
		localeResolver.setLocale(request, response, newLocale);

	}

	public void setLocaleResolver(SessionLocaleResolver localeResolver) {
		this.localeResolver = localeResolver;
	}

}
