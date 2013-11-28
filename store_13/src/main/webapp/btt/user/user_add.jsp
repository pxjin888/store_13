<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div class="pageContent">
	<form method="post" action="demo/common/ajaxDone.html" class="pageForm required-validate" onsubmit="return validateCallback(this, navTabAjaxDone);">
		<div class="pageFormContent" layoutH="56">
			<p>
				<label>用 户 名：</label>
				<input type="hidden" name="user_id" value="${request.user.userId}">
				<input name="sn" type="text" size="30" value="${request.user.userName}"/>
			</p>
			<s:if test="#request.user==null">
			<p>
				<label>密码：</label>
				<input name="name" class="required" type="text" size="30" value="张慧华" alt="请输入客户名称"/>
			</p>
			<p>
				<label>再次输入密码：</label>
				<input type="text" class="required" name="orgLookup.orgName" value="" />
			</p>
			</s:if>
			<s:else>
				<input type="hidden" value="#request.user.password">
			</s:else>
			<p>
				<label>部门编号：</label>
				<input type="text" readonly="readonly" value="" name="dwz_orgLookup.orgNum" class="textInput">
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
