
$(document).ready(function() {
	$("#closeBtn").click (function() {
		closeWin();
	});
	$("#saveBtn").click (function() {
		if(validate()){
			if($("#editForm").form('validate')){
			//开启遮罩
			var index = layer.load(1, {
			    shade: [0.1,'#fff'] //0.1透明度的白色背景
			});
			try{
				var url = ctx + '/test/save';
				$.post(url,getJsonParams("editForm"),function(json){
					//关闭遮罩
					layer.close(index);
					if(json.status ==1){
						layer.alert(json.msg,function(){
							closeWin();
						});
					}
					else
						layer.alert(json.msg);
				});
			}catch(e){
				//关闭遮罩
				layer.close(index);
				layer.alert("发生错误:"+e.message);
			}
			}
		}

	});
});


function validate(){
	var name = $("#name").textbox('getValue'); 
 	if(name == null || name == "" ){
 		layer.alert("姓名不能为空！");
 		return false;
 	}
 	var gender = $("#gender").combobox('getValue'); 
 	if(gender == null || gender == "" ){
 		 layer.alert("性别不能为空！");
 		 return false;
	}
 	var age = $("#age").numberbox('getValue'); 
 	if(age == null || age == "" ){
 		layer.alert("年龄不能为空！");
 		return false;
 	}
 	return true;
}