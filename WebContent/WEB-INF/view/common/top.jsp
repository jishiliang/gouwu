<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link type="text/css" rel="stylesheet" href="css/style.css" />
	<title>Insert title here</title>
	<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
	<script type="text/javascript" src="js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function logout(){
			if(confirm("您确定要退出系统吗？")){
				window.location.href = "user_logout.action";
			}
		}
		
		function checkLogin(){
			if("${currentUser.userName}" == ""){
				alert("请先登录!");
			} else{
				window.location.href = "shopping_list.action";
			}
		}
	</script>
</head>
<body>

<div id="logo">
	<!-- <img src="images/logo.gif" /> -->
</div>
<div class="help">
	<c:choose>
		<c:when test="${not empty currentUser }">
			<a href="shopping_list.action" class="shopping">购物车(${shoppingCar.shoppingCarItems == null? 0 : shoppingCar.shoppingCarItems.size()}件商品)</a>
			<a href="user_userCenter.action">${currentUser.userName }</a>
			<a href="javascript:logout()">注销</a>
			<a href="comment_list.action">留言</a>		
		</c:when>
		<c:otherwise>
			<a href="javascript:checkLogin()" class="shopping">购物车</a>
			<a href="user_toLoginView.action">登录</a>
			<a href="user_toRegisterView.action">注册</a>
			<a href="comment_list.action">留言</a>		
		</c:otherwise>
	</c:choose>
	

	<form action="product.action" method="get">
		<input type="text" id="txtSearch" name="searchProduct.name"
			onkeyup="" autocomplete="off" value="${searchProduct.name}"  /> <input
			type="submit" id="cmdSearch"  value="搜索" /><br/>
		<div id="suggest" style="width: 200px"></div>
	</form>
</div>

<div class="navbar">
	<ul class="clearfix">
		<li class="current"><a href="index.jsp">首页</a></li>
		<c:forEach var="bigType" items="${bigTypeList}">
			<li><a href="product.action?searchProduct.bigType.id=${bigType.id}&page=1">${bigType.name}</a></li>
		</c:forEach>
	</ul>
</div>


<div id="childNav">
	<div class="wrap">
		<ul class="clearfix">
			<c:forEach var="tag" items="${tagList}" varStatus="status">
				<li class="${status.first?'first' : status.last?'last':'' }"><a href="${tag.url}" target="_blank">${tag.name}</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
</body>
</html>