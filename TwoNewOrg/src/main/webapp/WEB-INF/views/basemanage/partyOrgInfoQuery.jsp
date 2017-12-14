<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html lang="zh-CN">
<head>
	<title>党组织信息展示</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/ztree/zTreeStyle/zTreeStyle.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.core-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.excheck-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.exedit-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/basemanage/partyOrgInfoQuery.js?time=new Date()'/>"></script>
</head>
<body>
	<div class="easyui-panel" title="党组织查询" style="width:100%;">
		<div style="padding:0px 30px 20px 20px;margin-top:20px;">
			<table cellspacing="15px">
				<tr>
					<td align="right">党组织名称：<input class="easyui-textbox" type="text" id="nameTxt" ></input></td>
				</tr>
				<tr style="height:10px;"></tr>
				<tr>
					<td colspan="2">
						<a href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" id="addBtn">新建</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" id="editBtn">修改</a>
						<a href="javascript:void(0)" class="easyui-linkbutton" style="width:50px;" id="searchBtn">检索</a>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="easyui-panel" title="党组织信息" style="width:95%">
			<div style="padding:10px 60px 20px 60px">
				<div style="margin:20px 0;"/>
		    	<ul id="partyOrgInfoTree" class="ztree" ></ul>
	   		</div>
   	</div>
	
</body>
</html>