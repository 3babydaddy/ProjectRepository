
// 显示列表

$(document).ready(function() {
	
	loadData();
	
	$("#searchBtn").click (function() {
		reloadData();
	});
	
	/**
	 * export Excel btn click
	 */
//	$("#exportExcelBtn").click(function() {
//		$('#exportExcelBtn').linkbutton('disable');//设置变灰按钮  
//		window.location.href=ctx + "/file/exportSocialReport";
//		setTimeout("$('#exportExcelBtn').linkbutton('enable')",5000); //设置三秒后提交按钮 显示  
//	});
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
			url : ctx+'/commonstatistics/privateschoolworkledger',
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
			     {title:"社会组织基本情况",width :"8%", align:"center", "colspan":17},      
			     {title:"党组织设置情况",width :"8%", align:"center", "colspan":20},      
			     {title:"党务工作者队伍建设",width :"8%", align:"center", "colspan":12},      
			     {title:"党员队伍建设情况",width :"8%", align:"center", "colspan":8},      
			     {title:"党建工作经费情况",width :"8%", align:"center", "colspan":5},      
			     {title:"活动场所情况",width :"8%", align:"center", "colspan":4},      
			     {title:"落实相关制度情况",width :"8%", align:"center", "colspan":7},      
			     {title:"党组织情况(补充)",width :"8%", align:"center", "colspan":7},      
			     {title:"党务工作者队伍建设(补充)",width :"8%", align:"center", "colspan":11},      
			     {title:"党员情况",width :"8%", align:"center", "colspan":1},      
			     {title:"机构设置",width :"8%", align:"center", "colspan":2},      
			     {field :"isIntoManage",title:"党建工作经费是否列入学校年度经费预算",width :"8%", align:"center", "rowspan":3}      
			 ],[
			      //社会组织基本情况
			     {field :"name",title :"社会组织名称",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},          
			     {field :"nature",title :"社会组织性质",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},          
			     {field :"category",title :"社会组织类别",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},          
			     {field :"registerOrg",title :"登记机构",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},          
			     {field :"businessDirectorOrg",title :"业务主管单位",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},          
			     {field :"address",title :"住地",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},          
			     {title :"2015年度检结果",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":4},          
			     {title :"从业人员情况",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":4},          
			     {title :"负责人情况",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":3},    
			     //党组织设置情况
			     {field :"isSetUpPartyOrg",title :"是否成立党组织",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {title :"未成立党组织原因",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":4}, 
			     {field :"partyOrgTimeTxt",title :"党组织成立时间",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {field :"partyChangeTime",title :"上次换届时间",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {field :"partyOrgName",title :"党组织名称",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {field :"partyOrgTel",title :"党组织联系电话",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {title :"党组织组建形式",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":4}, 
			     {field :"isInstructor",title :"选派党建工作指导员或联络员(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {title :"党组织类别",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":5}, 
			     {field :"subjectionPartyName",title :"上级党组织名称",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     //党务工作者队伍建设
			     {title :"书记",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":5}, 
			     {title :"党务副书记",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":3}, 
			     {title :"其他党务工作者",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":3}, 
			     {field :"isPartyMemberTrain",title :"每年是否至少参加1次培训",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     
			     //党员队伍建设情况
			     {field :"partymbrInSocielorgNum",title :"纳入社会组织党组织管理的党员总数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {title :"其中",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":4}, 
			     {field :"threeYearsMember",title :"近3年新发展党员数(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {field :"threeYearsUnqualifieds",title :"近3年处置不合格党员数(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {field :"isTrainingInRotation",title :"是否每年对党员轮训一遍",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     
			     //党建工作经费情况
			     {field :"totalPayDues",title :"平均每年党建工作经费总额(元)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {title :"经费来源",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":4}, 
			     
			     //活动场所情况
			     {field :"stageArea",title :"单独建设活动场所面积",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"othersShareStage",title :"与公办组织公用活动场所",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"communityShareStage",title :"与社区公用活动场所",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"otherInfo",title :"其他(说明)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     
			     //落实相关制度情况
			     {field :"quarterPartyMeetingTimes",title :"每季度召开党员大会(次)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"monthPartyMeetingTimes",title :"每月召开支部委员会(次)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"partyMeetingMonth",title :"是否每月召开1-2次党小组会",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"listenPartyClass",title :"每年组织党员听党课(次)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"lifeMeetingTimes",title :"每年召开组织生活会的次数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"developPartyBranchNo",title :"每年开展民主评议党员的党支部数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {field :"yearTotalPayDues",title :"上年度党员交纳党费总额(元)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     //党组织情况(补充)
			     {field :"isPartyIntoSchool",title :"是否将党组织建设有关内容纳入学校章程",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     {title :"党组织所隶属上级党组织",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":6},
			     //党务工作者队伍建设(补充)
			     {title :"书记",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":6},
			     {title :"专职副书记",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":2},
			     {title :"其他委员进入行政管理层情况",width :"8%", align:"center",formatter:ifNullShowHeng, "colspan":3},
			     //党员情况
			     {field :"schoolGroupNotIn",title :"在学校从事专职工作半年以上未转入组织关系的人",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2},
			     //机构设置
			     {field :"isIdeologicalPoliticalOrg",title :"是否设立思想政治教育工作机构",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2}, 
			     {field :"isMoralEducationOrg",title :"是否设立德育工作机构",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":2} 
			 ],[
			     //2015年度检结果
				 {field :"qualified",title :"合格",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"basicQualified",title :"基本合格",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"noQualified",title :"不合格",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"noJoin",title :"为参检",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //从业人员情况
				 {field :"jobinTotalnum",title :"总数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"jobinMajorNum",title :"专职人员(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"jobinPluralityNum",title :"兼职人员(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"jobinPartymemberNum",title :"中共党员(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //负责人情况
				 {field :"chargePartymemberIs",title :"是否党员",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"chargePartyorgSecretaryIs",title :"是否兼任党组织书记",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"chargeTwodeputyAcommitteeIs",title :"是否担任区县级以上(含区县)“两代表一委员”",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //未成立党组织原因
				 {field :"noParty",title :"没有党员",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"noSupport",title :"社会组织负责人不支持",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"noGuidance",title :"上级党组织未及时指导",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"partyOther",title :"其他",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //党组织组建形式
				 {field :"aloneCreate",title :"在会员中建",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"unionCreate",title :"在理事会中建",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"gridCreate",title :"在业务主管部门联合建",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"coverCreate",title :"其他",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //党组织类别织
				 {field :"partyBranch",title :"党支部",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},				 
				 {field :"generalParty",title :"党总支",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},				 
				 {field :"partyCommittee",title :"党委",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},				 
				 {field :"partyGridCommittee",title :"联合党支部",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},				 
				 {field :"coverOrgNum",title :"联合党支部覆盖社会组织数量(个)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},				 
				 //书记
				 {field :"secretaryName",title :"姓名",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"contributor",title :"社会组织负责人兼任",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"midBear",title :"由社会组织其他人员担任",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"upBear",title :"由上级党组织选派",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"bearOther",title :"其他",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //党务副书记
				 {field :"deputySecretaryNum",title :"人数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"deputySecretaryFullNum",title :"专职(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"deputySecretaryPartNum",title :"兼职(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //其他党务工作者
				 {field :"deputySecretaryOtherNum",title :"人数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"fullTimeWorkers",title :"专职(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"partTimeWorkers",title :"兼职(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //其中
				 {field :"partymbrGroupInSocielorgNum",title :"组织关系在社会组织党组织的党员数",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"partymbrUnderThirtyfiveNum",title :"35岁以下(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"partymbrOnCollegeNum",title :"大学及以上学历(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"partymbrUnderHighschoolNum",title :"高中及以下学历(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //经费来源
				 {field :"cityFunds",title :"市区财政支持数额(元)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"districtFunds",title :"市、区管党费支持数额(元)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"socialOrgFunds",title :"社会组织管理经费支持数额(元)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"otherContent",title :"其他(说明)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //党组织所隶属上级党组织
				 {field :"eduPartOrg",title :"区教育局党组织",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"socialPartyOrg",title :"区人力资源社会保障局党组织",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"socialParty",title :"区社会组织党委",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"townParyOrg",title :"乡镇(街道)党组织",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"communityPartyOrg",title :"社区党组织",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"otherPartyOrg",title :"其他",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //书记
				 {field :"secretaryName1",title :"姓名",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"prinConcurrently",title :"由校长兼任",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"midBear1",title :"由其他管理层人员担任",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"contributor1",title :"由出资人担任",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"upBear1",title :"由上级党组织选派",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"isBoardOfficer",title :"是否为董(理)事会成员",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //专职副书记
				 {field :"deputySecretaryFullIs",title :"是否配备",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"deputySecreraryNumberIs",title :"是否为董(理)事会成员",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 //其他委员进入行政管理层情况
				 {field :"schoolmasterHold",title :"担任校长",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"deputySchoolmasterHold",title :"担任副校长(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1},
				 {field :"otherMessageHold",title :"担任其他管理层职务(人)",width :"8%", align:"center",formatter:ifNullShowHeng, "rowspan":1}
				 
				 
			 ]],
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

function socialWorkExport(){
	$('#exportExcelBtn').linkbutton('disable');
	$('#exportExcelBtn').linkbutton({text:'正在导出...'});
	$.ajax({
		url:ctx + '/file/exportSocialReport',
		data:getQueryParams(),
		type:'post',
		dataType:'json',
		success:function(result){
			if(result != '1'){
				$('#exportExcelBtn').linkbutton({text:'导出Excel'});
				$('#exportExcelBtn').linkbutton('enable');
				window.location.href=ctx + "/file/exportSocialReportFile?filePath="+result;
			}else{
				layer.alert('导出失败');
			}
		}
	});
}

function getCheckedRow(){
	var row = $("#gridPanel").datagrid('getSelected');
	return row;
}

function showDept(){
	showDeptTree("receiveOrg","createOrgTxt");
}