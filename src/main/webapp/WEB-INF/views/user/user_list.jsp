<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${jsPath}/module/user/user_list.js">
</script> 


<div id="userListGrid" data-datagrid="datagrid" url="user/listData">
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
			<a class="btn" name="editAttribute" data-rel='btn'>
				<i class="icon-trash"></i><spring:message code="user.btn.attribute"/>
			</a>
			<a class="btn" name="search" data-rel='btn'> 
				<i class="icon-search"></i><spring:message code="common.btn.search"/>
			</a>
		</div>
	</div>
	<div class="datagrid-search">
	   <form class="form-search" style="width:100%" >
		   	<ul>
		   		<li style="width: 240px">
					<label style="width: 100px"><spring:message code="user.txt.username" />：</label>
					<input style="width: 120px"  type="text" placeholder='<spring:message code="user.txt.username" />' name="sch_username" /></li>
				</li>
				<li style="width: 180px">
					<label style="width: 40px"><spring:message code="user.txt.email" />：</label>
					<input style="width: 120px" type="text" placeholder='<spring:message code="user.txt.email" />' name="sch_email" /> 
		   		</li>
		   		<li style="width: 150px">
					<label style="width: 60px"><spring:message code="user.txt.status" />：</label>
		   			<select name="sch_status" data-chosen style="width: 80px">
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
		    <tr>
				<th width="25"><spring:message code="common.txt.seq"/></th>
				<th width="13"></th>
				<th width="90" class="sort-header" data-code="t.USER_ID"><spring:message code="user.txt.userid" /></th> 
				<th width="70" class="sort-header" data-code="t.USER_NAME"><spring:message code="user.txt.username" /></th> 
				<th width="200"><spring:message code="user.txt.email" /></th> 
				<th width="60"><spring:message code="user.txt.phone" /></th> 
				<th width="50"><spring:message code="user.txt.status" /></th> 
				<th width="110"><spring:message code="user.txt.address" /></th> 
				<th width="80"><spring:message code="user.txt.description" /></th> 
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
