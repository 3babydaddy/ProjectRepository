
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
			url : ctx+'/test/listWithSingle',
			queryParams : getQueryParams(),
			title: '用户列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			toolbar: [{
				text:'导出',
				iconCls: 'icon-excel_report1',
				handler: function(){exportData();}
			}],
			columns : [ [   
				 {field :"name",title :"姓名",width :"8%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"genderStr",title :"性别",width :"7%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"age",title :"年龄",width :"7%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"tel",title :"手机号",width :"10%", align:"center",formatter:ifNullShowHeng}
	           ,{field : 'statusAndDo',title : '状态/操作',width : "10%" ,align:'center',
				formatter:function(value,row,index){
					var look = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="lookRow(\''+ row.id  + '\')">查看</a>';
	                var edit =  '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="editRow(\''+ row.id  + '\')">修改</a>';
	                var del = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="delRow(\''+ row.id  + '\')">删除</a>';
	                return look + " / " + edit + " / " + del;
	            }}
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


function exportData(){
	layer.confirm('确认导出用户列表信息吗?', {icon: 3, title:'提示'}, function(index){
		document.queryForm.action = ctx +"/test/export" ;
	    document.queryForm.method = "post";   
	    document.queryForm.submit();
		layer.close(index);
	});
}

/**
 * 编辑
 * @param id
 * @returns
 */
function editRow(id){
	openWin("编辑", ctx + '/test/edit?id='+id,"50%","60%",function(){reloadData()});
}
/**
 * 查看
 * @param id
 * @returns
 */
function lookRow(id){
	openWin("查看", ctx + '/test/look?id='+id,"50%","60%");
}

function delRow(id){
	if(id==undefined){
		return;
	}
	layer.confirm('您确认想要删除记录吗？',function(index){  
		layer.close(index);
    	$.ajax({
    		url:ctx + '/test/delete?id='+id,
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
