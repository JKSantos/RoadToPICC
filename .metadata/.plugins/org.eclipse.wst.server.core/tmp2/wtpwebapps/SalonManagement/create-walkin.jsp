<!DOCTYPE html>
<html>
  <head>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/trans-reservation.css"/><!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>

  <body class="blue-grey lighten-5">
  <div class="wrapper">
        <header class="headnav">
                        <ul id="slide-out" class="side-nav fixed z-depth-0">
                          <div class="center">
                             <img src="./img/logo.png" class="circle" style="width: 100%; height: 100%; margin-top: 40px; margin-bottom: 20px;"></div>
                          <li><a href="admin-home.jsp" class="waves-effect"><b>Home</b></a></li>
                         <!--  <li class="no-padding"> -->
                            <ul class="collapsible" data-collapsible="accordion">
                              <li>
                                <a class="collapsible-header"><b>Maintenance</b></a>
                                  <div class="collapsible-body">
                                    <ul>
                                      <li><a href="employeeMaintenance.action">Employee</a></li>
                                      <li><a href="productServiceMaintenance.action">Product & Service</a></li>
                                      <li><a href="maintenance-promo.jsp">Promo</a></li>
                                      <li><a href="discountMaintenance.action">Discount</a></li>
                                      <li><a href="maintenance-package.jsp">Package</a></li>
                                      <li><a href="maintenance-catalogue.jsp">Catalogue</a></li>
                                      <li><a href="extraChargeMaintenance.action">Extra Charge</a></li>
                                    </ul>
                                  </div>
                              </li>
                              <li>
                                <a class="collapsible-header"><b>Transaction</b></a>
                                  <div class="collapsible-body">
                                    <ul>
                                      <li><a href="transactions-inventory.jsp">Inventory</a></li>
                                      <li><a href="transactions-reservation.jsp">Reservation</a></li>
                                      <li><a href="transactions-productorder.jsp">Product Order</a></li>
                                      <li class="purple"><a href="transactions-walkin.jsp">Walk-In</a></li>
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
                                   <a class="waves-effect waves-purple collapsible-header"><b>Maintenance</b></a>
                                     <div class="collapsible-body">
                                       <ul>
                                         <li><a href="tiles/maintenance-emp.jsp">Employee</a></li>
                                         <li><a href="maintenance-prodsvc.jsp">Product & Service</a></li>
                                         <li><a href="maintenance-promo.jsp">Promo</a></li>
                                         <li><a href="maintenance-discount.jsp">Discount</a></li>
                                         <li><a href="maintenance-package.jsp">Package</a></li>
                                         <li><a href="maintenance-catalouge.jsp">Catalogue</a></li>
                                         <li><a href="maintenance-extra.jsp">Extra Charge</a></li>
                                       </ul>
                                     </div>
                                 </li>
                                 <li>
                                   <a class="waves-effect waves-purple collapsible-header"><b>Transactions</b></a>
                                     <div class="collapsible-body">
                                       <ul>
                                         <ul>
                                         <li><a href="transactions-inventory.jsp">Monitor Product</a></li>
                                         <li ><a href="transactions-reservation.jsp">Manage Reservation</a></li>
                                         <li><a href="transactions-productorder.jsp">Product Order</a></li>
                                         <li><a href="transactions-vip.jsp">VIP</a></li>
                                         <li class="purple lighten-4"><a href="transactions-walkin.jsp">Walk In</a></li>
                                       </ul>
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
                    <div class="aside aside1 z-depth-barts">
                     <div class="container">
                                            <form class="col s12" id="myForm">

                                              <div class="row center">
                                                <div class="input-field col s12">
                                                  <h2 class="thin">Walk in</h2>
                                                </div>
                                              </div>

                                              <div class="row">
                                                  <p class="red-text col s6">(*) Includes required field</p>
                                                  <p id="notify" class="red-text col s6"></p>
                                              </div>
                                            <div class="row">
                                              <div class="input-field col s6">
                                                 <input type="number" class="validate" required id="reserve_headcount" maxlength="4" onkeyup="notifyStylist()">
                                                 <label for="reserve_headcount">Head Count <span class="red-text">*</span></label>
                                              </div>

                                              
                                               <div class="input-field col s6">
                                                 <select id="stylistlist" multiple>
                                                       <option value="" disabled selected>Choose your option</option>
                                                       <option value="">stylist1</option>
                                                       <option value="">stylist2</option>
                                                       <option value="">stylist3</option>
                                                       <option value="">stylist4</option>
                                                       <option value="">stylist5</option>
                                                       <option value="">stylist6</option>
                                                 </select>
                                                 <label>Stylist Available</label>
                                              </div>
                                            </div>


                                             <div class="row">
                                              <div class="col s6">
                                                <div class="row">
                                                  <h6>Packages</h6>
                                                  <div class="input-field col s6">
                                                   <select id="package-list"  class="browser-default">
                                                         <option value="" disabled selected>Choose your option</option>
                                                         <option value="Package 1">Package 1</option>
                                                         <option value="Package 2">Package 2</option>
                                                         <option value="Package 3">Package 3</option>
                                                         <option value="customize">Custom</option>
                                                   </select>
                                                   <!-- <label>Package Available</label> -->
                                                  </div>

                                                  <div class="input-field col s6">
                                                     <input type="number" class="validate" id="package_quantity">
                                                     <label for="package_quantity">Quantity</label>
                                                  </div>

                                                  <div class="col s12 center">
                                                     <button class="waves-effect waves-light btn col s4 center" onclick="addPackageToTable()"><i class="material-icons">add</i></button>
                                                  </div>
                                                </div>
                                                
                                                <div class="row">
                                                    <h6>Services</h6>
                                                    <div class="input-field col s6">
                                                     <select id="package-list"  class="browser-default">
                                                           <option value="" disabled selected>Choose your option</option>
                                                           <option value="Package 1">Package 1</option>
                                                           <option value="Package 2">Package 2</option>
                                                           <option value="Package 3">Package 3</option>
                                                           <option value="customize">Custom</option>
                                                     </select>
                                                     <!-- <label>Package Available</label> -->
                                                    </div>

                                                    <div class="input-field col s6">
                                                       <input type="number" class="validate" id="package_quantity">
                                                       <label for="package_quantity">Quantity</label>
                                                    </div>

                                                    <div class="col s12 center">
                                                       <button class="waves-effect waves-light btn col s4 center" onclick="addPackageToTable()"><i class="material-icons">add</i></button>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                  <h6>Extra Charges</h6>
                                                  <div class="input-field col s6">
                                                   <select id="charge-list"  class="browser-default">
                                                         <option value="" disabled selected>Choose your option</option>
                                                         <option value="Charge 1">Charge 1</option>
                                                         <option value="Charge 2">Charge 2</option>
                                                         <option value="Charge 3">Charge 3</option>
                                                         <option value="customize">Charge</option>
                                                   </select>
                                                   <!-- <label>Package Available</label> -->
                                                  </div>

                                                  <div class="input-field col s6">
                                                     <input type="number" class="validate" id="charge_price">
                                                     <label for="charge_price">Price</label>
                                                  </div>

                                                  <div class="col s12 center">
                                                     <button class="waves-effect waves-light btn col s4 center" onclick="addChargesToTable()"><i class="material-icons">add</i></button>
                                                  </div>
                                                </div>
                                              </div>

                                              <div class="col s6">
                                                <div class="row center">
                                                  <table class="centered highlight">
                                                    <thead>
                                                          <tr>
                                                              <th data-field="id">Name</th>
                                                              <th data-field="name">Amount</th>
                                                              <th data-field="price">Action</th>
                                                          </tr>
                                                        </thead>
                                                  </table>
                                                   <table class="centered highlight bordered" id="packageTable">

                                                   </table>
                                                 </div>
                                              </div>  
                                             </div>                  
                                           
                                                 <div id="div1">
                                                   
                                                 </div>
                                          
                                            <div class="row col s12 center">
                                               <a href="transactions-reservation.jsp" class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL</a>
                                                 <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit" value="Submit">CREATE</button>   
                                            </div>
                                                                
                                              </form>
                                            </div>

          </div>
        </article>
  </div>
                

  <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./js/materialize.min.js"></script>
    <script type="text/javascript" src="./js/addToTable.js"></script>

    <script type="text/javascript">
      $(document).ready(function() {
        $('select').material_select();
      });
    </script>

    <script type="text/javascript">
      $( document ).ready(function(){

        $(".button-collapse").sideNav();
            
        
      });
    </script>

    <script type="text/javascript">
    $('.datepicker').pickadate({
        selectMonths: true, // Creates a dropdown to control month
        selectYears: 15 // Creates a dropdown of 15 years to control year
      });
    </script>

    <script type="text/javascript">
        function enableService(datetime){
            var dtmstart = document.getElementById('datetimestart');
            var dtmend = document.getElementById('datetimeend');

            if(datetime.options[datetime.selectedIndex].value=='home'){
              dtmstart.disabled = false;
              dtmend.disabled = true;
              dtmend.value = '';
              dtmstart.value = '';
            }else if(datetime.options[datetime.selectedIndex].value=='event'){
              dtmstart.disabled = false;
              dtmend.disabled = false;
              dtmstart.value = '';
              dtmend.value = '';
            }
            return;
          }
    </script>

    

    <script type="text/javascript">
        $(function(){
          $('#package-list').change(function(){
            if ($(this).val()=='customize'){
              $('#servicelist').prop('disabled', false);
            }else{
              $('#servicelist').prop('disabled', true);
            }
          });
        });
    </script>
   
    <script type="text/javascript">
        $(document).ready(function(){
            // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
            $('.modal-trigger').leanModal();
          }); 
    </script>

    <script type="text/javascript">
        function enableVIP(selectguest){
          guest = document.getElementById('reserve_customername');

          if(selectguest.options[selectguest.selectedIndex].value=='guest'){
            guest.disabled = false;
          }else{
            guest.disabled = true;
            guest.value = '';
          }
          return;
        }
    </script>

<!-- reservation schedule viewer -->
  </body>

</html>