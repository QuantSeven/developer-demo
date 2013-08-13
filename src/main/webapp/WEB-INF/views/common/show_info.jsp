<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="pageFormContent form-area recoder-info" id="recoder-info" title='<spring:message code="common.title.record.info"/>'>
<ul>
	<li>
		<label><spring:message code="common.txt.createby"/>:</label>
		<input readonly="readonly" type="text" name="createAccountId"  value="${param.createAccountId}" ></input>
	</li>
	<li>
		<label><spring:message code="common.txt.createdate"/>:</label>
		<c:choose>
			<c:when test='${fn:contains(param.createDate, ".")}'>
				<input readonly="readonly" type="text" name="createDate" value='${fn:substringBefore(param.createDate,".") }'/>
			</c:when>
			<c:otherwise>
				<fmt:parseDate value="${param.createDate}" pattern="EEE MMM dd HH:mm:ss zzz yyyy" var="createDate" parseLocale="en_US"/>
				<input readonly="readonly" type="text" name="createDate" value='<fmt:formatDate value="${createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</li>
	<li>
		<label><spring:message code="common.txt.modifyby"/>:</label>
		<input readonly="readonly" type="text"  value="${param.modifyAccountId}" ></input>
	</li>
	 <li>
		<label><spring:message code="common.txt.modifydate"/>:</label>
		<c:choose>
			<c:when test='${fn:contains(param.modifyDate, ".")}'>
				<input readonly="readonly" type="text" value='${fn:substringBefore(param.modifyDate,".") }'/>
			</c:when>
			<c:otherwise>
				<fmt:parseDate value="${param.modifyDate}" pattern="EEE MMM dd HH:mm:ss zzz yyyy" var="modifyDate" parseLocale="en_US"/>
				<input readonly="readonly" type="text" value='<fmt:formatDate value="${modifyDate}" pattern="yyyy-MM-dd HH:mm:ss"/>'/>
			</c:otherwise>
		</c:choose>
	</li>
	</ul>
</div>
	
