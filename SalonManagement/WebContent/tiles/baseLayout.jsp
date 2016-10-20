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
    <!-- <link type="text/css" rel="stylesheet" href="css/mtnc-catalogue.css"/> -->
    <link rel="stylesheet" type="text/css" href="css/dropify.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/sweetalert.css"/>
    <link rel="stylesheet" type="text/css" href="css/mainloader.css"/>
    <link rel="stylesheet" type="text/css" href="css/materialize.clockpicker.css"/>
    <link rel="stylesheet" type="text/css" href="css/animate.css"/>
    <link rel="stylesheet" type="text/css" href="css/fullcalendar.css"/>
    <!--<link rel="stylesheet" type="text/css" href="css/angular-datatables.css"/>-->

    <link rel="stylesheet" type="text/css" href="css/xcharts.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/baselayout.css"/>
    <link rel="stylesheet" type="text/css" href="css/perfect-scrollbar.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/angular-validation.css"/>

    <link rel="stylesheet" type="text/css" href="css/nouislider.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/nouislider.pips.css"/>
    <link rel="stylesheet" type="text/css" href="css/nouislider.tooltips.css"/>


    <!--<link rel="stylesheet" type="text/css" href="css/drag.css"/>-->
    <!--<link rel="stylesheet" type="text/css" href="css/animate.css"/>-->


    <script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="js/materialize.js"></script>


    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>
        <tiles:insertAttribute name="title" ignore="true"/>
    </title>
</head>

<body class="purple lighten-5 demo" ng-app="app">
<div class="wrapper hide-on-large-only">
    <h1 class="grey-text text-darken-4">Download our application for Mobile and Table!</h1>
</div>
<div class="wrapper hide-on-med-and-down">
    <header class="headnav">
        <ul id="slide-out" class="side-nav fixed z-depth-0" style="background-color: #262626 !important;">
            <li class="user-details" style="height: 64px !important; background-color: #0d0d0d !important;">
                <div class="row">
                    <div class="col col s4 m4 l4" style="padding: 5px !important;">
                        <img src="<s:url action='getImage'><s:param name='ImageID'>${sessionScope.id}</s:param><s:param name='type'>employee</s:param></s:url>"
                             alt="" class="circle responsive-img valign profile-image"
                             style="width: 50px !important; height: 50px !important; margin-left: 10px !important;">
                    </div>
                    <div class="col col s8 m8 l8">
                        <ul id="profile-dropdown" class="dropdown-content"
                            style="margin-top: 55px !important; overflow-y: hidden !important; margin-left: -15px !important;">
                            <li>
                                <a href="employeeSettings" style="padding-top: 12px !important;">
                                    <i class="mdi-action-settings"></i>
                                    Settings
                                </a>
                            </li>
                            <li class="divider"></li>
                            <li><a href="logout" style="padding-top: 12px !important;"><i
                                    class="mdi-hardware-keyboard-tab"></i> Logout</a></li>
                        </ul>
                        <a class="truncate btn-flat dropdown-button waves-effect waves-light white-text profile-btn"
                           href="#"
                           data-activates="profile-dropdown"
                           style="margin-top: 10px !important; margin-left: -15px !important; padding-right: 0px !important;">${sessionScope.firstName}<i
                                class="material-icons right">arrow_drop_down</i></a>
                        <!--<p class="user-roal">Administrator</p>-->
                    </div>
                </div>
            </li>
            <!--<div class="center">-->
            <!--<img src="<s:url action='getImage'><s:param name='ImageID'>${sessionScope.id}</s:param><s:param name='type'>employee</s:param></s:url>"-->
            <!--class="circle" style="width: 150px; height: 150px; margin-top: 10px; margin-bottom: 5px;">-->
            <!--<a href="" style="padding: 0px !important;"></a>-->
            <!--<a href="logout" style="padding: 0px !important;">Logout</a>-->
            <!--</div>-->

            <ul class="collapsible collapsibleHighlight" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="login">
                        <span class="grey-text text-lighten-3">
                            <i class="material-icons">home</i>
                        <b>Home</b>
                        </span>
                    </a>
                </li>
            </ul>
            <!--  <li class="no-padding"> -->
            <ul class="collapsible collapsibleHighlight" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header">
                        <span class="grey-text text-lighten-3">
                            <i class="material-icons">build</i>
                            <b>Maintenance</b>
                        </span>
                    </a>
                    <div class="collapsible-body" style="background-color: #333333 !important;">
                        <ul class="navul">
                            <li><a href="employeeMaintenance">Employee</a></li>
                            <li><a href="productMaintenance">Product</a></li>
                            <li><a href="serviceMaintenance">Service</a></li>
                            <li><a href="packageMaintenance">Package</a></li>
                            <li><a href="locationMaintenance">Transportation Charge</a></li>
                            <li><a href="extraChargeMaintenance">Fee</a></li>
                            <li><a href="promoMaintenance">Promo</a></li>
                            <li><a href="discountMaintenance">Discount</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a class="collapsible-header">
                        <span class="grey-text text-lighten-3">
                            <i class="material-icons">shopping_cart</i>
                            <b>Transaction</b>
                        </span>
                    </a>
                    <div class="collapsible-body" style="background-color: #333333 !important;">
                        <ul class="navul">
                            <li><a href="getInventory">Inventory</a></li>
                            <li><a href="transWalkIn">Walk In</a></li>
                            <li><a href="transReserve">Reservation</a></li>
                            <li><a href="productsales">Product Sales</a></li>
                            <li><a href="paymentTransaction">Payment</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a class="collapsible-header">
                        <span class="grey-text text-lighten-3">
                        <i class="material-icons">search</i>
                        <b>Queries</b>
                        </span>
                    </a>
                    <div class="collapsible-body" style="background-color: #333333 !important;">
                        <ul class="navul">
                            <li><a href="employeeQueries">Employee</a></li>
                            <li><a href="productQueries">Product</a></li>
                            <li><a href="serviceQueries">Service</a></li>
                            <li><a href="packageQueries">Package</a></li>
                            <li><a href="deliveryQueries">Transportation Charge</a></li>
                            <li><a href="extraQueries">Fee</a></li>
                            <li><a href="promoQueries">Promo</a></li>
                            <li><a href="discountQueries">Discount</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a class="collapsible-header">
                        <span class="grey-text text-lighten-3">
                            <i class="material-icons">assessment</i>
                            <b>Reports</b>
                        </span>
                    </a>
                    <div class="collapsible-body" style="background-color: #333333 !important;">
                        <ul class="navul">
                            <li><a href="orderPage">Product Purchases</a></li>
                            <li><a href="salesReport">Sales Reports</a></li>
                            <li><a href="productTags">Product Tags</a></li>
                            <li><a href="cancelledReservations">Cancelled Reservations</a></li>
                            <li><a href="serviceAvailments">Service Availments</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
            <ul class="collapsible collapsibleHighlight" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="getDependencies">
                        <span class="grey-text text-lighten-3">
                            <i class="material-icons">settings</i>
                            <b>Utilities</b>
                        </span>
                    </a>
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
        <!--<div id="loader-wrapper">-->
        <!--<div id="loader"></div>-->
        <!--<div class="loader-section section-left"></div>-->
        <!--<div class="loader-section section-right"></div>-->
        <!--</div>-->
        <tiles:insertAttribute name="body"></tiles:insertAttribute>

    </article>


</div>


<!--Import jQuery before materialize.js-->

<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>
<script type="text/javascript" src="js/angular-animate.min.js"></script>
<script type="text/javascript" src="js/angular-datatables.min.js"></script>
<script type="text/javascript" src="js/angular-datatables.buttons.min.js"></script>
<script type="text/javascript" src="js/angular-materialize.min.js"></script>
<script type="text/javascript" src="js/bartstable.js"></script>
<!--<script type="text/javascript" src="js/picker.date.js"></script>-->
<script type="text/javascript" src="js/jquery.validate.js"></script>
<script type="text/javascript" src="js/maintenance-emp.js"></script>
<script type="text/javascript" src="js/additional.js"></script>
<script type="text/javascript" src="js/validation.js"></script>
<script type="text/javascript" src="js/stepform.js"></script>
<script type="text/javascript" src="js/jquery.formatter.min.js"></script>
<script type="text/javascript" src="js/dropify.min.js"></script>
<script type="text/javascript" src="js/sweetalert.min.js"></script>
<script type="text/javascript" src="js/angular-SweetAlert.min.js"></script>
<script type="text/javascript" src="js/ellipsis.js"></script>
<script type="text/javascript" src="js/priceformat.js"></script>
<script type="text/javascript" src="js/formatted-numbers.js"></script>
<script type="text/javascript" src="js/jquery.formatCurrency-1.4.0.js"></script>
<script type="text/javascript" src="js/moment-with-locale.js"></script>
<script type="text/javascript" src="js/jquery.ellipsis.min.js"></script>
<script type="text/javascript" src="js/materialize.clockpicker.js"></script>
<script type="text/javascript" src="js/lodash.min.js"></script>
<script type="text/javascript" src="js/highcharts.js"></script>
<script type="text/javascript" src="js/exporting.js"></script>
<script type="text/javascript" src="js/fullcalendar.js"></script>
<script type="text/javascript" src="js/moment.min.js"></script>
<!--<script type="text/javascript" src="js/ajax.js"></script>-->
<script type="text/javascript" src="js/nouislider.min.js"></script>

<!-- <script type="text/javascript" src="js/ProductsSales/ajax.js"></script> -->
<!-- jQuery Ajax -->

<script type="text/javascript" src="js/ajax/employee-position.js"></script>
<script type="text/javascript" src="js/ajax/product-name.js"></script>
<script type="text/javascript" src="js/ajax/service-name.js"></script>
<script type="text/javascript" src="js/ajax/product-category.js"></script>
<script type="text/javascript" src="js/ajax/service-category.js"></script>
<script type="text/javascript" src="js/ajax/delivery.js"></script>
<script type="text/javascript" src="js/ajax/extracharge.js"></script>
<script type="text/javascript" src="js/ajax/discount.js"></script>
<script type="text/javascript" src="js/ajax/discount-create.js"></script>
<script type="text/javascript" src="js/ajax/discount-update.js"></script>
<script type="text/javascript" src="js/ajax/discount-requirement.js"></script>
<script type="text/javascript" src="js/ajax/discount-deactivate.js"></script>
<script type="text/javascript" src="js/ajax/package.js"></script>
<script type="text/javascript" src="js/ajax/package-view.js"></script>
<script type="text/javascript" src="js/ajax/package-update.js"></script>
<script type="text/javascript" src="js/ajax/package-deactivate.js"></script>
<script type="text/javascript" src="js/ajax/promo.js"></script>
<script type="text/javascript" src="js/ajax/promo-update.js"></script>
<script type="text/javascript" src="js/ajax/promo-update-func.js"></script>
<script type="text/javascript" src="js/ajax/promo-requirement.js"></script>
<script type="text/javascript" src="js/perfect-scrollbar.jquery.min.js"></script>
<script type="text/javascript" src="js/baselayout.js"></script>
<script type="text/javascript" src="js/angular-resource.min.js"></script>
<script type="text/javascript" src="js/angular-checklist.js"></script>
<!--<script type="text/javascript" src="js/angular-materialize.min.js"></script>-->


<!-- Angular imports -->
<script type="text/javascript" src="app/main.js"></script>
<script type="text/javascript" src="app/dashboard/dashboard.fac.js"></script>
<script type="text/javascript" src="app/dashboard/dashboard.ctrl.js"></script>
<script type="text/javascript" src="app/employee/employee-factory.js"></script>
<script type="text/javascript" src="app/employee/employee-controller.js"></script>
<script type="text/javascript" src="app/payment/payment.ctr.js"></script>
<script type="text/javascript" src="app/payment/payment.fac.js"></script>
<script type="text/javascript" src="app/walkin/walkin.ctr.js"></script>
<script type="text/javascript" src="app/walkin/walkin.fac.js"></script>
<script type="text/javascript" src="app/prodsales/locationfactory.fac.js"></script>
<script type="text/javascript" src="app/prodsales/prodSales.ctrl.js"></script>
<script type="text/javascript" src="app/reservation/reservationFactory.fac.js"></script>
<script type="text/javascript" src="app/reservation/reservation-table.ctrl.js"></script>
<script type="text/javascript" src="app/queries/queryFactory.fac.js"></script>
<script type="text/javascript" src="app/queries/employee/empQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/productQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/serviceQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/packageQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/deliveryQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/extraQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/promoQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/queries/employee/discountQueryController.ctrl.js"></script>
<script type="text/javascript" src="app/report/reportsFactory.fac.js"></script>
<script type="text/javascript" src="app/report/salesReportController.ctrl.js"></script>
<script type="text/javascript" src="app/report/reservationReportController.ctrl.js"></script>
<script type="text/javascript" src="app/report/productTagsReportController.ctrl.js"></script>
<script type="text/javascript" src="app/report/cancelledReservationController.ctrl.js"></script>
<script type="text/javascript" src="app/report/serviceAvailmentsController.ctrl.js"></script>
</body>

</html>
