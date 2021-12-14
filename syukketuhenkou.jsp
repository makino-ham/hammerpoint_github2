<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<center>
		<font color="red"><c:out value="${riyuuErrorMsg }" /></font>
		<form action="/Hammerpoint/SyukketuKanri?action=henkou" method="post">
		<table border="1">
				<tr><th>学籍番号</th><td><c:out value="${syukketu.gakusekiId }" /></td></tr>
				<tr><th>生徒名</th><td><c:out value="${syukketu.seitoName }" /></td></tr>
				<tr><th>教科名</th><td><c:out value="${syukketu.kyoukaName }" /></td></tr>
				<tr><th>日付</th><td><c:out value="${syukketu.hiduke }" /></td></tr>
				<tr><th>時限</th><td><c:out value="${syukketu.jigen}" /></td></tr>
				<tr><th>出欠</th>
						<td>
								<c:choose>
										<c:when test="${syukketu.syukketu == 0 }">
												<input type="radio" name="syukketu" value="0" checked>出席
												<input type="radio" name="syukketu" value="1">欠席
												<input type="radio" name="syukketu" value="2">公欠
										</c:when>
										<c:when test="${syukketu.syukketu == 1 }">
												<input type="radio" name="syukketu" value="0">出席
												<input type="radio" name="syukketu" value="1" checked>欠席
												<input type="radio" name="syukketu" value="2">公欠
										</c:when>
										<c:when test="${syukketu.syukketu == 2 }">
												<input type="radio" name="syukketu" value="0">出席
												<input type="radio" name="syukketu" value="1">欠席
												<input type="radio" name="syukketu" value="2" checked>公欠
										</c:when>
								</c:choose>
						</td>
				</tr>
				<tr><th>変更理由</th><td><input type="text" name="riyuu"></td></tr>
		</table>
				<input type="submit" value="変更">
		</form>
</center>
</body>
</html>