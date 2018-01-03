<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<script src="${ctx}/resources/plugins/form/jquery.form.js"></script>
	<style>
	.btn_upload{position: relative;overflow: hidden;margin-right: 4px;display:inline-block;*display:inline;padding:4px 10px 4px;font-size:14px;line-height:18px;*line-height:20px;color:#fff;text-align:center;vertical-align:middle;cursor:pointer;background-color:#5bb75b;border:1px solid #cccccc;border-color:#e6e6e6 #e6e6e6 #bfbfbf;border-bottom-color:#b3b3b3;-webkit-border-radius:4px;-moz-border-radius:4px;border-radius:4px;margin-bottom: 5px;}
.btn_upload> input {position: absolute;top: 0; right: 0;margin: 0;border: solid transparent;opacity: 0;filter:alpha(opacity=0); cursor: pointer;}
	
	</style>
    <div id="uploadDlg" class="easyui-window" title="上传附件"
	    data-options="iconCls:'icon-save',modal:true,collapsible:false,minimizable:false,maximizable:false,closed:true" 
	    style="width:250px;height:100px;padding:10px;display: none;">
	    <form method="post" enctype="multipart/form-data" id="fileUploadForm">
		<div id="up_btn" class="btn_upload">
			<span>上传文件</span>
			<input id="fileName" type="file" name="fileName">
	  	</div>
	  	<input type="hidden" name="modelTbId" id="modelTbId" />
	  	<input type="hidden" name="model" id="model" />
	  
	  	</form>
	</div>
	<script type="text/javascript">
	
	function posterProcess1(options) {
		var settings = $.extend({
			script: 'fileUpload',
			scriptData : {},
			fileDesc : "只支持PNG/JPG/GIF格式文件",
			fileExt : '*.png;*.jpg;*.gif',
	        callBack : function() {
	        	
	        }
	    }, options);
		
		var status,btn;
		var isupload=false;
		$('#fileName').off('change').on('change', function(){
			var file = $(this).val();
			if(file == ""){
				alert("请选择上传文件！");
				return;
			}
			
			if(settings.fileExt != "*.*"){

				var ext = file.substring(file.indexOf("."));
				if($.inArray("*"+ext,settings.fileExt.split(";")) == -1){
					alert(settings.fileDesc);
					return;
				}
				
			}
			btn = $(this).parent();
			var fileName = $(this).val().split('\\').pop();
			isupload = true;
			$("#fileUploadForm").attr("action",settings.script);
			$("#modelTbId").val(settings.scriptData.modelTbId);
			$("#model").val(settings.scriptData.model);
	        $("#fileUploadForm").ajaxForm({
				beforeSubmit:function(formData, jqForm, options){
					btn.hide();
				}, 
				success:function(data){
					btn.show();
					alert('上传成功');
					$('#uploadDlg').window('close');
					
				}, 
				error:function(){
					btn.show();
				} 
				}).submit();
	        
		});
		showUploadWin(settings.callBack);
	}
	
	function showUploadWin(callBack){
		$("#uploadDlg").window({
			  top: $(document).scrollTop() +150,
			  onClose:function(){
				  callBack();
			  }
			});
			$("#uploadDlg").show();
			$('#uploadDlg').window('open');
	}
	</script>
</html>
