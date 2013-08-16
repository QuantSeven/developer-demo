<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<form id="menuForm" class="pageForm"  title='<spring:message code="menu.txt.title.info"/>' action="${action}" method="post"  modelAttribute="menu">
    <div class="page-content">
        <div class="pageFormContent form-area" title='<spring:message code="menu.txt.title.info"/>'>
            <ul>
                <li>
                    <label class="red"><spring:message code="menu.txt.menuId"/>:</label>
                    <input type="text" name="menuId" id="menuId" value="${menu.menuId}"  validate="{required:true,notspecificsymbol:true<c:if test="${empty menu}"> ,remote:'menu/validateId',messages:{remote:'菜单编号必须唯一'}</c:if>}" <c:if test="${not empty menu}"> readonly="readonly" class="readonly"</c:if>  />
                </li>

                <li>
                    <label class="red"><spring:message code="menu.txt.menuName"/>:</label>
                    <input type="text" name="name" id="name" value="${menu.name}" validate="{required:true,rangelength:[1,50]}" />
                </li>

                <li>
                    <label class="red"><spring:message code="menu.txt.url"/>:</label>
                    <input type="text" name="url" id="url" value="${menu.url}" validate="{required:true}"/>
                </li>
                <li>
                    <label><spring:message code="menu.txt.parentId"/>:</label>
                    <input type="text" name="parentId" id="parentId" value="${menu.parentId}"  />
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
    <div class="formBar">
        <ul>
            <c:choose>
                <c:when test="${not empty hideBtnSave }">
                    <li><a href="menu/index" data-rel="ajax" class="btn btn-primary" > <spring:message code="common.btn.cancel"/> </a></li>
                </c:when>
                <c:otherwise>
                    <li><button class="btn btn-primary" type="submit"><spring:message code="common.btn.save"/></button></li>
                    <li><a href="menu/index" data-rel="ajax" class="btn" > <spring:message code="common.btn.cancel"/> </a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </div>
</form>




