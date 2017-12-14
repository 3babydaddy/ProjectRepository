<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head id="Head1">
    <base href="<%=basePath%>">
    <title>滨海新区"两新"组织信息管理系统</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/default/easyui.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/icon.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/plugins/jquery-1.11.3.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jquery.easyui.min.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/resources/js/login/login.js'/>"></script>
    <script>
    if(top!=window){
    	top.location = window.location;
    }
    function checkBrowser(){
    	if(isIE8()){
    		alert('本系统不支持IE8及以下的浏览器\n请使用其他浏览器\t【推荐使用谷歌】\n点击下方“红色”字体部分进行下载!');
    		return false;
    	}
    	
    	$("#btn").val("登录中...");
    }
    </script>
    <link href="<c:url value='/resources/css/login/alogin.css'/> " type=text/css rel=stylesheet />
	
<body>
 <form id="myForm" method="post" action="userInfoContorller/login" onsubmit="return checkBrowser();">
    <div class="Main">
    	<div class="left"></div>
        <div class="right">
        	<div class="login">
        		<h1>请登录</h1>
        		<ul>
        			<li>用户名</li>
        			<li><input type="text" class="ipt" maxlength="20" name="name"/></li>
        			<li>密码</li>
        			<li><input type="password" class="ipt" maxlength="20" name="password"/></li>
        			<li>验证码</li>
        			<li>
        			<span class="txtCode"><input type="text" class="ipt" maxlength="8"/></span>
        			<a href="" class="code">获取验证码</a>
        			</li>
        			<li><span id="msg">${error }</span></li>
        			<li class="btn"><input type="submit" id="btn" value="登 录"/></li>
        		</ul>
        	</div>
        </div>
        <div class="clear"></div>
        <div class="bottom">
                   推荐 <a href="<c:url value='/resources/file/ChromeStandalone_59.0.3071.115_Setup.exe'/>" style="color: red;">使用谷歌浏览器</a>
                   分辨率:1440 * 900
        @2017 天津市天房科技发展股份有限公司
        </div>
      </div>
    </form>
	   
</body>
</html>