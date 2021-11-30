<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>クラス進級</title>
</head>
<body>
		<table>
				<tr>
					<form action="/Hammerpoint/ClassKanri?action=sinsaku" method="post">
						<th>学科：</th>
						<td>
								<select name="gakkaSelect" style="width:200px; font-size:18px;">
										<option value="999">学科を選択してください</option>
												<c:forEach var="gakka" items="${gakkaList}">
														<c:choose>
																<c:when test="${ gakka.gakkaId != gakkaId}">
																		<option  value="${gakka.gakkaId}">${gakka.gakkaName }</option>
																</c:when>
																<c:when test="${ gakka.gakkaId == gakkaId}">
																		<option  value="${gakka.gakkaId}" selected>${gakka.gakkaName }</option>
																</c:when>
														</c:choose>
												</c:forEach>
								</select>
							</td>
							<td><input type="submit" value="検索"></td>
						</form>
				</tr>
		</table>
		<font color="red"><c:out value="${ggErrorMsg }"></c:out></font>
		<c:choose>
				<c:when test="${check == 1 }">
						<form action="/Hammerpoint/ClassKanri?action=sinkyuu" method="post">
								<table border="1">
										<tr>
												<th>クラス名</th><th>選択欄</th>
										</tr>
										<c:forEach var="room" items="${classList}">
										<tr>
												<td><a href="./ClassKanri?action=${room.classId}">${room.className}</a></td>
												<td align="center"><input type="checkbox" name="classId" value="${room.classId }"></td>
										</tr>
										</c:forEach>
								</table>
										<font color="red"><c:out value="${checkErrorMsg }"/></font><br>
										<input type="submit" value="進級">
								</form>
				</c:when>
		</c:choose>
</body>
</html>