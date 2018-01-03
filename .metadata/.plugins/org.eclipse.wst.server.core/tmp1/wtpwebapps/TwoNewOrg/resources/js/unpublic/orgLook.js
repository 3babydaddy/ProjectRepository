$(function(){
	$("#otherCheckbox").click(function(){
		if($(this).get(0).checked)
			$("#otherTxt").prop("disabled",false);
		else
			$("#otherTxt").prop("disabled",true);
	});
	
	//初始化注册地
	var v = $(".register_address option:selected").val();
	createCitys(".register_address",v)
	//初始化经营地
	$(".operate_address").each(function(){
		createCitys(this,$(this).val());
	});
	
	
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