<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>クラス登録</title>
</head>
<body>
		<table>
				<tr>
				<th>学科：</th>
				<td>
						<form action="/Hammerpoint/ClassKanri?action=kensaku" method="post">
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
		<font color="red"><c:out value="${gakkaErrorMsg }"/></font><br>
		-----------------------------------------------------------------------------
		<c:choose>
				<c:when test="${check == 1 }">
		<table>
				<th>学科：</th><td><c:out value="${gakkaName }"/></td>
				<form action="/Hammerpoint/ClassKanri?action=touroku" method="post">
				<tr>
				<th>専攻：</th>
				<td>
						<select name="senkouSelect" required style="width:200px; font-size:18px;">
								<option value="999">専攻を選択してください</option>
															<c:forEach var="senkou" items="${senkouList}">
																	<c:choose>
																			<c:when test="${ senkou.senkouId != senkouId}">
																					<option  value="${senkou.senkouId}">${senkou.senkouName }</option>
																			</c:when>
																			<c:when test="${ senkou.senkouId == senkouId}">
																					<option  value="${senkou.senkouId}" selected>${senkou.senkouName }</option>
																			</c:when>
																	</c:choose>
															</c:forEach>
						</select>
				</td>
				<td><font color="red"><c:out value="${senkouErrorMsg }"/></font></td>
				</tr>
				<tr>
							<th>アドバイザー：</th>
				<td>
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
				<td><font color="red"><c:out value="${kyouinErrorMsg }"/></font></td>
				</tr>
				<tr>
						<th>学年：</th>
						<td>
						<select name="gakunenSelect" style="width:200px; font-size:18px;">
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
				<td><font color="red"><c:out value="${gakunenErrorMsg }"/></font></td>
				</tr>
				<tr>
				<th>クラス名：</th>
				<td><input type="text" name="className"></td>
				<td><font color="red"><c:out value="${classErrorMsg }"/></font></td>
				</tr>
			</table>
				<input type="submit" value="登録">
				</form>
			</c:when>
		</c:choose>
</body>
</html>