<%@ page language="java" import="by.htp.ellib.entity.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>main page</title>

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

</head>

<body>
	<%
	String userName;
	String userLogin;
	String userPassword;
	int userID;
	User user;
	user = (User) request.getAttribute("user");
	userName = user.getName();
	userID = user.getId();
	userLogin = user.getLogin();
	userPassword = user.getPassword();
	
	// request.getSession().setAttribute("USER", user);	

	
%>

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

	<h3>
		<%-- 					<fmt:setLocale value="${sessionScope.local}" />
					<fmt:setBundle basename="resources.locale" var="loc" />
					<fmt:message bundle="${loc}" key="locale.default.welcome_message"
						var="welcome_message" />

					<div align="center">${welcome_message}</div> --%>
	</h3>
	<a href="controller?command=go_to_index">Forget me</a>

	<h4>
		<c:out value="${requestScope.error}" />
		<br>
	</h4>


	<table Border=0 width="97%" height="30%">
		<tr bgcolor=#696969>
			<td width="60%" height="30%">

				<fieldset>
					<legend>Searching your free book</legend>

					<div>
						<form action="controller" method="post">
							<input type="hidden" name="command"	value="search_free_book_genre">
							<input type = "hidden" name="<%= "USER" %>" value="<%= userLogin %>" />
							<input type = "hidden" name="<%= "USER_pass" %>" value="<%= userPassword %>" />
							


							<select name="genre" id="genre">
								<option disabled value="Genre" selected>Genre</option>
								<option value="comedy">Comedy</option>
								<option value="novel">Novel</option>
								<option value="story">Story</option>
							</select> <label for="genre">Genre</label><br> <br>
							<input type="submit" value="Search" id="button"><br>
					</div>
					</form>
				</fieldset>
			</td>
			<td></td>
			<td>

				<h2>
					<div align="center">
						Hello,
						<c:out value="${requestScope.user.name }" />
						!
					</div>
				</h2>
			</td>
		</tr>
	</table>



	<div align=center>
		<fieldset>
			<legend>Our novelty:</legend>
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
	</div>


	</h2>
	<footer>
		<div align=center id="footer">© JD01, Minsk 2019 Designed by
			Mayevski Igor</div>
	</footer>

</body>
</html>