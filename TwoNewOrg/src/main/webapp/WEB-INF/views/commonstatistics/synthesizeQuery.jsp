<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>综合统计-综合查询列表</title>
	<script type="text/javascript" src="<c:url value='/resources/js/commonstatistics/synthesizeQuery.js?version=${jsversion}'/>"></script>
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
					<td align="right">组织名称：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="name" name="name" size=15/></td>
                    <td align="right">组织类别：</td>
                    <td align="left">
                        <select id="otherCondition" class="easyui-combobox" name="otherCondition" style="width: 160px;">
								<option value="">-请选择-</option>
								<option value="0">非公有制经济组织</option>
								<option value="1">社会组织</option>
						</select>
                    </td>
                    <td align="right">企业类型：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="industryType" name="industryType" style="width:160px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
								<c:forEach var="it" items="${enterpriseTypeList}">
								<optgroup label="${it.key }">
									<c:forEach items="${it.value }" var="cit">
										<option value="${cit.code }">${cit.value }</option>
									</c:forEach>
								</optgroup>
								</c:forEach>
                        </select>
                    </td>
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
                	<td align="right">性　　质：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="nature" name="nature" style="width:160px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${orgNatureList}">
							<optgroup label="${it.key }">
								<c:forEach items="${it.value }" var="cit">
									<option value="${cit.code }">${cit.value }</option>
								</c:forEach>
							</optgroup>
							</c:forEach>
                        </select>
                    </td>
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
                </tr>
                <tr style="height:5px;"></tr>
                <tr>
                	<td align="right">填报单位：</td>
                    <td align= "left" >
	                    <input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();"/>
	                    <input type="hidden" id="createOrg" name="createOrg" />
                    </td>
                    <td align="right">换届时间：</td>
                    <td align="left">
                        <input  type="text" class="easyui-datebox" style="width:160px;" />
                    </td>
                    <c:if test="${isQuWeiDept}">
	                    <td align="right">填报单位：</td>
	                    <td align= "left" >
	                    <input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();"/>
	                    <input type="hidden" id="createOrg" name="createOrg" />
	                    </td>
	                    </tr>
	                    <tr style="height:5px;"></tr>
	                    <tr>
	                    <td align="right" colspan="6" style="margin-right: 15px;"><a href="javascript:void(0)"
	                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
	                    </td>
                    </c:if>
                    <c:if test="${!isQuWeiDept}">
                    	<td align="right" colspan="2" style="margin-right: 15px;"><a href="javascript:void(0)"
	                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
	                    </td>
                    </c:if>
                 </tr>
			</table>
			</form>
		</div>
		<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
		</div>
	</div>
</body>
</html>