<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<style>
<!--
.widget-box {
    border-radius: 4px 4px 4px 4px;
    margin-top: 0;
    background: none repeat scroll 0 0 #F9F9F9;
    border: 1px solid #CDCDCD;
    clear: both;
    position: relative;
}
.widget-title, .modal-header, .table th, div.dataTables_wrapper .ui-widget-header {
    background-color: #EFEFEF;
    background-image: -moz-linear-gradient(center top , #FDFDFD 0%, #EAEAEA 100%);
    border-bottom: 1px solid #CDCDCD;
    border-radius: 4px 4px 4px 4px;
    height: 36px;
}
.filter {
    border-bottom: 1px solid #CCCCCC;
    overflow: hidden;
    padding: 12px 0 1px;
    width: 100%;
}
.widget-content {
    border-bottom: 1px solid #CDCDCD;
    padding: 12px 15px;
}
.nopadding {
    padding: 0 !important;
}
.changeView{
	float:right;
	text-align: right;
	margin-left: 7px;
}
.changeView a {
    background: url("themes/img/list-style.png") no-repeat scroll 5px 5px transparent;
    border-color: rgba(0, 0, 0, 0.15) rgba(0, 0, 0, 0.15) rgba(0, 0, 0, 0.25);
    border-radius: 4px 4px 4px 4px;
    border-style: solid;
    border-width: 1px;
    box-shadow: 0 1px 0 rgba(255, 255, 255, 0.2) inset, 0 1px 2px rgba(0, 0, 0, 0.05);
    display: inline-block;
    height: 24px !important;
    margin: 2px 2px 0 -3px;
    width: 24px !important;
}


a.list_style_b{ background-position:-95px 5px;}
.changeView a:hover{ background-color:#e6e6e6;}
a.list_style_a:hover, a.list_style_a_on{ background-position:5px -95px!important;}
a.list_style_b:hover, a.list_style_b_on{ background-position:-95px -95px!important;}


-->
</style>
<div class="widget-box">
	<div class="datagrid-toolbar" >
		<div class="changeView">
			<a class="viewType list_style_b list_style_b_on" href="javascript:void(0);" id="gridView"></a>
			<a class="viewType list_style_a" href="javascript:void(0);" id="treeView"></a>
		</div>
	</div>
	<div class="widget-content nopadding" id="dataGridContent">
		<div id="commonDataGridList" data-datagrid="datagrid" >
			<div class="datagrid-search">
				<c:if test="${not empty columns }">
				   <form class="form-search" >
				   		<ul>
				   			<c:forEach items="${columns }" var="column">
				   				<c:if test="${column.search }">
						   			<li>
										<label title='${column.title }'>${column.title }：</label>
										<input type="text" placeholder='${column.title }' name="sch_${column.field }" /> 
						   			</li>
				   				</c:if>
				   			</c:forEach>
				   			<li style="width:65px">
								<a class="btn" name="search" style="line-height: 17px"  data-rel='btn' ><i class="icon-search"></i><spring:message code="common.btn.search"/></a>
				   			</li>
				   		</ul>
					</form>
				</c:if>
			</div>
			<table class="table">
				<thead>
					<tr>
						<th width="25"><spring:message code="common.txt.seq"/></th>
						<c:forEach items="${columns }" var="column">
							<c:choose>
								<c:when test="${column.checkbox }">
									<th width="13"><input type="checkbox" class="datagrid-header-check" id="checkbox"/></th>
								</c:when>
								<c:otherwise>
									<th  width="${column.width }" <c:if test="${not empty column.style }"> style="${column.style}"</c:if> <c:if test="${not empty column.sortName }">class="sort-header" data-code="${column.sortName }"</c:if>>
										${column.title }
									</th>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</thead>
				<tbody style="display:none">
				  	<tr>
				  		<td>{{:#index+1}}</td>
			            <c:forEach items="${columns }" var="column">
							<c:choose>
								<c:when test="${not empty column.checkbox and  column.checkbox}">
									<td><input type="checkbox" class="datagrid-cell-check" value='{{:${column.field}}}'/></td>
								</c:when>
								<c:otherwise>
									<td <c:if test="${not empty column.style }"> style="${column.style}"</c:if>>{{:${column.field}}}</td>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<div class="widget-content nopadding" style="display: none" id="treeContent">
		<div class="datagrid-search">
		   <form class="form-search" onkeydown="if(event.keyCode==13){return false;}" >
			   	<ul>
			   		<li>
						<label>节点名称：</label>
						<input  type="text" class="search-query" placeholder="节点名称"  id="keyword" />
					</li>
			   	</ul>
			</form>
		</div>
		<ul id="commonTree" class="ztree"></ul>
	</div>
</div>
