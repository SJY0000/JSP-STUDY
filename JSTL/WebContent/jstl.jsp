<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 출력 태그 -->
<c:out value="JSTL 안녕!"></c:out>

<!-- JSTL로 빈 객체 사용 -->
<jsp:useBean id="test" class="beans.TestBean" />
<p> getInfo메소드 : <c:out value="${test.info }" />   <!-- 중괄호 안에는 변수 혹은 객체-->

<!-- parameter 가져오기 -->
<p> parameter : <c:out value="${param.name}"/><!--param은 parameter -->
<br>
<br>
<!-- IF문 -->
<c:if test="${param.name == 'bob'}"> <!-- else문이 없음  -->
	헬로우 bob
</c:if>
<c:if test="${param.name != 'bob'}">
	YOU ARE NOT bob!
</c:if>
<br>
<br>
<!-- SWITCH문 JSTL choose, when(케이스), otherwidse(디폴트) -->
<c:choose>
	<c:when test="${param.id == 1}">
		<b>ID는 1</b>
	</c:when>
	<c:when test="${param.id == 2}">
		<b>ID는 2</b>
	</c:when>
	<c:otherwise>
		<b>ID는 1 또는 2가 아니다.</b>
	</c:otherwise>
</c:choose>
<br>
<br>
<!-- 반복문 -->
<c:forEach var="i" begin="0" end="10" step="2"> <!-- 변수 i가 0부터 10까지 2씩 증가 -->
	i의 값 : <c:out value="${ i }"/> <br>
</c:forEach>
</body>
</html>