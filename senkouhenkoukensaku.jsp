<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.Senkou" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
<title>専攻変更の検索</title>
</head>
<body>
	<a class="button c" href="/Hammerpoint/GakkaSenkouKanri?action=gakkahenkousentaku" id="absolute">←</a>
	<h1>専攻変更の検索</h1>
	<table border="1">
		<tr><th>専攻名</th></tr>
		<c:forEach var="Senkou" items="${senkoulist}">
			<c:set var ="flag" value ="${Senkou.senkouFlag}"></c:set>
			<c:if test = "${flag == 0}">
				<tr>
					<td><a href="./SenkouKanri?action=slnk&id=${Senkou.senkouId}">${Senkou.senkouName}</a></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
	<%--<a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=senkouhenkou">決定</a>--%>
</body>
</html>