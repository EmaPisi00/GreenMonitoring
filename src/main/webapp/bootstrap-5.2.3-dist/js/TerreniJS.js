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
        $('#alrt').fadeOut();
        $('#Modal').modal('toggle');
        return true;
    } else {
        $('#alrt').fadeIn();
        return false;
    }
}