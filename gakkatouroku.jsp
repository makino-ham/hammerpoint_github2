<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//リクエストスコープに保存されたエラーメッセージを取得
	String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
<title>学科登録</title>
</head>
<body>
	<p class="x">学科</p>
	<form action = "/Hammerpoint/GakkaSenkouKanri?action=touroku" method="post">
		<input type="text" name="gakkatouroku">
		<input type="submit" value="学科登録"><br>
	</form>
	<c:if test="${not empty errorMsg}">
		<p>${errorMsg}</p>
	</c:if>
</body>
</html>