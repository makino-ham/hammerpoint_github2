<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出欠変更検索</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<form action="/Hammerpoint/SyukketuKanri?action=henkoukensaku" method="post">
<font color="red"><c:out value="${kyoukaErrorMsg }" /></font><br>
<font color="red"><c:out value="${gakusekiErrorMsg }" /></font>
<table border="1">
	<c:forEach var="kyouka" items="${listOfAllMyKyouka}">
		<tr>
		<c:choose>
				<c:when test="${kyoukaIdS == kyouka.kyoukaId }">
						<td><input type="radio" name="kyoukaId" value="${kyouka.kyoukaId }" checked></td>
						<th><c:out value="${kyouka.kyoukaName }" /></th>
				</c:when>
				<c:otherwise>
						<td><input type="radio" name="kyoukaId" value="${kyouka.kyoukaId }"></td>
						<th><c:out value="${kyouka.kyoukaName }" /></th>
				</c:otherwise>
		</c:choose>
		</tr>
	</c:forEach>
</table>
<br>
学籍番号：<input type="text" name="gakusekiId" value="${gakusekiId }">
<br>
<input type="submit" value="検索">
</form>
</body>
</html>