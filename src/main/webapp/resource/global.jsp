<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,org.apache.commons.lang.StringUtils" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="basePath" value="${pageContext.request.contextPath}" scope="session"/>
<c:set var="jsPath" value="${pageContext.request.contextPath}/js/viewui/" scope="session"/> 

<script type="text/javascript">
   /**
   * 全局变量定义
   */
    
	var jsPath = '${jsPath}';//站点根目录地址
	
	var wsBaseUrl = "${basePath}"+"/services";

	
	var tipMsg={};//国际化消息内容
	
	var btn={};  //button 按钮显示文本
	
	var msg={}; 
	
	var txt={}; 
	
   var  ui_config={};	
	
	var common = {};

</script>