<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
<title>学科専攻</title>
</head>
<body>
	<a class="button c" href="/Hammerpoint/Login?action=smain" method="post" id="absolute">←</a>
	<h1>学科専攻の管理</h1>
	<table>
		<tr><td><a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=gakkasenkousentaku">登録</a></td></tr>
		<tr><td><a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=gakkahenkousentaku">変更</a></td></tr>
		<tr><td><a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=gakkasakujosentaku">削除</a></td></tr>
	</table>
</body>
</html>