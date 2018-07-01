<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ssh商城</title>
</head>
<body>
<div id="header" class="wrap">
	<jsp:include page="common/top.jsp"/>
</div>

<div id="main" class="wrap">
	<div class="lefter">
		<jsp:include page="common/left.jsp"/>
	</div>
	<div class="main">
			<div class="price-off ">
				<h2>今日特价</h2>
				<ul class="product clearfix">
					<c:forEach var="productSpecial" items="${productSpecialList}">
						<li>
							<dl>
								<dt><a href="product_detail.action?productId=${productSpecial.id}" target="_blank"><img src="${productSpecial.proPic}" /></a></dt>
								<dd class="title"><a href="product_detail.action?productId=${productSpecial.id}" target="_blank">${productSpecial.name}</a></dd>
								<dd class="price">${productSpecial.price}</dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="side">

				<div class="news-list">
					<h4>最新公告</h4>
					<ul>
						<c:forEach var="notice" items="${noticeList}">
							<li><a href="notice_detail.action?noticeId=${notice.id}" target="_blank">${notice.title }</a></li>
						</c:forEach>
					</ul>
				</div>
				<div class="spacer"></div>
				<div class="news-list">
					<h4>新闻动态</h4>
					<ul>
						<c:forEach var="news" items="${newsList}">
							<li><a href="news_detail.action?newsId=${news.id}" target="_blank">${news.title }</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<div class="spacer clear"></div>
			
			<div class="hot">
				<h2>热卖推荐</h2>
				<ul class="product clearfix">
					<c:forEach var="productHot" items="${productHotList}">
						<li>
							<dl>
								<dt><a href="product_detail.action?productId=${productHot.id}" target="_blank"><img src="${productHot.proPic}" /></a></dt>
								<dd class="title"><a href="product_detail.action?productId=${productHot.id}" target="_blank">${productHot.name}</a></dd>
								<dd class="price">${productHot.price}</dd>
							</dl>
						</li>
					</c:forEach>
				</ul>
			</div>
			
		</div>
		<div class="clear"></div>
</div>

&nbsp;
<div id="footer">
	<jsp:include page="common/footer.jsp"/>
</div>
</body>
</html>