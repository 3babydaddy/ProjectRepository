<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
	<script type="text/javascript" src="<c:url value='/resources/js/test/testEdit.js?version=${jsversion}'/>"></script>
</head>
<body >
    	<div class="panel panel-info">
		   <div class="panel-body" align="center" style="border-width:0px;" >
				<form id="editForm" action="" name="editForm" class="easyui-form" method="post" >
	               	<input type="hidden"  id="id"  name="id"  value="${users.id}">	
			    	<div data-options="region:'center'" id="gridPanel"  style="height: auto;width: 100%">
					</div>
					<table class="table table-bordered" cellpadding="0" border="0" cellspacing="0"  height="70%" width="100%" style="margin-top:2%"   >
            		      <colgroup>
					 		<col width="15%"/>
					 		<col width="18%"/>
					 	  </colgroup>
						<tr>
		                    <td align="right">原因：</td>
		                    <td align="left"><input class="easyui-textbox" id="age" name="age" size=15 data-options="multiline:true,validType:'maxLength[3]'"/></td>
	                    </tr>
			    	</table>
				</form>
			</div>
		</div>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-primary" onclick="javascript:closeWin();">关闭窗口</button>
			</div>
    	</div>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {    
    length: {    
        validator: function(value, param){    
            return value.length == param[0];    
        },    
        message: '请录入 {0} 位数字.'   
    },
    maxLength : {
    	validator:function(value,param){
    		return value.length <= param[0];
    	},
    	message:'长度不能大于 {0} 位.'
    }
});  
function loadData(){
	var options = {
			//url : ctx+'/socialorg/orglist',
			//queryParams : getQueryParams(),
			title: '附件列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
			     {field :"id",hidden:true}
				,{field :"name",title :"文件名称",width :"8%", align:"center"}
	            ,{field :"createtime",title :"上传时间",width :"7%", align:"center"}   
	            ,{field :"creator",title :"上传者",width :"7%", align:"center"}
	           ,{field : 'statusAndDo',title : '操作',width : "15%" ,align:'center',
				formatter:function(value,row,index){
					var look = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="lookRow(\''+ row.id  + '\')">查看</a>';
	                var del = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="delRow(\''+ row.id  + '\')">删除</a>';
	                
	                	return look +" / "+ del;
	            }}
	         ] ],
	      onLoadSuccess : function(data) {

//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	$('#gridPanel').datagrid(options);
	
	$('#gridPanel').datagrid('loadData',{ total:1,rows:[{
			id:'1',
		  name:'aaaaaaaaaaa',
		  createtime:'aa',
		  createtor:'aa'
	  },
	  {
			id:'2',
		  name:'aaaaaaaaaaa',
		  createtime:'aa',
		  createtor:'aa'
	  },{
			id:'3',
			  name:'aaaaaaaaaaa',
			  createtime:'aa',
			  createtor:'aa'
		  }] });
}

$(function(){
	loadData();
})
</script>
</body>

</html>