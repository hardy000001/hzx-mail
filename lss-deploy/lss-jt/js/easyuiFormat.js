function trunc2Date(val,row){
	if(!!val){
		if(val.length > 10){
			return val.substr(0,10);
		}
	}
}

function trunc2Minute(val,row){
	if(!!val){
		if(val.length > 16){
			return val.substr(0,16);
		}
	}
}

var yesOrNoDict = {1:'是',0:'否'};
function yesOrNoFormatter(val,row,index){
	return yesOrNoDict[val];
}

function formatTip(val,row){
	 return '<span title='+val+'>'+val+'</span>'  
}

function yesOrnoFormatter(val,row){
	return 1 == val ? '是' : '否';
}

function sexFormater(val, row){
	if(row.sex == 1){
		return "<font color='blue'>"+val+"</font>";
	}else if(row.sex == 2){
		return "<font color='Fuchsia'>"+val+"</font>";
	}
	
	return val;
}



function dateFormatLong(val, row) {
	if (val == null || val == '') {
		return '';
	}
	var dt = parseToDate(val);
	return dt.format("yyyy-MM-dd hh:mm:ss");
}
function dateFormatShort(val, row) {
	if (val == null || val == '') {
		return '';
	}
	var dt = parseToDate(val);
	return dt.format("yyyy-MM-dd");
}

function parseToDate(value) {
	if (value == null || value == '') {
		return undefined;
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		if (!isNaN(value)) {
			dt = new Date(value);
		} else if (value.indexOf('/Date') > -1) {
			value = value.replace(/\/Date(−?\d+)\//, '$1');
			dt = new Date();
			dt.setTime(value);
		} else if (value.indexOf('/') > -1) {
			dt = new Date(Date.parse(value.replace(/-/g, '/')));
		} else {
			dt = new Date(value);
		}
	}
	return dt;
}

Date.prototype.format = function(format) {
	  var o = {
	    "M+" : this.getMonth() + 1, // 月
	    "d+" : this.getDate(), // 天
	    "h+" : this.getHours(), // 时
	    "m+" : this.getMinutes(), // 分
	    "s+" : this.getSeconds(), // 秒
	    "q+" : Math.floor((this.getMonth() + 3) / 3), // 刻
	    "S" : this.getMilliseconds() //毫秒
	  }
	  if (/(y+)/.test(format))
	    format = format.replace(RegExp.$1, (this.getFullYear() + "")
	        .substr(4 - RegExp.$1.length));
	  for ( var k in o)
	    if (new RegExp("(" + k + ")").test(format))
	      format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
	          : ("00" + o[k]).substr(("" + o[k]).length));
	  return format;
}

