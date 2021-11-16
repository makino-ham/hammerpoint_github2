<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教科変更削除の検索</title>
</head>
<body>
		<form action="/Hammerpoint/KyoukaKanri?action=hensaku" method="post">
				<select name="classSelect" style="width:200px; font-size:18px;">
						<option value="999">クラスを選択してください</option>
								<c:choose>
										<c:when test="${check == 1 }">
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
				<input type="submit" value="検索"><br>
				<font color="red"><c:out value="${classErrorMsg }"/></font>
		</form>
		<c:choose>
				<c:when test="${check == 1 }">
						<form action="/Hammerpoint/KyoukaKanri?action=sakujo" method="post">
								<table border="1">
										<tr>
												<th>科目名</th><th>選択欄</th>
										</tr>
										<c:forEach var="kyouka" items="${kyoukaList}">
										<tr>
												<td><a href="./KyoukaKanri?action=${kyouka.kyoukaId}">${kyouka.kyoukaName}</a></td>
												<td align="center"><input type="checkbox" name="kyoukaId" value="${kyouka.kyoukaId }"></td>
										</tr>
										</c:forEach>
								</table>
										<font color="red"><c:out value="${checkErrorMsg }"/></font><br>
										<input type="submit" value="削除">
								</form>
				</c:when>
		</c:choose>
</body>
</html>