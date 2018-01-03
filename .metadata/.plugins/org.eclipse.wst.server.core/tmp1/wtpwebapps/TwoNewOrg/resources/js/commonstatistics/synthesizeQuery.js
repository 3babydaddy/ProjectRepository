
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
			url : ctx+'/commonstatistics/synthesizequery',
			queryParams : getQueryParams(),
			title: '综合查询列表',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
			     {field :"id",hidden:true}
			     ,{field :"createOrgTxt",title :"填报单位",width :"8%", align:"center",formatter:ifNullShowHeng}
			     ,{field :"name",title :"组织名称",width :"8%", align:"center",formatter:ifNullShowHeng}
			     ,{field :"partyNum",title :"组织名称",width :"8%", align:"center",formatter:ifNullShowHeng}
			     ,{field :"industryTypeTxt",title :"企业类型",width :"8%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"businessTypeTxt",title :"行业类型",width :"8%", align:"center",formatter:ifNullShowHeng}
			     ,{field :"partyOrgName",title :"党组织名称",width :"8%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"partyOrgForm",title :"党组织组建形式",width :"8%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"partyOrgType",title :"党组织类别",width :"8%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"changeTime",title :"换届时间",width :"6%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"partyOrgTel",title :"党组织联系电话",width :"10%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"subjectionPartyName",title :"隶属党组织名称",width :"10%", align:"center",formatter:ifNullShowHeng}
	             ,{field :"secretaryName",title :"书记名称",width :"8%", align:"center",formatter:ifNullShowHeng}   
	             ,{field :"secretarySource",title :"书记来源",width :"8%", align:"center",formatter:ifNullShowHeng}
	             ,{field :"deputySecretaryType",title :"党务副书记及委员类型",width :"12%", align:"center",formatter:ifNullShowHeng}
	             ,{field :"deputySecretaryName",title :"党务副书记及委员名称",width :"12%", align:"center",formatter:ifNullShowHeng}
	             ,{field :"threeYearsMember",title :"近三年新发展党员数",width :"10%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"threeYearsUnqualifieds",title :"近三年处置不合格党员数",width :"10%", align:"center",formatter:ifNullShowHeng}
				 ,{field :"isTrainingInRotation",title :"是否对党员轮训一遍",width :"10%", align:"center",formatter:ifNullShowHeng}
					
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

function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}

function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}