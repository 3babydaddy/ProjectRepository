<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>党组织信息详情页</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
    <script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.js'/>"></script>    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.css'/>">    
	<script type="text/javascript" src="<c:url value='/resources/plugins/jquery.citys.js'/>"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/ztree/zTreeStyle/zTreeStyle.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.core-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.excheck-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.exedit-3.5.min.js'/>"></script>
</head>
<body >
		<form action="" id="editForm" class="form-horizontal" enctype="multipart/form-data">
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
						<td style="text-align: right;"><font color="red">*</font>党组织名称：</td>
						<td style="text-align: left;">${main.partyOrgName }</td>
						<td style="text-align: right;"><font color="red">*</font>党组织联系电话：</td>
						<td style="text-align: left;">${main.partyOrgTel }</td>
						<td style="text-align: right;"><font color="red">*</font>党组织组建形式：</td>
						<td style="text-align: left;">
							<input class="form-control" readonly="readonly" style="background-color: white;" value="覆盖建立" maxlength="20"/>
							<input class="form-control" type="hidden" name="partyOrgForm" value="3" maxlength="20"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>党组织类别：</td>
						<td style="text-align: left;">
							<select id="partyOrgType" name="partyOrgType" class="form-control" disabled="disabled">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${partyOrgClassList}">
									<option value="${it.code }" <c:if test="${main.partyOrgType == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>隶属党组织名称：</td>
						<td style="text-align: left;">
	    					<input  type="text" id="higherDepart" class="form-control" value="" readonly style="background-color: white;"  onclick="showMenu();"></input>
							<div id="parentPartyOrg" class="parentPartyOrg" style="display:none; position: absolute;background-color: #fff;border:1px solid #95B8E7;">
								<ul id="parentDepartMent" class="ztree" style="margin-top:0; width:160px;"></ul>
							</div>
	    				</td>
						<td style="text-align: right;"><font color="red">*</font>党组织成立时间：</td>
						<td style="text-align: left;">${main.partyOrgTimeTxt }</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>党组织成立相关附件：</td>
						<td style="text-align: left;">
							<input class="form-control" type="text" id="filepartyOrgAttachment" name="filepartyOrgAttachment" value="${main.filepartyOrgAttachment }" onclick="showUpload(this,4)"/>
							<input class="form-control" type="hidden" id="partyOrgAttachment" name="partyOrgAttachment" value="${main.partyOrgAttachment }" />
						</td>
					</tr>
				</table>
		   </div>
		</div>
		
		<div class="panel panel-info" >
		   <div class="panel-heading">
		      <h3 class="panel-title">党组织换届信息(允许录入多次)</h3>
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
						<c:if test="${!empty main.id and changeDateList.size() > 0}">
							<td style="text-align: right;">换届时间：</td>
							<td style="text-align: left;">
								<c:forEach items="${changeDateList }" var="e" varStatus="status">
									<div class="form-inline" style="margin-top:5px;">
										<input class="form-control" type="text" disabled="disabled" id="partymbrInUnpublicNum${status.index }" name="partymbrInUnpublicNum${status.index }" value="${e.changeTimeTxt }"/>
										<input class="form-control" type="text"  id="filepartymbrUnderThirtyfiveNum${status.index }" name="filepartymbrUnderThirtyfiveNum${status.index }" value="${e.changeAttachmentName }" onclick="showUpload(this,2)"/>
										<input class="form-control" type="hidden" id="partymbrUnderThirtyfiveNum${status.index }" name="partymbrUnderThirtyfiveNum${status.index }" value="${e.changeAttachmentId }"/>
									</div>
								</c:forEach>
							</td>
						</c:if>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
		      <h3 class="panel-title">书记信息</h3>
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
						<td style="text-align: right;"><font color="red">*</font>书记姓名：</td>
						<td style="text-align: left;">${main.secretaryName }</td>
						
						<td style="text-align: right;"><font color="red">*</font>出生日期：</td>
						<td style="text-align: left;">${main.secretaryBirthdayTxt }</td>
						
						<td style="text-align: right;"><font color="red">*</font>学历：</td>
						<td align= "left" >
							<select id="secretaryEducation" name="secretaryEducation" class="form-control" disabled="disabled">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${finalEducationList}">
									<option value="${it.code }" <c:if test="${main.secretaryEducation == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
	                    </td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>书记来源：</td>
						<td align= "left" >
							<select id="secretarySource" name="secretarySource" disabled="disabled" class="form-control" >
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${sourceList}">
									<option value="${it.code }" <c:if test="${main.secretarySource == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
	                    </td>
						<td style="text-align: right;">所在单位：</td>
	                    <td align= "left" >${main.secretaryCompanyTxt }</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
		      <h3 class="panel-title">党务副书记及委员</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 	</colgroup>
				 	<tr>
						<c:if test="${!empty main.id and deputsecList.size() > 0}">
							<td style="text-align: left;">
								<c:forEach items="${deputsecList }" var="e" varStatus="status">
									<div class="form-inline" style="margin-top:5px;">
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">类型：</font></label>
										<select name="deputySecretaryType${status.index }" class="form-control" disabled="disabled" style="width:100px;">
												<option value="">--请选择--</option>
												<c:forEach var="it" items="${partyTypeList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryType == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
									    </select>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">姓名：</font></label>
										<input class="form-control" style="width:100px;" disabled="disabled" name="deputySecretaryName${status.index }" value="${e.deputySecretaryName }"/>							  
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">出生日期：</font></label>
										<input class="form-control" style="width:100px;" disabled="disabled" type='date' name="deputySecretaryBirthdayTxt${status.index }" value="${e.deputySecretaryBirthdayTxt }"/>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">性别：</font></label>
										<select  name="deputySecretarySex${status.index }" class="form-control" disabled="disabled" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${genderList}">
												<option value="${it.code }" <c:if test="${e.deputySecretarySex == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">学历：</font></label>
										<select  name="deputySecretaryEducation${status.index }" class="form-control" disabled="disabled" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${finalEducationList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryEducation == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">是否是专职：</font></label>
										<select  name="deputySecretaryIsFullTime${status.index }" class="form-control" disabled="disabled" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${yesNoList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryIsFullTime == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>	
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">是否是理事会<br/>成员：</font></label>
										<select  name="isBoardOfficer${status.index }" class="form-control" disabled="disabled" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${yesNoList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryIsFullTime == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
									</div>
								</c:forEach>
							</td>
						</c:if>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">党员统计信息</h3>
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
						<td style="text-align: right;">组织关系在非公　<br/>企业的党员总数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled"  value="${opcInfo.partymbrInUnpublicNum }"/></td>
						<td style="text-align: right;">35岁以下：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrUnderThirtyfiveNum }"/></td>
						<td style="text-align: right;">中层管理&nbsp;&nbsp;&nbsp;<br/>人员人数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrMiddleManagerNum }"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">中高级专业技术<br/>人员人数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrOnMiddletechNum }"/></td>
						<td style="text-align: right;">生产经营一线<br/>职工人数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrFrontlineNum }"/></td>
						<td style="text-align: right;">组织关系不在非公<br/>企业的党员总数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrNotinUnpublicNum }"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">农村党员数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrInVillageNum }"/></td>
						<td align="right" colspan="4" style="margin-right: 15px;">
							<div align="right" border="false" >
					    		<div class="btn-group">
								  <input type="button" class="btn btn-primary" onclick="showPartyInfo('${orgIds}');" value="查看党员详情页面"></input>
								</div>
					    	</div>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
		      <h3 class="panel-title">其他党务工作者</h3>
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
	                    <td style="text-align: right;"><font color="red">*</font>其他党务工作者兼职人数：</td>
						<td style="text-align: left;">${main.partTimeWorkers }</td>
						
						<td style="text-align: right;"><font color="red">*</font>其他党务工作者全职人数：</td>
						<td style="text-align: left;">${main.fullTimeWorkers }</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
		      <h3 class="panel-title">活动场所情况</h3>
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
						<td style="text-align: right;"><font color="red">*</font>是否有单独活动场所：</td>
						<td align= "left" >
							<c:if test="${main.isOneself eq '1' }">是</c:if>
							<c:if test="${main.isOneself eq '0' }">否</c:if>
	                    </td>
						<td style="text-align: right;"><font color="red">*</font>使用面积：</td>
						<td style="text-align: left;">${main.stageArea }</td>
					</tr>
				</table>
		   </div>
		</div>
		<input type="hidden" id="mainId" name="mainId" value="${main.id }" />
		<input type="hidden" id="modular" name="modular" value="3" />
		<input type="hidden" id="type" name="type" value="1" />
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('lookwin');">关闭窗口</button>
			</div>
    	</div>
		</form>
	
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
		utils.e.openWin('cancelwin','文件上传',url,"60%","40%",function(){
			//reloadData()
		});
	}
	function showPartyInfo(orgIds){
		var url = ctx + '/cover/showPartyInfo?orgIds='+orgIds;
		utils.e.openWin('showPartyInfoWin','党员基本信息',url,"80%","50%",function(){
			//reloadData()
		});
	}
</script>
</html>