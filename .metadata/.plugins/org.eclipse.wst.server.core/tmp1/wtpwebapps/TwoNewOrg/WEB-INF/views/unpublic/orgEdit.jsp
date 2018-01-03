<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>组织信息详情页</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
    <script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.js'/>"></script>    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.css'/>">    
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/orgEdit.js?version=${jsversion}'/>"></script>
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
						<td style="text-align: right;"><font color="red">*</font>企业名称：</td>
						<td style="text-align: left;"><input class="form-control" name="name" value="${main.name }" maxlength="50"/></td>
						<td style="text-align: right;"><font color="red">*</font>企业类型：</td>
						<td style="text-align: left;">
							<select class="form-control" name="industryType" id="industryType">
								<option value="" >--请选择--</option>
								<c:forEach var="it" items="${enterpriseTypeList}">
								<optgroup label="${it.key }">
									<c:forEach items="${it.value }" var="cit">
										<option value="${cit.code }" <c:if test="${main.industryType == cit.code }"> selected="selected"</c:if>>${cit.value }</option>
									</c:forEach>
								</optgroup>
								</c:forEach>
								
							</select>
						</td>
						
						<td style="text-align: right;">国　　家：</td>
						<td style="text-align: left;">
							<select id="nationality" name="nationalityTxt" multiple class="selectpicker show-tick form-control" data-live-search="true" title="请选择">
								<!-- <option value="" selected>--请选择--</option> -->
								<c:forEach var="it" items="${notionalityList}">
								<c:set var="tmpNationality" value=""/>
								<c:forEach items="${nationalitys }" var="t">
								<c:if test="${it.code == t}">
								<c:set var="tmpNationality" value='selected="selected"' />
								</c:if>
								</c:forEach>
								<option value="${it.code }" ${ tmpNationality}>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;">行业类型：</td>
						<td style="text-align: left;">
							<select id="hangyeleixing" name="businessTypeTxt" class="form-control" multiple="multiple">
								<c:forEach var="it" items="${businessTypeList}">
								<c:set var="tmpBusinessType" value=""/>
								<c:forEach items="${businessTypes }" var="t">
								<c:if test="${it.code == t}">
								<c:set var="tmpBusinessType" value='selected="selected"' />
								</c:if>
								</c:forEach>
								<option value="${it.code }" ${tmpBusinessType}>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>联系电话：</td>
						<td style="text-align: left;"><input class="form-control" name="contactPhone" value="${main.contactPhone }" maxlength="14"/></td>
						<td style="text-align: right;"><font color="red">*</font>企业坐落地：</td>
						<td style="text-align: left;">
							<select id="belocated_address" class="form-control" name="belocatedAddress">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${enterpriseBelocatedAddressList}">
								<option value="${it.code }" <c:if test="${main.belocatedAddress == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;" class="level"><font color="red">*</font>园区级别：</td>
						<td style="text-align: left;" class="level">
							<select id="level" class="form-control" name="level">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${zoneLevelList}">
								<option value="${it.code }" <c:if test="${main.level == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						
						<td style="text-align: right;" class="inpark_name"><font color="red">*</font>所在园区　<br>名　　称：</td>
						<td style="text-align: left;" class="inpark_name"><input class="form-control" id="inpark_name" name="inparkName" value="${main.inparkName }" maxlength="100"/></td>
						
						<td style="text-align: right;" class="million_building_is"><font color="red">*</font>是 否 为　<br>亿元楼宇：</td>
						<td style="text-align: left;" class="million_building_is">	
							<select class="form-control" id="million_building_is" name="millionBuildingIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
								<option value="${it.code }" <c:if test="${main.millionBuildingIs == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;" class="building_name"><font color="red">*</font>所在商务　<br/>楼宇名称：</td>
						<td style="text-align: left;" class="building_name"><input class="form-control" id="building_name" name="buildingName" value="${main.buildingName }" maxlength="100"/></td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>年营业收入<br>(万元)：</td>
						<td style="text-align: left;"><input class="form-control" name="businessVolume" value="${main.businessVolume }" maxlength="10"/></td>
						<td style="text-align: right;"><font color="red">*</font>从业人员　<br>数量(名)：</td>
						<td style="text-align: left;"><input class="form-control" name="jobinTotalnum" value="${main.jobinTotalnum }" maxlength="10"/></td>
						<td style="text-align: right;"><font color="red">*</font>是否规模　<br>以上企业：</td>
						<td style="text-align: left;">
							<select class="form-control" name="onScaleIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
								<option value="${it.code }" <c:if test="${main.onScaleIs == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>企业注册地：</td>
						<td style="text-align: left;" colspan="3">
							 <div class="form-inline">
								<select class="register_address form-control" name="registerAddressLevel">
									<option value="">-请选择-</option>
									<c:forEach var="it" items="${addressLevelList}">
									<option value="${it.code }" <c:if test="${main.registerAddressLevel == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
								<c:if test="${main.registerAddressLevel == 1 }">
								<span class="proclass"><span class="citys" province="${main.registerAddressProvince }" city="${ main.registerAddressCity }" area="${main.registerAddressDistrict }" town="${main.registerAddressStreet }">
								<select name="province" class="form-control"></select>
								<select name="city" class="form-control"></select>
								<select name="area" class="form-control"></select>
								<select name="town" class="form-control"></select>
								</span></span>
								</c:if>
								<c:if test="${main.registerAddressLevel == 2 }">
								<span class="proclass"><span class="citys" province="${main.registerAddressProvince }" city="${ main.registerAddressCity }" area="${main.registerAddressDistrict }" town="${main.registerAddressStreet }">
								<select name="province" class="form-control"></select>
								<select name="city" class="form-control"></select>
								<select name="area" class="form-control"></select>
								</span></span>
								</c:if>
								<c:if test="${main.registerAddressLevel == 3 }">
								<span class="proclass"><span class="citys" province="${main.registerAddressProvince }" city="${ main.registerAddressCity }" area="${main.registerAddressDistrict }" town="${main.registerAddressStreet }">
								<select name="province" class="form-control"></select>
								<select name="city" class="form-control"></select>
								</span></span>
								</c:if>
													
								<input class="form-control" placeholder="例：塘沽新港街280号" name="registerAddress" value="${main.registerAddress }" maxlength="100"/>
							</div>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>是否存在<br>经营地：</td>
						<td style="text-align: left;">
							<select id="isHaveAddress" class="form-control" name="isHaveAddress">
								<c:forEach var="it" items="${yesNoList}">
								<option value="${it.code }" <c:if test="${main.isHaveAddress == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr id="operateAddressTd">
						<td style="text-align: right;"><font color="red">*</font>企业经营地：</td>
						<td style="text-align: left;" colspan="3">
							<c:if test="${empty main.id or empty addressList}">
							<div class="form-inline">
							<select class="operate_address form-control" name="operateAddressLevel">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${addressLevelList}">
								<option value="${it.code }">${it.value }</option>
								</c:forEach>
							</select>
							<input class="form-control" placeholder="例：塘沽新港街280号" name="operateAddress" value="${main.operateAddress }" maxlength="100"/>
							</div>
							</c:if>
							<c:if test="${!empty main.id }">
							<c:forEach items="${addressList }" var="e">
							<div class="form-inline">
							<select class="operate_address form-control" name="operateAddressLevel">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${addressLevelList}">
								<option value="${it.code }" <c:if test="${e.operateAddressLevel == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
							<c:if test="${e.operateAddressLevel == 1 }">
							<span class="proclass"><span class="citys" province="${e.operateAddressProvince }" city="${ e.operateAddressCity }" area="${e.operateAddressDistrict }" town="${e.operateAddressStreet }">
							<select name="province" class="form-control"></select>
							<select name="city" class="form-control"></select>
							<select name="area" class="form-control"></select>
							<select name="town" class="form-control"></select>
							</span></span>
							</c:if>
							<c:if test="${e.operateAddressLevel == 2 }">
							<span class="proclass"><span class="citys" province="${e.operateAddressProvince }" city="${ e.operateAddressCity }" area="${e.operateAddressDistrict }" town="${e.operateAddressStreet }">
							<select name="province" class="form-control"></select>
							<select name="city" class="form-control"></select>
							<select name="area" class="form-control"></select>
							</span></span>
							</c:if>
							<c:if test="${e.operateAddressLevel == 3 }">
							<span class="proclass"><span class="citys" province="${e.operateAddressProvince }" city="${ e.operateAddressCity }" area="${e.operateAddressDistrict }" town="${e.operateAddressStreet }">
							<select name="province" class="form-control"></select>
							<select name="city" class="form-control"></select>
							</span></span>
							</c:if>
							<input class="form-control" placeholder="例：塘沽新港街280号" name="operateAddress" value="${e.operateAddress }"/>
							</div>
							</c:forEach>
							</c:if>
							<button type="button" id="add_operate_address" class="btn btn-primary btn-sm glyphicon glyphicon-plus"></button>
						</td>
					</tr>
				</table>
		   </div>
		</div>
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">企业主要出资人情况</h3>
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
						<td style="text-align: left;"><input class="form-control" name="sponsorName" value="${sponsor.sponsorName }" maxlength="20"/></td>
						<td style="text-align: right;"><font color="red">*</font>是否党员：</td>
						<td style="text-align: left;">
							<select class="form-control" name="sponsorPartymemberIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
								<option value="${it.code }" <c:if test="${sponsor.sponsorPartymemberIs == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>是 否 兼 任　<br>党组织书记：</td>
						<td style="text-align: left;">
							<select class="form-control" name="sponsorPartyorgSecretaryIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
								<option value="${it.code }" <c:if test="${sponsor.sponsorPartyorgSecretaryIs == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>是否担任区县级　<br>以上(含区县)　<br/>“两代表一委员”：</td>
						<td style="text-align: left;">
							<select id="quxianji" class="form-control" name="sponsorTwodeputyAcommitteeIs">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${yesNoList}">
								<option value="${it.code }" <c:if test="${sponsor.sponsorTwodeputyAcommitteeIs == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
					</tr>
					<c:if test="${sponsor.sponsorTwodeputyAcommitteeIs == 1 }">
					<tr id="otherTypeTr">
					</c:if>
					<c:if test="${sponsor.sponsorTwodeputyAcommitteeIs != 1 }">
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
							  <input type="checkbox" ${tmpDeputyType } name="sponsorTwodeputyAcommitteeTypeTxt" <c:if test="${it.code == 10 }">id="otherCheckbox"</c:if> value="${it.code }"> ${it.value }
							</label>
							</c:forEach>
						</td>
						<td><input class="form-control" id="otherTxt" disabled="disabled" name="sponsorTwodeputyAcommitteeTypeOther" value="${sponsor.sponsorTwodeputyAcommitteeTypeOther }" maxlength="100"/></td>
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
						<td style="text-align: right;">组织关系在非公　<br/>企业的党员总数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrInUnpublicNum" value="${pmbrCount.partymbrInUnpublicNum }"/></td>
						<td style="text-align: right;">35岁以下：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrUnderThirtyfiveNum" value="${pmbrCount.partymbrUnderThirtyfiveNum }"/></td>
						<td style="text-align: right;">中层管理&nbsp;&nbsp;&nbsp;<br/>人员人数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrMiddleManagerNum" value="${pmbrCount.partymbrMiddleManagerNum }"/></td>
						
					</tr>
					<tr>
						<td style="text-align: right;">中高级专业技术<br/>人员人数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrOnMiddletechNum" value="${pmbrCount.partymbrOnMiddletechNum }"/></td>
						<td style="text-align: right;">生产经营一线<br/>职工人数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrFrontlineNum" value="${pmbrCount.partymbrFrontlineNum }"/></td>
						<td style="text-align: right;">组织关系不在非公<br/>企业的党员总数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrNotinUnpublicNum" value="${pmbrCount.partymbrNotinUnpublicNum }"/></td>
						
					</tr>
					<tr>
						<td style="text-align: right;">农村党员数：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" name="partymbrInVillageNum" value="${pmbrCount.partymbrInVillageNum }"/></td>
					</tr>
				</table>
				
				<!-- <button type="button" class="btn btn-primary" onclick="javascript:addPartymbr();">增加</button>
				<button type="button" class="btn btn-primary" onclick="javascript:removePartymbr();">减少</button> -->
		   </div>
		</div>
		<!-- 群团部门设置情况 -->
		<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">群团部门设置情况</h3>
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
						<td style="text-align: right;"><font color="red">*</font>工&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;会：</td>
						<td style="text-align: left;">
							<select class="form-control" name="hasSociaty">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${hasNotList}">
								<option value="${it.code }" <c:if test="${league.hasSociaty == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>共&nbsp;青&nbsp;团：</td>
						<td style="text-align: left;">
							<select class="form-control" name="hasYouthLeague">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${hasNotList}">
								<option value="${it.code }" <c:if test="${league.hasYouthLeague == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>妇&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;联：</td>
						<td style="text-align: left;">
							<select class="form-control" name="hasWomenLeague">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${hasNotList}">
								<option value="${it.code }" <c:if test="${league.hasWomenLeague == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
				
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
		<input type="hidden" id="leagueId" name="leagueId" value="${league.id }"/>
		<input type="hidden" id="pmbrCountId" name="pmbrCountId" value="${pmbrCount.id }"/>
		<input type="hidden" id="sponsorId" name="sponsorId" value="${sponsor.id }"/>
		<input type="hidden" id="flag" name="flag" value="${flag}"/>
		<input type="hidden" id="clickSign" name="clickSign" value="${clickSign}"/>
		
		<input type="hidden" id="sponsorTwodeputyAcommitteeType" name="sponsorTwodeputyAcommitteeType"/>
		<input type="hidden" name="nationality"/>
		<input type="hidden" name="businessType"/>

		<input type="hidden" name="registerAddressProvince"/>
		<input type="hidden" name="registerAddressCity"/>
		<input type="hidden" name="registerAddressDistrict"/>
		<input type="hidden" name="registerAddressStreet"/>
		
		<input type="hidden" name="operateAddressBigTxt"/>
		
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
    	
	<%-- <div class="form-inline hiddenCitySelect" style="display: none;">
	<select class="operate_address form-control" name="operateAddressLevel">
		<option value="0">-请选择-</option>
		<c:forEach var="it" items="${addressLevelList}">
		<option value="${it.code }" <c:if test="${main.operateAddressLevel == it.code }"> selected="selected"</c:if>>${it.value }</option>
		</c:forEach>
	</select>
	<input class="form-control" placeholder="例：塘沽新港街280号" name="operateAddress" value="${main.operateAddress }"/>
	</div> --%>
</body>
</html>