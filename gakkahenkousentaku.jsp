<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
<title>学科専攻変更の選択</title>
</head>
<body>
	<table>
		<tr><td><a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=gakkahenkou">学科変更</a></td></tr>
		<tr><td><a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=senkouhenkou">専攻変更</a></td></tr>
	</table>
</body>
</html>