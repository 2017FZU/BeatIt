<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="cn.springmvc.model.Students"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Success Page</h4>
	<%
		List<Students> list = (List<Students>) request.getAttribute("list");
	%>
	<table style="width: 100%">
		<tr>
			<td>name</td>
			<td>age</td>
			<td>sex</td>
		</tr>
		<%
			for (Students s : list) {
		%>
		<tr>
			<td>
				<%
					out.print(s.name);
				%>
			</td>
			<td>
				<%
					out.print(s.age);
				%>
			</td>
			<td>
				<%
					out.print(s.sex);
				%>
			</td>
		</tr>
		<%
			}
		%>

	</table>
</body>
</html>