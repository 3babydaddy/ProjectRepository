<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>组织信息详情页</title>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<script type="text/javascript" src="<c:url value='/resources/js/socialorg/orgEdit.js?version=${jsversion}'/>"></script>
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
						<td style="text-align: right;"><font color="red">*</font>名　　称：</td>
						<td style="text-align: left;"><input class="form-control" name="name" value="${main.name }" maxlength="50"/></td>
						<td style="text-align: right;"><font color="red">*</font>性　　质：</td>
						<td style="text-align: left;">
							<select class="form-control" name="nature">
								<option value="" >--请选择--</option>
	                            <c:forEach var="it" items="${orgNatureList}">
								<optgroup label="${it.key }">
									<c:forEach items="${it.value }" var="cit">
										<option value="${cit.code }" <c:if test="${main.nature == cit.code }"> selected="selected"</c:if>>${cit.value }</option>
									</c:forEach>
								</optgroup>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>类　　别：</td>
						<td style="text-align: left;">
							<select class="form-control" name="category">
								<option value="" >--请选择--</option>
	                            <c:forEach var="it" items="${orgCategoryList}">
	                               <option value="${it.code}" <c:if test="${main.category == it.code }"> selected="selected"</c:if>>${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>登记机构：</td>
						<td style="text-align: left;">
							<input class="form-control" name="registerOrg" value="${main. registerOrg}" maxlength="100"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>业　　务　<br/>主管单位：</td>
						<td style="text-align: left;"><input class="form-control" name="businessDirectorOrg" value="${main.businessDirectorOrg}" maxlength="100" readonly="readonly"/></td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>住　　地：</td>
						<td style="text-align: left;" colspan="3"><input class="form-control" name="address" value="${main.address }" maxlength="100"/></td>
						
					</tr>
				</table>
		   </div>
		</div>
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">从业人员情况</h3>
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
						<td style="text-align: right;"><font color="red">*</font>总　　数：</td>
						<td style="text-align: left;"><input class="form-control" name="jobinTotalnum" value="${count. jobinTotalnum}" maxlength="9" type="number"/></td>
						<td style="text-align: right;"><font color="red">*</font>专职人员：</td>
						<td style="text-align: left;"><input class="form-control" name="jobinMajorNum" value="${count.jobinMajorNum }" maxlength="9" type="number"/></td>
						<td style="text-align: right;"><font color="red">*</font>兼职人员：</td>
						<td style="text-align: left;"><input class="form-control" name="jobinPluralityNum" value="${count.jobinPluralityNum }" maxlength="9" type="number"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">中共党员：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="jobinPartymemberNum" value="${count.jobinPartymemberNum }" maxlength="9" type="number"/></td>
						<td style="text-align: right;">社会团体单位&nbsp;<br>会员数量(个)：</td>
						<td style="text-align: left;"><input class="form-control" name="jobinSocialteamGroupmemberNum" value="${count.jobinSocialteamGroupmemberNum }" maxlength="9" type="number"/></td>
						<td style="text-align: right;">社会团体个人&nbsp;<br>会员数量(人)：</td>
						<td style="text-align: left;" ><input class="form-control" name="jobinSocialteamIndividualmemberNum" value="${count.jobinSocialteamIndividualmemberNum }" maxlength="9" type="number"/></td>
					</tr>
				</table>
		   </div>
		</div>
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">负责人情况</h3>
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
						<td style="text-align: right;"><font color="red">*</font>姓　　名：</td>
						<td style="text-align: left;"><input class="form-control" name="chargeName" value="${charge.chargeName }" maxlength="20"/></td>
						<td style="text-align: right;"><font color="red">*</font>是否党员：</td>
						<td style="text-align: left;">
							<select class="form-control" name="chargePartymemberIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
	                               <option value="${it.code}" <c:if test="${charge.chargePartymemberIs == it.code }"> selected="selected"</c:if>>${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>是 否 兼 任　<br>党组织书记：</td>
						<td style="text-align: left;">
							<select class="form-control" name="chargePartyorgSecretaryIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
	                               <option value="${it.code}" <c:if test="${charge.chargePartyorgSecretaryIs == it.code }"> selected="selected"</c:if>>${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>是否担任区县级<br>以上（含区县）<br>“两代表一委员”：</td>
						<td style="text-align: left;">
							<select id="quxianji" class="form-control" name="chargeTwodeputyAcommitteeIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
	                               <option value="${it.code}" <c:if test="${charge.chargeTwodeputyAcommitteeIs == it.code }"> selected="selected"</c:if>>${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
					</tr>
					<c:if test="${charge.chargeTwodeputyAcommitteeIs == 1 }">
					<tr id="otherTypeTr">
					</c:if>
					<c:if test="${charge.chargeTwodeputyAcommitteeIs != 1 }">
					<tr id="otherTypeTr" style="display: none;">
					</c:if>
						<td style="text-align: right;">具体类型：</td>
						<td style="text-align: left;" colspan="4">
							<c:forEach items="${partyDeputyTypeList }" var="it">
							<c:set var="tmpDeputyType" value=""/>
							<c:forEach items="${deputyTypes }" var="t">
							<c:if test="${it.code == t}">
							<c:set var="tmpDeputyType" value='checked="checked"' />
							</c:if>
							</c:forEach>
							<label class="checkbox-inline">
							  <input type="checkbox" ${tmpDeputyType } name="chargeTwodeputyAcommitteeTypeTxt" <c:if test="${it.code == 10 }">id="otherCheckbox"</c:if> value="${it.code }"> ${it.value }
							</label>
							</c:forEach>
							
						</td>
						<td><input class="form-control" id="otherTxt" disabled="disabled" name="chargeTwodeputyAcommitteeTypeOther" value="${charge.chargeTwodeputyAcommitteeTypeOther }"/></td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info" style="display: none;">
		   <div class="panel-heading">
		      <h3 class="panel-title">党员信息</h3>
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
						<td style="text-align: right;">纳入社会组织党组织<br>管理的党员总数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${pmbrCount.partymbrInSocielorgNum }"/></td>
						<td style="text-align: right;">组织关系在社会组织<br>党组织的党员数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${pmbrCount.partymbrGroupInSocielorgNum }"/></td>
						<td style="text-align: right;">35岁以下：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${pmbrCount.partymbrUnderThirtyfiveNum }"/></td>
					</tr>
					<tr>
						<td style="text-align: right;">大学及以上学历：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${pmbrCount.partymbrOnCollegeNum }"/></td>
						<td style="text-align: right;">高中及以下学历：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${pmbrCount.partymbrUnderHighschoolNum }"/></td>
						
					</tr>
				</table>
				<c:if test="${!empty main.id }">
				<%-- <%@include file="partyMbrList.jsp" %> --%>
				<%-- <iframe id="mainframe" src="${ctx }/socialorg/partyMbrList?id=${main.id}" style="height:auto;width: 100%; "></iframe> --%>
				<!-- <button type="button" class="btn btn-primary" onclick="javascript:showPartyMbrList();">增加</button>
				<button type="button" class="btn btn-primary" onclick="javascript:showPartyMbrList();">减少</button> -->
				</c:if>
		   </div>
		</div>
		<div class="panel panel-info">
			<div class="panel-heading">
		      <h3 class="panel-title">临时统计</h3>
		   </div>
		   <div class="panel-body" align="left">
		   	<select id="otherCondition" name="otherCondition" class="form-control" multiple="multiple">
				<c:forEach var="it" items="${otherConditionList}">
				<c:set var="tmp" value=""/>
				<c:forEach items="${otherCounts }" var="t">
				<c:if test="${it.code == t.fieldName}">
				<c:set var="tmp" value='selected="selected"' />
				</c:if>
				</c:forEach>
				<option value="${it.code }" ${tmp }>${it.value }</option>
				</c:forEach>
			</select>
			</div>
		</div>
		
		<input type="hidden" id="reportHigher" name="reportHigher"/>
		<input type="hidden" id="mainId" name="mainId" value="${main.id }"/>
		<input type="hidden" id="jobinId" name="jobinId" value="${count.id }"/>
		<input type="hidden" id="pmbrCountId" name="pmbrCountId" value="${pmbrCount.id }"/>
		<input type="hidden" id="chargeId" name="chargeId" value="${charge.id }"/>
		<input type="hidden" id="flag" name="flag" value="${flag}"/>
		
		<input type="hidden" id="chargeTwodeputyAcommitteeType" name="chargeTwodeputyAcommitteeType"/>
		</form>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:save(1);">保存</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:reportHigher();">上报</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('editwin');">关闭窗口</button>
			</div>
    	</div>
    	<div id="addpartymbrWin"></div>
    	<div id="removepartymbrWin"></div>
</body>
</html>