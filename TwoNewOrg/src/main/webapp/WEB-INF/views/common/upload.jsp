<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
    <script type="text/javascript" src=" <c:url value="/resources/plugins/uploadify/jquery.uploadify.min.js"/> "></script>
    <script type="text/javascript" src=" <c:url value="/resources/js/comm/upload.js"/> "></script>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/uploadify/uploadify.css'/>" />
    <div id="uploadDlg" class="easyui-window" title="上传附件"
	    data-options="iconCls:'icon-save',modal:true,collapsible:false,minimizable:false,maximizable:false,closed:true" 
	    style="width:400px;height:300px;padding:10px;display: none;">
		<input id="posterUpload" name="file" type="file" />
	</div>
</html>
