$(document).ready(function() {
  $('[data-toggle=offcanvas]').click(function() {
    $(this).toggleClass('visible-xs text-center');
    $(this).find('i').toggleClass('glyphicon-chevron-right glyphicon-chevron-left');
    $('.row-offcanvas').toggleClass('active');
    $('#lg-menu').toggleClass('hidden-xs').toggleClass('visible-xs');
    $('#xs-menu').toggleClass('visible-xs').toggleClass('hidden-xs');
    $('#btnShow').toggle();
  });

  onClickSubmit();
  setDefaultLatLng();

  function onClickSubmit() {

    $( "#form" ).submit(function( event ) {
      event.preventDefault();
      window.location.href = '/subcriptions.html';
    });
  }

  function setDefaultLatLng() {
    $('#lat').val(6.9329258);
    $('#lng').val(79.8632811);
  }
});
