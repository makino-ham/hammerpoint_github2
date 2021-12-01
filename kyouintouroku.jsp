<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>教員登録</title>
</head>
<body>
<center>
<h1>教員登録</h1>
	<form action="/Hammerpoint/KyouinKanri?action=touroku" method="post">
	<table>
		<tr>
		<th><a class="lbl">教員ID</a></th>
		<td><a class="txt"><input type="text" name="kyouin_ID" maxlength="5" value="${kyouinIdS }"></a></td>
		</tr>
		<tr>
		<th><a class="lbl">パスワード</a></th>
		<td><a class="pass"><input type="password" id="input_pass" name="password" maxlength="20" value="${password }"></a></td>
  		<td><input type="button" id="btn_passview" onclick="pushHideButton();" value="表示"></td>
		</tr>
		<script language="javascript">
  		function pushHideButton(){
  			var txtPass = document.getElementById("input_pass");
  			var btnPass = document.getElementById("btn_passview");
  			if(txtPass.type === "text" ){
  				txtPass.type = "password";
  				btnPass.value = "表示";
  			}else{
  				txtPass.type = "text";
  				btnPass.value = "非表示";
  			}
  		}
  		</script>
		<tr>
		<th><a class="lbl">教員名</a></th>
		<td><a class="txt">姓:<input type="text" name="kanSei" maxlength="20" value="${kan_sei }"></a></td>
		<td><a class="txt">名:<input type="text" name="kanMei" maxlength="20" value="${kan_mei }"></a></td>
		</tr>
		<tr>
		<th><a class="lbl">ふりがな</a></th>
		<td><a class="txt">姓:<input type="text" name="huriSei" maxlength="20" value="${huri_sei }"></a></td>
		<td><a class="txt">名:<input type="text" name="huriMei" maxlength="20" value="${huri_mei }"></a></td>
		</tr>
		<tr>
		<th><a class="lbl">性別</a></th>
		<td>
		<c:choose>
				<c:when test="${gender == 0 }">
					<a class="rdo"><input type="radio" name="gender" value="0" checked>　男</a>
					<a class="rdo"><input type="radio" name="gender" value="1">　女</a>
					<a class="rdo"><input type="radio" name="gender" value="2">　その他</a>
				</c:when>
				<c:when test="${gender == 1}">
					<a class="rdo"><input type="radio" name="gender" value="0">　男</a>
					<a class="rdo"><input type="radio" name="gender" value="1" checked>　女</a>
					<a class="rdo"><input type="radio" name="gender" value="2">　その他</a>
				</c:when>
				<c:when test="${gender == 2}">
					<a class="rdo"><input type="radio" name="gender" value="0">　男</a>
					<a class="rdo"><input type="radio" name="gender" value="1">　女</a>
					<a class="rdo"><input type="radio" name="gender" value="2" checked>　その他</a>
				</c:when>
				<c:otherwise>
					<a class="rdo"><input type="radio" name="gender" value="0">　男</a>
					<a class="rdo"><input type="radio" name="gender" value="1">　女</a>
					<a class="rdo"><input type="radio" name="gender" value="2">　その他</a>
				</c:otherwise>
			</c:choose>
		</td>
		</tr>
		<tr>
		<th><a>学科</a></th>
		<td><select name="gakkaSelect" style="width:200px; font-size:18px;">
		<option value="999">学科を選択してください</option>
		<c:forEach var="gk" items="${gakkaList}">
			<c:choose>
				<c:when test="${ gk.gakkaId != gakkaId}">
					<option  value="${gk.gakkaId}">${gk.gakkaName }</option>
				</c:when>
				<c:when test="${ gk.gakkaId == gakkaId}">
					<option  value="${gk.gakkaId}" selected>${gk.gakkaName }</option>
				</c:when>
			</c:choose>
		</c:forEach>
		</select></td>
		</tr>
		</table>
		<br><input type="submit" value="送信" ><br><br>
		<font color="red"><c:out value="${errorMsg }"/></font>
		<font color="red"><c:out value="${errorMsg2 }"/></font>
		<font color="red"><c:out value="${errorMsg3 }"/></font>
		<font color="red"><c:out value="${errorMsg4 }"/></font>
		<font color="red"><c:out value="${errorMsg5 }"/></font>
	</form>
</center>
</body>
</html>