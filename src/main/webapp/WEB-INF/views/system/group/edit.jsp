<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="groupForm" class="pageForm" action="${action}" method="post"  modelAttribute="group" style="overflow: hidden;">
	<div class="pageFormContent form-area" title='<spring:message code="group.txt.basepinfo"/>'>
	<ul>
		<li>
			<label class="red"><spring:message code="group.txt.id"/>:</label>
			<input type="text" name="id" id="id" value="${group.id}" validate="{required:true,notspecificsymbol:true,rangelength:[1,50]<c:if test="${empty group}">,remote:'group/validateId',messages:{remote:'用户组编号必须唯一'}</c:if>}" <c:if test="${not empty group}">readonly="readonly" class="readonly"</c:if> />
		</li>
		<li>
			<label class="red"><spring:message code="group.txt.name"/>:</label>
			<input type="text" name="name" id="name" value="${group.name}" validate="{required:true,rangelength:[1,50]}" />
		</li>
		<li>
			<label class="red"><spring:message code="group.txt.enable"/>:</label>
			 <span data-buttonset="">
				<c:choose>
					<c:when test="${not empty group }">
			            <input type="radio"  id="enableTrue" value="1" name="groupExtension.enable" <c:if test="${group.groupExtension.enable}">checked="checked"</c:if > /><label for="enableTrue">启用</label>
			            <input type="radio"  id="enableFalse" value="0" name="groupExtension.enable" <c:if test="${!group.groupExtension.enable}">checked="checked"</c:if > /><label for="enableFalse">禁用</label>
					</c:when>
					<c:otherwise>
			            <input type="radio"  id="enableTrue" value="1" name="groupExtension.enable" checked="checked" /><label for="enableTrue">启用</label>
			            <input type="radio"  id="enableFalse" value="0" name="groupExtension.enable"  /><label for="enableFalse">禁用</label>
					</c:otherwise>
				</c:choose>
	         </span>
		</li>
		<li>
			<label class="red"><spring:message code="group.txt.manageruserid"/>:</label>
			<div class="input-append">
				<input class="input-medium" type="hidden" name="groupExtension.managerUserId" id="managerUserId" value="${group.groupExtension.managerUserId}" validate="{required:true}" />
				<input class="input-medium uneditable-input" onfocus="this.blur()" type="text" id="managerUserName" value="${user.first}" validate="{required:true}"/>
				<span class="add-on" id="selectManager"><i class="icon-user" ></i></span>
			</div>
		</li>
		<li style="width: 600px;height:30px">
			<label><spring:message code="group.txt.description"/>:</label>
			<textarea style="width:465px;height:30px" id="description" name="groupExtension.description">${group.groupExtension.description}</textarea>
		</li>
		</ul>
	</div>
	<c:import url="../../common/show_info.jsp">
	 	<c:param name="createAccountId" value="${group.groupExtension.createAccountId}" />
		<c:param name="createDate" value="${group.groupExtension.createDate}"/>
		<c:param name="modifyAccountId" value="${group.groupExtension.modifyAccountId}"/>
		<c:param name="modifyDate" value="${group.groupExtension.modifyDate}"/>
	</c:import>
</form>
