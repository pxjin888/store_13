<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="materialFactory_add.action" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>供 应 商 编 码：</label>
				<input name="materialFactoryCode" type="text" size="25" value=""/>
			</p>
			<p>
				<label>供 应 商 名 称：</label>
				<input name="materialFactoryName" type="text" size="25" value=""/>
			</p>
			<p>
				<label>地  址：</label>
				<input name="materialFactoryAddress" type="text" size="25" value=""/>
			</p>
			<p>
				<label>联 系 人：</label>
				<input name="materialFactoryContactName" type="text" size="25" value=""/>
			</p>
			<p>
				<label>职 位：</label>
				<input name="materialFactoryTitle" type="text" size="25" value=""/>
			</p>
			<p>
				<label>联 系 电 话：</label>
				<input name="materialFactoryContactphone" type="text" size="25" value=""/>
			</p>
			<p>
				<label>其 他 联 系 方 式：</label>
				<input name="materialFactoryContactOther" type="text" size="25" value=""/>
			</p>
			<p>
				<label>邮 箱：</label>
				<input name="materialFactoryMail" type="text" size="25" value=""/>
			</p>
			<p>
				<label>网 站：</label>
				<input name="materialFactoryWeb" type="text" size="25" value=""/>
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
