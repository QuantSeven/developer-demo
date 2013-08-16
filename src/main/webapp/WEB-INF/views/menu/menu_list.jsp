<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${jsPath}/module/menu/menu_list.js"></script>

<div id="menuList" data-datagrid="datagrid" url="menu/listData">
    <div class="datagrid-toolbar">
        <div class="btn-group toolButton">
                <a class="btn" name="add" data-rel='btn' href="menu/addForm" >
                    <i class="icon-plus"></i><spring:message code="common.btn.add"/>
                </a>
            <a class="btn" name="edit" data-rel='btn' href="menu/editForm" >
                <i class="icon-edit"></i><spring:message code="common.btn.edit"/>
            </a>
            <a class="btn" name="delete" data-rel='btn' href="menu/delete" >
                <i class="icon-trash"></i><spring:message code="common.btn.delete"/>
            </a>
                <a class="btn" name="view" data-rel='btn' href="menu/view">
                    <i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
                </a>
                <a class="btn" name="search" data-rel='btn'>
                <i class="icon-search"></i>
                <spring:message code="common.btn.search"/>
                 </a>
        </div>
    </div>

    <div class="datagrid-search">
        <form class="form-search">
            <ul>
                <li>
                    <label><spring:message code="menu.txt.menuName"/>：</label>
                    <input type="text" placeholder="<spring:message code="menu.txt.menuName" />" name="sch_name" />
                </li>

                <li style="width: 150px">
                    <label style="width: 60px"><spring:message code="menu.txt.visible" />：</label>
                    <select name="sch_visable" data-chosen style="width: 80px">
                        <option value=""><spring:message code="common.txt.please.select"/></option>
                        <option value="1">启用</option>
                        <option value="0">停用</option>
                    </select>
                </li>
            </ul>
        </form>
    </div>

    <table class="table">
        <thead>
        <th width="20"><spring:message code="common.txt.seq" /></th>
        <th width="15">#</th>
        <th width="150"><spring:message code="menu.txt.menuName" /></th>
        <th width="200"><spring:message code="menu.txt.url" /></th>
        <th width="300"><spring:message code="menu.txt.createUser" /></th>
        <th width="120"><spring:message code="menu.txt.visible" /></th>
        </thead>
        <tbody class="hide">
        <tr>
            <td style="text-align: right;">{{>#index+1 }}</td>
            <td><input type="checkbox" class="datagrid-cell-check" value="{{>menuId}}"/></td>
            <td>{{>name}}</td>
            <td title="{{>url}}">{{>url}}</td>
            <td>{{>createUser}}</td>
            <td style="text-align: center;">{{:~formatEnable(visable) }}</td>
            <%--<td >{{>createDate}}</td>--%>
            <%--<td>{{>~formatDate(createDate,'yyyy-MM-dd hh:mm:ss')}}</td>--%>
        </tr>
        </tbody>
    </table>
</div>
