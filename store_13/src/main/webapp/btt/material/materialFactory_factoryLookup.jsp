<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" action="demo/database/dwzOrgLookup2.html">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<div class="pageHeader">
	<form rel="pagerForm" method="post" action="demo/database/dwzOrgLookup2.html" onsubmit="return dwzSearch(this, 'dialog');">
	<div class="searchBar">
		<ul class="searchContent">
			<li>
				<label>部门名称:</label>
				<input class="textInput" name="orgName" value="" type="text">
			</li>	  
			<li>
				<label>部门编号:</label>
				<input class="textInput" name="orgNum" value="" type="text">
			</li>
			<li>
				<label>部门经理:</label>
				<input class="textInput" name="leader" value="" type="text">
			</li>
			<li>
				<label>上级部门:</label>
				<input class="textInput" name="parentOrg.orgName" value="" type="text">
			</li> 
		</ul>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">查询</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="button" multLookup="materialId" warn="请选择物料" >选择带回</button></div></div></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">

	<table class="table" layoutH="118" targetType="dialog" width="100%">
		<thead>
			<tr>
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="factoryId" /></th>
				<th width="80">供应商id</th>
				<th width="120">供应商名</th>
				<th>物料单位</th>
				<th width="100">物料数量</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.material_list">
			<tr>
				<td><input type="checkbox" name="factoryId" value="{factoryId:'<s:property value="{factoryId:"/>', factoryName:'<s:property value="factoryName"/>'}"/></td>
				<td><s:property value="factoryId"/></td>
				<td><s:property value="factoryName"/></td>
				<td><s:property value="materialUnit"/></td>
				<td><s:property value="materialNum"/></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>

	<div class="panelBar">
		<div class="pages">
			<span>每页</span>
			<select name="numPerPage" onchange="dwzPageBreak({targetType:dialog, numPerPage:'10'})">
				<option value="10" selected="selected">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
			</select>
			<span>条，共2条</span>
		</div>
		<div class="pagination" targetType="dialog" totalCount="2" numPerPage="10" pageNumShown="1" currentPage="1"></div>
	</div>
</div>