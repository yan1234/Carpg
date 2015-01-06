//调用方法
//先调用loadPrivince()初始化位置地区，参数为位置区码
//然后在省份区域调用loadCity(),参数为省份的区码，城市区域调用loadArea(),参数为城市区码
function loadProvince(regionId) {
	$("#id_provSelect").html("");
	$("#id_provSelect").append("<option value=''>请选择省份");
	var jsonStr = getAddress(regionId, 0);
	for (var k in jsonStr) {
		$("#id_provSelect").append("<option value='" + k + "'>" + jsonStr[k] + "</option>");
	}
	if (regionId.length != 6) {
		$("#id_citySelect").html("");
		$("#id_citySelect").append("<option value=''>请选择城市");
		$("#id_areaSelect").html("");
		$("#id_areaSelect").append("<option value=''>请选择区域");
	} else {
		$("#id_provSelect").val(regionId.substring(0, 2) + "0000");
		loadCity(regionId);
	}
}
function loadCity(regionId) {
	$("#id_citySelect").html("");
	$("#id_citySelect").append("<option value=''>请选择城市</option>");
	if (regionId.length != 6) {
		$("#id_areaSelect").html("");
		$("#id_areaSelect").append("<option value=''>请选择区域</option>");
	} else {
		var jsonStr = getAddress(regionId, 1);
		for (var k in jsonStr) {
			$("#id_citySelect").append("<option value='" + k + "'>" + jsonStr[k] + "</option>");
		}
		if (regionId.substring(2, 6) == "0000") {
			$("#id_areaSelect").html("");
			$("#id_areaSelect").append("<option value=''>请选择区域</option>");
		} else {
			$("#id_citySelect").val(regionId.substring(0, 4) + "00");
			loadArea(regionId);
		}
	}
}
function loadArea(regionId) {
	$("#id_areaSelect").html("");
	$("#id_areaSelect").append("<option value=''>请选择区域</option>");
	if (regionId.length == 6) {
		var jsonStr = getAddress(regionId, 2);
		for (var k in jsonStr) {
			$("#id_areaSelect").append("<option value='" + k + "'>" + jsonStr[k] + "</option>");
		}
		if (regionId.substring(4, 6) != "00") {
			$("#id_areaSelect").val(regionId);
		}
	}
}
function getAddress(regionId, type) {
	var array = {};
	if (type == 0) {//省级列表
		var obj = address["0"];
		var len = obj.length;
		for (var i = 0; i < len; i++) {
			var key = obj[i][0];
			var value = obj[i][1];
			array[key] = value;
		}
	} else {
		if (type == 1) {//市级列表
			var str = regionId.substring(0, 2);
			var obj = address["1"];
			var len = obj.length;
			for (var i = 0; i < len; i++) {
				var key = obj[i][0];
				var value = obj[i][1];
				if (key.substring(0, 2) == str) {
					array[key] = value;
				}
			}
		} else {
			if (type == 2) {//区县级列表
				var str = regionId.substring(0, 4);
				var obj = address["2"];
				var len = obj.length;
				for (var i = 0; i < len; i++) {
					var key = obj[i][0];
					var value = obj[i][1];
					if (key.substring(0, 4) == str) {
						array[key] = value;
					}
				}
			}
		}
	}
	return array;
}

