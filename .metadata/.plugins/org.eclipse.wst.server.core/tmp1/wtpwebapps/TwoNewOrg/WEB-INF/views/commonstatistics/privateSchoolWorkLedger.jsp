<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>综合统计-民办学校党建工作台账</title>
	<script type="text/javascript" src="<c:url value='/resources/js/commonstatistics/privateSchoolWorkLedger.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 140px;">
		  <form id="queryForm" action="" name="queryForm" class="easyui-form" style="margin-top:10px;" method="post" >		
				<table cellpadding="5" border="0" cellspacing="0">
					<colgroup>
				 		<col width="160"/>
				 		<col width="120"/>
				 		<col width="160"/>
				 		<col width="120"/>
				 		<col width="160"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td align="right">填报单位：</td>
	                    <td align= "left" >
		                    <input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();" />
		                    <input type="hidden" id="createOrg" name="createOrg" />
	                    </td>
	                    <td align="right">填报时间开始时间：</td>
	                    <td align= "left">
	                    	<input type="text" class="easyui-datebox" name="startTime" />
	                    </td>
	                    <td align="right">填报时间开始时间：</td>
	                    <td align="left">
	                    	<input type="text" class="easyui-datebox" name="endTime" />
	                    </td>
	                </tr>
	                <tr style="height:5px;"></tr>
	                <tr>
	                    <td align="right" colspan="6" style="margin-right: 15px;">
	                    	<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
	                    	<a href="../test/export" class="easyui-linkbutton" icon="icon-excel_report1" id="searchBtn">导出Excel</a>
	                    </td>
	                    <td align="right"></td>
	                </tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%"></div>
	</div>
</body>
</html>