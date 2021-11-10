<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>時間割管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<center>
<a class="button c" href="/Hammerpoint/Login?action=smain" method="post"id="absolute">←</a>

	<h1>${account.name }　さん　ようこそ</h1>
	<!-- うまいことで来てるかな17:30 -->
	<table>
		<tr>
		<td><a class="button a" href="/Hammerpoint/JikanwariKanri?action=touroku" method="get">新しく時間割を登録する</a></td>
		</tr>
		<tr>
		<td><a class="button a" href="/Hammerpoint/JikanwariKanri?action=ikkatu" method="get">時間割を一括登録する</a></td>
		</tr>
		<tr>
		<td><a class="button a" href="/Hammerpoint/JikanwariKanri?action=hensaku" method="get">時間割を変更・削除する</a></td>
		</tr>
	</table>
</center>
</body>
</html>