$(document).ready(function() {
    $("#summit").click(function(){
        $("#rimuovi_terreno").submit();
    });
    $("#showModal").click(function(){
        $('#Modal').modal('toggle');
    });
    $("#closeModal").click(function(){
        $('#Modal').modal('hide')
    });


});
