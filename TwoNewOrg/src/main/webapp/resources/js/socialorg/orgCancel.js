
var method;
$(function(){
	method = $("#method").val();
	
	loadData();
	
	if(method == 'look'){
		$("#remarks").prop("disabled",true);
		$("#saveBtn").prop("disabled",true);
	}
	
})
function loadData(){
	var options = {
			url : ctx+'/socialorg/cancelFile',
			queryParams : {id:$("#mainId").val()},
			//title: '附件列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
			     {field :"id",hidden:true}
				,{field :"uploadfilename",title :"文件名称",width :"20%", align:"center"}
	            ,{field :"uploadTime",title :"上传时间",width :"20%", align:"center",formatter:Common.TimeFormatter}   
	            ,{field :"uploader",title :"上传者",width :"15%", align:"center"}
	           ,{field : 'statusAndDo',title : '操作',width : "15%" ,align:'center',
				formatter:function(value,row,index){
					var look = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="downRow(\''+ row.id  + '\')">查看</a>';
	                var del = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="delRow(\''+ row.id  + '\')">删除</a>';
	                
	                if(method == "look"){
	                	return look;
	                }
	                	return look +" / "+ del;
	            }}
	         ] ],
	      onLoadSuccess : function(data) {

//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	
	if(method == 'edit'){
		options.toolbar=[{
			text:'上传',
			iconCls: 'icon-upload-1',
			handler: function(){
				uploadFile($("#mainId").val());
			}
		}];
	}
	$('#gridPanel').datagrid(options);
	
	
} 

function reloadData(){
	var params =  {id:$("#mainId").val()};
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}
function save(){
	$.ajax({
		url:ctx + '/socialorg/orgcancel',
		type:'post',
		data:{id:$("#mainId").val(),remarks:$("#remarks").val()},
		dataType:'json',
		success:function(result){
			if(result.status ==1){
				alertx(result.msg,function(i){
					parent.utils.e.closeWin('cancelwin');
				});
			}
			else
				alertx(result.msg);
		}
	});
}
function uploadFile(modelTbId){
	posterProcess1({
		scriptData : {modelTbId :modelTbId ,model:"1"},
		script : 'fileUpload',
		fileExt:'*.*',
		callBack : function(urls) {
			//console.log(urls);
			$("#gridPanel").datagrid('reload');
		}
	});
}

function downRow(fileId){
	$("#hiddeFileIframe").attr("src",ctx + '/socialorg/downFile?id='+fileId);
	
}

function delRow(fileId){
	layer.confirm('您确定要删除该文件吗？',function(){
		$.ajax({
			url:ctx + '/socialorg/deleteFile',
			type:'post',
			data:{id:fileId},
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