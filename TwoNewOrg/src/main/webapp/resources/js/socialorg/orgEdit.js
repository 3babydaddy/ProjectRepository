$(function(){
	
	validate();
	
	$("#otherCheckbox").click(function(){
		if($(this).get(0).checked)
			$("#otherTxt").prop("disabled",false);
		else
			$("#otherTxt").prop("disabled",true);
	});
	
	$("#quxianji").change(function(){
		var v = $(this).val();
		if(v == '1'){
			$("#otherTypeTr").css("display",'');
		}else{
			$("#otherTypeTr").css("display",'none');
		}
	});
	if($('#otherCheckbox').is(':checked')) {
		$("#otherTxt").prop("disabled",false);
	}
	
	var set = {
			nonSelectedText: '请选择',
			numberDisplayed: 10,
			allSelectedText:'全部'
		    };
	$("#otherCondition").multiselect(set);
	
	$("#nature").on('change', function(){
		eduactionSign();
	});
	eduactionSign();
	
	//点击列表页上的上报按钮事件
	if($("#clickSign").val() == 'clickSign'){
		reportHigher();
	}
});

function eduactionSign(){
	var nature = $("#nature").val();
	if(nature == "0201" || nature == "0204"){
		document.getElementById("isIdeologicalPoliticalOrg").disabled=false;
		document.getElementById("isMoralEducationOrg").disabled=false;
	}else{
		document.getElementById("isIdeologicalPoliticalOrg").disabled=true;
		document.getElementById("isMoralEducationOrg").disabled=true;
	}
}

function save(flag){
	if(flag == 1){
		
		$("#reportHigher").val(0);
		submit(0);
	}else{
		myconfirm('一旦上报信息不可修改，您确认要上报吗？',function(r){    
			if(r){
				var prmFlag = $("#flag").val();
				if(prmFlag != "true"){
					myconfirm('该组织下没有党员信息,是否不需要录入?',function(r){    
						if(r){
							submit(1);
						}
					});
				}else{
					submit(1);
				}
			}
		});
	}
	
	
	
}

function submit(flag){
	$(".btn").prop('disabled',true);
	//组装数据
	var chargeTwodeputyAcommitteeType = getChecked("chargeTwodeputyAcommitteeTypeTxt");
	$("#chargeTwodeputyAcommitteeType").val(chargeTwodeputyAcommitteeType);
    $.post(ctx + "/socialorg/orgedit",getJsonParams("editForm"),function(result){
    	$(".btn").prop('disabled',false);
    	if(result.status == 1){
        	alertx(result.msg,function(){
        		if(flag == 0)
        			window.location.reload();
        		else
        			parent.utils.e.closeWin('editwin');
        	});
    	}else{
    		alertx(result.msg);
    	}

    });
}

function reportHigher(){
	$("#reportHigher").val(1);
	$("#editForm").submit();
}
function getChecked(checkName){
	var chk_value =[]; 
	$('input[name="'+checkName+'"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	return chk_value.join(",");
}


function addPartymbr(){
	var url = ctx + "/socialorg/addPartymbr";
	parent.utils.e.openWin('addpartymbrWin','增加',url,"80%","80%",function(){
		window.location.reload();
	},false);
}

function removePartymbr(){
	var url = ctx + "/socialorg/removePartymbr";
	parent.utils.e.openWin('removepartymbrWin','减少',url,"80%","80%",function(){
		window.location.reload();
	},false);
}
/*******************************/
function showPartyMbrList(){
	var url = ctx + "/socialorg/partyMbrList?id="+$("#mainId").val();
	openLayerDialog('党员信息','700','400',url);
}

function validate(){
	$("#editForm").validate({
	    rules: {
	      name:{
		        required: true
		      },
	      nature: {
	        required: true
	      },
	      category:{
	    	  required: true
	      },
	      registerOrg:{
	    	  required: true
	      },
	      businessDirectorOrg:{
	    	  required: true
	      },
	      address:{
	    	  required: true
	      },
	      jobinTotalnum:{
	    	  required: true,
	    	  maxlength:9,
	    	  digits:true
	      },
	      jobinMajorNum:{
	    	  required: true,
	    	  maxlength:9,
	    	  digits:true
	      },
	      jobinPluralityNum:{
	    	  required: true,
	    	  maxlength:9,
	    	  digits:true
	      },
	      jobinSocialteamGroupmemberNum:{
	    	  maxlength:9,
	    	  digits:true
	      },
	      jobinSocialteamIndividualmemberNum:{
	    	  maxlength:9,
	    	  digits:true
	      },
	      chargeName:{
	    	  required: true
	      },
	      chargePartymemberIs:{
	    	  required: true
	      },
	      chargePartyorgSecretaryIs:{
	    	  required: true
	      },
	      chargeTwodeputyAcommitteeIs:{
	    	  required: true
	      }
	      
	    },
	    messages: {
	    	name: "请输入名称",
	    	nature: {
	    		required: "请选择性质"
		    },
		    category: {
		    	required: "请选择类别"
		    },
		    registerOrg: {
		    	required: "请输入登记机构"
		    },
		    businessDirectorOrg: {
		    	required: "请输入业务主管单位"
		    },
		    address: {
		    	required: "请输入住地"
		    },
		    jobinTotalnum: {
		    	required: "请输入总数"
		    },
		    jobinMajorNum: {
		    	required: "请输入专职人员数量"
		    },
		    jobinPluralityNum: {
		    	required: "请输入兼职人员数量"
		    },
		    chargeName: {
		    	required: "请输入负责人姓名"
		    },
		    chargePartymemberIs: {
		    	required: "请选择是否党员"
		    },
		    chargePartyorgSecretaryIs: {
		    	required: "请选择是否党组织书记"
		    },
		    chargeTwodeputyAcommitteeIs: {
		    	required: "请选择是否担任区县级<br>以上（含区县）<br>“两代表一委员”"
		    }
	    },
	    submitHandler:function(form){
	    	save(0);
        } 
	});
}