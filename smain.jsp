<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>システム管理者管理</title>

<link rel="icon" type="image/png" href="<%=request.getContextPath() %>/image/hammerpoint_icon.png"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/siskan.css" />
		<script type="text/javascript"src="<%=request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
		<!-- <script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script> -->
</head>
<body>
<div class="container">
<div class="header wrapper">
<nav>
                <ul class="main-nav">
<li><a class="logout" href="/Hammerpoint/Login?action=start" method="post">←</a></li>
<li><h1>システム管理者：メイン画面</h1></li>
<li><p class="username"><img class="text_img" src="<%=request.getContextPath() %>/image/user_icon.png">${account.name }</p></li>
</ul>
</nav>
</div>
<div class="main mainwrapper example">
	<table>
		<tr>
		<td><a class="button a" href="/Hammerpoint/Seni?action=seito" method="get">生徒管理<br><img class="img" src="<%=request.getContextPath() %>/image/106_color.png"></a></td>
		<td><a class="button a" href="/Hammerpoint/Seni?action=kyouin" method="get">教員管理<br><img class="img" src="<%=request.getContextPath() %>/image/9539_color.png"></a></td>
		<td><a class="button a" href="/Hammerpoint/Seni?action=kyouka" method="get">教科管理<br><img class="img" src="<%=request.getContextPath() %>/image/2187_color.png"></a></td>
		<td><a class="button a" href="/Hammerpoint/Seni?action=data" method="get">データ管理<br><img class="img" src="<%=request.getContextPath() %>/image/9161_color.png"></a></td>
		</tr>
		<tr>
		<td><a class="button a" href="/Hammerpoint/Seni?action=jikanwari" method="get">時間割管理<br><img class="img" src="<%=request.getContextPath() %>/image/12178_color.png"></a></td>
		<td><a class="button a" href="/Hammerpoint/Seni?action=gakkasenkou" method="get">学科専攻管理<br><img class="img" src="<%=request.getContextPath() %>/image/7221_color.png"></a></td>
		<td><a class="button a" href="/Hammerpoint/Seni?action=class" method="get">クラス管理<br><img class="img" src="<%=request.getContextPath() %>/image/101_color.png"></a></td>
		<td><a class="button a" href="/Hammerpoint/Seni?action=password" method="get">パスワード変更<br><img class="img" src="<%=request.getContextPath() %>/image/icon_144200_256.png"></a></td>
		</tr>
	</table>
	</div>

	</div>


</body>
</html>