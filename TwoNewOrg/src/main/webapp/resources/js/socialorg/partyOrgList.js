
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
			url : ctx+'/socialorg/partyorglist',
			queryParams : getQueryParams(),
			title: '党组织信息列表',
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
				,{field :"orgName",title :"企业名称",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"partyInNum",title :"正式党员",width :"6%", align:"center",formatter:ifNullShowHeng}
				,{field :"partyNotInNum",title :"组织关系不在社会企业的党员总数",width :"6%", align:"center",formatter:ifNullShowHeng}
				,{field :"partyOrgName",title :"党组织名称",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"partyOrgFormTxt",title :"党组织组建形式",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"partyOrgTypeTxt",title :"党组织类别",width :"6%", align:"center",formatter:ifNullShowHeng}
				,{field :"partyOrgTel",title :"党组织联系电话",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"subjectionPartyName",title :"隶属党组织名称",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"secretaryName",title :"书记名称",width :"6%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"secretarySourceTxt",title :"书记来源",width :"6%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"partyOrgTimeTxt",title :"党组织成立时间",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"creater",title :"创建人",width :"5%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"statusTxt",title :"状态",width :"6%", align:"center",formatter:ifNullShowHeng}
	            ,{field : 'statusAndDo',title : '操作',width : "6%" ,align:'center',
					formatter:function(value,row,index){
						if(row.status == '1' && !isQuWeiDept){
							return '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="reportHigherOption(\''+ row.id  + '\',\'clickSign\',\''+ row.nature + '\')">上报</a>';
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
			text:'新增',
			iconCls: 'icon-add',
			handler: function(){addRow();}
		},{
			text:'查看',
			iconCls: 'icon-search',
			handler: function(){lookRow();}
		},{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){editRow();}
		},{
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
	var orgIdStr = "";
	var orgNameStr = "";
	var nature = "";
	var row = getCheckedRow();
	if(row.length > 0){
		nature = row[0].nature;
	}
	for(var i=0; i< row.length; i++){
		if(row[i].status > 0){
			layer.alert('“'+row[i].partyOrgName + '”已新增，不能进行新增操作！');
			return;
		}
	}
	for(var i = 0; i < row.length; i++){
		if(orgIdStr == "" && orgNameStr==""){
			orgIdStr = row[i].orgId;
			orgNameStr = row[i].orgName;
		}else{
			orgIdStr = orgIdStr + "," + row[i].orgId;
			orgNameStr = orgNameStr + "," + row[i].orgName;
		}
	}
	var url = ctx + '/socialorg/partyorgedit?id=&orgIds='+orgIdStr+'&orgNames='+orgNameStr+'&nature='+nature;
	//openWin("编辑", url,"90%","90%",function(){reloadData()});
	
	utils.e.openWin('editwin','新增',url,"80%","80%",function(){
		reloadData();
	},true);
}
/**
 * 编辑
 * @param id
 * @returns
 */
function editRow(){
	var row = getCheckedRow();
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(id == null){
		layer.alert('“'+row.orgName + '”未进行过党组织信息录入操作！');
		return;
	}
	if(row.status == 2 || row.status == 5){
		layer.alert('“'+row.partyOrgName + '”已上报，不能进行修改操作！');
		return;
	}
	if(row.status > 2){
		layer.alert('“'+row.partyOrgName + '”已进行了撤销操作，不能进行修改操作！');
		return;
	}
	var nature = row.nature; 
	var url = ctx + '/socialorg/partyorgedit?id='+id+'&nature='+nature;
	//openWin("编辑", url,"90%","90%",function(){reloadData()});
	
	utils.e.openWin('editwin','编辑',url,"80%","80%",function(){
		reloadData();
	},true);
}
/**
 * 查看
 * @param id
 * @returns
 */
function lookRow(){
	var row = getCheckedRow();
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(id == null){
		layer.alert('“'+row.orgName + '”未进行过新增操作！');
		return;
	}
	var nature = row.nature; 
	var url = ctx + '/socialorg/partyOrglook?id='+id+'&nature='+nature;
	//openWin("查看", url,"90%","90%");
	utils.e.openWin('lookwin','查看',url,"80%","80%",function(){
		reloadData()
	},true);
}

function cancelRow(){
	var row = getCheckedRow();
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(row.status > 2 || row.status < 2){
		layer.alert('“'+row.orgName + '”不能进行撤销操作！');
		return;
	}
	var url = ctx + '/socialorg/partyorgcancel?id='+id+'&method=edit';
	//openWin("撤销", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','撤销',url,"80%","80%",function(){
		reloadData()
	});
}



function cancelReasonRow(id){
	var row = getCheckedRow();
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	
	if(row.status != 3 && row.status != 4){//撤销中和已撤销时
		layer.alert('“'+row.orgName + '”没有进行撤销操作，不能查看撤销原因！');
		return;
	}
	
	var url = ctx + '/socialorg/partyorgcancel?id='+id+'&method=look';
	//openWin("撤销原因", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','撤销原因',url,"80%","80%",function(){
		reloadData()
	});
}

function delRow(id){
	var row = getCheckedRow();
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(id==undefined){
		layer.alert('“'+row.orgName + '”未建立党组织,不能进行删除操作！');
		return;
	}
	if(row.status != 1){
		layer.alert('“'+row.orgName + '”不能进行删除操作！');
		return;
	}
	layer.confirm('您确认想要删除记录吗？',function(index){  
		layer.close(index);
    	$.ajax({
    		url:ctx + '/socialorg/partyorgdelete',
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
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(row.status != 3){
		layer.alert('“'+row.orgName + '”未进行撤销申请，不能进行取消撤销操作！');
		return;
	}
	layer.confirm('您想要取消撤销审批吗？',function(){    
    	$.ajax({
    		url:ctx + '/socialorg/partyNocancel',
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
	var row = getCheckedRow();
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
			layer.alert('“'+row[i].orgName + '”未进行撤销申请，不能进行撤销通过操作！');
			return;
		}
	}
	
	layer.confirm('确认撤销审批通过吗？',function(){    
		$.ajax({
    		url:ctx + '/socialorg/partyCancelok',
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
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(row.status != 2 && row.status != 5){
		layer.alert('“'+row.orgName + '”未进行上报，不能进行退回操作！');
		return;
	}
	layer.confirm('您确认要退回操作吗？',function(){    
		$.ajax({
			url:ctx + '/socialorg/partyOrgSetStatus',
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
	var row = $("#gridPanel").datagrid('getSelections');
	return row;
}
function reportHigherRow(){
	var partyOrgIds = "";
	var row = getCheckedRow();
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
			layer.alert('“'+row[i].orgName + '”未进行上报，不能进行上报通过操作！');
			return;
		}
	}
	
	$.ajax({
		url:ctx + '/socialorg/partyOrgsSetStatus',
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
function reportHigherOption(partyOrgId, sign, nature){
	var url = ctx + '/socialorg/partyorgedit?id='+partyOrgId+'&clickSign='+sign+'&nature='+nature;
	utils.e.openWin('editwin','编辑',url,"80%","80%",function(){
		reloadData();
	});
}

function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}