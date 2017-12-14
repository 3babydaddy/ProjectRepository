<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
<title>部门结构</title>
<%@ include file="/WEB-INF/views/index/detailHeader.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx }/resources/plugins/ztree/zTreeStyle/zTreeStyle.css" />
<script type="text/javascript" src="${ctx }/resources/plugins/ztree/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/plugins/ztree/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx }/resources/plugins/ztree/jquery.ztree.exhide-3.5.min.js"></script>
</head>
<body>
		<div>
			<table align="center" style="margin: 10px;">
				<tr>
					<td>
						<form class="form-inline">
						    <input type="text" class="form-control" id="searchTxt" placeholder="输入部门名称" style="float: left;width: 150px;"/>
						  	<button type="button" id="searchBtn" class="btn btn-default" style="float: left;margin-left: 5px;">搜索</button>
						</form>
					</td>
				</tr>			
				<tr>
					<td>
						<ul id="deptShow" class="ztree"></ul>
					</td>
				</tr>			
			</table>
		</div>
		<div align="center" border="false" style="position:fixed;right:10px;bottom:10px;" id="returnDiv">
    		<div class="btn-group">
			  <button type="button" class="btn btn-default" onclick="javascript:ok();">确定</button>
			  <button type="button" class="btn btn-default" onclick="javascript:clearTxt();">清空</button>
			  <!-- <button type="button" class="btn btn-default" onclick="javascript:parent.closeLayerDialog();">关闭</button> -->
			</div>
    	</div>
<script type="text/javascript">

var setting = {
		data: {
			searchTxt: {
				title: "t"
			},
			simpleData: {
				enable: true,
				idKey:'id',
				pIdKey:'higherDepart'
			}				
		},
		callback: {
//			beforeClick: beforeClick,
			onClick: onClick
		},
		view: {
			fontCss: getFontCss
		}
	};

var key;
$(document).ready(function(){
	var index = layer.load(1, {
	  shade: [0.1,'#fff'] //0.1透明度的白色背景
	});
	init();
	key = $("#searchTxt");
	key.bind("focus", focusKey)
	.bind("blur", blurKey)
	.bind("propertychange", filter)
	.bind("input", filter);
	
	$("#searchBtn").bind('click',filter);
	
	layer.close(index);
});
function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	
	}
}

function init() {
	
	$.ajax({
		url : "${ctx}/dept/getAllDepts",
		type : "POST",
		async: false,
		data : {
			name : ''
		},
		success : function(data) {
			var tree = $.fn.zTree.init($("#deptShow"), setting, data);
			tree.expandAll(true);
			/* var treeObj = $.fn.zTree.getZTreeObj("deptShow");
			var nodes = treeObj.getNodeByParam('id', ${currentDeptId});
			if(nodes != null){
				//$("#higherDepart").attr("value", nodes.name);				
			} */
			
			 
		}
	});
}

var nodeList = [] , zTree;
function searchNode(e) {
	zTree = $.fn.zTree.getZTreeObj("deptShow");
	var value = $.trim(key.get(0).value);
	if (key.hasClass("empty")) {
		value = "";
	}
	
	if (value === "") return;
	
	updateNodes(false);
	nodeList = zTree.getNodesByParamFuzzy("name", value);
	updateNodes(true);

}
function updateNodes(highlight) {
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
	}
}
function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#416AA3", "font-weight":"bold","background":"#E0ECFF"} : {color:"#333", "font-weight":"normal","background":"none"};
}


var id = "", name = ""; 
function onClick(e, treeId, treeNode) {
	//console.log(treeNode);
	
	var parentId = treeNode.higherDepart;
	id = treeNode.id;
	name = treeNode.name;
	
}


function ok(){
	var zTree = $.fn.zTree.getZTreeObj("deptShow");
	var nodes = zTree.getSelectedNodes();
	if (nodes.length == 0) {
		alert("请至少选择一个节点");
		return;
	}
	parent.$("#${keyId}").val(id);
	parent.$("#${valueId}").val(name);
	parent.closeLayerDialog();
}

function clearTxt(){
	parent.$("#${keyId}").val("");
	parent.$("#${valueId}").val("");
	parent.closeLayerDialog();
}

var hiddenNodes=[]; //用于存储被隐藏的结点

//过滤ztree显示数据
function filter(){
  //显示上次搜索后背隐藏的结点
  zTree = $.fn.zTree.getZTreeObj("deptShow");
  zTree.showNodes(hiddenNodes);

  //查找不符合条件的叶子节点
  function filterFunc(node){
	  var value = $.trim(key.get(0).value);
      if(node.isParent||node.name.indexOf(value)!=-1) return false;
      return true;        
  };

  //获取不符合条件的叶子结点
  hiddenNodes=zTree.getNodesByFilter(filterFunc);

  //隐藏不符合条件的叶子结点
  zTree.hideNodes(hiddenNodes);
};
</script>
</body>
</html>