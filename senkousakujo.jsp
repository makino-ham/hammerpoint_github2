<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Gakka" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
	<title>専攻削除</title>
</head>
<body>
	<a class="button c" href="/Hammerpoint/GakkaSenkouKanri?action=gakkasakujosentaku" id="absolute">←</a>
	<form action="/Hammerpoint/SenkouKanri?action=sisukansakujokanryou" method="post">
		<select name="senkou">
			<c:forEach var="Senkou" items="${senkoulist}">
				<c:set var = "flag" value = "${Senkou.senkouFlag}"></c:set>
				<c:if test = "${flag == 0}">
					<option value="${Senkou.senkouId}">${Senkou.senkouName}</option>
				</c:if>
			</c:forEach>
		</select>
		<input type="submit" value="決定">
		<c:if test="${not empty errorMsg}">
			<p>${errorMsg}</p>
		</c:if>
	</form>
</body>
</html>