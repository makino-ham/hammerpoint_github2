<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/css/main.css">
<title>Insert title here</title>
</head>
<body>
<center>
	<h1>xxxxxxxxxxxxx山田太xxxxxxxxxxxxx</h1>
	<table>
		<tr>
		<td><a class="button a" href="/Hammerpoint/SeitoKanri?action=touroku">生徒情報を登録する</a></td>
		<td><a class="button a" href="/Hammerpoint/SeitoKanri?action=kensaku">生徒情報を変更、削除する</a></td>
		</tr>
		<tr>
		<td><a class="button a" href="">生徒情報を一括登録する</a></td>
		<td><a class="button a" href="/Hammerpoint/SeitoKanri?action=sinkyu">進級登録</a></td>
		</tr>
		<tr>
		<td><a class="button a" href="/Hammerpoint/SeitoKanri?action=joutai">生徒の状態を管理する</a></td>
		<td><a class="button a" href="/Hammerpoint/SeitoKanri?action=ikkatu">生徒を一括登録する</a></td>
		</tr>
	</table>
</center>
</body>
</html>