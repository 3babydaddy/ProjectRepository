<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>部门主要领导编辑页</title>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/bootstrap/easyui.css'/>" /> --%>
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/bootstrap-treeview/bootstrap-treeview.css'/>" />
<script src="<c:url value='/resources/plugins/bootstrap-treeview/bootstrap-treeview.js'/>"></script> --%>

</head>
<body >
		<form action="" id="editForm" class="form-horizontal">
		<input type="hidden" name="id" value="${info.id }"/>
    	<div class="panel panel-info">
		   <div class="panel-heading">
		      <h3 class="panel-title">基本信息</h3>
		   </div>
		   <div class="panel-body" align="center">
				<table class="table table-bordered" cellpadding="2" border="0" cellspacing="0" >
					<colgroup>
				 		<col width="90" />
				 		<col width="120"/>
				 		<col width="100"/>
				 		<col width="120"/>
				 		<col width="90"/>
				 		<col width="120"/>
				 	</colgroup>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>姓　　名：</td>
						<td style="text-align: left;">
							<input class="form-control" value="${info.name }" name="name" id="name" maxlength="20"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>职　　务：</td>
						<td style="text-align: left;">
							<input class="form-control" value="${info.post }" name="post" maxlength="50"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>职　　级：</td>
						<td style="text-align: left;">
							<select class="form-control" name="postLevel">
								<option value="">-请选择-</option>
								<c:forEach items="${postLevelList }" var="it">
									<option value="${it.code }" <c:if test="${info.postLevel == it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						
					</tr>
					<tr>
						<td style="text-align: right;"><font color="red">*</font>工作手机号：</td>
						<td style="text-align: left;">
							<input class="form-control" value="${info.contactTel }" name="contactTel" maxlength="11"/>
						</td>
						<td style="text-align: right;"><font color="red">*</font>类　　型：</td>
						<td style="text-align: left;">
							<select class="form-control" name="type">
								<option value="">-请选择-</option>
								<c:forEach items="${leaderLevelList }" var="it">
									<option value="${it.code }" <c:if test="${info.type eq it.code }"> selected="selected"</c:if>>${it.value }</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right;"><font color="red">*</font>所在部门：</td>
						<td style="text-align: left;">
							<%-- <input type="text" class="easyui-combotree" id="dept" data-options="url:'${ctx }/deptleader/getAllDept'"> --%>
							<%-- <input type="text" id="depName" name="depName" class="form-control" value="${info.deptId }"  onclick="$('#treeview').show()" >
							<input type="hidden" id="deptId" name="deptId" value="${info.deptId }">
							<div id="treeview" style="display:none;position: absolute;"/> --%>
							<input type="text" class="form-control" id="deptName" name="deptName" readonly="readonly" value="${info.deptName }"/>
							<input type="hidden" id="deptId" name="deptId" value="${info.deptId }" />
						</td>
						
					</tr>
				</table>
		   </div>
		</div>
			<input type="hidden" id="status" name="status" value="${info.status }" />
		</form>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
    		  <c:if test="${isQuWeiDept || 'edit' ne method}">
			  	<button type="button" class="btn btn-primary" onclick="javascript:save(0);">保存</button>
			  </c:if>
			  <c:if test="${!isQuWeiDept }">
			  	<button type="button" class="btn btn-primary" onclick="javascript:save(1);">上报</button>
			  </c:if>
			  <button type="button" class="btn btn-primary" onclick="javascript:parent.utils.e.closeWin('editwin');">关闭窗口</button>
			</div>
    	</div>
    	<div id="addpartymbrWin"></div>
    	<div id="deptwin"></div>
    	<div id="editPage"></div>
<script type="text/javascript">
$(function(){
	validate();
});
function getQueryParams() {
	var params = {};
	var fields =$('#editForm').serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 

   return   params ; 
	
}
function doSubmit(){
	
	$.ajax({
		url:"${ctx }/deptleader/save?method=${method}",
		data:getQueryParams(),
		type:'post',
		dataType:'json',
		success:function(result){
			if(result.status ==1){
				alertx(result.msg,function(i){
					parent.utils.e.closeWin('editwin');
				});
			}
			else
				alertx(result.errorMsg);
		}
	});
}


function validate(){
	$("#editForm").validate({
	    rules: {
	      name:{
		        required: true
		      },
	      post: {
	        required: true
	      },
	      postLevel:{
	    	  required: true
	      },
	      contactTel:{
	    	  required: true,
	    	  isMobile:true

	      },
	      type:{
	    	  required: true
	      }
	      
	    },
	    messages: {
	    	name: "请输入姓名",
	    	post: {
	    		required: "请输入职务"
		    },
		    postLevel: {
		    	required: "请选择职级"
		    },
		    contactTel: {
		    	required: "请输入工作手机号"
		    },
		    type: {
		    	required: "请选择类型"
		    }
	    },
	    submitHandler:function(form){
	    	doSubmit();
        } 
	});
}

function save(flag){
	if(flag == 1){
		//$("#status").val("3");
		$("#editForm").submit();
	}else if(isQuWeiDept){
		$("#status").val("2");
		$("#editForm").submit();
	}else{
		$("#status").val("1");
		$("#editForm").submit();
	}
}

</script>
<script type="text/javascript">
	
 
  		function showDept(){
	 		showDeptTree("deptId","deptName");
  			
  		}
  	</script>
</body>
</html>