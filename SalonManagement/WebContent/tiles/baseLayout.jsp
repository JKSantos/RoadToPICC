<!DOCTYPE html>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="css/materialize.css"/>
    <link type="text/css" rel="stylesheet" href="css/mystyle.css"/>
    <link type="text/css" rel="stylesheet" href="css/mtnc-emp.css"/>
    <link rel="stylesheet" type="text/css" href="css/bartstable.css"/>
    <link rel="stylesheet" type="text/css" href="css/material.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/dropify.min.css"/>
    <!--<link rel="stylesheet" type="text/css" href="css/drag.css"/>-->
    <!--<link rel="stylesheet" type="text/css" href="css/animate.css"/>-->

    <%@ taglib uri="/struts-tags" prefix="s" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>
        <tiles:insertAttribute name="title" ignore="true"/>
    </title>
</head>

<body class="purple lighten-5">
<div class="wrapper">
    <header class="headnav">
        <ul id="slide-out" class="side-nav fixed z-depth-0">
            <div class="center">
                <img src="<s:url action='getImage'><s:param name='ImageID'>${sessionScope.id}</s:param><s:param name='type'>employee</s:param></s:url>"
                     class="circle" style="width: 150px; height: 150px; margin-top: 10px; margin-bottom: 5px;">
                <a href="" style="padding: 0px !important;">${sessionScope.firstName} ${sessionScope.lastName}</a>
                <a href="logout" style="padding: 0px !important;">Logout</a>
            </div>

            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header" href="admin-home.jsp"><i
                            class="material-icons">home</i><b>Home</b></a>
                </li>
            </ul>
            <!--  <li class="no-padding"> -->
            <ul class="collapsible" data-collapsible="accordion">
                <li>
                    <a class="collapsible-header"><i class="material-icons">build</i><b>Maintenance</b></a>
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
                    <a class="collapsible-header"><i class="material-icons">shopping_cart</i><b>Transaction</b></a>
                    <div class="collapsible-body">
                        <ul>
                            <li><a href="getInventory">Inventory</a></li>
                            <li><a href="transactions-reservation.jsp">Reservation</a></li>
                            <li><a href="transactions-productorder.jsp">Product Sales</a></li>
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
                            class="material-icons">assessment</i><b>Reports</b></a>
                </li>
            </ul>
</div>
</ul>

<!--<nav class="z-depth-0">-->
<!--<div class="nav-wrapper purple darken-4">-->
<!--<a href="#!" class="brand-logo purple-text text-lighten-5" style="margin-left: 20px;">Salon Management System</a>-->
<!--<ul id="nav-mobile" class="right">-->
<!--<li><a class="waves-effect">jeff</a></li>-->
<!--<li><a class="waves-effect">asdas</a></li>-->
<!--</ul>-->

<!--</div>-->
<!--</nav>-->
<nav class="z-depth-0">
    <div class="nav-wrapper purple darken-4">
        <a href="#" class="brand-logo purple-text text-lighten-5 center">Salon Management System</a>
    </div>
</nav>
</header>

<article>
    <!--<div class="wrapper">-->
    <!--<div class="col s12 center" style="margin-top: 200px;">-->
    <!--<button id="welcome" class="btn waves-effect waves-light purple darken-3 animated infinite rubberBand z-depth-3" style="width: 300px; height: 150px;font-size: 30px;">WELCOME</button>-->
    <!--</div>-->
    <!--</div>-->


    <!--<div class="col s12 center" style="margin-top: 200px;">-->
    <!--<h3>${ sessionScope.firstName } ${ sessionScope.lastName }, your is ${ sessionScope.id }</h3>-->
    <!--</div>-->
    <!--<div id="draggable" class="ui-widget-content fixed-action-btn horizontal transparent" style="border:0px !important; bottom: 45px; right: 24px; z-index: 100000px;">-->
    <!--<a class="btn-floating btn-large red">-->
    <!--<i class="large material-icons">mode_edit</i>-->
    <!--</a>-->
    <!--<ul>-->
    <!--<li><a class="btn-floating red"><i class="material-icons">insert_chart</i></a></li>-->
    <!--<li><a class="btn-floating yellow darken-1"><i class="material-icons">format_quote</i></a></li>-->
    <!--<li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>-->
    <!--<li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>-->
    <!--</ul>-->
    <!--</div>-->

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

    #empModalUpdate {
        width: 600px !important;
        height: 590px !important;
        max-height: 100% !important;
        margin-top: -30px !important;
    }

    #createAddOption {
        width: 500px !important;
        height: 400px !important;
    }

    label .has-error {
        color: red;
    }

</style>


<!--Import jQuery before materialize.js-->
<script type="text/javascript" src="js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="js/materialize.js"></script>
<script type="text/javascript" src="js/angular.min.js"></script>
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
<script type="text/javascript" src="js/ajax.js"></script>



<!--<script type="text/javascript" src="js/drag.js"></script>-->

<script type="text/javascript">
    $(document).ready(function () {
        $(".button-collapse").sideNav();
    });

    $(document).ready(function (){
        $('select').material_select();
        $("select[required]").css({display: "inline", height: 0, padding: 0, width: 0});
    });

    $('.dropify').dropify({
        messages: {
            'error': 'Oops, something wrong appended.'
        }
    });

    $('#createContact').formatter({
        'pattern': '+63 {{999}}-{{999}}-{{9999}}',
        'persistent': true
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