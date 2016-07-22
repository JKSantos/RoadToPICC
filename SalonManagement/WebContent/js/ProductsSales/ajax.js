window.onload = updatePSTable();
window.onload = updatePSCRTable();


function updatePSTable() {

    $.ajax({
        type: 'POST',
        url: 'orders',
        dataType: 'json',
        async: true,
        success: function (data) {
            var orderlist = data.orderList;
            var table = $('#productsalestbl').DataTable();
            if (orderlist != null) {
                table.clear().draw();

                $.each(orderlist, function (i, order) {
                    var x;
                    var addbtn = "<center><button class='waves-effect waves-light modal-trigger btn-flat transparent black-text' " +
                        "title='Update' onclick='openUpdate(this.value)' value='" + order.intSalesID + "' id='submitbtn' style='padding: 0px;'>" +
                        "<i class='material-icons'>edit</i></button><button class='prodsalesdeacbtn waves-effect waves-light btn-flat transparent " +
                        "red-text text-accent-4' title='Deactivate' value='" + order.intSalesID + "' onclick='PSDeactivate(this.value)'>" +
                        "<i class='material-icons'>delete</i></button></center>";
                    if (order.intType == 1) {
                        x = "Delivery";
                        if (order.strStatus == "REQUEST") {
                            addbtn = "<center><button class='waves-effect waves-light modal-trigger btn-flat transparent black-text' " +
                                "title='Update' onclick='acceptOrder(this.value)' value='" + order.intSalesID + "' style='padding: 0px;'>" +
                                "<i class='material-icons'>check_circle</i></button><button class='prodsalesdeacbtn waves-effect waves-light btn-flat transparent " +
                                "red-text text-accent-4' title='Deactivate' value='" + order.intSalesID + "' onclick='declineOrder(this.value)'>" +
                                "<i class='material-icons'>cancel</i></button></center>";
                        }
                    } else if (order.intType == 2) {
                        x = "Pick up";
                        if (order.strStatus == "REQUEST") {
                            addbtn = "<center><button class='waves-effect waves-light modal-trigger btn-flat transparent black-text' " +
                                "title='Update' onclick='acceptOrder(this.value)' value='" + order.intSalesID + "' style='padding: 0px;'>" +
                                "<i class='material-icons'>check_circle</i></button><button class='prodsalesdeacbtn waves-effect waves-light btn-flat transparent " +
                                "red-text text-accent-4' title='Deactivate' value='" + order.intSalesID + "' onclick='declineOrder(this.value)'>" +
                                "<i class='material-icons'>cancel</i></button></center>";
                        }
                    }
                    table.row.add([
                        order.strName,
                        order.strAddress,
                        order.strContactNo,
                        x,
                        order.strStatus,
                        addbtn
                    ]);
                });
                table.draw();
            }

        }

    });
}

function updatePSCRTable() {

    $.ajax({
        type: 'POST',
        url: 'getAllProduct',
        dataType: 'json',
        async: true,
        success: function (data) {
            var productList = data.productList;
            var table = $('#crpstbl').DataTable();
            if (productList != null) {
                table.clear().draw();

                $.each(productList, function (i, product) {
                    var checkbox = "<input type='checkbox' name='selectedProducts'" +
                        "id='pscheckbox" + product.intProductID + "' required class='pscheck filled-in'" +
                        " value='" + product.intProductID + "' onchange='crpsCheckbtn(this.value)'><label for='pscheckbox" + product.intProductID + "'></label>";
                    var quantity = "<input type='number' class='right-align psQty' name='productQuantity' id='ps" + product.intProductID +
                        "' style='width: 75px; margin-top: 0px !important; margin-bottom: 0px !important;' disabled value='1' min='1' max='99'>";
                    var price = "<span>P " + product.dblProductPrice + "</span>";
                    table.row.add([
                        checkbox,
                        product.strProductName,
                        product.strProductDesc,
                        price,
                        quantity
                    ]);
                });

                table.draw();
            }

        }

    });
}


// var id = checkboxID;
// var $this = $('#pscheckbox' + id).attr('id');
// console.log($this);
var total = 0;
var $qty = 0;
var i = 1;
var q = 0;

function crpsCheckbtn(checkbtnid) {
    var id = $('#pscheckbox' + checkbtnid);

    if (id.is(':checked')) {
        $('#ps' + checkbtnid).attr('disabled', false);
        var $tr = id.closest('tr'),
            price = $tr.find('td:eq(3)').text(),
            $pricefield = $tr.find('td #ps' + checkbtnid);

        $pricefield.focus(function () { //kapag nag focus sa textfield, kung ano nakalagay makukuha
            q = parseFloat($tr.find('td .psQty').val()).toFixed(2);
            $qty = parseFloat($tr.find('td .psQty').val()).toFixed(2);
        });

        var $price = parseFloat(price.replace(/[^\d.]/g, '')).toFixed(2);
        console.log(price);
        $qty = parseFloat($tr.find('td .psQty').val()).toFixed(2);
        var showqty = parseInt($qty);
        total += $qty * $price;
        $('#crPackPrice').val('P ' + parseFloat(total).toFixed(2));
        console.log(parseFloat(total).toFixed(2));
        console.log('checked');
        q = parseFloat($tr.find('td .psQty').val()).toFixed(2);
        console.log(q);
        $pricefield.on('input', function () {
            $qty = parseFloat($tr.find('td .psQty').val()).toFixed(2);
            if ($qty > q) {
                console.log($qty + '>' + q);
                console.log(total);
                total += ($qty - q) * $price;
                console.log(total);
                showqty = parseInt($qty);
                console.log('q' + q);
                total = Math.abs(total);
                $('#crPackPrice').val('P ' + parseFloat(total).toFixed(2));
                $('#pslist #x' + checkbtnid + '').remove();
                $('#pslist #item' + checkbtnid + ' .span').append('<span class="grey-text text-darken-3" id="x' + checkbtnid + '"> (' + showqty + ')</span>');
                console.log(parseFloat(total).toFixed(2));
            } else if ($qty < q) {
                total -= (q - $qty) * $price;
                total = Math.abs(total);
                $('#crPackPrice').val('P ' + parseFloat(total).toFixed(2));
                showqty = parseInt($qty);
                $('#pslist #x' + checkbtnid + '').remove();
                $('#pslist #item' + checkbtnid + ' .span').append('<span class="grey-text text-darken-3" id="x' + checkbtnid + '"> (' + showqty + ')</span>');
            } else if ($qty == q) {

            } else {

            }
            q = $qty;
        });

        $pricefield.keydown(function (e) {
            if (e.which == 8 && ( document.activeElement.id == 'ps' + checkbtnid)) {
                e.preventDefault();
                return false;
            }
        });


        var name = $tr.find('td:eq(1)').text();

        $('#pslist').append('<div style="margin: 3px;" class="chip z-depth-1 grey lighten-2 grey-text text-darken-4"' +
            'id="item' + checkbtnid + '"><b>' + name + '</b><span class="span"><span class="grey-text text-darken-3" id="x' + checkbtnid + '">' +
            ' (' + showqty + ')</span></span>' + '<i id="prodchip' + checkbtnid + '" class="uncheckchip material-icons" style="margin-right: 5px' +
            '!important">close</i></div>').show();

    } else {
        $('#ps' + checkbtnid).attr('disabled', true);
        var $utr = id.closest('tr'),
            unprice = $utr.find('td:eq(3)').text();
        var $unprice = parseFloat(unprice.replace(/[^\d.]/g, '')).toFixed(2);
        $qty = parseFloat($utr.find('td .psQty').val()).toFixed(2);
        total = total - ($qty * $unprice);
        total = Math.abs(total);
        $('#crPackPrice').val('P ' + parseFloat(total).toFixed(2));
        $('#pscheckbox' + checkbtnid + '').prop('checked', false);
        $('#item' + checkbtnid + '').remove();
        $('#ps' + checkbtnid + '').attr('disabled', true);
        $('#ps' + checkbtnid).val(1);

    }

    $('#prodchip' + checkbtnid + '').click(function () {
        var $utr = id.closest('tr'),
            unprice = $utr.find('td:eq(3)').text();
        var $unprice = parseFloat(unprice.replace(/[^\d.]/g, '')).toFixed(2);
        $qty = parseFloat($utr.find('td .psQty').val()).toFixed(2);
        total = total - ($qty * $unprice);
        total = Math.abs(total);
        $('#crPackPrice').val('P ' + parseFloat(total).toFixed(2));
        $('#pscheckbox' + checkbtnid + '').prop('checked', false);
        $('#item' + checkbtnid + '').remove();
        $('#ps' + checkbtnid + '').attr('disabled', true);
        $('#ps' + checkbtnid).val(1);
    });

}


function PSDeactivate(prodsaleid) {
    var deactivatePS = {
        'intOrderID': prodsaleid
    }
    console.log(prodsaleid);
    var $tr = $(this).closest('tr');

    swal({
            title: "Are you sure?",
            text: "",
            type: "warning",
            showCancelButton: true,
            confirmButtonColor: "#DD6B55",
            confirmButtonText: "Yes, delete it!",
            closeOnConfirm: false
        },
        function () {
            swal("Deleted!", ".", "success");
            $.ajax({
                type: 'post',
                url: 'declineOrder',
                data: deactivatePS,
                success: function (response) {
                    $tr.find('td').fadeOut(500, function () {
                        $tr.remove();
                    });
                }
            });
        });
}