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
<form action="/Hammerpoint/SyukketuKanri?action=joukyoukensaku" method="post">
		教科：
		<select name="kyoukaSelect" style="width: 200px; font-size: 18px;">
				<option value="999">教科を選択してください</option>
						<c:forEach var="kyouka" items="${listOfAllMyKyouka}">
								<c:choose>
										<c:when test="${ kyouka.kyoukaId != kyoukaId}">
												<option value="${kyouka.kyoukaId}">${kyouka.kyoukaName }</option>
										</c:when>
										<c:when test="${ kyouka.kyoukaId == kyouka.kyoukaId}">
												<option value="${kyouka.kyoukaId}" selected>${kyouka.kyoukaName }</option>
										</c:when>
								</c:choose>
						</c:forEach>
		</select>
		学籍番号：<input type="text" name="gakusekiId" value="${gakusekiId }">
		<input type="submit" value="検索">
		<c:choose>
				<c:when test="${check == 1 }">
						<table border="1">
								<tr>
										<th rowspan="2">クラス</th><th rowspan="2">生徒名</th>
										<c:forEach var="syukketu" items="${syukketuList}">
												<th colspan="${syukketu.count }"><c:out value="${syukketu.hiduke }" /></th>
										</c:forEach>
								</tr>
								<tr>
										<c:forEach var="jigen" items="${jigenList}">
												<th><c:out value="${jigen.jigen }" />限</th>
										</c:forEach>
								</tr>
								<tr>
										<td><c:out value="${seito.className }" /></td>
										<td><c:out value="${seito.seitoName }" /></td>
										<c:forEach var="s" items="${sList}">
												<c:choose>
														<c:when test="${s.syukketu == 0 }">
																<td align="center">〇</td>
														</c:when>
														<c:when test="${s.syukketu == 1 }">
																<td align="center">×</td>
														</c:when>
														<c:when test="${s.syukketu == 2 }">
																<td align="center">-</td>
														</c:when>
												</c:choose>
										</c:forEach>
								</tr>
						</table>
				</c:when>
		</c:choose>
</form>
</body>
</html>