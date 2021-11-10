<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
<title>学科削除</title>
</head>
<body>
	<form>
		<select>
			<option value="学科の選択">学科の選択</option>
		</select>
		<input type="submit" value="決定">
	</form>
	<a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=x">決定</a>
</body>
</html>