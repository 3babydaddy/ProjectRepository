<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>非公有制经济组织-党组织信息</title>
	<script type="text/javascript" src="<c:url value='/resources/js/unpublic/partyOrgList.js?version=${jsversion}'/>"></script>
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
					<td align="right">党组织名称：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="partyOrgName" name="partyOrgName" size=15/></td>
                    <td align="right">党组织类别：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="partyOrgType" name="partyOrgType" style="width:160px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${partyOrgClassList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">党组织组建形式：</td>
                    <td align="left">
                         <select class="easyui-combobox" id="partyOrgForm" name="partyOrgForm" style="width:160px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${partyOrgFormList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
                	<td align="right">活动场所单位：</td>
                    <td align= "left" >
	                    <input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();" />
	                    <input type="hidden" id="belongUnit" name="belongUnit" />
                    </td>
                    
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