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

$.fn.colsSUM = function(colName) {
	var rows = this.datagrid('getRows')
	var sum = 0;
	for ( var i = 0; i < rows.length; i++) {
		sum += rows[i][colName];
	}
	return sum;
};


var Common = {

//EasyUI用DataGrid用日期格式化
TimeFormatter: function (value, rec, index) {
    if (value == undefined) {
        return "";
    }
    if(jQuery.isNumeric(value)){
    	return new Date(value).format("yyyy-MM-dd hh:mm:ss");
    }
    /*json格式时间转js时间格式*/
    value = value.substr(1, value.length - 2);
    var obj = eval('(' + "{Date: new " + value + "}" + ')');
    var dateValue = obj["Date"];
    if (dateValue.getFullYear() < 1900) {
        return "";
    }
    var val = dateValue.format("yyyy-MM-dd hh:mm:ss");
    return val.substr(11, 5);
},
DateTimeFormatter: function (value, rec, index) {
	
    if (value == undefined || value == "" || value =="null") {
        return "";
    }
    if(jQuery.isNumeric(value)){
    	return new Date(value).format("yyyy-MM-dd hh:mm:ss");
    }
    /*json格式时间转js时间格式*/
    value = value.substr(1, value.length - 2);
    var obj = eval('(' + "{Date: new " + value + "}" + ')');
    var dateValue = obj["Date"];
    if (dateValue.getFullYear() < 1900) {
        return "";
    }

    return dateValue.format("yyyy-MM-dd hh:mm:ss");
},

//EasyUI用DataGrid用日期格式化
DateFormatter: function (value, rec, index) {
	if (value == undefined || value == "" || value =="null") {
        return "";
    }
    if(jQuery.isNumeric(value)){
    	return new Date(value).format("yyyy-MM-dd");
    }
    /*json格式时间转js时间格式*/
    value = value.substr(1, value.length - 2);
    var obj = eval('(' + "{Date: new " + value + "}" + ')');
    var dateValue = obj["Date"];
    if (dateValue.getFullYear() < 1900) {
        return "";
    }

    return dateValue.format("yyyy-MM-dd");
}
};


function disableAllChecked(tableId){
	$("#"+tableId+" table thead tr").eq(0).find(" th.check-all").removeClass("check-btn").removeClass("check-all").prop("style","width:30px;");
}

function disableRowChecked(tableId,index){
	$("#"+tableId+" table tbody tr").eq(index).find(" td.check-row").removeClass("check-btn").removeClass("check-row").prop("style","width:30px;");
}