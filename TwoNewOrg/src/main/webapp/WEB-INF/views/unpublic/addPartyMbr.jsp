<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/index/include.jsp"%> --%>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/addPartyMbr.js?version=${jsversion}'/>"></script>
</head>
<body >
	<form id="editForm">
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">变动信息</h3>
		   </div>
		   <div class="panel-body" align="center" >
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
						<td style="text-align: left;"><input class="form-control" name="name" maxlength="20"/></td>
						<td style="text-align: right;"><font color="red">*</font>类　　型：</td>
						<td style="text-align: left;">
							<select class="form-control" name="type">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${changeTypeList}">
								<option value="${it.code}">${it.value}</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>性　　别：</td>
						<td style="text-align: left;">
							<select id="gender" class="form-control" name="gender">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${genderList}">
	                               <option value="${it.code}">${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>出生日期：</td>
						<td style="text-align: left;">
							<input id="birthday" name="birthday" type="text" onClick="WdatePicker({maxDate:'%y-%M-%d'})" class="form-control" />
						</td>
						<td style="text-align: right;"><font color="red">*</font>最高学历：</td>
						<td style="text-align: left;">
							<select id="education" class="form-control" name="education">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${educationList}">
	                               <option value="${it.code}">${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>组织关系是否在企业：</td>
						<td style="text-align: left;">
							<select id="partymbrInUnpublicIs" class="form-control" name="partymbrInUnpublicIs">
								<option value="" selected="selected">-请选择-</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		<div class="panel panel-info">
		<div class="panel-heading">
	      <h3 class="panel-title">符合条件</h3>
	   </div>
	   <div class="panel-body" align="center">
	    	<label class="checkbox-inline">
			  <input type="checkbox" name="partymbrMiddleManagerIs" value="1"> 中层管理人员
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" name="partymbrOnMiddletechIs" value="1"> 中高级专业技术人员
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" name="partymbrFrontlineIs" value="1"> 生产经营一线职工
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" name="partymbrInVillageIs" value="1"> 农村党员
			</label>
	   </div>
	   </div>
	   
	   <div class="panel panel-info">
		<div class="panel-heading">
	      <h3 class="panel-title">其他条件</h3>
	   </div>
	   <div class="panel-body" align="left">
	   	<select id="otherCondition" name="otherCondition" class="form-control" multiple="multiple">
			<c:forEach var="it" items="${otherConditionList}">
			<option value="${it.code }">${it.value }</option>
			</c:forEach>
		</select>
	   </div>
	   
	   <input type="hidden" name="mainId" id="mainId" value="${mainId }"/>
	   <input type="hidden" name="unpublicOrgInfoId" id="unpublicOrgInfoId" value="${mainId }"/>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
    		  <button type="button" class="btn btn-primary" onclick="javascript:save();" id="saveBtn">保存</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('addpartymbrWin');">关闭窗口</button>
			</div>
    	</div>
    	</form>
</body>

</html>