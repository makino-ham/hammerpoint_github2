<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教員ログイン</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<center>
<h1>出欠管理システム：教員ログイン</h1>
<a class="button c" href="/Hammerpoint/Login?action=start" method="post"id="absolute">←</a>

<form action="/Hammerpoint/TeacherKanri?action=kyouin" method="post">
<input type="text" placeholder="教員IDを入力" class="textbox" name="id"required="required" value="10000">
<br>
<input type="password" placeholder="パスワードを入力" class="textbox" name="pass"required="required" value="eYfbBDnM">
<br>
${err }
<br>
<input type="submit" value="ログイン" class="button a">


</form>


</center>
</body>
</html>