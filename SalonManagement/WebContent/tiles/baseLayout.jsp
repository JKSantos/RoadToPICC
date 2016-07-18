<!DOCTYPE html>

<html>
<head>
    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
    <link type="text/css" rel="stylesheet" href="css/materialize.css"/>
    <link type="text/css" rel="stylesheet" href="css/mystyle.css"/>
    <link type="text/css" rel="stylesheet" href="css/mtnc-emp.css"/>
    <link rel="stylesheet" type="text/css" href="css/bartstable.css"/>
    <link rel="stylesheet" type="text/css" href="css/material.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/dropify.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/sweetalert.css"/>
    <link rel="stylesheet" type="text/css" href="css/mainloader.css"/>
    <link rel="stylesheet" type="text/css" href="css/animate.css"/>
    <!--<link rel="stylesheet" type="text/css" href="css/drag.css"/>-->
    <!--<link rel="stylesheet" type="text/css" href="css/animate.css"/>-->


    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>
        <tiles:insertAttribute name="title" ignore="true"/>
    </title>
</head>

<body class="purple lighten-5 demo">
<div class="wrapper">
    <header class="headnav">
        <ul id="slide-out" class="side-nav fixed z-depth-0">
            <li class="user-details purple darken-1" style="height: 64px !important;">
                <div class="row">
                    <div class="col col s4 m4 l4" style="padding: 5px !important;">
                        <img src="<s:url action='getImage'><s:param name='ImageID'>${sessionScope.id}</s:param><s:param name='type'>employee</s:param></s:url>"
                             alt="" class="circle responsive-img valign profile-image"
                             style="width: 50px !important; height: 50px !important; margin-left: 10px !important;">
                    </div>
                    <div class="col col s8 m8 l8">
                        <ul id="profile-dropdown" class="dropdown-content"
                            style="margin-top: 55px !important; overflow-y: hidden !important; margin-left: -15px !important;">
                            <li><a href="#" style="padding-top: 12px !important;"><i
                                    class="mdi-action-face-unlock"></i>
                                Profile</a></li>
                            <li><a href="#" style="padding-top: 12px !important;"><i class="mdi-action-settings"></i>
                                Settings</a></li>
                            <li><a href="#" style="padding-top: 12px !important;"><i
                                    class="mdi-communication-live-help"></i> Help</a></li>
                            <li class="divider"></li>
                            <li><a href="#" style="padding-top: 12px !important;"><i
                                    class="mdi-action-lock-outline"></i> Lock</a></li>
                            <li><a href="logout" style="padding-top: 12px !important;"><i
                                    class="mdi-hardware-keyboard-tab"></i> Logout</a></li>
                        </ul>
                        <a class="truncate btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
                           href="#"
                           data-activates="profile-dropdown"
                           style="margin-top: 5px !important; margin-left: -15px !important; padding-right: 0px !important;">${sessionScope.firstName}<i
                                class="material-icons right">arrow_drop_down</i></a>
                        <p class="user-roal">Administrator</p>
                    </div>
                </div>
            </li>
            <!--<div class="center">-->
            <!--<img src="<s:url action='getImage'><s:param name='ImageID'>${sessionScope.id}</s:param><s:param name='type'>employee</s:param></s:url>"-->
            <!--class="circle" style="width: 150px; height: 150px; margin-top: 10px; margin-bottom: 5px;">-->
            <!--<a href="" style="padding: 0px !important;"></a>-->
            <!--<a href="logout" style="padding: 0px !important;">Logout</a>-->
            <!--</div>-->

            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="admin-home.jsp"><i
                            class="material-icons">home</i><b>Home</b></a>
                </li>
            </ul>
            <!--  <li class="no-padding"> -->
            <ul class="collapsible" id="mtnc" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header"><i class="material-icons">build</i><b>Maintenance</b></a>
                    <div class="collapsible-body">
                        <ul>
                            <li><a href="employeeMaintenance">Employee</a></li>
                            <li><a href="productMaintenance">Product</a></li>
                            <li><a href="serviceMaintenance">Service</a></li>
                            <li><a href="packageMaintenance">Package</a></li>
                            <li><a href="locationMaintenance">Delivery Charge</a></li>
                            <li><a href="extraChargeMaintenance">Other Charge</a></li>
                            <li><a href="promoMaintenance">Promo</a></li>
                            <li><a href="discountMaintenance">Discount</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a class="collapsible-header"><i class="material-icons">shopping_cart</i><b>Transaction</b></a>
                    <div class="collapsible-body">
                        <ul class="navul">
                            <li><a href="getInventory">Inventory</a></li>
                            <li><a href="transactions-reservation.jsp">Reservation</a></li>
                            <li><a href="product-sales">Product Sales</a></li>
                            <li><a href="transactions-vip.jsp">VIP</a></li>
                            <li><a href="transactions-walkin.jsp">Walk In</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="admin-home.jsp"><i
                            class="material-icons">settings</i><b>Utilities</b></a>
                </li>
            </ul>
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="admin-home.jsp"><i
                            class="material-icons">search</i><b>Queries</b></a>
                </li>
            </ul>
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="admin-home.jsp"><i
                            class="material-icons">assessment</i><b>Reports</b></a>
                </li>
            </ul>
        </ul>

    </header>


    <article>
        <nav class="z-depth-0">
            <div class="nav-wrapper purple darken-4">
                <a href="#" class="brand-logo center purple-text text-lighten-5 ">Salon Management System</a>
            </div>
        </nav>
        <div id="loader-wrapper">
            <div id="loader"></div>
            <div class="loader-section section-left"></div>
            <div class="loader-section section-right"></div>
        </div>
        <tiles:insertAttribute name="body"></tiles:insertAttribute>

    </article>


</div>

<style type="text/css">
    #createEmployeeModal {
        width: 600px !important;
        height: 590px !important;
        max-height: 100% !important;
        margin-top: -30px !important;
    }

    #createPackageModal {
        width: 600px !important;
        height: 590px !important;
        max-height: 100% !important;
        margin-top: -30px !important;
    }

    #createProduct {
        width: 600px !important;
        height: 535px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    #createService {
        width: 600px !important;
        height: 535px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    #createDeliveryModal {
        width: 400px !important;
        height: 435px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    #createExtraChargeModal {
        width: 400px !important;
        height: 435px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    .updateExtraModal {
        width: 400px !important;
        height: 435px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    .updateDeliveryModal {
        width: 400px !important;
        height: 435px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    .servUpdateModal {
        width: 600px !important;
        height: 535px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    .prodUpdateModal {
        width: 600px !important;
        height: 535px !important;
        max-height: 100% !important;
        margin-top: -20px !important;
    }

    .empModalUpdate {
        width: 600px !important;
        height: 610px !important;
        max-height: 100% !important;
        margin-top: -40px !important;
    }

    .viewEmpModal {
        width: 700px !important;
        height: 620px !important;
        max-height: 100% !important;
        margin-top: -30px !important;
    }

    #createAddOption {
        width: 500px !important;
        height: 410px !important;
    }

    #updateOption {
        width: 500px !important;
        height: 410px !important;
    }

    #crProdAddCate {
        width: 400px !important;
        height: 400px !important;
    }

    #crServAddCateModal {
        width: 400px !important;
        height: 400px !important;
    }

    .upServAddCatModal {
        width: 400px !important;
        height: 400px !important;
    }

    #upProdAddCatModal {
        width: 400px !important;
        height: 400px !important;
    }

    .paging_full_numbers a.paginate_active {
        color: #4a148c !important;
    }

    .tabs .indicator {
        display: none;
    }

    .tabs .tab a.active {
        border-bottom: 2px solid #6a1b9a;
    }

    .profile-btn {
        margin: 0;
        text-transform: capitalize;
        padding: 0;
        text-shadow: 1px 1px 1px #444;
        font-size: 15px
    }

    .user-roal {
        color: #fff;
        margin-top: -16px;
        font-size: 13px;
        text-shadow: 1px 1px 1px #444;
    }

    .collapsible-body .navul li a {
        margin-left: 40px !important;
    }

    .dataTables_filter {
        display: none;
    }

    nav .nav-wrapper form, nav .nav-wrapper form .input-field {
        height: 100%;
    }

    .btnshadow {
        box-shadow: 0 2px 5px 0 rgba(0, 0, 0, 0.16), 0 2px 10px 0 rgba(0, 0, 0, 0.12) !important;
    }

    .viewEmpModal {
        -webkit-animation-duration: 1.5s;
        -webkit-animation-delay: 0s;
        -o-animation-duration: 1.5s;
        -o-animation-delay: 0s;
        -moz-animation-duration: 1.5s;
        -moz-animation-delay: 0s;
    }

    .input-field input[type=search] {
        height: inherit;
    }

</style>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/bartstable.js"></script>
<script type="text/javascript" src="js/maintenance-emp.js"></script>
<script type="text/javascript" src="js/picker.date.js"></script>
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/additional.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/stepform.js"></script>
<script type="text/javascript" src="js/jquery.formatter.min.js"></script>
<script type="text/javascript" src="js/dropify.min.js"></script>
<script type="text/javascript" src="js/sweetalert.min.js"></script>
<!--<script type="text/javascript" src="js/ajax.js"></script>-->
<script type="text/javascript" src="js/ellipsis.js"></script>
<script type="text/javascript" src="js/priceformat.js"></script>
<script type="text/javascript" src="js/formatted-numbers.js"></script>
<script type="text/javascript" src="js/jquery.formatCurrency-1.4.0.js"></script>


<!--<script type="text/javascript" src="js/drag.js"></script>-->

<script type="text/javascript">

    function setBirthDate(id, date) {

        var monthNames = ["January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        ];

        var from = date.split("-");
        var dat = new Date(Date.parse(date));
        var d = monthNames[dat.getMonth()] + "/" + from[2] + "/" + from[0];

        $(id).val(d);
    }

    $(document).ready(function () {
        $(".button-collapse").sideNav();
        $(".dropdown-button").dropdown();

//        $('#emppics').removeClass('sor');
//        $('#emppics').css('background-image','none');
    });

    $(document).ready(function () {
        $('select').material_select();
        $("select[required]").css({display: "inline", height: 0, padding: 0, width: 0});
    });

    $('.dropify').dropify({
        messages: {
            'error': 'Oops, something wrong appended.'
        }
    });

    $('#createContact').formatter({
        'pattern': '09{{99}}-{{999}}-{{9999}}',
        'persistent': true
    });

    $('.updateContact').each(function () {
        $(this).formatter({
            'pattern': '09{{99}}-{{999}}-{{9999}}',
            'persistent': true
        });
    });

    $('ul.tabs').each(function () {
        $(this).tabs();
    });

    $(document).ready(function () {
        $('.tooltipped').tooltip({delay: 5});
    });

    $(document).ready(function () {

        setTimeout(function () {
            $('body').addClass('loaded');
        }, 800);
    });

    $('#crItemPrice').priceFormat({
        prefix: 'Php ',
        centsSeparator: '.',
        thousandsSeparator: ',',
        limit: 9
    });


    $('.upProdItemPrice').each(function () {
        $(this).priceFormat({
            prefix: 'Php ',
            centsSeparator: '.',
            thousandsSeparator: ',',
            limit: 9
        });
    });


    $('.prodPrice').priceFormat({
        prefix: 'Php ',
        centsSeparator: '.',
        thousandsSeparator: ',',
        limit: 9
    });

    $('.servPrice').priceFormat({
        prefix: 'Php ',
        centsSeparator: '.',
        thousandsSeparator: ',',
        limit: 9
    });


    $('#crServicePrice').priceFormat({
        prefix: 'Php ',
        centsSeparator: '.',
        thousandsSeparator: ',',
        limit: 9
    });


</script>

<!--<script>-->
<!--$(function() {-->
<!--$( "#draggable" ).draggable();-->
<!--});-->
<!--</script>-->

<!--<script type="text/javascript">-->
<!--$('#welcome').addClass('animated infinite rubberBand');-->
<!--</script>-->

<!-- Upload image -->


</body>

</html>