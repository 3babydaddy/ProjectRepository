<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ include file="/WEB-INF/views/index/include.jsp"%> --%>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/removePartyMbr.js?version=${jsversion}'/>"></script>
</head>
<body >
    	<!-- <div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
		</div> -->
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
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;">姓　　名：</td>
						<td style="text-align: left;"><input class="form-control" disabled="disabled" value="${pmbrInfo.name }"/></td>
						<td style="text-align: right;"><font color="red">*</font>去　　向：</td>
						<td style="text-align: left;">
							<input class="form-control" placeholder="例：以介绍信为转向" name="gowhere" maxlength="100"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>联系方式：</td>
						<td style="text-align: left;">
							<input class="form-control" name="contact" maxlength="30"/>
						</td>
					</tr>
				</table>
		   </div>
		</div>
		<!-- <div class="panel panel-info">
		<div class="panel-heading">
	      <h3 class="panel-title">符合条件</h3>
	   </div>
	   <div class="panel-body" align="left">
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 组织关系在非公企业的党员
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 35岁以下
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 中层管理人员
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 中高级专业技术人员
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 生产经营一线职工
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 组织关系不在非公企业的党员
			</label>
	    	<label class="checkbox-inline">
			  <input type="checkbox" id="inlineCheckbox1" value="option1"> 农村党员
			</label>
	   </div>
	   </div> -->
	   <input type="hidden" name="id" id="id" value="${id }"/>
	   <input type="hidden" name="pmbrInfoId" id="pmbrInfoId" value="${id }"/>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
    		  <button type="button" class="btn btn-primary" onclick="javascript:save();" id="saveBtn">保存</button>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('removepartymbrWin');">关闭窗口</button>
			</div>
    	</div>
    	</form>
</body>

</html>