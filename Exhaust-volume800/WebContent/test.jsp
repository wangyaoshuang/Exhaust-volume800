<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.1.min.js"></script>
<title>弹出输入框</title>
<script type="text/javascript">
 function alert1(){
  //每次弹出情况原来写的数据
  $("#bb").val("");
  $("#aa").show();
 }
 function hide1(){
  $("#aa").hide();
 }
 function show1(){
  var bb = $("#bb").val();
  alert("您输入了："+bb);
 }
</script>
</head>
<body>	
 <div>
  <input type="button" value="弹出" onclick="alert1()"/>
 </div>
 
 <div id="aa" style="display:none;height: auto;background-color:#D7F1FF;width: 200px;margin:0 auto;margin-top:160px">
  <div style="float:right;cursor: pointer;"><a onclick="hide1()">关闭</a></div>
  <b>请输入原因：</b><textarea id="bb"></textarea><br>
  <input type="button" value="确定" onclick="show1()"/>
  <input type="button" value="取消" onclick="hide1()"/>
 </div>
</body>
</html>