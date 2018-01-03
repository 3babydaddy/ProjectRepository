var method;
$(function(){
	method = $("#method").val();
	if(method == 'look'){
		$("#remarks").prop("disabled",true);
		$("#saveBtn").prop("disabled",true);
	}
});

function save(){
	myconfirm('一旦撤销组织无法恢复，您确认要撤销吗？',function(r){    
		if(r){
			$.ajax({
				url:ctx + '/unpublic/orgcancel',
				type:'post',
				data:{id:$("#mainId").val(),remarks:$("#remarks").val()},
				dataType:'json',
				success:function(result){
					if(result.status ==1){
						alertx(result.msg,function(i){
							parent.utils.e.closeWin('cancelwin');
						});
					}
					else
						alertx(result.msg);
				}
			});
		}
	});
	
}