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

<a class="button c" href="/Hammerpoint/JikanwariKanri?action=kanri" method="post"id="absolute">←</a>

<form action="/Hammerpoint/JikanwariKanri?action=kensaku" method="post">
<center>
<select name="classSelect"required style="width:300px; font-size:20px;">
	<option value="">クラスを選択してください</option>
	<c:forEach var="room" items="${classList}">

		<c:choose>
	<c:when test="${ classId!=room.classId}">
		<option  value="${room.classId }">${room.className }</option>
	</c:when>
		<c:when test="${ classId==room.classId}">
		<option  value="${room.classId }" selected>${room.className }</option>
	</c:when>
</c:choose>

	</c:forEach>
</select>
<input type="submit" value="検索"style="width:60px; font-size:20px;">
</form>
<c:choose>
	<c:when test="${check == 1 }">

<p>時間割を登録する</p>
<br>
	<form action="/Hammerpoint/JikanwariKanri?action=touroku" method="post">
	<table border="1"style="width:300px; font-size:20px;">
		<tr><th>選択</th><th>教科</th></tr>

	<c:forEach var="kyouka" items="${kyoukaList}">
		<tr>
		<td align="center"><input type="radio" name="kyoukaRadio" required value="${kyouka.kyoukaId }"></td>
		<td><c:out value="${kyouka.kyoukaName}" /></td>
		</tr>
	</c:forEach>
	</table>
<table>
<tr>
<td><th>曜日</th></td>
<td>
	<select name="youbiSelect" value="5" required style="width:300px; font-size:20px;">
<option value="">曜日を選択してください</option>
	<c:forEach var="youbi" items="${youbiList}">
		<option  value="${youbi.youbiId }">${youbi.youbiName }</option>
	</c:forEach>
</select>
</td>
</tr>
<tr>
<td><th>授業数</th></td>
<td>
<input type="number"name="jugyousuu" required placeholder="授業数を記入する" max="32" style="width:300px; font-size:20px;">
</td>
</tr>
<tr>
<td><th>時限</th></td>
<td>
<input type="checkbox" name="jigen" value="1">1限
<input type="checkbox" name="jigen" value="2">2限
<input type="checkbox" name="jigen" value="3">3限
<input type="checkbox" name="jigen" value="4">4限
<input type="checkbox" name="jigen" value="5">5限
</td>
</tr>

</table>
<c:choose>
	<c:when test="${check == 1 }">
		<font color="red"><c:out value="${errerMsg }"/></font>
	</c:when>
</c:choose>

	<input type="submit" value="登録"style="width:60px; font-size:20px;">

	</form>
	</c:when>
</c:choose>


</center>
</body>
</html>