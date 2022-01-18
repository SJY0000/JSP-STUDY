<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:import url="header.jsp">
	<c:param name="title" value="단독이미지"/>
</c:import>
<!-- dataSource는 context.xml에 있는 name과 같아야함 -->
<sql:setDataSource var="ds" dataSource="jdbc/webshop"/> 

<sql:query var="results" dataSource="${ds}" sql="select * from images where id=?">
	<sql:param>${param.image}</sql:param> <!-- ?에 들어갈 변수 -->
</sql:query>

<c:set var="image" scope="page" value="${results.rows[0]}" /> <!-- 한개만 출력 -->
<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>

  <div class="container">
    <div class="heading">
      <h1><c:out value="${fn:toUpperCase(fn:substring(image.stem, 0, 1))}${fn:toLowerCase(fn:substring(image.stem, 1, -1))}" /></h1>
      <div class="rating">Rated: ${image.average_ranking}</div>
    </div>
    <div class="flex-box">
      <div class="picture">
        <div class="imageby">Image by <a href="#">${image.attribution_name}</a></div>
        <img src="${pageContext.request.contextPath}/pics/${picName}">
      </div>
      <div class="rating-radio">
      <form action="<c:url value="/gallery" />" method="post">
      <input type="hidden" name="action" value="rate">
      <input type="hidden" name="image" value="${image.id}">
        <h3>점수를 선택하기</h3>
	        <div><input type="radio" name="rating" value="5" />5 - 최고! </div>
	        <div><input type="radio" name="rating" value="4" />4 - 좋은작품! </div>
	        <div><input type="radio" name="rating" value="3" />3 - 괜찮음 </div>
	        <div><input type="radio" name="rating" value="2" />2 - 그럭저럭 </div>
	        <div><input type="radio" name="rating" value="1" />1 - 지뢰작 </div>
	        <p><input type="submit" name="submit" value="OK"></p>
        </form>
      </div>
    </div>
  </div>
<%-- 동적 컨텐츠(내용)이 들어감  --%>
<c:import url="footer.jsp"/>