<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/default/easyui.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/icon.css'/>" />
<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/easyui-lang-zh_CN.js'/>"></script>
<!-- easyui和bootstrap兼容问题 -->
<style>
/*bootstrap兼容问题和easyui的bug*/  
 .panel-header, .panel-body {  
border-width: 0px;  
}  
.datagrid,.combo-p{  
border:solid 1px #D4D4D4;  
}  
.datagrid *{  
-webkit-box-sizing: content-box;  
-moz-box-sizing: content-box;  
box-sizing: content-box;  
} 

.panel{
	font-size: 14px;
	color: #333;
}
.table{
	margin-bottom: 0;
}
.table  tr  td{
	font-size: 14px;
	color: #333;
}
h3.panel-title{
	color:inherit;
	font-weight: 500;
	font-size: 16px;
	line-height: 1.1;
}
.btn{
	margin: 10px 0px;
}
.border{
	border: 1px solid #ddd;
    box-sizing: border-box;
	
}
.padding{
	padding: 15px;
}
.border-notop{
	border: 1px solid #ddd;
    box-sizing: border-box;
    border-top: none;
} 
</style>