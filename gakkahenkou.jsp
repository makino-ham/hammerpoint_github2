<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" media="screen" href="<%= request.getContextPath() %>/css/gakkasenkou.css" />
<title>学科変更</title>
</head>
<body>
	<h1>学科変更</h1>
	<form>
		<select>
			<option value="学科の選択">学科の選択</option>
		</select>
		<select name="doubutuSelect" style="width:200px; font-size:18px;">
			<c:forEach var="doubutu" items="${doubutuList}">
				<c:choose>
					<c:when test="${seito.doubutuId != doubutu.doubutuId }">
						<option  value="${doubutu.doubutuId }">${doubutu.doubutuName }</option>
					</c:when>
					<c:when test="${seito.doubutuId == doubutu.doubutuId }">
						<option value="${doubutu.doubutuId }" selected>${doubutu.doubutuName }</option>
					</c:when>
				</c:choose>
			</c:forEach>
		</select>
		<table>
		<%for(int i = 1; i <= 4; i++) { %>
			<tr><td></td><th><input type="placeholder" value="aaa"><br></th></tr>
		<%} %>
		</table>
		<input type="submit" value="決定" name="gakkahenkou">
	</form>
	<a class="button a" href="/Hammerpoint/GakkaSenkouKanri?action=shisukantourokukanryou">決定</a>
</body>
</html>