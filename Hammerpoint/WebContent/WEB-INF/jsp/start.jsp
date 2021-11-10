<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<center>
<h1>出欠管理システム：ログイン</h1>
<a class="button a" href="/Hammerpoint/Login?action=slogin" method="get">システム管理者ログイン</a>

<a class="button b" href="/Hammerpoint/Login?action=klogin" method="get">教員ログイン</a>

</center>
</body>
</html>