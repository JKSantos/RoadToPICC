<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Location"%>
<!-- <div class="container"> -->
<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Utilities</h3>
            
			<div class="col s6">
                   <ul class="collapsible" data-collapsible="accordion">
                     <li>
                         <div class="collapsible-header active" id="listheadcollapsible">Configurations<i
                                 class="material-icons">settings</i>
                         </div>
                         <div class="collapsible-body" id="listcollapsible"
                              style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                          	
	                          <form method="post" action="updateDependencies">
	                             <div class="row">
			                        <div class="input-field col s6"  style="margin-top: 25px;">
			                        	<input type="hidden" name="strName" value="downPayment"/>
			                            <input type="text" class="validate" id="downPayment"
			                                   name="strValue" placeholder="" value="${ dependencies.get(0).getStrValue() }">
			                            <label for="downPayment" class="active"><b>Down Payment(Percentage)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        <div class="input-field col s6" style="margin-top: 25px;">
			                        	<input type="hidden" name="strName" value="headCount"/>
			                            <input type="text" class="validate" id="headCount"
			                                   name="strValue" placeholder="3" value="${ dependencies.get(1).getStrValue() }">
			                            <label for="headCount" class="active"><b>Head Count per Employee</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="maxHeadCount"/>
			                            <input id="maxHeadCount" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(2).getStrValue() }">
			                            <label for="maxHeadCount" class="active"><b>Maximum Head Count(Reservation)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="reservationMargin"/>
			                            <input id="reservationMargin" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(3).getStrValue() }">
			                            <label for="reservationMargin" class="active"><b>Reservation Margin(Days)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="reservationAdjustment"/>
			                            <input id="reservationAdjustment" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(4).getStrValue() }">
			                            <label for="reservationAdjustment" class="active"><b>Price Adjustment(Reservation)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="homeAdjustment"/>
			                            <input id="homeAdjustment" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(5).getStrValue() }">
			                            <label for="homeAdjustment" class="active"><b>Price Adjustment(Home Service)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="appointmentGracePeriod"/>
			                            <input id="appointmentGracePeriod" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(6).getStrValue() }">
			                            <label for="appointmentGracePeriod" class="active"><b>Appointment Grace Period(Minutes)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="AppointmentMargin"/>
			                            <input id="AppointmentMargin" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(7).getStrValue() }">
			                            <label for="AppointmentMargin" class="active"><b>Appointment Margin(Hours)</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="minimumPurchase"/>
			                            <input id="minimumPurchase" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(8).getStrValue() }">
			                            <label for="minimumPurchase" class="active"><b>Minimum Purchase Price For Delivery</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="service_fee"/>
			                            <input id="refund" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(9).getStrValue() }">
			                            <label for="refund" class="active"><b>Service Fee</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6">
			                        	<input type="hidden" name="strName" value="processing_fee"/>
			                            <input id="refund" name="strValue"
			                                   class="validate" placeholder="5" value="${ dependencies.get(10).getStrValue() }">
			                            <label for="refund" class="active"><b>Processing Fee</b><i
			                                    class="material-icons red-text tiny">error_outline</i></label>
			                        </div>
			                        
			                        <div class="input-field col s6 right">
			                            <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
					                        value="Submit">SAVE
					                		</button>
			                        </div>
			                    </div>
	                         </form>   
                         </div>
                     </li>
                     <!--<li>-->
                         <!--<div class="collapsible-header" id="listheadcollapsible">Company Profile<i-->
                                 <!--class="material-icons">profile</i>-->
                         <!--</div>-->
                         <!--<div class="collapsible-body" id="listcollapsible"-->
                              <!--style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">-->
                             <!--BODY-->
                         <!--</div>-->
                     <!--</li>-->
                      <li>
                         <div class="collapsible-header" id="listheadcollapsible">Appearance<i
                                 class="material-icons">settings</i>
                         </div>
                         <div class="collapsible-body" id="listcollapsible"
                              style="margin:0px 0px 0px 0px !important; padding: 0px 0px 0px 0px !important;">
                             <a href="download.action?fineName=/Reports/ProductTag_Sample_155719233892016.pdf" >Click Me</a><br>
                             <a href="MyFirstHighCharts.jsp" >High Charts</a>
                         </div>
                     </li>
             
               </div>
            
        </div>
    </div>



</div>