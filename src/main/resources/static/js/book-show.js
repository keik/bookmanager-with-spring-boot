'use strict';

(function () {

  _.templateSettings.escape = /{%-([\s\S]+?)%}/g,
  _.templateSettings.evaluate = /{%([\s\S]+?)%}/g,
  _.templateSettings.interpolate = /{%=([\s\S]+?)%}/g,

  $(function init() {

    // Tags operation
    $('#js-edit-tags-btn').on('click', handlers.onClickEditTagsBtn);
    $('#js-add-tag-form').on('submit', handlers.onSubmitAddTagForm);
    $(document).on('click', '.js-delete-tag-btn', handlers.onClickDeleteTagBtn);
    $('#js-cancel-tags-btn').on('click', handlers.onClickCancelTagsBtn);

    // Comments operation
    $(document).on('click',  '.js-edit-comment-btn',   handlers.onClickEditCommentBtn);
    $(document).on('submit', '.js-edit-comment-form',  handlers.onSubmitEditCommentForm);
    $(document).on('click',  '.js-cancel-comment-btn', handlers.onClickCancelCommentBtn);
  });

  var handlers = {

    /**
     * Open edit tags area
     */
    onClickEditTagsBtn: function (e) {
      e.preventDefault();

      $('#js-add-tag-form').show();
      $('.js-delete-tag-btn').show();
    },

    /**
     * Add new tag to the book
     */
    onSubmitAddTagForm: function (e) {
      e.preventDefault();

      var $form = $(this);
      $.ajax({
        url: $form.attr('action'),
        method: $form.attr('method'),
        data: $form.serialize()
      }).done(function (newTag) {

        var newTagHtml = _.template($('#js-tag-template').text(), newTag);
        $('#js-tags').append(newTagHtml);

        // Clear inputed values
        $form.find('input:not([type=hidden])').val('');

      }).fail(function (xhr) {

        // TODO
        console.log(xhr);
        alert('Something wrong');
      });
    },

    onClickDeleteTagBtn: function (e) {
      e.preventDefault();

      var $this = $(this);
      var $form = $($this.attr('href'));

      $.ajax({
        url: $form.attr('action'),
        method: $form.attr('method'),
        headers: getCsrfHeader()
      }).done(function (msg) {

        // Update HTML with updated comment
        $this.closest('.js-tag').remove();
      }).fail(function (xhr) {

        // TODO
        console.log('fail', xhr);
        alert('Something wrong');
      });
    },

    /**
     * Close edit tag area
     */
    onClickCancelTagsBtn: function (e) {
      e.preventDefault();

      $('#js-add-tag-form').hide();
      $('.js-delete-tag-btn').hide();
    },

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

      var $form = $(this);
      var $comment = $form.closest('.js-comment');

      $.ajax({
        url: $form.attr('action'),
        method: $form.attr('method'),
        data: $form.serialize(),
        headers: getCsrfHeader()
      }).done(function () {

        // Update HTML with updated comment
        $comment.find('.js-comment-content').text($form[0].content.value);
        $comment.find('.js-edit-comment-form').hide();
        $comment.find('.js-comment-content').show();
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
