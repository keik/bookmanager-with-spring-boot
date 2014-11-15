'use strict';

(function () {

  $(function init() {
    $(document).on('click', '.delete-btn', handlers.onClickDeleteBtn);
  });

  var handlers = {
    onClickDeleteBtn: function (e) {
      var $this = $(this);
      $.ajax({
        url: '/books/' + $this.data('id'),
        method: 'delete'
      }).done(function (msg) {
        console.log('done', msg);
        $this.closest('tr').remove();
      }).fail(function (xhr) {
        console.log('fail', xhr);
        alert('ERROR: something wrong');
      });
    }
  };

}());
