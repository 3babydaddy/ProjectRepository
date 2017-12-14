<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/index/include.jsp"%> --%>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<%@ include file="/WEB-INF/views/common/upload_file.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
	<title></title>
	<%@ include file="/WEB-INF/views/index/EasyuiBootstrap.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/partyOrgFileUpload.js?version=${jsversion}'/>"></script>
</head>
<body >
	<div class="panel panel-info">
		<div class="panel-heading">
	      <h3 class="panel-title">附件列表</h3>
	   </div>
	   <div class="panel-body" align="center">
			<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
			</div>
	   </div>
   </div>
	<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
   		<div class="btn-group">
		  <button type="button" class="btn btn-primary" onclick="closeJbox()">关闭窗口</button>
		</div>
   	</div>
   	<input type="hidden" id="id" value="${main.id }"/>
   	<input type="hidden" id="fileUrl" value=""/>
   	<input type="hidden" id="uploadFileName" value="${main.filename}"/>
   	<input type="hidden" id="mainId" value="${main.mainTableId }"/>
   	<input type="hidden" id="sign" value="${sign }"/>
   	<input type="hidden" id="method" value="${method }"/>
   	<input type="hidden" id="modelFile" value="${main.modular }"/>
   	<input type="hidden" id="typeFile" value="${main.type }"/>
   	<input type="hidden" id="actionFile" value="${main.action }"/>
    
   	<iframe id="hiddeFileIframe" style="display: none;"></iframe>
</body>

</html>