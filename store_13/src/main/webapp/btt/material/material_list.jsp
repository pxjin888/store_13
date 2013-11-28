<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="user_pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}">
	<input type="hidden" name="keywords" value="${param.keywords}" />
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
</form>


<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html" method="post">
	<div class="searchBar">
		<!--<ul class="searchContent">
			<li>
				<label>我的客户：</label>
				<input type="text"/>
			</li>
			<li>
			<select class="combox" name="province">
				<option value="">所有省市</option>
				<option value="北京">北京</option>
				<option value="上海">上海</option>
				<option value="天津">天津</option>
				<option value="重庆">重庆</option>
				<option value="广东">广东</option>
			</select>
			</li>
		</ul>
		-->
		<table class="searchContent">
			<tr>
				<td>
					物料名称：<input type="text" name="keyword" />
				</td>
				<td>
					<select class="combox" name="province">
						<option value="">所有省市</option>
						<option value="北京">北京</option>
						<option value="上海">上海</option>
						<option value="天津">天津</option>
						<option value="重庆">重庆</option>
						<option value="广东">广东</option>
					</select>
				</td>
				<td>
					建档日期：<input type="text" class="date" readonly="true" />
				</td>
			</tr>
		</table>
		<div class="subBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">检索</button></div></div></li>
				<li><a class="button" href="demo_page6.html" target="dialog" mask="true" title="查询框"><span>高级检索</span></a></li>
			</ul>
		</div>
	</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="/btt/material/material_addDisplay.jsp" target="dialog" height="400" rel="material_addDisplay"><span>添加</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?uid={sid_user}" target="ajaxTodo" title="确定要删除吗?"><span>删除</span></a></li>
			<li><a class="edit" href="/btt/user/group_edit.action?uid={sid_user}" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls" target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80">物料id</th>
				<th width="120">物料名</th>
				<th>物料单位</th>
				<th width="100">物料数量</th>
				<th width="150">物料代号</th>
				<th width="80" align="center">规格/型号</th>
				<th width="80">最少量</th>
				<th width="80">批量数</th>
				<th width="80">批量价</th>
				<th width="80">序列号</th>
				<th width="80">通道数</th>
				<th width="80">通道号</th>
				<th width="80">版本</th>
				<th width="80">位置</th>
				<th width="80">使用</td>
			</tr>
		</tdead>
		<tbody>
			<s:iterator value="#request.material_list">
			<tr target="sid_user" rel="10">
				<td><s:property value="materialId"/></td>
				<td><s:property value="materialName"/></td>
				<td><s:property value="materialUnit"/></td>
				<td><s:property value="materialNum"/></td>
				<td><s:property value="materialCode"/></td>
				<td><s:property value="materialType"/></td>
				<td><s:property value="materialMinNum"/></td>
				<td><s:property value="materialBatchNum"/></td>
				<td><s:property value="materialBatchPrice"/></td>
				<td><s:property value="materialSequence"/></td>
				<td><s:property value="materialPassNum"/></td>
				<td><s:property value="materialPassNo"/></td>
				<td><s:property value="materialVersion"/></td>
				<td><s:property value="materialPostion"/></td>
				<td><s:property value="materialUse"/></td>
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
			<span>条，共${totalCount}条</span>
		</div>
		
		<div class="pagination" targetType="navTab" totalCount="200" numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
