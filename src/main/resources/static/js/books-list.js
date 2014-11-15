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
        $this.closest('tr').remove();
      }).fail(function (xhr) {
        alert('ERROR: something wrong');
      });
    }
  };

}());
