<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>组织信息详情页</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/orgLook.js?version=${jsversion}'/>"></script>
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
						<td style="text-align: left;">${main.name }</td>
						
						<td style="text-align: right;"><font color="red">*</font>企业类型：</td>
						<td style="text-align: left;">
							${main.industryTypeTxt }
						</td>
						
						<td style="text-align: right;">国　　家：</td>
						<td style="text-align: left;">
						${main.nationlityTxt }
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>行业类型：</td>
						<td style="text-align: left;">
						${main.businessTypeTxt }
						</td>
						<td style="text-align: right;"><font color="red">*</font>联系电话：</td>
						<td style="text-align: left;">${main.contactPhone }</td>
						
						<td style="text-align: right;"><font color="red">*</font>企业坐落地：</td>
						<td style="text-align: left;">
						${main.belocatedAddressTxt }
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;">园区级别：</td>
						<td style="text-align: left;">
						${main.levelTxt }
						</td>
						
						<td style="text-align: right;">所在园区　<br>名　　称：</td>
						<td style="text-align: left;">${main.inparkName }</td>
						<td style="text-align: right;">是 否 为　<br>亿元楼宇：</td>
						<td style="text-align: left;">
						${main.millionBuildingIsTxt }
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;">所在商务　<br>楼宇名称：</td>
						<td style="text-align: left;">${main.buildingName }</td>
						<td style="text-align: right;"><font color="red">*</font>状　　态：</td>
						<td style="text-align: left;">${main.statusTxt }</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>年营业收入<br>(万元)：</td>
						<td style="text-align: left;">${main.businessVolume }</td>
						<td style="text-align: right;"><font color="red">*</font>从业人员　<br>数量(名)：</td>
						<td style="text-align: left;">${main.jobinTotalnum }</td>
						<td style="text-align: right;"><font color="red">*</font>是否规模　<br/>以上企业：</td>
						<td style="text-align: left;">${main.onScaleIsTxt }
						</td>
						
					</tr>
					<tr>
					
						<td style="text-align: right;"><font color="red">*</font>企业注册地：</td>
						<td style="text-align: left;" colspan="3">
							<div class="form-inline">
								<select class="register_address form-control" name="registerAddressLevel" disabled="disabled">
									<option value="">-请选择-</option>
									<c:forEach var="it" items="${addressLevelList}">
									<option value="${it.code }" <c:if test="${main.registerAddressLevel == it.code }"> selected="selected"</c:if>>${it.value }</option>
									</c:forEach>
								</select>
								<c:if test="${main.registerAddressLevel == 1 }">
								<span class="proclass"><span class="citys" province="${main.registerAddressProvince }" city="${ main.registerAddressCity }" area="${main.registerAddressDistrict }" town="${main.registerAddressStreet }">
								<select name="province" class="form-control" disabled="disabled"></select>
								<select name="city" class="form-control" disabled="disabled"></select>
								<select name="area" class="form-control" disabled="disabled"></select>
								<select name="town" class="form-control" disabled="disabled"></select>
								</span></span>
								</c:if>
								<c:if test="${main.registerAddressLevel == 2 }">
								<span class="proclass"><span class="citys" province="${main.registerAddressProvince }" city="${ main.registerAddressCity }" area="${main.registerAddressDistrict }" town="${main.registerAddressStreet }">
								<select name="province" class="form-control" disabled="disabled"></select>
								<select name="city" class="form-control" disabled="disabled"></select>
								<select name="area" class="form-control" disabled="disabled"></select>
								</span></span>
								</c:if>
								<c:if test="${main.registerAddressLevel == 3 }">
								<span class="proclass"><span class="citys" province="${main.registerAddressProvince }" city="${ main.registerAddressCity }" area="${main.registerAddressDistrict }" town="${main.registerAddressStreet }">
								<select name="province" class="form-control" disabled="disabled"></select>
								<select name="city" class="form-control" disabled="disabled"></select>
								</span></span>
								</c:if>
													
								<input class="form-control" disabled="disabled" placeholder="例：塘沽新港街280号" name="registerAddress" value="${main.registerAddress }"/>
							</div>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>是否存在<br>经营地：</td>
						<td style="text-align: left;">
							<select id="isHaveAddress" class="form-control" name="isHaveAddress" disabled="disabled">
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
						<select class="operate_address form-control" name="operateAddressLevel" disabled="disabled">
							<option value="">-请选择-</option>
							<c:forEach var="it" items="${addressLevelList}">
							<option value="${it.code }">${it.value }</option>
							</c:forEach>
						</select>
						<input class="form-control" placeholder="例：塘沽新港街280号" name="operateAddress" value="${main.operateAddress }" maxlength="100" disabled="disabled"/>
						</div>
						</c:if>
						<c:if test="${!empty main.id }">
						<c:forEach items="${addressList }" var="e">
						<div class="form-inline">
						<select class="operate_address form-control" name="operateAddressLevel" disabled="disabled">
							<option value="">-请选择-</option>
							<c:forEach var="it" items="${addressLevelList}">
							<option value="${it.code }" <c:if test="${e.operateAddressLevel == it.code }"> selected="selected"</c:if>>${it.value }</option>
							</c:forEach>
						</select>
						<c:if test="${e.operateAddressLevel == 1 }">
						<span class="proclass"><span class="citys" province="${e.operateAddressProvince }" city="${ e.operateAddressCity }" area="${e.operateAddressDistrict }" town="${e.operateAddressStreet }">
						<select name="province" class="form-control" disabled="disabled"></select>
						<select name="city" class="form-control" disabled="disabled"></select>
						<select name="area" class="form-control" disabled="disabled"></select>
						<select name="town" class="form-control" disabled="disabled"></select>
						</span></span>
						</c:if>
						<c:if test="${e.operateAddressLevel == 2 }">
						<span class="proclass"><span class="citys" province="${e.operateAddressProvince }" city="${ e.operateAddressCity }" area="${e.operateAddressDistrict }" town="${e.operateAddressStreet }">
						<select name="province" class="form-control" disabled="disabled"></select>
						<select name="city" class="form-control" disabled="disabled"></select>
						<select name="area" class="form-control" disabled="disabled"></select>
						</span></span>
						</c:if>
						<c:if test="${e.operateAddressLevel == 3 }">
						<span class="proclass"><span class="citys" province="${e.operateAddressProvince }" city="${ e.operateAddressCity }" area="${e.operateAddressDistrict }" town="${e.operateAddressStreet }">
						<select name="province" class="form-control" disabled="disabled"></select>
						<select name="city" class="form-control" disabled="disabled"></select>
						</span></span>
						</c:if>
						<input disabled="disabled" class="form-control" placeholder="例：塘沽新港街280号" name="operateAddress" value="${e.operateAddress }"/>
						</div>
						</c:forEach>
						</c:if>
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
						<td style="text-align: left;">${sponsor.sponsorName }</td>
						<td style="text-align: right;"><font color="red">*</font>是否党员：</td>
						<td style="text-align: left;">${sponsor.sponsorPartymemberIsTxt }
							
						</td>
						<td style="text-align: right;"><font color="red">*</font>是 否 兼 任　<br/>党组织书记：</td>
						<td style="text-align: left;">${sponsor.sponsorPartyorgSecretaryIsTxt }
							
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>是否担任区县级　<br>以上（含区县）<br>“两代表一委员”：</td>
						<td style="text-align: left;">${sponsor.sponsorTwodeputyAcommitteeIsTxt }
							
						</td>
						
						<td style="text-align: right;"></td>
						<td style="text-align: left;">
						</td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;">
						</td>
					</tr>
					<c:if test="${sponsor.sponsorTwodeputyAcommitteeIs == 1 }">
					<tr id="otherTypeTr">
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
							  <input type="checkbox" ${tmpDeputyType } disabled="disabled" name="sponsorTwodeputyAcommitteeType" <c:if test="${it.code == 10 }">id="otherCheckbox"</c:if> value="${it.code }"> ${it.value }
							</label>
							</c:forEach>
						</td>
						<td>${sponsor.sponsorTwodeputyAcommitteeTypeOther }</td>
					</tr>
					</c:if>
				</table>
		   </div>
		</div>
		
		<div class="panel panel-info">
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
						<td style="text-align: right;"><font color="red">*</font>组织关系在非公　<br>企业的党员总数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrInUnpublicNum }</td>
						<td style="text-align: right;"><font color="red">*</font>35岁以下：</td>
						<td style="text-align: left;">${pmbrCount.partymbrUnderThirtyfiveNum }</td>
						<td style="text-align: right;"><font color="red">*</font>中层管理　<br>人员人数：</td>
						<td style="text-align: left;">${pmbrCount. partymbrMiddleManagerNum}</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>中高级专业　<br>技术人员人数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrOnMiddletechNum }</td>
						<td style="text-align: right;"><font color="red">*</font>生产经营一线<br>职工人数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrFrontlineNum }</td>
						<td style="text-align: right;"><font color="red">*</font>组织关系不在非公<br>企业的党员总数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrNotinUnpublicNum }</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>农村党员数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrInVillageNum }</td>
					</tr>
				</table>
				
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
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>工　　会：</td>
						<td style="text-align: left;">
							${league.hasSociatyTxt }
						</td>
						<td style="text-align: right;"><font color="red">*</font>共 青 团：</td>
						<td style="text-align: left;">
							${league.hasYouthLeagueTxt }
						</td>
						<td style="text-align: right;"><font color="red">*</font>妇　　联：</td>
						<td style="text-align: left;">
							${league.hasWomenLeagueTxt }
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
		   	${main.otherCondition }
			</div>
		</div>
		 
		</form>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('lookwin');">关闭窗口</button>
			</div>
    	</div>
</body>
</html>