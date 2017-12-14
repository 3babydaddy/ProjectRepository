$(document).ready(function() {
	init();
	loadData();
	$("#searchBtn").click (function() {
		reloadData();
	});
	$("#loadBtn").click (function() {
		reloadCache();
	});
});

function init() {
	
}
function reloadData() {
	
	var params = getQueryParams();
	$('#orderInfoTbl').datagrid('options').queryParams = params;
	$("#orderInfoTbl").datagrid('reload');
}
function loadData() {
	
	var params = getQueryParams();
	$('#orderInfoTbl').datagrid({
		url : 'datadictionaryList',
		queryParams : params,
		title: '数据字典列表',
		rownumbers:true,
		fitColumns : true,
		striped : true,
		singleSelect : true,
		remoteSort: true,
		pagination:true,
		toolbar:[{
					iconCls: 'icon-edit',
					text:'增加',
					handler: function(){
						addRow();
					}
	             }
		],
		columns : [[ 
		  {field : 'dmm' ,title : '数据字典名称',width : "15%", align:'center'} 
		, {field : 'code',title : '代码',width : "10%", align:'center'} 
		, {field : 'value'    ,title : '值',width : "25%",align:'center'}
		, {field : 'pdmm'    ,title : '父代码',width : "10%",align:'center' } 
		, {field : 'orders'     ,title : '排序',width : "5%",align:'center' } 
		, {field : 'remarks'   ,title : '备注',width : "20%",align:'center' } 
		, {field : 'statusAndDo',title : '操作',width : "10%" ,align:'center',
			formatter:function(value,row,index){
				var copy = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="editRow('+row.id+',\''+"copy"+'\')">复制</a>';
				var edit = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="editRow('+row.id+',\''+"change"+'\')">修改</a>';
				var dele = '<a href="javascript:void(0)" class="easyui-linkbutton" name="editBtn" onclick="deleteRow('+row.id+')">删除</a>';
                return copy + "&nbsp;&nbsp;&nbsp;" + edit + "&nbsp;&nbsp;&nbsp;" + dele;
            }}
         ]],
      onLoadSuccess : function(data) {
    	 
      }
	});
}

function getQueryParams() {
	
	return {
		dmm:$("#search_dmm").val(),
		code:$("#search_code").val(),
		value:$("#search_value").val()
	};
}


function reloadCache(){
	$.ajax({
		url:ctx + '/systemset/reloadcache',
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.data =='重新加载成功'){
				alert(result.data);
			}
			else
				alert(result.errorMsg);
		}
	});
}

function closeWindow(){
	$("#editdialog").dialog({
		closed:true
	});
}
function saveOrUpdate(title){
	$("#editdialog").dialog({    
	    title: title,    
	    width: 850,    
	    height: 400,    
	    closed: false,    
	    cache: false,    
	    modal: true
	}); 
}
//添加行
function addRow(){
	ClearForm('editForm');
	openEditWin('新增');
}
//编辑行
function editRow(id,type){
	var str = "编辑";
	var rows = $('#orderInfoTbl').datagrid('getRows');
	$.each(rows,function(i,o){
		if(o.id==id){
			loadForm(o);
		}
	});
	if("copy"==type){
		str = "新增";
	}
	openEditWin(str,id,type);
}
//新增信息
function addInfo(){
	if(!$('#editForm').form('validate')){
		return;
	}
	if(($('#edit_level').val()!="")&&(isNaN($('#edit_level').val()))){
		alert("【级别】必须为数字类型！");
		return;
	}
	if(($('#edit_orders').val()!="")&&(isNaN($('#edit_orders').val()))){
		alert("【排序】必须为数字类型！");
		return;
	}
	$.ajax({
		url:ctx + '/systemset/addInfo',
		type:'post',
		data:$('#editForm').serialize(),
		dataType:'json',
		success:function(result){
			if(result.status =='1'){
				alert(result.msg);
				closeEditWin();
			}
			else
				alert(result.errorMsg);
		}
	});
}
//修改信息
function editInfo(id){
	if(!$('#editForm').form('validate')){
		return;
	}
	if(($('#edit_level').val()!="")&&(isNaN($('#edit_level').val()))){
		alert("【级别】必须为数字类型！");
		return;
	}
	if(($('#edit_orders').val()!="")&&(isNaN($('#edit_orders').val()))){
		alert("【排序】必须为数字类型！");
		return;
	}
	$.ajax({
		url:ctx + '/systemset/editInfo?id='+id,
		type:'post',
		data:$('#editForm').serialize(),
		dataType:'json',
		success:function(result){
			if(result.status =='1'){
				alert(result.msg);
				closeEditWin();
			}
			else
				alert(result.errorMsg);
		}
	});
}
//删除行
function deleteRow(id){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.ajax({
	    		url:ctx + '/systemset/delInfo?id='+id,
	    		type:'post',
	    		dataType:'json',
	    		success:function(result){
	    			if(result.status =='1'){
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
//打开dialog
function openEditWin(title,id,type){
	document.getElementById("edit_isLoad").checked=true;
	$('#editWin').dialog({    
	    title: title,    
	    width: 500,    
	    height: 400,    
	    closed: false,    
	    cache: false, 
	    modal: true,
	    iconCls:'icon-edit',
	    buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				if(id==null||"copy"==type){//增加
					addInfo();
				}else{//修改
					editInfo(id);
				}
			}
		},{
			text:'关闭',
			iconCls:'icon-cancel',
			handler:function(){
				closeEditWin();
			}
		}]
	}); 
}
//关闭dialog
function closeEditWin(){
	$('#editWin').dialog({'closed':true});
	reloadData();
}
//动态加载form
function loadForm(row){
	$('#editForm').form('load',{
		dmm:row.dmm,
		code:row.code,
		value:row.value,
		level:row.level,
		pdmm:row.pdmm,
		remarks:row.remarks,
		orders:row.orders
	});
}
//清空form
function ClearForm(id) {
    var objId = document.getElementById(id);
    if (objId == undefined) {
        return;
    }
    for (var i = 0; i < objId.elements.length; i++) {
    	if (objId.elements[i].type == "text") {
    		objId.elements[i].value = "";
    	}
    }
}

