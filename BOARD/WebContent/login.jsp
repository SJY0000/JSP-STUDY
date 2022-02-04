
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
<title>게시판</title>
<style>
html, body {
	height: 100%;
}

body {
	display: flex;
	align-items: center;
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	width: 100%;
	max-width: 330px;
	padding: 15px;
	margin: auto;
}

.form-signin .checkbox {
	font-weight: 400;
}

.form-signin .form-floating:focus-within {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
}
</style>
</head>
<body class="text-center">

	<main class="form-signin">
		<form action="<%=request.getContextPath()%>/Controller">
			<img class="mb-4"
				src="https://cdn.pixabay.com/photo/2012/04/26/19/08/note-42883_960_720.png"
				alt="" width="72" height="57">
			<div class="form-floating">
				<input type="email" class="form-control" id="floatingInput" name="email"
					placeholder="name@example.com"> <label for="floatingInput">Id</label>
			</div>
			<div class="form-floating">
				<input type="password" class="form-control" id="floatingPassword" name="password"
					placeholder="Password"> <label for="floatingPassword">Password</label>
			</div>

			<button class="w-100 btn btn-lg btn-primary" type="submit">로그인</button>
			<footer class="navbar-fixed-bottom py-3 my-4">
				<div class=" py-1">
					<ul class="nav justify-content-center border-bottom pb-1 mb-1">
						<li class="nav-item"><a href="<%=request.getContextPath()%>"
							class="nav-link px-2 text-muted">게시판</a></li>
						<li class="nav-item"><a href="#"
							class="nav-link px-2 text-muted">FAQs</a></li>
						<li class="nav-item"><a href="#"
							class="nav-link px-2 text-muted">About</a></li>
					</ul>
				</div>
				<p class="text-center text-muted">&copy; 2022 KoreaIT</p>
			</footer>
		</form>
	</main>
</body>