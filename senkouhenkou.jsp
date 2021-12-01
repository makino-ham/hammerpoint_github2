<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Senkou, model.Gakka, java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//リクエストスコープに保存されたsenkouNameを取得
	String senkouname = (String) request.getAttribute("senkouName");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
	<title>専攻変更</title>
</head>
<body>
	<a class="button c" href="/Hammerpoint/SenkouKanri?action=senkouhenkoukensaku" id="absolute">←</a>
	<form action="/Hammerpoint/SenkouKanri?action=${senkou.senkouId}" method="post">
		<table>
			<tr>
				<th>学科</th>
				<c:set var = "gid" value = "${senkou.gakkaId}"></c:set>
				<c:choose>
					<c:when test = "${gid == 0}"><td>なし</td></c:when>
					<c:otherwise><td>${gakka.gakkaName}</td></c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>元の専攻名</th>
				<td>${senkou.senkouName}</td>
			</tr>
			<tr>
				<th>変更する専攻名</th>
				<td>
					<input type="text" name="senkouName" placeholder="${senkou.senkouName}" required="required">
				</td>
			</tr>
		</table>
		<c:if test="${not empty errorMsg}">
			<p>${errorMsg}</p>
		</c:if>
		<input type="submit" value="送信">
	</form>
</body>
</html>