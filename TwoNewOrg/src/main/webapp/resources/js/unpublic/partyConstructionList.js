
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
			url : ctx+'/unpublic/partyconstructionlist',
			queryParams : getQueryParams(),
			title: '年度党建情况列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : false,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
			     {field :"id",hidden:true}
			    ,{field :"ck",title :"选择",checkbox:true}
				,{field :"partyOrgName",title :"党组织名称",width :"6%", align:"center",formatter:ifNullShowHeng}
				,{field :"threeYearsMember",title :"近三年新发展党员数",width :"10%", align:"center",formatter:ifNullShowHeng}
				,{field :"threeYearsUnqualifieds",title :"近三年处置不合格党员数",width :"10%", align:"center",formatter:ifNullShowHeng}
				,{field :"isTrainingInRotationTxt",title :"是否对党员轮训一遍",width :"10%", align:"center",formatter:ifNullShowHeng}
				,{field :"isPartyMemberTrainTxt",title :"党员是否按时足额主动交纳党费",width :"12%", align:"center",formatter:ifNullShowHeng}
				,{field :"isDevelopListenTxt",title :"是否按规定开展“三会一课”",width :"12%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"isDevelopDiscussionsTxt",title :"是否按规定每年开展民主评议党员",width :"12%", align:"center",formatter:ifNullShowHeng}   
	           // ,{field :"isChangeEveryyearTxt",title :"党组织是否按规定进行换届",width :"12%", align:"center",formatter:ifNullShowHeng}
	            //,{field :"isRectifyPartyTxt",title :"是否完成整顿相对后进基层党组织",width :"12%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"partyAvgTime",title :"党员(平均学时)",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"year",title :"年度",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"creater",title :"创建人",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"statusTxt",title :"状态",width :"8%", align:"center",formatter:ifNullShowHeng}
	         ] ],
	      onLoadSuccess : function(data) {

//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	if(!isQuWeiDept){
		options.toolbar = [{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){addRow();}
		},{
			text:'查看',
			iconCls: 'icon-search',
			handler: function(){lookRow();}
		},
//		{
//			text:'修改',
//			iconCls: 'icon-edit',
//			handler: function(){editRow();}
//		},
		{
			text:'撤销申请',
			iconCls: 'icon-undo',
			handler: function(){cancelRow();}
		},{
			text:'取消撤销',
			iconCls: 'icon-back',
			handler: function(){nocancel();}
		},{
			text:'撤销原因',
			iconCls: 'icon-help',
			handler: function(){cancelReasonRow();}
		},{
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){delRow();}
		}
//		,{
//			text:'党员维护',
//			iconCls: 'icon-large-clipart',
//			handler: function(){partyMbrRow('edit');}
//		}
		];
	}else{
		options.toolbar = [{
			text:'查看',
			iconCls: 'icon-search',
			handler: function(){lookRow();}
		},{
			text:'上报通过',
			iconCls: 'icon-ok',
			handler: function(){reportHigherRow();}
		},{
			text:'撤销通过',
			iconCls: 'icon-ok',
			handler: function(){cancelok();}
		},{
			text:'退回',
			iconCls: 'icon-undo',
			handler: function(){returnRow();}
		},{
			text:'撤销原因',
			iconCls: 'icon-help',
			handler: function(){cancelReasonRow();}
		}
//		,{
//			text:'党员列表',
//			iconCls: 'icon-large-clipart',
//			handler: function(){partyMbrRow('look');}
//		}
		];
	}
	$('#gridPanel').datagrid(options);
	
}

function reloadData(){
	var params = getQueryParams();
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}


function addRow(){
	var row = getCheckedRow();
	var title = "编辑";
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	//影响后台用工具类判断
	if(id == null){
		id = "";
		title = "新增";
	}
	if(row.status == 2 || row.status == 5){
		layer.alert('“'+row.partyOrgName + '”已上报，不能进行修改操作！');
		return;
	}
	if(row.status > 2){
		layer.alert('“'+row.partyOrgName + '”已进行了撤销操作，不能进行修改操作！');
		return;
	}
	//获取党组织的信息
	var partyOrgName = row.partyOrgName;
	var partyOrgId = row.unpublicPartyOrgId;
	var url = ctx + '/unpublic/partyconstructionedit?id='+id+'&partyOrgId='+partyOrgId+'&partyOrgName='+partyOrgName;
	
	utils.e.openWin('editwin', title,url,"80%","80%",function(){
		reloadData();
	},true);
}
/**
 * 编辑
 * @param id
 * @returns
 */
//function editRow(){
//	var row = getCheckedRow();
//	if(row == null){
//		layer.alert('请选择一条记录！')
//		return;
//	}
//	var id = row.id;
//	if(row.status == 2 || row.status == 5){
//		layer.alert('“'+row.name + '”已上报，不能进行修改操作！');
//		return;
//	}
//	if(row.status > 2){
//		layer.alert('“'+row.name + '”已进行了撤销操作，不能进行修改操作！');
//		return;
//	}
//	//var url = ctx + '/unpublic/orgedit?id='+id;
//	//openWin("编辑", url,"90%","90%",function(){reloadData()});
//	
//	utils.e.openWin('editwin','编辑',url,"80%","80%",function(){
//		reloadData();
//	},true);
//}
/**
 * 查看
 * @param id
 * @returns
 */
function lookRow(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	if(row.id == null || row.id == undefined){
		layer.alert('“'+row.partyOrgName + '”未建立党建，不能进行查看操作！');
		return;
	}
	var partyOrgName = row.partyOrgName;
	var url = ctx + '/unpublic/partyconstructionlook?id='+id+'&partyOrgName='+partyOrgName;
	//openWin("查看", url,"90%","90%");
	utils.e.openWin('lookwin','查看',url,"80%","80%",function(){
		reloadData()
	},true);
}

function cancelRow(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	if(row.status > 2 || row.status < 2){
		layer.alert('“'+row.partyOrgName + '”不能进行撤销操作！');
		return;
	}
	var url = ctx + '/unpublic/partyconstructioncancel?id='+id+'&method=edit';
	//openWin("撤销", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','撤销',url,"80%","80%",function(){
		reloadData()
	});
}



function cancelReasonRow(id){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	
	if(row.status != 3 && row.status != 4){//撤销中和已撤销时
		layer.alert('“'+row.partyOrgName + '”没有进行撤销操作，不能查看撤销原因！');
		return;
	}
	
	var url = ctx + '/unpublic/partyconstructioncancel?id='+id+'&method=look';
	//openWin("撤销原因", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','撤销原因',url,"80%","80%",function(){
		reloadData()
	});
}

function delRow(id){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	if(row.id == null || row.id == undefined){
		layer.alert('“'+row.partyOrgName + '”未建立党建，不能进行删除操作！');
		return;
	}
	if(row.status != 1){
		layer.alert('“'+row.partyOrgName + '”不能进行删除操作！');
		return;
	}
	layer.confirm('您确认想要删除记录吗？',function(index){  
		layer.close(index);
    	$.ajax({
    		url:ctx + '/unpublic/partyconstructiondelete',
    		data:{id:id},
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
    				layer.alert(result.errorMsg);
    		}
    	});
	}); 
}


function nocancel(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	if(row.status != 3){
		layer.alert('“'+row.partyOrgName + '”未进行撤销申请，不能进行取消撤销操作！');
		return;
	}
	layer.confirm('您想要取消撤销审批吗？',function(){    
    	$.ajax({
    		url:ctx + '/unpublic/partyConstructionNocancel',
    		data:{id:id},
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
	});
}

function  cancelok(){
	var partyOrgIds = "";
	var row = $("#gridPanel").datagrid('getSelections');
	if(row.length < 1){
		layer.alert('请选择一条记录！')
		return;
	}
	
	for(var i = 0; i < row.length; i++){
		if(row[i].status == 3){
			if(partyOrgIds == ""){
				partyOrgIds = row[i].id;
			}else{
				partyOrgIds = partyOrgIds + ',' + row[i].id;
			}
		}else{
			layer.alert('“'+row[i].partyOrgName + '”未进行撤销申请，不能进行撤销通过操作！');
			return;
		}
	}
	
	layer.confirm('确认撤销审批通过吗？',function(){    
		$.ajax({
    		url:ctx + '/unpublic/partyConstructionCancelok',
    		data:{partyOrgIds:partyOrgIds},
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
	});
}

//function partyMbrRow(method){
//	var row = getCheckedRow();
//	if(row == null){
//		layer.alert('请选择一条记录！')
//		return;
//	}
//	var id = row.id;
//	if(row.status == 3){
//		layer.alert('“'+row.name + '”进行撤销中，不能进行党员维护操作！');
//		return;
//	}
//	if(row.status == 4){
//		layer.alert('“'+row.name + '”已经撤销，不能进行党员维护操作！');
//		return;
//	}
//	showPartyMbrList(id,method);
//}

function showPartyMbrList(id,method){
	var url = ctx + "/unpublic/partyMbrList?id="+id+"&method="+method;
	utils.e.openWin('editwin','党员维护',url,"80%","80%",function(){
		reloadData();
	},true);
}

function returnRow(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	if(row.status != 2 && row.status != 5){
		layer.alert('“'+row.partyOrgName + '”未进行上报，不能进行退回操作！');
		return;
	}
	layer.confirm('您确认要退回操作吗？',function(){    
		$.ajax({
			url:ctx + '/unpublic/partyConstructionSetStatus',
			data:{id:id , status:1},
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
	});
}

function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}
function reportHigherRow(){
	var partyOrgIds = "";
	var row = $("#gridPanel").datagrid('getSelections');
	if(row.length < 1){
		layer.alert('请选择一条记录！')
		return;
	}
	for(var i = 0; i < row.length; i++){
		if(row[i].status == 5){
			if(partyOrgIds == ""){
				partyOrgIds = row[i].id;
			}else{
				partyOrgIds = partyOrgIds + ',' + row[i].id;
			}
		}else{
			layer.alert('“'+row[i].partyOrgName + '”未进行上报，不能进行上报通过操作！');
			return;
		}
	}
	$.ajax({
		url:ctx + '/unpublic/partyConstructionsSetStatus',
		data:{partyOrgIds:partyOrgIds , status:2},
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
function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}