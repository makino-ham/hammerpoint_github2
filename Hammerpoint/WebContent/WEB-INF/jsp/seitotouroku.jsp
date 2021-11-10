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
<form action="/Hammerpoint/SeitoKanri?action=touroku" method="post">
<table>
<tr>
<th>学籍番号</th><td><input type="text" name="gakuseki"></td>
</tr>
<tr>
<th>生徒名</th><td><input type="text" name="seitoName"></td>
</tr>
<tr>
<th>性別</th>
<td><input type="radio" name="gender" value="0">男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="2">その他</td>
</tr>
<tr>
<th>メールアドレス：</th><td><input type="email" name="mail"></td>
</tr>
<tr>
<th>動物</th>
<td>
<select name="doubutuSelect" style="width:200px; font-size:18px;">
	<option value="J00">動物を選択してください</option>
	<c:forEach var="doubutu" items="${doubutuList}">
		<option  value="${doubutu.doubutuId }">${doubutu.doubutuName }</option>
	</c:forEach>
</select>
</td>
</tr>
<tr>
<th>クラス</th>
<td>
<select name="classSelect" style="width:200px; font-size:18px;">
	<option value="J00">クラスを選択してください</option>
	<c:forEach var="room" items="${classList}">
		<option  value="${room.classId }">${room.className }</option>
	</c:forEach>
</select>
</td>
</tr>
</table>
<input type="submit" value="送信">
</form>
</body>
</html>