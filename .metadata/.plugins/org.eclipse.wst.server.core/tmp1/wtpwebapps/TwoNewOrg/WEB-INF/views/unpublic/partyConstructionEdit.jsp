<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>年度党建情况详情页</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
    <script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.js'/>"></script>    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.css'/>">    
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/partyConstructionEdit.js?version=${jsversion}'/>"></script>
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
							<td style="text-align: right;">党组织名称：</td>
		                    <td style="text-align: left;"><input class="form-control" name="partyOrgName" value="${main.partyOrgName }" /></td>
		                    
							<td style="text-align: right;"><font color="red">*</font>近三年新发展党员数：</td>  
							<td style="text-align: left;"><input class="form-control" name="threeYearsMember" value="${main.threeYearsMember }" /></td>
							
							<td style="text-align: right;"><font color="red">*</font>近三年处置不合格党员数：</td>
							<td style="text-align: left;"><input class="form-control" name="threeYearsUnqualifieds" value="${main.threeYearsUnqualifieds }"/></td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>是否对党员轮训一遍：</td>
							<td style="text-align: left;">
								<select class="form-control" name="isTrainingInRotation" id="isTrainingInRotation">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isTrainingInRotation == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>党员是否按时足<br/>额主动交纳党费：</td>
							<td style="text-align: left;">
								<select id="isPartyMemberTrain" name="isPartyMemberTrain" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isPartyMemberTrain == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>党建工作经费是否纳入<br/>企业管理费年度预算：</td>
							<td style="text-align: left;">
								<select id="isIntoManage" name="isIntoManage" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isIntoManage == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>是否按规定开展“三会一课”：</td>
							<td style="text-align: left;">
								<select id="isDevelopListen" name="isDevelopListen" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isDevelopListen == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>是否按规定每年开展民<br/>主评议党员：</td>
							<td style="text-align: left;">
								<select id="isDevelopDiscussions" name="isDevelopDiscussions" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isDevelopDiscussions == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>是否按规定每年开展党<br/>员党性分析：</td>
							<td style="text-align: left;">
								<select id="isDevelopAnalysis" name="isDevelopAnalysis" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isDevelopAnalysis == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>党组织是否按规定进行换届：</td>
							<td style="text-align: left;">
								<select id="isChangeEveryyear" name="isChangeEveryyear" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isChangeEveryyear == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>是否倒排相对后进基层<br/>党组织：</td>
							<td style="text-align: left;">
								<select id="isBackwardParty" name="isBackwardParty" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isBackwardParty == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right;"><font color="red">*</font>是否完成整顿相对后进<br/>基层党组织：</td>
							<td style="text-align: left;">
								<select id="isRectifyParty" name="isRectifyParty" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isRectifyParty == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>整顿报告：</td>
							<td style="text-align: left;">
								<input class="form-control" type="text" id="filerectifyAtachementId" name="filerectifyAtachementId" value="${main.rectifyAtachementIdTxt }" onclick="showUpload(this, 3)" />
								<input type="hidden" id="rectifyAtachementId" name="rectifyAtachementId" value="${main.rectifyAtachementId }"/>
							</td>
						</tr>
					</table>
			   </div>
			</div>
			<div class="panel panel-info">
				<div class="panel-heading">
			      <h3 class="panel-title">学时</h3>
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
							<td style="text-align: right;"><font color="red">*</font>书记总学时：</td>
							<td style="text-align: left;"><input class="form-control" name="secretaryTotalTime"  value="${main.secretaryTotalTime }" /></td>
							
							<td style="text-align: right;"><font color="red">*</font>党员总学时：</td>
							<td style="text-align: left;"><input class="form-control" name="partyTotalTime" value="${main.partyTotalTime }"/></td>
							
							<td style="text-align: right;"><font color="red">*</font>党员平均学时：</td>
							<td style="text-align: left;"><input class="form-control" name="partyAvgTime" value="${main.partyAvgTime }"/></td>
						</tr>
					</table>
			   </div>
			</div>
			
			<input type="hidden" id="reportHigher" name="reportHigher"/>
			<input type="hidden" id="id" name="id" value="${main.id }"/>
			<input type="hidden" id="unpublicPartyOrgId" name="unpublicPartyOrgId" value="${main.unpublicPartyOrgId }"/>
			<input type="hidden" id="modular" name="modular" value="2" />
			<input type="hidden" id="type" name="type" value="2" />
		</form>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:save(0);">保存</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:submitReport();">上报</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('editwin');">关闭窗口</button>
			</div>
    	</div>
    	<div id="addpartymbrWin"></div>
    	<div id="removepartymbrWin"></div>
	
</body>
</html>