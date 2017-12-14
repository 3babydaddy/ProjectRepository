<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>智能预警-预警信息列表</title>
	<script type="text/javascript" src="<c:url value='/resources/js/intelligentwarn/earlyWarnInfoList.js?version=${jsversion}'/>"></script>
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
					<td align="right">开始时间：</td>
                    <td align="left"><input  type="text" class="easyui-datebox" style="width:160px;" name="startTime" /></td>
                    <td align="right">截止时间：</td>
                    <td align="left"><input  type="text" class="easyui-datebox" style="width:160px;" name="endTime" /></td>
                    <td align="right">接收部门：</td>
                    <td align= "left" >
	                    <input type="text" id="createOrgTxt" size=15  readonly="readonly" onclick="showDept();" />
	                    <input type="hidden" id="receiveOrg" name="receiveOrg" />
                    </td>
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
                    <td align="right">发送次数：</td>
                    <td align="left"><input class="easyui-textbox" type="text"  name="warningTimes" size=15/></td>
                	<td align="right">人员类别：</td>
                    <td align="left">
                    	<select class="easyui-checkbox" style="width:160px;" name="receivePeopleType" id="receivePeopleType">
							<option value="">--请选择--</option>
							<c:forEach var="it" items="${personnelCategoryList}">
								<option value="${it.code }">${it.value }</option>
							</c:forEach>
						</select>
                    </td>
                    <td align="right" colspan="6" style="margin-right: 15px;"><a href="javascript:void(0)"
                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
                    </td>
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