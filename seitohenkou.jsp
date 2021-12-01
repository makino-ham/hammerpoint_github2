<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>xxxxxxxxxx山田太さんようこそxxxxxxxxxxx</h1>
<form action="/Hammerpoint/SeitoKanri?action=${seito.gakusekiId }" method="post">
<table>
<tr>
<th>学籍番号：</th><td><c:out value="${seito.gakusekiId}" /></td>
</tr>
<tr>
<th>生徒名：</th><td><input type="text" name="seitoName" value="${seito.seitoName }"></td>
<td><font color="red"><c:out value="${nameErrorMsg }"></c:out></font></td>
</tr>
<tr>
<th>性別：</th>
<td>
		<c:choose>
			<c:when test="${seito.gender == 0 }">男</c:when>
			<c:when test="${seito.gender == 1 }">女</c:when>
			<c:when test="${seito.gender == 2 }">その他</c:when>
		</c:choose>
</td>
</tr>
<tr>
<th>メールアドレス：</th><td><input type="email" name="mail" value="${seito.mail }"></td>
<td><font color="red"><c:out value="${mailErrorMsg }"></c:out></font></td>
</tr>
<tr>
<th>動物：</th>
<td>
<select name="doubutuSelect" style="width:200px; font-size:18px;">
	<option value="999">動物を選択してください</option>
	<c:forEach var="doubutu" items="${doubutuList}">
		<c:choose>
				<c:when test="${seito.doubutuId != doubutu.doubutuId }">
					<option  value="${doubutu.doubutuId }">${doubutu.doubutuName }</option>
				</c:when>
				<c:when test="${seito.doubutuId == doubutu.doubutuId }">
					<option value="${doubutu.doubutuId }" selected>${doubutu.doubutuName }</option>
				</c:when>
		</c:choose>
	</c:forEach>
</select>
</td>
<td><font color="red"><c:out value="${doubutuErrorMsg }"></c:out></font></td>
</tr>
<tr>
<th>クラス：</th>
<td><c:out value="${seito.className}" /></td>
</tr>
</table>
<input type="submit" value="変更">
</form>
</body>
</html>