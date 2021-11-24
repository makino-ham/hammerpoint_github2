<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Gakka, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//リクエストスコープに保存されたgakkaNameを取得
	String gakkaname = (String) request.getAttribute("gakkaName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
	<title>学科変更</title>
</head>
<body>
	<form action="/Hammerpoint/GakkaSenkouKanri?action=${gakka.gakkaId}" method="post">
		<table>
			<tr>
				<th>元の学科名</th><td>${gakka.gakkaName}</td>
			</tr>
			<tr>
				<th>変更する学科名</th><td><input type="text" name="gakkaName" placeholder="${gakka.gakkaName}"></td>
			</tr>
		</table>
		<c:if test="${not empty errorMsg}">
			<p>${errorMsg}</p>
		</c:if>
		<input type="submit" value="送信">
	</form>
</body>
</html>