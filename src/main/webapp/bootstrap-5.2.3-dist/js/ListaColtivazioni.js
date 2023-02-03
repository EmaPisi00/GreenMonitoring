$(document).ready(function() {

    $("#alrt").hide();

    $("#summit").click(function(){
        if ($('#chk:checked').length == 0) {
            $("#alrt").fadeIn();
            return false;
        }
        else {
            $("#aggiungi_coltivazione").submit();
        }
    });
});
