
// 显示列表

$(document).ready(function() {
	loadData();
	
	$("#searchBtn").click (function() {
		reloadData();
	});
	
	$("#clearBtn").click(function(){
		$("#queryForm").form('clear');
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
			url : ctx+'/dbbackup/list',
			queryParams : getQueryParams(),
			title: '备份数据列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			toolbar: [{
				text:'备份',
				iconCls: 'icon-add',
				handler: function(){backupRow();}
			}],
			columns : [ [
			     {field :"id",hidden:true}
				,{field :"filename",title :"文件名称",width :"15%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"path",title :"文件路径",width :"20%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"filesize",title :"文件大小",width :"8%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"backupTime",title :"备份时间",width :"15%", align:"center",formatter:Common.TimeFormatter}
	            ,{field :"operator",title :"备份人",width :"10%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"spentTime",title :"耗时",width :"10%", align:"center",formatter:ifNullShowHeng}
	         ] ],
	      onLoadSuccess : function(data) {

//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	
	$('#gridPanel').datagrid(options);
	
}

function reloadData(){
	var params = getQueryParams();
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}



function backupRow(){

	var url = ctx + '/dbbackup/manual';
	$.messager.confirm('确认','您确认要备份数据库记录吗？',function(r){  
		if(r){
			$.ajax({
	    		url:url,
	    		type:'get',
	    		dataType:'json',
	    		success:function(result){
	    			if(result.status ==1){
	    				alert(result.msg);
	    					reloadData();
	    			}
	    			else
	    				alert(result.errorMsg);
	    		}
	    	});
		}
    	
	}); 
}
