<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>易买网-购物车</title>
</head>
<body>
<div id="header" class="wrap">
	<jsp:include page="/WEB-INF/view/common/top.jsp"/>
</div>
<div id="position" class="wrap">
		${navCode }
</div>

<div class="wrap">
		<jsp:include page="${mainPage }"/>
</div>
	
<div id="footer">
	<jsp:include page="/WEB-INF/view/common/footer.jsp"/>
</div>
</body>
</html>