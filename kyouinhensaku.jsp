<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教員変更削除検索</title>
</head>
<body>
<center>
<h1>教員変更削除検索</h1>
<form action="/Hammerpoint/KyouinKanri?action=hensaku" method="post">
	<select name="gakkaSelect" style="width:200px; font-size:18px;">
	<option value="999">学科を選択してください</option>
	<c:choose>
			<c:when test="${check == 1 }">
				<c:forEach var="gakka" items="${gakkaList}">
				<c:choose>
					<c:when test="${gakkaId != gakka.gakkaId }">
						<option  value="${gakka.gakkaId }">${gakka.gakkaName }</option>
					</c:when>
					<c:when test="${gakkaId == gakka.gakkaId }">
						<option  value="${gakka.gakkaId }" selected>${gakka.gakkaName }</option>
					</c:when>
				</c:choose>
				</c:forEach>
			</c:when>
	<c:otherwise>
	<c:forEach var="gakka" items="${gakkaList}">
		<option  value="${gakka.gakkaId }">${gakka.gakkaName }</option>
	</c:forEach>
	</c:otherwise>
	</c:choose>
</select>
<input type="submit" value="検索">
<font color="red"><c:out value="${errorMsg }"/></font>
</form>
<c:choose>
<c:when test="${check == 1}">
<form>
	<table border="1">
		<tr><th>学科</th><th>性別</th><th>名前</th><th>教員ID</th><th>選択</th></tr>
		<c:forEach var="kyouin" items="${kyouinList }">
			<tr>
			<td><c:out value="${kyouin.gakkaName }"/></td>
			<td>
			<c:choose>
				<c:when test="${kyouin.gender == 0 }">男</c:when>
				<c:when test="${kyouin.gender == 1 }">女</c:when>
				<c:when test="${kyouin.gender == 2 }">その他</c:when>
			</c:choose>
			</td>
			<td><a href="/Hammerpoint/KyouinKanri?action=${kyouin.kyouinId }"><c:out value="${kyouin.kyouinName }"/></a></td>
			<td><c:out value="${kyouin.kyouinId }"/></td>
			<td align="center"><input type="checkbox" name="kyouinId" value="${kyouin.kyouinId }"></td>
			</tr>
		</c:forEach>
	</table>
</form>
<input type="submit" value="削除">
</c:when>
</c:choose>
</center>
</body>
</html>