<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="app">
<head>
	<meta charset="utf-8" />
	<title>Notebook | Web Application</title>
	<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<link rel="stylesheet" href="${ctx }/css/app.v2.css" type="text/css" />
	<link rel="stylesheet" href="${ctx }/js/calendar/bootstrap_calendar.css" type="text/css" cache="false" />
	<link rel="stylesheet" href="${ctx }/css/regulation_form.css" type="text/css" />
	<script src="${ctx}/js/jquery.js" type=text/javascript></script>
	<script>
		function tijiao(){
			var form_uploadImg=document.getElementsByName("form_uploadImg")[0];
			form_uploadImg.submit();
		}
	</script>
</head>
<body>
	<section class="vbox"> <jsp:include page="../header.jsp" /> <section>
		<section class="hbox stretch"> <!-- .aside --> 
			<jsp:include	page="../aside.jsp" /> <!-- /.aside --> 
			<section id="content">
				<section class="vbox"> <section class="scrollable padder">
					<ul class="breadcrumb no-border no-radius b-b b-light pull-in">
						<li><a href="index.html"><i class="fa fa-home"></i>组卷系统</a></li>
					</ul>
					<div class="m-b-md">
						<h3 class="m-b-none">组卷系统</h3>
						<small>Welcome back,${teacher.name}</small>
					</div>
					<section> 
					<form action="${ctx }/exam/editregulation" name="form_uploadImg" method="post" enctype="multipart/form-data">
						<div class="tjcolor">
							<span id="nspan">设置试卷不重题的年数：</span>
							<input id="nian" name="repeat" value="${regulation }"/>
							<a id="tijiao" onclick="tijiao()">提交</a>
						</div>
					</form>
</section> 
</section> 
</section> 
</section> 
</section> 
</section> 
<script src="${ctx }/js/app.v2.js"></script> <!-- Bootstrap --> <!-- App -->
<script src="${ctx }/js/charts/easypiechart/jquery.easy-pie-chart.js" cache="false"></script> 
<script	src="${ctx }/js/charts/sparkline/jquery.sparkline.min.js" cache="false"></script> 
<script	src="${ctx }/js/charts/flot/jquery.flot.min.js" cache="false"></script>
<script src="${ctx }/js/charts/flot/jquery.flot.tooltip.min.js" cache="false"></script> 
<script src="${ctx }/js/charts/flot/jquery.flot.resize.js" cache="false"></script>
<script src="${ctx }/js/charts/flot/jquery.flot.grow.js" cache="false"></script>
<script	src="${ctx }/js/calendar/bootstrap_calendar.js" cache="false"></script>
<script src="${ctx }/js/sortable/jquery.sortable.js" cache="false"></script>
</body>
</html>
