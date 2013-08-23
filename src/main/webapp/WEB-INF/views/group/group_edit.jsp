<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="groupForm" class="pageForm"  title='<spring:message code="group.txt.title.info"/>' action="${action}" method="post"  modelAttribute="group">
    <div class="page-content">
		<div class="pageFormContent form-area" title='<spring:message code="group.txt.title.info"/>'>
			<ul>
				<li>
					<label class="red"><spring:message code="group.txt.groupid"/>:</label>
					<input type="text" name="groupId" id="groupId" value="${group.groupId}"  validate="{required:true,notspecificsymbol:true<c:if test="${empty group}"> ,remote:'group/validatePk',messages:{remote:'用户编号必须唯一'}</c:if>}" <c:if test="${not empty group}"> readonly="readonly" class="readonly"</c:if>  />
				</li>
				<li>
					<label class="red"><spring:message code="group.txt.groupname"/>:</label>
					<input type="text" name="groupName" id="groupName" value="${group.groupName}" validate="{required:true,rangelength:[1,50]}" />
				</li>
				
				<li>
					<label><spring:message code="group.txt.status"/>:</label>
					 <div data-buttonset="">
					 	<c:choose>
					 		<c:when test="${not empty group }">
			            		<input type="radio" validate="{required:true}" id="enableTrue" value="1" name="status" <c:if test="${group.status}">checked="checked"</c:if > /><label for="enableTrue">启用</label>
			            		<input type="radio" validate="{required:true}" id="enableFalse" value="0" name="status" <c:if test="${group.status}">checked="checked"</c:if > /><label for="enableFalse">停用</label>
					 		</c:when>
					 		<c:otherwise>
			            		<input type="radio" validate="{required:true}" id="enableTrue" value="1" name="status" checked="checked" /><label for="enableTrue">启用</label>
			            		<input type="radio" validate="{required:true}" id="enableFalse" value="0" name="status" /><label for="enableFalse">停用</label>
					 		</c:otherwise>
					 	</c:choose>
			         </div>
				</li>
				<li>
					<label><spring:message code="group.txt.description"/>:</label>
					<input type="text" name="description" id="description" value='${group.description}' />
				</li>
			</ul>
		</div>
	</div>
</form>