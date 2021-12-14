<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出欠登録</title>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
		<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
		<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<center>
	<h2>${account.name}　先生　ようこそ</h2>
  <form action="/Hammerpoint/SyukketuKanri?action=touroku" method="post">
  <table  class="jikanwarihyouji" border="1" style="border-collapse: collapse">
    <tr><th colspan="4"><c:out value="${kyoukaName }" /></th></tr>
  	<tr><th colspan="2"><c:out value="${gakunen }" /></th><th colspan="2"><c:out value="${senkouName }" /></th></tr>
		<tr style="background-color:lightgreen;">
				<th>生徒名</th><th>出席</th><th>欠席</th><th>公欠</th>
		</tr>

	<c:forEach var="seito" items="${seitoList}">
			<c:set var="gakuseki" value="${seito.gakusekiId },0"/>
			<tr>
					<td class="seito"><c:out value="${seito.seitoName }" /></td>
					<c:set var="gakuseki" value="${seito.gakusekiId },0"/>
					<td class="radio"><input type="radio" name="${seito.gakusekiId }" value="${gakuseki }" checked></td>
					<c:set var="gakuseki" value="${seito.gakusekiId },1"/>
					<td class="radio"><input type="radio" name="${seito.gakusekiId }" value="${gakuseki }"></td>
					<c:set var="gakuseki" value="${seito.gakusekiId },2"/>
					<td class="radio"><input type="radio" name="${seito.gakusekiId }" value="${gakuseki }"></td>
			</tr>
	</c:forEach>
</table>
<input type="submit" value="登録へ進む">
</form>
</center>
</body>
</html>