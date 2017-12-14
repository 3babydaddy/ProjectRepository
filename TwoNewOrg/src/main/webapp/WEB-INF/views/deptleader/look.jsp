<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>部门主要领导编辑页</title>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
</head>
<body >
		<form action="" id="editForm" class="form-horizontal">
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">基本信息</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 		<col width="90" />
				 		<col width="120"/>
				 		<col width="100"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>姓　　名：</td>
						<td style="text-align: left;">
						${info.name }
						</td>
						<td style="text-align: right;"><font color="red">*</font>职　　务：</td>
						<td style="text-align: left;">
						${info.post }
						</td>
						<td style="text-align: right;"><font color="red">*</font>职　　级：</td>
						<td style="text-align: left;">
						${info.postLevel }
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>工作手机号：</td>
						<td style="text-align: left;">
						${info.contactTel }
						</td>
						<td style="text-align: right;"><font color="red">*</font>类　　型：</td>
						<td style="text-align: left;">
						${info.type }
						</td>
						<td style="text-align: right;"><font color="red">*</font>所在部门：</td>
						<td style="text-align: left;">
						${info.deptName }
						</td>
						
					</tr>
				</table>
		   </div>
		</div>
		
		</form>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('lookwin');">关闭窗口</button>
			</div>
    	</div>
    	<div id="addpartymbrWin"></div>
    	<div id="removepartymbrWin"></div>
</body>
</html>