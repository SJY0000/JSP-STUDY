<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>ID 입력시 DB검색 후 결과 출력</h1>
    ID 입력 : <input onkeyup="sendServer()" type="text" />
    <div id="output"></div>
    <script type="text/javascript">
      const input = document.querySelector('input[type="text"]'); // type으로 선택할 떄는 대괄호([])사용
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax request 객체 생성

      // input.addEventListener('keyup', sendServer());
      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let id = input.value; // input에 입력한 값
        let url = 'getDB.jsp?id=' + id; // 요청할 jsp페이지 주소
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
