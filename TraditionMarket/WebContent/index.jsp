<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css" />
<title>Insert title here</title>
</head>
<body>
 <header>
      <nav class="navbar navbar-expand-lg navbar-dark bg-secondary">
        <div class="container-fluid">
          <a class="navbar-brand" href="#">전,알,사</a>
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
                <a class="nav-link" href="<%= request.getContextPath() %>">홈</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/Boards?action=notice">공지사항</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="<%= request.getContextPath() %>/Boards?action=board">게시판</a>
              </li>
            </ul>
            <ul class="navbar-nav ms-auto">
              <li class="nav-item">
                <p class="nav-link">님</p>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="">/&nbsp;&nbsp;&nbsp;마이페이지</a>
              </li>
              <li class="nav-item">
                <a class="nav-link" href="">/&nbsp;&nbsp;&nbsp;로그아웃</a>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </header>
<h1>home</h1>
<script type="text/javascript">
console.log("<%= request.getContextPath() %>");
</script>
</body>
</html>