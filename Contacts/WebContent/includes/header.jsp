<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://cdn.datatables.net/v/bs4/dt-1.10.24/datatables.min.css" >
	<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
	<link rel="shortcut icon" href="<%= request.getContextPath() %>/assets/images/contact.png" type="image/x-icon">
	<title>연락처 관리</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<span class="navbar-brand">🚀My APP</span>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		    	<span class="navbar-toggler-icon"></span>
		  	</button>
		  	<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		    	<div class="navbar-nav">
		      		<a id="m-home" class="nav-link" href="<%= request.getContextPath() %>">Home </a>
		      		<a id="m-contacts" class="nav-link" href="<%= request.getContextPath() %>/contact">contacts</a>
		    	</div>
		  	</div>
		</nav>
	</header>