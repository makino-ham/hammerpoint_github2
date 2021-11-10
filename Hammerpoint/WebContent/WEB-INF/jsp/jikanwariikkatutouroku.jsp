<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>時間割一括登録</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<a class="button c" href="/Hammerpoint/JikanwariKanri?action=kanri" method="post"id="absolute">←</a>
<form action="/Hammerpoint/Ikkatutouroku?action=ikkatutouroku" method="post">
<input type="file" name="ikkatutouroku"accept=".csv">

  <button type="submit">一括登録する</button>
</form>

	<!--
	<table border="1">
		<tr><th>選択</th><th>教科</th></tr>
	<c:forEach var="kyouka" items="${ikkatuList}">
		<tr>
		<th><c:out value="${Jikanwari.kyoukaID}" /></th>
		<th><c:out value="${Jikanwari.youbiId}" /></th>
		<th><c:out value="${Jikanwari.jugyouSuu}" /></th>
		<th><c:out value="${Jikanwari.jigen}" /></th>
		</tr>
	</c:forEach>
	</table>
-->
</body>
</html>