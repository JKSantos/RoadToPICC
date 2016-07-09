<!DOCTYPE html>
<html ng-app>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>

  <head>
  <%@ taglib uri="/struts-tags" prefix="s" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mtnc-emp.css"/>
  <link rel="stylesheet" type="text/css" href="./css/datatables.min.css"/>
  <link rel="stylesheet" type="text/css" href="./css/datatables.material.min.css"/>
  <link rel="stylesheet" type="text/css" href="./css/material.min.css">

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
                              <li class="purple"><a href="transactions-vip.jsp">VIP</a></li>
                              <li><a href="transactions-productorder.jsp">Product Order</a></li>
                              <li><a href="#!">Walk-In</a></li>
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
                                 <li class="purple"><a href="transactions-vip.jsp">VIP</a></li>
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
                    <div class="aside aside1 z-depth-barts">
                      <header><h2 class="thin">VIP Registration</h2><div class="divider"></div></header>

                      <div class="container">
                          <form class="col s12" action="createEmployee" onsubmit="return passvalidation()" method="post" enctype="multipart/form-data">
                              <div class="row">
                                  <div class="input-field col s12" style="margin-bottom: 40px;">
                                      <label class="red-text">(*) Indicates required field</label>
                                  </div>
                                  <div class="input-field col s4" style="margin-top: 15px;">
                                      <input name="strEmpFirstName" id="fname" type="text" class="validate active" required>
                                      <label for="fname">First Name<span class="red-text">*</span></label>
                                  </div>
                                  <div class="input-field col s4" style="margin-top: 15px;">
                                      <input name="strEmpMiddleName" id="mname" type="text" class="validate">
                                      <label for="mname">Middle Name</label>
                                  </div>
                                  <div class="input-field col s4" style="margin-top: 15px;">
                                      <input name="strEmpLastName" id="lname" type="text" class="validate" required>
                                      <label for="lname">Last Name<span class="red-text">*</span></label>
                                  </div>
                                    <div class="input-field col s6" >
                                      <select name="strEmpGender" required>
                                        <option value="" disabled selected></option>
                                        <option value="M">Male</option>
                                        <option value="F">Female</option>
                                      </select>
                                      <label>Gender <span class="red-text">*</span></label>
                                  </div>
                                  <div class="input-field col s1">
                                    <p style="margin-top: 12px; margin-left: -7px;">(+63)</p>
                                  </div>
                                  <div class="input-field col s5">
                                      <input name="strEmpContactNo" type="text" id="contact" class="validate" maxlength="10">
                                      <label for="contact">Contact Number</label>
                                  </div>
                                  <div class="input-field col s12">
                                      <input name="strEmpAddress" type="text" id="address" class="validate">
                                      <label for="address">Address <span class="red-text">*</span></label>
                                  </div>
                                  <div class="divider"></div>
                                  <div class="input-field col s12">
                                    <h4>VIP Account</h4>
                                  </div>
                                  <div class="input-field col s6 offset-s3">
                                      <input name="strEmpUsername" type="text" class="validate" id="user" maxlength="15">
                                      <label for="user">Username</label>
                                  </div>
                                  <div class="input-field col s6 offset-s3">
                                      <input name="strEmpPassword" type="password" class="validate" id="pass" maxlength="20" onkeyup="checkPass(); return false;">
                                      <label for="pass">Password <span id="pw1"></span></label>
                                  </div>
                                  <div class="input-field col s6 offset-s3">
                                      <input name="strEmpPassword2" type="password" class="validate" id="conf-pass" maxlength="20" onkeyup="checkPass(); return false;">
                                      <label for="conf-pass">Confirm Password <span id="pw2"></span></label>
                                  </div>
                                  <div class="input-field col s6 offset-s3">
                                      <span id="confirmMsg" class="confirmMsg"></span>
                                  </div>

                                  <div class="input-field col s12"> 
                                      <button class="waves-effect waves-white btn-flat purple" title="Create" type="submit" value="Submit" id="savebtn" disabled="disabled" onclick="success()">Create</button>
                                      <button class="waves-effect waves-purple btn-flat white" type="reset" value="Reset" title="Clear">CLEAR</button>
                                  </div>
                              </div>
                          </form>
                      </div>
                    </div>
          </div>
        </article>

        

  </div>

  <!--Import jQuery before materialize.js-->
    <script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="./js/jquery-latest.min.js"></script>
    <script type="text/javascript" src="./js/materialize.min.js"></script>
    <script type="text/javascript" src="./js/angular.min.js"></script>
    <script type="text/javascript" src="./js/package.js"></script>
    <script type="text/javascript" src="./js/datatables.min.js"></script>
    <script type="text/javascript" src="./js/datatables.material.min.js"></script>


    <script type="text/javascript">
      $(document).ready(function() {
          $('#example').DataTable( {
            "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
              columnDefs: [
                  {
                      targets: [ 0, 1, 2 ],
                      className: 'mdl-data-table__cell--non-numeric',

                  },
              ]
          } );
      } );
    </script>

    <script type="text/javascript">
      $(document).ready(function() {
          $('#example').DataTable();
      } );
    </script>



    <script type="text/javascript">
    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
      });
    </script>


    

    <script type="text/javascript">
      $( document ).ready(function(){
        $(".button-collapse").sideNav();
          // Initialize collapsible (uncomment the line below if you use the dropdown variation)
        $('.collapsible').collapsible();
      });
    </script>
    <!-- bday -->

    <!-- Upload image -->

    <script text="text/javascript">
      var loadFile = function(event) {
        var employeeimg = document.getElementById('employeeimg');
        employeeimg.src = URL.createObjectURL(event.target.files[0]);
      };
    </script>

    <script text="text/javascript">
      var loadPic = function(event) {
        var empupdateimg = document.getElementById('empupdateimg');
        empupdateimg.src = URL.createObjectURL(event.target.files[0]);
      };
    </script>

    <script text="text/javascript">
      var loadFile = editFile(event) {
        var output2 = document.getElementById('output2');
        output2.src = URL.createObjectURL(event.target.files[0]);
      };
    </script>

    

    <script>
      $(document).ready(function() {
          $('select').material_select();
        });
      </script>

    <!--- Modal -->

    <script type="text/javascript">
        $(document).ready(function(){
            // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
            $('.modal-trigger').leanModal();
          }); 
    </script>

    <script type="text/javascript">
          function checkPass(){
            var pass1 = document.getElementById('password').value;
            var pass2 = document.getElementById('conf-password').value;
            var good = "#66cc66";
            var bad = "#ff6666";

            if(pass1 == pass2 && pass2 == pass1 && pass1 != null && pass2 != null) {
              document.getElementById('confirmMsg').style.color = good;
              document.getElementById('confirmMsg').innerHTML = "Password Match!";
              document.getElementById('savebtn').disabled = false;
            }else{
              document.getElementById('confirmMsg').style.color = bad;
              document.getElementById('confirmMsg').style.color = bad;
              document.getElementById('confirmMsg').innerHTML = "Password Do Not Match!";
              document.getElementById('savebtn').disabled = true;
            }
          }
    </script>

    <!-- add option -->
    

    <script>
           $(document).ready(function(){
              $("#optionadd").click(function(){
                 var addopt = $("#add-optname").val();
                 if(jQuery.trim(addopt).length > 0){
                      var o = new Option(addopt, addopt, true);
                      /// jquerify the DOM object 'o' so we can use the html method
                      $(o).html(addopt);
                      $("#slct1").append(o);
                 }else{
                  $("<div title='Error!'>Empty</div>").dialog();
                 }
                   
                   });
                  
                });
    </script>
   
   <script type="text/javascript">
      function removeopt(){
        var x = document.getElementById("slct1");
        var y = document.getElementById("slct2");

        x.remove(x.selectedIndex);
        y.remove(y.selectedIndex);
      }
   </script>

   <script type="text/javascript">
   // var month = document.getElementById('month').value;
   // var day = document.getElementById('day').value;
   // var year = document.getElementById('year').value;

   // var curday = new Date();
   // var mm = curday.getMonth()+1;
   // var dd = curday.getDate();
   // var yyyy = curday.getFullYear();

   // var bday = month+'/'+day+'/'+year;
   // var today = mm+'/'+dd+'/'+yyyy;
   
   // if(day<32 && month!='' || day!='' || year!=''){
   //       if(month<=mm && day<=dd && year<=yyyy && day!='' && year>0){
   //             var myage = yyyy - year;
   //             document.getElementById('age').innerHTML = myage;
   //             document.getElementById('age').style.color = "black";
   //       } else if(month>=mm && day>dd && year<yyyy && year>0){
   //         var myage = yyyy-year;
   //         document.getElementById('age').innerHTML = myage-1;
   //         document.getElementById('age').style.color = "black";
   //       } else if(month>=mm && day<dd && year<yyyy && year>0){
   //         var myage = yyyy-year;
   //         document.getElementById('age').innerHTML = myage-1;
   //         document.getElementById('age').style.color = "black";
   //       }else if(year=='' || day=='' || month==''){
   //         document.getElementById('age').innerHTML = " ";
   //       }else if(year>yyyy){
   //         document.getElementById('age').innerHTML = "dpa buhay";
   //         document.getElementById('age').style.color = "red";
   //       }
   //   }
   //  else{
   //       document.getElementById('age').innerHTML = "";
   //  }
   // </script>

   <script type="text/javascript">
         function checkPass(){
           var pass1 = document.getElementById('pass').value;
           var pass2 = document.getElementById('conf-pass').value;
           var good = "#66cc66";
           var bad = "#ff6666";

           if(pass1 == pass2 && pass2 == pass1 && pass1 != "" && pass2 != "") {
             document.getElementById('confirmMsg').style.color = good;
             document.getElementById('confirmMsg').innerHTML = "Password Match!";
             document.getElementById('savebtn').disabled = false;
           }else{
             document.getElementById('confirmMsg').style.color = bad;
             document.getElementById('confirmMsg').style.color = bad;
             document.getElementById('confirmMsg').innerHTML = "Password Do Not Match!";
             document.getElementById('savebtn').disabled = true;
           }
         }
   </script>

  </body>

</html>