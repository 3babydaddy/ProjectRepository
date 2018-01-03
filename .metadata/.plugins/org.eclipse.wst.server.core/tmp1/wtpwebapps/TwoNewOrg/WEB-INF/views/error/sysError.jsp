<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
	<title>滨海新区"两新"组织信息管理系统</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/default/easyui.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/icon.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/plugins/jquery-1.11.3.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jquery.easyui.min.js'/>"></script>
</head>
<script type="text/javascript">
	$(document).ready(function() {
		
		if (window.top !=window.self) {
			window.top.location.href="/TwoNewOrg/sysError";
		}
	})
</script>
<body>
<h2>系统异常</h2>
<p>系统异常或者您尚未登入，请联系管理员或<a href="javascript:void(0)"  onclick="window.top.location.href='/TwoNewOrg/'">重新登入</a>。</p>
</body>
</html>