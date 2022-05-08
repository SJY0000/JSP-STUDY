<jsp:include page="./fragments/header.jsp" />
<div class="main"></div>
<script>
  const mainlogo = document.querySelector('.main');
  mainlogo.addEventListener('click', function () {
    location.href = 'order.jsp';
  });
</script>
<jsp:include page="./fragments/footer.jsp" />
