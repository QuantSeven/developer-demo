<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<script type="text/javascript" src="${jsPath}/module/system/log_list.js"></script>

<div id="logList" data-datagrid="datagrid" url="log/listData">
	<div class="datagrid-toolbar">
		<div class="btn-group toolButton">
			<shiro:hasPermission name="log:view">
				<a class="btn" name="view" data-rel='btn' href="log/view">
					<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
				</a>
			</shiro:hasPermission>
			<shiro:lacksPermission name="log:view">
				<a class="btn disabled" data-rel='btn'>
					<i class="icon-zoom-in"></i><spring:message code="common.btn.view"/>
				</a>
			</shiro:lacksPermission>
			
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
				<label><spring:message code="log.txt.accountId"/>：</label>
				<input type="text" placeholder="<spring:message code="log.txt.accountId" />" name="sch_accountId" />
			 </li>
		
			<li style="width:500px;">
				<label><spring:message code="log.txt.createDate" />：</label>
				<input class="date" type="text" placeholder="<spring:message code="log.txt.startDate" />" name="sch_startDate" />
				<spring:message code="common.txt.to" />
				<input class="date" type="text" placeholder="<spring:message code="log.txt.endDate" />" name="sch_endDate" />
			</li>
		</ul>
		</form>
	</div>
	
	<table class="table">
		<thead>
			<th width="20"><spring:message code="common.txt.seq" /></th>
			<th width="15">#</th> 
			<th width="150"><spring:message code="log.txt.accountId" /></th>
			<th width="100"><spring:message code="log.txt.first" /></th>
			<th width="100"><spring:message code="log.txt.ipAddress" /></th> 
			<th width="200"><spring:message code="log.txt.action" /></th> 
			<th width="300"><spring:message code="log.txt.message" /></th> 
			<th width="120"><spring:message code="log.txt.createDate" /></th> 
		</thead>
		<tbody class="hide">
			<tr>
				<td style="text-align: right;">{{>#index+1 }}</td>
	            <td><input type="checkbox" class="datagrid-cell-check" value="{{>logId}}"/></td>
				<td>{{>accountId}}</td>
				<td>{{>extensionColumn.first}}</td>
				<td>{{>ipAddress}}</td>
				<td title="{{>action}}">{{>action}}</td>
				<td title="{{>message}}">{{>message}}</td>
				<td>{{>~formatDate(createDate,'yyyy-MM-dd hh:mm:ss')}}</td>
			</tr>
		</tbody>
	</table>
</div>

