<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>パスワード変更</title>
</head>
<body>
<center>
<h1>パスワード変更</h1>
<form action="/Hammerpoint/KyouinKanri?action=touroku" method="post">
	<table>
		<tr>
		<th><a class="lbl">新しいパスワード</a></th>
		<td><a class="pass"><input type="password" id="newpass" maxlength="20" value=""></a></td>
		<td><input type="button" id="newbtn" onclick="pushHideButton();" value="表示"></td>
		</tr>
		<tr>
		<th><a class="lbl">パスワードを確認</a></th>
		<td><a class="pass"><input type="password" id="input_pass" maxlength="20" value=""></a></td>
		<td><input type="button" id="btn_passview" onclick="pushHideButton2();" value="表示"></td>
		</tr>
		<script language="javascript">
  		function pushHideButton(){
  			var txtPass = document.getElementById("newpass");
  			var btnPass = document.getElementById("newbtn");
  			if(txtPass.type === "text" ){
  				txtPass.type = "password";
  				btnPass.value = "表示";
  			}else{
  				txtPass.type = "text";
  				btnPass.value = "非表示";
  			}
  		}
  		function pushHideButton2(){
  			var txtPass2 = document.getElementById("input_pass");
  			var btnPass2 = document.getElementById("btn_passview");
  			if(txtPass2.type === "text" ){
  				txtPass2.type = "password";
  				btnPass2.value = "表示";
  			}else{
  				txtPass2.type = "text";
  				btnPass2.value = "非表示";
  			}
  		}
  		</script>
	</table>
	</form>
</center>
</body>
</html>