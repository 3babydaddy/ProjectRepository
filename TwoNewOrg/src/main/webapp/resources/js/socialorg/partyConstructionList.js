
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
			url : ctx+'/socialorg/partyconstructionlist',
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
				,{field :"totalPayDues",title :"党建工作经费总额",width :"10%", align:"center",formatter:ifNullShowHeng}
				,{field :"cityFunds",title :"市区财政支出数额(元)",width :"10%", align:"center",formatter:ifNullShowHeng}
				//,{field :"districtFunds",title :"区管党费支持数额(元)",width :"10%", align:"center",formatter:ifNullShowHeng}
				//,{field :"socialOrgFunds",title :"社会组织管理经费支持数额(元)",width :"12%", align:"center",formatter:ifNullShowHeng}
				,{field :"yearTotalPayDues",title :"上年度党员缴纳党费总额",width :"12%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"annualSurveyTxt",title :"年检结果",width :"8%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"year",title :"年度",width :"8%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"creater",title :"创建人",width :"8%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"statusTxt",title :"状态",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field : 'statusAndDo',title : '操作',width : "6%" ,align:'center',
					formatter:function(value,row,index){
						if(row.status == '1' && !isQuWeiDept){
							return '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="reportHigherOption(\''+ row.id + '\',\'clickSign\',\''+ row.nature + '\')">上报</a>';
						}
		            }	
	             }
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
	var title = "编辑";
	var row = getCheckedRow();
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
	var partyOrgId = row.partyOrgId;
	var nature = row.nature;
	var url = ctx + '/socialorg/partyconstructionedit?id='+id+'&partyOrgId='+partyOrgId+
							'&partyOrgName='+partyOrgName+'&nature='+nature;
	//openWin("编辑", url,"90%","90%",function(){reloadData()});
	
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
//	//var url = ctx + '/socialorg/orgedit?id='+id;
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
	var nature = row.nature;
	var url = ctx + '/socialorg/partyconstructionlook?id='+id+'&partyOrgName='+partyOrgName+'&nature='+nature;
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
	var url = ctx + '/socialorg/partyconstructioncancel?id='+id+'&method=edit';
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
	
	var url = ctx + '/socialorg/partyconstructioncancel?id='+id+'&method=look';
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
    		url:ctx + '/socialorg/partyconstructiondelete',
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
    		url:ctx + '/socialorg/partyConstructionNocancel',
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
    		url:ctx + '/socialorg/partyConstructionCancelok',
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

function showPartyMbrList(id,method){
	var url = ctx + "/socialorg/partyMbrList?id="+id+"&method="+method;
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
			url:ctx + '/socialorg/partyConstructionSetStatus',
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
		if(row[i].status == 5 || row[i].status == 1){
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
		url:ctx + '/socialorg/partyConstructionsSetStatus',
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

/**
 * 列表页上的上报功能
 * @param id
 * @returns
 */
function reportHigherOption(id, sign, nature){
	var url = ctx + '/socialorg/partyconstructionedit?id='+id+'&clickSign='+sign+'&nature='+nature;
	utils.e.openWin('editwin','编辑',url,"80%","80%",function(){
		reloadData();
	});
}

function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}