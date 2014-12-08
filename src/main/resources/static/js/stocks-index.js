'use strict';

(function () {

  $(function init() {
    $('#js-delete-btn').on('click', handlers.onClickDeleteBtn);
  });

  var handlers = {

    /**
     * Delete selected books
     */
    onClickDeleteBtn: function (e) {
      e.preventDefault();

      var $this = $(this);
      var $form = $($this.attr('href'));

      // collect selected book ids
      var selectedIds = [];
      var $selected = $('[name=selected]:checked').each(function (idx, el) {
        selectedIds.push(el.value);
      });

      $.ajax({
        url: $form.attr('action') + '/' + selectedIds.join(','),
        method: $form.attr('method'),
        headers: getCsrfHeader()
      }).done(function () {
        $selected.closest('tr').remove();
      }).fail(function (xhr) {

        // TODO
        console.log(xhr);
        alert('Something wrong');
      });
    }
  };

  function getCsrfHeader() {

    // Spring Security CSRF Protection
    var csrfHeader = $('meta[name=_csrf_header]').attr('content');
    var csrfToken = $('meta[name=_csrf]').attr('content');
    var headers = {};
    headers[csrfHeader] = csrfToken;
    return headers;
  }

}());
