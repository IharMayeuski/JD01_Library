<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Registration</title>

<style type="text/css">
a {
	height: 250px;
	margin-top: 30px;
}

h1, h2 {
	margin-left: 15px;
	margin-top: 30px;
	color: white;
	border: 0;
	border-collapse: collapse;
}

h4 {
	color: #800000
}

#button {
	background: #800000;
	color: white;
}

div {
	margin: 5px;
}

label, legend, h1, h2, h3, span, a {
	color: white
}

body {
	font-family: Roboto;
	background: #696969;
}

table {
	margin-left: 0px;
	border: 0;
	border-collapse: collapse;
	color: white;
	background: #696969;
}

form, td, a2 {
	/* font-weight: bolder; */
	/* width: 200px; */
	background: #696969;
}

footer {
	background: #696969;
	color: white;
}
</style>

</head>

<body>

	<table Border=0 width="97%" height="10%">
		<tr bgcolor=#696969>
			<td width="30%" height="10%"><p>
					<img src="img/icon2.png" alt="pic">
				</p></td>
			<td></td>
			<td width="10%" height="10%">
				<div align="right"></div>

				<div align="right">
					<form action="controller" method="post">
						<input type="hidden" name="command" value="change_locale">
						<input type="hidden" name="locale" value="rus"> <input
							type="submit" name="submit" value="ru" />
					</form>
					<br>
					<form action="controller" method="post">
						<input type="hidden" name="command" value="change_locale">
						<input type="hidden" name="locale" value="en"> <input
							type="submit" name="submit" value="eng" />
					</form>
				</div>
			</td>
		</tr>
	</table>
	<%--				<fmt:setLocale value="${sessionScope.local}" />
					<fmt:setBundle basename="resources.locale" var="loc" />
					<fmt:message bundle="${loc}" key="locale.default.welcome_message"
						var="welcome_message" />

					<div align="center">${welcome_message}</div> 
--%>

	<table Border=0 width="97%" height="10%">
		<tr>
			<td width="20%" height="10%">
				<!-- <a href="controller?command=go_to_index">Forget me</a>  <br> <br> -->
				<a href="controller?command=go_to_index">Main page</a></a>
			</td>
			<br>

			<td><a>
					<div align="right"></div>
			</a></td>
		</tr>
	</table>

	<h2>Please to input your data, User!</h2>

	<h4>
		<c:out value="${requestScope.error}" />
	</h4>
	
	<table Border=0 width="97%" height="10%">
		<tr>
			<td width="10%" height="10%">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="registration">

					Enter login: <input type="text" name="login" value="" /><br>
					<br>

					<%-- 	${enter_password}: --%>
					Enter password: <input type="password" name="password" value="" />
					<br>
					<br> Enter e-mail: <input type="email" name="email" value="" />
					<br>
					<br> Enter name: <input type="name" name="name" value="" /> <br>
					<br> Enter surname:<input type="surname" name="surname"
						value="" /> <br>
					<br> <input type="submit" name="submit" value="Enter" />
				</form>
			</td>
			<td></td>
		</tr>
	</table>
	</td>
	</tr>

	<footer>
		<br>
		<div align=center id="footer">© JD01, Minsk 2019 Designed by
			Mayevski Igor</div>
	</footer>
</body>

</html>