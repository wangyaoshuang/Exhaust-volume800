<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<header class="bg-dark dk header navbar navbar-fixed-top-xs">
		<div class="navbar-header aside-md"> 
			<a class="btn btn-link visible-xs" data-toggle="class:nav-off-screen" data-target="#nav"><i class="fa fa-bars"></i></a> 
			<a href="#" class="navbar-brand" data-toggle="fullscreen"><img src="${ctx }/images/logo.png" class="m-r-sm">${choice }管理</a> 
			<a class="btn btn-link visible-xs" data-toggle="dropdown" data-target=".nav-user"> <i class="fa fa-cog"></i> </a> 
		</div>
		<ul class="nav navbar-nav hidden-xs">
			<li class="dropdown"> 
				
				<section class="dropdown-menu aside-xl on animated fadeInLeft no-borders lt">
					<div class="wrapper lter m-t-n-xs">
						<a href="#" class="thumb pull-left m-r"> 
							<img src="${ctx }/images/avatar.jpg" class="img-circle"> 
						</a>
						<div class="clear"> 
							<a href="#">
								<span class="text-white font-bold">@Mike Mcalidek
								</a>
							</span> 
							<small class="block">Art Director</small> 
							<a href="#" class="btn btn-xs btn-success m-t-xs">Upgrade</a> 
						</div>
					</div>
					<div class="row m-l-none m-r-none m-b-n-xs text-center">
						<div class="col-xs-4">
							<div class="padder-v"> 
								<span class="m-b-xs h4 block text-white">245</span> 
								<small class="text-muted">Followers</small> 
							</div>
						</div>
						<div class="col-xs-4 dk">
							<div class="padder-v"> 
								<span class="m-b-xs h4 block text-white">55</span>
								<small class="text-muted">Likes</small> 
							</div>
						</div>
						<div class="col-xs-4">
							<div class="padder-v"> 
								<span class="m-b-xs h4 block text-white">2,035</span> 
								<small class="text-muted">Photos</small> 
							</div>
						</div>
					</div>
				</section>
			</li>
			
		</ul>
		<ul class="nav navbar-nav navbar-right hidden-xs nav-user">
				<li class="dropdown"> 
					<a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
						<span class="thumb-sm avatar pull-left">
							<img src="${ctx }/upload/${teacher.photo }"/>
						</span>${teacher.name }
						<b class="caret"></b> 
					</a>
					<ul class="dropdown-menu animated fadeInRight">
						<span class="arrow top"></span>
						<!--<li> <a href="#"> <span class="badge bg-danger pull-right">3</span> Notifications </a> </li>-->
						<li> <a href="${ctx }/photo/photo.jsp" id="shangchuantouxiang">头像设置</a> </li>
						<li> <a href="${ctx }/teacher/password.jsp">修改密码</a> </li>
						<li> <a href="${ctx}/Teachermanage/edit?id=${teacher.id}">个人信息</a> </li>
                        <li> <a href="${ctx}/login.jsp">退出</a> </li>
					</ul>
				</li>
			</ul>
		</header>
	</body>
	</html>