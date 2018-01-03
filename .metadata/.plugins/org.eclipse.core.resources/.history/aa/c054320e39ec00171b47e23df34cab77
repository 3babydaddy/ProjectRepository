$(function(){
	
	validate();
	
	hideOther();
	
	belocateOther($("#belocated_address").val());
	
	$("#industryType").change(function(){
		var v = $(this).val();
		 var tempAjax = "";
		$.ajax({
    		url:ctx+'/data/getNationalityDatas?industryType=' + v,
    		dataType:'json',
    		async: false,
    		success:function(msg){
    			 $.each(msg,function(i,n){
                     tempAjax += "<option value='"+n.code+"'>"+n.value+"</option>";
                  });
                  $("#nationality").empty();
                  $("#nationality").append(tempAjax);    
                                      //更新内容刷新到相应的位置
                  $('#nationality').selectpicker('render');
                  $('#nationality').selectpicker('refresh');
    		}
    	});
	});
	
	$("#belocated_address").change(function(){
		belocateOther($(this).val());
	});
	
	$("#otherCheckbox").click(function(){
		if($(this).get(0).checked)
			$("#otherTxt").prop("disabled",false);
		else
			$("#otherTxt").prop("disabled",true);
	});
	
	/**
	 * 是否存在经营地
	 */
	$("#isHaveAddress").change(function(){
		var v = $(this).val();
		if(v == '1'){
			$("#operateAddressTd").css("display",''); 
		}else{
			$("#operateAddressTd").css("display",'none');
		}
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
	var i = 0;
	$("#add_operate_address").click(function(){
		$(this).before($(this).prev().clone());
		//$(this).before($(".hiddenCitySelect").clone().show());
		if(i == 0){
			$(this).prev().append('<button type="button"  class="remove_operate_address btn btn-primary btn-sm glyphicon glyphicon-minus"></button>');
		}
		i++;
		
		$(this).prev().find(".operate_address").val("").trigger('change');
		$(this).prev().find("input[name=operateAddress]").val("");
		
	});
	$("#editForm").on('click','.remove_operate_address',function(){
			$(this).parent().remove();
			i--;
		});
	
	
	/*var set = {
			nonSelectedText: '请选择',
			numberDisplayed: 10,
			allSelectedText:'全部'
		    };
	$("#nationality").multiselect(set);*/
	$("#nationality").selectpicker({});
	
	var set = {
			nonSelectedText: '请选择',
			numberDisplayed: 10,
			allSelectedText:'全部'
		    };
	$("#hangyeleixing").multiselect(set);
	
	 
	$("#editForm").on('change','.register_address,.operate_address',function(){
		var $this = $(this);
		var v = $(this).val();
		var div = createCitySelect(v);
		$this.next(".proclass").remove();
		$this.after('<span class="proclass">' + div + '</span>');
		
		createCitys(this,v);
	});
	//初始化注册地
	var v = $(".register_address option:selected").val();
	createCitys(".register_address",v)
	//初始化经营地
	$(".operate_address").each(function(){
		createCitys(this,$(this).val());
	});
	
	
	var set = {
			nonSelectedText: '请选择',
			numberDisplayed: 10,
			allSelectedText:'全部'
		    };
	$("#otherCondition").multiselect(set);
	
	setTimeout(function(){initCitys();},300);
	
	var v = $("#isHaveAddress").val();
	if(v == '1'){
		$("#operateAddressTd").css("display",''); 
	}else{
		$("#operateAddressTd").css("display",'none');
	}
});

var isinit = true;
function initCitys(){
	
	initCitysByClass(".register_address ");
	initCitysByClass(".operate_address ");
	
	isinit =false;
}

function initCitysByClass(el){
	$(el).each(function(){
		var $this = $(this).next().find(".citys");
		var p = $this.attr("province");
		var c = $this.attr("city");
		var a = $this.attr("area");
		var t = $this.attr("town");
		if(p && p !='')
			$this.find("select[name=province]").val(p).trigger('change');
		if(c && c !='')
			$this.find("select[name=city]").val(c).trigger('change');
		if(a && a !='')
			$this.find("select[name=area]").val(a).trigger('change');
		if(t && t !='')
			$this.find("select[name=town]").val(t).trigger('change');
	});
}

function belocateOther(v){
	
	if(v == 1){
		hideOther();
		$("#level").prop("disabled",false);
		$("#inpark_name").prop("disabled",false);
	}
	else if(v == 2){
		hideOther();
		$("#million_building_is").prop("disabled",false);
		$("#building_name").prop("disabled",false);
	}else if(v == 3){
		$("#level").prop("disabled",false);
		$("#inpark_name").prop("disabled",false);
		$("#million_building_is").prop("disabled",false);
		$("#building_name").prop("disabled",false);
	}else{
		hideOther();
	}
	
	addRules(v);
}

function addRules(v){
	if(v == 1){
		$("#level").rules("add",{required:true});
		$("#inpark_name").rules("add",{required:true});
		
		$("#million_building_is").rules("remove","required");
		$("#building_name").rules("remove","required");
	}else if(v == 2){
		$("#million_building_is").rules("add",{required:true});
		$("#building_name").rules("add",{required:true});
		
		$("#level").rules("remove","required");
		$("#inpark_name").rules("remove","required");
	}else if(v == 3){
		$("#level").rules("add",{required:true});
		$("#inpark_name").rules("add",{required:true});
		
		$("#million_building_is").rules("add",{required:true});
		$("#building_name").rules("add",{required:true});
		
	}else{

		$("#level").rules("remove","required");
		$("#inpark_name").rules("remove","required");
		$("#million_building_is").rules("remove","required");
		$("#building_name").rules("remove","required");
	}
		
}

function createCitys(el,f){
	
	var $town =  $(el).next(".proclass").find('.citys select[name="town"]');
    var townFormat = function(info){
    $town.hide().empty();
    if(info['code']%1e4&&info['code']<7e6 && info['code'] == '120116'){	//是否为“区”且不是港澳台地区
    	$.ajax({
    		url:ctx+'/citys/getList/'+info['code'],
    		dataType:'json',
    		async: false,
    		success:function(town){
    			$town.show();
    			for(i in town){
    					$town.append('<option value="'+i+'">'+town[i]+'</option>');
    			}
    			//if(isinit)setTimeout(function(){initCitys();},100);
    		}
    	});
    }
    };
    var defaultCode = 0;
    var param = "";
    if(f == 1){
    	defaultCode = 120116;
    }else if(f == 2){
    	defaultCode = 120000;
    }
    $(el).next(".proclass").find('.citys').citys({
    	dataUrl:ctx + "/citys/getList?level="+f,
    	code:defaultCode,
    	async:false,
        onChange:function(info){
        	townFormat(info);
        }
    },function(api){
        var info = api.getInfo();
        townFormat(info);
    });
}
function createCitySelect(f){
	var select_ = [];
	select_.push('<span class="citys">');
	if(f == 1){
		select_.push('<select name="province" class="form-control"></select>');
		select_.push('<select name="city" class="form-control"></select>');
		select_.push('<select name="area" class="form-control"></select>');
		select_.push('<select name="town" class="form-control"></select>');
	}else if(f == 2){
		select_.push('<select name="province" class="form-control"></select>');
		select_.push('<select name="city" class="form-control"></select>');
		select_.push('<select name="area" class="form-control"></select>');
	}else if(f == 3){
		select_.push('<select name="province" class="form-control"></select>');
		select_.push('<select name="city" class="form-control"></select>');
	}
	
	select_.push('</span>');
	return select_.join("");
}


function hideOther(){
	$("#level,#million_building_is").prop("disabled",true);
	$("#inpark_name").prop("disabled",true);
	$("#building_name").prop("disabled",true);
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
	var nationality = $("#nationality").val();
	var businessType = $("#hangyeleixing").val();
	$("input[name=nationality]").val(nationality);
	$("input[name=businessType]").val(businessType);
	
	var sponsorTwodeputyAcommitteeType = getChecked("sponsorTwodeputyAcommitteeTypeTxt");
	$("#sponsorTwodeputyAcommitteeType").val(sponsorTwodeputyAcommitteeType);
	
	//地址信息
	var registerCitys = $(".register_address").next(".proclass").find(".citys");
	if(registerCitys){
		var province = registerCitys.find("select[name=province]").val();
		var city = registerCitys.find("select[name=city]").val();
		var area = registerCitys.find("select[name=area]").val();
		var town = registerCitys.find("select[name=town]").val();
		if(province){
			getElByName("registerAddressProvince").val(province);
		}
		if(city){
			getElByName("registerAddressCity").val(city);
		}
		if(area){
			getElByName("registerAddressDistrict").val(area);
		}
		if(town){
			getElByName("registerAddressStreet").val(town);
		}
	}
	
	var list = [];
	$(".operate_address").each(function(){
		var operateCitys = $(this).next(".proclass").find(".citys");
		var operateAddressLevel = $(this).val();
		var province = operateCitys.find("select[name=province]").val();
		var city = operateCitys.find("select[name=city]").val();
		var area = operateCitys.find("select[name=area]").val();
		var town = operateCitys.find("select[name=town]").val();
		var operateAddress = $(this).next().next("input[name=operateAddress]").val();
		
		var txt = [];
		if(operateAddressLevel){
			txt.push(operateAddressLevel);
		}
		if(province){
			txt.push(province);
		}
		if(city){
			txt.push(city);
		}
		if(area){
			txt.push(area);
		}
		if(town){
			txt.push(town);
		}
		if(operateAddress){
			txt.push("^" +operateAddress);
		}
		var one = txt.join(",");
		list.push(one);
		
	});
	var l = list.join(";");
	//console.log(l);
	//return;
	getElByName("operateAddressBigTxt").val(l);
    $.post(ctx + "/unpublic/orgedit",getJsonParams("editForm"),function(result){
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
	var url = ctx + "/unpublic/addPartymbr";
	parent.utils.e.openWin('addpartymbrWin','增加',url,"80%","80%",function(){
		window.location.reload();
	},false);
}

function removePartymbr(){
	var url = ctx + "/unpublic/removePartymbr";
	parent.utils.e.openWin('removepartymbrWin','减少',url,"80%","80%",function(){
		window.location.reload();
	},false);
}

function validate(){
	$("#editForm").validate({
	    rules: {
	      name:{
		        required: true
		      },
	      businessType: {
	        required: true
	      },
	      industryType:{
	    	  required: true
	      },
	      belocatedAddress:{
	    	  required: true
	      },
	      contactPhone:{
	    	  required: true
	      },
	      businessVolume:{
	    	  required: true,
	    	  maxlength:8,
	    	  number:true
	      },
	      jobinTotalnum:{
	    	  required: true,
	    	  maxlength:8,
	    	  digits:true
	      },
	      onScaleIs:{
	    	  required: true
	      },
	      registerAddressLevel:{
	    	  required: true
	      },
	      registerAddress:{
	    	  required: true
	      },
	      operateAddressLevel:{
	    	  required: true
	      },
	      operateAddress:{
	    	  required: true
	      },
	      sponsorName:{
	    	  required: true
	      },
	      sponsorPartymemberIs:{
	    	  required: true
	      },
	      sponsorPartyorgSecretaryIs:{
	    	  required: true
	      },
	      sponsorTwodeputyAcommitteeIs: {
		    	required: true
		    },
		    sponsorPartyorgSecretaryIs: {
		    	required:true
		    }
		    ,
		    hasSociaty:{
		    	required: true
		    },
		    hasYouthLeague:{
		    	required: true
		    },
		    hasWomenLeague:{
		    	required: true
		    }
	      
	      
	    },
	    messages: {
	    	name: "请输入名称",
	    	businessType: {
	    		required: "请选择企业类型"
		    },
		    industryType: {
		    	required: "请选择行业类型"
		    },
		    belocatedAddress: {
		    	required: "请选择企业坐落地"
		    },
		    contactPhone: {
		    	required: "请输入联系电话"
		    },
		    businessVolume: {
		    	required: "请输入年营业收入"
		    },
		    jobinTotalnum: {
		    	required: "请输入从业人员数量"
		    },
		    onScaleIs: {
		    	required: "请选择是否规模以上企业"
		    },
		    registerAddressLevel: {
		    	required: "请选择企业注册地"
		    },
		    registerAddress: {
		    	required: "请录入企业注册地"
		    },
		    operateAddressLevel: {
		    	required: "请选择企业经营地"
		    },
		    operateAddress: {
		    	required: "请录入企业经营地"
		    },
		    sponsorName: {
		    	required: "请输入出资人姓名"
		    },
		    sponsorPartymemberIs: {
		    	required: "请选择是否党员"
		    },
		    sponsorPartyorgSecretaryIs: {
		    	required: "请选择是否兼任党组织书记"
		    },
		    sponsorTwodeputyAcommitteeIs: {
		    	required: "请选择是否担任区县级<br>以上(含区县)<br/>“两代表一委员”"
		    },
		    sponsorPartyorgSecretaryIs: {
		    	required: "请选择是否兼任党组织书记"
		    },
		    hasSociaty:{
		    	required: "请选择是否有工会"
		    },
		    hasYouthLeague:{
		    	required: "请选择是否有共青团"
		    },
		    hasWomenLeague:{
		    	required: "请选择是否有妇联"
		    }
	    },
	    submitHandler:function(form){
	    	save(0);
        } 
	});
}

function getElByName(name){
	return $("input[name="+name+"]");
}