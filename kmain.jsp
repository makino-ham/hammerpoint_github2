<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>システム管理者管理</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
		<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<a class="button c" href="/Hammerpoint/Login?action=start" method="post"id="absolute">ログアウト</a>


<center>
	<h2>${account.name}　先生　ようこそ</h2>
	<h3>本日の時間割</h3>
	<a class="button e" href="/Hammerpoint/TeacherKanri?action=syukketu" method="get">出欠を管理する</a>
	<a class="button e" href="/Hammerpoint/TeacherKanri?action=syukketu" method="get">出欠状況を見る</a>
  <table  class="jikanwarihyouji" border="1" style="border-collapse: collapse">
		<tr style="background-color:lightgreen;">
			<th>専攻</th>
			<th>学年</th>
			<th>授業名</th>
			<th>時限</th>
		</tr>

	<c:forEach var="kyouka" items="${kyoukaList}">
		<tr>
		<td class="senkou"><c:out value="${kyouka.senkouName}" /></td>
		<td class="gakunen"><c:out value="${kyouka.gakunen}" /></td>
		<td class="jugyou"><c:out value="${kyouka.kyoukaName}" /></td>
		<td class="jigen"><c:out value="${kyouka.jigen}" /></td>
		</tr>
	</c:forEach>
</table>
</center>
</body>
</html>