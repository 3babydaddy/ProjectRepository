
// 显示列表

$(document).ready(function() {
	
	loadData();
	
	$("#searchBtn").click (function() {
		reloadData();
	});
});
function getQueryParams() {
	var params = {};
	var fields =$('#queryForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 

   return   params ; 
	
}


function loadData(){
	var options = {
			url : ctx+'/intelligentwarn/earlywarnsetlist',
			queryParams : getQueryParams(),
			title: '预警设置列表',
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
			    ,{field :"warningTitle",title :"预警名称",width :"8%", align:"center",formatter:ifNullShowHeng}
			    ,{field :"warningRulesContent",title :"预警描述",width :"20%", align:"center",formatter:ifNullShowHeng}
			    ,{field :"warningMsg",title :"提醒信息配置",width :"20%", align:"center",formatter:ifNullShowHeng}
				,{field :"warningCycle",title :"提醒周期",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"warningSwitch",title :"预警状态",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"warningPeoplesTypes",title :"涉及接受提醒人类型",width :"12%", align:"center",formatter:ifNullShowHeng}
				,{field :"expectStartTime",title :"开始时间",width :"12%", align:"center",formatter:ifNullShowHeng}
	         ] ],
	      onLoadSuccess : function(data) {

//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	
	if(isQuWeiDept){
		options.toolbar = [{
			text:'手动发布',
			iconCls: 'icon-add',
			handler: function(){manualRelease();}
		},{
			text:'开启',
			iconCls: 'icon-ok',
			handler: function(){updateStatus('0');}
		},{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){editRow();}
		},{
			text:'停用',
			iconCls: 'icon-cancel',
			handler: function(){updateStatus('1');}
		}];
	}
	$('#gridPanel').datagrid(options);
}

function reloadData(){
	var params = getQueryParams();
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}

function updateStatus(flag){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	
	$.ajax({
		//url:ctx + '/intelligentwarn/updateStatus',
		data:{id:id , status:flag},
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.status ==1){
				layer.alert(result.msg,function(i){
					reloadData();
					layer.close(i); // 关闭提示框
				});
			}
			else
				layer.alert(result.msg);
		}
	});
}

function editRow(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	
	//var url = ctx + '/intelligentwarn/intelligentwarnedit?id='+id;
	//openWin("编辑", url,"90%","90%",function(){reloadData()});
	
	utils.e.openWin('editwin','编辑',url,"80%","80%",function(){
		reloadData();
	},true);
}

function manualRelease(){
	var url = ctx + '/intelligentwarn/earlywarnsetedit?id=';
	//openWin("编辑", url,"90%","90%",function(){reloadData()});
	
	utils.e.openWin('editwin','新增',url,"80%","80%",function(){
		reloadData();
	},true);
}

function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}

function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}