<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒データ表示</title>
</head>
<body>
<center>
<h1>生徒データ表示</h1>
<form action="/Hammerpoint/DataKanri?action=kensaku" method="post">
	<select name="classSelect" style="width:200px; font-size:18px;">
	<option value="999">クラスを選択してください</option>
				<c:forEach var="classroom" items="${classList}">
				<c:choose>
					<c:when test="${classId != classroom.classId }">
						<option  value="${classroom.classId }">${classroom.className }</option>
					</c:when>
					<c:when test="${classId == classroom.classId }">
						<option  value="${classroom.classId }" selected>${classroom.className }</option>
					</c:when>
					<c:otherwise>
						<option  value="${classroom.classId }">${classroom.className }</option>
					</c:otherwise>
				</c:choose>
				</c:forEach>
	</select>
	<input type="submit" value="検索">
	<br><font color="red"><c:out value="${errorMsg }"/></font>
	</form>
<c:choose>
<c:when test="${check == 1}">
	<table border="1">
		<tr><th>学籍番号</th><th>名前</th><th>性別</th><th>動物</th></tr>
		<c:forEach var="seito" items="${seitoList }">
			<tr>
			<td><c:out value="${seito.gakusekiId }"/></td>
			<td><c:out value="${seito.seitoName }"/></td>
			<td>
			<c:choose>
				<c:when test="${seito.gender == 0 }">男</c:when>
				<c:when test="${seito.gender == 1 }">女</c:when>
				<c:when test="${seito.gender == 2 }">その他</c:when>
			</c:choose>
			</td>
			<td><c:out value="${seito.doubutuName }"/></td>
			</tr>
		</c:forEach>
	</table>
</c:when>
</c:choose>
</center>
</body>
</html>