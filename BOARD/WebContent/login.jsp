<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css" />
    <title>게시판</title>
  </head>
  <body>
    <jsp:include page="common/header.jsp" />

    <main class="form-signin">
      <div class="container">
        <div class="col-md-3 mx-auto">
          <form class="mb-6">
          
            <img class="d-flex justify-content-center" src="https://cdn.pixabay.com/photo/2012/04/26/19/08/note-42883_960_720.png" alt="" width="72" height="57" />
            <div class="form-floating mt-5">
              <input type="email" class="form-control" id="floatingInput" placeholder="name@example.com" />
              <label for="floatingInput">Id</label>
            </div>
            <div class="form-floating mt-2">
              <input type="password" class="form-control" id="floatingPassword" placeholder="Password" />
              <label for="floatingPassword">Password</label>
            </div>
            <div class="d-grid mt-3">
              <button class="btn btn-lg btn-primary" type="submit">로그인</button>
            </div>
          </form>
        </div>
      </div>
    </main>
    <jsp:include page="common/footer.jsp"></jsp:include>
    <script src="<%=request.getContextPath()%>/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
