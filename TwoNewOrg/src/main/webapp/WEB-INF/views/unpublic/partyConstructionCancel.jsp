<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/index/include.jsp"%> --%>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/partyConstructionCancel.js?version=${jsversion}'/>"></script>
</head>
<body >
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">备注</h3>
		   </div>
		   <div class="panel-body" align="center" >
		   		<form class="form-inline" role="form">
			   		 <div class="form-group">
	                       <textarea id="remarks" name="remarks" class="form-control" style="width: 638px;height:100px">${reason.reason }</textarea>
	                 </div>
                 </form>
		   </div>
		</div>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
    		  <button type="button" class="btn btn-primary" onclick="javascript:save();" id="saveBtn">确认撤销</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('cancelwin');">关闭窗口</button>
			</div>
    	</div>
    	<input type="hidden" id="method" value="${method }"/>
   		<input type="hidden" id="mainId" value="${id }"/>
</body>

</html>