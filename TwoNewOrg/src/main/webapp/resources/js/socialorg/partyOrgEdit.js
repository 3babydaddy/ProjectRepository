
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
			$(this).prev().find("input[name=partymbrInUnpublicNum"+(i-1)+"]").attr('id','partymbrInUnpublicNum'+i).val("");
			$(this).prev().find("input[name=filepartymbrUnderThirtyfiveNum"+(i-1)+"]").attr('id','filepartymbrUnderThirtyfiveNum'+i).val("");
			$(this).prev().find("input[name=partymbrUnderThirtyfiveNum"+(i-1)+"]").attr('id','partymbrUnderThirtyfiveNum'+i).val("");
			$(this).prev().find("input[name=partymbrInUnpublicNum"+(i-1)+"]").attr('name','partymbrInUnpublicNum'+i).val("");
			$(this).prev().find("input[name=filepartymbrUnderThirtyfiveNum"+(i-1)+"]").attr('name','filepartymbrUnderThirtyfiveNum'+i).val("");
			$(this).prev().find("input[name=partymbrUnderThirtyfiveNum"+(i-1)+"]").attr('name','partymbrUnderThirtyfiveNum'+i).val("");
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
			$(this).prev().find("select[name=deputySecretaryType"+(j-1)+"]").attr('name','deputySecretaryType'+j).val("");
			$(this).prev().find("input[name=deputySecretaryName"+(j-1)+"]").attr('name','deputySecretaryName'+j).val("");
			$(this).prev().find("input[name=deputySecretaryBirthdayTxt"+(j-1)+"]").attr('name','deputySecretaryBirthdayTxt'+j).val("");
			$(this).prev().find("select[name=deputySecretarySex"+(j-1)+"]").attr('name','deputySecretarySex'+j).val("");
			$(this).prev().find("select[name=deputySecretaryEducation"+(j-1)+"]").attr('name','deputySecretaryEducation'+j).val("");
			$(this).prev().find("select[name=deputySecretaryIsFullTime"+(j-1)+"]").attr('name','deputySecretaryIsFullTime'+j).val("");
			$(this).prev().find("select[name=isBoardOfficer"+(j-1)+"]").attr('name','isBoardOfficer'+j).val("");
		});
		
		$("#editForm").on('click','.remove_operate_party',function(){
				$(this).parent().remove();
				j--;
		});
		
		$("#isSetUpPartyOrg").on('change', function(){
			isSetUpPartyOrg();
		});
		isSetUpPartyOrg();
		
		$("#isInstructor").on('change', function(){
			showInstructorInfo();
		});
		showInstructorInfo();
		
		$("#partyOrgForm").on('change', function(){
			showChangInfo();
		});
		showChangInfo();
		
		var nature = $("#nature").val();
		if(nature == '0201'){
			document.getElementById("isPartyIntoSchool").disabled=false;
			document.getElementById("parentPartyOrgType").disabled=false;
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

function isSetUpPartyOrg(){
	var setUpSign = $("#isSetUpPartyOrg").val();
	if(setUpSign == 0 && setUpSign != ""){
		document.getElementById("absencePartyOrgReasion").disabled=false;
		//hiddenAllsign(setUpSign);
	}else{
		document.getElementById("absencePartyOrgReasion").disabled=true;
		//showAllsign(setUpSign);
	}
}

function showChangInfo(){
	var hiddenFlag = $("#partyOrgForm").val();
	if(hiddenFlag == 0 && hiddenFlag != ""){
		showAllsign(hiddenFlag);
		document.getElementById("divTwo").style.display='none';
	}else if(hiddenFlag == 1 || hiddenFlag == 2){
		showAllsign(hiddenFlag);
		document.getElementById("divTwo").style.display='';
	}else{
		hiddenAllsign(hiddenFlag);
	}
}

function showInstructorInfo(){
	var setUpSign = $("#isInstructor").val();
	if(setUpSign == 0 && setUpSign != ""){
		document.getElementById("divOne").style.display='none';
	}else{
		document.getElementById("divOne").style.display='';
	}
}

function hiddenAllsign(flag){
	if((flag == 0 && flag != "") || flag == 3){
		//document.getElementById("partyOrgForm").style.display='none';
		document.getElementById("partyOrgType").style.display='none';
		document.getElementById("isInstructor").style.display='none';
	}
	document.getElementById("divOne").style.display='none';
	document.getElementById("divTwo").style.display='none';
	document.getElementById("divThree").style.display='none';
	document.getElementById("divFour").style.display='none';
	document.getElementById("divFive").style.display='none';
	document.getElementById("divSix").style.display='none';
	document.getElementById("divSev").style.display='none';
}

function showAllsign(flag){
	if((flag == 0 && flag != "") || flag != 3){
		//document.getElementById("partyOrgForm").style.display='';
		document.getElementById("partyOrgType").style.display='';
		document.getElementById("isInstructor").style.display='';
	}
	document.getElementById("divOne").style.display='';
	document.getElementById("divTwo").style.display='';
	document.getElementById("divThree").style.display='';
	document.getElementById("divFour").style.display='';
	document.getElementById("divFive").style.display='';
	document.getElementById("divSix").style.display='';
	document.getElementById("divSev").style.display='';
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
	$.ajax({
		url:'../socialorg/partyorgedit',
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
	      isInstructor:{
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
	    	  required: true
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
		    isInstructor: {
	    		required: "请选择是否选派党建工作指导员或联络员"
		    },
		    instructorName: {
	    		required: "请填写选派党建工作指导员或联络员的姓名"
		    },
		    instructorUnitTxt: {
	    		required: "请选择选派党建工作指导员或联络员的单位"
		    },
		    instructorJob: {
	    		required: "请选择是否选派党建工作指导员或联络员"
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
	var url = ctx + '/socialorg/uploadFile?id='+id+'&mainTableId='+mainId+'&filename='+filename+
						'&sign='+obj.id+'&modular='+modular+'&type='+type+'&action='+action+'&method=edit';
	//openWin("撤销", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','文件上传',url,"60%","40%",function(){
		//reloadData()
	});
}

function showPartyInfo(orgIds){
	var url = ctx + '/socialorg/showPartyInfo?orgIds='+orgIds;
	utils.e.openWin('showPartyInfoWin','党员基本信息',url,"80%","50%",function(){
		//reloadData()
	});
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
