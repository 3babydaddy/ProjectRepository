var M = {};
M.dialog7;
var utils = {
		
		 e : {
				openDialog : function(dialogId,title,url,width,height,callback){
					var winWidth = width==null?"90%":""+width;
				    var winHeight = height==null?"90%":""+height;
				    if(!winWidth.endWith("%"))winWidth+="px";
				    if(!winHeight.endWith("%"))winHeight+="px";
				    
				    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="auto"></iframe>';
					var options = {
							    title: title,    
							    width:winWidth ,    
							    height: winHeight,    
							    closed: false,    
							    cache: false,    
							    content: content,    
							    modal: true   
					};
					if(callback && typeof(callback) === "function"){
						options.onClose = callback;
					}
					if($('#'+dialogId).length <=0){
						$('body').append('<div id="'+dialogId+'"></div>')
					}
					$('#'+dialogId).dialog(options);
				},
				closeDialog:function(dialogId){
					$('#'+dialogId).dialog('close');
				},
				
				openWin: function(dialogId,title,url,width,height,callback,maximized){
					var winWidth = width==null?"90%":""+width;
				    var winHeight = height==null?"90%":""+height;
				    if(!winWidth.endWith("%"))winWidth+="px";
				    if(!winHeight.endWith("%"))winHeight+="px";
				    
				    var content = '<iframe src="' + url + '" width="100%" height="99%" frameborder="0" scrolling="auto"></iframe>';
					var options = {
							    title: title,    
							    width:winWidth ,    
							    height: winHeight,    
							    closed: false,    
							    cache: false,    
							    content: content,    
							    modal: true   
					};
					if(callback && typeof(callback) === "function"){
						options.onClose = callback;
					}
					if(maximized)
						options.maximized = maximized;
					else
						options.maximized = false;
					
					if($('#'+dialogId).length <=0){
						$('body').append('<div id="'+dialogId+'"></div>')
					}
					$('#'+dialogId).window(options);
				},
				closeWin:function(dialogId){
					$('#'+dialogId).window('close');
				}
				
		},
		y:{
			openDialog:function(title,url,width,height,callback){
				if(M.dialog7){
				    return M.dialog7.show();
				}
				var options = {
					    'style'   : 'pc',
					    'title'   : title,
					    'content' :  url,
					    'modal'   : true,
					    'contentTextAlign' : 'left',
					    'width'   : width,
					    'height'  : height,
					    'animateType' : 'linear',
					    'buttons' :{
					        '关闭' : function(){
					            M.dialog7.close();
					            if(callback && typeof(callback) === "function"){
					            	callback;
								}
					        }
					    }
					};
				
				
				M.dialog7 = jqueryAlert(options);
			}
		}
};

//字符串以xx结尾
String.prototype.endWith=function(endStr){
	  var d=this.length-endStr.length;
	  return (d>=0&&this.lastIndexOf(endStr)==d);
};
