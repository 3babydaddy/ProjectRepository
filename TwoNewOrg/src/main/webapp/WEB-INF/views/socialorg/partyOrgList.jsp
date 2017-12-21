<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>社会组织-党组织信息</title>
	<script type="text/javascript" src="<c:url value='/resources/js/socialorg/partyOrgList.js?version=${jsversion}'/>"></script>
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
                        <select class="easyui-combobox" id="partyOrgType" style="width:160px;" name="partyOrgType" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${partyOrgClassList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">党组织组建形式：</td>
                    <td align="left">
                         <select class="easyui-combobox" id="partyOrgForm" name="partyOrgForm" style="width:160px;"  data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${partyOrgFormList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
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
                    <td align="right">创建人：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="creater" name="creater" size=15/></td>
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
                	<td align="right">正式党员：</td>
                    <td align="left">
                         <select class="easyui-combobox" id="inOperator" name="inOperator" data-options="editable:false" style="float:left;width:60px;font-size:12px;margin-top:2px;">
                            <option value="" >请选择</option>
                            <c:forEach var="it" items="${operatorList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                        <input class="easyui-textbox" type="number" id="partyInNum" name="partyInNum" style="float:right;width:90px;" size=15/>
                    </td>
                    <td align="right">组织关系不在非公<br/>企业的党员总数：</td>
                    <td align="left">
                         <select class="easyui-combobox" id="notinOperator" name="notinOperator" data-options="editable:false" style="float:left;width:60px;font-size:12px;margin-top:2px;">
                            <option value="" >请选择</option>
                            <c:forEach var="it" items="${operatorList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                        <input class="easyui-textbox" type="number" id="partyNotInNum" name="partyNotInNum" size=15 style="float:left;width:90px;" size=15/>
                    </td>
                    <td align="right" colspan="2" style="margin-right: 15px;"><a href="javascript:void(0)"
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