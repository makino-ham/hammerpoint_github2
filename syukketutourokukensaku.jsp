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
<center>
	<h2>${account.name}　先生　ようこそ</h2>
  <form action="/Hammerpoint/SyukketuKanri?action=tourokuKensaku" method="post">
  <table  class="jikanwarihyouji" border="1" style="border-collapse: collapse">
		<tr style="background-color:lightgreen;">
				<th>選択欄</th>
				<th>専攻</th>
				<th>学年</th>
				<th>授業名</th>
				<th>時限</th>
		</tr>
	<c:forEach var="kyouka" items="${kyoukaList}">
			<c:set var="zenbu" value="${kyouka.kyoukaId },${kyouka.jigen },${kyouka.gakunen},${kyouka.classId},${kyouka.kyoukaName },${kyouka.senkouName }"/>
			<tr>
					<td class="check"><input type="radio" name="zenbu" value="${zenbu }"></td>
					<td class="senkou"><c:out value="${kyouka.senkouName}" /></td>
					<td class="gakunen"><c:out value="${kyouka.gakunen}" /></td>
					<td class="jugyou"><c:out value="${kyouka.kyoukaName}" /></td>
					<td class="jigen"><c:out value="${kyouka.jigen}" /></td>
			</tr>
	</c:forEach>
</table>
<font color="red"><c:out value="${radioErrorMsg }" /></font><br>
<input type="submit" value="登録へ進む">
</form>
</center>
</body>
</html>