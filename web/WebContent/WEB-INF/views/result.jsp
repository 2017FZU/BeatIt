<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

	<table style="width: 100%">
		<tr>
			<td>name</td>
			<td>age</td>
			<td>sex</td>
		</tr>
		<c:forEach var="student" items="${requestScope.list}"
			varStatus="status">
			<tr>
				<td><c:out value="${student.name}" /></td>
				<td><c:out value="${student.age}" /></td>
				<td><c:out value="${student.sex}" /></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>