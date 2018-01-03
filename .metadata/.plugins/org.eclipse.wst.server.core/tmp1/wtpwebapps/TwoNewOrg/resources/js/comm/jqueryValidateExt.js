jQuery.validator.addMethod("Length", function(value, element) {       
     return this.optional(element) || /^[0-9]{1,10}$/.test(value);       
}, "请输入正数，长度在1-10之间。");
jQuery.validator.addMethod("isMobile", function(value, element) {
	var length = value.length;
	var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");
//匹配密码，以字母开头，长度在8-16之间，只能包含字符、数字和下划线。      
jQuery.validator.addMethod("isPwd", function(value, element) {       
     return this.optional(element) || /^[a-zA-Z]\\w{8,16}$/.test(value);       
}, "以字母开头，长度在8-16之间，只能包含字符、数字和下划线。");
//8-16位英数组合密码
jQuery.validator.addMethod("isEasyPwd", function(value, element) {    
	return this.optional(element) || (/[a-zA-Z]+(?=\d+)|\d+(?=[a-zA-Z]+)/g.test(value)  && value.length>=8 && value.length <= 16);       
}, "请输入8-16位英数组合密码");  