
$(function(){
	
	 validate();
		
	 initPartyOrgTree();
	 
	 var i = 0;
		$("#add_operate_address").click(function(){
			$(this).before($(this).prev().clone());
			if(i == 0){
				$(this).prev().append('<button type="button"  class="remove_operate_address btn btn-primary btn-sm glyphicon glyphicon-minus"></button>');
			}
			i++;
			$(this).prev().find(".operate_address").val("").trigger('change');
			$(this).prev().find("input[id=partymbrInUnpublicNum"+(i-1)+"]").attr('id','partymbrInUnpublicNum'+i).val("");
			$(this).prev().find("input[id=filepartymbrUnderThirtyfiveNum"+(i-1)+"]").attr('id','filepartymbrUnderThirtyfiveNum'+i).val("");
			$(this).prev().find("input[id=partymbrUnderThirtyfiveNum"+(i-1)+"]").attr('id','partymbrUnderThirtyfiveNum'+i).val("");
		});
		
		$("#editForm").on('click','.remove_operate_address',function(){
				$(this).parent().remove();
				i--;
		});
		
		
		var j = 0;
		$("#add_operate_party").click(function(){
			$(this).before($(this).prev().clone());
			if(j == 0){
				$(this).prev().append('<button type="button"  class="remove_operate_party btn btn-primary btn-sm glyphicon glyphicon-minus"></button>');
			}
			j++;
			$(this).prev().find(".operate_party").val("").trigger('change');
			$(this).prev().find("select[name=deputySecretaryType]").val("");
			$(this).prev().find("input[name=deputySecretaryName]").val("");
			$(this).prev().find("input[name=deputySecretaryBirthdayTxt]").val("");
			$(this).prev().find("select[name=deputySecretarySex]").val("");
			$(this).prev().find("select[name=deputySecretaryEducation]").val("");
			$(this).prev().find("select[name=deputySecretaryIsFullTime]").val("");
			$(this).prev().find("select[name=isBoardOfficer]").val("");
		});
		
		$("#editForm").on('click','.remove_operate_party',function(){
				$(this).parent().remove();
				j--;
		});
		
		//点击列表页上的上报按钮事件
		if($("#clickSign").val() == 'clickSign'){
			submitReport();
		}
});

function getQueryParams() {
	var params = {};
	var fields =$('#editForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 

   return   params ; 
	
}

function reloadData(){
	var params = getQueryParams();
	
	$('#gridPanel').datagrid('options').queryParams = params;
	
	 $('#gridPanel').datagrid('reload');
}

function save(flag){
	//debugger;
	if(flag == 0){
		$("#reportHigher").val(0);
		submit(0);
	}else if(flag == 1){
		myconfirm('一旦上报信息不可修改，您确认要上报吗？',function(r){    
			if(r){
				submit(1);
			}
		});
	}
}

function submitReport(){
	$("#reportHigher").val(1);
	$("#editForm").submit();
}

function submit(flag){
	//var formdata=new FormData($("#editForm")[0]);
	//alert($("#editForm").serialize());
	changeInfoConver();
	deputySecConver();
	$.ajax({
		url:'../cover/partyorgedit',
		type : 'post',
		data:$("#editForm").serialize(),
		//dataType:'json',
		async: false,
		success:function(result){
			//$(".btn").prop('disabled',true);
			if(result.status == 1){
		    	alertx(result.msg,function(){
		    		if(flag == 0)
		    			//window.location.reload();
		    			parent.utils.e.closeWin('editwin');
		    		else
		    			parent.utils.e.closeWin('editwin');
		    	});
			}else{
				alertx(result.msg);
			}
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown) {
			 debugger;
		 },
	});
}

function validate(){
	$("#editForm").validate({
	    rules: {
	      orgIds:{
		        required: true
		      },
		  isSetUpPartyOrg:{
	    	  required: true
	      },
	      absencePartyOrgReasion:{
	    	  required: true
	      },
	      partyOrgForm:{
	    	  required: true
	      },
	      partyOrgType:{
	    	  required: true
	      },
	      instructorName:{
	    	  required: true
	      },
	      instructorUnitTxt:{
	    	  required: true
	      },
	      instructorJob:{
	    	  required: true
	      },
	      partyOrgTimeTxt:{
	    	  required: true
	      },
	      filepartyOrgAttachment:{
	    	  required: true
	      },
	      partyOrgName:{
	    	  required: true
	      },
	      partyOrgTel:{
	    	  required: true,
	    	  isMobile:true
	      },
	      secretaryName:{
	    	  required: true
	      },
	      secretaryBirthdayTxt:{
	    	  required: true
	      },
	      secretaryEducation:{
	    	  required: true
	      },
	      secretarySource:{
	    	  required: true
	      },
	      secretaryCompanyTxt:{
	    	  required: true
	      },
	      partTimeWorkers:{
	    	  required: true,
	    	  maxlength:8,
	    	  number: true
	      },
	      fullTimeWorkers:{
	    	  required: true,
	    	  maxlength:8,
	    	  number: true
	      },
	      stageArea:{
	    	  required: true,
	    	  maxlength:8,
	    	  number: true
	      },
	      otherShareStage:{
	    	  required: true
	      },
	      communityShareStage:{
	    	  required: true
	      },
	      otherInfo:{
	    	  required: true
	      }
	    },
	    messages: {
	    	name: "请输入名称",
	    	isSetUpPartyOrg: {
	    		required: "请选择是否成立党组织"
		    },
		    absencePartyOrgReasion: {
	    		required: "请选择未建立党组织原因"
		    },
		    partyOrgForm: {
	    		required: "请选择党组织组建形式"
		    },
		    partyOrgType: {
	    		required: "请选择党组织类别"
		    },
		    partyOrgTimeTxt: {
	    		required: "请填写选派党建工作指导员或联络员的职务"
		    },
		    filepartyOrgAttachment: {
	    		required: "请上传党组织成立相关附件"
		    },
		    partyOrgName: {
	    		required: "请输入党组织名称"
		    },
		    partyOrgTel: {
	    		required: "请输入党组织联系电话"
		    },
		    secretaryName: {
	    		required: "请输入书记姓名"
		    },
		    secretaryBirthdayTxt: {
	    		required: "请输入书记出生日期"
		    },
		    secretaryEducation: {
	    		required: "请选择书记学历"
		    },
		    secretarySource: {
	    		required: "请选择书记来源"
		    },
		    secretaryCompanyTxt: {
	    		required: "请选择书记所在单位"
		    },
		    partTimeWorkers: {
	    		required: "请输入兼职人数"
		    },
		    fullTimeWorkers: {
	    		required: "请输入专职人数"
		    },
		    stageArea: {
	    		required: "请输入单独建设活动场所面积"
		    },
		    otherShareStage: {
	    		required: "请选择与其他单位党组织共用活动场所"
		    },
		    communityShareStage: {
	    		required: "请选择与社区共用活动场所活动场所"
		    },
		    otherInfo: {
	    		required: "请输入其他"
		    }
	    },
	    submitHandler:function(form){
	    	save(1);
        } 
	});
}

var departmentSetting = {
		check: {
			enable: false
		},
		data: {
			simpleData: {
				enable: true,
				idKey:'id',
				pIdKey:'parentPartyOrg'
			}
		},
		callback: {
//			beforeClick: beforeClick,
			onClick: onClick
		}
	};

function initPartyOrgTree(){
	$.ajax({
		url : "../basemanage/partyorginfoquery",
		type : "POST",
		data : {
			name : '',
			avail:'0'
		},
		success : function(data) {
			var tree = $.fn.zTree.init($("#parentDepartMent"), departmentSetting, data);
			tree.expandAll(true);
			var higherDepartHid = $("#subjectionPartyId").val(); 
			var treeObj = $.fn.zTree.getZTreeObj("parentDepartMent");
			var nodes = treeObj.getNodeByParam('id', higherDepartHid);
			if(nodes != null){
				//$("#subjectionPartyName").attr("value", nodes.name);	
				$("#higherDepart").attr("value", nodes.name);
			}
		}
	})
}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("parentDepartMent"),
	nodes = zTree.getSelectedNodes(),
	nodesValue = "";
	nodesId = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		nodesValue += nodes[i].name + ",";
		nodesId += nodes[i].id + ",";
	}
	if (nodesValue.length > 0 ) {
		nodesValue = nodesValue.substring(0, nodesValue.length-1);		
		nodesId = nodesId.substring(0, nodesId.length-1);		
	}
	$("#higherDepart").attr("value", nodesValue);
	$("#subjectionPartyId").attr("value", nodesId);
	$("#subjectionPartyName").attr("value", nodesValue);
}
function isIE(){
	if (window.navigator.userAgent.indexOf("MSIE")>=1) 
	return true; 
	else
	return false; 
	}
function showMenu() {
	var higherDepart = $("#higherDepart");
	var higherDepartOffset = $("#higherDepart").offset();
	if(isIE())
		$("#parentPartyOrg").css({left:higherDepartOffset.left + "px", "top":(higherDepartOffset.top + higherDepart.outerHeight())/2 + "px"}).slideDown("fast");
	else
		$("#parentPartyOrg").css({left:higherDepartOffset.left + "px", top:higherDepartOffset.top + higherDepart.outerHeight() + "px"}).slideDown("fast");

	$("#parentPartyOrg").css("zIndex",999);
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#parentPartyOrg").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "parentPartyOrg" || $(event.target).parents("#parentPartyOrg").length>0)) {
		hideMenu();
	}
}

function showUpload(obj, action){
	var id = $("#"+obj.id.replace('file','')).val();
	var filename = $("#"+obj.id).val();
	var mainId = $("#mainId").val();
	var modular = $("#modular").val();
	var type = $("#type").val();
	var action = action;
	var url = ctx + '/cover/uploadFile?id='+id+'&mainTableId='+mainId+'&filename='+filename+
						'&sign='+obj.id+'&modular='+modular+'&type='+type+'&action='+action+'&method=edit';
	//openWin("撤销", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','文件上传',url,"60%","40%",function(){
		//reloadData()
	});
}

function showPartyInfo(partyOrgId){
	var url = ctx + '/cover/showPartyInfo?partyOrgId='+partyOrgId;
	utils.e.openWin('showPartyInfoWin','党员基本信息',url,"80%","40%",function(){
		//reloadData()
	});
}

function changeInfoConver(){
	var changeArray = new Array();
	var changeTimes = document.getElementsByName("partymbrInUnpublicNum");
	var changeFiles = document.getElementsByName("partymbrUnderThirtyfiveNum");
	for(var i = 0; i < changeTimes.length; i++){
		if(changeTimes[i].value != "" || changeFiles[i].value != ""){
			var info = createChangeInfo(changeTimes[i].value, changeFiles[i].value);
			changeArray.push(info);
		}
	}
	//debugger;
	$("#changeList").val(JSON.stringify(changeArray));
}

function deputySecConver(){
	var deputySecArray = new Array();
	var types = document.getElementsByName("deputySecretaryType");
	var names = document.getElementsByName("deputySecretaryName");
	var birthdayTxts = document.getElementsByName("deputySecretaryBirthdayTxt");
	var sexs = document.getElementsByName("deputySecretarySex");
	var educations = document.getElementsByName("deputySecretaryEducation");
	var isFullTimes = document.getElementsByName("deputySecretaryIsFullTime");
	var isBoardOfficers = document.getElementsByName("isBoardOfficer");
	for(var i = 0; i < types.length; i++){
		if(types[i].value != "" || names[i].value != "" || birthdayTxts[i].value != "" || sexs[i].value != ""
				|| educations[i].value != "" || isFullTimes[i].value != "" || isBoardOfficers[i].value != ""){
			var info = createDuputySecInfo(types[i].value, names[i].value, birthdayTxts[i].value, sexs[i].value
					, educations[i].value, isFullTimes[i].value, isBoardOfficers[i].value);
			deputySecArray.push(info);
		}
	}
	$("#deputySecretaryList").val(JSON.stringify(deputySecArray));
}

//换届信息实体类
function createChangeInfo(changeTimeTxt, changeAttachmentId){
	var changeInfo = new Object();
	changeInfo.changeTimeTxt = changeTimeTxt;
	changeInfo.changeAttachmentId = changeAttachmentId;
	return changeInfo;
}
//党务信息实体类
function createDuputySecInfo(deputySecretaryType, deputySecretaryName, deputySecretaryBirthdayTxt, deputySecretarySex,
							deputySecretaryEducation, deputySecretaryIsFullTime, isBoardOfficer){
	var duputySecInfo = new Object();
	duputySecInfo.deputySecretaryType = deputySecretaryType;
	duputySecInfo.deputySecretaryName = deputySecretaryName;
	duputySecInfo.deputySecretaryBirthdayTxt = deputySecretaryBirthdayTxt;
	duputySecInfo.deputySecretarySex = deputySecretarySex;
	duputySecInfo.deputySecretaryEducation = deputySecretaryEducation;
	duputySecInfo.deputySecretaryIsFullTime = deputySecretaryIsFullTime;
	duputySecInfo.isBoardOfficer = isBoardOfficer;
	return duputySecInfo;
}

function getElByName(name){
	return $("input[name="+name+"]");
}
function showDept(){
	showDeptTree("instructorUnit","createOrgTxt");
}
function showDept1(){
	showDeptTree("secretaryCompany","createOrgTxt1");
}

function showDept2(){
	showDeptTree("belongUnit","createOrgTxt2");
}
