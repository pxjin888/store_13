<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
<!--
	function plusAll(obj){
		
		
	
		if($(obj).attr("checked")=="checked"){
			$(obj).parent().parent().prev().find("input[name='module_id']").attr("checked","true");
			var pre = $(obj).nextAll().last().attr("value");
			var now = $(obj).attr("value");
			var after ;
			if(pre.indexOf(now) == -1){
				after = pre+","+now;
			} 
			if(after.indexOf(",") == 0){
				after = after.substr(1);
			}
			$(obj).nextAll().last().attr("value",after);
		}
	}
	
//-->
</script>
<div class="pageContent">
	<form method="post" action="group_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用 户 组 名：</label>
				<input name="groupName" class="required textInput" id="groupName" type="text" size="30" value=""/>
			</p>
			<p>
				<table class="table" width="100%" layoutH="50">
					<thead>
						<tr>
							<th width="80">模块名称</th>
							<th width="120">功能点</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="#request.modules">
						<tr>
							<td><s:property value="value"/><input type="checkbox" value='<s:property value="key"/>' name='module_id' /></td>
							<td>
								<s:iterator value="#request.operations">
								    <s:property value="value"/><input type="checkbox" value='<s:property value="key"/>' onclick="plusAll(this);" name='operation_id' />
								</s:iterator>
								<input type="hidden" value="" name="operation_ids">
							</td>
						</tr>
						</s:iterator>
					</tbody>
				</table>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	</form>
</div>
