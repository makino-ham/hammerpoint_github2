<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>クラス変更</title>
</head>
<body>
		<table>
				<tr>
					<th>学年：</th>
					<td>
					<form action="/Hammerpoint/ClassKanri?action=hensaku" method="post">
							<select name="gakunenSelect" style="width:60px; font-size:18px;">
									<option value="999">学年を選択してください</option>
									<c:choose>
											<c:when test="${gakunen == 1}">
													<option value="1" selected>1</option>
													<option value="2">2</option>
											</c:when>
											<c:when test="${gakunen == 2}">
													<option value="1">1</option>
													<option value="2" selected>2</option>
											</c:when>
											<c:otherwise>
													<option value="1">1</option>
													<option value="2">2</option>
											</c:otherwise>
									</c:choose>
						</select>
						</td>
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
						<form action="/Hammerpoint/ClassKanri?action=sakujo" method="post">
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
										<input type="submit" value="削除">
								</form>
				</c:when>
		</c:choose>
</body>
</html>