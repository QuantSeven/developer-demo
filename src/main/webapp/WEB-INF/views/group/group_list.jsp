<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${jsPath}/module/group/group_list.js">
</script> 
<div data-layout fit="true" style="width: 100%;height: 100%">
	<div region="center" border="false">
		<div id="groupListGrid" >
			<div class="datagrid-toolbar">
				<div class="btn-group toolButton">
					<a class="btn" name="add" href="#" data-rel='btn' >
						<i class="icon-plus"></i><spring:message code="common.btn.add"/>
					</a>
					<a class="btn" name="edit" href="#"  data-rel='btn'>
						<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
					</a> 			
					<a class="btn" name="view" id="view" href="#" data-rel='btn' >
						<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
					</a> 
					<a class="btn" name="delete" data-rel='btn'  href="group/delete">
						<i class="icon-trash"></i><spring:message code="common.btn.delete"/>
					</a> 
					<a class="btn" name="addUser" data-rel='btn'> 
						<i class="icon-plus"></i><spring:message code="group.txt.adduser"/>
					</a>
					<a class="btn" name="deleteUser" data-rel='btn'> 
						<i class="icon-delete"></i><spring:message code="group.txt.deleteuser"/>
					</a>
					<a class="btn" name="search" data-rel='btn'> 
						<i class="icon-search"></i><spring:message code="common.btn.search"/>
					</a>
				</div>
			</div>
			<div class="datagrid-search">
			   <form class="form-search" style="width:100%" >
				   	<ul>
				   		<li>
							<label ><spring:message code="group.txt.groupid" />：</label>
							<input type="text" placeholder='<spring:message code="group.txt.groupid" />' name="filter_groupId" /></li>
						</li>
						<li >
							<label ><spring:message code="group.txt.groupname" />：</label>
							<input type="text" placeholder='<spring:message code="group.txt.groupname" />' name="filter_groupName" /> 
				   		</li>
				   	</ul>
				</form>
			</div>
			<table class="table">
				<thead>
				    <tr>
						<th width="25"><spring:message code="common.txt.seq"/></th>
						<th width="13"></th>
						<th width="100" class="sort-header" data-code="GROUP_ID"><spring:message code="group.txt.groupid" /></th> 
						<th width="100" class="sort-header" data-code="GROUP_NAME"><spring:message code="group.txt.groupname" /></th> 
						<th width="50"><spring:message code="group.txt.status" /></th> 
						<th width="200"><spring:message code="group.txt.description" /></th> 
					</tr>
				</thead>
				<tbody style="display:none" >
			    <tr>
		            <td>{{:#index+1 }}</td>
		            <td><input type="checkbox" class="datagrid-cell-check" value="{{:groupId}}"/></td>
					<td>{{:groupId }}</td>
					<td>{{:groupName }}</td>
					<td style="text-align: center;">{{:~formatEnable(status) }}</td>
					<td>{{:description}}</td>
				  </tr>
			   
				</tbody>
			</table>
		</div>
	</div>
	<div region="east" style="width:500px;" title="组成员列表">
		<div id="groupUserListGrid" data-datagrid="datagrid">
			<table class="table">
				<thead>
				    <tr>
						<th width="25"><spring:message code="common.txt.seq"/></th>
						<th width="13"></th>
						<th width="100" class="sort-header" data-code="USER_ID"><spring:message code="user.txt.userid" /></th> 
						<th width="100" class="sort-header" data-code="USER_NAME"><spring:message code="user.txt.username" /></th> 
						<th width="200"><spring:message code="user.txt.email" /></th> 
					</tr>
				</thead>
				<tbody style="display:none" >
			    <tr>
		            <td>{{:#index+1 }}</td>
		            <td><input type="checkbox" class="datagrid-cell-check" value="{{:userId}}"/></td>
					<td>{{:userId }}</td>
					<td>{{:userName }}</td>
					<td>{{:email }}</td>
				 </tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
