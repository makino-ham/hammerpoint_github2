<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/css/main.css" />
<title>データ管理</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<a class="button c" href="/Hammerpoint/JikanwariKanri?action=kanri" method="post"id="absolute">←</a>
<center>
<h1>データ管理</h1>
<table>
		<tr>
		<td><a class="btn a" href="/Hammerpoint/DataKanri?action=seito">生徒データ表示</a></td>
		</tr>
		<tr>
		<td><a class="btn a" href="/Hammerpoint/DataKanri?action=doubutu">動物データ表示</a></td>
		</tr>
		<tr>
		<td><a class="btn a" href="/Hammerpoint/DataKanri?action=kaikinsyou">皆勤賞データ表示</a></td>
		</tr>
	</table>
</center>
</body>
</html>