<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${jsPath}/module/user/user_list.js">
</script> 


<div id="userListGrid" data-datagrid="datagrid" url="user/dataGrid">
	<div class="datagrid-toolbar">
		<div class="btn-group toolButton">
			<a class="btn" name="add" href="user/addForm" data-rel='btn' >
				<i class="icon-plus"></i><spring:message code="common.btn.add"/>
			</a>
			<a class="btn" name="edit" href="user/editForm" data-rel='btn'>
				<i class="icon-edit"></i><spring:message code="common.btn.edit"/>
			</a> 			
			<a class="btn" name="view" id="view" data-rel='btn' href="user/view">
				<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
			</a> 
			<a class="btn" name="delete" data-rel='btn' href="user/delete">
				<i class="icon-trash"></i><spring:message code="common.btn.delete"/>
			</a> 
			<a class="btn" name="search" data-rel='btn'> 
				<i class="icon-search"></i><spring:message code="common.btn.search"/>
			</a>
			<a class="btn" id="btnTest"> 
				<i class="icon-search"></i>测试
			</a>
		</div>
	</div>
	<div class="datagrid-search">
	   <form class="form-search" style="width:100%" >
		   	<ul>
		   		<li>
					<label ><spring:message code="user.txt.username" />：</label>
					<input type="text" placeholder='<spring:message code="user.txt.username" />' name="filter_username" /></li>
				</li>
				<li >
					<label ><spring:message code="user.txt.email" />：</label>
					<input type="text" placeholder='<spring:message code="user.txt.email" />' name="filter_email" /> 
		   		</li>
		   	</ul>
		</form>
	</div>
	<table class="table">
		<thead>
		    <tr>
				<th width="25"><spring:message code="common.txt.seq"/></th>
				<th width="13"></th>
				<th width="100" class="sort-header" data-code="USER_ID"><spring:message code="user.txt.userid" /></th> 
				<th width="100" class="sort-header" data-code="USER_NAME"><spring:message code="user.txt.username" /></th> 
				<th width="200"><spring:message code="user.txt.email" /></th> 
				<th width="100" class="sort-header" data-code="PHONE"><spring:message code="user.txt.phone" /></th> 
				<th width="50"><spring:message code="user.txt.status" /></th> 
				<th width="110"><spring:message code="user.txt.address" /></th> 
				<th width="200"><spring:message code="user.txt.description" /></th> 
			</tr>
		</thead>
		<tbody style="display:none" >
	    <tr>
            <td>{{:#index+1 }}</td>
            <td><input type="checkbox" class="datagrid-cell-check" value="{{:userId}}"/></td>
			<td>{{:userId }}</td>
			<td>{{:userName }}</td>
			<td>{{:email }}</td>
			<td>{{:phone }}</td>
			<td style="text-align: center;">{{:~formatEnable(status) }}</td>
			<td>{{:address }}</td>
			<td>{{:description}}</td>
		  </tr>
	   
		</tbody>
	</table>
</div>
