<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" method="post" action="/operation_list.action">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value='<s:property value="#request.page.numPerPage"/>' />
</form>

<div class="pageContent">
	<table class="table" width="100%" layoutH="50">
		<thead>
			<tr>
				<th width="80">功能点id</th>
				<th width="120">功能点名称</th>
				<th>不可见列名</th>
				<th width="100">功能点类型</th>
				<th width="150">证件号码</th>
				<th width="80" align="center">信用等级</th>
				<th width="80">所属行业</th>
				<th width="80">建档日期</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.operation_list">
			<tr target="sid_user" rel='<s:property value="operationId"/>'>
				<td><s:property value="operationId"/></td>
				<td><s:property value="operationName"/></td>
				<td><s:property value="operationColumnInvisible"/></td>
				<td><s:property value="operationType"/></td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select>
			<span>条，共<s:property value="#request.page.totalCount"/>条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount='<s:property value="#request.page.totalCount"/>' numPerPage='<s:property value="#request.page.numPerPage"/>' pageNumShown="10" currentPage='<s:property value="#request.page.pageNum"/>'></div>

	</div>
</div>
