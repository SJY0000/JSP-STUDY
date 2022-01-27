<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>AJAX 연습</title>
  </head>
  <body>
    <h1>1부터 빈칸에 적은 숫자까지 총 합은?</h1>
    <input type="text" />
    <button onclick="sendServer();">계산하기</button>
    <div id="output"></div>
    <script type="text/javascript">
      const input = document.querySelector('input[type="text"]'); // type으로 선택할 떄는 대괄호([])사용
      const output = document.getElementById('output');
      const request = new XMLHttpRequest(); // ajax request 객체 생성

      function sendServer() {
        // 버튼을 누르면 실행되는 함수
        let v = input.value; // input에 입력한 값
        let url = 'sum.jsp?val=' + v; // 요청할 jsp페이지 주소
        request.open('GET', url, true);
        request.send();
        request.onreadystatechange = function () {
          if (request.readyState == 4 && request.status == 200) {
            let val = request.responseText; // 요청한 결과 받기
            console.log(val);
            output.textContent = val;
          }
        };
      }
    </script>
  </body>
</html>
