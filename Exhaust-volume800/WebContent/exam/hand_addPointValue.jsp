<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="app">
<head>
<meta charset="utf-8" />
<title>添加分值</title>
<meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<link rel="stylesheet" href="${ctx }/css/app.v2.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/js/calendar/bootstrap_calendar.css" type="text/css" cache="false" />
<link href="${ctx }/css/zujuan_kecheng.css" rel="stylesheet" type="text/css">
<script src="${ctx }/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	<c:forEach items="${types }" var="type">
		<c:set var="num" value="${0 }"/>
		<c:forEach items="${exam.sorts }" var="sort">
			<c:if test="${type.id==sort.question.type.id }">
				<c:set var="num" value="${num+1 }"/>
			</c:if>
		</c:forEach>
		<c:if test="${num!=0 }">
			$("#summarize${type.id}").attr("style","display:table-row");
			
			$("#ty${type.id}").html(${num});
		</c:if>
	</c:forEach>
});
</script>
<style type="text/css">
table{border-collapse:   separate;   border-spacing:   1px; background-color:#E6E6FA ;
}
#queding{
	width:120px;
	height:35px;
	font-size:14px;
	color:#636363;
	backgronud-color:#00E5EE

}
</style>
</head>
<body>
<section class="vbox">
	<jsp:include page="../header.jsp"/>
  	<section>
    <section class="hbox stretch"> <!-- .aside -->
    	<jsp:include page="../aside.jsp"/>
      	<!-- /.aside -->
      	<section id="content">
        	<section class="vbox">
          		<section class="scrollable padder">
            		<div class="m-b-md">
              			<h3 class="m-b-none"><a href="${ctx }/question/preview">组卷系统</a></h3>
              			<small>Welcome back, ${teacher.name }</small>
            		</div>
            		<section class="panel panel-default">
            		<form action="${ctx}/exam/addPointValue" method="post">
            		<table class="table table-hover">
            			<tr id="summarize0">
            				<td>题型</td>
            				<td>个数</td>
            				<td>每题分值</td>
            			</tr>
            			<tr id="summarize1" style="display:none">	
            				<td>单项选择</td>
            				<td id="ty1">0</td>
            				<td><input type="text" name="score1" id="score1"/></td>
            			</tr>
            			<tr id="summarize2" style="display:none">	
            				<td>多项选择</td>
            				<td id="ty2">0</td>
            				<td><input type="text" name="score2"/></td>
            			</tr>
            			<tr id="summarize3" style="display:none">	
            				<td>填空</td>
            				<td id="ty3">0</td>
            				<td><input type="text" name="score3"/></td>
            			</tr>
            			<tr id="summarize4" style="display:none">	
            				<td>判断</td>
            				<td id="ty4">0</td>
            				<td><input type="text" name="score4"/></td>
            			</tr>
            			<tr id="summarize5" style="display:none">	
            				<td>解释概念</td>
            				<td id="ty5">0</td>
            				<td><input type="text" name="score5"/></td>
            			</tr>
            			<tr id="summarize6" style="display:none">	
            				<td>简答</td>
            				<td id="ty6">0</td>
            				<td><input type="text" name="score6"/></td>
            			</tr>
            			<tr id="summarize7" style="display:none">	
            				<td>论述</td>
            				<td id="ty7">0</td>
            				<td><input type="text" name="score7"/></td>
            			</tr>
            			<tr id="summarize8" style="display:none">	
            				<td>计算</td>
            				<td id="ty8">0</td>
            				<td><input type="text" name="score8"/></td>
            			</tr>
            			<tr id="summarize9" style="display:none">	
            				<td>证明</td>
            				<td id="ty9">0</td>
            				<td><input type="text" name="score9"/></td>
            			</tr>
            			<tr id="summarize10" style="display:none">	
            				<td>其他</td>
            				<td id="ty10">0</td>
            				<td><input type="text" name="score10"/></td>
            			</tr>
            				
            			<tr>
            				<td colspan="4" align="center"><input id="queding" type="submit" value="确定"/></td>
            			</tr>
            			
            	    </table>
            	    </form>	
				    </section>
        		</section>
        	</section>
        </section>
	</section>
	</section>
	
</section>

</body>
</html>