<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒一括登録</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<center>
<a class="button c" href="/Hammerpoint/JikanwariKanri?action=kanri" method="post" id="absolute">←</a>
<form action="/Hammerpoint/Ikkatutouroku?action=seitoikkatutouroku" method="post">
<input type="file" name="ikkatutouroku"accept=".csv">

  <button type="submit">一括登録する</button>
  <table border="1" style="border-collapse: collapse">
  <tr><th colspan="6">一括で登録する際は、以下の要素を持たせたcsvファイルを使用してください。<br>要素ごとにカンマで区切ってください。<br>(例：月曜日,コンピュータ概論ⅠA,週4時間,1限の場合<br>0,0,120,0)</th></tr>
    <tr><th>学籍番号</th><th>生徒名</th><th>メールアドレス</th><th>クラスID</th><th>動物ID</th><th>性別</th></tr>
   <tr><td>例：100A1001</td>
   <td>例：浦山田太</td>
   <td></td>
   <td>
   		<c:forEach var="room" items="${classList}">
				<c:out value="${room.className}=${room.classId }"/><br>
		</c:forEach>
   </td>
   <td>
	   	<c:forEach var="doubutu" items="${doubutuList}">
					<c:out value="${doubutu.doubutuName}=${doubutu.doubutuId }"/><br>
			</c:forEach>
   </td>
   <td>男=0　 女=1　その他=２</td></tr>
  </table>
</form>
</center>
</body>
</html>