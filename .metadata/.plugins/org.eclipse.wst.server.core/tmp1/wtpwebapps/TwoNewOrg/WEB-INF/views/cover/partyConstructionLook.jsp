<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>年度党建情况详情页</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
    <script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.js'/>"></script>    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.css'/>">    
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
		                    <td style="text-align: left;"><input class="form-control" disabled="disabled" name="partyOrgName" value="${main.partyOrgName }" /></td>
		                    
							<td style="text-align: right;"><font color="red">*</font>近三年新发展党员数：</td>  
							<td style="text-align: left;"><input class="form-control" disabled="disabled" name="threeYearsMember" value="${main.threeYearsMember }" /></td>
							
							<td style="text-align: right;"><font color="red">*</font>近三年处置不合格党员数：</td>
							<td style="text-align: left;"><input class="form-control" disabled="disabled" name="threeYearsUnqualifieds" value="${main.threeYearsUnqualifieds }"/></td>
						</tr>
						<tr>
							<td style="text-align: right;"><font color="red">*</font>是否对党员轮训一遍：</td>
							<td style="text-align: left;">
								<c:if test="${main.isTrainingInRotation eq '1' }">是</c:if>
								<c:if test="${main.isTrainingInRotation eq '0' }">否</c:if>
							</td>
							<td style="text-align: right;">党员是否按时足<br/>额主动交纳党费：</td>
							<td style="text-align: left;">
								<c:if test="${main.isPartyMemberTrain eq '1' }">是</c:if>
								<c:if test="${main.isPartyMemberTrain eq '0' }">否</c:if>
							</td>
							<td style="text-align: right;">党建工作经费是否纳入<br/>企业管理费年度预算：</td>
							<td style="text-align: left;">
								<c:if test="${main.isIntoManage eq '1' }">是</c:if>
								<c:if test="${main.isIntoManage eq '0' }">否</c:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">是否按规定开展“三会一课”：</td>
							<td style="text-align: left;">
								<c:if test="${main.isDevelopListen eq '1' }">是</c:if>
								<c:if test="${main.isDevelopListen eq '0' }">否</c:if>
							</td>
							<td style="text-align: right;">是否按规定每年开展民<br/>主评议党员：</td>
							<td style="text-align: left;">
								<c:if test="${main.isDevelopDiscussions eq '1' }">是</c:if>
								<c:if test="${main.isDevelopDiscussions eq '0' }">否</c:if>
							</td>
							<td style="text-align: right;">是否按规定每年开展党<br/>员党性分析：</td>
							<td style="text-align: left;">
								<c:if test="${main.isDevelopAnalysis eq '1' }">是</c:if>
								<c:if test="${main.isDevelopAnalysis eq '0' }">否</c:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">党组织是否按规定进行换届：</td>
							<td style="text-align: left;">
								<c:if test="${main.isChangeEveryyear eq '1' }">是</c:if>
								<c:if test="${main.isChangeEveryyear eq '0' }">否</c:if>
							</td>
							<td style="text-align: right;">是否倒排相对后进基层<br/>党组织：</td>
							<td style="text-align: left;">
								<c:if test="${main.isBackwardParty eq '1' }">是</c:if>
								<c:if test="${main.isBackwardParty eq '0' }">否</c:if>
							</td>
							<td style="text-align: right;">是否完成整顿相对后进<br/>基层党组织：</td>
							<td style="text-align: left;">
								<c:if test="${main.isRectifyParty eq '1' }">是</c:if>
								<c:if test="${main.isRectifyParty eq '0' }">否</c:if>
							</td>
						</tr>
						<tr>
							<td style="text-align: right;">整顿报告：</td>
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
							<td style="text-align: left;"><input class="form-control" name="secretaryTotalTime" disabled="disabled" value="${main.secretaryTotalTime }" /></td>
							
							<td style="text-align: right;"><font color="red">*</font>党员总学时：</td>
							<td style="text-align: left;"><input class="form-control" name="partyTotalTime" disabled="disabled" value="${main.partyTotalTime }"/></td>
							
							<td style="text-align: right;"><font color="red">*</font>党员平均学时：</td>
							<td style="text-align: left;"><input class="form-control" name="partyAvgTime" disabled="disabled" value="${main.partyAvgTime }"/></td>
						</tr>
					</table>
			   </div>
			</div>
			<input type="hidden" id="reportHigher" name="reportHigher"/>
			<input type="hidden" id="mainId" name="mainId" value="${main.id }"/>
			<input type="hidden" id="coverPartyOrgId" name="coverPartyOrgId" value="${main.coverPartyOrgId }"/>
			<input type="hidden" id="modular" name="modular" value="3" />
			<input type="hidden" id="type" name="type" value="2" />
		</form>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('lookwin');">关闭窗口</button>
			</div>
    	</div>
	
</body>
<script type="text/javascript">
	function showUpload(obj, action){
		var id = $("#"+obj.id.replace('file','')).val();
		var filename = $("#"+obj.id).val();
		var mainId = $("#mainId").val();
		var modular = $("#modular").val();
		var type = $("#type").val();
		var action = action;
		var url = ctx + '/unpublic/uploadFile?id='+id+'&mainTableId='+mainId+'&filename='+filename+
							'&sign='+obj.id+'&modular='+modular+'&type='+type+'&action='+action+'&method=look';
		//openWin("撤销", url,"50%","90%",function(){reloadData()});
		utils.e.openWin('cancelwin','文件上传',url,"60%","80%",function(){
			//reloadData()
		});
	}
</script>
</html>