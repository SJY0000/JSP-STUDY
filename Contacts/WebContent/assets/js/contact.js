(function (path) {
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
    lengthMenu: [5, 10, 25], // 한 페이지에 표시할 row 수
    pageLength: 5, // 표시할 페이지 갯수
    ordering: false, // 열의 정렬 기능
    stateSave: true,
  });
  // 추가 버튼을 클릭하면 => Modal을 선택하고 제목바꾸기
  $('.btn-add').click(function (e) {
    // JQuery로 변수는 $로 시작 (JQuery로 선택한 객체)
    const $modal = $('#modal-add-update');
    // find는 Modal의 title-add-upd를 찾음
    $modal.find('#title-add-upd').text('연락처 추가');
    $modal.find('form').attr('action', path + '/Contacts?action=post');
  });
})(path);
