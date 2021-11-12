<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒登録</title>
</head>
<body>
<h1>xxxxxxxxxx山田太さんようこそxxxxxxxxxxx</h1>
<form action="/Hammerpoint/SeitoKanri?action=touroku" method="post">
<table>
<tr>
<th>学籍番号</th>
<td><input type="text" name="gakuseki" value="${seito.gakusekiId }">
<c:choose>
	<c:when test="${check == 1 }">
		<font color="red"><c:out value="${gakusekiMsg }"/></font>
	</c:when>
</c:choose>
</td>
</tr>
<tr>
<th>生徒名</th>
<td><input type="text" name="seitoName" value="${seito.seitoName }">
<c:choose>
	<c:when test="${check == 1 }">
		<font color="red"><c:out value="${nameMsg }"/></font>
	</c:when>
</c:choose>
</td>
</tr>
<tr>
<th>性別</th>
<td>
<c:choose>
	<c:when test="${check == 1 }">
		<c:choose>
			<c:when test="${seito.gender == 0}">
				<input type="radio" name="gender" value="0" checked>男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="2">その他
			</c:when>
			<c:when test="${seito.gender == 1}">
				<input type="radio" name="gender" value="0">男<input type="radio" name="gender" value="1" checked>女<input type="radio" name="gender" value="2">その他
			</c:when>
			<c:otherwise>
				<input type="radio" name="gender" value="0">男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="2" checked>その他
			</c:otherwise>
		</c:choose>
	</c:when>
	<c:otherwise>
		<input type="radio" name="gender" value="0" checked>男<input type="radio" name="gender" value="1">女<input type="radio" name="gender" value="2">その他
	</c:otherwise>
</c:choose>
</td>
</tr>
<tr>
<th>メールアドレス：</th>
<td><input type="email" name="mail" value="${seito.mail }">
<c:choose>
	<c:when test="${check == 1 }">
		<font color="red"><c:out value="${mailMsg }"/></font>
	</c:when>
</c:choose>
</td>
</tr>
<tr>
<th>動物</th>
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
<c:choose>
	<c:when test="${check == 1 }">
		<font color="red"><c:out value="${doubutuMsg }"/></font>
	</c:when>
</c:choose>
</td>
</tr>
<tr>
<th>クラス</th>
<td>
<select name="classSelect" style="width:200px; font-size:18px;">
	<option value="999">クラスを選択してください</option>
	<c:forEach var="classRoom" items="${classList}">
		<c:choose>
				<c:when test="${seito.classId != classRoom.classId }">
					<option  value="${classRoom.classId }">${classRoom.className }</option>
				</c:when>
				<c:when test="${seito.classId == classRoom.classId }">
					<option value="${classRoom.classId }" selected>${classRoom.className }</option>
				</c:when>
		</c:choose>
	</c:forEach>
</select>
<c:choose>
	<c:when test="${check == 1 }">
		<font color="red"><c:out value="${classMsg }"/></font>
	</c:when>
</c:choose>
</td>
</tr>
</table>
<input type="submit" value="送信">
</form>
</body>
</html>