<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h1>Spring + Spring MVC + Mybatis(Embedded database\Mysql)  注解+Java实现方式，无XML</h1>

<input id="start" value=""/>
<button onclick="startThread()">开启线程</button>

<script type="text/javascript" src="resources/js/jquery.min.js"></script>
<script>
function startThread(){
	$.ajax({
		url : "http://localhost:8080/grabclass/threads/cyclicBarrier",
		type : "get",
		data : {num:$("#start").val()},
		dataType : "json",
		success : function(data){
			alert("success"); // 正常弹出的话，证明，数据加载成功
		}
	});
};


</script>
</body>
</html>