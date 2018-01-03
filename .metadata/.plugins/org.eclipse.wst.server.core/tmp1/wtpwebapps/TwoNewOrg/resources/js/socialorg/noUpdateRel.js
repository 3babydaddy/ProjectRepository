var method;
$(function(){
	method = $("#method").val();
	if(method == 'look'){
		$("#remarks").prop("disabled",true);
		$("#saveBtn").prop("disabled",true);
	}
});

function save(){
	var remark = $("#remarks").val();
	if(remark == null || remark == ""){
		alert("未更新缘由不能为空");
		return;
	}
	myconfirm('确定提交吗？',function(r){    
		if(r){
			$.ajax({
				url:ctx + '/socialorg/noUpdateRel',
				type:'post',
				data:{id:$("#mainId").val(),remarks:remark},
				dataType:'json',
				success:function(result){
					if(result.status ==1){
						alertx(result.msg,function(i){
							parent.utils.e.closeWin('noupdaterelWin');
						});
					}
					else
						alertx(result.msg);
				}
			});
		}
	});
	
}