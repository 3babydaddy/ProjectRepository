posterProcess = function(options) {
	
	var settings = $.extend({
		script: 'fileUpload',
		scriptData : {},
		fileDesc : "只支持PNG/JPG/GIF格式文件",
		fileExt : '*.png;*.jpg;*.gif',
        callBack : function() {
        }
    }, options);
	
	var addList  = [];
	var erroList = [] ;
	
	$("#posterUpload").uploadify({
		'buttonImage' : ctx + '/resources/plugins/uploadify/browseBtn.png',
		'width':80,
		'height':24,
//		'buttonText' : '请选择文件',
		'swf': ctx + '/resources/plugins/uploadify/uploadify.swf',
        'multi': true,
        'method'   : 'post',
        'uploader' : settings.script,
        'formData': settings.scriptData,
        //'cancelImg' : ctx +'/resources/plugins/uploadify/cancel.png',
        'sizeLimit' : 10000000,
        'removeCompleted' : true,
        'auto': true,
        'fileTypeDesc' : settings.fileDesc,
        'fileTypeExts' : settings.fileExt,
        'fileObjName' : 'file',
        'onUploadSuccess' : function(file, data, response) {
        	var json = JSON.parse(data);;
            if(json.status == -1){
            	erroList.push(json.msg);
            }
            $.each(json.url,function(index,element) {
            	addList.push(element);
            });
        },
        'onQueueComplete' : function(queueData) {
        	if(erroList.length>0){
        		var erromsg = "";
        		for(var i=0;i<erroList.length;i++){
        			erromsg =erromsg + erroList[i] ;
        		}
        		alert(erromsg);
        		erroList = [] ;
        	}else{
        		alert('上传成功');
	        	if (settings.callBack != undefined && typeof(settings.callBack) == "function") {
	        		settings.callBack(addList);
	        	}
        	}
        	$("#posterUpload").uploadify('destroy');
        	$('#uploadDlg').window('close');
        }
    });
	
	$("#uploadDlg").window({
	  top: $(document).scrollTop() +150
	});
	$("#uploadDlg").show();
	$('#uploadDlg').window('open');
}
