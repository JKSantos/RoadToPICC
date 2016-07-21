<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/materialize.css"/>
    <link type="text/css" rel="stylesheet" href="css/mystyle.css"/>
    <link type="text/css" rel="stylesheet" href="css/admin.css"/>

    <link rel="stylesheet" type="text/css" href="css/drag.css"/>
    <link rel="stylesheet" type="text/css" href="css/animate.css"/>

    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>

<body class="purple lighten-5">
<div class="wrapper">
    <header class="headnav">
        <ul id="slide-out" class="side-nav fixed z-depth-0">
            <div class="center">
                <img src="img/logo.png" class="circle"
                     style="width: 100%; height: 100%; margin-top: 40px; margin-bottom: 20px;">
            </div>

            <li><a href="admin-home.jsp" class="waves-effect"><b>Home</b></a></li>
            <!--  <li class="no-padding"> -->
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header"><b>Maintenance</b></a>
                    <div class="collapsible-body">
                        <ul>
                            <li><a href="employeeMaintenance">Employee</a></li>
                            <li><a href="productServiceMaintenance">Product & Service</a></li>
                            <li><a href="catalogueMaintenance">Catalogue</a></li>
                            <li><a href="packageMaintenance">Package</a></li>
                            <li><a href="locationMaintenance">Delivery Charge</a></li>
                            <li><a href="extraChargeMaintenance">Other Charge</a></li>
                            <li><a href="promoMaintenance">Promo</a></li>
                            <li><a href="discountMaintenance">Discount</a></li>
                        </ul>
                    </div>
                </li>
                <li>
                    <a class="collapsible-header"><b>Transaction</b></a>
                    <div class="collapsible-body">
                        <ul>
                            <li><a href="getInventory">Inventory</a></li>
                            <li><a href="transactions-reservation.jsp">Reservation</a></li>
                            <li><a href="transactions-productorder.jsp">Product Order</a></li>
                            <li><a href="transactions-vip.jsp">VIP</a></li>
                            <li><a href="transactions-walkin.jsp">Walk In</a></li>
                        </ul>
                    </div>
                </li>
            </ul>
            <li><a href="utilities.jsp" class="waves-effect"><b>Utilities</b></a></li>
        </ul>

        <nav class="z-depth-0">
            <div class="nav-wrapper purple darken-4">
                <a href="#!" class="brand-logo purple-text text-lighten-5" style="margin-left: 30px;">Salon Management
                    System</a>

                <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                <ul class="side-nav" id="mobile-demo">
                    <li><a href="admin-home.jsp" class="waves-effect waves-orange"><b>Home</b></a></li>
                    <ul class="collapsible collapsible-accordion">
                        <li>
                            <a class="waves-effect waves-orange collapsible-header"><b>Maintenance</b></a>
                            <div class="collapsible-body">
                                <ul>
                                    <li><a href="tiles/maintenance-emp.jsp">Employee</a></li>
                                    <li><a href="maintenance-prodsvc.jsp">Product & Service</a></li>
                                    <li><a href="maintenance-promo.jsp">Promo</a></li>
                                    <li><a href="maintenance-discount.jsp">Discount</a></li>
                                    <li><a href="maintenance-package.jsp">Package</a></li>
                                    <li><a href="maintenance-catalogue.jsp">Catalogue</a></li>
                                    <li><a href="maintenance-extra.jsp">Extra Charge</a></li>
                                </ul>
                            </div>
                        </li>
                        <li>
                            <a class="waves-effect waves-orange collapsible-header"><b>Transaction</b></a>
                            <div class="collapsible-body">
                                <ul>
                                    <li><a href="transactions-inventory.jsp">Inventory</a></li>
                                    <li><a href="transactions-reservation.jsp">Reservation</a></li>
                                    <li><a href="transactions-productorder.jsp">Product Order</a></li>
                                    <li><a href="#!">Walk-In</a></li>
                                </ul>
                            </div>
                        </li>
                    </ul>
                    <li><a href="utilities.jsp" class="waves-effect waves-orange"><b>Utilities</b></a></li>
                </ul>
            </div>
        </nav>
    </header>

    <article>


        <div class="col s12 center" style="margin-top: 200px;">
            <h3>${ sessionScope.firstName } ${ sessionScope.lastName }, your is ${ sessionScope.id }</h3>
        </div>

        <div id="draggable" class="ui-widget-content fixed-action-btn horizontal transparent"
             style="border:0px !important; bottom: 45px; right: 24px; z-index: 100000px;">
            <a class="btn-floating btn-large red">
                <i class="large material-icons">mode_edit</i>
            </a>
            <ul>
                <li><a class="btn-floating red"><i class="material-icons">insert_chart</i></a></li>
                <li><a class="btn-floating yellow darken-1"><i class="material-icons">format_quote</i></a></li>
                <li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
                <li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>
            </ul>
        </div>

    </article>


</div>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.min.js"></script>
<script type="text/javascript" src="js/drag.js"></script>

<script type="text/javascript">
    $(document).ready(function () {
        $(".button-collapse").sideNav();
    });

    $(function () {
        $('#map').vectorMap({map: 'ph_mill_en'});
    });
</script>

<script>
    $(function () {
        $("#draggable").draggable();
    });
</script>


</body>

</html>