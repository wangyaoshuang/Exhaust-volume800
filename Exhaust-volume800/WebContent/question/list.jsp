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
<!--[if lt IE 9]> 
<script src="${ctx }/js/ie/html5shiv.js" cache="false"></script> 
<script src="${ctx }/js/ie/respond.min.js" cache="false"></script> 
<script src="${ctx }/js/ie/excanvas.js" cache="false"></script> 
<![endif]-->
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
	<table class="table table-hover">


		<form action="${ctx}/question/toadd" method=post>
			<table>
				<tr height="80">
					<td width="100">章节：</td>
					<td><select name=chapter width="100">
							<option value="${chapter_id}">默认章节</option>
							
					</select></td>



				</tr>
				<tr height="80">
					<td>题型：</td>
					<td><select name=QuestionType>
							<option value="1">单选题</option>
							<option value="2">双选题</option>
							<option value="3">填空题</option>
							<option value="4">判断题</option>
							<option value="5">解释概念</option>
							<option value="6">简答题</option>
							<option value="7">计算题</option>
							<option value="8">证明题</option>
					</select></td>

				</tr>
				<tr height="80">
					<td>难易程度：</td>
					<td><select name=QuestionLevel>
							<option value="1">记忆</option>
							<option value="2">理解</option>
							<option value="3">简单应用</option>
							<option value="4">综合应用</option>
					</select></td>
				</tr>
				
				<tr height="80">
					<td></td>
					<td align="center" colspan="3"><input type="submit" value="确定题型"></td>
				</tr>
			</table>
		</form>

	</table>
	</section> </section> </section> </section> </section> </section> <script src="${ctx }/js/app.v2.js"></script> <!-- Bootstrap --> <!-- App -->
	<script src="${ctx }/js/charts/easypiechart/jquery.easy-pie-chart.js"
		cache="false"></script> <script
		src="${ctx }/js/charts/sparkline/jquery.sparkline.min.js"
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
