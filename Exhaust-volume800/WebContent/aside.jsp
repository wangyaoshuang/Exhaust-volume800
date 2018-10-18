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
	<aside class="bg-dark lter aside-md hidden-print" id="nav">
        <section class="vbox">
          <header class="header bg-primary lter text-center clearfix">
            <div class="btn-group">
              <button type="button" class="btn btn-sm btn-dark btn-icon" title="New project"><i class="fa fa-plus"></i></button>
              <div class="btn-group hidden-nav-xs">
                <button type="button" class="btn btn-sm btn-primary dropdown-toggle" data-toggle="dropdown"> Switch Project <span class="caret"></span> </button>
                <ul class="dropdown-menu text-left">
                  <li><a href="#">Project</a></li>
                  <li><a href="#">Another Project</a></li>
                  <li><a href="#">More Projects</a></li>
                </ul>
              </div>
            </div>
          </header>
          <section class="w-f scrollable">
            <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0" data-size="5px" data-color="#333333"> <!-- nav -->
              <nav class="nav-primary hidden-xs">
                <ul class="nav">
                  <li class="active"> <a href="index.html" class="active"> <i class="fa fa-dashboard icon"> <b class="bg-danger"></b> </i> <span>工作台</span> </a> </li>
                 <c:forEach items="${courses }" var="course">
                  <li><a href="#layout"><i class="fa fa-columns icon"><b class="bg-warning"></b></i><span class="pull-right"><i class="fa fa-angle-down text"></i><i class="fa fa-angle-up text-active"></i></span> <span>${course.name }</span> </a>
                    <ul class="nav lt">
                    <c:forEach items="${chapters }" var="chapter">
                    	<c:if test="${chapter.course.id==course.id }">
                      		<li > <a href="${ctx }/question/findQuestionByChapter/${chapter.id }" > <i class="fa fa-angle-right"></i> <span>${chapter.name }</span> </a> </li>
                    	</c:if>
                    </c:forEach>
                    </ul>
                  </li>
                </c:forEach>
                <c:forEach items="${parentmenu }" var="parent">
                  <li><a href="#layout"><i class="fa fa-columns icon"><b class="bg-warning"></b></i><span class="pull-right"><i class="fa fa-angle-down text"></i><i class="fa fa-angle-up text-active"></i></span> <span>${parent.name }</span> </a>
                    <ul class="nav lt">
         
                    <c:forEach items="${allmenu }" var="menu">
                  
                    <c:if test="${parent.id==menu.parentMenu.id }">
                  
                      <li > <a href="${ctx}/${menu.url}" > <i class="fa fa-angle-right"></i> <span>${menu.name }</span> </a> </li>
                    </c:if>
                    </c:forEach>
                    </ul>
                  </li>
                </c:forEach>
                </ul>
              </nav>
              <!-- / nav --> </div>
          </section>
          <footer class="footer lt hidden-xs b-t b-dark">
            <div id="chat" class="dropup">
              <section class="dropdown-menu on aside-md m-l-n">
                <section class="panel bg-white">
                  <header class="panel-heading b-b b-light">Active chats</header>
                  <div class="panel-body animated fadeInRight">
                    <p class="text-sm">No active chats.</p>
                    <p><a href="#" class="btn btn-sm btn-default">Start a chat</a></p>
                  </div>
                </section>
              </section>
            </div>
            <div id="invite" class="dropup">
              <section class="dropdown-menu on aside-md m-l-n">
                <section class="panel bg-white">
                  <header class="panel-heading b-b b-light"> John <i class="fa fa-circle text-success"></i> </header>
                  <div class="panel-body animated fadeInRight">
                    <p class="text-sm">No contacts in your lists.</p>
                    <p><a href="#" class="btn btn-sm btn-facebook"><i class="fa fa-fw fa-facebook"></i> Invite from Facebook</a></p>
                  </div>
                </section>
              </section>
            </div>
            <a href="#nav" data-toggle="class:nav-xs" class="pull-right btn btn-sm btn-dark btn-icon"> <i class="fa fa-angle-left text"></i> <i class="fa fa-angle-right text-active"></i> </a>
            <div class="btn-group hidden-nav-xs">
              <button type="button" title="Chats" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#chat"><i class="fa fa-comment-o"></i></button>
              <button type="button" title="Contacts" class="btn btn-icon btn-sm btn-dark" data-toggle="dropdown" data-target="#invite"><i class="fa fa-facebook"></i></button>
            </div>
         </footer>
         </section>
      </aside>
</body>
</html>