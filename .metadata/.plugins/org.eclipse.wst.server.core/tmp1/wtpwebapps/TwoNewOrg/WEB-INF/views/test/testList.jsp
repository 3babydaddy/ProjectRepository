<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>测试页面</title>
	<script type="text/javascript" src="<c:url value='/resources/js/test/testList.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 200px;" align="center">
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
					<td align="right">姓名：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="name" name="name" size=15/></td>
                    <td align="right">性别：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="gender" name="gender" style="width:130px;" data-options="editable:false">
                                <option value="" >--请选择--</option>
                                <c:forEach var="it" items="${genderList}">
                                   <option value="${it.code}">${it.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    
                    <td align="right">年龄：</td>
                    <td align= "left" ><input class="easyui-textbox" type="text" id="age" name="age" size=15/></td>    
                    <td align="right">手机号：</td>
                    <td align="left" ><input class="easyui-textbox" type="text" id="tel" name="tel" size=15></input></td>                                               
                    
                </tr>
                <tr>
                    
                    <td   align="center" colspan="4" style="margin-right: 15px;"><a href="javascript:void(0)"
                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a></td>
                    <td align="right"></td>
                </tr>
			</table>
			</form>
		</div>
		<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
		</div>
	</div>
</body>
</html>