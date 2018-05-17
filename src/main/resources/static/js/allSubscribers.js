$(document).ready( function () {
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
} );

