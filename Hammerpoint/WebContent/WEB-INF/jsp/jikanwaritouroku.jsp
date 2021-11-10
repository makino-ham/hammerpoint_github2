<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
<title>時間割登録</title>
</head>
<body>
<h1>${account.name }　さん　ようこそ</h1>
<p>登録</p>
<a class="button c" href="/Hammerpoint/JikanwariKanri?action=kanri" method="post"id="absolute">←</a>

<form action="/Hammerpoint/JikanwariKanri?action=touroku" method="post">
<center>
<select name="classSelect" style="width:200px; font-size:18px;">
	<option value="J00">クラスを選択してください</option>
	<c:forEach var="room" items="${classList}">
		<option  value="${room.classId }">${room.className }</option>
	</c:forEach>
</select>
<input type="submit" value="検索">
</form>
<c:choose>
	<c:when test="${check == 1 }">
	<form>
	<table border="1">
		<tr><th>選択</th><th>教科</th></tr>
	<c:forEach var="kyouka" items="${kyoukaList}">
		<tr>
		<td align="center"><input type="radio" name="kyoukaRadio" value="${kyouka.kyoukaId }"></td>
		<td><c:out value="${kyouka.kyoukaName}" /></td>
		</tr>
	</c:forEach>
	</table>
	</form>
	</c:when>
</c:choose>
</center>

</body>
</html>