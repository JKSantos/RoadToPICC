// CREATE BEGIN

$(document).ready(function () {

    $('#createEmpForm').validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success");
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
            strEmpContactNo: {
                customphone: true
            },
            strEmpEmail: {
                required: true,
                email: true
            },
            strEmpAddress: {
                required: true,
                regx: "^[a-zA-Z0-9\- `#.,]+$",
                noSpace: true,
                minlength: 5
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
            strEmpContactNo: {
                customphone: "<span class='white-text'><b>Contact</b>: Invalid number</span><br/>"
            },
            strEmpEmail: {
                required: "<span class='white-text'><b>Email</b>: Required</span><br/>",
                email: "<span class='white-text'><b>Email</b>: Invalid Email</span><br/>"
            },
            strEmpAddress: {
                required: "<span class='white-text'><b>Address</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Address</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Address</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Address</b>: Minimum of 5 characters</span><br/>"
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
    $.validator.addMethod('customphone', function (value, element) {
        return this.optional(element) || /09[0-9]{2}-[0-9]{3}-[0-9]{4}/.test(value);
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
                minlength: 2
            }
        },
        messages: {
            addOptionName: {
                required: "<span class='white-text'><b>Option Name</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Option Name</b>: Empty field</span><br/>",
                regx: "<span class='white-text'><b>Option Name</b>: Invalid characters</span><br/>",
                minlength: "<span class='white-text'><b>Option Name</b>: Minimum of 2 letters</span><br/>"
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
            swal("Successfully updated!", "", "success");
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
            strEmpContactNo: {
                customphone: true
            },
            strEmpEmail: {
                required: true,
                email: true
            },
            strEmpAddress: {
                required: true,
                regx: "^[a-zA-Z0-9\- `#.,]+$",
                noSpace: true,
                minlength: 5
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
            strEmpContactNo: {
                customphone: "<span class='white-text'><b>Contact</b>: Invalid number</span><br/>"
            },
            strEmpEmail: {
                required: "<span class='white-text'><b>Email</b>: Required</span><br/>",
                email: "<span class='white-text'><b>Email</b>: Invalid Email</span><br/>"
            },
            strEmpAddress: {
                required: "<span class='white-text'><b>Address</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Address</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Address</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Address</b>: Minimum of 5 characters</span><br/>"
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
    $.validator.addMethod('customphone', function (value, element) {
        return this.optional(element) || /09[0-9]{2}-[0-9]{3}-[0-9]{4}/.test(value);
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
                minlength: 2
            }
        },
        messages: {
            updateAddOptionName: {
                required: "<span class='white-text'><b>Option Name</b>: Required</span><br/>",
                noSpace: "<span class='white-text'><b>Option Name</b>: Empty field</span><br/>",
                regx: "<span class='white-text'><b>Option Name</b>: Invalid characters</span><br/>",
                minlength: "<span class='white-text'><b>Option Name</b>: Minimum of 2 letters</span><br/>"
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
            swal("Successfully created!", "", "success");
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
                minlength: 2
            },
            strItemDetails: {
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 2 letters</span><br/>"
            },
            strItemDetails: {
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
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
                minlength: 2
            }
        },
        messages: {
            crProdAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 2 characters</span><br/>",
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
            swal("Successfully updated!", "", "success");
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
                minlength: 2
            },
            strItemDetails: {
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"

            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 2 letters</span><br/>"
            },
            strItemDetails: {
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
// UPDATE PROD FORM
//UPDATE CREATE NEW CATEGORY
$(".upProdAddCatForm").each(function () {
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
            upProdAddCatName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            }
        },
        messages: {
            upProdAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 2 characters</span><br/>"
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
            swal("Successfully created!", "", "success");
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
                minlength: 2
            },
            strItemDetails: {
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 2 letters</span><br/>"
            },
            strItemDetails: {
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
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
                minlength: 2
            }
        },
        messages: {
            crServAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 2 characters</span><br/>",
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
            swal("Successfully created!", "", "success");
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
                minlength: 2
            },
            strItemDetails: {
                minlength: 2
            },
            strItemCategory: {
                required: true,
                valueNotEquals: "default"
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strItemName: {
                required: "<span class='white-text'><b>Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Name</b>: Minimum of 2 letters</span><br/>"
            },
            strItemDetails: {
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            strItemCategory: {
                required: "<span class='white-text'><b>Category</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Category</b>: Required</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
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
                minlength: 2
            }
        },
        messages: {
            upServAddCatName: {
                required: "<span class='white-text'><b>Category Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Category Name</b>: Invalid character</span><br/>",
                noSpace: "<span class='white-text'><b>Category Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Category Name</b>: Minimum of 2 characters</span><br/>",
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
            swal("Successfully created!", "", "success");
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
                minlength: 2
            },
            strCity: {
                required: true,
                regx: "^[A-Za-z0-9\- #,.'`\s]+$",
                noSpace: true,
                minlength: 2
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strBrgy: {
                required: "<span class='white-text'><b>Barangay</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Barangay</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Barangay</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Barangay</b>: Minimum of 2 letters</span><br/>"
            },
            strCity: {
                required: "<span class='white-text'><b>City</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>City</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>City</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>City</b>: Minimum of 2 letters</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});


$('.updateDeliveryForm').each(function () {

    $(this).validate({


        submitHandler: function () {
            swal("Successfully created!", "", "success");
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
                minlength: 2
            },
            strCity: {
                required: true,
                regx: "^[A-Za-z0-9\- #,.'`\s]+$",
                noSpace: true,
                minlength: 2
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strBrgy: {
                required: "<span class='white-text'><b>Barangay</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Barangay</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Barangay</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Barangay</b>: Minimum of 2 letters</span><br/>"
            },
            strCity: {
                required: "<span class='white-text'><b>City</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>City</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>City</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>City</b>: Minimum of 2 letters</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("greaterThanZero", function(value, element) {
        return this.optional(element) || (parseFloat(value) > 0);
    });
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
//CREATE DELIVERY CHARGE END

//CREATE EXTRA CHARGE
$(document).ready(function () {

    $('#createExtraForm').validate({
        
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.extraerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strECName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            },
            strECDetails: {
                noSpace: true,
                minlength: 2
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strECName: {
                required: "<span class='white-text'><b>Charge Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Charge Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Charge Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Charge Name</b>: Minimum of 2 letters</span><br/>"
            },
            strECDetails: {
                noSpace: "<span class='white-text'><b>Details</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});

$('.updateExtraForm').each(function () {

    $(this).validate({
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.upextraerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strECName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            },
            strECDetails: {
                noSpace: true,
                minlength: 2
            },
            price: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strECName: {
                required: "<span class='white-text'><b>Charge Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Charge Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Charge Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Charge Name</b>: Minimum of 2 letters</span><br/>"
            },
            strECDetails: {
                noSpace: "<span class='white-text'><b>Details</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Details</b>: Minimum of 2 letters</span><br/>"
            },
            price: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Enter value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Enter value</span><br/>"
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
//CREATE EXTRA CHARGE END

//CREATE PACKAGE BEGIN
$(function () {

    $('#createPackageForm').validate({
        
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.crpackageerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strPackageName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            },
            strPackageDesc: {
                noSpace: true,
                minlength: 2
            },
            intPackageType: {
                required: true,
                valueNotEquals: "default"
            },
            createPackagePrice: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strPackageName: {
                required: "<span class='white-text'><b>Package Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Package Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Package Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Package Name</b>: Minimum of 2 letters</span><br/>"
            },
            strPackageDesc: {
                noSpace: "<span class='white-text'><b>Description</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Description</b>: Minimum of 2 letters</span><br/>"
            },
            intPackageType: {
                required: "<span class='white-text'><b>Type</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Type</b>: Select type</span><br/>"
            },
            createPackagePrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Invalid value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Invalid value</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.packcheckbox:checked').length > 0; });
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
//CREATE PACKAGE END

$(function () {

    $('#updatePackageForm').validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.updatepackageerror',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strUpdatePackageName: {
                required: true,
                regx: "^[A-Za-z\- '`\s]+$",
                noSpace: true,
                minlength: 2
            },
            strUpdatePackageDesc: {
                noSpace: true,
                minlength: 2
            },
            intUpdatePackageType: {
                required: true,
                valueNotEquals: "default"
            },
            updatePackagePrice: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strUpdatePackageName: {
                required: "<span class='white-text'><b>Package Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Package Name</b>: Invalid characters</span><br/>",
                noSpace: "<span class='white-text'><b>Package Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Package Name</b>: Minimum of 2 letters</span><br/>"
            },
            strUpdatePackageDesc: {
                noSpace: "<span class='white-text'><b>Description</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Description</b>: Minimum of 2 letters</span><br/>"
            },
            intUpdatePackageType: {
                required: "<span class='white-text'><b>Type</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Type</b>: Select type</span><br/>"
            },
            updatePackagePrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Price</b>: Invalid value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Invalid value</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.chkbox:checked').length > 0; });
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});

//promo
$(function () {

    $('#createPromoForm').validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.crpromoerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            crExp: {
                required: function () {
                    return $('[name=crNonExp]:checked').length === 0;
                }
            },
            crPromoName: {
                required: true,
                regx: "^[A-Za-z0-9\- :'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            crPromoDesc: {
                noSpace: true,
                minlength: 5
            },
            crPromoType: {
                required: true,
                valueNotEquals: "default"
            },
            crPromoPrice: {
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            crExp: {
                required: "<span class='white-text'><b>Availability</b>: Required</span><br/>"
            },
            crPromoName: {
                required: "<span class='white-text'><b>Promo Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Promo Name</b>: Invalid character/s</span><br/>",
                noSpace: "<span class='white-text'><b>Promo Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Promo Name</b>: Minimum of 5 letters</span><br/>"
            },
            crPromoDesc: {
                minlength: "<span class='white-text'><b>Description</b>: Minimum of 5 characters</span><br/>",
                noSpace: "<span class='white-text'><b>Description</b>: Empty Field</span><br/>"
            },
            crPromoType: {
                required: "<span class='white-text'><b>Promo Type</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Promo Type</b>: Required</span><br/>"
            },
            crPromoPrice: {
                valueNotEquals: "<span class='white-text'><b>Price</b>: Invalid value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Price</b>: Invalid value</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.chkbox:checked').length > 0;
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});

$(function () {

    $('#updatePromoForm').validate({
        ignore: "",
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.uppromoerrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            upExp: {
                required: function () {
                    return $('[name=upNonExp]:checked').length === 0;
                }
            },
            upPromoName: {
                required: true,
                regx: "^[A-Za-z0-9\- :'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            upPromoDesc: {
                noSpace: true,
                minlength: 5
            },
            upPromoGuide: {
                noSpace: true,
                minlength: 5
            },
            upPromoPrice: {
                required: function() {
                    return $('[name=upFree]:checked').length === 0;
                }

            }
        },
        messages: {
            upExp: {
                required: "<span class='white-text'><b>Availability</b>: Required</span><br/>"
            },
            upPromoName: {
                required: "<span class='white-text'><b>Promo Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Promo Name</b>: Invalid character/s</span><br/>",
                noSpace: "<span class='white-text'><b>Promo Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Promo Name</b>: Minimum of 5 letters</span><br/>"
            },
            upPromoDesc: {
                minlength: "<span class='white-text'><b>Description</b>: Minimum of 5 characters</span><br/>",
                noSpace: "<span class='white-text'><b>Description</b>: Empty Field</span><br/>"
            },
            upPromoGuide: {
                minlength: "<span class='white-text'><b>Guidelines</b>: Minimum of 5 characters</span><br/>",
                noSpace: "<span class='white-text'><b>Guidelines</b>: Empty Field</span><br/>"
            },
            upPromoPrice: {
                required: "<span class='white-text'><b>Price</b>: Required</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.chkbox:checked').length > 0;
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
//promo

//discount
$(function () {

    $('#createDiscountForm').validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.discounterrorcontainer',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strDiscountName: {
                required: true,
                regx: "^[A-Za-z0-9\- :'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strApplicability: {
                required: true
            },
            strDiscountPricePercent: {
                required: true,
                regx: "^[0-9.]+$"
            },
            strDiscountPriceFixed: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strDiscountName: {
                required: "<span class='white-text'><b>Discount Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Discount Name</b>: Invalid character/s</span><br/>",
                noSpace: "<span class='white-text'><b>Discount Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Discount Name</b>: Minimum of 5 letters</span><br/>"
            },
            strApplicability: {
                required: "<span class='white-text'><b>Applicability</b>: Required</span><br/>"
            },
            strDiscountPricePercent: {
                required: "<span class='white-text'><b>Value</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Value</b>: Invalid amount</span><br/>"
            },
            strDiscountPriceFixed: {
                required: "<span class='white-text'><b>Value</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Value</b>: Invalid value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Value</b>: Invalid value</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.chkbox:checked').length > 0;
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});


$(function () {

    $('#updateDiscountForm').validate({

        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.updatediscounterror',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            strDiscountName: {
                required: true,
                regx: "^[A-Za-z0-9\- :'`\s]+$",
                noSpace: true,
                minlength: 5
            },
            strUpdateApplicability: {
                required: true
            },
            strDiscountPricePercent: {
                required: true,
                regx: "^[0-9.]+$"
            },
            strDiscountPriceFixed: {
                required: true,
                valueNotEquals: "Php 0.00",
                valueNotEquals2: "0.0"
            }
        },
        messages: {
            strDiscountName: {
                required: "<span class='white-text'><b>Discount Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Discount Name</b>: Invalid character/s</span><br/>",
                noSpace: "<span class='white-text'><b>Discount Name</b>: Empty Field</span><br/>",
                minlength: "<span class='white-text'><b>Discount Name</b>: Minimum of 5 letters</span><br/>"
            },
            strUpdateApplicability: {
                required: "<span class='white-text'><b>Applicability</b>: Required</span><br/>"
            },
            strDiscountPricePercent: {
                required: "<span class='white-text'><b>Value</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Value</b>: Invalid amount</span><br/>"
            },
            strDiscountPriceFixed: {
                required: "<span class='white-text'><b>Value</b>: Required</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Value</b>: Invalid value</span><br/>",
                valueNotEquals2: "<span class='white-text'><b>Value</b>: Invalid value</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.chkbox:checked').length > 0;
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
//discount

//INVENTORY
$(function () {

    $('#deflostexpForm').validate({
        submitHandler: function(form) {
            swal("Successfully created!", "", "success");
            form.submit();
        },
        ignore: ":hidden",
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.inventoryerror',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            intProductID: {
                required: true,
                valueNotEquals: "default"
            },
            intQuantity: {
                required: true,
                regx: "^[0-9]+$"
            }
        },
        messages: {
            intProductID: {
                required: "<span class='white-text'><b>Item Name</b>: Select an item</span><br/>",
                valueNotEquals: "<span class='white-text'><b>Item Name</b>: Select an item</span><br/>"
            },
            intQuantity: {
                required: "<span class='white-text'><b>Quantity</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Quantity</b>: Numbers only</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.packcheckbox:checked').length > 0; });
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});
//INVENTORY



//requirement
$(function () {

    $('#createRequirementForm').validate({
        
        ignore: ":hidden",
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.errorCreateRequirement',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            addCrRequirementName: {
                required: true,
                regx: "^[A-Za-z0-9\- :'`\s]+$",
                noSpace: true,
                minlength: 5
            }
        },
        messages: {
            addCrRequirementName: {
                required: "<span class='white-text'><b>Requirement Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Requirement Name</b>: Invalid name</span><br/>",
                noSpace: "<span class='white-text'><b>Requirement Name</b>: Empty</span><br/>",
                minlength: "<span class='white-text'><b>Requirement Name</b>: At least 5 characters</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.packcheckbox:checked').length > 0; });
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});

$(function () {

    $('#createPromoRequirementForm').validate({

        ignore: ":hidden",
        errorClass: 'invalid',
        validClass: 'valid',
        errorElement: 'div',
        errorLabelContainer: '.errorCreatePromoRequirement',
        errorPlacement: function (error, element) {
            // Append error within linked label
            $(element)
                .closest("form")
                .find("label[for='" + element.attr("id") + "']");
        },
        rules: {
            addCrPromoRequirementName: {
                required: true,
                regx: "^[A-Za-z0-9\- :'`\s]+$",
                noSpace: true,
                minlength: 5
            }
        },
        messages: {
            addCrPromoRequirementName: {
                required: "<span class='white-text'><b>Requirement Name</b>: Required</span><br/>",
                regx: "<span class='white-text'><b>Requirement Name</b>: Invalid name</span><br/>",
                noSpace: "<span class='white-text'><b>Requirement Name</b>: Empty</span><br/>",
                minlength: "<span class='white-text'><b>Requirement Name</b>: At least 5 characters</span><br/>"
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
    $.validator.addMethod("checkboxes", function (value, element) {
        return $('.packcheckbox:checked').length > 0; });
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
    $.validator.addMethod("valueNotEquals2", function(value, element, arg){
        return arg != value;
    });
});