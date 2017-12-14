
$(function(){
	
	validate();
	
});

function save(flag){
	if(flag == 0){
		$("#reportHigher").val(0);
		submit(0);
	}else{
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
	$(".btn").prop('disabled',true);
	$.ajax({
		url:'../cover/partyconstructionedit',
		type : 'post',
		data:$("#editForm").serialize(),
		//dataType:'json',
		async: false,
		success:function(result){
			$(".btn").prop('disabled',false);
			if(result.status == 1){
		    	alertx(result.msg,function(){
		    		if(flag == 0){
		    			//window.location.reload();
		    			parent.utils.e.closeWin('editwin');
		    		}else{
		    			parent.utils.e.closeWin('editwin');
		    		}
		    	});
			}else{
				alertx(result.msg);
			}
		},
		 error: function(XMLHttpRequest, textStatus, errorThrown) {
			 //debugger;
		 },
	});
}


function getChecked(checkName){
	var chk_value =[]; 
	$('input[name="'+checkName+'"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	return chk_value.join(",");
}

function validate(){
	$("#editForm").validate({
	    rules: {
	    	partyOrgName : {
		        required: true
		    },
		    threeYearsMember : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    threeYearsUnqualifieds : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    isTrainingInRotation : {
		    	required: true
		    },
		    isPartyMemberTrain : {
		    	required: true
		    },
		    isIntoManage : {
		    	required: true
		    },
		    isDevelopListen : {
		    	required: true
		    },
		    isDevelopDiscussions : {
		    	required: true
		    },
		    isDevelopAnalysis : {
		    	required: true
		    },
		    isChangeEveryyear : {
		    	required: true
		    },
		    isBackwardParty : {
		    	required: true
		    },
		    isRectifyParty : {
		    	required: true
		    },
		    filerectifyAtachementId : {
		    	required: true
		    },
		    secretaryTotalTime : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    partyTotalTime : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    partyAvgTime : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    }
		},
	    messages: {
	    	name: "请输入名称",
	    	threeYearsMember : {
	    		required: "请输入数量"
		    },
		    threeYearsUnqualifieds : {
	    		required: "请输入数量"
		    },
		    isTrainingInRotation : {
	    		required: "请选择是否对党员轮训一遍"
		    },
		    isPartyMemberTrain : {
	    		required: "请选择党员是否按时足额主动交纳党费"
		    },
		    isIntoManage : {
	    		required: "请选择党建工作经费是否纳入企业管理费年度预算"
		    },
		    isDevelopListen : {
	    		required: "请选择是否按规定开展“三会一课”"
		    },
		    isDevelopDiscussions : {
	    		required: "请选择是否按规定每年开展民主评议党员"
		    },
		    isDevelopAnalysis : {
	    		required: "请选择是否按规定每年开展党员党性分析"
		    },
		    isChangeEveryyear : {
	    		required: "请选择党组织是否按规定进行换届"
		    },
		    isBackwardParty : {
	    		required: "请选择是否倒排相对后进基层党组织"
		    },
		    isRectifyParty : {
	    		required: "请选择是否完成整顿相对后进基层党组织"
		    },
		    filerectifyAtachementId : {
	    		required: "请上传整顿报告"
		    },
		    secretaryTotalTime : {
	    		required: "请输入书记总学时"
		    },
		    partyTotalTime : {
	    		required: "请输入党员总学时"
		    },
		    partyAvgTime : {
	    		required: "请输入党员平均学时"
		    }
	    },
	    submitHandler:function(form){
	    	save(1);
        } 
	});
}

function showUpload(obj, action){
	var id = $("#"+obj.id.replace('file','')).val();
	var filename = $("#"+obj.id).val();
	var mainId = $("#id").val();
	var modular = $("#modular").val();
	var type = $("#type").val();
	var action = action;
	var url = ctx + '/unpublic/uploadFile?id='+id+'&mainTableId='+mainId+'&filename='+filename+
						'&sign='+obj.id+'&modular='+modular+'&type='+type+'&action='+action+'&method=edit';
	//openWin("撤销", url,"50%","90%",function(){reloadData()});
	utils.e.openWin('cancelwin','文件上传',url,"60%","80%",function(){
		//reloadData()
	});
}

function getElByName(name){
	return $("input[name="+name+"]");
}
function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}