<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="app">
<head>
<meta charset="utf-8" />
<title>Notebook | Web Application</title>
<meta name="description"
	content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="${ctx }/css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/js\calendar/bootstrap_calendar.css"
	type="text/css" cache="false" />
	<link rel="stylesheet" href="${ctx }/css/record/record_Choose.css" type="text/css" />
<!--[if lt IE 9]> 
<script src="${ctx }/js/ie/html5shiv.js" cache="false"></script> 
<script src="${ctx }/js/ie/respond.min.js" cache="false"></script> 
<script src="${ctx }/js/ie/excanvas.js" cache="false"></script> 
<![endif]-->
<script charset="utf-8" src="${pageContext.request.contextPath }/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath }/kindeditor/lang/zh_CN.js"></script>
<script>  
        KindEditor.ready(function(K) {  
              K.create('.editor_id', {  
                uploadJson : '${ctx}/kindeditor/jsp/upload_json.jsp',  
                fileManagerJson : '${ctx}/kindeditor/jsp/file_manager_json.jsp',  
                allowFileManager : true,
                afterBlur: function(){this.sync();}
              
               
              });  
        });  
</script> 

<script type="text/javascript">
 function alert1(){
  	//每次弹出情况原来写的数据
  	$("#wenbenkuang").val("");
  	$("#tanchukuang").show();
 }
 function hide1(){
  	$("#tanchukuang").hide();
 }
 function show1(){
  	var description_fu = document.getElementsByName("description_fu");
 	var chooseSubmit = document.getElementsByName("chooseSubmit");
  	var chooseSubmitValue = chooseSubmit[0].action+"?description="+description_fu[0].value+"&teacher_id=${teacher.id}";
	chooseSubmit[0].action = chooseSubmitValue;
  	window.chooseSubmit.submit();
 }
</script>
<script src="${ctx }/js/jquery-1.8.3.min.js" cache="false"></script> 
<script type="text/javascript">
function myfun(){
	var prompt="${prompt }";
	
	if(prompt=="false"){
		alert("当前试题在试卷当中有记录，请先删除试卷！");
	}
}
window.onload = myfun;
</script>
<script type="text/javascript">
function check(){
	
	var answer=document.getElementsByName("answer")[0];
	
						if(answer.value!=''){
							
							alert1();
						}else{
							alert("答案不能为空");
						}
}
</script>

<style type="text/css">
.chose{display:none;
}
</style>
</head>
<body>
	<section class="vbox"> <jsp:include page="../header.jsp" /> <section>
	<section class="hbox stretch"> <!-- .aside --> <jsp:include
		page="../aside.jsp" /> <!-- /.aside --> <section id="content">
	<section class="vbox"> <section class="scrollable padder">
	<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
		<li><a href="index.html"><i class="fa fa-home"></i> 组卷系统</a></li>
	</ul>
	<div class="m-b-md">
		<h3 class="m-b-none">工作台</h3>
		<small>Welcome back, username</small>
	</div>
	<section class="panel panel-default">
	<div style="position:absolute;z-index:60">
	<table class="table table-hover">


		<form action="${ctx}/question/addChoose" method=post name="chooseSubmit">
			<table>
				<input type="hidden" name="chapter" value="${chapter}"/>
				<input type="hidden" name="QuestionType" value="${QuestionType}"/>
				<input type="hidden" name="QuestionLevel" value="${QuestionLevel}"/>
				<tr height="80">
					<td>题干：</td>
					<td><textarea class="editor_id" name="content" style="width:120%;height:150px;visibility:hidden;"></textarea> </td>
				</tr>
				<tr><td colspan="2"><h4 style="color:red" align="center">提示：添加选项时只需添加选项的内容即可哦</h4></td>
				
				</tr>
				<tr height="60">
					<td><input type="button" value="添加A选项" onclick="document.getElementsByClassName('chose')[0].style.display='inline'"/></td>
					<td class="chose"><textarea class="editor_id" name="chooseA" id="" style="width:120%;height:100px;visibility:hidden;"></textarea> </td>
					
				</tr>
				<tr height="60">
					<td><input type="button" value="添加B选项" onclick="document.getElementsByClassName('chose')[1].style.display='inline'"/></td>
					<td class="chose"><textarea class="editor_id" name="chooseB" style="width:120%;height:100px;visibility:hidden;"></textarea> </td>
				</tr>
				<tr height="60">
					<td><input type="button" value="添加C选项" onclick="document.getElementsByClassName('chose')[2].style.display='inline'"/></td>
					<td class="chose"><textarea class="editor_id" name="chooseC" style="width:120%;height:100px;visibility:hidden;"></textarea> </td>
				</tr>
				<tr height="60">
					<td><input type="button" value="添加D选项" onclick="document.getElementsByClassName('chose')[3].style.display='inline'"/></td>
					<td class="chose"><textarea class="editor_id" name="chooseD" style="width:120%;height:100px;visibility:hidden;"></textarea> </td>
				</tr>
				<tr height="80">
					<td>答案：</td>
					<td><textarea class="editor_id" name="answer" style="width:120%;height:50px;visibility:hidden;"></textarea> </td>
				</tr>
				<tr height="80">
					<td align="center" colspan="3">
					<input type="button" value="添加" onclick="check()"/></td>
				</tr>
			</table>
		</form>

	</table>
	</div>
	<div id="tanchukuang">
  		<div class="closeButton">
  			<a onclick="hide1()" onmouseover="changeColor()">关闭</a>
  		</div>
  		<b id="description_title">添加一段描述吧！</b>
  		<textarea id="wenbenkuang" name="description_fu"></textarea><br>
 		<input type="button" value="确定" onclick="show1()" id="okButton"/>
 		<input type="button" value="取消" onclick="hide1()" id="notOkButton"/>
 	</div>
	</section> </section> </section> </section> </section> </section> <script src="${ctx }/js/app.v2.js"></script> <!-- Bootstrap --> <!-- App -->
	<script src="${ctx }/js/charts/easypiechart/jquery.easy-pie-chart.js" cache="false"></script> 
	<script	src="${ctx }/js/charts/sparkline/jquery.sparkline.min.js"
		cache="false"></script> <script
		src="${ctx }/js/charts/flot/jquery.flot.min.js" cache="false"></script>
	<script src="${ctx }/js/charts/flot/jquery.flot.tooltip.min.js"
		cache="false"></script> <script
		src="${ctx }/js/charts/flot/jquery.flot.resize.js" cache="false"></script>
	<script src="${ctx }/js/charts/flot/jquery.flot.grow.js" cache="false"></script>
	<script src="${ctx }/js/charts/flot/demo.js" cache="false"></script> <script
		src="${ctx }/js/calendar/bootstrap_calendar.js" cache="false"></script>
	<script src="${ctx }/js/calendar/demo.js" cache="false"></script> <script
		src="${ctx }/js/sortable/jquery.sortable.js" cache="false"></script>
</body>
</html>
