<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/index/include.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html >
<head>
	<title></title>
</head>
<body >
    	<div class="panel panel-info">
		   <div class="panel-body" align="center" style="border-width:0px;" >
				<form id="editForm" action="" name="editForm" class="easyui-form" method="post" >
				     
					<table class="table table-bordered" cellpadding="0" border="0" cellspacing="0"  height="70%" width="100%" style="margin-top:2%"   >
					
		               <input   type="hidden"  id="id"  name="id"  value="${users.id}">	
   		                  		
            		      <colgroup>
					 		<col width="15%"/>
					 		<col width="18%"/>
					 	  </colgroup>
					      <tr> 
			                 <td align="right">姓名:</td>
			                <td align="left">${users.name }</td>
			                </tr>
			                <tr> 
			               <td align="right">性别：</td>
							<td align="left">${users.genderStr }</td>
							</tr>
							<tr>
		                    <td align="right">年龄：</td>
		                    <td align="left">${users.age }</td>
		                    </tr>
			                <tr> 
		                    <td align="right">手机号：</td>
		                    <td align="left" >${users.tel }</td>  
			                 </tr>
                              
				    	</table>
				    	<a href="javascript:void(0)" class="easyui-linkbutton" icon="icon-cancel"  id="closeBtn"    style="width:100px;height:28px;" onclick="closeWin();">关闭</a>			    	
				</form>
			</div>
		</div>

</body>

</html>