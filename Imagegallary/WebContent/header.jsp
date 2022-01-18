<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 현재위치에서 다른위치로 옮겨질 경우 경로가 바뀌는 경우를 대비해서 ${pageContext.request.contextPath}/ 경로 앞에 붙여준다. -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<title>${param.title}</title>
</head>
<body>
<div class="headerWrapper">
	<div class="header">
		<img src="${pageContext.request.contextPath}/image/logo.png"/>
		<span id="title"></span>
	</div>
</div>
<div class="content">
