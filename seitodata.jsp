<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>生徒データ表示</title>
</head>
<body>
<center>
<h1>生徒データ表示</h1>
<form action="/Hammerpoint/DataKanri?action=seito" method="post">
	<select name="classSelect" style="width:200px; font-size:18px;">
	<option value="999">クラスを選択してください</option>
	</select>
	<input type="submit" value="検索">
	</form>
</center>
</body>
</html>