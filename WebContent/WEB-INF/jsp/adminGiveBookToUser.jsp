<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Give book</title>
<style type="text/css">
a {
	height: 250px;
	margin-top: 10px;
}

h1 {
	margin-left: 15px;
	margin-top: 10px;
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
	<%-- 					<fmt:setLocale value="${sessionScope.local}" />
					<fmt:setBundle basename="resources.locale" var="loc" />
					<fmt:message bundle="${loc}" key="locale.default.welcome_message"
						var="welcome_message" />

					<div align="center">${welcome_message}</div> 
--%>

	<table Border=0 width="97%" height="10%">
		<tr>
			<td width="30%" height="10%"><a
				href="controller?command=go_to_index">Forget me</a> <br> <br>
				<a href="controller?command=go_admin_page">Admin page</a><br> <br>
				<a href="controller?command=show_books">Show all books</a> <br>
				<br> <a href="controller?command=show_users">Show all users</a><br>
				<br> <a href="controller?command=show_free_books">Show free
					books</a><br> <br> <a
				href="controller?command=show_booked_books">Show booked books</a> <br></td>

			<td><a>
					<div align="right">Hello, admin!</div>
			</a></td>
		</tr>
	</table>


	<h4>
		<c:out value="${requestScope.error}" />
		<br>
	</h4>
	
	<div align="centre"><h3>We gave the book for USER!</h3></div>
	<br>

	<table Border=0 width="97%" height="10%">
		<tr>
			<td width="30%" height="10%">
				<fieldset>
					<legend><strong> Add new book</strong></legend>

					<div>

						<form action="controller" method="post"> 
						<input type="hidden" name="command" value="addBook">
						Enter author: <input type="text" name="author" value="" /> <br>
							<br> 
						Enter name: <input type="text" name="name" value="" />
							<br> <br> 
						Enter year: <input type="text"
								name="year_book" value="" /> <br> <br>

						Enter genre: <select name="genre" id="genre">
								<option disabled value="Genre" selected>Genre</option>
								<option value="comedy">Comedy</option>
								<option value="novel">Novel</option>
								<option value="story">Story</option>
							</select> <br> <br> 
						Enter price: <input type="text" name="price"
								value="" /> <br> <br> <input type="submit"
								name="submit" value="Enter" />
					</form>
					</div>
					
				</fieldset>
			</td>

			<td width="30%" height="10%">
				<fieldset> <legend><strong>Give book to user</strong></legend>

					<div>
						<form action="controller" method="post"> 
							<input type="hidden" name="command" value="giveBook">
							Book_ID: <input type="text" name="book_id" value="" /> <br>
							<br> 
							User_ID: <input type="text" name="users_id" value="" />
							<br><br><br><br><br><br><br><br>
							 <input type="submit" name="submit" value="Enter" />
						</form>
					</div>
					
				</fieldset>
			</td>

			<td width="30%" height="10%">
				<fieldset>
					<legend><strong>Take book from user</strong></legend>

					<div>

						<form action="controller" method="post">
							<input type="hidden" name="command" value="receiveBook">
							Book_ID: <input type="text" name="book_id" value="" /> <br>
							<br> <br><br><br><br><br><br><br><br><br>
							<input type="submit" name="submit" value="Enter" />
						</form>
					</div>
					
				</fieldset>
			</td>
		</tr>
</table>
</h2>


		<footer>
			<br>
			<div align=center id="footer">© JD01, Minsk 2019 Designed by
				Mayevski Igor</div>
		</footer>
</body>

</html>