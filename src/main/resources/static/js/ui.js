'use strict';

(function () {

  $(function init() {

    $(document).on('click', '.dropdown-btn', function (e) {
      e.preventDefault();
      var $this = $(this);
      var $dropdownList = $('#' + $this.attr('aria-controls'));
      if ($dropdownList.is(':hidden')) {
        var offset = $this.offset();
        $dropdownList
          .css({top: offset.top + $this.outerHeight() + 2, left: offset.left})
          .show();
        $(document).one('click', function () { $dropdownList.hide(); });
      }
    });
  });


}());
