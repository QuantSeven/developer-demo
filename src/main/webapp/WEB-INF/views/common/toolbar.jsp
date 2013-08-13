<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="btn-group toolButton">
	<c:choose>
		<c:when test="${not empty param.showComBtn ? param.showComBtn : 'true'}">
			<shiro:hasPermission name="${param.moduleName}:add">
				<a class="btn " name="add" href="${not empty param.addUrl ? param.addUrl : 'javascript:void(0);' }" data-rel='btn' >
			</shiro:hasPermission>
			<shiro:lacksPermission name="${param.moduleName}:add">
				<a class="btn disabled" data-rel='btn' >
			</shiro:lacksPermission>
				<i class="icon-plus"></i>
				<spring:message code="common.btn.add"/>
			</a>
			
			<shiro:hasPermission name="${param.moduleName}:edit">
				<a class="btn" name="edit" href="${not empty param.editUrl ? param.editUrl : 'javascript:void(0);' }" data-rel='btn'>
			</shiro:hasPermission>
			<shiro:lacksPermission name="${param.moduleName}:edit">
				<a class="btn disabled" data-rel='btn' >
			</shiro:lacksPermission>
				<i class="icon-edit"></i>
				<spring:message code="common.btn.edit"/>
			</a> 	
			
			<shiro:hasPermission name="${param.moduleName}:delete">
				<a class="btn" name="delete" data-rel='btn' href="${not empty param.deleteUrl ? param.deleteUrl : 'javascript:void(0);' }">
			</shiro:hasPermission>
			<shiro:lacksPermission name="${param.moduleName}:delete">
				<a class="btn disabled" data-rel='btn' >
			</shiro:lacksPermission>
				<i class="icon-trash"></i>
				<spring:message code="common.btn.delete"/>
			</a> 
			
			<!-- 需要扩展的其他操作按钮组 -->
			${param.extBtnGroup }
			
			<shiro:hasPermission name="${param.moduleName}:view">
			<a class="btn" name="view" id="view" data-rel='btn' href="${not empty param.viewUrl ? param.viewUrl : 'javascript:void(0);' }">
			</shiro:hasPermission>
			<shiro:lacksPermission name="${param.moduleName}:view">
				<a class="btn disabled" data-rel='btn' >
			</shiro:lacksPermission>
				<i class="icon-zoom-in"></i>
				<spring:message code="common.btn.view"/>
			</a> 
			<a class="btn" name="search" data-rel='btn'> 
				<i class="icon-search"></i>
				<spring:message code="common.btn.search"></spring:message>
			</a>
		</c:when>
		<c:otherwise>
			<!-- 需要扩展的其他操作按钮组 -->
			${param.extBtnGroup }
		</c:otherwise>
	</c:choose>
</div>

<!-- 
		<div class="btn-group toolButton">
			<a class="btn" name="add" href="user/addForm" data-rel='btn' >
				<i class="icon-plus"></i><spring:message code="common.btn.add"/>
			</a>
			<a class="btn" name="edit" href="user/editForm" data-rel='btn'>
				<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
			</a> 			
			<a class="btn" name="view" id="view" data-rel='btn' href="user/view">
				<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
			</a> 
			<a class="btn" name="delete" data-rel='btn' href="user/delete">
				<i class="icon-trash"></i><spring:message code="common.btn.delete"/>
			</a> 
			<a class="btn" name="editAttribute" data-rel='btn'>
				<i class="icon-trash"></i><spring:message code="user.btn.attribute"/>
			</a>
			<a class="btn" name="search" data-rel='btn'> 
				<i class="icon-search"></i><spring:message code="common.btn.search"/>
			</a>
		</div>
		 -->