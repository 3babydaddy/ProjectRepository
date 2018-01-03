<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<%--<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
</head>	
<body >
<div class="easyui-layout" style="width:100%;height:100%;" >
	<div data-options="region:'north'" class="easyui-panel" title="查询" style="width:100%;height: 90px;" align="center">
	  <form id="queryForm" action="" name="queryForm" class="easyui-form" method="post" style="margin-top:10px;">		
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
					<td align="right">企业名称：</td>
                    <td align="left">
                        <input type="text" class="easyui-textbox" id="name" name="name"/>
                    </td>
                    <td   align="right" colspan="3" style="margin-right: 15px;"><a href="javascript:void(0)"
                        class="easyui-linkbutton" icon="icon-search" id="searchBtn">查询</a>
                    </td>
				</tr>
			</table>
		</form>
	</div>
	<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
	</div>
	<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
   		<div class="btn-group">
   		  <button type="button" class="btn btn-primary" onclick="javascript:closePop();" id="saveBtn">确认</button>
   		  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('showOrgInfoPop');;">关闭</button>
		</div>
   	</div>
</div>
<script type="text/javascript">
/*************************党员信息******************************/

$(function(){
	loadData();
	
	$("#searchBtn").click(function(){
		reloadData();
	});
	
});

function getQueryParams() {
	var params = {};
	var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 

	//params["id"] = mainId;
   return   params ; 
	
}

function reloadData(){
	
	$('#gridPanel').datagrid('options').queryParams = getQueryParams();
	
	 $('#gridPanel').datagrid('reload');
}

function loadData(){
	
	var options = {
			url : ctx+'/unpublic/showOrgInfoPop',
			queryParams : getQueryParams(),
			title: '组织列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
			     {field :"id",hidden:true}
			    ,{field :"ck",title :"选择",checkbox:true}
				,{field :"name",title :"企业名称",width :"10%", align:"center"}
	            ,{field :"createOrgTxt",title :"填报单位",width :"15%", align:"center"}
	            ,{field :"creator",title :"创建人",width :"8%", align:"center"}
	         ] ],
	      onLoadSuccess : function(data) {
	    	  //if(data.total != 0){
	    		//  $('#gridPanel').datagrid('selectAll');
	    	//  }
//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	
	$('#gridPanel').datagrid(options);
} 

function closePop(){
	var row = getCheckedRow();
	
	if(row != null){
		window.parent.$('#partyOrgDiv').append('<button class="btn btn-xs" value="'+row.id+'" name="btn_org_name">'+row.name+'<span class="glyphicon glyphicon-remove"></span></button>')
	}
	parent.utils.e.closeWin('showOrgInfoPop');
}

function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}
</script>
</body>
</html>