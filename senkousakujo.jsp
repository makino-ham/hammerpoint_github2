<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>専攻削除</title>
</head>
<body>
	<form>
		<select>
			<option value="学科の選択">学科の選択</option>
		</select>
		<input type="button" value="検索"><br>
		<table>
			<%for(int i = 1; i <= 4; i++) { %>
			<tr><td><input type="checkbox" name="x"></td><th>教科</th></tr>
			<%} %>
		</table>
		<input type="submit" value="削除">
	</form>
</body>
</html>