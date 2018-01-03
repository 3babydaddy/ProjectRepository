<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<html>
<head>
	<title>数据字典管理</title>
	
	<script type="text/javascript" src="<c:url value='/resources/js/systemset/datadictionary.js??version=${jsversion}'/>"></script>
</head>
<body>
<div class="easyui-layout" style="width:100%;height:100%;" >
	<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 140px;" align="center">
			<table cellpadding="5" border="0" cellspacing="0">
				<colgroup>
			 		<col width="120"/>
			 		<col width="120"/>
			 		<col width="120"/>
			 		<col width="120"/>
			 		<col width="120"/>
			 		<col width="120"/>
			 		<col width="120"/>
			 		<col width="120"/>
			 	</colgroup>
			 	<tr>
					<td align="right">代码名称：</td>
					<td align="left"><input class="easyui-textbox" id="search_dmm" ></input></td>
					<td align="right">键：</td>
					<td align="left"><input class="easyui-textbox" id="search_code" ></input></td>
					<td align="right">值：</td>
					<td align="left"><input class="easyui-textbox" id="search_value" ></input></td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
					</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-reload" id="loadBtn">重新加载</a>
					</td>
				</tr>
				
			</table>
	</div>
	<div id='editWin' closed="true" >
		<form id="editForm">
		<table style="margin: 50px auto 0 auto;text-align: right;">
			<tr>
				<td>代码名称：</td>
				<td><input type="text" id="edit_dmm" name="dmm" style="width: 350px" required=true class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td>键：</td>
				<td><input type="text" id="edit_code" name="code" style="width: 350px" required=true class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td>值：</td>
				<td><input type="text" id="edit_value" name="value" style="width: 350px" required=true class="easyui-validatebox"/></td>
			</tr>
			<tr>
				<td>级别：</td>
				<td><input type="text" id="edit_level" style="width: 350px" name="level"/></td>
			</tr>
			<tr>
				<td>父级别代码：</td>
				<td><input type="text" id="edit_pdmm" style="width: 350px" name="pdmm"/></td>
			</tr>
			<tr>
				<td>排序：</td>
				<td><input type="text" id="edit_orders" style="width: 350px" name="orders"/></td>
			</tr>
			<tr>
				<td>备注：</td>
				<td><input type="text" id="edit_remarks" style="width: 350px" name="remarks"/></td>
			</tr>
			<tr>
				<td></td>
				<td style="text-align: left;">
				<label><input type="checkbox" id="edit_isLoad" name="isLoad" checked="checked" value="1"/>加载到缓存</label></td>
			</tr>
		</table>
		</form>
	</div>
	<div data-options="region:'center'"  id="orderInfoTbl" style="height: auto;" ></div>
	
</div>
</body>
</html>