<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form id="menuForm" class="pageForm"  title='<spring:message code="menu.txt.title.info"/>' action="${action}" method="post"  modelAttribute="menu">
    <div class="page-content">
        <div class="pageFormContent form-area" title='<spring:message code="menu.txt.title.info"/>'>
            <ul>
                <li>
                    <label class="red"><spring:message code="menu.txt.menuid"/>:</label>
                    <input type="text" name="menuId" id="menuId" value="${menu.menuId}"  validate="{required:true,notspecificsymbol:true<c:if test="${empty menu}"> ,remote:'menu/validatePk',messages:{remote:'菜单编号必须唯一'}</c:if>}" <c:if test="${not empty menu}"> readonly="readonly" class="readonly"</c:if>  />
                </li>

                <li>
                    <label class="red"><spring:message code="menu.txt.menuname"/>:</label>
                    <input type="text" name="name" id="name" value="${menu.name}" validate="{required:true,rangelength:[1,50]}" />
                </li>

                <li>
                    <label class="red"><spring:message code="menu.txt.href"/>:</label>
                    <input type="text" name="href" id="href" value="${menu.href}" validate="{required:true}"/>
                </li>
                <li>
                    <label><spring:message code="menu.txt.parent"/>:</label>
                    <input type="hidden" id="hiddenParentId" value="${menu.parentId }"/>
                    <select name="parentId" id="parentId">
                    	<option value="">---请选择---</option>
	                    <c:forEach items="${menus }" var="item">
	                    	<c:choose>
	                    		<c:when test="${item.menuId eq menu.parentId}">
			                    	<option value="${item.menuId }" selected="selected">${item.name }</option>
	                    		</c:when>
	                    		<c:otherwise>
			                    	<option value="${item.menuId }">${item.name }</option>
	                    		</c:otherwise>
	                    	</c:choose>
	                    </c:forEach>
                    </select>
                </li>

                <li>
                    <label><spring:message code="menu.txt.visible"/>:</label>
                    <div data-buttonset="">
                        <c:choose>
                            <c:when test="${not empty menu }">
                                <input type="radio" validate="{required:true}" id="enableTrue" value="1" name="visable" <c:if test="${menu.visable==1}">checked="checked"</c:if > /><label for="enableTrue">启用</label>
                                <input type="radio" validate="{required:true}" id="enableFalse" value="0" name="visable" <c:if test="${menu.visable==0}">checked="checked"</c:if > /><label for="enableFalse">停用</label>
                            </c:when>
                            <c:otherwise>
                                <input type="radio" validate="{required:true}" id="enableTrue" value="1" name="visable" checked="checked" /><label for="enableTrue">启用</label>
                                <input type="radio" validate="{required:true}" id="enableFalse" value="0" name="visable" /><label for="enableFalse">停用</label>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</form>




