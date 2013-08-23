<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<form id="userForm" class="pageForm"  title='<spring:message code="user.txt.title.info"/>' action="${action}" method="post"  modelAttribute="user">
    <div class="page-content">
		<div class="pageFormContent form-area" title='<spring:message code="user.txt.title.info"/>'>
			<ul>
				<li>
					<label class="red"><spring:message code="user.txt.userid"/>:</label>
					<input type="text" name="userId" id="userId" value="${user.userId}"  validate="{required:true,notspecificsymbol:true<c:if test="${empty user}"> ,remote:'user/validatePk',messages:{remote:'用户编号必须唯一'}</c:if>}" <c:if test="${not empty user}"> readonly="readonly" class="readonly"</c:if>  />
				</li>
				<%-- 
				<li>
					<label class="red"><spring:message code="user.txt.pwd"/>:</label>
					 {required:true,messages:{required:'请输入联系人姓名'}}
					<input type="password" name="pwd" id="pwd" value="${user.pwd}" validate="{required:true,minlength:6}" />
				</li>
				--%>
				<li>
					<label class="red"><spring:message code="user.txt.username"/>:</label>
					<input type="text" name="userName" id="userName" value="${user.userName}" validate="{required:true,rangelength:[1,50]}" />
				</li>
				
				<li>
					<label class="red"><spring:message code="user.txt.email"/>:</label>
					<input type="text" name="email" id="email" value="${user.email}" validate="{required:true,email:true}" />
				</li>
				<li>
					<label><spring:message code="user.txt.phone"/>:</label>
					<input type="text" name="phone" id="phone" value="${user.phone}" validate="{required:false,mobile:true,rangelength:[10,16],messages:{rangelength:'<spring:message code="validator.messages.mobilelength"/>'}}" />
				</li>
				
				<li>
					<label><spring:message code="user.txt.address"/>:</label>
					<input type="text" name="address" id="address" value='${user.address}' />
				</li>
				<li>
					<label><spring:message code="user.txt.status"/>:</label>
					 <div data-buttonset="">
					 	<c:choose>
					 		<c:when test="${not empty user }">
			            		<input type="radio" validate="{required:true}" id="enableTrue" value="1" name="status" <c:if test="${user.status==1}">checked="checked"</c:if > /><label for="enableTrue">启用</label>
			            		<input type="radio" validate="{required:true}" id="enableFalse" value="0" name="status" <c:if test="${user.status==0}">checked="checked"</c:if > /><label for="enableFalse">停用</label>
					 		</c:when>
					 		<c:otherwise>
			            		<input type="radio" validate="{required:true}" id="enableTrue" value="1" name="status" checked="checked" /><label for="enableTrue">启用</label>
			            		<input type="radio" validate="{required:true}" id="enableFalse" value="0" name="status" /><label for="enableFalse">停用</label>
					 		</c:otherwise>
					 	</c:choose>
			         </div>
				</li>
				<li>
					<label><spring:message code="user.txt.description"/>:</label>
					<input type="text" name="description" id="description" value='${user.description}' />
				</li>
			</ul>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<c:choose>
				<c:when test="${not empty hideBtnSave }">
					<li><a href="user/index" data-rel="ajax" class="btn btn-primary" > <spring:message code="common.btn.cancel"/> </a></li>
				</c:when>
				<c:otherwise>
					<li><button class="btn btn-primary" type="submit"><spring:message code="common.btn.save"/></button></li> 
					<li><a href="user/index" data-rel="ajax" class="btn" > <spring:message code="common.btn.cancel"/> </a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</form>