<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
	<script type="text/javascript" src="<c:url value='/resources/js/test/testEdit.js?version=${jsversion}'/>"></script>
	<%-- <link href="<c:url value='/resources/plugins/bootstrap-3.3.5/css/bootstrap.min.css'/>" rel="stylesheet">
	<link href="<c:url value='/resources/plugins/bootstrap-3.3.5/css/bootstrap-theme.min.css'/>" rel="stylesheet"> --%>
</head>
<body >
    	<div class="panel panel-info">
		   <div class="panel-body" align="center" style="border-width:0px;" >
				<form id="editForm" action="" name="editForm" class="easyui-form" method="post" >
	               	<input type="hidden"  id="id"  name="id"  value="${users.id}">	
					<table class="table table-bordered" cellpadding="0" border="0" cellspacing="0"  height="70%" width="100%" style="margin-top:2%"   >
            		      <colgroup>
					 		<col width="15%"/>
					 		<col width="18%"/>
					 	  </colgroup>
					      <tr> 
					      	<td align="right">姓名：</td>
							<td align="left">
								<input class="easyui-textbox"  value="${users.name}" type="text" id="name" name="name" size=15/>
							</td>
						</tr>
						<tr>
			                <td align="right">性别:</td>
			                <td align="left"></input>
				                <select class="easyui-combobox" id="gender"  name="gender"   style="width:150px;" data-options="editable:false ">
					                    <option value="">请选择</option>
					                   <c:forEach items="${genderList}" var="it">
							               <c:choose>
						               			<c:when test="${it.code eq users.gender}">
						               				<option value="${it.code}" selected="selected">${it.value}</option>
						               			</c:when>
						               			<c:otherwise>
						               				<option value="${it.code }">${it.value}</option>
						               			</c:otherwise>
						               		</c:choose>
						               </c:forEach>
								</select>			                
			                </td>
							</tr>
						<tr>
		                    <td align="right">年龄：</td>
		                    <td align="left"><input class="easyui-numberspinner" value="${users.age}" type="text" id="age" name="age" size=15 data-options="validType:'maxLength[3]'"/></td>
	                    </tr>
	                    <tr>
		                    <td align="right">手机号：</td>
		                    <td align="left" ><input class="easyui-numberbox"  value="${users.tel}" type="text" id="tel" name="tel" size=15 data-options="validType:'length[11]'"/></td>  
		                 </tr>
				    	</table>
                         <a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-cancel"  id="closeBtn"    style="width:100px;height:28px;">关闭</a>
                         <a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-save" id="saveBtn"  style="width:100px;height:28px;">提交</a>
				</form>
			</div>
		</div>
<script type="text/javascript">
$.extend($.fn.validatebox.defaults.rules, {    
    length: {    
        validator: function(value, param){    
            return value.length == param[0];    
        },    
        message: '请录入 {0} 位数字.'   
    },
    maxLength : {
    	validator:function(value,param){
    		return value.length <= param[0];
    	},
    	message:'长度不能大于 {0} 位.'
    }
});  

</script>
</body>

</html>