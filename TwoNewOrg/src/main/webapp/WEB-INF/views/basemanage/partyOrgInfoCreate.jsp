<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<title>党组织信息新增</title>
	<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
    <script type="text/javascript" src="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.js'/>"></script>    
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-select/bootstrap-select.css'/>">    
	<script type="text/javascript" src="<c:url value='/resources/plugins/jquery.citys.js'/>"></script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/ztree/zTreeStyle/zTreeStyle.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.core-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/ztree/jquery.ztree.excheck-3.5.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/basemanage/partyOrgInfoCreate.js?time=new Date()'/>"></script>
	<style type="text/css">
		a{
			width:80px;
		}
	</style>
</head>
<body>
	<form id="sysFrm" class="form-horizontal"  method="post" >
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">基本信息</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 		<col width="90" />
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font style="font-size: 14px;">党组织名称：</font></td>
		    			<td align="left">
		    				<input class="form-control"  type="text" id="name" name="name"></input>
		    			</td>
		    			<td style="text-align: right;"><font style="font-size: 14px;">党组织类型：</font></td>
		    			<td align="left">
	                        <select class="form-control" id="partyOrgType" name="partyOrgType">
	                            <option value="" >--请选择--</option>
	                            <c:forEach var="it" items="${partyOrgClassList}">
									<option value="${it.code }">${it.value }</option>
								</c:forEach>
	                        </select>
	                    </td>
		    			<td style="text-align: right;"><font style="font-size: 14px;">党组织成立时间：</font></td>
		    			<td align="left">
		    				<input type="text" onClick="WdatePicker()" class="form-control" id="partySetUpTime" name="partySetUpTime" />
		    			</td>
					</tr>
					<tr>
						<td style="text-align: right;"><font style="font-size: 14px;">本届党组织<br/>开始时间：</font></td>
		    			<td align="left">
		    				<input  type="text" onClick="WdatePicker()" class="form-control" id="partyStartTime" name="partyStartTime" />
		    			</td>
		    			<td style="text-align: right;"><font style="font-size: 14px;">上级部门：</font></td>
		    			<td style="text-align: left;">
	    					<input  type="text" id="higherDepart" class="form-control" value="" readonly value=""  onclick="showMenu(); return false;"></input>
							<div id="parentPartyOrg" class="parentPartyOrg" style="display:none; position: absolute;background-color: #fff;border:1px solid #95B8E7;">
								<ul id="parentDepartMent" class="ztree" style="margin-top:0; width:160px;"></ul>
							</div>
	    			</td>
					</tr>
				</table>
				<input type="hidden" id="higherDepartHid" value="${partyOrgInfo.parentPartyOrg}">
	    		<div style="margin-top:20px; align:center;">
					<a href="javascript:void(0)" class="btn btn-primary" id="gobackBtn">后退</a>
			    	<a href="javascript:void(0)" class="btn btn-primary" id="submitBtn">提交</a>
				</div>
		   </div>
		</div>
	</form>
	
	<%-- <%@ include file="/WEB-INF/views/resourceSetting/setting.jsp"%> --%>
</body>
</html>