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
	<script type="text/javascript" src="<c:url value='/resources/js/socialorg/partyOrgEdit.js?version=${jsversion}'/>"></script>
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
						<td style="text-align: right;">建立党组织单位：</td>
	                    <td style="text-align: left;" colspan="5">
	                    		<div  class="form-control" id="partyOrgDiv" style="height: 70px;width:780px;float:left;">
	                    			<c:forEach var="it" items="${unitList}">
										<button class="btn btn-xs" value="${it.code }" onClick="changeOrg(this)" name="btn_org_name">${it.value }<span class="glyphicon glyphicon-remove"></span></button>
									</c:forEach>
								</div>
								<input type="button" class="btn" value="添加" onclick="showOrgInfoPop();" style="float:left;margin:35px 0 0 10px;" />
	                    </td>
	                </tr>
					<tr>   
						<td style="text-align: right;"><font color="red">*</font>是否成立党组织：</td>
						<td style="text-align: left;">
							<select class="form-control" name="isSetUpPartyOrg" id="isSetUpPartyOrg">
								<option value="" >--请选择--</option>
								<c:forEach var="it" items="${yesNoList}">
									<option value="${it.code }" <c:if test="${main.isSetUpPartyOrg == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>未建立党组织原因：</td>
						<td style="text-align: left;">
							<select id="absencePartyOrgReasion" name="absencePartyOrgReasion" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${noEstablishPartyOrgReasonList}">
									<option value="${it.code }" <c:if test="${main.absencePartyOrgReasion == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>党组织组建形式：</td>
						<td style="text-align: left;">
							<select id="partyOrgForm" name="partyOrgForm" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${partyOrgFormList}">
									<option value="${it.code }" <c:if test="${main.partyOrgForm == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>党组织类别：</td>
						<td style="text-align: left;">
							<select id="partyOrgType" name="partyOrgType" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${partyOrgClassList}">
									<option value="${it.code }" <c:if test="${main.partyOrgType == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>是否选派党建工作<br/>指导员或联络员：</td>
						<td style="text-align: left;">
							<select id="isInstructor" name="isInstructor" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${yesNoList}">
									<option value="${it.code }" <c:if test="${main.isInstructor == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>党组织成立时间：</td>
						<td style="text-align: left;"><input class="form-control" type="text" onClick="WdatePicker()" id="partyOrgTimeTxt" name="partyOrgTimeTxt" value="${main.partyOrgTimeTxt }" maxlength="20"/></td>
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
		<div class="panel panel-info" id="divThree">
		   <div class="panel-heading">
		      <h3 class="panel-title">党组织基本信息</h3>
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
						<td style="text-align: left;"><input class="form-control" name="partyOrgName" value="${main.partyOrgName }"/></td>
						
						<td style="text-align: right;"><font color="red">*</font>党组织联系电话(书记手机号)：</td>
						<td style="text-align: left;"><input class="form-control" name="partyOrgTel" value="${main.partyOrgTel }" maxlength="11" /></td>
						
						<td style="text-align: right;"><font color="red">*</font>隶属党组织名称：</td>
						<td style="text-align: left;">
	    					<input  type="text" id="higherDepart" class="form-control" value="" readonly style="background-color: white;"  onclick="showMenu();"></input>
							<div id="parentPartyOrg" class="parentPartyOrg" style="display:none; position: absolute;background-color: #fff;border:1px solid #95B8E7;">
								<ul id="parentDepartMent" class="ztree" style="margin-top:0; width:160px;"></ul>
							</div>
	    				</td>
					</tr>
				</table>
		   </div>
		</div>
    	<div class="panel panel-info" id="divOne">
		   <div class="panel-heading">
		      <h3 class="panel-title">指导员或联络员信息</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 	</colgroup>
				 	<tr>
						<td style="text-align: left;">
							<c:if test="${instructList.size() == 0}">
								<div class="form-inline" style="margin-top:5px;">
									<label>人员姓名：</label>
									<input class="form-control" name="instructorName" value="" maxlength="20"/>
									<label>单位及职务：</label>
									<input class="form-control" name="instructorJob" value="" maxlength="20"/>
									<label>指导单位：</label>
				                       <select id="zhidaodanwei0" name="instructorUnitTxt" class="form-control"  multiple="multiple">
										    <c:forEach var="it" items="${unitList}">
										        <option value="${it.code }" >${it.value }</option>
										    </c:forEach>
									    </select>
								</div>
							</c:if>
							<c:if test="${!empty main.id and instructList.size() > 0}">
								<c:forEach items="${instructList }" var="e" varStatus="status">
									<div class="form-inline" style="margin-top:5px;">
										<label>人员姓名：</label>
										<input class="form-control" name="instructorName" value="${e.instructorName }" maxlength="20"/>
										<label>单位及职务：</label>
										<input class="form-control" name="instructorJob" value="${e.instructorJob }" maxlength="20"/>
										<label>指导单位：</label>
							               	<select id="zhidaodanwei${status.index }" name="instructorUnitTxt" class="form-control" multiple="multiple">
											    <c:forEach var="it" items="${unitList}">
											        <c:set var="tmpInstructorUnit" value=""/>
													   <c:forEach items="${e.orgIdList }" var="t">
														  <c:if test="${it.code == t}">
														      <c:set var="tmpInstructorUnit" value='selected="selected"' />
														  </c:if>
													  </c:forEach>
											        <option value="${it.code }" ${tmpInstructorUnit}>${it.value }</option>
											    </c:forEach>
										    </select>
									</div>
								</c:forEach>
							</c:if>
							<button type="button" id="add_operate_instructor" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info" id="divEight">
		   <div class="panel-heading">
		      <h3 class="panel-title">下级党组织信息</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 	</colgroup>
				 	<tr>
						<td style="text-align: left;">
							<c:if test="${lowerPartyList.size() == 0}">
								<div class="form-inline" style="margin-top:5px;">
									<label>名称：</label>
									<input class="form-control" name="lowerPartyOrgName" value="" maxlength="20"/>
									<label>类型：</label>
									<select id="lowerPartyOrgType" name="lowerPartyOrgType" class="form-control">
										<option value="">--请选择--</option>
										<c:forEach var="it" items="${partyOrgClassList}">
											<option value="${it.code }" >${it.value }</option>
										</c:forEach>
									</select>
									<label>数量：</label>
									<input class="form-control" name="lowerPartyOrgSum" value="" maxlength="20"/>
								</div>
							</c:if>
							<c:if test="${!empty main.id and lowerPartyList.size() > 0}">
								<c:forEach items="${lowerPartyList }" var="e" >
									<div class="form-inline" style="margin-top:5px;">
										<label>名称：</label>
										<input class="form-control" name="lowerPartyOrgName" value="${e.lowerPartyOrgName }" maxlength="20"/>
										<label>类型：</label>
										<select id="lowerPartyOrgType" name="lowerPartyOrgType" class="form-control">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${partyOrgClassList}">
												<option value="${it.code }" <c:if test="${e.lowerPartyOrgType == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
										<label>数量：</label>
										<input class="form-control" name="lowerPartyOrgSum" value="${e.lowerPartyOrgSum}" maxlength="20"/>
									</div>
								</c:forEach>
							</c:if>
							<button type="button" id="add_operate_lowerParty" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info" id="divTwo">
		   <div class="panel-heading">
		      <h3 class="panel-title">党组织换届信息(允许录入多次)</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 		<col width="90" />
				 		<col width="540"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;">换届时间：</td>
						<td style="text-align: left;">
						
							<c:if test="${changeDateList.size() == 0}">
								<div class="form-inline" style="margin-top:5px;">
									<input class="form-control" type="text" onClick="WdatePicker()" id="partymbrInUnpublicNum0" name="partymbrInUnpublicNum" />
									<label>换届相关附件</label><input class="form-control" type="text" id="filepartymbrUnderThirtyfiveNum0" name="filepartymbrUnderThirtyfiveNum" onclick="showUpload(this,2)"/>
									<input class="form-control" type="hidden" id="partymbrUnderThirtyfiveNum0" name="partymbrUnderThirtyfiveNum" />
								</div>
							</c:if>
							<c:if test="${!empty main.id and changeDateList.size() > 0}">
								<c:forEach items="${changeDateList }" var="e" varStatus="status">
									<div class="form-inline" style="margin-top:5px;">
										<input class="form-control" type="text" onClick="WdatePicker()" id="partymbrInUnpublicNum${status.index }" name="partymbrInUnpublicNum" value="${e.changeTimeTxt }"/>
										<label>换届相关附件</label><input class="form-control" type="text" id="filepartymbrUnderThirtyfiveNum${status.index }" name="filepartymbrUnderThirtyfiveNum" value="${e.changeAttachmentName }" onclick="showUpload(this,2)"/>
										<input class="form-control" type="hidden" id="partymbrUnderThirtyfiveNum${status.index }" name="partymbrUnderThirtyfiveNum" value="${e.changeAttachmentId }"/>
									</div>
								</c:forEach>
							</c:if>
							<button type="button" id="add_operate_address" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		
		<div class="panel panel-info" id="divFour">
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
						<td style="text-align: left;"><input class="form-control" name="secretaryName" value="${main.secretaryName }"/></td>
						
						<td style="text-align: right;"><font color="red">*</font>书记出生日期：</td>
						<td style="text-align: left;"><input class="form-control" name="secretaryBirthdayTxt" type="text" onClick="WdatePicker({maxDate:'%y-%M-%d'})" value="${main.secretaryBirthdayTxt }"/></td>
						
						<td style="text-align: right;"><font color="red">*</font>书记学历：</td>
						<td align= "left" >
							<select id="secretaryEducation" name="secretaryEducation" class="form-control">
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
							<select id="secretarySource" name="secretarySource" class="form-control" >
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${sourceList}">
									<option value="${it.code }" <c:if test="${main.secretarySource == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
	                    </td>
						<td style="text-align: right;"><font color="red">*</font>书记所在单位：</td>
	                    <td align= "left" >
		                    <input class="form-control" type="text" id="secretaryCompanyOne" name="secretaryCompany" value="${main.secretaryCompany }" />
	                    
	                    	<select id="secretaryCompanyTwo" name="secretaryCompany" class="form-control">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${unitList}">
									<option value="${it.code }" <c:if test="${main.secretaryCompany == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
	                    </td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info" id="divFive">
			<div class="panel-heading">
		      <h3 class="panel-title">副书记及委员</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 	</colgroup>
				 	<tr>
						<td style="text-align: left;">
							<c:if test="${deputsecList.size() == 0}">
								<div class="form-inline" style="margin-top:5px;">
									<label style="width:90px;text-align: right;"><font style="font-weight:normal;">类型：</font></label>
									<select name="deputySecretaryType" class="form-control" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${partyTypeList}">
											<option value="${it.code }">${it.value }</option>
										</c:forEach>
								    </select>
							 		<label style="width:90px;text-align: right;"><font style="font-weight:normal;">姓名：</font></label>
							 		<input class="form-control" style="width:100px;" name="deputySecretaryName" value=""/>							  
									<label style="width:90px;text-align: right;"><font style="font-weight:normal;">出生日期：</font></label>
									<input class="form-control" style="width:100px;" type='text' onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="deputySecretaryBirthdayTxt" value=""/>
									<label style="width:90px;text-align: right;"><font style="font-weight:normal;">性别：</font></label>
									<select  name="deputySecretarySex" class="form-control" style="width:100px;">
										<option value="">--请选择--</option>
										<c:forEach var="it" items="${genderList}">
											<option value="${it.code }">${it.value }</option>
										</c:forEach>
									</select>
									<label style="width:90px;text-align: right;"><font style="font-weight:normal;">学历：</font></label>
									<select  name="deputySecretaryEducation" class="form-control" style="width:100px;">
										<option value="">--请选择--</option>
										<c:forEach var="it" items="${finalEducationList}">
											<option value="${it.code }" >${it.value }</option>
										</c:forEach>
									</select>
									<label style="width:90px;text-align: right;"><font style="font-weight:normal;">是否是专职：</font></label>
									<select  name="deputySecretaryIsFullTime" class="form-control" style="width:100px;">
										<option value="">--请选择--</option>
										<c:forEach var="it" items="${yesNoList}">
											<option value="${it.code }" >${it.value }</option>
										</c:forEach>
									</select>	
									<label style="width:90px;text-align: right;"><font style="font-weight:normal;">是否是理事会<br/>成员：</font></label>
									<select  name="isBoardOfficer" class="form-control" style="width:100px;">
										<option value="">--请选择--</option>
										<c:forEach var="it" items="${yesNoList}">
											<option value="${it.code }" >${it.value }</option>
										</c:forEach>
									</select>
									<c:if test="${nature eq '0201' or nature eq '0204'}">
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">其它委员进入<br/>管理情况：</font></label>
										<select  name="otherInfoManager" class="form-control" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${otherInfoManagerList}">
												<option value="${it.code }" >${it.value }</option>
											</c:forEach>
										</select>
									</c:if>					  
								</div>
							</c:if>
							<c:if test="${!empty main.id and deputsecList.size() > 0}">
								<c:forEach items="${deputsecList }" var="e" >
									<div class="form-inline" style="margin-top:5px;">
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">类型：</font></label>
										<select name="deputySecretaryType" class="form-control" style="width:100px;">
												<option value="">--请选择--</option>
												<c:forEach var="it" items="${partyTypeList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryType == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
									    </select>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">姓名：</font></label>
										<input class="form-control" style="width:100px;" name="deputySecretaryName" value="${e.deputySecretaryName }"/>							  
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">出生日期：</font></label>
										<input class="form-control" style="width:100px;" type='text' onClick="WdatePicker({maxDate:'%y-%M-%d'})" name="deputySecretaryBirthdayTxt" value="${e.deputySecretaryBirthdayTxt }"/>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">性别：</font></label>
										<select  name="deputySecretarySex" class="form-control"  style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${genderList}">
												<option value="${it.code }" <c:if test="${e.deputySecretarySex == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">学历：</font></label>
										<select  name="deputySecretaryEducation" class="form-control" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${finalEducationList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryEducation == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">是否是专职：</font></label>
										<select  name="deputySecretaryIsFullTime" class="form-control" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${yesNoList}">
												<option value="${it.code }" <c:if test="${e.deputySecretaryIsFullTime == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>	
										<label style="width:90px;text-align: right;"><font style="font-weight:normal;">是否是理事会<br/>成员：</font></label>
										<select  name="isBoardOfficer" class="form-control" style="width:100px;">
											<option value="">--请选择--</option>
											<c:forEach var="it" items="${yesNoList}">
												<option value="${it.code }" <c:if test="${e.isBoardOfficer == it.code }"> selected="selected"</c:if>>${it.value }</option>
											</c:forEach>
										</select>
										<c:if test="${nature eq '0201' or nature eq '0204'}">
											<label style="width:90px;text-align: right;"><font style="font-weight:normal;">其它委员进入<br/>管理情况：</font></label>
											<select  name="otherInfoManager" class="form-control" style="width:100px;">
												<option value="">--请选择--</option>
												<c:forEach var="it" items="${otherInfoManagerList}">
														<option value="${it.code }" <c:if test="${e.otherInfoManager == it.code }"> selected="selected"</c:if>>${it.value }</option>
												</c:forEach>
											</select>
										</c:if>
									</div>
								</c:forEach>
							</c:if>
							<button type="button" id="add_operate_party" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button>
						</td>
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
						<td style="text-align: left;"><input class="form-control" disabled="disabled"  value="${opcInfo.partymbrGroupInSocielorgNum }"/></td>
						<td style="text-align: right;">35岁以下：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrUnderThirtyfiveNum }"/></td>
						<td style="text-align: right;">大学及以上学历：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrOnCollegeNum }"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">高中及以下学历：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrUnderHighschoolNum }"/></td>
						<td style="text-align: right;">组织关系不在非公<br/>企业的党员总数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${opcInfo.partymbrGroupNotInSocielorgNum }"/></td>
						<td align="right" colspan="2" style="margin-right: 15px;">
							<div align="right" border="false" >
					    		<div class="btn-group">
								  <button type="button" class="btn btn-primary" onclick="showPartyInfo('${orgIds}')">查看党员详情页面</button>
								</div>
					    	</div>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info" id="divSix">
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
	                    <td style="text-align: right;"><font color="red">*</font>兼职人数：</td>
						<td style="text-align: left;"><input class="form-control" name="partTimeWorkers" value="${main.partTimeWorkers }"/></td>
						
						<td style="text-align: right;"><font color="red">*</font>专职人数：</td>
						<td style="text-align: left;"><input class="form-control" name="fullTimeWorkers" value="${main.fullTimeWorkers }"/></td>
						
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info" id="divSev">
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
						<td style="text-align: right;"><font color="red">*</font>单独建设活动场所面积(m²)：</td>
						<td style="text-align: left;"><input class="form-control" name="stageArea" value="${main.stageArea }"/></td>
						<td style="text-align: right;"><font color="red">*</font>与其他单位党组织共用活动场所：</td>
						<td style="text-align: left;"><input class="form-control" name="otherShareStage" value="${main.otherShareStage }"/></td>
	                    <td style="text-align: right;"><font color="red">*</font>与社区共用活动场所活动场所：</td>
						<td style="text-align: left;"><input class="form-control" name="communityShareStage" value="${main.communityShareStage }" maxlength="20"/></td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>其他：</td>
						<td style="text-align: left;"><input class="form-control" name="otherInfo" value="${main.otherInfo }"/></td>
						<c:if test="${nature eq '0201' or nature eq '0204'}">
							<td style="text-align: right;"><font color="red">*</font>是否将党组织建设有关内容纳入学校章程：</td>
							<td align= "left" >
								<select id="isPartyIntoSchool" id="isPartyIntoSchool" name="isPartyIntoSchool" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${yesNoList}">
										<option value="${it.code }" <c:if test="${main.isPartyIntoSchool == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
		                    </td>
							<td style="text-align: right;"><font color="red">*</font>隶属上级党组织类别(学校特有)：</td>
							<td align= "left" >
								<select id="parentPartyOrgType" id="parentPartyOrgType" name="parentPartyOrgType" class="form-control">
									<option value="">--请选择--</option>
									<c:forEach var="it" items="${heightPartyOrgList}">
										<option value="${it.code }" <c:if test="${main.parentPartyOrgType == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
		                    </td>
		                </c:if>
					</tr>
				</table>
		   </div>
		</div>
		<input type="hidden" id="partyOrgIds" name="partyOrgIds" value="${orgIds}"/>
		<input type="hidden" id="reportHigher" name="reportHigher"/>
		<input type="hidden" id="mainId" name="mainId" value="${main.id }" />
		<input type="hidden" id="reportHigher" name="reportHigher"/>
		<input type="hidden" id="subjectionPartyId" name="subjectionPartyId" value="${main.subjectionPartyId}" />
		<input type="hidden" id="subjectionPartyName" name="subjectionPartyName" value="${main.subjectionPartyName }" />
		<input type="hidden" id="id" name="id" value="${main.id }" />
		<input type="hidden" id="changeList" name="changeList" /> 
		<input type="hidden" id="deputySecretaryList" name="deputySecretaryList" /> 
		<input type="hidden" id="instructList" name="instructList" /> 
		<input type="hidden" id="lowerPartyList" name="lowerPartyList" /> 
		<input type="hidden" id="instructSize" name="instructSize" value="${instructSize }" /> 
		<input type="hidden" id="modular" name="modular" value="1" />
		<input type="hidden" id="type" name="type" value="1" />
		<input type="hidden" id="clickSign" name="clickSign" value="${clickSign }" />
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
