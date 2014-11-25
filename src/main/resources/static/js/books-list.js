'use strict';

(function () {

  $(function init() {
    $('#delete-btn').on('click', handlers.onClickDeleteBtn);
  });

  var handlers = {

    /**
     * Delete selected books
     */
    onClickDeleteBtn: function (e) {
      e.preventDefault();

      // Spring Security CSRF Protection
      var csrfHeader = $('meta[name=_csrf_header]').attr('content');
      var csrfToken = $('meta[name=_csrf]').attr('content');
      var headers = {};
      headers[csrfHeader] = csrfToken;

      // collect selected book ids
      var selectedIds = [];
      var $selected = $('[name=selected]:checked').each(function (idx, el) {
        selectedIds.push(el.value);
      });

      $.ajax({
        url: 'books/' + selectedIds.join(','),
        method: 'delete',
        headers: headers
      }).done(function () {
        $selected.closest('tr').remove();
      }).fail(function (xhr) {

        // TODO
        console.log(xhr);
        alert('Something wrong');
      });
    }
  };

}());
