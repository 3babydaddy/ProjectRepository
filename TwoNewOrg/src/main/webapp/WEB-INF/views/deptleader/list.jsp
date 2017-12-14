<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<html >
<head>
	<title>部门主要领导信息</title>
	<script type="text/javascript" src="<c:url value='/resources/js/deptleader/list.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:100%;height:100%;" >
		<div data-options="region:'north'" class="easyui-panel" title="任务查询" style="width:100%;height: 120px;" align="center">
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
					<td align="right">姓　　名：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="name" name="name" size=15/></td>
					<td align="right">职　　务：</td>
                    <td align="left"><input class="easyui-textbox" type="text" id="post" name="post" size=15/></td>
                    <td align="right">职　　级：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="postLevel" name="postLevel" style="width:130px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${postLevelList}">
                               <option value="${it.code}">${it.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    
                </tr>
                <tr>
                    <td align="right">联系方式：</td>
                    <td align= "left" ><input class="easyui-textbox" type="text" id="contactTel" name="contactTel" size=15/></td>    
                    <td align="right">类　　型：</td>
                    <td align="left">
                        <select class="easyui-combobox" id="type" name="type" style="width:130px;" data-options="editable:false">
                            <option value="" >--请选择--</option>
                            <c:forEach var="it" items="${leaderLevelList}">
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
		<div id="deptwin"></div>		
	</div>
</body>
</html>