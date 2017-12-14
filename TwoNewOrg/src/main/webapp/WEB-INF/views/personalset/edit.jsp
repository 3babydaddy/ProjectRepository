<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>个人设置页</title>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<link href="<c:url value='/resources/plugins/messagetip/jquery.messagetip.css'/>" rel="stylesheet">
<script src="<c:url value='/resources/plugins/messagetip/jquery.messagetip.js'/>"></script>
</head>
<body >
		<form action="" id="editForm" class="form-horizontal">
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">基本信息</h3>
		   </div>
		   <div class="panel-body border-notop" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 		<col width="90" />
				 		<col width="120"/>
				 		<col width="100"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>姓　　名：</td>
						<td style="text-align: left;">
							<input class="form-control" name="name" value="${name }"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>联系方式：</td>
						<td style="text-align: left;">
							<input class="form-control" name="tel" value="${tel}"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>原始密码：</td>
						<td style="text-align: left;">
							<input class="form-control" type="password" name="oldpassword" value="${password }"/>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>新&nbsp; 密 &nbsp;码：</td>
						<td style="text-align: left;">
							<input class="form-control" type="password" name="password" id="newpassword" value="${password }"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>重复密码：</td>
						<td style="text-align: left;">
							<input class="form-control" type="password" name="repassword" value="${password }"/>
						</td>
					</tr>
				</table>
				<button type="button" class="btn btn-primary" onclick="javascript:save();">保存</button>
		   </div>
		</div>
		
		</form>
<script type="text/javascript">
$(function(){
	$("#editForm").validate({
	    rules: {
	      name:{
		    required: true
		  },
	      tel: {
	        required: true,
	        isMobile:true
	      },
	      oldpassword:{
	    	  required:true
	      },
	      password:{
	    	  required:true,
	    	  isEasyPwd:true
	      },
	      repassword:{
	    	  equalTo:"#newpassword"
	      }
	    },
	    messages: {
	    	name: "请输入姓名.",
	        tel: {
	          required: "请输入联系方式（手机号）."
	        },
	        oldpassword:{
	        	required:"请输入原始密码."
	        },
	        password:{
	        	required:"请输入用户新密码."
	        },
	        repassword:{
	        	equalTo:"重复密码必须和新密码相同."
	        }
	    },
	    submitHandler:function(form){
	    	var p = getJsonParams("editForm");
	    	$(".btn").prop('disabled',true);
            $.post(ctx + "/personal/set",p,function(result){
            	$(".btn").prop('disabled',false);
            	if(result.status == 1){
	            		
	            	alertx(result.msg,function(){
	            		window.location.reload();
	            	});
            	}else{
            		alertx(result.msg);
            	}

            });
        }   
	});
	
	
});

function save(){
	$("#editForm").submit();
}


</script>
</body>
</html>