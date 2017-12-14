<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>智能预警-预警设置列表</title>
	<script type="text/javascript" src="<c:url value='/resources/js/intelligentwarn/earlyWarnSetList.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 140px;" align="center">
		  <form id="queryForm" action="" name="queryForm" class="easyui-form" style="margin-top:10px;" method="post" >		
				<table cellpadding="5" border="0" cellspacing="0">
					<colgroup>
				 		<col width="140"/>
				 		<col width="120"/>
				 		<col width="140"/>
				 		<col width="120"/>
				 		<col width="140"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
					<td align="right">提醒内容：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="warningMsg" name="partyOrgName" size=15/></td>
					<td align="right">提醒周期：</td>
                    <td align= "left" >
						<select id="warningCycle" name="warningCycle" class="easyui-combobox" style="width:160px;" >
							<option value="">--请选择--</option>
							<c:forEach var="it" items="${reminderCycleList}">
								<option value="${it.code }">${it.value }</option>
							</c:forEach>
						</select>
	                </td>
	                <td align="right">提醒状态：</td>
                    <td align= "left" >
						<select id="warningSwitch" name="warningSwitch" class="easyui-combobox" style="width:160px;" >
							<option value="">--请选择--</option>
							<c:forEach var="it" items="${warnSwitchList}">
								<option value="${it.code }">${it.value }</option>
							</c:forEach>
						</select>
	                </td>
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
                	<td align="right" colspan="6" style="margin-right: 15px;"><a href="javascript:void(0)"
                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a></td>
                    <td align="right"></td>
                </tr>
			</table>
			</form>
		</div>
		<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
		</div>
		<div id="lookwin"></div> 
		<div id="cancelwin"></div> 
				
	</div>
</body>
</html>