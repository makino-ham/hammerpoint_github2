<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>変更画面</title>
</head>
<body>
		<table>
				<th>学科：</th><td><c:out value="${gakkaName }"/></td>
				<form action="/Hammerpoint/ClassKanri?action=${classId }" method="post">
				<tr>
				<th>専攻：</th><td><c:out value="${senkouName }"/></td>
				</tr>
				<tr>
							<th>アドバイザー：</th>
				<td>
							<select name="kyouinSelect" style="width:200px; font-size:18px;">
								<option value="99999">教員を選択してください</option>
										<c:forEach var="kyouin" items="${kyouinList}">
												<c:choose>
														<c:when test="${kyouin.kyouinId != classRoom.kyouinId }">
																<option  value="${kyouin.kyouinId }">${kyouin.kyouinName }</option>
														</c:when>
														<c:when test="${kyouin.kyouinId == classRoom.kyouinId }">
																<option  value="${kyouin.kyouinId }" selected>${kyouin.kyouinName }</option>
														</c:when>
												</c:choose>
										</c:forEach>
							</select>
				</td>
				<td><font color="red"><c:out value="${kyouinErrorMsg }"/></font></td>
				</tr>
				<tr>
						<th>学年：</th><td><c:out value="${classRoom.gakunen }"/></td>
				</tr>
				<tr>
				<th>クラス名：</th>
				<td><input type="text" name="className" value="${className }"></td>
				<td><font color="red"><c:out value="${classErrorMsg }"/></font></td>
				</tr>
			</table>
				<input type="submit" value="変更">
				</form>
</body>
</html>