$(document).ready(function(){

    $('[data-toggle="datepicker"]').datepicker({
        autoHide: true,
        format: 'yyyy-mm-dd'
    });

    var addButton = $('.addRowBtn'); //Add button selector
    var wrapper = $('.wrapper'); //Input field wrapper
    var fieldHTML = '<div class="form-inline mb-2">\n' +
        '                                                <div class="form-group mr-5">\n' +
        '                                                    <input type="text" class="form-control rounded-0" name="firstname" placeholder="Firstname *" required="required" />\n' +
        '                                                </div>\n' +
        '                                                <div class="form-group mr-5">\n' +
        '                                                    <input type="text" class="form-control rounded-0" name="lastname" placeholder="Lastname *" required="required" />\n' +
        '                                                </div>\n' +
        '                                                <div class="form-group mr-5">\n' +
        '                                                    <input type="text" class="form-control rounded-0" name="pseudonym" placeholder="Pseudonym" />\n' +
        '                                                </div>\n' +
        '                                                <div class="form-group mr-4">\n' +
        '                                                    <input type="text" class="form-control rounded-0" data-toggle="datepicker" name="dob" placeholder="Date of birth *" required="required" />\n' +
        '                                                </div>\n' +
        '                                                <div class="form-group">\n' +
        '                                                    <button class="form-control removeBtn"><i class="text-danger ion-close-round"></i></button>\n' +
        '                                                </div>\n' +
        '                                            </div>'; //New input field html
    $(addButton).click(function(e){
        e.preventDefault();//Once add button is clicked
        $(wrapper).fadeIn().append(fieldHTML); // Add field html
        $('[data-toggle="datepicker"]').datepicker({
            autoHide: true,
            format: 'yyyy-mm-dd'
        });
    });
    $(wrapper).on('click', '.removeBtn', function(e){ //Once remove button is clicked
        e.preventDefault();
        $(this).closest('.form-inline').fadeOut().remove(); //Remove field html
    });

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


});