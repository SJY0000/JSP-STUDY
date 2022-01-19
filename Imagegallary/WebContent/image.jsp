<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<c:import url="header.jsp">
	<c:param name="title" value="단독이미지"/>
</c:import>
<!-- dataSource는 context.xml에 있는 name과 같아야함 -->
<!-- transaction 구간을 한번에 처리, 중복 사용을 해도 문제 생기지 않음 -->
<sql:transaction dataSource="jdbc/webshop">
	
	<sql:query var="results" sql="select * from images where id=?">
		<sql:param>${param.image}</sql:param> <!-- ?에 들어갈 변수 -->
	</sql:query>
	
	<c:set var="image" scope="page" value="${results.rows[0]}" /> <!-- 한개만 출력 -->
	<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>
	<c:set var="average_ranking" scope="page" value="${image.average_ranking}"/>
	
	<!-- action이 "rate"이면 rankings 와 average_ranking을 Update 한다. -->
	<c:if test='${param.action == "rate"}'>
		<c:set scope="page" var="newRating"
		value="${(image.average_ranking * image.rankings + param.rating)/(image.rankings + 1)}" />
		
		<c:set scope="page" var="average_ranking" value="${newRating}" />
	
		<sql:update sql="update images set average_ranking=?, rankings=? where id=?">
			<sql:param>${newRating}</sql:param> <!-- 첫번째 ?에 들어갈 변수 -->
			<sql:param>${image.rankings + 1}</sql:param> <!-- 두번째 ?에 들어갈 변수 -->
			<sql:param>${param.image}</sql:param> <!-- 세번째 ?에 들어갈 변수 -->
		</sql:update>
	</c:if>

</sql:transaction>
 
  <div class="container">
    <div class="heading">
      <h1><c:out value="${fn:toUpperCase(fn:substring(image.stem, 0, 1))}${fn:toLowerCase(fn:substring(image.stem, 1, -1))}" /></h1>
      <div class="rating">Rated:<fmt:formatNumber value="${average_ranking}" maxFractionDigits="1"/></div>
    </div>
    <div class="flex-box">
      <div class="picture">
        <div class="imageby">Image by <a href="#">${image.attribution_name}</a></div>
        <img src="${pageContext.request.contextPath}/pics/${picName}">
      </div>
      <div class="rating-radio">
      <form action="<c:url value="/gallery" />" method="get">
      <input type="hidden" name="action" value="rate">
      <input type="hidden" name="image" value="${image.id}">
        <h3>점수를 선택하기</h3>
	        <div><input type="radio" name="rating" value="5" />5 - 최고! </div>
	        <div><input type="radio" name="rating" value="4" />4 - 좋은작품! </div>
	        <div><input type="radio" name="rating" value="3" />3 - 괜찮음 </div>
	        <div><input type="radio" name="rating" value="2" />2 - 그럭저럭 </div>
	        <div><input type="radio" name="rating" value="1" />1 - 지뢰작 </div>
	        <p>
	        	<input type="submit" name="submit" value="OK">
	        	<button><a href="${pageContext.request.contextPath}/gallery?action=home">돌아가기</a></button>
	        </p>
        </form>
      </div>
    </div>
  </div>
<%-- 동적 컨텐츠(내용)이 들어감  --%>
<c:import url="footer.jsp"/>