<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="box">
	<h2>商品分类</h2>
	<dl>
		<c:forEach var="bigType" items="${bigTypeList}">
			<dt>${bigType.name}</dt>
			<c:forEach var="smallType" items="${bigType.smallTypeList}">
				<dd><a href="product.action?searchProduct.bigType.id=${bigType.id}&searchProduct.smallType.id=${smallType.id}&page=1">${smallType.name}</a></dd>
			</c:forEach>
		</c:forEach>	
	</dl>
</div>

<div class="spacer"></div>

<div class="last-view">
	<h2>最近浏览</h2>
	<dl class="clearfix">
		<c:forEach var="product" items="${lately}">
			<dt><img src="${product.proPic}" class="imgs" style="width: 50px; height: 50px;" /></dt>
			<dd><a style="font-size: 12px; height: 46px; overflow: hidden; line-height: 14px" href="product_detail.action?productId=${product.id}">${product.name}</a></dd>
		</c:forEach>		
	</dl>
</div>
</body>
</html>