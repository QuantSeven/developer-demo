<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html lang="en" >
	<head>
	<title>业务流程管理平台</title> 
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/resource/meta.jsp" %>
    <%@ include file="/resource/global.jsp" %>
    <%@ include file="/resource/include-base-styles.jsp" %>
    <%@ include file="/resource/include-plugin-js.jsp" %>
 	<%@ include file="/resource/include-pousheng-js.jsp" %> 
   	<link href="${jsPath }/pousheng/themes/default/pousheng.css" rel="stylesheet">
    <!-- 开发平台地址 -->
	<script src="${jsPath }/lib/seajs/sea.js" 
			data-config="${jsPath }/lib/seajs/config.js"
        	data-main="${jsPath }/module/common/main.js"></script> 

	<script type="text/javascript">
		function loginOut(){
			 pousheng.confirm("&nbsp;&nbsp;确定要注销吗?",function(r) {
    	    	 if(r) {
    	    		 window.parent.location.href="logout";
    	    	 } else {
    	    		 $(this).dialog("close");
    	    	 }
    	    });
		}
	</script>
</head>
<body>
	<div data-layout="">
		<div region="north" border="false" style="height: 56px;">
			<div id="topPanel">
				<div class="logo-sets">
					<img alt="logo" src="themes/img/toplogo.png" id="logo">
				</div>
				<div class="topBanner">
					<div class="exit">
						<i class="icon-off icon-white"></i> 
						<a href="javascript:loginOut()" style="color: #FFFFFF">注销</a>
					</div>
					<%-- 
					<div class="splitter"></div>
					<div class="user-info">
						<i class="icon-pencil icon-white"></i>
						<a href="${modifyPasswordUrl}" style="color: #FFFFFF" target="_blank">修改密码</a>
					</div> 
					--%>
					<div class="splitter"></div>
					<div class="user-info">
						<i class="icon-user icon-white"></i>
						<font color="#FFFFFF">${username}</font>
					</div>
					<!-- 把流程建模定义在"模板管理"模块中，以下为判断是否有权限进入流程建模页面 -->
					<!-- <shiro:hasPermission name="processModel:design">
						<div class="splitter"></div>
						<div class="user-info">
							<i class="icon-qrcode icon-white"></i> 
							<a href="design" style="color: #FFFFFF" target="_blank">流程建模</a>
						</div>
					</shiro:hasPermission> -->
				</div>
			</div>
		</div>
		
		<div region="west" split="true" headerCls="left-menu" title="导航菜单" style="width: 150px;">
			<div id="leftmenu"></div>
			<script id="leftMenuTemplate" type="text/x-jsrender">
            	<div class="group">
	            	<h3 index={{>#index}} style="border:0px;">
						<a class='header' title="{{:name}}">
							<i class="menu1 {{:iconClass}}"/><span class="title">{{:name}}</span> 
	                		<i class="icon-chevron-right"></i>
						</a>
					</h3>
                	<div style="border:0px;">
                		<ul class="menu-nav">
                    		{{for children}}
	                  			<li> 
                         			<a href="{{:url}}" type="menu" title="{{:name}}">
										<i class="menu2 {{:iconClass}}"></i>{{:name}}
									</a>
								</li>
                    		{{/for}}
                		</ul>
                	</div>
				</div>
			</script>
		</div>
		
		<div region="center">
			<div id="indexTab" border="false">
				<div id="tabs-1" title="首页" />
					<div style="padding-left:15px;">
						<h3 style="color:#2C77A9">${username}，欢迎您登录工作流管理平台！</h3>
						IP地址：${ip}
						&nbsp;&nbsp;
						当前时间：
						<span id="mytime"></span>
						<script type="text/javascript">
							setInterval("mytime.innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);
						</script>
					</div>
				   <img src="themes/img/welcome3.jpg"  style="margin-left: 100px;"/>
				</div>
			</div>
		</div>
		
		<div region="south" style="height:30px;">
			<div id="footer">POUSHENG All Rights Reserved.[ Version 2.20 ]</div>
		</div>	
	</div>
  	<%@ include file="/resource/application-language.jsp" %>
</body>
</html>






  
