<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>预警规则详情页</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
    <script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.js'/>"></script>    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.css'/>">    
	<script type="text/javascript" src="<c:url value='/resources/js/intelligentwarn/earlyWarnSetEdit.js?version=${jsversion}'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/jquery.citys.js'/>"></script>
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
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font style="font-size:14px;">预警规则名称：</font></td>
	                    <td align="left"><input class="form-control" name="warningTitle" value="${sponsor.sponsorName }" maxlength="20"/></td>
						<td style="text-align: right;"><font color="red">*</font><font style="font-size:14px;">提醒周期：</font></td>
						<td align="left">
							<select class="form-control" name="warningCycle" id="warningCycle">
								<c:forEach var="it" items="${reminderCycleList}">
									<option value="${it.code }" <c:if test="${main.name == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font style="font-size:14px;">预警发送时间：</font></td>
	                    <td align="left"><input class="form-control" type="date" name="expectStartTime" value="${sponsor.sponsorName }" maxlength="20"/></td>
					</tr>
					<tr>
						<td style="text-align: right;"><font style="font-size:14px;">预警开关：</font></td>
	                    <td align= "left" >
							<select id="warningSwitch" name="warningSwitch" class="form-control" >
								<c:forEach var="it" items="${warnSwitchList}">
									<option value="${it.code }" <c:if test="${main.name == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
		                </td>
						<td style="text-align: right;"><font style="font-size:14px;">预警规则缘由：</font></td>
						<td style="text-align: left;"><textarea style="height:50px;" class="form-control" name="warningRulesContent" value="${sponsor.sponsorName }" maxlength="20"></textarea></td>
						<td style="text-align: right;"><font color="red">*</font>发送短信内容：</td>
						<td style="text-align: left;"><textarea rows="2" cols="1" class="form-control" type="file" name="warningMsg" value="${sponsor.sponsorName }" maxlength="20"></textarea></td>
					</tr>
					<tr>
						<td style="text-align: right;"><font style="font-size:14px;">未及时更新<br/>延期时间：</font></td>
						<td style="text-align: left;">
							<div class="form-inline">
								<input class="form-control" style="width:80px;" name="overTime" value="" maxlength="20" />
								<select class="operate_address form-control" name="overTimeUnit">
									<option value="">-请选择-</option>
									<c:forEach var="it" items="${timeUnitList}">
										<option value="${it.code }">${it.value }</option>
									</c:forEach>
								</select>
							</div>
						</td>
					</tr>
				</table>
		   </div>
		</div>
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title"><font style="font-size:14px;">选择发送人员</font></h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 		<col width="90" />
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font color="red">*</font><font style="font-size:14px;">部门名称：</font></td>
						<td style="text-align: left;">
							<div class="form-inline" style="margin-top:5px;">
								<input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();" class="form-control" maxlength="50"/>
		                    	<input type="hidden" id="instructorUnit" name="instructorUnit" />
		                    	
		                    	<input class="form-control" type="checkbox" style="margin:-2px 0 0 2px;width:13px;" name="warningPeoplesTypes" type="checkbox" />一把手
		                    	<input class="form-control" type="checkbox" style="margin:-2px 0 0 2px;" name="warningPeoplesTypes" type="checkbox" />主管领导
		                    	<input class="form-control" type="checkbox" style="margin:-2px 0 0 2px;" name="warningPeoplesTypes" type="checkbox" />工作人员
							</div> 
							<button type="button" id="add_operate_address" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button> 
						</td>
					</tr>
				</table>
		   </div>
		</div>
		
	</form>
	<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
   		<div class="btn-group">
		  <button type="button" class="btn btn-primary" onclick="javascript:save(1);">保存</button>
		  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('editwin');">关闭窗口</button>
		</div>
   	</div>
   	<div id="addpartymbrWin"></div>
   	<div id="removepartymbrWin"></div>
	
</body>
</html>