<!DOCTYPE html>
<html>
  <head>
  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/trans-prodorder.css"/>
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
                                <a class="collapsible-header active"><b>Transaction</b></a>
                                  <div class="collapsible-body">
                                    <ul>
                                      <li><a href="transactions-inventory.jsp">Inventory</a></li>
                                      <li><a href="transactions-reservation.jsp">Reservation</a></li>
                                      <li><a href="transactions-vip.jsp">VIP</a></li>
                                      <li class="purple"><a href="transactions-productorder.jsp">Product Sales</a></li>
                                      <li><a href="transactions-walkin.jsp">Walk-In</a></li>
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
                                         <li><a href="maintenance-catalogue.jsp">Catalogue</a></li>
                                         <li><a href="maintenance-extra.jsp">Extra Charge</a></li>
                                       </ul>
                                     </div>
                                 </li>
                                 <li>
                                   <a class="waves-effect waves-purple collapsible-header"><b>Transaction</b></a>
                                     <div class="collapsible-body">
                                       <ul>
                                         <li><a href="transactions-inventory.jsp">Inventory</a></li>
                                         <li><a href="transactions-reservation.jsp">Reservation</a></li>
                                         <li class="purple"><a href="transactions-productorder.jsp">Product Order</a></li>
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
            
                    <div class="aside aside1 z-depth-barts"> <!--main aside1-->
                          <header><h2 class="thin" style="margin-top: 30px;">Product Sales</h2></header>
                          <div class="container">
                          <div class="row">
                              <form class="col s12" style="margin-top: 20px;">
                                  <div class="input-field col s6">
                                      <select id="ordertype" onchange="enableAddress(this);">
                                            <option value="" disabled selected>Choose Order Type</option>
                                            <option value="delivery">Delivery</option>
                                            <option value="pickup">Pick-up</option>
                                          </select>
                                          <label>Order Type</label>
                                  </div>
                                  
                                  <div class="input-field col s12">
                                  		<input type="text" class="validate" id="order_name">
                                  		<label for="order_name">Name</label>
                                  </div>
                                  
                                  <div class="input-field col s12">
                                      <input type="text" class="validate" id="order_address">
                                      <label for="order_address">Address</label>
                                  </div>
                                  <div class="input-field col s8">
                                      <select id="productlist" class="icons" onchange="enableQty(this);">
                                            <option value="" disabled selected>Choose Product</option>
                                            <c:forEach items="${productList}" var="product">
                                                          <option value="${product.intProductID}">${product.strProductName}</option>
                                             </c:forEach>
                                                            </select>
                                            
                                      <label>Product List</label>
                                  </div>
                                  <div class="input-field col s4">
                                    <input type="text" class="validate right-align" id="order_prodquantity" maxlength="5" disabled="disabled">
                                    <label for="order_prodquantity">Quantity</label>
                                  </div>
                                  <div class="input-field col s4 offset-s8">
                                      <input type="text" class="validate right-align" id="order_prodprice" maxlength="8" disabled="disabled">
                                      <label for="order_prodprice">Total Price</label>
                                  </div>
                                  <div class="input-field col s12">
                                      <button id="orderbtn" type="submit" value="Submit" class="waves-effect waves-light purple btn-flat">Order</button>
                                      <button id="orderclear" type="reset" value="Reset" class="waves-effect waves-purple white btn-flat">CLEAR</button>
                                  </div>
                              </form>
                          </div>
                          </div>
                      </div>
        </article>

        

  </div>
                



    

  <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./js/materialize.min.js"></script>

    <script type="text/javascript">
      $( document ).ready(function(){

        $(".button-collapse").sideNav();
            
        
      });
    </script>
    <script type="text/javascript">
    $(document).ready(function() {
        $('select').material_select();
      });
    </script>

    <script type="text/javascript">
        function enableAddress(selectorder){
          var order_ad = document.getElementById('order_address');

          if(selectorder.options[selectorder.selectedIndex].value=='delivery'){
            order_ad.disabled = false;
          }
          else{
            order_ad.disabled = true;
            order_ad.value = '';
          }
          return;
        }
    </script>
   
   <script type="text/javascript">
      function enableQty(selProd){
        var order_prod = document.getElementById('order_prodquantity');
        var order_price = document.getElementById('order_prodprice');

        if(selProd.options[selProd.selectedIndex].value!=''){
          order_prod.disabled = false;
          order_price.disabled = false;
        }else{
          order_prod.disabled = true;
          order_price.disabled = true;
          order_prod.value = '';
          order_price.value = '';

        }
        return;
      }
   </script>
 

  </body>

</html>