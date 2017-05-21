
     // 重置查询面板
	function clearQueryFm(){
		$('#queryFm').form('clear');
	}

   function resizeGrid() {
	    $('#dg').datagrid('resize', {
	        width: function () { return document.body.clientWidth * 0.9; },
	    });
	    $('#query_panel').panel('resize', {
	        width: function () { return document.body.clientWidth * 0.9; },
	    });
	}

  $(window).resize(resizeGrid);
  
  ////时间 快速设置
 ////用于queryFm
	function setToday(formId,beginName,endName,type){
  		var date = new Date();
  		var m = (date.getMonth()+1);
  		var  curDate = date.getFullYear()+"-"+(m<10?("0"+m):m)+"-"+date.getDate();
		$("#"+formId).find(":input[name="+beginName+"]").val(curDate+" 00:00:00");
  		$("#"+formId).find(":input[name="+endName+"]").val(curDate +" 23:59:59");
  	}
  	function setTodayBefore(formId,beginName,endName,type){
  		var date = new Date();
  		var m = (date.getMonth()+1);
  		var  curDate = date.getFullYear()+"-"+(m<10?("0"+m):m)+"-"+date.getDate();
  			$("#"+formId).find(":input[name="+beginName+"]").val("");
      		$("#"+formId).find(":input[name="+endName+"]").val(curDate +" 00:00:00");
  	}
  	
  	function setTimeNull(formId,beginName,endName,type){
  			$("#"+formId).find(":input[name="+beginName+"]").val("");
      		$("#"+formId).find(":input[name="+endName+"]").val("");
  	}
	function reloadGrid(grid){
			$(".pagination-first").click();
	};
	
	
	function getNowFormatDate() {
	
	    var date = new Date();
	    var month = date.getMonth() + 1;
	    var strDate = date.getDate();
	    function checkTime(i) {
			if (i < 10) {
				i = "0" + i
			}
			return i
		}
	    var currentdate = date.getFullYear() + "-" + checkTime(month) + "-" + checkTime(strDate)
	            + " " + checkTime(date.getHours()) + ":" + checkTime(date.getMinutes()) + ":" + checkTime(date.getSeconds());
	    return currentdate;
	} 
	/**
	 *生成随机数据
	 *
	 **/
	 function generateNum(n){
	 	  if(typeof(n)=='undefined')n=3
	 	  var _data=new Date().format("yyMMddhhmm");
	      var res = _data;
	      for(var i = 0; i < n ; i ++) {
	          var num = Math.floor(Math.random()*10); //向下取整生成0--9的数字
	          res += num;
	      }
	      return res;
	 }