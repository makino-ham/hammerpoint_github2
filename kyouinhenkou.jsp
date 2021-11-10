<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教員変更</title>
</head>
<body>
<h1>教員変更</h1>
<form action="/Hammerpoint/KyouinKanri?action=${kyouin.kyouinId }" method="post">
<table>
<tr>
<th>教員ID</th><td><c:out value="${kyouin.kyouinId}" /></td>
</tr>
<tr>
<th>パスワード</th><td><input type="password" name="pass" value="${kyouin.password }"></td>
</tr>
<tr>
<th>教員名</th><td>姓:<input type="text" name="kan_sei" value="${kyouin.kanSei }"></td>
			   <td>名:<input type="text" name="kan_mei" value="${kyouin.kanMei }"></td>
</tr>
<tr>
<th>ふりがな</th><td>姓:<input type="text" name="huri_sei" value="${kyouin.huriSei }"></td>
			     <td>名:<input type="text" name="huri_mei" value="${kyouin.huriMei }"></td>
</tr>
<tr>
<th>担当学科</th><td><select name="gakkaSelect" style="width:200px; font-size:18px;">
		<option value="Ji0">学科を選択してください</option>
		<c:forEach var="gk" items="${gakkaList}">
			<option  value="${gk.gakkaId }">${gk.gakkaName }</option>
		</c:forEach>
		</select></td>
</tr>
</table>
</form>
</body>
</html>