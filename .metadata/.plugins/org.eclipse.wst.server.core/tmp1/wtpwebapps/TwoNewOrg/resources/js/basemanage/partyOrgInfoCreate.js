var departmentSetting = {
	check: {
		enable: false
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'id',
			pIdKey:'parentPartyOrg'
		}
	},
	callback: {
//		beforeClick: beforeClick,
		onClick: onClick
	}
};

//function beforeClick(treeId, treeNode) {
//	var check = (treeNode && !treeNode.isParent);
//	if (!check) alert("只能选择城市...");
//	return check;
//}

function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("parentDepartMent"),
	nodes = zTree.getSelectedNodes(),
	nodesValue = "";
	nodesId = "";
	nodes.sort(function compare(a,b){return a.id-b.id;});
	for (var i=0, l=nodes.length; i<l; i++) {
		nodesValue += nodes[i].name + ",";
		nodesId += nodes[i].id + ",";
	}
	if (nodesValue.length > 0 ) {
		nodesValue = nodesValue.substring(0, nodesValue.length-1);		
		nodesId = nodesId.substring(0, nodesId.length-1);		
	}
	$("#higherDepart").attr("value", nodesValue);
	$("#higherDepartHid").attr("value", nodesId);
}
function isIE(){
	if (window.navigator.userAgent.indexOf("MSIE")>=1) 
	return true; 
	else
	return false; 
	}
function showMenu() {
	var higherDepart = $("#higherDepart");
	var higherDepartOffset = $("#higherDepart").offset();
	if(isIE())
		$("#parentPartyOrg").css({left:higherDepartOffset.left + "px", "top":(higherDepartOffset.top + higherDepart.outerHeight())/2 + "px"}).slideDown("fast");
	else
		$("#parentPartyOrg").css({left:higherDepartOffset.left + "px", top:higherDepartOffset.top + higherDepart.outerHeight() + "px"}).slideDown("fast");

	$("#parentPartyOrg").css("zIndex",999);
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#parentPartyOrg").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "parentPartyOrg" || $(event.target).parents("#parentPartyOrg").length>0)) {
		hideMenu();
	}
}




$(document).ready(function() {
	
	init();
	
	
	$("#gobackBtn").click( function(){
		
		window.history.go(-1);
	})
	
	$("#submitBtn").click(function (){
		var v = validate();
		if (v != "") {
			$.messager.alert({
				title:'警告',
				msg:'请输入' + v + "！"
			});
			
			return;
		}
		
		var name = $("#name").val();
		var partyOrgType = $("#partyOrgType").val();
		var higherDepart = $("#higherDepartHid").val();
		
		$.ajax({
			url : "../basemanage/partyorginfocreate",  
			type : "POST",
			data : {
				name : name,
				partyOrgType : partyOrgType,
				partySetUpTimeStr : $("#partySetUpTime").val(),
				partyStartTimeStr : $("#partyStartTime").val(),
				parentPartyOrg : higherDepart
			},
			success : function(data) {
				
				if (data == '2') {
					alert("系统已存在为：["+name+"]的党组织");
					return;
				}else if(data == 0){
					alert("新增党组织失败，请联系管理员！");
					return;
				}
				
				$.messager.alert("提交成功");
				
				window.location.href= '../basemanage/partyorginfoquery';
			},
			error : function(XMLHttpRequest, textStatus, errorThrown){
				debugger;
			}
		});
		
	})
	
})

function init() {
	
	$.ajax({
		url : "../basemanage/partyorginfoquery",
		type : "POST",
		data : {
			name : '',
			avail:'0'
		},
		success : function(data) {
	
			var tree = $.fn.zTree.init($("#parentDepartMent"), departmentSetting, data);
			tree.expandAll(true);
			var id = $("#idHid").val();
			var treeObj = $.fn.zTree.getZTreeObj("parentDepartMent");
			var nodes = treeObj.getNodeByParam('id', id);
			if(nodes != null){
				$("#higherDepart").attr("value", nodes.name);				
			}
		}
	})
}
function validate() {
	
	var name = $("#name").val();
	var higherDepart = $("#higherDepartHid").val();

	if (name == null || trim(name) == "") {
		return "名称";
	}
	if (higherDepart == null || trim(higherDepart) == "") {
		return "上级部门";
	}
	
	return "";
}

function trim(input) {
	
	return input.replace(/(^\s*)|(\s*$)/g,'');
}
