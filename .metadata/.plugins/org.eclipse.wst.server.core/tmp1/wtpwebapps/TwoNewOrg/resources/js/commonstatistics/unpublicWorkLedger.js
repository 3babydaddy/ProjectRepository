
// 显示列表

$(document).ready(function() {

	loadData();

	$("#searchBtn").click(function() {
		reloadData();
	});

	/**
	 * export Excel btn click
	 */
	$("#exportExcelBtn").click(function() {
		$('#exportExcelBtn').linkbutton('disable');//设置变灰按钮  
		window.location.href=ctx + "/file/exportUnpublicReport";
		setTimeout("$('#exportExcelBtn').linkbutton('enable')",5000); //设置三秒后提交按钮 显示  
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
			url : ctx+'/commonstatistics/unpublicworkledger', 
			queryParams : getQueryParams(),
			title: '',
			rownumbers:true,
			fitColumns : true,
			striped : true,
			singleSelect : true,
			remoteSort: true,
			pagination:true,
			nowrap:false,
			columns : [ [
				{field :"createOrg",title:"单位",width :"8%", align:"center", "rowspan":3},      
				{title:"企业座落地",width :"8%", align:"center", "colspan":3},      
				{field :"level",title:"园区级别(国家级、市级、区级及以下)",width :"8%", align:"center", "rowspan":3},     
				{field :"millionBuildingIs",title:"是否为亿元楼宇(是、否)",width :"8%", align:"center", "rowspan":3},     
				{field :"inparkBuildName",title:"所在园区、商务楼宇名称",width :"8%", align:"center", "rowspan":3}, 
				{title:"企业基本情况",width :"8%", align:"center", "colspan":10},      
				{title:"企业出资人情况",width :"8%", align:"center", "colspan":4},      
				{title:"党组织设置情况",width :"8%", align:"center", "colspan":13},      
				{title:"党组织书记情况",width :"8%", align:"center", "colspan":5},      
				{title:"党务工作者情况",width :"8%", align:"center", "colspan":2},      
				{title:"党务队伍建设情况",width :"8%", align:"center", "colspan":11},      
				{title:"群团部门设置情况",width :"8%", align:"center", "colspan":3},      
				{title:"基础保障情况",width :"8%", align:"center", "colspan":3},      
				{title:"落实相关制度情况",width :"8%", align:"center", "colspan":4}    
	         ],[
	            //企业座落地
	            {field :"park",title:"园区",width :"8%", align:"center", "rowspan":2},
	            {field :"building",title:"楼宇",width :"8%", align:"center", "rowspan":2},
	            {field :"adOther",title:"其他",width :"8%", align:"center", "rowspan":2},
	            //企业基本情况
	            {field :"name",title:"企业名称",width :"8%", align:"center", "rowspan":2},
	            {field :"registerAddress",title:"企业注册地",width :"8%", align:"center", "rowspan":2},
	            {field :"belocatedAddress",title:"企业经营地",width :"8%", align:"center", "rowspan":2},
	            {field :"contactPhone",title:"联系电话",width :"8%", align:"center", "rowspan":2},
	            {title:"企业类型",width :"8%", align:"center", "colspan":3},
	            {field :"businessVolume",title:"年营业收入(万元)",width :"8%", align:"center", "rowspan":2},
	            {field :"jobinTotalnum",title:"从业人员数量(名)",width :"8%", align:"center", "rowspan":2},
	            {field :"onScaleIs",title:"是否规模以上企业(是、否)",width :"8%", align:"center", "rowspan":2},
	            //企业出资人情况
	            {field :"sponsorName",title:"企业出资人姓名",width :"8%", align:"center", "rowspan":2},
	            {field :"sponsorPartymemberIs",title:"是否是党员",width :"8%", align:"center", "rowspan":2},
	            {field :"sponsorPartyorgSecretaryIs",title:"是否兼任党组织书记",width :"8%", align:"center", "rowspan":2},
	            {field :"sponsorTwodeputyAcommitteeIs",title:"是否担任区县级以上(含区县)“两代表一委员”",width :"8%", align:"center", "rowspan":2},
	            //党组织设置情况
	            {field :"isSetUpPartyOrg",title:"是否建立党组织",width :"8%", align:"center", "rowspan":2},
	            {field :"partyOrgTimeTxt",title:"党组织成立时间",width :"8%", align:"center", "rowspan":2},
	            {title:"党组织组建形式",width :"8%", align:"center", "colspan":3},
	            {title:"党组织类别",width :"8%", align:"center", "colspan":3},
	            {title:"未建立党组织原因",width :"8%", align:"center", "colspan":4},
	            {field :"isInstructor",title:"未建党组织的是否选派党建工作指导员、联络员",width :"8%", align:"center", "rowspan":2},
	            //党组织书记情况
	            {field :"secretaryName",title:"党组织书记姓名",width :"8%", align:"center", "rowspan":2},
	            {title:"来源",width :"8%", align:"center", "colspan":4},
	            //党务工作者情况
	            {field :"deputySecretaryNum",title:"企业党务工作者人数",width :"8%", align:"center", "rowspan":2},
	            {field :"deputySecretaryFullNum",title:"专职人数",width :"8%", align:"center", "rowspan":2},
	            //党务队伍建设情况
	            {field :"partymbrInUnpublicNum",title:"组织关系在非公企业的党员总数",width :"8%", align:"center", "rowspan":2},
	            {field :"partymbrUnderThirtyfiveNum",title:"35岁以下人数",width :"8%", align:"center", "rowspan":2},
	            {field :"partymbrMiddleManagerNum",title:"中层管理人员人数",width :"8%", align:"center", "rowspan":2},
	            {field :"partymbrOnMiddletechNum",title:"中高级专业技术人员人数",width :"8%", align:"center", "rowspan":2},
	            {field :"partymbrFrontlineNum",title:"生产经营一线职工人数",width :"8%", align:"center", "rowspan":2},
	            {field :"partymbrNotinUnpublicNum",title:"组织关系不在非公企业的党员总数",width :"8%", align:"center", "rowspan":2},
	            {field :"partymbrInVillageNum",title:"农村党员数",width :"8%", align:"center", "rowspan":2},
	            {field :"threeYearsMember",title:"近3年新发展党员数",width :"8%", align:"center", "rowspan":2},
	            {field :"threeYearsUnqualifieds",title:"近3年处置不合格党员数",width :"8%", align:"center", "rowspan":2},
	            {field :"isTrainingInRotation",title:"是否每年对党员轮训一遍",width :"8%", align:"center", "rowspan":2},
	            {field :"isPartyMemberTrain",title:"党员是否按时足额主动交纳党费",width :"8%", align:"center", "rowspan":2},
	            //群团部门设置情况
	            {title:"是否健全群团组织",width :"8%", align:"center", "colspan":3},
	            //基础保障情况
	            {field :"isOneself",title:"是否有单独活动场所",width :"8%", align:"center", "rowspan":2},
	            {field :"stageArea",title:"使用面积",width :"8%", align:"center", "rowspan":2},
	            {field :"isIntoManage",title:"党建工作经费是否纳入企业管理费年度预算",width :"8%", align:"center", "rowspan":2},
	            //落实相关制度情况
	            {field :"isDevelopListen",title:"是否按规定开展“三会一课”",width :"8%", align:"center", "rowspan":2},
	            {field :"isDevelopDiscussions",title:"是否按规定每年开展民主评议党员",width :"8%", align:"center", "rowspan":2},
	            {field :"isDevelopAnalysis",title:"是否按规定每年开展党员党性分析",width :"8%", align:"center", "rowspan":2},
	            {field :"isChangeEveryyear",title:"党组织是否按规定进行换届",width :"8%", align:"center", "rowspan":2}
	            
	         ],[
	            //企业类型
	            {field :"privateBusiness",title:"私营企业",width :"8%", align:"center", "rowspan":1},
	            {field :"mainlangBusiness",title:"港澳台商投资企业",width :"8%", align:"center", "rowspan":1},
	            {field :"foreignBusiness",title:"外商投资企业",width :"8%", align:"center", "rowspan":1},
	            //党组织组建形式
	            {field :"aloneCreate",title:"单独建立",width :"8%", align:"center", "rowspan":1},
	            {field :"unionCreate",title:"联合建立",width :"8%", align:"center", "rowspan":1},
	            {field :"gridCreate",title:"网格建立",width :"8%", align:"center", "rowspan":1},
	            //党组织类别
	            {field :"partyBranch",title:"党支部",width :"8%", align:"center", "rowspan":1},
	            {field :"generalParty",title:"党总支",width :"8%", align:"center", "rowspan":1},
	            {field :"partyCommittee",title:"党委",width :"8%", align:"center", "rowspan":1},
	            //未建立党组织原因
	            {field :"noParty",title:"没有党员",width :"8%", align:"center", "rowspan":1},
	            {field :"noSupport",title:"企业出资人不支持",width :"8%", align:"center", "rowspan":1},
	            {field :"noGuidance",title:"上级党组织未及时指导",width :"8%", align:"center", "rowspan":1},
	            {field :"partyOther",title:"外商投资企业",width :"8%", align:"center", "rowspan":1},
	            //来源
	            {field :"contributor",title:"出资人担任",width :"8%", align:"center", "rowspan":1},
	            {field :"midBear",title:"中层管理人员担任",width :"8%", align:"center", "rowspan":1},
	            {field :"upBear",title:"上级党组织选派",width :"8%", align:"center", "rowspan":1},
	            {field :"bearOther",title:"其他人员担任",width :"8%", align:"center", "rowspan":1},
	            //是否健全群团组织
	            {field :"hasSociaty",title:"公会",width :"8%", align:"center", "rowspan":1},
	            {field :"hasYouthLeague",title:"共青团",width :"8%", align:"center", "rowspan":1},
	            {field :"hasWomenLeague",title:"妇联",width :"8%", align:"center", "rowspan":1}
	            
	         ] ],
	      onLoadSuccess : function(data) {
	    	  //	debugger;
//	    	  $("a[name='editBtn']").linkbutton({text:'编辑'})
	      }
	};
	//debugger;
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