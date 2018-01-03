<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>非公有制经济组织-年度党建情况</title>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/partyConstructionList.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 140px;" align="center">
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
						<td align="right"><font style="font-size:14px;">是否对党员轮训一遍：</font></td>
	                    <td style="text-align: left;">
							<select class="easyui-combobox" name="isTrainingInRotation" id="isTrainingInRotation" style="width:160px;" data-options="editable:false">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${yesNoList}">
									<option value="${it.code }" >${it.value }</option>
								</c:forEach>
							</select>
						</td>
	                    
	                    <td align="right"><font style="font-size:14px;">党员是否按时足额交纳党费：</font></td>
	                    <td align="left">
	                        <select class="easyui-combobox" name="isPartyMemberTrain" id="isPartyMemberTrain" style="width:160px;" data-options="editable:false">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${yesNoList}">
									<option value="${it.code }" >${it.value }</option>
								</c:forEach>
							</select>
	                    </td>
	                    <td align="right"><font style="font-size:14px;">是否按规定开展“三会一课”：</font></td>
	                    <td align="left">
	                        <select class="easyui-combobox" name="isDevelopListen" id="isDevelopListen" style="width:160px;" data-options="editable:false">
								<option value="">--请选择--</option>
								<c:forEach var="it" items="${yesNoList}">
									<option value="${it.code }" >${it.value }</option>
								</c:forEach>
							</select>
	                    </td>
	                </tr>
	                <tr>
	                	<td align="right">党组织名称：</td>
	                    <td align= "left" >
		                    <input type="text" id="partyOrgName" name="partyOrgName" class="easyui-textbox" size=15 />
	                    </td>
	                    <c:if test="${isQuWeiDept}">
	                		<td align="right">填报单位：</td>
		                    <td align= "left" >
			                    <input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();" />
			                    <input type="hidden" id="createOrg" name="createOrg" />
		                    </td>
	                	</c:if>
	                	<c:if test="${!isQuWeiDept}">
	                		<td align="right">填报单位：</td>
		                    <td align= "left" >
			                    <input type="text" class="easyui-textbox" readonly="readonly" style="width:160px;" value="${createOrgName}" />
		                    </td>
	                	</c:if>
						<td align="right">状态：</td>
	                    <td align="left">
	                         <select class="easyui-combobox" id="status" name="status" style="width:160px;" data-options="editable:false">
	                            <option value="" >--请选择--</option>
	                            <c:forEach var="it" items="${partyorgStatusList}">
	                               <option value="${it.code}">${it.value}</option>
	                            </c:forEach>
	                        </select>
	                    </td>
	                </tr>
	                <tr style="height:5px;"></tr>
	                <tr>
	                	<td align="right">创建人：</td>
                   		<td align="left"><input class="easyui-textbox" type="text" id="creater" name="creater" size=15/></td>
	                    <td   align="right" colspan="4" style="margin-right: 15px;"><a href="javascript:void(0)"
	                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a></td>
	                    <td align="right"></td>
	                </tr>
				</table>
			</form>
		</div>
		<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
		</div>
		<div id="editwin"></div> 
		<div id="lookwin"></div> 
		<div id="cancelwin"></div> 
				
	</div>
</body>
</html>