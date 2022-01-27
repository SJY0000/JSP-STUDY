<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>회원가입 시 Email 중복 체크</h1>
    <form name="myform">
    	<input type="email" name="email" placeholder="Email 입력해주세요."/>
    	<input type="button" onclick="sendServer()" value="중복체크"/>
    </form>
    <div id="output"></div>
    <script type="text/javascript">
      const input = document.querySelector('input[type="email"]'); // type으로 선택할 떄는 대괄호([])사용
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax request 객체 생성

      // input.addEventListener('keyup', sendServer());
      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let email = input.value; // input에 입력한 값
        let url = 'emailcheck.jsp?email=' + email; // 요청할 jsp페이지 주소
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = getInfo;
      }
      
      function getInfo() {
          if (request.readyState == 4 && request.status == 200) {
            let info = request.responseText; // 요청한 결과 받기
            output.textContent = info;
          }
        };
    </script>
  </body>
</html>
