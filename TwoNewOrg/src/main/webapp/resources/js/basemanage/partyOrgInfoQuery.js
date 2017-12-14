var setting = {
	check: {
		enable: true,
		chkStyle: "radio",
		radioType: "all"
	},
	data: {
		simpleData: {
			enable: true,
			idKey:'id',
			pIdKey:'parentPartyOrg'
		}
	},
	view: {
		fontCss: getFont,
		nameIsHTML: true
	}
};




$(document).ready(function() {
	
	loadData();

	$("#searchBtn").click (function() {
		loadData();
	})
	
	$("#editBtn").click (function() {
		editDepartment();
	})
	
	$("#addBtn").click(function (){
		window.location.href= '../basemanage/partyorginfocreate';
	})
})

function loadData() {
	var name = $("#nameTxt").val();
	$.ajax({
		url : "../basemanage/partyorginfoquery",
		type : "POST",
		data : {
			name : name
		},
		success : function(data) {
			$.each(data,function(i,o){
				if(o.status == '0'){
					o.font = {'text-decoration':'line-through'};
					o.name = o.name + '(已撤销)';
				}
			});
			var tree = $.fn.zTree.init($("#partyOrgInfoTree"), setting, data);
			tree.expandAll(true);
		}
	})
}
function getFont(treeId, node) {
	return node.font ? node.font : {};
}


function editDepartment() {
	
     var treeObj=$.fn.zTree.getZTreeObj("partyOrgInfoTree"),
     nodes=treeObj.getCheckedNodes(true),
     value="";
     name="";
     for(var i=0;i<nodes.length;i++){
	     value+= nodes[i].id;
	     //name = nodes[i].name;
	     if(i < nodes.length-1){
	    	 value+= ","
	     }
     }
     if(value == ""){
    	 //alert("请选择部门");
    	 $.messager.alert("请选择部门");
     }else{
    	 window.location.href= '../basemanage/partyorginfomodify?depId=' + value;
     }
//     if(confirm("确认删除 节点 -- " + treeNode.name + " 吗？"))
     
}
