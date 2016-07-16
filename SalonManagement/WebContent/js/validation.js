// CREATE BEGIN

$(document).ready(function () {

    $('#createEmpForm').validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success")
            $(form).ajaxSubmit();
            
            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.errorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strEmpFirstName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            },
            strEmpMiddleName: {
                regx: "^[A-Za-z\- '`]+$",
                noSpace: true,
                minlength: 2
            },
            strEmpLastName: {
                required: true,
                regx: "^[A-Za-z\- '`]+$",
                noSpace: true,
                minlength: 2
            },
            strBirthdate: {
                required: true,
                date: true
            },
            createAge: {
                required: true
            },
            strEmpGender: {
                required: true,
                valueNotEquals: "default"
            },
            strEmpEmail: {
                required: true,
                email: true
            },
            strEmpAddress: {
                required: true,
                regx: "^[a-zA-Z0-9\- `#.,]+$",
                noSpace: true,
                minlength: 10
            },
            selectedJob: {
                required: true,
                valueNotEquals: "default"
            }
        },
        messages: {
            strEmpFirstName: {
                required: "<span class='white-text'><b>First Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>First Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>First Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>First Name</b>: Minimum of 2 letters</span><br/>"
            },
            strEmpMiddleName: {
                regx: "<span class='white-text'><b>Middle Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Middle Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Middle Name</b>: Minimum of 2 letters</span><br/>"
            },
            strEmpLastName: {
                required: "<span class='white-text'><b>Last Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Last Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Last Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Last Name</b>: Minimum of 2 letters</span><br/>"
            },
            strBirthdate: {
                required: "<span class='white-text'><b>Birthday</b>: Required</span><br/>"
            },
            strEmpGender: {
                required: "<span class='white-text'><b>Gender</b>: Required</span><br/>",
                notEqualTo: "<span class='white-text'><b>Gender</b>: Required</span><br/>"
            },
            strEmpEmail: {
                required: "<span class='white-text'><b>Email</b>: Required</span><br/>",
                email: "<span class='white-text'><b>Email</b>: Invalid Email</span><br/>"
            },
            strEmpAddress: {
                required: "<span class='white-text'><b>Address</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Address</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Address</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Address</b>: Minimum of 10 characters</span><br/>"
            },
            selectedJob: {
                required: "<span class='white-text'><b>Position</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Position</b>: Required</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
       var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
       return arg != value;
    });
});


// CREATE END
// CREATE ADD OPTION
$(document).ready(function () {
    $("#createOption").validate({
        
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.errorCreateoption',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']")
        },
        rules: {
            addOptionName: {
                required: true,
                noSpace: true,
                regx: "^[a-zA-Z\- ]+$",
                minlength: 5
            }
        },
        messages: {
            addOptionName: {
                required: "<span class='white-text'><b>Option Name</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Option Name</b>: Empty field</span><br/>",
                regx: "<span class='white-text'><b>Option Name</b>: Invalid characters</span><br/>",
                minlength: "<span class='white-text'><b>Option Name</b>: Minimum of 5 letters</span><br/>"
            }
        }

    });
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
});
// CREATE ADD OPTION END

$('.updateSubmitForm').each(function () {
    $(this).click(function () {
        $('.updateEmpForm').valid();
    })
});

// UPDATE EMPLOYEE BEGIN
$(".updateEmpForm").each(function () {
    $(this).validate({

        submitHandler: function () {
            swal("Successfully updated!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.updateerror',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        ignore: "hidden",
        rules: {
            strEmpFirstName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            },
            strEmpMiddleName: {
                regx: "^[A-Za-z\- '`]+$",
                noSpace: true,
                minlength: 2
            },
            strEmpLastName: {
                required: true,
                regx: "^[A-Za-z\- '`]+$",
                noSpace: true,
                minlength: 2
            },
            strBirthdate: {
                required: true,
                date: true
            },
            createAge: {
                required: true
            },
            strEmpGender: {
                required: true,
                valueNotEquals: "default"
            },
            strEmpEmail: {
                required: true,
                email: true
            },
            strEmpAddress: {
                required: true,
                regx: "^[a-zA-Z0-9\- `#.,]+$",
                noSpace: true,
                minlength: 10
            },
            selectedJob: {
                required: true,
                valueNotEquals: "default"
            }
        },
        messages: {
            strEmpFirstName: {
                required: "<span class='white-text'><b>First Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>First Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>First Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>First Name</b>: Minimum of 2 letters</span><br/>"
            },
            strEmpMiddleName: {
                regx: "<span class='white-text'><b>Middle Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Middle Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Middle Name</b>: Minimum of 2 letters</span><br/>"
            },
            strEmpLastName: {
                required: "<span class='white-text'><b>Last Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Last Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Last Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Last Name</b>: Minimum of 2 letters</span><br/>"
            },
            strBirthdate: {
                required: "<span class='white-text'><b>Birthday</b>: Required</span><br/>"
            },
            strEmpGender: {
                required: "<span class='white-text'><b>Gender</b>: Required</span><br/>",
                notEqualTo: "<span class='white-text'><b>Gender</b>: Required</span><br/>"
            },
            strEmpEmail: {
                required: "<span class='white-text'><b>Email</b>: Required</span><br/>",
                email: "<span class='white-text'><b>Email</b>: Invalid Email</span><br/>"
            },
            strEmpAddress: {
                required: "<span class='white-text'><b>Address</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Address</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Address</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Address</b>: Minimum of 10 characters</span><br/>"
            },
            selectedJob: {
                required: "<span class='white-text'><b>Position</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Position</b>: Required</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});

// UPDATE EMPLOYEE END
// ADD OPTION UPDATE EMPLOYEE
$('#updateOptionForm').each(function () {
    $(this).validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.errorUpdateoption',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']")
        },
        rules: {
            updateAddOptionName: {
                required: true,
                noSpace: true,
                regx: "^[a-zA-Z\- ]+$",
                minlength: 5
            }
        },
        messages: {
            updateAddOptionName: {
                required: "<span class='white-text'><b>Option Name</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Option Name</b>: Empty field</span><br/>",
                regx: "<span class='white-text'><b>Option Name</b>: Invalid characters</span><br/>",
                minlength: "<span class='white-text'><b>Option Name</b>: Minimum of 5 letters</span><br/>"
            }
        }

    });
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
});
// ADD OPTION UPDATE EMPLOYEE END

// CREATE PROD SVC
$(document).ready(function () {

    $('#createProductForm').validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.crproderrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strItemName: {
                required: true,
                regx: "^[A-Za-z0-9\- #'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strItemDetails: {
                required: true,
                noSpace: true,
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            dblItemPrice: {
                required: true,
                valueNotEquals: "Php 0.00"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 5 letters</span><br/>"
            },
            strItemDetails: {
                required: "<span class='white-text'><b>Details</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Details</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            dblItemPrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
// CREATE PROD SVC END

// CREATE PROD SVC ADD CATEGORY
$(document).ready(function () {
    $("#createAddCatForm").validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.crprodcat',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            crProdAddCatName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 5
            }
        },
        messages: {
            crProdAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 5 characters</span><br/>",
            }
        }

    });
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
// CREATE PROD SVC ADD CATEGORY END

// UPDATE PROD FORM
$('.upProdSubmitBtn').each(function () {
    $(this).click(function () {
        $('.updateProdForm').valid();
    })
});


$(".updateProdForm").each(function () {
    $(this).validate({

        submitHandler: function () {
            swal("Successfully updated!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.upproderrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strItemName: {
                required: true,
                regx: "^[A-Za-z0-9\- #'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strItemDetails: {
                required: true,
                noSpace: true,
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            dblItemPrice: {
                required: true,
                valueNotEquals: "Php 0.00"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 5 letters</span><br/>"
            },
            strItemDetails: {
                required: "<span class='white-text'><b>Details</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Details</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            dblItemPrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });
    
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
// UPDATE PROD FORM
//UPDATE CREATE NEW CATEGORY
$(".upProdAddCateForm").each(function () {
    $(this).validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.upprodcat',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            upAddProdCatName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 5
            }
        },
        messages: {
            upAddProdCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 5 characters</span><br/>"
            }
        }

    });
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
//UPDATE CREATE NEW CATEGORY END

// UPDATE SERVICE FORM
$(document).ready(function () {

    $('#createServiceForm').validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.crserverrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strItemName: {
                required: true,
                regx: "^[A-Za-z0-9\- #'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strItemDetails: {
                required: true,
                noSpace: true,
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            dblItemPrice: {
                required: true,
                valueNotEquals: "Php 0.00"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 5 letters</span><br/>"
            },
            strItemDetails: {
                required: "<span class='white-text'><b>Details</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Details</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            dblItemPrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
// UPDATE SERVICE FORM END
//CREATE SERVICE ADD CATEGORY
$(document).ready(function () {
    $("#createServAddCatForm").validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.crservcat',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            crServAddCatName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 5
            }
        },
        messages: {
            crServAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 5 characters</span><br/>",
            }
        }

    });
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});


//UPDATE ADD SERVICE
$('.updateservForm').each(function () {

    $(this).validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.upserverrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strItemName: {
                required: true,
                regx: "^[A-Za-z0-9\- #'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strItemDetails: {
                required: true,
                noSpace: true,
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            dblItemPrice: {
                required: true,
                valueNotEquals: "Php 0.00"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 5 letters</span><br/>"
            },
            strItemDetails: {
                required: "<span class='white-text'><b>Details</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Details</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            dblItemPrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
//UPDATE ADD SERVICE END
//UPDATE ADD SERVICE CATEGORY
$('.upServAddCatForm').each(function () {
    $(this).validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.upservcat',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            upServAddCatName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 5
            }
        },
        messages: {
            upServAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 5 characters</span><br/>",
            }
        }

    });
    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
//UPDATE ADD SERVICE CATEGORY END

//CREATE DELIVERY CHARGE
$(document).ready(function () {

    $('#createDeliveryForm').validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.deliveryerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strBrgy: {
                required: true,
                regx: "^[A-Za-z0-9\- #,.'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strCity: {
                required: true,
                regx: "^[A-Za-z0-9\- #,.'`\s]+$",
                noSpace: true,
                minlength: 2
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00"
            }
        },
        messages: {
            strBrgy: {
                required: "<span class='white-text'><b>Barangay</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Barangay</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Barangay</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Barangay</b>: Minimum of 5 letters</span><br/>"
            },
            strCity: {
                required: "<span class='white-text'><b>City</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>City</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>City</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>City</b>: Minimum of 2 letters</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});


$('.updateDeliveryForm').each(function () {

    $(this).validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success")
            $(form).ajaxSubmit();

            // Materialize.toast('Successfully Created!', 5000, 'green');


        },
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.updeliveryerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strBrgy: {
                required: true,
                regx: "^[A-Za-z0-9\- #,.'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strCity: {
                required: true,
                regx: "^[A-Za-z0-9\- #,.'`\s]+$",
                noSpace: true,
                minlength: 2
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00"
            }
        },
        messages: {
            strBrgy: {
                required: "<span class='white-text'><b>Barangay</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Barangay</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Barangay</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Barangay</b>: Minimum of 5 letters</span><br/>"
            },
            strCity: {
                required: "<span class='white-text'><b>City</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>City</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>City</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>City</b>: Minimum of 2 letters</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
            }
        }

    });

    $('form').on('submit', function (e) {
        $(".error_note").remove();
        var select = $(this).find('select').filter("[required=required]");
        $.each(select, function (index, elm) {
            val = $(this).val();
            target = $(this).closest('.input-field');
            if (typeof target !== "undefined") {
                input_target = target.find('input.select-dropdown');
                if (typeof input_target !== "undefined") {
                    if (val == '' || val == false || val == 0 || val == null) {

                        input_target.css({'border-color': '#EA454B', 'box-shadow': '0 1px 0 0 #EA454B'});

                        $('html,body').animate({scrollTop: $("body").offset().top}, 'slow');
                        e.preventDefault();

                    } else {
                        input_target.css({'border-color': '#9e9e9e'});
                    }

                }
            }
        });
    });

    $.validator.addMethod("regx", function(value, element, regexp){
        var re = new RegExp(regexp);
        return this.optional(element) || re.test(value);
    });
    $.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    });
    $.validator.addMethod("valueNotEquals", function(value, element, arg){
        return arg != value;
    });
});
//CREATE DELIVERY CHARGE END


// CREATE PACKAGE FORM
$().ready(function () {
    $("#createPackageForm").validate({

        submitHandler: function () {
            Materialize.toast('Successfully Updated!', 5000, 'green');
            $(form).ajaxSubmit();
        },
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']")
                .append(error);
        },
        errorElement: "span",
        rules: {
            strPackageName: {
                required: true,
                minlength: 2
            },
            strPackageDesc: {
                minlength: 5
            },
            intPackageType: {
                required: true
            },
            dblPackagePrice: {
                required: true
            }
        },
        messages: {
            strPackageName: {
                required: " (Required)",
                minlength: " (Must be at least 2 letters)"
            },
            strPackageDesc: {
                minlength: " (Must be at least 5 letters)"
            },
            intPackageType: {
                required: " (Required)"
            },
            dblPackagePrice: {
                required: " (Required)"
            }
        }
    });
    jQuery.validator.addMethod("specialname", function (value, element) {
        return this.optional(element) || /([a-zA-Z-`'\s])$/.test(value);
    }, "<span class='red-text'> (A-z ` - ' are allowed)</span>");

    jQuery.validator.addMethod("specialprodsvc", function (value, element) {
        return this.optional(element) || /([a-zA-Z-`'\s])$/.test(value);
    }, "<span class='red-text'> (A-z - are allowed)</span>");

    jQuery.validator.addMethod("specialaddress", function (value, element) {
        return this.optional(element) || /([#A-Za-z0-9\s.,-])$/.test(value);
    }, "<span class='red-text'> (A-z 0-9 . , - # are allowed)</span>");

    jQuery.validator.addMethod("specialprice", function (value, element) {
        return this.optional(element) || /([0-9])$/.test(value);
    }, "<span class='red-text'> (Numbers only)</span>");

    jQuery.validator.addMethod("specialoption", function (value, element) {
        return this.optional(element) || /([a-zA-Z\s])$/.test(value);
    }, "<span class='red-text'> (Letters and spaces are allowed)</span>");

    jQuery.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    }, " (Empty field)");

    jQuery.validator.addClassRules("group-required", {
        require_from_group: [1, ".group-required"]
    }, " (Required)");

});
// CREATE PACKAGE FORM END

// CREATE PROMO
$().ready(function () {
    $("#createPromoForm").validate({

        submitHandler: function () {
            Materialize.toast('Successfully Updated!', 5000, 'green');
            $(form).ajaxSubmit();
        },
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']")
                .append(error);
        },
        errorElement: "span",
        rules: {
            strPromoName: {
                required: true,
                minlength: 2
            },
            strPromoDesc: {
                minlength: 5
            },
            require_from_group: [1, ".fillone"]
        },
        messages: {
            strPromoName: {
                required: " (Required)",
                minlength: " (Must be at least 2 letters)"
            },
            strPromoDesc: {
                minlength: " (Must be at least 5 letters)"
            },
            require_from_group: " (Required)"
        }
    });
    jQuery.validator.addMethod("specialname", function (value, element) {
        return this.optional(element) || /([a-zA-Z-`'\s])$/.test(value);
    }, "<span class='red-text'> (A-z ` - ' are allowed)</span>");

    jQuery.validator.addMethod("specialprodsvc", function (value, element) {
        return this.optional(element) || /([a-zA-Z-`'\s])$/.test(value);
    }, "<span class='red-text'> (A-z - are allowed)</span>");

    jQuery.validator.addMethod("specialaddress", function (value, element) {
        return this.optional(element) || /([#A-Za-z0-9\s.,-])$/.test(value);
    }, "<span class='red-text'> (A-z 0-9 . , - # are allowed)</span>");

    jQuery.validator.addMethod("specialprice", function (value, element) {
        return this.optional(element) || /([0-9])$/.test(value);
    }, "<span class='red-text'> (Numbers only)</span>");

    jQuery.validator.addMethod("specialoption", function (value, element) {
        return this.optional(element) || /([a-zA-Z\s])$/.test(value);
    }, "<span class='red-text'> (Letters and spaces are allowed)</span>");

    jQuery.validator.addMethod("noSpace", function (value, element) {
        return value.indexOf(" ") != "";
    }, " (Empty field)");
    jQuery.validator.addClassRules("fillone", {
        require_from_group: [1, ".fillone"]
    }, " (Required)");


    jQuery.validator.addClassRules("fillthree", {
        require_from_group: [1, ".fillthree"]
    }, " (Required)");

});
// CREATE PROMO END