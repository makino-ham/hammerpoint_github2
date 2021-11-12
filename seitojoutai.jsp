<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒進級</title>
</head>
<body>
	<form action="/Hammerpoint/SeitoKanri?action=joutaiKensaku" method="post">
		<select name="classSelect" style="width:200px; font-size:18px;">
		<option value="999">クラスを選択してください</option>
		<c:choose>
			<c:when test="${check == 1  || check == 3}">
				<c:forEach var="room" items="${classList}">
				<c:choose>
					<c:when test="${classId != room.classId }">
						<option  value="${room.classId }">${room.className }</option>
					</c:when>
					<c:when test="${classId == room.classId }">
						<option  value="${room.classId }" selected>${room.className }</option>
					</c:when>
				</c:choose>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<c:forEach var="room" items="${classList}">
					<option  value="${room.classId }">${room.className }</option>
				</c:forEach>
			</c:otherwise>
		</c:choose>
		</select>
		<c:choose>
			<c:when test="${check == 4 }"><font color="red">クラスが選択されていません</font></c:when>
		</c:choose>
		<c:choose>
			<c:when test="${flag == 0 }">
				<br><input type="radio" name="flag" value="0" checked>在学中<input type="radio" name="flag" value="1">休学中<br>
			</c:when>
			<c:when test="${flag == 1 }">
				<br><input type="radio" name="flag" value="0" >在学中<input type="radio" name="flag" value="1" checked>休学中<br>
			</c:when>
			<c:otherwise>
				<br><input type="radio" name="flag" value="0" checked>在学中<input type="radio" name="flag" value="1">休学中<br>
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${check == 3 }">
				学籍番号：<input type="text" name="gakusekiId" value="${gakusekiId }">
			</c:when>
			<c:otherwise>
				学籍番号：<input type="text" name="gakusekiId">
			</c:otherwise>
		</c:choose>
		<input type="submit" value="検索"><br>
		<c:choose>
			<c:when test="${check == 2 }"><font color="red">学籍番号が正しくありません</font></c:when>
		</c:choose>
		<c:choose>
			<c:when test="${check == 5 }"><font color="red">生徒にチェックが入っていません</font></c:when>
		</c:choose>
	</form>
	<c:choose>
	<c:when test="${check == 1 || check == 3}">
	<form action="/Hammerpoint/SeitoKanri?action=joutaiTouroku" method="post">
	<table border="1">
		<tr><th>学籍番号</th><th>名前</th><th>性別</th><th>動物</th><th>選択欄</th></tr>
	<c:forEach var="seito" items="${seitoList}">
		<tr>
		<td><c:out value="${seito.gakusekiId}" /></td>
		<td><a href="./SeitoKanri?action=${seito.gakusekiId}">${seito.seitoName}</a></td>
		<td>
		<c:choose>
			<c:when test="${seito.gender == 0 }">男</c:when>
			<c:when test="${seito.gender == 1 }">女</c:when>
			<c:when test="${seito.gender == 2 }">その他</c:when>
		</c:choose>
		</td>
		<td><c:out value="${seito.doubutuName}" /></td>
		<td align="center"><input type="checkbox" name="gakusekiId" value="${seito.gakusekiId }"></td>
		</tr>
	</c:forEach>
	</table>
	<c:choose>
		<c:when test="${flag == 0 }">
			<input type="radio" name="flag" value="1" checked>休学<input type="radio" name="flag" value="2">退学
			<input type="radio" name="flag" value="3">卒業
		</c:when>
		<c:when test="${flag == 1 }">
			<input type="radio" name="flag" value="4" checked>復学<input type="radio" name="flag" value="2">退学
			<input type="radio" name="flag" value="3">卒業
		</c:when>
	</c:choose>
	<input type="submit" value="登録">
	</form>
	</c:when>
</c:choose>
</body>
</html>