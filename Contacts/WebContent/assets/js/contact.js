//console.log('테스트');
(function (path) {
  //코드 작성
  $('.table').DataTable({
    language: {
      lengthMenu: '표시할 줄수 선택    _MENU_',
      search: '검색',
      paginate: { previous: '이전', next: '다음' },
      info: '페이지 _PAGE_ / _PAGES_',
      infoEmpty: '데이터가 없습니다.',
      infoFiltered: '(전체 페이지 _MAX_ 에서 검색)',
      thousands: ',',
    },
    lengthMenu: [5, 10, 25], //한페이지 표시할 줄수
    pageLength: 5, //페이지의 갯수
    ordering: false, //열의 정렬기능(삭제)
    stateSave: true,
  });
  //추가버튼을 클릭하면 => 모달을 선택하고 제목바꾸기
  $('.btn-add').click(function (e) {
    //제이쿼리 변수는 $로 시작 (제이쿼리로 선택한 객체)
    const $modal = $('#modal-add-update');
    // 모달안에 title-add-upd를 찾음
    $modal.find('#title-add-upd').text('새 연락처');
    $modal.find('form').attr('action', path + '/contact?cmd=post');
  });
  // add , update의 submit 버튼을 클릭 => 추가 또는 업데이트 (AJAX)
  $('#add-update').on('submit', function (e) {
    e.preventDefault(); //submit 동작 중지
    e.stopPropagation(); //이벤트 중지
    $('.btn-action').prop('disabled', true); //모달창 닫기중지

    $.ajax({
      type: 'POST',
      url: $('#add-update').attr('action'),
      data: $('#add-update').serialize(), //form의 입력한 내용을 문자열로 변환
      dataType: 'json', //data를 json 타입으로 변환
    }).done(function (data) {
      if (data.status) {
        //요청결과를 성공적으로 받음
        $('#modal-add-update').modal('hide'); //닫기
        location.reload(); //새로고침
        //console.log(data);
      }
    });
  });
  //테이블에서 수정 버튼 클릭시 => 모달창 (id로 그 연락처 내용을 채움)
  $('table').on('click', '.btn-edit', function (e) {
    const $modal = $('#modal-add-update');
    // 모달안에 title-add-upd를 찾음
    $modal.find('#title-add-upd').text('업데이트');
    $modal.find('form').attr('action', path + '/contact?cmd=update');

    $.ajax({
      type: 'POST',
      url: path + '/contact?cmd=edit',
      data: 'id=' + $(this).data('id'), //클릭한 객체의 id속성값
      dataType: 'json', //받을때 타입
    })
      .done(function (data) {
        console.log(data);
        if (data.status) {
          //요청결과를 성공적으로 받음
          $('#name').val(data.contact.name);
          $('#email').val(data.contact.email);
          $('#phone').val(data.contact.phone);
          // 히든타입의 id 입력창을 넣는다 이때 id도 입력됨
          $modal.find('form').append('<input type="hidden" name="id" value="' + data.contact.id + '">');

          $modal.modal('show');
        }
      })
      .fail(function (jqXHR, textStatus) {
        console.log(textStatus);
      });
  });
  //테이블에서 삭제버튼 클릭 => 삭제 모달창 생성
  $('table').on('click', '.btn-delete', function (e) {
    $('#frm-delete').find('input[name=id]').val($(this).data('id'));
  });
  //삭제할때(삭제모달의 form의 submit버튼을 클릭했을때)
  $('#frm-delete').submit(function (e) {
    e.preventDefault();
    e.stopPropagation();
    $('.btn-action').prop('disabled', true);

    $.ajax({
      type: 'POST',
      url: path + '/contact?cmd=delete',
      data: $('#frm-delete').serialize(), //폼태그 입력내용을 문자열로 변환
      dataType: 'json', //받을때 타입
    })
      .done(function (data) {
        if (data.status) {
          //성공시
          $('#modal-delete').modal('hide'); //모달창 닫기
          location.reload(); //새로 고침
        }
      })
      .fail(function (jqXHR, textStatus) {
        //실패시
        console.log(textStatus);
      });
  });
})(path);
