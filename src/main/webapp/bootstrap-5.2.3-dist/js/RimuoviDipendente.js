$(document).ready(function() {

    $("#alrt").hide();

    $("#summit").click(function(){
        $("#rimuovi_dipendente").submit();
    });

    $("#showModal").click(function() {
            if ($('#chk:checked').length == 0) {
                $("#alrt").fadeIn();
                return false;
            }
            else {
                $("#alrt").fadeOut();
                $('#Modal').modal('toggle');
            }
        });

    $("#closeModal").click(function(){
        $('#Modal').modal('hide')
    });
});
