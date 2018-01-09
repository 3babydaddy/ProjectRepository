<%@page import="java.util.Date"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:set var="jsversion" value="<%=new Date().getTime() %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
 <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
 <!-- Bootstrap -->
 <link href="<c:url value='/resources/plugins/bootstrap-3.3.5/css/bootstrap.min.css'/>" rel="stylesheet">
 <!-- 可选的Bootstrap主题文件（一般不使用） -->
<link href="<c:url value='/resources/plugins/bootstrap-3.3.5/css/bootstrap-theme.min.css'/>" rel="stylesheet">
<link href="<c:url value='/resources/plugins/bootstrap-multiselect-0.9.13/dist/css/bootstrap-multiselect.css'/>" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/resources/plugins/zui/lib/bootbox/bootbox.css">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/gray/easyui.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/icon.css'/>" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
   <script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
   <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script type="text/javascript" src="<c:url value='/resources/plugins/jquery-1.11.3.min.js'/>"></script>
<!--IE8只能支持jQuery1.9-->
<!--[if lte IE 8]>
<script src="http://cdn.bootcss.com/jquery/1.9.1/jquery.min.js"></script>
<![endif]-->
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="<c:url value='/resources/plugins/bootstrap-3.3.5/js/bootstrap.min.js'/>"></script>
<%-- <script src="<c:url value='/resources/plugins/bootstrap-3.3.5/js/bootlint.js'/>"></script> --%>
<script type="text/javascript" src="<c:url value='/resources/plugins/layer/layer.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/My97DatePicker/WdatePicker.js'/>"></script>
<script src="${ctx }/resources/plugins/zui/lib/bootbox/bootbox.min.js"></script>
<!-- 验证框架-->
<script src="${ctx}/resources/plugins/jqueryValidate/jquery.validate.min.js"></script>
<script src="${ctx}/resources/plugins/jqueryValidate/additional-methods.min.js"></script>
<script src="${ctx}/resources/plugins/jqueryValidate/localization/messages_zh.min.js"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jquery.jdirk.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jeasyui.extensions.messager.alert.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/easyui-lang-zh_CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-multiselect-0.9.13/dist/js/bootstrap-multiselect.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/index/public.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/comm/jqueryValidateExt.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/comm/EasyuiExtend.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/comm/utils.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/comm/public.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/comm/common.js'/>"></script>
<script>
var ctx="${ctx}";
var currentUser = "${user.username}"; 
var isQuWeiDept = ${isQuWeiDept}; 
</script>
<style type="text/css">
#Absolute-Center {  
  margin: auto;  
  width: inherit;
  height: inherit;
  position: fixed;
  /* top: 0; left: 0; bottom: 0; right: 0;   */
}  
.table th, .table td { 
	text-align: center;
	vertical-align: middle;
}
</style>
<style>
label.error {
	color: red;
	font-weight: normal;
	margin-left: 0.5em;
	float: none;
}

select.error,input.error {
	border: 1px dotted red;
}
</style>

