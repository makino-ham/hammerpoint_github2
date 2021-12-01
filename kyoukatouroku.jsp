<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教科登録</title>
</head>
<body>
	<form action="/Hammerpoint/KyoukaKanri?action=kensaku" method="post">
	<table>
		<tr>
			<th>教員検索：</th>
			<td>
			<select name="gakkaSelect" required style="width:200px; font-size:18px;">
					<option value="999">学科を選択してください</option>
					<c:choose>
							<c:when test="${check == 1 }">
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
							</c:when>
							<c:otherwise>
									<c:forEach var="gakka" items="${gakkaList}">
											<option  value="${gakka.gakkaId}">${gakka.gakkaName }</option>
									</c:forEach>
							</c:otherwise>
			</c:choose>
			</select>
			</td>
			<td><input type="submit" value="検索"></td>
		</tr>
	</table>
	</form><font color="red"><c:out value="${gakkaMsg }"/></font>
	<table>
		<tr>
	<c:choose>
			<c:when test="${check == 1 }">
			<th>教員選択：</th>
				<td>
					<form action="/Hammerpoint/KyoukaKanri?action=touroku" method="post">
							<select name="kyouinSelect" style="width:200px; font-size:18px;">
								<option value="99999">教員を選択してください</option>
										<c:forEach var="kyouin" items="${kyouinList}">
												<c:choose>
														<c:when test="${kyouinId != kyouin.kyouinId }">
																<option  value="${kyouin.kyouinId }">${kyouin.kyouinName }</option>
														</c:when>
														<c:when test="${kyouinId == kyouin.kyouinId }">
																<option  value="${kyouin.kyouinId }" selected>${kyouin.kyouinName }</option>
														</c:when>
												</c:choose>
										</c:forEach>
							</select>
				</td>
				<td>
							<c:choose>
									<c:when test="${check == 1 }">
										<font color="red"><c:out value="${kyouinMsg }"/></font>
									</c:when>
							</c:choose>
				</td>
		</tr>
		<tr>
				<th>クラス：</th>
					<td>
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
					</td>
					<td>
									<c:choose>
											<c:when test="${check == 1 }">
												<font color="red"><c:out value="${classMsg }"/></font>
											</c:when>
									</c:choose>
					</td>
			</tr>
			<tr>
						<th>教科名：</th>
							<td><input type="text" name="kyoukaName"></td>
							<td>
							<c:choose>
									<c:when test="${check == 1 }">
										<font color="red"><c:out value="${kyoukaMsg }"/></font>
									</c:when>
							</c:choose>
							</td>
			</tr>
	</table>
							<input type="submit" value="登録">
					</form>
			</c:when>
	</c:choose>
</body>
</html>