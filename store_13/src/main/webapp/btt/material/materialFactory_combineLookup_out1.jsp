<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<form id="pagerForm" action="demo/database/dwzOrgLookup2.html">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${model.numPerPage}" />
	<input type="hidden" name="orderField" value="${param.orderField}" />
	<input type="hidden" name="orderDirection" value="${param.orderDirection}" />
</form>

<script type="text/javascript">
<!--
	
	function changeMaterialNum(obj){
// 		alert("changeMaterialNum");
		var materialNum = $(obj).val();
		$(obj).parent().parent().next().find(".materialPartNumSum").each(function(){
			$(this).nextAll(".materialPartNum").removeAttr("value");
			$(this).prevAll(".box").removeAttr("checked");
		});
		if(materialNum == ""){
			return ;
		}
		var materialTempNum = parseFloat(materialNum);
		
		$(obj).parent().parent().next().find(".materialPartNumSum").each(function(){
			var partNum = parseFloat($(this).text());
			if(materialTempNum >= partNum){
				materialTempNum = materialTempNum - partNum;
				$(this).nextAll(".materialPartNum").val(partNum);
 				$(this).prevAll(".box").attr("checked","true");
			}else{
				$(this).nextAll(".materialPartNum").val(materialTempNum);
				return false;
			}
		});
		
		changeCheckboxSum($(obj).parent().parent().parent());
	}
	function changeMaterialPartNum(obj){
		
		$(obj).siblings().eq(0).attr("checked","true");
		
		changeCheckboxSum($(obj).parent().parent().parent().parent());
		
		var temp = 0;
		$(obj).parent().parent().children().each(function(){
			if($(this).children(".box").attr("checked")){
				temp += parseFloat($(this).children(".materialPartNum").val()) ;
			}
		});
		$(obj).parent().parent().parent().parent().find(".materialNum").val(temp);
	}
	function changeCheckboxPart(obj){
		
		changeCheckboxSum($(obj).parent().parent().parent().parent());
		
		var temp = 0;
		$(obj).parent().parent().children().each(function(){
			if($(this).children(".box").attr("checked")){
				temp += parseFloat($(this).children(".materialPartNum").val()) ;
			}
		});

		$(obj).siblings(".materialPartNum").val(0);
		
		$(obj).parent().parent().parent().parent().find(".materialNum").val(temp);
	}
	
	//改变带回checkbox 的value
	//obj位置为tr td的 数组jquery对象
	function changeCheckboxSum(obj){
		var materialPriceId = "";
		var materialProviderId = "";
		var materialProviderName = "";
		var materialInputPrice = "";
		var materialPartNum = "";
		
		obj.find(".box").parent().parent().children().each(function(){
			if($(this).children(".box").attr("checked")=="checked"||$(this).children(".box").attr("checked")=="true"){
				materialPriceId += $(this).children(".materialPriceId").val() + "&";
				materialProviderId += $(this).children(".materialFactoryId").val() + "&";
				materialProviderName += $(this).children(".materialFactoryName").text() + "&";
				materialInputPrice += $(this).children(".inputPrice").text() + "&";
				materialPartNum += $(this).children(".materialPartNum").val() + "&";
			}
		});
		var checkbox = obj.children().eq(0).children("div").children("input");

		var jsonStr = checkbox.val();
		alert(jsonStr);
		var json = eval("(" + jsonStr + ")");
		json["materialPriceId"] = materialPriceId.substr(0,materialPriceId.length-1);;
		json["materialProviderId"] = materialProviderId.substr(0,materialProviderId.length-1);
		json["materialProviderName"] = materialProviderName.substr(0,materialProviderName.length-1);
		json["materialInputPrice"] = materialInputPrice.substr(0,materialInputPrice.length-1);
		json["materialPartNum"] = materialPartNum.substr(0,materialPartNum.length-1);
		checkbox.val(JSON.stringify(json));
	}
	
//-->
</script>

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
				<th width="30"><input type="checkbox" class="checkboxCtrl" group="materialId" /></th>
				<th width="80">物料id</th>
				<th width="120">物料名</th>
				<th width="80">物料单位</th>
				<th width="180">物料数量</th>
				<th width="300">供应商及详情</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="#request.materialMap">
			<tr>
				<td><input type="checkbox" value="{materialId:'<s:property value="key.materialId"/>', materialName:'<s:property value="key.materialName"/>', materialNum:'', materialProviderId:'', materialProviderName:'', materialPriceId:'', materialInputPrice:'', materialPartNum:''}"/></td>
				<td><s:property value="key.materialId"/></td>
				<td><s:property value="key.materialName"/></td>
				<td><s:property value="key.materialUnit"/></td>
				<td>现有库存：<s:property value="key.materialNum"/>,使用：<input name="materialNum" class="materialNum" type="text" size="1" onchange="changeMaterialNum(this)"></td>
				<td>
					<s:iterator value="value" id="lastStore">
					  <span class="part">
					    <input type="checkbox" class="box" onclick="changeCheckboxPart(this)"/>
					    <input type="hidden" class="materialPriceId" value='<s:property value="#lastStore.materialPriceId"/>'>
					    <input type="hidden" class="materialFactoryId" value='<s:property value="#lastStore.materialFactoryId"/>'>
					    <span class="materialFactoryName"><s:property value="#lastStore.materialFactoryName"/></span>[
					    ¥<span class="inputPrice"><s:property value="#lastStore.inputPrice"/></span>,现有库存
					    <span class="materialPartNumSum"><s:property value="#lastStore.materialPartNum"/></span>
					    ]
					  <input name="materialPartNum" class="materialPartNum" type="text" size="1" onchange="changeMaterialPartNum(this)" >
					  </span>
					</s:iterator>
				</td>
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