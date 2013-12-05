<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<script type="text/javascript">

</script>

<div class="pageContent">
	<form method="post" action="materialLog_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>分类：</label>
				<select class="combox" name="logDo" id="operation" onchange="changeLogDo();" >
					<option value="mix">物料拼合</option>
				</select>
				<input type="hidden" id="logDo" value="mix" rel="logDo" />
			</p>
			<p>
				<label>综合物料 ：</label>
				<input name="materialCombine.materialId"  type="hidden" >
				<input name="materialCombine.materialName" type="text" />
				<a class="btnLook" href="/material_list_combineLookup_factory.action?logDo=mix" lookupGroup="materialCombine" targetType="dialog">物料带回</a>
			</p>
			<p>
				<label>数量 ：</label>
				<input name="materialCombine.materialId"  type="hidden" >
				<input name="materialCombine.materialName" type="text" />
			</p>
			<p style="height:80px;">
				<label>物料组成：</label>
				
				<input name="materialCombine.materialProviderId" type="hidden" >
				
				<textarea rows="5" cols="20">insert test</textarea>
			</p>
			<p>
				<label>备注：</label>
				<input name="logRemark" type="text" size="25" />
			</p>
		</div>
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
