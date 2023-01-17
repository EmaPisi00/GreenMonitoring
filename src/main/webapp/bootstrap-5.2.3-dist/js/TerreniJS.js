$(document).ready(function() {

    $("#alrt").hide();

    $("#summit").click(function(){
        $("#rimuovi_terreno").submit();
    });
    $("#closeModal").click(function(){
        $('#Modal').modal('hide')
    });
});
function validate(){
    if($("#chk").prop("checked") == true){
        $('#Modal').modal('toggle');
    } else {
        $('#alrt').fadeIn();
    }
}