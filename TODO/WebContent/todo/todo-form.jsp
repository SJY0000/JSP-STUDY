<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <title>Todo Form</title>
  </head>
  <body>
    <header>
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">TODO APP</a>
          <button
            class="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span class="navbar-toggler-icon"></span>
          </button>
          <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/todos?action=list">ToDo 리스트</a>
              </li>
            </ul>
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout">로그아웃</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <!-- navbar 끝 -->
    <!-- 본문 -->
    <div class="container">
      <div class="row mt-5">
        <div class="col-md-5">
          <div class="card card-body">
          <!-- 만약에 todo가 null이면 새로입력 , todo가 있으면 update -->
          <c:if test="${todo != null}">
          	<form action="<%=request.getContextPath()%>/todos">
          	<input type="hidden" name="action" value="update" >
          </c:if>
          <c:if test="${todo == null}">
          	<form action="<%=request.getContextPath()%>/todos">
          	<input type="hidden" name="action" value="insert" > 
          </c:if>
          
          <h2>
          	<c:if test="${todo != null}"> 할 일 수정</c:if>
          	<c:if test="${todo == null}"> 할 일 추가</c:if>
          </h2>
          
          <c:if test="${todo != null }">
          	<input type="hidden" name="id" value='<c:out value="${todo.id}" />' /> 
          </c:if>
          
          <div class="form-group mt-3">
          	<label>제목</label>
          	<input type="text" class="form-control" name="title" value='<c:out value="${todo.title}" />' required /> 
          </div>
          <div class="form-group mt-3">
          	<label>할 일 설명</label>
          	<input type="text" class="form-control" name="description" value='<c:out value="${todo.description}" />' required /> 
          </div>
          <div class="form-group mt-3">
          	<label>현재 상태</label>
          	<select class="form-control" name="isDone">
          		<option value="false">진행중</option>
          		<option value="true">완료</option>
          	</select>
          </div>
		  <div class="form-group mt-3">
          	<label>목표 일자</label>
          	<input type="date" class="form-control" name="targetDate" value='<c:out value="${todo.targetDate}" />' required /> 
          </div>
          <div class="form-group mt-3">
          	<button type="submit" class="btn btn-info">저장</button>
          </div>
          </form>
          </div>
        </div>
      </div>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />
    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
