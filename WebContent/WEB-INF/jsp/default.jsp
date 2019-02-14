<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!-- <html xmlns="http://www.w3.org/1999/xhtml"> -->


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">

<title>default page</title>

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
}

div {
	margin: 5px;
}

label, legend, h1, h2, span, a, table, #button, footer {
	color: white;
}

footer, form, td, a2, body {
	background: #696969; 
	}

body {
	font-family: Roboto;
	/* background: url(img/original.jpg); */
	}

table {
	margin-left: 0px;
	border: 0;
	border-collapse: collapse;

}

form, td, a2 {
	/* font-weight: bolder; */
	/* width: 200px; */
	
}


</style>

</head>



<!-- <body vlink="#cecece" alink="#ff0000">  -->

<body { background: url(img/original.jpg);}>

	 
	<table Border=0 width="97%" height="10%">
		<tr>
	<!-- 	<tr bgcolor=#696969> -->
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
		<fmt:setLocale value="${sessionScope.local}" />
		<fmt:setBundle basename="resources.locale" var="loc" />
		<fmt:message bundle="${loc}" key="locale.default.welcome_message"
			var="welcome_message" />
		<fmt:message bundle="${loc}" key="locale.default.enter_login_message"
			var="enter_login_message" />
		<fmt:message bundle="${loc}"
			key="locale.default.enter_password_message"
			var="enter_password_message" />
		<fmt:message bundle="${loc}" key="locale.default.link_main_page"
			var="link_main_page" />
		<fmt:message bundle="${loc}" key="locale.default.button_login"
			var="button_login" />
		<fmt:message bundle="${loc}" key="locale.default.button_search"
			var="button_search" />
		<fmt:message bundle="${loc}" key="locale.default.link_registration"
			var="link_registration" />
		<fmt:message bundle="${loc}" key="locale.default.enter_searching"
			var="searching" />
		<fmt:message bundle="${loc}" key="locale.default.enter_novelty"
			var="novelty" />


		<!--  locale.default.welcome_message = \u041F\u0440\u0438\u0432\u0435\u0442
locale.default.enter_login_message = \u041B\u043E\u0433\u0438\u043D
locale.default.enter_password_message = \u041F\u0430\u0440\u043E\u043B\u044C
locale.default.link_main_page = \u0413\u043B\u0430\u0432\u043D\u0430\u044F \u0441\u0442\u0440\u0430\u043D\u0438\u0446\u0430
locale.default.button_login = \u041B\u043E\u0433\u0438\u043D 
locale.default.button_search = \u041F\u043E\u0438\u0441\u043A
locale.default.link_registration = \u0420\u0435\u0433\u0438\u0441\u0442\u0440\u0430\u0446\u0438\u044F
locale.default.enter_searching = \u041D\u0430\u0439\u0434\u0438 \u0441\u0432\u043E\u044E \u043A\u043D\u0438\u0433\u0443
locale.default.enter_novelty = \u041D\u0430\u0448\u0438 \u043D\u043E\u0432\u0438\u043D\u043A\u0438
-->



	<h2><div align="center">${welcome_message}</div></h2>
	</h3>
	<a href="controller?command=go_to_index">${link_main_page}</a>

	<h4>
		<div align="right"></div><c:out value="${requestScope.error}" /></div>
		<br>
	</h4>


	<table Border=0 width="97%" height="30%">
			<tr>
	<!-- 	<tr bgcolor=#696969> -->
			<td width="60%" height="30%">

				<fieldset>
					<legend>${searching}</legend>

					<div>
						<form action="controller" method="post">
							<input type="hidden" name="command" value="search_genre">

							<select name="genre" id="genre">
								<option disabled value="Genre" selected>Genre</option>
								<option value="comedy">Comedy</option>
								<option value="novel">Novel</option>
								<option value="story">Story</option>
							</select> <label for="genre">Genre</label><br> <br>
							<input type="submit" value=${button_search } id="button"><br>
					</div>
					</form>
				</fieldset>
			</td>
			<td></td>
			<form action="controller" method="post">
				<input type="hidden" name="command" value="authorization">
				<td width="20%" height="30%">
					<div align="right"></div> <a> ${enter_login_message} <input
						type="text" name="login" value="" /> <br> <br>${enter_password_message}
						<input type="password" name="password" value="" /> <br> <br>
					<input type="submit" name="submit" value=${button_login
						} id="button" />
				</a>
			</form>


			
			<h3><a href="controller?command=goToRegistratioPage">${link_registration}</a></h3><br>
			<h2>	
				<div align="right">
					<form action="controller" method="post">
						</td>
		</tr>
	</table>
	<!-- <a href="controller?command=find_book">FindBook</a><br><br> -->



	<div align=center>
		<fieldset>
			<legend>${novelty}</legend>
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