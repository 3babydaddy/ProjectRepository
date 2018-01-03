<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html>
<head>
	<title>智能预警设置</title>
	<script type="text/javascript" src="<c:url value='/resources/js/warning/warningSet.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="设置查询" style="width:100%;height: 80;" align="center">
		  <form id="queryForm" action="" name="queryForm" class="easyui-form" method="post" >		
				<table cellpadding="5" border="0" cellspacing="0">
					<colgroup>
				 		<col width="160"/>
				 		<col width="120"/>
				 		<col width="120"/>
				 		<col width="120"/>
				 		<col width="140"/>
				 		<col width="120"/>
				 		<col width="140"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
					<td align="right">提醒内容：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="name" name="name" size=15/></td>
                    <td align="right">提醒周期：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="industryType" name="industryType" style="width:130px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
								<c:forEach var="it" items="${cycles}">
									<option value="${it.code}">${it.value}</option>
								</c:forEach>
                        </select>
                    </td>
                    <td align="right">提醒状态：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="level" name="level" style="width:130px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${switchs}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td   align="right" colspan="2" style="margin-right: 15px;"><a href="javascript:void(0)"
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