
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
		url:'../socialorg/partyconstructionedit',
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
		    isPartyMemberTrain : {
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
		    totalPayDues : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    cityFunds : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    districtFunds : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    socialOrgFunds : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    otherContent : {
		    	required: true
		    },
		    quarterPartyMeetingTimes : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    monthPartyMeetingTimes : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    partyMeetingMonth : {
		    	required: true
		    },
		    listenPartyClass : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    lifeMeetingTimes : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    developPartyBranchNo : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    yearTotalPayDues : {
		    	required: true,
	    		maxlength:8,
	    		number : true
		    },
		    annualSurvey : {
		    	required: true
		    },
		    isIntoManage : {
		    	required: true
		    }
	    },
	    messages: {
	    	name: "请输入名称",
	    	isPartyMemberTrain : {
	    		required: "请选择党务工作者是否参加1次以上培训"
		    },
		    threeYearsMember : {
	    		required: "请输入近三年新发展党员数"
		    },
		    threeYearsUnqualifieds : {
	    		required: "请输入近三年处置不合格党员数"
		    },
		    threeYearsUnqualifieds : {
	    		required: "请选择是否对党员轮训一遍"
		    },
		    totalPayDues : {
	    		required: "请输入党建工作经费总额"
		    },
		    cityFunds : {
	    		required: "请输入市区财政支出数额"
		    },
		    districtFunds : {
	    		required: "请输入区管党费支持数额"
		    },
		    socialOrgFunds : {
	    		required: "请输入社会组织管理经费支持数额"
		    },
		    otherContent : {
	    		required: "请输入其他"
		    },
		    quarterPartyMeetingTimes : {
	    		required: "请输入每季度召开党员大会次数"
		    },
		    monthPartyMeetingTimes : {
	    		required: "请输入每月召开支部委员会次数"
		    },
		    partyMeetingMonth : {
	    		required: "请选择是否每月召开1~2次党小组会"
		    },
		    listenPartyClass : {
	    		required: "请输入组织党员听党课次数"
		    },
		    lifeMeetingTimes : {
	    		required: "请输入召开组织生活会的次数"
		    },
		    developPartyBranchNo : {
	    		required: "请输入开展民主评议党员的党支部数"
		    },
		    yearTotalPayDues : {
	    		required: "请输入上年度党员缴纳党费总额"
		    },
		    annualSurvey : {
	    		required: "请选择年检结果"
		    },
		    isIntoManage : {
	    		required: "请选择党建工作经费是否列入学校年度经费预算"
		    }
	    },
	    submitHandler:function(form){
	    	save(1);
        } 
	});
}

function getElByName(name){
	return $("input[name="+name+"]");
}
function showDept(){
	showDeptTree("createOrg","createOrgTxt");
}