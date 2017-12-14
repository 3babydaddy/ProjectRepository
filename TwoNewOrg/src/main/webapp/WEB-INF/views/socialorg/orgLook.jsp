<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>组织信息详情页</title>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
</head>
<body >
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
						<td style="text-align: right;">名　　称：</td>
						<td style="text-align: left;">${main.name }</td>
						<td style="text-align: right;">状　　态：</td>
						<td style="text-align: left;">${main.statusTxt }</td>
						<td style="text-align: right;">性　　质：</td>
						<td style="text-align: left;">${main.natureTxt }</td>
					</tr>
					<tr>
						<td style="text-align: right;">类　　别：</td>
						<td style="text-align: left;">${main.categoryTxt }</td>
						<td style="text-align: right;">登记机构：</td>
						<td style="text-align: left;">${main.registerOrg }</td>
						<td style="text-align: right;">业　　务　<br/>主管单位：</td>
						<td style="text-align: left;">${main.businessDirectorOrg }</td>
					</tr>
					<tr>
						<td style="text-align: right;">住　　地：</td>
						<td style="text-align: left;" colspan="3">${main.address }</td>
						
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
						<td style="text-align: right;">总　　数：</td>
						<td style="text-align: left;">${count.jobinTotalnum }</td>
						<td style="text-align: right;">专职人员：</td>
						<td style="text-align: left;">${count.jobinMajorNum }</td>
						<td style="text-align: right;">兼职人员：</td>
						<td style="text-align: left;">${count.jobinPluralityNum }</td>
					</tr>
					<tr>
						<td style="text-align: right;">中共党员：</td>
						<td style="text-align: left;">${count.jobinPartymemberNum }</td>
						<td style="text-align: right;">社会团体单位　<br>会员数量(个)：</td>
						<td style="text-align: left;">${count.jobinSocialteamGroupmemberNum }</td>
						<td style="text-align: right;">社会团体个人　<br>会员数量(人)：</td>
						<td style="text-align: left;" >${count.jobinSocialteamIndividualmemberNum }</td>
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
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;">姓　　名：</td>
						<td style="text-align: left;">${charge.chargeName }</td>
						<td style="text-align: right;">是否党员：</td>
						<td style="text-align: left;">${charge.chargePartymemberIsTxt }</td>
						<td style="text-align: right;">是 否 兼 任　<br>党组织书记：</td>
						<td style="text-align: left;">${charge.chargePartyorgSecretaryIsTxt }</td>
					</tr>
					<tr>
						<td style="text-align: right;">是否担任区县级 <br>以上（含区县） <br>“两代表一委员”：</td>
						<td style="text-align: left;">${charge.chargeTwodeputyAcommitteeIsTxt }</td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
						<td style="text-align: right;"></td>
						<td style="text-align: left;"></td>
					</tr>
					<c:if test="${charge.chargeTwodeputyAcommitteeIs == 1 }">
					<tr id="otherTypeTr" style="display: ;">
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
							  <input type="checkbox" ${tmpDeputyType } disabled="disabled" name="chargeTwodeputyAcommitteeType" <c:if test="${it.code == 10 }">id="otherCheckbox"</c:if> value="${it.code }"> ${it.value }
							</label>
							</c:forEach>
						</td>
						<td>${charge.chargeTwodeputyAcommitteeTypeOther }</td>
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
				 	</colgroup>
					<tr>
						<td style="text-align: right;">纳入社会组织党组织<br>管理的党员总数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrInSocielorgNum }</td>
						<td style="text-align: right;">组织关系在社会组织<br>党组织的党员数：</td>
						<td style="text-align: left;">${pmbrCount.partymbrGroupInSocielorgNum }</td>
						<td style="text-align: right;">35岁以下：</td>
						<td style="text-align: left;">${pmbrCount.partymbrUnderThirtyfiveNum }</td>
					</tr>
					<tr>
						<td style="text-align: right;">大学及以上学历：</td>
						<td style="text-align: left;">${pmbrCount.partymbrOnCollegeNum }</td>
						<td style="text-align: right;">高中及以下学历：</td>
						<td style="text-align: left;">${pmbrCount.partymbrUnderHighschoolNum }</td>
						
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
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('lookwin');">关闭窗口</button>
			</div>
    	</div>
</body>
</html>