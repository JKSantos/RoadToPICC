<!DOCTYPE html>
<html ng-app>
  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <head>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mtnc-catalogue.css"/>
  <link rel="stylesheet" type="text/css" href="./css/bartstable.css"/>

  <link rel="stylesheet" type="text/css" href="./css/material.min.css"/>

    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>

  <body class="blue-grey lighten-5">
  <div class="wrapper">
        <header class="headnav">
                        <ul id="slide-out" class="side-nav fixed z-depth-0">
                          <div class="center">
                              <img src="./img/logo.png" class="circle" style="width: 100%; height: 100%; margin-top: 40px; margin-bottom: 20px;">
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
                      
                                      <li><a href="packageMaintenance">Package</a></li>
                                      <li><a href="locationMaintenance">Delivery Charge</a></li>
                                      <li><a href="extraChargeMaintenance">Other Charge</a></li>
                                      <li><a href="promoMaintenance">Promo</a></li>
                                      <li><a href="discountMaintenance">Discount</a></li>
                                    </ul>
                                  </div>
                              </li>
                              <li>
                                <a class="collapsible-header active"><b>Transaction</b></a>
                                  <div class="collapsible-body">
                                    <ul>
                                      <li><a href="transactions-inventory.jsp">Inventory</a></li>
                                      <li><a href="transactions-reservation.jsp">Reservation</a></li>
                                      <li><a href="transactions-productorder.jsp">Product Sales</a></li>
                                      <li  class="purple lighten-4"><a href="transactions-vip.jsp">Payment</a></li>
                                      <li><a href="transactions-walkin.jsp">Walk In</a></li>
                                    </ul>
                                  </div>
                              </li>
                            </ul>
                          <li><a href="utilities.jsp" class="waves-effect"><b>Utilities</b></a></li>
                        </ul>

                
                       <nav class="z-depth-0">
                         <div class="nav-wrapper purple">
                           <a href="#!" class="brand-logo white-text" style="margin-left: 30px;">Salon Management System</a>
                         
                           <a href="#" data-activates="mobile-demo" class="button-collapse"><i class="material-icons">menu</i></a>
                           <ul class="side-nav" id="mobile-demo">
                             <li><a href="admin-home.jsp" class="waves-effect waves-purple"><b>Home</b></a>
                               <ul class="collapsible collapsible-accordion">
                                 <li>
                                   <a class="collapsible-header"><b>Maintenance</b></a>
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
                                   <a class="collapsible-header active"><b>Transaction</b></a>
                                     <div class="collapsible-body">
                                       <ul>
                                         <li class="purple"><a href="transactions-inventory.jsp">Inventory</a></li>
                                         <li><a href="transactions-reservation.jsp">Reservation</a></li>
                                         <li class="purple lighten-4"><a href="transactions-productorder.jsp">Product Sales</a></li>
                                         <li><a href="transactions-walkin.jsp">Walk-In</a></li>
                                       </ul>
                                     </div>
                                 </li>
                               </ul>
                             <li><a href="utilities.jsp" class="waves-effect waves-purple"><b>Utilities</b></a></li>
                           </ul>
                         </div>
                       </nav>
                </header>

        <article>
          <div class="wrapper">
                              
                      <div class="aside aside2 z-depth-barts">
                             <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                             <h3 class="grey-text text-darken-1">Payment</h3>
                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#createpayment" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                             <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                     <thead>
                                         <tr>
                                             <th><center>Event Date</center></th>
                                             <th><center>Guest Name</center></th>
                                             <th><center>Payment Amount</center></th>
                                             <th><center>Remaining Balance</center></th>
                                             <th><center>Action</center></th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                         <tr>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td class="center" style="padding:0; margin:0;"><center>
                                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#viewpayment" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="View"><i class="material-icons">visibility</i></a>
                                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#updatepayment" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update"><i class="material-icons">edit</i></a>
                                              <a data-delay="30" data-position="bottom" data-tooltip="Deactivate" class="tooltipped waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4" href="" style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Deactivate"><i class="material-icons">delete</i></a></center>
                                             </td>
                                         </tr>
                                     </tbody>
                                 </table>
                           </div>                    
                        </div>

                        <div id="createpayment" class="modal modal-fixed-footer" style="width: 30% !important; height: 65% !important; max-height: 100% !important; margin-top: 0px;">
                          <form class="col s12" name="createWalkinForm" method="post" action="createTag">
                              <div class="modal-content">
                                <div class="wrapper">
                                  <h4 class="grey-text text-darken-1"><center>Create Payment</center></h4>
                                  <div class="aside asideA z-depth-0">
                                    <div class="row">
                                      <div class="input-field col s12">
                                        <select class="multiple" id="paymentgn">
                                          <option selected disabled>Guest Name...</option>
                                        </select>
                                        <label for="paymentgn">Guest Name</label>
                                      </div>
                                      <div class="input-field col s12" style="margin-top: 15px;">
                                        <input type="text" id="paymentrb" class="black-text" disabled value="0.00">
                                        <label class="active black-text" for="paymentrb">Remaining Balance</label>
                                      </div>
                                      <div class="input-field col s12" style="margin-top: 4px;">
                                        <input type="text" id="paymentpa">
                                        <label for="paymentpa" class="active">Payment Amount</label>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                              </div>
                          </form>
                        </div>

                        <div id="updatepayment" class="modal modal-fixed-footer" style="width: 30% !important; height: 65% !important; max-height: 100% !important; margin-top: 0px;">
                          <form class="col s12" name="createWalkinForm" method="post" action="createTag">
                              <div class="modal-content">
                                <div class="wrapper">
                                  <h4 class="grey-text text-darken-1"><center>Update Payment</center></h4>
                                  <div class="aside asideA z-depth-0">
                                    <div class="row">
                                      <div class="input-field col s12">
                                        <select class="multiple" id="paymentgn" disabled>
                                          <option selected disabled>Guest Name...</option>
                                        </select>
                                        <label for="paymentgn">Guest Name</label>
                                      </div>
                                      <div class="input-field col s12" style="margin-top: 15px;">
                                        <input type="text" id="paymentrb" class="black-text" disabled value="0.00">
                                        <label class="active black-text" for="paymentrb">Remaining Balance</label>
                                      </div>
                                      <div class="input-field col s12" style="margin-top: 4px;">
                                        <input type="text" id="paymentpa">
                                        <label for="paymentpa" class="active">Payment Amount</label>
                                      </div>
                                    </div>
                                  </div>
                                </div>
                              </div>
                              <div class="modal-footer">
                                <button type="submit" class="waves-effect waves-white btn-flat purple white-text">SAVE</button>
                                <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">CANCEL</a>
                              </div>
                          </form>
                        </div>

                        


        </article>
           <!-- Modal Structure -->
  </div>
                

    <style type="text/css">
        .wrapper > * {
          flex: 1 100%;
        }

        .wrapper {
          display: -webkit-box;
          display: -moz-box;
          display: -ms-flexbox;
          display: -webkit-flex;
          display: flex;
          flex-direction: row;
        
          -webkit-flex-flow: row wrap;
          flex-flow: row wrap;
          -moz-flex-flow: row wrap;
          -o-flex-flow: row wrap;
        }

        .asideA {
          background: transparent;
          border-radius: 1px;
          margin: 10px;
          text-align: center;
          width: 25px;
          height: 30%;
        }

        @media all and (min-width: 500px) {
          .aside { flex: 1 auto; }

        }

        .zeromargintop {
          margin-top: 0%;
        }
      </style>

  <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./js/materialize.js"></script>

    <script type="text/javascript" src="./js/maintenance-emp.js"></script>
    <script type="text/javascript" src="./js/angular.min.js"></script>

    <script type="text/javascript" src="./js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="./js/bartstable.js"></script>

    <script type="text/javascript">
    $(document).ready(function(){
        $('ul.tabs').tabs('select_tab', 'add');
      });
    </script>
    


  </body>



</html>