<!DOCTYPE html>
<html ng-app>
  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <head>  
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mtnc-catalogue.css"/>
  <link rel="stylesheet" type="text/css" href="./css/bartstable.css"/>
  <link rel="stylesheet" type="text/css" href="./css/material.min.css"/>


    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <link rel="icon" type="image/png" href="img/Salon_Icon.ico" />
    <title>Reservation</title>
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
                                      <li class="purple lighten-4"><a href="transactions-reservation.jsp">Reservation</a></li>
                                      <li><a href="transactions-productorder.jsp">Product Order</a></li>
           
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
                                         <li><a href="transactions-inventory.jsp">Inventory</a></li>
                                         <li class="purple"><a href="transactions-reservation.jsp">Reservation</a></li>
                                         <li><a href="transactions-productorder.jsp">Product Order</a></li>
                                         <li><a href="#!">Walk-In</a></li>
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
                             <h3 class="grey-text text-darken-1">Reservation</h3>
                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text" href="#createreserve" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                             <a class="tooltipped waves-effect waves-light btn-flat purple darken-3 left white-text" href="calendar-view.jsp" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom" data-tooltip="Calendar"><center>Calendar</center></a>
                             <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                                     <thead>
                                         <tr>
                                             <th><center>Date of Event</center></th>
                                             <th><center>Guest Name</center></th>
                                             <th><center>Venue</center></th>
                                             <th><center>Time</center></th>
                                             <th><center>Status</center></th>
                                             <th><center>Actions</center></th>
                                         </tr>
                                     </thead>
                                     <tbody>
                                         <tr>
                                             <td style="padding:0; margin:0;"><center>01/01/01</center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td style="padding:0; margin:0;"><center></center></td>
                                             <td class="center" style="padding:0; margin:0;"><center>
                                             <a href="" class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" title="View" style="padding-left: 10px; padding-right:10px; margin: 3px;" data-delay="30" data-position="bottom" data-tooltip="View"><i class="material-icons">visibility</i></a>
                                             <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text" title="Update" href="#updateReservation" style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30" data-position="bottom" data-tooltip="Update"><i class="material-icons">edit</i></a>
                                             <a data-delay="30" data-position="bottom" data-tooltip="Deactivate" class="tooltipped waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4" href="" style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Deactivate"><i class="material-icons">delete</i></a>
                                             </center>
                                             </td>
                                         </tr>
                                     </tbody>
                                 </table>
                           </div>                    
                        </div>

                        <div id="reservecalendar" class="modal modal-fixed-footer">
                          <h3>Calendar</h3>
                        </div>

                        <!-- START -->
                        <div id="createreserve" class="modal modal-fixed-footer" style="width: 95% !important; height: 92% !important; max-height: 100% !important; margin-top: -40px;">
                              <div class="col s12">
                                <ul class="tabs">
                                  <li class="tab col s6"><a class="active purple-text" href="#homeservice">Home Service</a></li>
                                  <li class="tab col s6"><a class="purple-text" href="#eventreservation">Event</a></li>
                                </ul>
                              </div>

                            
                              <div id="homeservice" class="col s12">
                                <form class="col s12" name="addReservation" method="post" action="">
                                <div class="modal-content">
                                  <div class="wrapper">
                                    <div class="aside asideA z-depth-0">
                                      <div class="row">
                                        <h4>Home Service Reservation</h4>
                                        <div class="input-field col s12" style="margin-top: -10px;">
                                          <input type="text" id="homecustname" value="">
                                          <label for="homecustname" class="active">Name</label>
                                        </div>
                                        <div class="input-field col s12" style="margin-top: 0;">
                                          <input type="number" id="homecustnum" value="" min="0">
                                          <label for="homecustnum" class="active">Contact Number</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input name="street" type="text" id="homestreet">
                                          <label for="homestreet" class="active">Street</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input type="text" id="homebrgy" value="">
                                          <label for="homebrgy" class="active ">Barangay</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input type="text" id="homecity" value="">
                                          <label for="homecity" class="active">City</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="homedate" value="">
                                          <label for="homedate" class="active">Date</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="hometime">
                                          <label for="hometime" class="active">Time</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="number" id="homehc">
                                          <label for="homehc">Head Count</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <select class="multiple zeromargintop" id="homestylist">
                                            <option selected disabled>Stylist..</option>
                                          </select>
                                          <label for="homestylist">Stylist</label>
                                        </div>
                                      </div>
                                    </div>
                                    <div class="aside asideB z-depth-0">
                                      <div class="row">
                                        <h4>Inclusion</h4>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="homepackage">
                                              <option selected disabled>Packages...</option>
                                            </select>
                                            <label for="homepackage">Package</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="homeqty1">
                                            <label class="active" for="homeqty1">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="homeservice">
                                              <option selected disabled>Services...</option>
                                            </select>
                                            <label for="homeservice">Service</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="homeqty2">
                                            <label class="active" for="homeqty2">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="homepromo">
                                              <option selected disabled>Promos...</option>
                                            </select>
                                            <label for="homepromo">Promo</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="homeqty3">
                                            <label class="active" for="homeqty3">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="homeprod">
                                              <option selected disabled>Products...</option>
                                            </select>
                                            <label for="homeprod">Product</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="homeqty4">
                                            <label class="active" for="homeqty4">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>Item Name</th>
                                              <th>Quantity</th>
                                              <th>Price</th>
                                              <th>Action</th>
                                            </tr>
                                          </thead>
                                        </table>
                                      </div>
                                    </div>

                                  </div>
                                  </div>

                                <div class="modal-footer">
                                  <a type="submit" class="modal-trigger waves-effect waves-white btn-flat purple white-text">Save</a>
                                  <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">Cancel</a>
                                </div>
                                </form>
                              </div>

                              <!-- EVENT RESERVATION -->
                              <div id="eventreservation" class="col s12">
                                <form class="col s12" name="addReservation" method="post" action="">
                                <div class="modal-content">
                                  <div class="wrapper">
                                    <div class="aside asideA z-depth-0">
                                      <div class="row">
                                        <h4>Event Reservation</h4>
                                        <div class="input-field col s4">
                                          <select class="multiple" id="eventgtype">
                                            <option>Guest Type</option>
                                          </select>
                                          <label for="eventgtype">Guest Type</label>
                                        </div>
                                        <div class="input-field col s8">
                                          <input type="text" id="eventcompname">
                                          <label for="eventcompname" class="active">Company Name</label>
                                        </div>
                                        <div class="input-field col s12" style="margin-top: -10px;">
                                          <input type="text" id="eventcustname" value="">
                                          <label for="eventcustname" class="active">Name</label>
                                        </div>
                                        <div class="input-field col s12" style="margin-top: 0;">
                                          <input type="number" id="eventcustnum" value="" min="0">
                                          <label for="eventcustnum" class="active">Contact Number</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input name="street" type="text" id="eventstreet">
                                          <label for="eventstreet" class="active">Street</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="eventbrgy" value="">
                                          <label for="eventbrgy" class="active">Barangay</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input class="active" type="text" id="eventcity" value="">
                                          <label for="eventcity" class="active">City</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="eventdate" value="">
                                          <label for="eventdate" class="active">Date</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="eventtime">
                                          <label for="eventtime" class="active">Time</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="number" id="eventhc" min="0">
                                          <label for="eventhc">Head Count</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <select class="multiple" id="eventstylist">
                                            <option selected disabled>Stylist..</option>
                                          </select>
                                          <label for="eventstylist">Stylist</label>
                                        </div>
                                      </div>
                                    </div>
                                    <div class="aside asideB z-depth-0">
                                      <div class="row">
                                        <h4>Inclusion</h4>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="eventpackage">
                                              <option selected disabled>Packages...</option>
                                            </select>
                                            <label for="eventpackage">Package</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="eventqty1">
                                            <label class="active" for="eventqty1">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="eventservices">
                                              <option selected disabled>Services...</option>
                                            </select>
                                            <label for="eventservices">Service</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="eventqty2">
                                            <label class="active" for="eventqty2">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="eventpromo">
                                              <option selected disabled>Promos...</option>
                                            </select>
                                            <label for="eventpromo">Promo</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="eventqty3">
                                            <label class="active" for="eventqty3">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="eventprod">
                                              <option selected disabled>Products...</option>
                                            </select>
                                            <label for="eventprod">Product</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="eventqty4">
                                            <label class="active" for="eventqty4">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>Item Name</th>
                                              <th>Quantity</th>
                                              <th>Price</th>
                                              <th>Action</th>
                                            </tr>
                                          </thead>
                                        </table>
                                      </div>
                                    </div>

                                  </div>
                                  </div>

                                

                                <div class="modal-footer">
                                  <a type="submit" class="modal-trigger waves-effect waves-white btn-flat purple white-text">Save</a>
                                  <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">Cancel</a>
                                </div>
                                </form>
                              </div>
                            </div>
                            <!-- END -->

                              <!-- EVENT RESERVATIoN END -->
                              
                              <!-- UPDATE START================================ -->
                              <!-- START -->
                        <div id="updateReservation" class="modal modal-fixed-footer" style="width: 95% !important; height: 92% !important; max-height: 100% !important; margin-top: -40px;">
                              <div class="col s12">
                                <ul class="tabs">
                                  <li class="tab col s6"><a class="active purple-text" href="#uphomeservice">Home Service</a></li>
                                  <li class="tab col s6"><a class="purple-text" href="#upeventreservation">Event</a></li>
                                </ul>
                              </div>

                            
                              <div id="uphomeservice" class="col s12">
                                <form class="col s12" name="UpdateHomeReservation" method="post" action="">
                                <div class="modal-content">
                                  <div class="wrapper">
                                    <div class="aside asideA z-depth-0">
                                      <div class="row">
                                        <h4>Update Home Service Reservation</h4>
                                        <div class="input-field col s12" style="margin-top: -10px;">
                                          <input type="text" id="uphomecustname" value="">
                                          <label for="uphomecustname" class="active">Name</label>
                                        </div>
                                        <div class="input-field col s12" style="margin-top: 0;">
                                          <input type="number" id="uphomecustnum" value="" min="0">
                                          <label for="uphomecustnum" class="active">Contact Number</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input name="street" type="text" id="uphomestreet">
                                          <label for="uphomestreet" class="active">Street</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input type="text" id="uphomebrgy" value="">
                                          <label for="uphomebrgy" class="active">Barangay</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input class="active" type="text" id="uphomecity" value="">
                                          <label for="uphomecity" class="active">City</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="uphomedate" value="">
                                          <label for="uphomedate" class="active">Date</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="uphometime">
                                          <label for="uphometime" class="active">Time</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="number" id="uphomehc">
                                          <label for="uphomehc">Head Count</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <select class="multiple zeromargintop" id="uphomestylist">
                                            <option selected disabled>Stylist..</option>
                                          </select>
                                          <label for="uphomestylist">Stylist</label>
                                        </div>
                                      </div>
                                    </div>
                                    <div class="aside asideB z-depth-0">
                                      <div class="row">
                                        <h4>Inclusion</h4>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="uphomepackage">
                                              <option selected disabled>Packages...</option>
                                            </select>
                                            <label for="uphomepackage">Package</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="uphomeqty1">
                                            <label class="active" for="uphomeqty1">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="uphomeservice">
                                              <option selected disabled>Services...</option>
                                            </select>
                                            <label for="uphomeservice">Service</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="uphomeqty2">
                                            <label class="active" for="uphomeqty2">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="uphomepromo">
                                              <option selected disabled>Promos...</option>
                                            </select>
                                            <label for="uphomepromo">Promo</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="uphomeqty3">
                                            <label class="active" for="uphomeqty3">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="uphomeprod">
                                              <option selected disabled>Products...</option>
                                            </select>
                                            <label for="uphomeprod">Product</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="uphomeqty4">
                                            <label class="active" for="uphomeqty4">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>Item Name</th>
                                              <th>Quantity</th>
                                              <th>Price</th>
                                              <th>Action</th>
                                            </tr>
                                          </thead>
                                        </table>
                                      </div>
                                    </div>
                                    <div class="aside asideC z-depth-0">
                                      <div class="row">
                                      <h4>Other Charges</h4>
                                        <div class="col s12" style="margin-top: 10px !important;">
                                          <div class="input-field col s6">
                                            <select class="multiple" id="uphomecname">
                                              <option disabled selected>Charge Name...</option>
                                            </select>
                                            <label for="uphomecname">Charge Name</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" value="1" id="uphomeprice">
                                            <label for="uphomeprice">Price</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-light btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>Charge Name</th>
                                              <th>Price</th>
                                            </tr>
                                          </thead>
                                        </table>
                                        <div class="col s12" style="margin-top: 230px;">
                                          <div class="input-field col s6 offset-s6">
                                            <input type="number" id="uphomereserveamount">
                                            <label for="uphomereserveamount" class="active">Total Amount</label>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  </div>

                                <div class="modal-footer">
                                  <a type="submit" class="modal-trigger waves-effect waves-white btn-flat purple white-text">Save</a>
                                  <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">Cancel</a>
                                </div>
                                </form>
                              </div>

                              <!-- EVENT RESERVATION -->
                              <div id="upeventreservation" class="col s12">
                                <form class="col s12" name="updateEventReservation" method="post" action="">
                                <div class="modal-content">
                                  <div class="wrapper">
                                    <div class="aside asideA z-depth-0">
                                      <div class="row">
                                        <h4>Update Event Reservation</h4>
                                        <div class="input-field col s4">
                                          <select class="multiple" id="upeventgtype">
                                            <option>Guest Type</option>
                                          </select>
                                          <label for="upeventgtype">Guest Type</label>
                                        </div>
                                        <div class="input-field col s8">
                                          <input type="text" id="upeventcompname">
                                          <label for="upeventcompname" class="active">Company Name</label>
                                        </div>
                                        <div class="input-field col s12" style="margin-top: 0px;">
                                          <input type="text" id="upeventcustname" value="">
                                          <label for="upeventcustname" class="active">Name</label>
                                        </div>
                                        <div class="input-field col s12" style="margin-top: 0;">
                                          <input type="number" id="upeventcustnum" value="" min="0">
                                          <label for="upeventcustnum" class="active">Contact Number</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input name="street" type="text" id="upeventstreet">
                                          <label for="upeventstreet" class="active">Street</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="upeventbrgy" value="">
                                          <label for="upeventbrgy" class="active">Barangay</label>
                                        </div>
                                        <div class="input-field col s12">
                                          <input class="active" type="text" id="upeventcity" value="">
                                          <label for="upeventcity" class="active">City</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="upeventdate" value="">
                                          <label for="upeventdate" class="active">Date</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="text" id="upeventtime">
                                          <label for="upeventtime" class="active">Time</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <input type="number" id="upeventhc" min="0">
                                          <label for="upeventhc">Head Count</label>
                                        </div>
                                        <div class="input-field col s6">
                                          <select class="multiple" id="upeventstylist">
                                            <option selected disabled>Stylist..</option>
                                          </select>
                                          <label for="upeventstylist">Stylist</label>
                                        </div>
                                      </div>
                                    </div>
                                    <div class="aside asideB z-depth-0">
                                      <div class="row">
                                        <h4>Inclusion</h4>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="upeventpackage">
                                              <option selected disabled>Packages...</option>
                                            </select>
                                            <label for="upeventpackage">Package</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="upeventqty1">
                                            <label class="active" for="upeventqty1">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="upeventservices">
                                              <option selected disabled>Services...</option>
                                            </select>
                                            <label for="upeventservices">Service</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="upeventqty2">
                                            <label class="active" for="upeventqty2">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="upeventpromo">
                                              <option selected disabled>Promos...</option>
                                            </select>
                                            <label for="upeventpromo">Promo</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="upeventqty3">
                                            <label class="active" for="upeventqty3">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <div class="col s12">
                                          <div class="input-field col s6">
                                            <select class="multiple zeromargintop" id="upeventprod">
                                              <option selected disabled>Products...</option>
                                            </select>
                                            <label for="upeventprod">Product</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" id="upeventqty4">
                                            <label class="active" for="upeventqty4">Quantity</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-white btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>Item Name</th>
                                              <th>Quantity</th>
                                              <th>Price</th>
                                              <th>Action</th>
                                            </tr>
                                          </thead>
                                        </table>
                                      </div>
                                    </div>
                                    <div class="aside asideC z-depth-0">
                                      <div class="row">
                                      <h4>Other Charges</h4>
                                        <div class="col s12" style="margin-top: 10px !important;">
                                          <div class="input-field col s6">
                                            <select class="multiple" id="upchargename">
                                              <option disabled selected>Charge Name...</option>
                                            </select>
                                            <label for="upchargename">Charge Name</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <input type="number" min="0" value="1" id="upeventprice">
                                            <label for="upeventprice">Price</label>
                                          </div>
                                          <div class="input-field col s3">
                                            <button class="waves-effect waves-light btn-flat purple white-text"><i class="material-icons">add</i></button>
                                          </div>
                                        </div>
                                        <table>
                                          <thead>
                                            <tr>
                                              <th>Charge Name</th>
                                              <th>Price</th>
                                            </tr>
                                          </thead>
                                        </table>
                                        <div class="col s12" style="margin-top: 230px;">
                                          <div class="input-field col s6">
                                            <select class="multiple">
                                              <option selected disabled>Payment Type...</option>
                                            </select>
                                          </div>
                                          <div class="input-field col s6">
                                            <input type="number" id="uphomeamount">
                                            <label for="uphomeamount" class="active">Total Amount</label>
                                          </div>
                                        </div>
                                      </div>
                                    </div>
                                  </div>
                                  </div>

                                

                                <div class="modal-footer">
                                  <a type="submit" class="modal-trigger waves-effect waves-white btn-flat purple white-text">Save</a>
                                  <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">Cancel</a>
                                </div>
                                </form>
                              </div>
                            </div>
                            <!-- END -->

                              <!-- EVENT RESERVATIoN END -->
                        
                              <!-- UPDATE ENDD=================================== -->
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
        width: 30px;
        height: 30%;
      }

      .asideB {
        background: transparent;
        border-radius: 1px;
        margin: 10px;
        text-align: center;
        width: 30px;
        height: 30%;
      }

      .asideC {
        background: transparent;
        border-radius: 1px;
        margin: 10px;
        text-align: center;
        width: 30px;
        height: 30%;
      }

      @media all and (min-width: 600px) {
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
        $('ul.tabs').tabs('select_tab', 'homeservice');
      });
    </script>

    <script type="text/javascript">
    $(document).ready(function(){
        $('ul.tabs').tabs('select_tab', 'uphomeservice');
      });
    </script>
    


  </body>



</html>