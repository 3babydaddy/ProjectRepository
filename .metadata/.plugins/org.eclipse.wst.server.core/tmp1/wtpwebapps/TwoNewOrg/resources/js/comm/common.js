var dialog_msg_xcvbnm;
var myTrigger_xcvbnm;
var tipmsg_xcvbnm;
var dialog_msg_layer_index;
var alert_msg_xcvbnm;
function alertx(msg,callback){
	if(callback && typeof(callback) === "function"){
		alert_msg_xcvbnm = bootbox.alert(msg, callback);
    }else{
    	alert_msg_xcvbnm = bootbox.alert(msg);
    }
	
}

// 打开遮罩加载页面
function openProgress(msg){
	dialog_msg_xcvbnm = bootbox.dialog({
	    message: '<p class="text-center">'+msg+'</p>',
	    closeButton: false
	});
}

// 关闭遮罩加载页面
function closeProgress(){
	dialog_msg_xcvbnm.modal('hide');
}

function openProgressExt(msg){
	var randomDialog_index = dialog_msg_xcvbnm + Math.random();
	randomDialog_index = bootbox.dialog({
	    message: '<p class="text-center">'+msg+'</p>',
	    closeButton: false
	});
	return randomDialog_index;
}

function closeProgressExt(randomDialog_index){
	randomDialog_index.modal('hide');
}

//打开窗口
function openDialog(title,width,height,url,callback){
	
	myTrigger_xcvbnm = $.zui.modalTrigger;
	var options = {
			title:title,
			url:url,
			width:width,
			height:height,
			position:'center',
			type:'iframe'
	};
	if(callback && typeof(callback) === "function"){
        options.onHide = callback;
    }
	myTrigger_xcvbnm.show(options);
	
}


//关闭窗口
function closeDialog(){
	myTrigger_xcvbnm.close();
}

function openLayerDialog(title,width,height,url,callback){
	var w,h ;
	w = String(width).indexOf("%") !=-1 ? width : width +'px';
		
	h =  String(height).indexOf("%") !=-1 ?height : height +'px';
	var options = {
			  type: 2,
			  title: title,
			  //shadeClose: true,
			  shade: [0.5,'#333'],
			  area: [w,h],
			  content: url
			};
	if(callback && typeof(callback) === "function"){
        options.end = callback;
    }
	dialog_msg_layer_index = layer.open(options); 
}

//关闭窗口
function closeLayerDialog(){
	//myTrigger_xcvbnm.close();
	layer.close(dialog_msg_layer_index);
}
function myconfirm(msg,setting,callback){
	var length = arguments.length;
	var callback_ = callback ? callback : setting;
	var options = {
	    message: msg,
	    buttons: {
	        confirm: {
	            label: '确认',
	            className: 'btn-success'
	        },
	        cancel: {
	            label: '取消',
	            className: 'btn-danger'
	        }
	    },
	    callback: callback_
	};
	if(length === 3 && setting){
		options.buttons = {
				
				confirm: {
		            label: setting.ok,
		            className: 'btn-success'
		        },
		        cancel: {
		            label: setting.cancel,
		            className: 'btn-danger'
		        }
		};
	}
	bootbox.confirm(options);

}


function tipMsg(msg){
	tipmsg_xcvbnm = new $.zui.Messager(msg, {
	    icon: 'info-sign',
	    placement: 'center' // 定义显示位置
	}).show();
}


var formValidate = function(formId){
	if(formId){
		var form = $("#" + formId);
		var i = 0;
		form.find("input,textare,select").each(function(){
			var placeholder = $(this).attr("placeholder");
			var msg = $(this).attr("msg");
			var regx = $(this).attr("regx");
			var required = $(this).attr("required");
			var isNull = false;
			var isWrong = false;
			if(required || $(this).hasClass("required") || $(this).parent().hasClass("required") || $(this).parent().prev().hasClass("required")){
				if($(this).val() == ""){
					isNull = true;
					
				}
			}
			
			if(regx){
				if( regx == "required"){
					if($(this).val() == ""){
						isNull = true;
					}
				}
				if(!regx.test($(this).val())){
					isWrong = true;
		        }
			}
			
			if(isNull){
				tipMsg(placeholder ? placeholder + "为必填项":"该项为必填项");
				$(this).focus();
				i++;
				return false;
			}
			if(isWrong){
				tipMsg(msg ? msg : (placeholder ? placeholder +"未通过验证":"该项未通过验证"));
				$(this).focus();
				i++;
				return false;
			}
		});
		if(i > 0){
			return false;
		}
		
		return true;
	}
	return false;
}