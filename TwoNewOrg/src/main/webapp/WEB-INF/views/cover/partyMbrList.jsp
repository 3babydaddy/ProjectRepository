<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
</head>	
<body >
<div class="easyui-layout" style="width:100%;height:100%;" >
	<div data-options="region:'north'" class="easyui-panel" title="查询" style="width:100%;height: 90px;" align="center">
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
                    <td align="left">
                        <input type="text" class="easyui-textbox" name="name"/>
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
	<div id="importwin" style="text-align: center;" closed="true">
		<form class="easyui-form" action="${ctx}/cover/importPartymbr" method="post" name="editForm" id="editForm"
				enctype="multipart/form-data">
			<br>
		<input type="text" name="dataFile" id="dataFile" style="width: 250px" class="easyui-filebox"
								data-options="buttonText:'选择导入文件..'" />
			<br>
			<br>
		<a href="#" class="easyui-linkbutton" icon="icon-print" onclick="importForm()">导入</a>
		<a href="<c:url value='/resources/templet/fugai.xlsx'/>" class="easyui-linkbutton" icon="icon-save">下载模板</a>
		
		<input type="hidden" name="mainId" value="${id }"/>
		</form>
	</div>
</div>
<script type="text/javascript">
/*************************党员信息******************************/
var mainId = '${id}';
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

	params["id"] = mainId;
   return   params ; 
	
}

function reloadData(){
	
	$('#gridPanel').datagrid('options').queryParams = getQueryParams();
	
	 $('#gridPanel').datagrid('reload');
}

function loadData(){
	var options = {
			url : ctx+'/cover/partyMbrList',
			queryParams : {id:mainId},
			title: '党员列表',
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
				,{field :"name",title :"姓名",width :"10%", align:"center"}
	            ,{field :"gender",title :"性别",width :"5%", align:"center"}   
	            ,{field :"birthdayTxt",title :"出生日期",width :"10%", align:"center"}
	            ,{field :"education",title :"学历",width :"10%", align:"center"}
	            ,{field :"isFromCoverOrg",title :"是否从覆盖组织转入",width :"10%", align:"center"}   
	         ] ],
	      onLoadSuccess : function(data) {

//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	
	if('edit' == '${method}' && '${main.status}' != '2'){
		options.toolbar=[{
			text:'导入Excel',
			iconCls: 'icon-excel_report1',
			handler: function(){
				importExcel();
			}
		},{
			text:'增加',
			iconCls: 'icon-add',
			handler: function(){
				add();
			}
		},{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){
				edit();
			}
		},{
			text:'减少',
			iconCls: 'icon-remove',
			handler: function(){
				del();
			}
		}
		//,{
		//	text:'未更新说明',
		//	iconCls: 'icon-tip',
		//	handler: function(){noUpdateRel();}
		//}
		];
	}
	
	$('#gridPanel').datagrid(options);
	
	
} 

function importExcel(){
	$('#importwin').dialog({    
	    title: '党员导入',    
	    width: 400,    
	    height: 200,    
	    closed: false,    
	    cache: false,    
	    modal: true 
	});
}
function importForm(){
	var val = $('#dataFile').textbox('getValue');
	if(val==""){
		alert("请选择文件.");
		return false;
	}
	//弹遮罩
	layerIndex = top.layer.load(1, {
	    shade: [0.5,'#000'] //0.5透明度的黑色背景
	});
	$("#editForm").ajaxSubmit({  
        type: "POST",//提交类型  
        dataType: "json",//返回结果格式  
        success: function (data) {//请求成功后的函数  
        //	console.info(data);
        	top.layer.close(layerIndex); //关遮罩
        	$('#importwin').dialog('close');
	    	if(data.status == 1){
	    		alert(data.msg);
	    		reloadData();
	    	}else{
	    		alert("导入失败.");
	    	}

        },  
       // error: function (data) { alert(data.msg); },//请求失败的函数  
        async: true  
   
	}); 
}

function add(){
	var url = ctx + "/cover/addPartymbr?mainId="+mainId;
	utils.e.openWin('addpartymbrWin','增加',url,"80%","80%",function(){
		reloadData();
	},false);
}

function edit(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	if(id == undefined)
		return;
	var url = ctx + "/cover/editPartymbr?id="+id+'&mainId='+mainId;
	utils.e.openWin('editpartymbrWin','修改',url,"80%","80%",function(){
		reloadData();
	},false);
}

function del(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	
	if(id == undefined)
		return;
	
	//var url = ctx + "/unpublic/removePartymbr?id="+id;
	//utils.e.openWin('removepartymbrWin','减少',url,"80%","80%",function(){
	//	reloadData();
	//},false);
	
	$.ajax({
		url:'../cover/removePartymbr',
		type : 'post',
		data:{id : id},
		//dataType:'json',
		async: false,
		success:function(result){
			if(result.status == 1){
				alert(result.msg);
	    		reloadData();
			}else{
				alertx(result.msg);
			}
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown) {
			 debugger;
		 },
	});
}

function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}
</script>
</body>
</html>