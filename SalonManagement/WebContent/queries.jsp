<!DOCTYPE html>
 <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ page import="com.gss.model.Employee" %>
  <%@ page import="com.gss.model.EmployeeCategory" %>
 
<html ng-app>
  <head>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mtnc-emp.css"/>

  <link rel="stylesheet" type="text/css" href="./css/bartstable.css"/>
  <link rel="stylesheet" type="text/css" href="./css/material.min.css"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>


  <body class="purple lighten-5">

  <div class="wrapper">
            <header class="headnav">
                <ul id="slide-out" class="side-nav fixed z-depth-0">
                  <div class="center">
                      <img src="./img/logo.png" class="circle" style="width: 100%; height: 100%; margin-top: 40px; margin-bottom: 20px;">
                    </div>
                  <li>
                  <a href="admin-home.jsp" class="waves-effect"><b>Home</b></a>
                  </li>
                 <!--  <li class="no-padding"> -->
                    <ul class="collapsible" data-collapsible="accordion">
                      <li>
                        <a class="collapsible-header"><b>Maintenance</b></a>
                          <div class="collapsible-body">
                            <ul>
                              <li class="purple lighten-4"><a href="employeeMaintenance">Employee</a></li>
                                      <li><a href="productServiceMaintenance">Product & Service</a></li>
                                      <li><a href="catalogueMaintenance">Catalogue</a></li>
                                      <li><a href="packageMaintenance">Package</a></li>
                                      <li><a href="locationMaintenance">Delivery Charge</a></li>
                                      <li><a href="extraChargeMaintenance">Other Chrage</a></li>
                                      <li><a href="discountMaintenance">Discount</a></li>
                                      <li><a href="promoMaintenance">Promo</a></li>
                            </ul>
                          </div>
                      </li>
                      <li>
                        <a class="collapsible-header"><b>Transaction</b></a>
                          <div class="collapsible-body">
                            <ul>
                              <li><a href="#">Inventory</a></li>
                              <li><a href="#">Reservation</a></li>
                              <li><a href="#">VIP</a></li>
                              <li><a href="#">Product Order</a></li>
                              <li><a href="#">Walk-In</a></li>
                            </ul>
                          </div>
                      </li>
                    </ul>
                    <li><a href="queries.jsp"><b>Queries</b></a></li>
                    <li><a href="reports.jsp"><b>Reports</b></a></li>
                  <li><a href="utilities.jsp" class="waves-effect"><b>Utilities</b></a></li>
                </ul>

        
               <nav class="z-depth-0">
                 <div class="nav-wrapper purple darken-4">
                   <a href="#!" class="brand-logo purple-text text-lighten-5" style="margin-left: 20px;">Salon Management System</a>
                 
                   <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                   <ul class="side-nav" id="mobile-demo">
                     <li>
                     <a href="admin-home.jsp" class="waves-effect"><b>Home</b></a>
                     </li>
                       <ul class="collapsible collapsible-accordion">
                          <li>
                           <a class="waves-effect collapsible-header"><b>Maintenance</b></a>
                          
                             <div class="collapsible-body">
                               <ul>
                                 <li><a href="employeeMaintenance">Employee</a></li>
                                      <li><a href="productServiceMaintenance">Product & Service</a></li>
                                      <li><a href="promoMaintenance">Promo</a></li>
                                      <li><a href="discountMaintenance">Discount</a></li>
                                      <li><a href="maintenance-package.jsp">Package</a></li>
                                      <li><a href="catalogueMaintenance">Catalogue</a></li>
                                      <li><a href="extraChargeMaintenance">Extra Charge</a></li>
                               </ul>
                             </div>
                         </li>
                         <li>
                           <a class="waves-effect collapsible-header"><b>Transaction</b></a>
                             <div class="collapsible-body">
                               <ul>
                                 <li><a href="#">Inventory</a></li>
                                 <li><a href="#">Reservation</a></li>
                                 <li><a href="#">VIP</a></li>
                                 <li><a href="#">Product Order</a></li>
                                 <li><a href="#">Walk-In</a></li>
                               </ul>
                             </div>
                         </li>
                       </ul>
                    <li><a href="queries.jsp"><b>Queries</b></a></li>
                    <li><a href="utilities.jsp"><b>Utilities</b></a></li>
                   </ul>
                 </div>
               </nav>
        </header>

        <article>
        <!-- <div class="container"> -->
          <div class="wrapper">
<!--                     <div class="aside aside1 z-depth-barts">
                    
                    </div> -->

                    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                        <h3 class="grey-text text-darken-1">Queries</h3>
                        <div class="input-field col s3" style="top: 80px !important; margin-left: 15px; width: 150px !important;">
                              <select id="qfilter">
                                <option></option>
                              </select>
                              <label for="qfilter" class="active">Filter by:</label>
                        </div>
                        <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                <thead>
                                    <tr>
                                        <th><center>ID</center></th>
                                        <th><center>Name</center></th>
                                        <th><center>Actions</center></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td style="padding:0; margin:0;"><center></center></td>
                                        <td style="padding:0; margin:0;"><center></center></td>
                                        <td class="center" style="padding:0; margin:0;">
                                        <a data-delay="30" data-position="bottom" data-tooltip="View" class="tooltipped waves-effect waves-purple btn-flat transparent black-text" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                        <i class="material-icons"></i>
                                        </a>
                                        </td>
                                    </tr>
                                    
                                </tbody>
                            </table>
                        </div>


                      




                </div>
        </div>
      <!-- </div> -->
        </article>

        

  </div>

  <style type="text/css">
  #addOptionSelect {
    height: 150px !important;
  }

  #updateAddOptionSelect {
    height: 150px !important;
  }

  #create {
    width: 80% !important;
    height: 86% !important;
    max-height: 100% !important;
  }
  #empArchive {
    widows: 80% !important;
    height: 86% !important;
    max-height: 100% !important;
  }

  #update{
    width: 80% !important;
    height: 86% !important;
    max-height: 100% !important;
  }

  #addOption {
    width: 40% !important;
  }

  #updateOption {
    width: 40% !important;
  }

  #updateAddOption {
    width: 40% !important;
  }
  
  .error{
    color: red;
  }
  </style>

  <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./js/materialize.js"></script>
    <script type="text/javascript" src="./js/angular.min.js"></script>
    <script type="text/javascript" src="./js/maintenance-emp.js"></script>
    <script type="text/javascript" src="./js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="./js/bartstable.js"></script>
    <script type="text/javascript" src="./js/picker.date.js"></script>
    <script type="text/javascript" src="./js/jquery.validate.js"></script>
    <script type="text/javascript" src="./js/addvalidate.js"></script>
    <script type="text/javascript" src="./js/validation.js"></script>


    




    <script>
      $('#deleteEmpForm').validate({
          submitHandler: function() {
            Materialize.toast('Successfully Deactivated!', 5000, 'green');
            $(form).ajaxSubmit();
          }
        });
    </script>

  </body>



</html>