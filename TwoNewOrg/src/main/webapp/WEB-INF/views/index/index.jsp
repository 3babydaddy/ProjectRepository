<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>滨海新区"两新"组织信息管理系统</title>
    <script type="text/javascript">	
	 var _menus = ${menus};
	</script>
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/index/default.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/gray/easyui.css'/>" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/resources/plugins/jeasyui/themes/icon.css'/>" />
	<script type="text/javascript" src="<c:url value='/resources/plugins/jquery-1.11.3.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/jquery.easyui.min.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/jeasyui/easyui-lang-zh_CN.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/index/index.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/index/public.js?'/>"></script>
	<script type="text/javascript" src="<c:url value='/resources/plugins/layer/layer.js'/>"></script>
	<style type="text/css">.ic{margin-left: 20px;}</style>
</head>

<body class="easyui-layout" style="overflow-y: hidden"  scroll="no">
<noscript>
    <div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="<c:url value='/resources/images/index/noscript.gif'/>" alt='抱歉，请开启脚本支持！' /> </div>
    </noscript>
	<div region="north" split="true" border="false" style="overflow: hidden; height: 90px; background: url(./resources/images/tf/header.png) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体"> 
        <span style="float:right; padding-right:20px;line-height: 90px;" class="head">欢迎 ${showname} 
        <!-- <a href="#" id="editpass">修改密码</a> --> 
        <a href="${ctx }/index" class="ic" title="主页"><img src="<c:url value='/resources/images/tf/home-(2).png'/>"/></a>
        <a href="javascript:alert('暂未提供');" class="ic" title="帮助"><img src="<c:url value='/resources/images/tf/help-(3).png'/>"/></a>
        <a href="logout" id="loginOut" class="ic" title="退出"><img src="<c:url value='/resources/images/tf/quit-(1).png'/>"/></a>
        </span> <span style="padding-left:10px; font-size: 16px; ">
        <%-- <img src="<c:url value='/resources/images/index/blocks.gif'/>" width="20" height="20" align="absmiddle" />demo系统</span> --%>
        <img src="<c:url value='/resources/images/tf/title.png'/>" style="margin-top: 10px;max-width: 889px;max-height: 53px;" id="title"/>
        </div>

	<div region="west" hide="true" split="true" title="导航菜单" style="width:180px;" id="west">
      <div id="nav" class="easyui-accordion" fit="true" border="false"> 
    	<!--  导航内容 --> 
	  </div>
    </div>
    
	<div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
      <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
	      <div title="欢迎使用" style="padding:20px;overflow:hidden; " >
	          <!-- <h1 style="font-size:24px;">欢迎使用滨海新区"两新"组织信息管理系统</h1> -->
	          <!-- <marquee direction="right" scrollamount="10">
	          <img alt="" src="./resources/images/index/fly.png" /></marquee> -->
	          <img src="./resources/images/tf/pic.png" style="position:absolute;width:381px;height:299px;left: 50%;top:50%;margin-left: -190px;margin-top: -150px; "/>
	      </div>
	  </div>
   </div>

	<div region="south" split="true" border="false" style="overflow: hidden; height: 40px; background: url(./resources/images/tf/footer.png) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体"></div>


	<div id="mm" class="easyui-menu" style="width: 150px;">
		<div id="mm-tabupdate" data-options="iconCls:'icon-reload'">刷新</div>
		<div class="menu-sep" data-options="iconCls:''"></div>
		<div id="mm-tabcloseall" data-options="iconCls:''">全部关闭</div>
		<div id="mm-tabcloseother" data-options="iconCls:''">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose" data-options="iconCls:'icon-no'">关闭</div>
	</div>

	<script type="text/javascript">
		$(function() {
			calcWind();
		});

		$(window).resize(function() {
			calcWind();
		});

		function calcWind() {
			var w = $(document).width();
			var sw = w - 230 - 50;
			$("#title").css("width", sw);
		}
	</script>
</body>
</html>