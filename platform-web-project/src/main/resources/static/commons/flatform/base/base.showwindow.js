 
(function($) {
	 
	$.devfrw.base.showwindow = {
			openMaxWin : function(url) {
				 openMaxWin(url);
			} 
			 
			 
	};
	
	
	function openMaxWin(url) {
		 var maxh = screen.availHeight-30;
		 var maxw = screen.availWidth-10;
		 window.open(url,'_blank','top=0,left=0,width='+maxw+',height='+maxh+',toolbar=yes,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');
	}
	
})(jQuery);
