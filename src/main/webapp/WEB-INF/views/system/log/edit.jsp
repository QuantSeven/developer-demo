<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form id="logForm" class="pageForm" action="${action}" method="post" data-rel="valid" modelAttribute="log">
	<div class="page-content">
		<div class="pageFormContent form-area" title="">
			<ul>
				<li>
					<label><spring:message code="log.txt.accountId"/>:</label>
					<input type=text name="value" id="value" value="${log.accountId}" />
				</li>		
		
				<li>
					<label><spring:message code="log.txt.ipAddress"/>:</label>
					<input type=text name="value" id="value" value="${log.ipAddress}" />
				</li>
				
				<li>
					<label><spring:message code="log.txt.action"/>:</label>
					<input type=text name="value" id="value" value="${log.action}" />
				</li>
				<li>
					<label><spring:message code="log.txt.createDate"/>:</label>
					<input type=text name="value" id="value"
						value='<fmt:formatDate value="${log.createDate}" pattern="yyyy-MM-dd"/>' />
				</li>
				<li style="height: 120px;width: 99%">
					<label><spring:message code="log.txt.message"/>:</label>
					<textarea name="message" rows="5" cols="100" style="width: 470px;">${log.message}</textarea>
				</li>
			</ul>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li><a href="log/index" data-rel="ajax" class="btn btn-primary" ><spring:message code="common.btn.close"/> </a></li>
		</ul>
	</div>
</form>