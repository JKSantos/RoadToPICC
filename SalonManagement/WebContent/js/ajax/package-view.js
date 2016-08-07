/**
 * Created by Castillo on 8/6/2016.
 */

function openViewPackage(id) {
    $('#viewModalPackage').openModal({
        dismissible: true, // Modal can be dismissed by clicking outside of the modal
        opacity: .9, // Opacity of modal background
        in_duration: 300, // Transition in duration
        out_duration: 300, // Transition out duration
    });
    $('#viewContainer .chip').remove();
    $.ajax({
        type: 'get',
        url: 'api/v1/getAllPackage',
        dataType: 'json',
        async: true,
        success: function (data) {
            for (var i = 0; i < data.packageList.length; i++) {
                var viewPackageID = data.packageList[i].intPackageID;
                if(parseInt(viewPackageID) == parseInt(id)) {
                    for (var x = 0; x < data.packageList[i].productList.length; x++) {
                        var prodPrice = parseFloat(data.packageList[i].productList[x].product.dblProductPrice).toFixed(2);
                        prodPrice = addCommas(prodPrice);
                        var productName = data.packageList[i].productList[x].product.strProductName,
                            productSrc = data.packageList[i].productList[x].product.strPhotoPath;
                        var viewData = "<div class='col s3' style='display:none !important;'><div class='card medium z-depth-1' style='margin: 5px !important;'><div class='card-image waves-effect waves-block waves-light'>" +
                            "<img class='activator' src='" + productSrc +"' height='300'></div><div class='card-content'>" +
                            "<span class='card-title activator grey-text text-darken-4' style='font-size: 15px; line-height: 15px !important;'><b>" + productName +
                            "</b><p>Php " + prodPrice + "</p><p>Quantity: " + data.packageList[i].productList[x].intProductQuantity + "</p>" +
                            "</div>" +
                            "<div class='card-reveal'>" +
                            "<span class='card-title grey-text text-darken-4'>" + productName +
                            "<p style='margin-top: 20px !important;'>" + data.packageList[i].productList[x].product.strProductDesc + "</p>" +
                            "</div>" +
                            "</div></div>"
                        $(viewData).prependTo($('#viewContainer')).fadeIn('slow');
                    }
                    for (var y = 0; y < data.packageList[i].serviceList.length; y++) {
                        var price = parseFloat(data.packageList[i].serviceList[y].service.dblServicePrice).toFixed(2);
                        price = addCommas(price);
                        var serviceName = data.packageList[i].serviceList[y].service.strServiceName,
                            servicePath = data.packageList[i].serviceList[y].service.strPhotoPath;
                        var viewServData = "<div class='col s3' style='display:none !important;'><div class='card medium z-depth-1' style='margin: 5px !important;'>" +
                            "<div class='card-image waves-effect waves-block waves-light'>" +
                            "<img class='activator' src='" + servicePath +"' height='300'></div><div class='card-content'>" +
                            "<span class='card-title activator grey-text text-darken-4' style='font-size: 15px; line-height: 15px !important;'><b>" + serviceName +
                            "</b><p>Php " + price + "</p><p>Quantity: " + data.packageList[i].serviceList[y].intQuantity + "</p>" +
                            "</div>" +
                            "<div class='card-reveal'>" +
                            "<span class='card-title grey-text text-darken-4'>" + serviceName +
                            "<p style='margin-top: 20px !important;'>" + data.packageList[i].serviceList[y].service.strServiceDesc + "</p>" +
                            "</div>" +
                            "</div></div>"
                        $(viewServData).prependTo($('#viewContainer')).fadeIn('slow');
                    }

                }
            }
        }
    });
}