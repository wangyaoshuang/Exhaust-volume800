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
	<script src="${ctx}/js/jquery.js" type=text/javascript></script>
	
	
	
	<link rel="stylesheet" href="${ctx }/css/course_form.css" type="text/css" />
<script type="text/javascript">
$(document).ready(function(){
	
	
	$('#zengjia').on('click', function(){
		var formData = new FormData();
		var name = document.getElementsByName("name")[0].value;
		var courseId = document.getElementsByName("c_id")[0].value;
		var chapterorder = document.getElementsByName("chapterOrder")[0].value;
		
		formData.append("name",name);    //章节名称
		formData.append("c_id",courseId);    //课程id
		formData.append("chapterOrder",chapterorder);//章节序列
		alert("增加章节名称是："+name);
			$.ajax
		    ({ //请求登录处理页
		    	url :"${ctx }/chapter/add" , 
		    	type : 'POST', 
		    	data : formData, 
		    	dataType:"text",
		        async: true,  
		        cache: false,  
		        contentType: false,  
		        processData: false, 
		    	 success: function (result) { 
		         	window.location.href="${ctx}/chapter/list"
		        	},  
		         error: function(result) { 
		         	$("#uptishi").css({"color":"red",
		         		"font-size":"10px",
		         		"font-family":"围绕雅黑",
		        		"display":"block",
		        		"float":"left",
		        		"margin-left":"13px"
		         	});
		         }
	});
	})

$('#xiugai').on('click', function(){
		var formData = new FormData();
		var chapterId = document.getElementsByName("id")[0].value;
		var name = document.getElementsByName("name")[0].value;
		var courseId = document.getElementsByName("c_id")[0].value;
		var chapterorder = document.getElementsByName("chapterOrder")[0].value;
		
		formData.append("id",chapterId);
		formData.append("name",name);    //课程名称
		formData.append("c_id",courseId);    //课程id
		formData.append("chapterOrder",chapterorder);//章节序列
		alert("修改的章节名称是："+name);
			$.ajax
		    ({ //请求登录处理页
		    	url :"${ctx }/chapter/edit" , 
		    	type : 'POST', 
		    	data : formData, 
		    	dataType:"text",
		        async: true,  
		        cache: false,  
		        contentType: false,  
		        processData: false, 
		    	 success: function (result) { 
		         	window.location.href="${ctx}/chapter/list"
		        	},  
		         error: function(result) { 
		         	$("#uptishi").css({"color":"red",
		         		"font-size":"10px",
		         		"font-family":"围绕雅黑",
		        		"display":"block",
		        		"float":"left",
		        		"margin-left":"13px"
		         	});
		         }
	});
	})
})	
</script>
</head>
<body>
	<section class="vbox"> <jsp:include page="../header.jsp" /> 
		<section>
			<section class="hbox stretch"> <!-- .aside --> 
				<jsp:include	page="../aside.jsp" /> <!-- /.aside --> 
				<section id="content">
					<section class="vbox"> 
						<section class="scrollable padder">
							<div class="location">
          			 			组卷系统
          			 		</div>
          			 		<div class="pd-20">
          			 		<c:if test="${action=='edit' }">
								<form action="${ctx }/chapter/edit" name="myform_eidt" method="post" enctype="multipart/form-data">
							</c:if> 
							<c:if test="${action!='edit' }">
								<form action="${ctx }/chapter/add" name="myform" method="post" enctype="multipart/form-data">
							</c:if> 
								<div class="shangbu">
									<div class="everyone">
									<c:if test = "${action=='edit'}">
									    <input type="hidden" name="id" value="${chapter.id}" />
									    
									</c:if>
          			 					<span>章节名称：</span>
          			 					<input type="text" name="name" value="${chapter.name }"/>
          			 				</div>
          			 				<div class="time">
										<span id="xueshi">课程id：</span>
										<select name="c_id">
											<c:forEach items="${courselist }" var="course">
											<option value="${course.id }">${course.name }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<div class="time">
										<span id="xueshi">章节序列：</span>
										<input type="text" name="chapterOrder" value="${chapter.chapterOrder }"/>
									</div>
								</div>
								
								
							<c:if test="${action=='edit' }">
								<div class="tijiao">
									<a id = "xiugai">修改</a>
								</div>
							</c:if>
							<c:if test="${action!='edit' }">
								<div class="tijiao">
									<a id ="zengjia">增加</a>
								</div>
							</c:if>
          			 	</form>
          			 		</div>
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
s