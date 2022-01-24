<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link
      rel="stylesheet"
      href="<%=request.getContextPath()%>/css/bootstrap.min.css"
    />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
    <title>Todo리스트</title>
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
            <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/list"
                  >ToDo 리스트</a
                >
              </li>
            </ul>
            <ul class="navbar-nav mb-2">
              <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/logout"
                  >로그아웃</a
                >
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
    <!-- navbar 끝 -->
    <!-- 본문 -->
    <div class="container">
      <h3 class="text-center">DO IT list's</h3>
      <hr />
      <div class="container text-left">
        <a href="" class="btn btn-success">할일 추가</a>
      </div>
      <br />
      <table class="table table-bordered">
        <thead>
          <tr>
            <th>제목</th>
            <th>마감 일자</th>
            <th>현재 상태</th>
            <th>액션</th>
          </tr>
        </thead>
        <tbody></tbody>
      </table>
    </div>
    <!-- 본문 끝 -->
    <jsp:include page="../common/footer.jsp" />
    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
