function   keyLogin() { 
		if(event.keyCode=="13") 
			{   
			login();
			} 
		} 

$(document).ready(function() {
	if (self != top) { 
		
		window.top.location.href="/TwoNewOrg/";
		return;
	}
});


function isIE8(){
	var DEFAULT_VERSION = 8;
    var ua = navigator.userAgent.toLowerCase();
    var isIE = ua.indexOf("msie")>-1;
    var safariVersion;
    if(isIE){
        safariVersion =  ua.match(/msie ([\d.]+)/)[1];
        var sa = parseInt(safariVersion);
        if(safariVersion <= DEFAULT_VERSION ){
            //alert("ie8以下")
        	return true;
        }else{
            //alert("ie8以上")
        	return false;
        }
    }else{
       // alert("非ie")
    	return false;
    }
}