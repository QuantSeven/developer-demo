<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${jsPath}/module/system/group_list.js">

</script>
	<div data-layout fit="true" style="height:100%;width:100%;">
		<!-- 用户组列表-->
		<div  region="center" >
		    <div id="GroupList">
				<div class="datagrid-toolbar">
					<c:import url="../../common/toolbar.jsp">
						<c:param name="moduleName" value="group"/>
					</c:import>
				</div>
				<div class="datagrid-search">
				   <form class="form-search" >
				   		<ul>
							<li>
								<label><spring:message code="group.txt.name" />：</label>
								<input  type="text" placeholder='<spring:message code="group.txt.name" />' name="sch_name" />
							</li>
				   		</ul>
					</form>
				</div>
				<table class="table">
					<thead>
						<tr>
							<!--<th width="20"><input type="checkbox" class="datagrid-header-check"/></th>  -->
							<th width="25"><spring:message code="common.txt.seq"/></th>
							<th width="15"></th>
							<th width="130" class="sort-header" data-code="g.ID_"><spring:message code="group.txt.id" /></th> 
							<th width="130" class="sort-header" data-code="g.NAME_"><spring:message code="group.txt.name" /></th> 
							<th width="60"><spring:message code="group.txt.manageruserid" /></th> 
							<th width="50"><spring:message code="group.txt.enable" /></th> 
							<th width="110"><spring:message code="group.txt.modifyaccountid" /></th> 
							<th width="80"><spring:message code="group.txt.modifydate" /></th> 
						</tr>
					</thead>
					<tbody  style="display:none">
			        <tr>
            			<td>{{:#index+1 }}</td>
            			<td><input type="checkbox" class="datagrid-cell-check" value="{{:id}}"/></td>
						<td>{{:id }}</td>
						<td>{{:name }}</td>
						<td>{{:groupExtension.user.first }}</td>
						<td style="text-align: center;">{{:~formatEnable(groupExtension.enable) }}</td>
						<td>{{:groupExtension.modifyAccountId }}</td>
						<td>{{:~formatDate(groupExtension.modifyDate,"yyyy-MM-dd") }}</td>
					</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<div  title='<spring:message code="group.txt.user.list" />' region="east" style="width: 300px;">
		<div id="GroupUserList" title="鼠标按住不放可拖动组成员排序">
				<div class="datagrid-toolbar">
					<c:set var="extBtnGroup" scope="request">
						<shiro:hasPermission name="group:addUserRelation">
							<a class="btn" name="addGroupUser" data-rel="btn" href="#">
								<i class="icon-plus"></i><spring:message code="group.btn.adduser" />
							</a> 
						</shiro:hasPermission>
						<shiro:lacksPermission name="group:addUserRelation">
							<a class="btn disabled">
								<i class="icon-plus"></i><spring:message code="group.btn.adduser" />
							</a> 
						</shiro:lacksPermission>
						
						<shiro:hasPermission name="group:deleteUserRelation">
							<a class="btn" name="deleteGroupUser" data-rel="btn">
								<i class="icon-trash"></i><spring:message code="group.btn.deleteuser" />
							</a>
						</shiro:hasPermission>
						<shiro:lacksPermission name="group:deleteUserRelation">
							<a class="btn disabled">
								<i class="icon-trash"></i><spring:message code="group.btn.deleteuser" />
							</a> 
						</shiro:lacksPermission>
					
					</c:set>
					<c:import url="../../common/toolbar.jsp">
						<c:param name="moduleName" value="group"/>
						<c:param name="showComBtn" value="false"/>
						<c:param name="extBtnGroup" value="${extBtnGroup }"/>
					</c:import>
				</div>
			     <table class="table" id="groupUserTable">
					<thead>
						<tr>
							<th width="25"><spring:message code="common.txt.seq"/></th>
							<th width="13"><input type="checkbox" class="datagrid-header-check"/></th>
							<th width="100"><spring:message code="user.txt.id" /></th> 
							<th width="100"><spring:message code="user.txt.first" /></th> 
						</tr>
					</thead>
					<tbody  style="display:none;" >
				      <tr>
				      	<td>{{:#index+1 }}</td>
            			<td ><input type="checkbox" class="datagrid-cell-check" value="{{:id}}"/></td>
						<td>{{:id }}</td>
						<td>{{:first }}</td>
					  </tr>	
					</tbody>
				</table>
				</div>
			</div>
		</div>
	</div>
