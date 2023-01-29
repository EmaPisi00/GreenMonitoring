$(document).ready(function() {

    $("#alrt").hide();

    $("#summit").click(function(){
        $("#associa_dipendente").submit();
    });


    $("#showModal").click(function() {
                $("#alrt").fadeOut();
                $('#Modal').modal('toggle');
        });

    $("#closeModal").click(function(){
        $('#Modal').modal('hide')
    });
});
