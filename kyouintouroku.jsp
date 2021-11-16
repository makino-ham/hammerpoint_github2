<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath() %>/js/passwordviw.js"></script>
<title>教員登録</title>
</head>
<body>
<center>
<h1>教員登録</h1>
	<form action="/Hammerpoint/KyouinKanri?action=touroku" method="post">
	<table>
		<tr>
		<th><a class="lbl">教員ID</a></th>
		<td><a class="txt"><input type="text" name="kyouin_ID" maxlength="5" value=""></a></td>
		</tr>
		<tr>
		<th><a class="lbl">パスワード</a></th>
		<td><a class="pass"><input type="password" id="input_pass" name="password" maxlength="20" value=""></a></td>
  		<td><button id="btn_passview">表示</button></td>
		</tr>
		<tr>
		<th><a class="lbl">教員名</a></th>
		<td><a class="txt">姓:<input type="text" name="kanSei" maxlength="20" value=""></a></td>
		<td><a class="txt">名:<input type="text" name="kanMei" maxlength="20" value=""></a></td>
		</tr>
		<tr>
		<th><a class="lbl">ふりがな</a></th>
		<td><a class="txt">姓:<input type="text" name="huriSei" maxlength="20" value=""></a></td>
		<td><a class="txt">名:<input type="text" name="huriMei" maxlength="20" value=""></a></td>
		</tr>
		<tr>
		<th><a class="lbl">性別</a></th>
		<td><a class="rdo"><input type="radio" name="gender" value="0">　男</a></td>
		<td><a class="rdo"><input type="radio" name="gender" value="1">　女</a></td>
		</tr>
		<tr>
		<th><a>学科</a></th>
		<td><select name="gakkaSelect" style="width:200px; font-size:18px;">
		<option value="999">学科を選択してください</option>
		<c:forEach var="gk" items="${gakkaList}">
			<option  value="${gk.gakkaId }">${gk.gakkaName }</option>
		</c:forEach>
		</select></td>
		</tr>
		<tr>
		<td><input type="submit" value="送信"></td>
		</tr>
	</table>
	</form>
</center>
</body>
</html>