function ifNullShowHeng(value,row,index){
	if(value==null||value==undefined||value==""){
		return "－";
	}
	return value;
}

function getJsonParams(formid){
	var params = {};
	var fields =$('#' + formid).serializeArray(); //自动序列化表单元素为JSON对象
	$.each( fields, function(i, field){
		params[field.name] = field.value; //设置查询参数
	}); 

   return   params ; 
}

function showDeptTree(keyId,valueId){
	var url = ctx + "/dept/onlyAllDept?keyId="+keyId+"&valueId=" + valueId;
	openLayerDialog('部门列表','300','500',url);
}
//TODO
function showOrgDeptTree(){
	var url = ctx + "/dept/hasOrgDept?keyId="+keyId+"&valueId=" + valueId;
	openLayerDialog('部门列表','300','500',url);
}
//TODO
function showPartyOrgTree(){
	var url = ctx + "/dept/onlyPartyOrg?keyId="+keyId+"&valueId=" + valueId;
	openLayerDialog('党组织列表','300','500',url);
}
//TODO
function showOrgPartyOrgTree(){
	var url = ctx + "/dept/hasOrgPartyOrg?keyId="+keyId+"&valueId=" + valueId;
	openLayerDialog('党组织列表','300','500',url);
}


function showOrgPartyTree(){
	
}

Date.prototype.format = function(format) {
    var o = {
        "M+": this.getMonth() + 1, // month
        "d+": this.getDate(), // day
        "h+": this.getHours(), // hour
        "m+": this.getMinutes(), // minute
        "s+": this.getSeconds(), // second
        "q+": Math.floor((this.getMonth() + 3) / 3), // quarter
        "S": this.getMilliseconds()
        // millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1, (this.getFullYear() + "")
            .substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};
