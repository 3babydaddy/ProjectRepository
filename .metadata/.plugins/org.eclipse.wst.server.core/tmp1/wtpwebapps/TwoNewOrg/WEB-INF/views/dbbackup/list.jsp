<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<html >
<head>
	<title>数据库备份信息</title>
	<script type="text/javascript" src="<c:url value='/resources/js/dbbackup/list.js?version=${jsversion}'/>"></script>
</head>
<body>
	<div class="easyui-layout" style="width:97%;height:95.5%;" >
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
					<td align="right">开始时间：</td>
                    <td align="left"><input class="easyui-datetimebox" type="text" id="startTime" name="startTime" size=15/></td>
					<td align="right">结束时间：</td>
                    <td align="left"><input class="easyui-datetimebox" type="text" id="endTime" name="endTime" size=15/></td>
                    <td   align="right" colspan="2" style="margin-right: 15px;">
                    	<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
                    	<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-clear" id="clearBtn">清空</a>
                    </td>
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