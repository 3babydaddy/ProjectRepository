<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>社会组织-组织信息</title>
	<script type="text/javascript" src="<c:url value='/resources/js/socialorg/orgList.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 170px;" align="center">
		  <form id="queryForm" action="" name="queryForm" class="easyui-form" method="post" >		
				<table cellpadding="5" border="0" cellspacing="0">
					<colgroup>
				 		<col width="100"/>
				 		<col width="120"/>
				 		<col width="100"/>
				 		<col width="120"/>
				 		<col width="100"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
					<td align="right">名　　称：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="name" name="name" size=15/></td>
                    <td align="right">性　　质：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="nature" name="nature" style="width:130px;" data-options="editable:false">
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
                    <td align="right">类　　别：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="category" name="category" style="width:130px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${orgCategoryList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    
                                                                   
                    
                </tr>
                <tr>
                    <td align="right">登记机构：</td>
                    <td align= "left" ><input class="easyui-textbox" type="text" id="registerOrg" name="registerOrg" size=15/></td>    
                	<!-- <td align="right">业务主管　<br>单　　位：</td>
                    <td align="left" ><input class="easyui-textbox" type="text" id="businessDirectorOrg" name="businessDirectorOrg" size=15></input></td> -->
                    <td align="right">住　　地：</td>
                    <td align="left" ><input class="easyui-textbox" type="text" id="address" name="address" size=15></input></td>
                    <c:if test="${isQuWeiDept}">
                    <td align="right">填报单位：</td>
                    <td align= "left" >
                    <input type="text" id="createOrgTxt" size=15 readonly="readonly" onclick="showDept();"/>
                    <input type="hidden" id="createOrg" name="createOrg" />
                    </td>
                    </c:if>
                </tr>
                <tr>
                	<td align="right">临时统计：</td>
                    <td align="left">
                        <select id="otherCondition" class="easyui-combobox" name="otherCondition" style="width: 100px;">
								<option value="">-请选择-</option>
								<c:forEach var="it" items="${otherConditionList}">
	                               <option value="${it.code}">${it.value}</option>
	                            </c:forEach>
							</select>
                    </td>
                    <td   align="right" colspan="6" style="margin-right: 15px;"><a href="javascript:void(0)"
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