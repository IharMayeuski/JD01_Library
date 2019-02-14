<%@ page language="java" import="by.htp.ellib.entity.*" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search book</title>

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
label, legend, h1, h2, span, a {
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
<%-- <%
	String userName;
	User user;
	user = (User) request.getAttribute("user");
	userName = user.getName();
	
%> --%>

<%-- <table>
<col width="1200"><col width="1200">
		<tr>
			<td colspan="2"> --%>

				<table Border=0 width="100%" height="10%">
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
				</table> <%--				<fmt:setLocale value="${sessionScope.local}" />
					<fmt:setBundle basename="resources.locale" var="loc" />
					<fmt:message bundle="${loc}" key="locale.default.welcome_message"
						var="welcome_message" />
					<div align="center">${welcome_message}</div> 
--%>


				<table Border=0 width="100%" height="10%">
					<tr>
						<td width="20%" height="10%">
						<!-- <a href="controller?command=go_to_index">Forget me</a>  <br> <br> -->
						<a href="controller?command=go_to_index">Main page</a></a></td><br>

						<td><a>
								<div align="right"></div>
						</a></td>
					</tr>
				</table>


				<div align=center>
					<fieldset>
						<legend>Our books:</legend>
						<table border="1">
							<tr>
								<td>Author</td>
								<td>Name</td>
								<td>Year</td>
								<td>Genre</td>
								<td>Price</td>
							</tr>

							<c:forEach items="${requestScope.books}" var="book">
								<tr>
									<td>${book.author}</td>
									<td>${book.name}</td>
									<td>${book.year_book}</td>
									<td>${book.genre}</td>
									<td>${book.price}</td>
								</tr>
							</c:forEach>
						</table>
					</fieldset>
					</h1>
				</div> <br> <br> <br> <br> <br> <br>
<!-- </td>
</tr>

</table> -->

	<footer Border=0 width="100%" height="10%">
			<br>
			<div align=center id="footer">© JD01, Minsk 2019 Designed by
				Mayevski Igor</div>
		</footer>
</body>

</html>