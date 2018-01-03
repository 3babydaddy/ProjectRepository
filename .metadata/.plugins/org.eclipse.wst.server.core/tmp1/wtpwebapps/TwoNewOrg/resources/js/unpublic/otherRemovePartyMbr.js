$(function(){
	
	$("#editForm").validate({
	    rules: {
	      gowhere: {
	        required: true
	      },
	      contact:{
	    	  required: true
	      }      
	      
	    },
	    messages: {
	    	gowhere: {
	    		required: "请输入去向"
		    },
		    contact: {
		    	required: "请输入联系方式"
		    }
	    },
	    submitHandler:function(form){
	    	
	    	onSubmit();
        } 
	});
});
function save(){
	$("#editForm").submit();
}

function onSubmit(){
	$.post(ctx + "/unpublic/otherRemovePartymbr",getJsonParams("editForm"),function(result){
    	$(".btn").prop('disabled',false);
    	if(result.status == 1){
        	alertx(result.msg,function(){
        			parent.utils.e.closeWin('removepartymbrWin');
        	});
    	}else{
    		alertx(result.msg);
    	}

    });
}
