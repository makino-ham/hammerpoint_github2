<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出欠変更検索</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/style.css" />
<script type="text/javascript"src="<%=request.getContextPath() %>/js/myscript.js"></script>
</head>
<body>
<form action="/Hammerpoint/SyukketuKanri?action=henkoukensaku" method="post">
<font color="red"><c:out value="${kyoukaErrorMsg }" /></font><br>
<font color="red"><c:out value="${gakusekiErrorMsg }" /></font>
<font color="red"><c:out value="${nullErrorMsg }" /></font>
<table border="1">
	<c:forEach var="kyouka" items="${listOfAllMyKyouka}">
		<tr>
		<c:choose>
				<c:when test="${kyoukaIdS == kyouka.kyoukaId }">
						<td><input type="radio" name="kyoukaId" value="${kyouka.kyoukaId }" checked></td>
						<th><c:out value="${kyouka.kyoukaName }" /></th>
				</c:when>
				<c:otherwise>
						<td><input type="radio" name="kyoukaId" value="${kyouka.kyoukaId }"></td>
						<th><c:out value="${kyouka.kyoukaName }" /></th>
				</c:otherwise>
		</c:choose>
		</tr>
	</c:forEach>
</table>
<select name="year" id="id_year">
</select>
<select name="month" id="id_month">
</select>
<select name="day" id="id_day">
</select>
<script>
(function() {
  'use strict';

  /*
    今日の日付データを変数todayに格納
   */
  var optionLoop, this_day, this_month, this_year, today;
  today = new Date();
  this_year = today.getFullYear();
  this_month = today.getMonth() + 1;
  this_day = today.getDate();

  /*
    ループ処理（スタート数字、終了数字、表示id名、デフォルト数字）
   */
  optionLoop = function(start, end, id, this_day) {
    var i, opt;

    opt = null;
    for (i = start; i <= end ; i++) {
      if (i === this_day) {
        opt += "<option value='" + i + "' selected>" + i + "</option>";
      } else {
        opt += "<option value='" + i + "'>" + i + "</option>";
      }
    }
    return document.getElementById(id).innerHTML = opt;
  };


  /*
    関数設定（スタート数字[必須]、終了数字[必須]、表示id名[省略可能]、デフォルト数字[省略可能]）
   */
  optionLoop(1950, this_year, 'id_year', this_year);
  optionLoop(1, 12, 'id_month', this_month);
  optionLoop(1, 31, 'id_day', this_day);
})();

</script>
<br>
時限：
<select name="jigen">
	<c:choose>
			<c:when test="${jigen == 2 }">
					<option value="1">1</option>
					<option value="2" selected>2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
			</c:when>
			<c:when test="${jigen == 3 }">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3" selected>3</option>
					<option value="4">4</option>
					<option value="5">5</option>
			</c:when>
			<c:when test="${jigen == 4 }">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4" selected>4</option>
					<option value="5">5</option>
			</c:when>
			<c:when test="${jigen == 5 }">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5" selected>5</option>
			</c:when>
			<c:otherwise>
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
			</c:otherwise>
	</c:choose>
</select>
<br>
学籍番号：<input type="text" name="gakusekiId" value="${gakusekiId }">
<br>
<input type="submit" value="検索">
</form>
</body>
</html>