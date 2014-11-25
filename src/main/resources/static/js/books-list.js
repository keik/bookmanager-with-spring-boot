'use strict';

(function () {

  $(function init() {
    $('#delete-btn').on('click', function (e) {
      e.preventDefault();

      var csrfHeader = $('meta[name=_csrf_header]').attr('content');
      var csrfToken = $('meta[name=_csrf]').attr('content');
      var headers = {};
      headers[csrfHeader] = csrfToken;

      var selected = [];
      $('[name=selected]:checked').each(function (idx, el) {
        selected.add(el.value);
      });

      $.ajax({
        url: 'books/' + selected.join(','),
        method: 'delete',
        headers: headers
      }).done(function (msg) {
        console.log(msg);
      }).fail(function (xhr) {
        console.log(xhr);
      });

      //$.ajax({
      //  url: '/books'
      //});
    });
  });

}());
