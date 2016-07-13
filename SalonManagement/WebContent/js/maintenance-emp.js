$(document).ready(function () {
    $('#example').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ],
        "rowHeight": '10px'
    });
});


$(document).ready(function () {
    $('#serviceTable').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ]
    });
});

$(document).ready(function () {
    $('#discArchTable').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ]
    });
});

$(document).ready(function () {
    $('#empArchiveTable').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ]
    });
});

$(document).ready(function () {
    $('#prodsvcArchive').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ]
    });
});

$(document).ready(function () {
    $('#carArchivetable').DataTable({
        "bLengthChange": false,
        responsive: true,
        columnDefs: [
            {
                targets: [0, 1, 2],
                className: 'mdl-data-table__cell--non-numeric'
            }
        ]
    });
});

// the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
$('.modal-trigger').leanModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-update').leanModal({
        dismissible: false, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-archive').leanModal({
    dismissible: true,
    opacity: .9,
    in_duration: 400,
    out_duration: 400
});

$('.modal-viewall').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: 0.95, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);


$('.modal-view').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 200, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-option').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-optionUpdate').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-category').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('.modal-updateCategory').leanModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .2, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 200, // Transition out duration
    }
);

$('#btnCreateExit').click(function () {
    $('#createEmpForm').trigger("reset");
    $('.errorcontainer').hide();
});

$('.btnUpdateExit').click(function () {
    $('.updateEmpForm').trigger("reset");
    $('.updateerror').empty();
});

//add option

$('#createAddPosition').click(function () {
    if ($('#createOption').valid()) {
        $('select').material_select('destroy');
        var addopt = $('#addOptionName').val();
        $('#crSelectedJob').append('<option selected>' + addopt + '</option>').attr('value', addopt);
        $('select').material_select();

        $('#createAddOption').closeModal();
    }
});


//
// $(document).ready(function(){
//    var x = $('#addOptionName') .val();
//     if(x == "" || x == null){
//         $('#createAddPosition').attr('disabled',true);
//     } else {
//         $('#createAddPosition').attr('disabled',false);
//     }
// });
//end add option


$('#addOptionSelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});

$('#updateOptionSelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});

$('#addCatgorySelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});

$('#updateCatgorySelect').click(function () {
    $(this).siblings('select').css('width', $(this).outerWidth(true)).toggle();
});


$('.tooltipped').tooltip({delay: 30});

$(document).ready(function () {
    $(".button-collapse").sideNav();
    // Initialize collapsible (uncomment the line below if you use the dropdown variation)
    $('.collapsible').collapsible();
    $('select').material_select();


});


// add product / service in table (PROMO)

function addServPromo() {
    var table = document.getElementById("promoTable");
    var promoType = $("#promoService").val();
    var promoQty = $("#promoServiceQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoType + '" id="createServPromType[' + i + ']" name="createServPromType" readonly style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var servPromQty = document.createElement("input");
    servPromQty.type = "number";
    servPromQty.name = "createServPromQty";
    servPromQty.style = "color: black";
    servPromQty.value = promoQty;
    cell2.appendChild(servPromQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteServPromo(this)" />';
    var x = document.getElementById("promoService");
    x.remove(x.selectedIndex);
}

function deleteServPromo(row) {
    var x = row.parentNode.parentNode.rowIndex;
    // var packageText = document.getElementById("promoTable").rows[i].cells[0].innerHTML;
    var packageText = document.getElementById("promoTable").rows[x].cells[0].firstChild.value;
    document.getElementById('promoTable').deleteRow(x);

    alert(packageText);
    var y = document.getElementById("promoService");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}

function addProdPromo() {
    var table = document.getElementById("promoTable");
    var promoProdType = $("#promoProduct").val();
    var promoProdQty = $("#promoProductQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoProdType + '" id="createProdPromType[' + i + ']" name="createProdPromType" readonly style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var prodPromQty = document.createElement("input");
    prodPromQty.type = "number";
    prodPromQty.name = "createProdPromQty";
    prodPromQty.style = "color: black";
    prodPromQty.value = promoProdQty;
    cell2.appendChild(prodPromQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteProdPromo(this)" />';
    var x = document.getElementById("promoProduct");
    x.remove(x.selectedIndex);
}

function deleteProdPromo(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("promoTable").rows[i].cells[0].firstChild.value;
    document.getElementById('promoTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("promoProduct");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}


// add product/service in update BEGIN
function updateServPromo() {
    var table = document.getElementById("updatePromoTable");
    var promoType = $("#updatePromoService").val();
    var promoQty = $("#updatePromoSQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoType + '" id="updateServPromType[' + i + ']" name="createServPromType" disabled="disabled" style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var updateServPromQty = document.createElement("input");
    updateServPromQty.type = "number";
    updateServPromQty.name = "createServPromQty";
    updateServPromQty.disabled = true;
    updateServPromQty.style = "color: black";
    updateServPromQty.value = promoQty;
    cell2.appendChild(updateServPromQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteUpdateSPromo(this)" />';
    var x = document.getElementById("updatePromoService");
    x.remove(x.selectedIndex);
}

function deleteUpdateSPromo(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("updatePromoTable").rows[i].cells[0].firstChild.value;
    document.getElementById('updatePromoTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("updatePromoService");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}

function updateProdPromo() {
    var table = document.getElementById("updatePromoTable");
    var promoProdType = $("#updatePromoProduct").val();
    var promoProdQty = $("#updatePromoPQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoProdType + '" id="createProdPromType" name="updateProdPromType" disabled="disabled" style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var updateProdPromQty = document.createElement("input");
    updateProdPromQty.type = "number";
    updateProdPromQty.name = "createProdPromQty";
    updateProdPromQty.disabled = true;
    updateProdPromQty.style = "color: black";
    updateProdPromQty.value = promoProdQty;
    cell2.appendChild(updateProdPromQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteUpdatePPromo(this)" />';
    var x = document.getElementById("updatePromoProduct");
    x.remove(x.selectedIndex);


}

function deleteUpdatePPromo(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("updatePromoTable").rows[i].cells[0].firstChild.value;
    document.getElementById('updatePromoTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("updatePromoProduct");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}
// add product/service in update END


// add product/service in package BEGIN
function createPackageService() {
    var table = document.getElementById("createPackageTable");
    var packageType = $("#createPackageService").val();
    var packageQty = $("#createPackageServiceQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + packageType + '" id="createPackServType[' + i + ']" name="createPackServType" readonly style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var createPackServQty = document.createElement("input");
    createPackServQty.type = "number";
    createPackServQty.name = "createPackServQty";
    createPackServQty.style = "color: black";
    createPackServQty.value = packageQty;
    cell2.appendChild(createPackServQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteCreateServPack(this)" />';
    var x = document.getElementById("createPackageService");
    x.remove(x.selectedIndex);
}

function deleteCreateServPack(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("createPackageTable").rows[i].cells[0].firstChild.value;
    document.getElementById('createPackageTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("createPackageService");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}

function createPackageProduct() {
    var table = document.getElementById("createPackageTable");
    var promoType = $("#createPackageProduct").val();
    var promoQty = $("#createPackageProductQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoType + '" id="createPackProdType[' + i + ']" name="createPackProdType" readonly style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var createPackProdQty = document.createElement("input");
    createPackProdQty.type = "number";
    createPackProdQty.name = "createPackProdQty";
    createPackProdQty.style = "color: black";
    createPackProdQty.value = promoQty;
    cell2.appendChild(createPackProdQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteCreateProdPack(this)" />';
    var x = document.getElementById("createPackageProduct");
    x.remove(x.selectedIndex);
}

function deleteCreateProdPack(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("createPackageTable").rows[i].cells[0].firstChild.value;
    document.getElementById('createPackageTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("createPackageProduct");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}

function updatePackageService() {
    var table = document.getElementById("updatePackageTable");
    var promoType = $("#updatePackageService").val();
    var promoQty = $("#updatePackageServiceQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoType + '" id="updateServPackType[' + i + ']" name="createServPackType" readonly style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var updateServPackQty = document.createElement("input");
    updateServPackQty.type = "number";
    updateServPackQty.name = "createServPackQty";
    updateServPackQty.style = "color: black";
    updateServPackQty.value = promoQty;
    cell2.appendChild(updateServPackQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteUpdateServPack(this)" />';
    var x = document.getElementById("updatePackageService");
    x.remove(x.selectedIndex);
}

function deleteUpdateServPack(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("updatePackageTable").rows[i].cells[0].firstChild.value;
    document.getElementById('updatePackageTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("updatePackageService");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}

function updatePackageProduct() {
    var table = document.getElementById("updatePackageTable");
    var promoType = $("#updatePackageProduct").val();
    var promoQty = $("#updatePackageProductQty").val();
    var removeBtn = document.createElement('button');

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var i = rowCount;
    var cell1 = row.insertCell(0);
    cell1.innerHTML = '<input type="text" value="' + promoType + '" id="updateProdPackType[' + i + ']" name="createProdPackType" readonly style="color:black;"/>';
    var cell2 = row.insertCell(1);
    var updateProdPackQty = document.createElement("input");
    updateProdPackQty.type = "number";
    updateProdPackQty.name = "updateProdPackQty";
    updateProdPackQty.disabled = true;
    updateProdPackQty.style = "color: black";
    updateProdPackQty.value = promoQty;
    cell2.appendChild(updateProdPackQty);
    var cell3 = row.insertCell(2);
    // cell1.innerHTML = promoType;
    // cell2.innerHTML = promoQty;
    cell3.innerHTML = '<input id="removeBtn" type="button" value="Remove" class="waves-effect waves-light btn red" onclick="deleteUpdateProdPack(this)" />';
    var x = document.getElementById("updatePackageProduct");
    x.remove(x.selectedIndex);
}

function deleteUpdateProdPack(row) {
    var i = row.parentNode.parentNode.rowIndex;
    var packageText = document.getElementById("updatePackageTable").rows[i].cells[0].firstChild.value;
    document.getElementById('updatePackageTable').deleteRow(i);

    alert(packageText);
    var y = document.getElementById("updatePackageProduct");
    var option = document.createElement("option");
    option.text = packageText;

    y.add(option);
}
// add product/service in package END


// add another option BEGIN
// EMPLOYEE BEGIN


// EMPLOYEE END

// PRODUCT AND SERVICE BEGIN

// $("#createAddCatBtn").click(function () {

//   });
// product

// bday BEGIN
$('#createBirthday').pickadate({
    selectYears: 40,
    selectMonths: true,
    labelMonthNext: 'Next month',
    labelMonthPrev: 'Previous month',
    labelMonthSelect: 'Select a month',
    labelYearSelect: 'Select a year',
    monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
    weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    weekdaysLetter: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
    today: 'Today',
    clear: 'Clear',
    close: 'Close',
    format: 'mmmm/d/yyyy',
    max: 'Today',
    yearRange: "1970:Today",
    onSet: function (arg) {
        if ('select' in arg) { //prevent closing on selecting month/year
            this.close();
        }
        var bdate = $('#createBirthday').val();
        var dob = new Date(bdate);
        var today = new Date();
        var age = today.getTime() - dob.getTime();
        age = Math.floor(age / (1000 * 60 * 60 * 24 * 365.25));
        if (age < 18) {
            $('#createAge').css('color', 'red').val("Not Qualified");
            $('.nextform').attr('disabled', true);
        } else {
            $('#createAge').val(age).css('color', 'black');
            $('.nextform').attr('disabled', false);
        }

    }
});


$('.updateEmpBirthday').pickadate({
    selectYears: 40,
    selectMonths: true,
    labelMonthNext: 'Next month',
    labelMonthPrev: 'Previous month',
    labelMonthSelect: 'Select a month',
    labelYearSelect: 'Select a year',
    monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
    weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    weekdaysLetter: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
    today: 'Today',
    clear: 'Clear',
    close: 'Close',
    format: 'mmmm/d/yyyy',
    max: 'Today',
    yearRange: "1970:Today",
    onSet: function (arg1) {
        if ('select' in arg1) { //prevent closing on selecting month/year
            this.close();
        }
        var bdate1 = $('.updateEmpBirthday').val();
        var dob1 = new Date(bdate1);
        var today1 = new Date();
        var age1 = today1.getTime() - dob1.getTime();
        age1 = Math.floor(age1 / (1000 * 60 * 60 * 24 * 365.25));
        if (age1 < 18) {
            $('.updateEmpAge').css('color', 'red').val("Not Qualified");
            $('.updateSubmitForm').attr('disabled', true);
        } else {
            $('.updateEmpAge').val(age1).css('color', 'black');
            $('.updateSubmitForm').attr('disabled', false);
        }

    }
});

// bday END

// promo BEGIN
$('.datepicker-promo').pickadate({
    changeMonth: true,
    selectYears: 10,
    selectMonths: true,
    labelMonthNext: 'Next month',
    labelMonthPrev: 'Previous month',
    labelMonthSelect: 'Select a month',
    labelYearSelect: 'Select a year',
    monthsFull: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
    monthsShort: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'],
    weekdaysFull: ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'],
    weekdaysShort: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
    weekdaysLetter: ['S', 'M', 'T', 'W', 'T', 'F', 'S'],
    maxDate: '-18Y',
    clear: 'Clear',
    close: 'Close',
    format: 'mmmm/d/yyyy',
    min: 'Today',
    yearRange: "Today:2020"
});
// promo END

$(document).ready(function () {

    $('#createBirthday').change(function () {

        var today = new Date();
        var dd = Number(today.getDate());
        var mm = Number(today.getMonth() + 1);
        var yyyy = Number(today.getFullYear());

        var myBD = $('#createBirthday').val();
        var myBDM = Number(myBD.split("/")[0]);
        var myBDD = Number(myBD.split("/")[1]);
        var myBDY = Number(myBD.split("/")[2]);
        var age = yyyy - myBDY;

        if (mm < myBDM) {
            age = age - 1;

        }
        else if (mm == myBDM && dd < myBDD) {
            age = age - 1;
        }

        if (age > 17) {
            $('#createAge').val(age);
        } else {
            $('#createAge').val("not qualified");
        }


    });

});


$('#updateBirthday').each(function () {

    $(this).change(function () {

        var today = new Date();
        var dd = Number(today.getDate());
        var mm = Number(today.getMonth() + 1);

        var yyyy = Number(today.getFullYear());

        var myBD = $('#updateBirthday').val();
        var myBDM = Number(myBD.split("/")[0])
        var myBDD = Number(myBD.split("/")[1])
        var myBDY = Number(myBD.split("/")[2])
        var age = yyyy - myBDY;

        if (mm < myBDM) {
            age = age - 1;
        }
        else if (mm == myBDM && dd < myBDD) {
            age = age - 1;
        }

        $('#updateAge').val(age);
    });

});

$(document).ready(function () {
    $(".nonexpiry").click(function () {
        if ($(".nonexpiry").is(":checked")) {
            $(".expiration")
                .attr("disabled", "disabled");
        }
        else {
            $(".expiration")
                .removeAttr("disabled");
        }
    });
});

$(document).ready(function () {
    $(".free").click(function () {
        if ($(".free").is(":checked")) {
            $(".price")
                .attr("disabled", "disabled");
        }
        else {
            $(".price")
                .removeAttr("disabled");
        }
    });
});