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
			//url : ctx+'/warning/setList',
			queryParams : getQueryParams(),
			title: '预警信息设置',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
//			     {field :"id",hidden:true}
//			    ,
			    {field :"ck",title :"选择",checkbox:true}
			    ,{field :"warningTitle",title :"预警名称",width :"10%", align:"center",formatter:ifNullShowHeng}
				,{field :"warningContent",title :"预警描述",width :"20%", align:"center",formatter:ifNullShowHeng}
				,{field :"warningMsg",title :"提醒信息配置",width :"30%", align:"left",formatter:ifNullShowHeng}
				,{field :"warningCycle",title :"提醒周期",width :"5%", align:"center",formatter:ifNullShowHeng}
				,{field :"warningStatus",title :"预警状态",width :"5%", align:"center",formatter:ifNullShowHeng}
	            ,{field :"warningPeoples",title :"涉及接受提醒人",width :"10%", align:"center",formatter:ifNullShowHeng}   
	            ,{field :"registerAddress",title :"操作",width :"20%", align:"center",formatter:ifNullShowHeng}   
	         ] ],
	      onLoadSuccess : function(data) {
//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	
	if(isQuWeiDept){
		options.toolbar = [{
			text:'手工发布',
			iconCls: 'icon-add',
			handler: function(){addRow();}
		},{
			text:'开启',
			iconCls: 'icon-ok',
			handler: function(){lookRow();}
		},{
			text:'编辑',
			iconCls: 'icon-edit',
			handler: function(){editRow();}
		},{
			text:'停用',
			iconCls: 'icon-cancel',
			handler: function(){cancelRow();}
		}];
	}
	$('#gridPanel').datagrid(options);
	
	//模拟数据
	var str = '{"total":2,'+
		'"rows":[{"warningTitle":"党组织建立预警","warningContent":"组织未及时建立党组织，给出提醒，规则：1~2人，联合党支部；3+ 党支部；7+ 支部委员会；50+ 党总支；100+ 党委","warningMsg":"请相关负责人抓紧时间建立党组织","warningCycle":"每天","warningStatus":"开启","warningPeoples":"工作人员、主管领导、一把手"},'+
				'{"warningTitle":"党组织换届预警","warningContent":"党组织未及时换届给出提醒，规则：三年换届；","warningMsg":"请相关负责人抓紧时间换届","warningCycle":"每年","warningStatus":"开启","warningPeoples":"工作人员、主管领导、一把手"}]}';   
	debugger;
	var data = $.parseJSON(str);    
	$('#gridPanel').datagrid('loadData', data); 
}

function reloadData() {
	var params = getQueryParams();
	$('#gridPanel').datagrid('options').queryParams = params;
	$('#gridPanel').datagrid('reload');
}






















