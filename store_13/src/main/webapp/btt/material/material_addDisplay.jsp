<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="material_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="240">
			<p>
				<label>物 料 名：</label>
				<input name="materialName" type="text" size="25" value=""/>
			</p>
			<p>
				<label>物 料 代号：</label>
				<input name="materialCode" type="text" size="25" value=""/>
			</p>
			<p>
				<label>规格 / 型号：</label>
				<input name="materialType" type="text" size="25" value=""/>
			</p>
			<p>
				<label>单位：</label>
				<input name="materialUnit" type="text" size="25" value=""/>
			</p>
			
		</div>
		<div class="tabs " currentIndex="0" eventType="click">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li><a href="javascript:;"><span>基本物料</span></a></li>
						<li><a href="javascript:;"><span>成品物料</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent pageFormContent" style="height:150px;">
				<div>
					<p>
						<label>最 少 量：</label>
						<input name="materialMinNum" type="text" size="25" value=""/>
					</p>
					<p>
						<label>批 量 数：</label>
						<input name="materialBatchNum" type="text" size="25" value=""/>
					</p>
					<p>
						<label>批 量 价：</label>
						<input name="materialBatchPrice" type="text" size="25" value=""/>
					</p>
				</div>
				<div>
					<p>
						<label>序 列 号：</label>
						<input name="materialSequence" type="text" size="25" value=""/>
					</p>
					<p>
						<label>通 道 数：</label>
						<input name="materialPassNum" type="text" size="25" value=""/>
					</p>
					<p>
						<label>通 道 号：</label>
						<input name="materialPassNo" type="text" size="25" value=""/>
					</p>
					<p>
						<label>版 本：</label>
						<input name="materialVersion" type="text" size="25" value=""/>
					</p>
					<p>
						<label>使 用：</label>
						<input name="materialUse" type="text" size="25" value=""/>
					</p>
					<p>
						<label>摆放位置：</label>
						<input name="materialPosition" type="text" size="25" value=""/>
					</p>
					<p>
						<label>包含物料：</label>
						<input name="materialCombine.materialId" value="" type="hidden" >
						<input name="materialCombine.materialName" type="text" />
						<a class="btnLook" href="/material_list_combineLookup.action" lookupGroup="materialCombine" targetType="dialog">查找带回</a>
					</p>
				</div>
			</div>
			<div class="tabsFooter">
				<div class="tabsFooterContent"></div>
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
