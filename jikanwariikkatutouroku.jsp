<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<center>
<a class="button c" href="/Hammerpoint/JikanwariKanri?action=kanri" method="post" id="absolute">←</a>
<form action="/Hammerpoint/Ikkatutouroku?action=ikkatutouroku" method="post">
<input type="file" name="ikkatutouroku"accept=".csv">

  <button type="submit">一括登録する</button>
  <table border="1" style="border-collapse: collapse">
  <tr><th colspan="4">一括で登録する際は、以下の要素を持たせたcsvファイルを使用してください。<br>要素ごとにカンマで区切ってください。<br>(例：月曜日,コンピュータ概論ⅠA,週4時間,1限の場合<br>0,0,120,0)</th></tr>
  <tr><td>曜日ID</td><td>教科ID</td><td>授業数</td><td>時限</td></tr>
   <tr><td>月曜=0<br>火曜=1<br>水曜=2<br>木曜=3<br>金曜=4</td><td>
	<c:forEach var="kyouka" items="${kyoukaList}">
		<c:out value="${kyouka.kyoukaName }=${kyouka.kyoukaId }"/><br>
	</c:forEach>
   </td>
   <td>週4時間=120<br>週3時間=90<br>週2時間=60<br>週1時間=30</td>
   <td>1限=1<br>2限=2<br>3限=3<br>4限=4<br>5限=5</td></tr>
  </table>
</form>
</center>
</body>
</html>