
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
			url : ctx+'/deptleader/list',
			queryParams : getQueryParams(),
			title: '部门通信人员列表',
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
				,{field :"name",title :"姓名",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"post",title :"职务",width :"7%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"postLevel",title :"职级",width :"7%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"contactTel",title :"联系方式",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"deptName",title :"所在部门",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"type",title :"类型",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"status",title :"状态",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field : 'statusAndDo',title : '修改后待审核',width : "10%" ,align:'center',
					formatter:function(value,row,index){
						if(row.updateSign == '1'){
							return '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="lookOrgInfo(\''+ row.id  + '\',\'edit\')">查阅</a>';
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
			text:'删除',
			iconCls: 'icon-remove',
			handler: function(){delRow();}
		}];
	}else{
		options.toolbar = [{
			text:'查看',
			iconCls: 'icon-search',
			handler: function(){lookRow();}
		},{
			text:'修改',
			iconCls: 'icon-edit',
			handler: function(){editRow();}
		},{
			text:'审核',
			iconCls: 'icon-ok',
			handler: function(){ auditResult();}
		}];
	}
	$('#gridPanel').datagrid(options);
	
}

function reloadData(){
	var params = getQueryParams();
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}



function addRow(){
	var url = ctx + '/deptleader/edit?id=&method=add';
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
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	if(row.status == '待审核' || row.status == '删除申请'){
		$.messager.alert("该条数据已上报；不能修改");
		return;
	}
	var id = row.id;
	
	var url = ctx + '/deptleader/edit?id='+id+'&method=edit';
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
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	var id = row.id;
	var url = ctx + '/deptleader/look?id='+id;
	//openWin("查看", url,"90%","90%");
	utils.e.openWin('lookwin','查看',url,"80%","80%",function(){
		reloadData()
	},true);
}

function delRow(){
	var row = getCheckedRow();
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	if(row.status == '待审核' || row.status == '删除申请'){
		$.messager.alert("该条数据已上报；不能修改");
		return;
	}
	var id = row.id;
	if(id==undefined){
		return;
	}
	var url = ctx + '/deptleader/del?id='+id+'&status='+row.status+'&method=DEL';
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){  
		if(r){
			$.ajax({
	    		url:url,
	    		type:'post',
	    		dataType:'json',
	    		success:function(result){
	    			if(result.status ==1){
	    				//alert(result.msg);
	    				$.messager.alert(result.msg);
	    				reloadData();
	    			}
	    			else
	    				//alert(result.errorMsg);
	    				$.messager.alert(result.msg);
	    		}
	    	});
		}
    	
	}); 
}
function auditResult(){
	var row = getCheckedRow();
	var msg = '';
	if(row == null){
		layer.alert('请选择一条记录！')
		return;
	}
	if(row.status == '正常'){
		$.messager.alert("该条数据不需要审核");
		return;
	}
	var id = row.id;
	var status = row.status;
	if(id==undefined){
		return;
	}
	if(row.status == '删除申请'){
		msg = '您确认同意删除该条记录吗？';
	}else{
		msg = '您确认审核通过该条记录吗？';
	}
	//var url = ctx + '/deptleader/audit?id='+id+'&status='+status+'&method=AUDIT';
	$.messager.defaults = { ok: '是', cancel: '否' };

	$.messager.confirm('操作提示', msg,function(r){  
		if(r){
			$.ajax({
				url:ctx + '/deptleader/audit',
	    		type:'post',
	    		data:{id:id,status:status},
	    		dataType:'json',
	    		success:function(result){
	    			if(result.status ==1){
	    				//$.messager.alert(result.msg);
	    				alert(result.msg);
	    				reloadData();
	    			}
	    			else
	    				alert(result.errorMsg);
	    				//$.messager.alert(result.msg);
	    		}
	    	});
		}else{
			$.ajax({
	    		url:ctx + '/deptleader/audit',
	    		type:'post',
	    		data:{id:id,status:'审核不通过'},
	    		dataType:'json',
	    		success:function(result){
	    			if(result.status ==1){
	    				//$.messager.alert(result.msg);
	    				alert(result.msg);
	    				reloadData();
	    			}
	    			else
	    				alert(result.errorMsg);
	    				//$.messager.alert(result.msg);
	    		}
	    	});
		}
    	
	}); 
}
/**
 * 查看修改后的数据
 * @param id
 * @returns
 */
function lookOrgInfo(deptId){
	
	var url = ctx + '/deptleader/lookupdateInfo?id='+deptId;
	//openWin("查看", url,"90%","90%");
	utils.e.openWin('lookwin','查看',url,"80%","80%",function(){
		reloadData()
	},true);
}
function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}
