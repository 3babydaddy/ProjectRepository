
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
			url : ctx+'/unpublic/orglist',
			queryParams : getQueryParams(),
			title: '组织信息列表',
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
			    ,{field :"createOrgTxt",title :"填报单位",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"name",title :"企业名称",width :"8%", align:"center",formatter:ifNullShowHeng}
				,{field :"industryTypeTxt",title :"企业类型",width :"6%", align:"center",formatter:ifNullShowHeng}
				,{field :"businessTypeTxt",title :"行业类型",width :"6%", align:"center",formatter:ifNullShowHeng}
				,{field :"belocatedAddressTxt",title :"企业坐落地",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"registerAddress",title :"企业注册地",width :"7%", align:"center",formatter:ifNullShowHeng}   
//	            ,{field :"business_director_org",title :"园区级别",width :"8%", align:"center",formatter:ifNullShowHeng}
//	            ,{field :"address",title :"是否为亿元楼宇",width :"8%", align:"center",formatter:ifNullShowHeng}
//	            ,{field :"address",title :"所在园区、商务楼名称",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"contactPhone",title :"联系电话",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"businessVolume",title :"年营业收入（万元）",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"jobinTotalnum",title :"从业人员数量（名）",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"onScaleIsTxt",title :"是否规模以上企业",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"creator",title :"创建人",width :"5%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"statusTxt",title :"状态",width :"5%", align:"center",formatter:ifNullShowHeng}
	            ,{field : 'statusAndDo',title : '操作',width : "6%" ,align:'center',
					formatter:function(value,row,index){
						if(row.status == '1' && !isQuWeiDept){
							return '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="reportHigherOption(\''+ row.id  + '\',\'clickSign\')">上报</a>';
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
		},{
			text:'党员维护',
			iconCls: 'icon-large-clipart',
			handler: function(){partyMbrRow('edit');}
		},{
			text:'导出Excel',
			iconCls: 'icon-excel_report1',
			id : 'export',
			handler: function(){exportExcel();}
		}];
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
		},{
			text:'党员列表',
			iconCls: 'icon-large-clipart',
			handler: function(){partyMbrRow('look');}
		}];
		
	}
	var batchSwitch = $("#batchSwitch").val();
	/**
	 * 批量上报功能
	 * 根据数据字典配置是否开启
	 */
	if(!isQuWeiDept && batchSwitch == "1"){
		var batchReport = {
				text:'批量上报',
				iconCls: 'icon-ok',
				handler: function(){batchReportFun();}
			};
		options.toolbar.push(batchReport);
	}
	$('#gridPanel').datagrid(options);
	
}

/**
 * 导出所有数据到excel
 */


function exportExcel(){
	$('#export').linkbutton("disable");
	$('#export').linkbutton({text:'正在导出...'});
	$.ajax({
		url:ctx + '/unpublic/exportUnpublicExcel',
		data:getQueryParams(),
		type:'post',
		dataType:'json',
		success:function(result){
			if(result != '1'){
				$('#export').linkbutton({text:'导出Excel'});
				$('#export').linkbutton("enable");
				window.location.href=ctx + "/unpublic/exportUnpublicExcelFile?filePath="+encodeURIComponent(result);
			}else{
				layer.alert('导出失败');
			}
		}
	});
}

function reloadData(){
	var params = getQueryParams();
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}


function addRow(){
	var url = ctx + '/unpublic/orgedit?id=';
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
	if(row.status == 2 || row.status == 5){
		layer.alert('“'+row.name + '”已上报，不能进行修改操作！');
		return;
	}
	if(row.status > 2){
		layer.alert('“'+row.name + '”已进行了撤销操作，不能进行修改操作！');
		return;
	}
	var url = ctx + '/unpublic/orgedit?id='+id;
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
	var url = ctx + '/unpublic/orglook?id='+id;
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
		layer.alert('“'+row.name + '”不能进行撤销操作！');
		return;
	}
	var url = ctx + '/unpublic/orgcancel?id='+id+'&method=edit';
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
		layer.alert('“'+row.name + '”没有进行撤销操作，不能查看撤销原因！');
		return;
	}
	
	var url = ctx + '/unpublic/orgcancel?id='+id+'&method=look';
	//openWin("撤销原因", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','撤销原因',url,"80%","80%",function(){
		reloadData()
	});
}

function delRow(id){
	var partyOrgIds = "";
	var row = getCheckedRow();
	if(row.length < 1){
		layer.alert('请选择一条记录！')
		return;
	}
	for(var i = 0; i < row.length; i++){
		if(row[i].status == 1){
			if(partyOrgIds == ""){
				partyOrgIds = row[i].id;
			}else{
				partyOrgIds = partyOrgIds + ',' + row[i].id;
			}
		}else{
			layer.alert('“'+row[i].name + '”不能进行删除操作！');
			return;
		}
	}
	
	layer.confirm('您确认想要删除记录吗？',function(index){  
		layer.close(index);
    	$.ajax({
    		url:ctx + '/unpublic/orgdelete',
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
		layer.alert('“'+row.name + '”未进行撤销申请，不能进行取消撤销操作！');
		return;
	}
	layer.confirm('您想要取消撤销审批吗？',function(){    
    	$.ajax({
    		url:ctx + '/unpublic/nocancel',
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
			layer.alert('“'+row[i].name + '”未进行撤销申请，不能进行撤销通过操作！');
			return;
		}
	}
	
	layer.confirm('确认撤销审批通过吗？',function(){    
		$.ajax({
    		url:ctx + '/unpublic/cancelok',
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

function partyMbrRow(method){
	var row = getCheckedRow();
	if(row.length != 1){
		layer.alert('请选择一条记录！')
		return;
	}else{
		row = row[0];
	}
	var id = row.id;
	if(row.status == 3){
		layer.alert('“'+row.name + '”进行撤销中，不能进行党员维护操作！');
		return;
	}
	if(row.status == 4){
		layer.alert('“'+row.name + '”已经撤销，不能进行党员维护操作！');
		return;
	}
	showPartyMbrList(id,method);
}

function showPartyMbrList(id,method){
	var url = ctx + "/unpublic/partyMbrList?id="+id+"&method="+method;
	utils.e.openWin('editwin','党员维护',url,"80%","80%",function(){
		reloadData();
	},true);
}

function returnRow(){
	var partyOrgIds = "";
	var row = $("#gridPanel").datagrid('getSelections');
	if(row.length < 1){
		layer.alert('请选择一条记录！')
		return;
	}
	for(var i = 0; i < row.length; i++){
		if(row[i].status == 2 || row[i].status == 5){
			if(partyOrgIds == ""){
				partyOrgIds = row[i].id;
			}else{
				partyOrgIds = partyOrgIds + ',' + row[i].id;
			}
		}else{
			layer.alert('“'+row[i].name + '”未进行上报，不能进行退回操作！');
			return;
		}
	}
	layer.confirm('您确认要退回操作吗？',function(){    
		$.ajax({
			url:ctx + '/unpublic/orgSetStatus',
			data:{status:1, partyOrgIds : partyOrgIds},
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
			layer.alert('“'+row[i].name + '”未进行上报，不能进行上报通过操作！');
			return;
		}
	}

	$.ajax({
		url:ctx + '/unpublic/orgSetStatus',
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

function batchReportFun(){
	var partyOrgIds = "";
	var row = getCheckedRow();
	if(row.length <= 1){
		layer.alert('请至少选择2条记录！')
		return;
	}
	
	for(var i = 0; i < row.length; i++){
		if(row[i].status == 1){
			if(partyOrgIds == ""){
				partyOrgIds = row[i].id;
			}else{
				partyOrgIds = partyOrgIds + ',' + row[i].id;
			}
		}else{
			layer.alert('只能对临时保存的记录进行批量上报操作！');
			return;
		}
	}
	layer.confirm('请确保批量上报数据准确性，上报后无法修改，确认批量上报吗？',function(){
		$.ajax({
			url:ctx + '/unpublic/orgSetStatus',
			data:{partyOrgIds:partyOrgIds , status:5},
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

/**
 * 列表页上的上报功能
 * @param id
 * @returns
 */
function reportHigherOption(orgId, sign){
	var url = ctx + '/unpublic/orgedit?id='+orgId+'&clickSign='+sign;
	utils.e.openWin('editwin','编辑',url,"80%","80%",function(){
		reloadData();
	});
}