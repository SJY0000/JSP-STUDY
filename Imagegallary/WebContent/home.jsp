<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<c:import url="header.jsp">
	<c:param name="title" value="홈페이지"/>
</c:import>
<!-- dataSource는 context.xml에 있는 name과 같아야함 -->
<sql:setDataSource var="ds" dataSource="jdbc/webshop"/> 

<sql:query var="results" dataSource="${ds}" sql="select * from images order by id" />

<table class="images">
	<c:set var="tablewidth" value="8"/>

	
	<c:forEach var="image" items="${results.rows}" varStatus="row">
		
		<c:if test="${row.index % tablewidth == 0}"><tr></c:if>
		
		<c:set var="picName" scope="page" value="${image.stem}.${image.image_extension}"/>

		<td>
			<a href="<c:url value="/gallery?action=image&image=${image.id}"/>">
				<img src="${pageContext.request.contextPath}/pics/${picName}">
			</a>
		</td>
		
		<c:if test="${row.index+1 % tablewidth == 0}"></tr></c:if>

	</c:forEach>
</table>

<%-- 동적 컨텐츠(내용)이 들어감  --%>
<c:import url="footer.jsp"/>
