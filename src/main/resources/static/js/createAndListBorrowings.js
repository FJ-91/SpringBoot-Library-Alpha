$( document ).ready(function() {

    $(".chosen-select").chosen();

    $('table').DataTable({
        responsive: {
            details: {
                type: 'column'
            }
        },

        columnDefs: [ {
            className: 'control',
            orderable: false,
            targets:   0
        } ],

        order: [ 1, 'asc' ],

        dom: 'Bfrtip'

    });

    $('.chosen-container-single').attr('style','width: 100%');

});