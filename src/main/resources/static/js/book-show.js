'use strict';

(function () {

  $(function init() {
    $(document).on('click',  '.js-edit-comment-btn',   handlers.onClickEditCommentBtn);
    $(document).on('click',  '.js-cancel-comment-btn', handlers.onClickCancelCommentBtn);
    $(document).on('submit', '.js-edit-comment-form',  handlers.onSubmitEditCommentForm);
  });

  var handlers = {

    /**
     * Open edit comment area
     */
    onClickEditCommentBtn: function (e) {
      e.preventDefault();

      var $comment = $(this).closest('.js-comment');
      $comment.find('.js-edit-comment-form').show();
      $comment.find('.js-comment-content').hide();
    },

    /**
     * Close edit comment area
     */
    onClickCancelCommentBtn: function (e) {
      e.preventDefault();

      var $comment = $(this).closest('.js-comment');
      var $form = $comment.find('.js-edit-comment-form').hide();
      var $content = $comment.find('.js-comment-content').show();

      // Restore form content value
      $form[0].content.value = $content.text();
    },

    /**
     * Update comment
     */
    onSubmitEditCommentForm: function (e) {
      e.preventDefault();

      var $this = $(this);
      var $comment = $this.closest('.js-comment');

      // Spring Security CSRF Protection
      var csrfHeader = $('meta[name=_csrf_header]').attr('content');
      var csrfToken = $('meta[name=_csrf]').attr('content');
      var headers = {};
      headers[csrfHeader] = csrfToken;

      $.ajax({
        url: $this.attr('action'),
        method: $this.attr('method'),
        data: $this.serialize(),
        headers: headers
      }).done(function () {

        console.log($this.serializeArray());
        // Update HTML with updated comment
        $comment.find('.js-comment-content').text($this[0].content.value);
        $comment.find('.js-edit-comment-form').hide();
        $comment.find('.js-comment-content').show();

      }).fail(function (xhr) {

        // TODO
        console.log(xhr);
        alert('Something wrong');
      });
    }

  };

}());
