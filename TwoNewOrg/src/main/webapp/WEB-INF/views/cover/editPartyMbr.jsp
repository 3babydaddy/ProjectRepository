<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/index/include.jsp"%> --%>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
	<script type="text/javascript" src="<c:url value='/resources/js/cover/editPartyMbr.js?version=${jsversion}'/>"></script>
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
						<td style="text-align: left;"><input class="form-control" name="name" value="${main.name }" maxlength="20"/></td>
						<td style="text-align: right;"><font color="red">*</font>性　　别：</td>
						<td style="text-align: left;">
							<select id="gender" class="form-control" name="gender">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${genderList}">
	                               <option value="${it.code}" <c:if test="${main.gender == it.code }"> selected="selected"</c:if>>${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>出生日期：</td>
						<td style="text-align: left;">
							<input id="birthday" name="birthdayTxt" type="text" onClick="WdatePicker({maxDate:'%y-%M-%d'})" value="${main.birthdayTxt}" class="form-control" />
						</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>最高学历：</td>
						<td style="text-align: left;">
							<select id="education" class="form-control" name="education">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${educationList}">
	                               <option value="${it.code}" <c:if test="${main.education == it.code }"> selected="selected"</c:if>>${it.value}</option>
	                            </c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>是否从覆盖组织转入：</td>
						<td style="text-align: left;">
							<select id="education" class="form-control" name="isFromCoverOrg">
								<option value="" selected="selected">-请选择-</option>
								<option value="1" <c:if test="${main.isFromCoverOrg == 1 }"> selected="selected"</c:if>>是</option>
								<option value="0" <c:if test="${main.isFromCoverOrg == 0 }"> selected="selected"</c:if>>否</option>
							</select>
						</td>
					</tr>
				</table>
		   </div>
		</div>
	   <input type="hidden" name="id" id="id" value="${main.id }"/>
	   <input type="hidden" name="mainId" id="mainId" value="${main.id }"/>
	   <input type="hidden" name="coverOrgInfoId" id="coverOrgInfoId" value="${mainId }"/>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
    		  <button type="button" class="btn btn-primary" onclick="javascript:save();" id="saveBtn">保存</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('editpartymbrWin');">关闭窗口</button>
			</div>
    	</div>
    	</form>
</body>

</html>