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
<title>専攻登録</title>
</head>
<body>
	<p class="x">専攻</p>
	<form action = "/Hammerpoint/GakkaSenkouKanri?action=senkoutouroku" method="post">
		<input type="text" name="senkoutouroku2">
		<input type="submit" value="専攻登録">
	</form>
	<c:if test="${not empty errorMsg}">
		<p>${errorMsg}</p>
	</c:if>
</body>
</html>