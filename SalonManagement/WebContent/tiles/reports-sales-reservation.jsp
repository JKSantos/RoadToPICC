<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="reservationReportController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Reports</h3>
              
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
        <div class="row">
        	<div class="col s12"><a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#reservationListModal" ng-click = "vm.getData()"><i
                    class="material-icons">list</i></a></div>
        	<div class="col s6">
	          <div class="card darken-1">
	            <div class="card-content black-text">
	             <h2 class="thin purple-text text-darken-2">Products Summary:</h2>
	             <h4 class="light">Total product loss: <span class="thin">30%</span></h4>
	             <h4 class="light">Total product defect: <span class="thin">70%</span></h4>
	             <h4 class="light">Total product expired: <span class="thin">30%</span></h4>
	            </div>
	           
	          </div>
	        </div>
	        <div class="col s6">
	        	<div class="col s12">
	        		<div class="card darken-1">
			            <div class="card-content black-text">
			   				 <div class="input-field col s6">
                                    <input input-date
                                           type="text"
                                           id="ngDateFrom"
                                           placeholder="January/1/2016"
                                           ng-model="vm.details.datFrom"
                                           months-full="{{ vm.month }}"
                                           disable="disable"
                                           min=""
                                           max=""
                                           today="today"
                                           clear="clear"
                                           close="close"
                                           select-years="15"
                                           ng-change="vm.changeDatFrom(vm.details.datFrom)"/>
                                    <label for="ngDateFrom" class="active"><b>Date From</b><i
                                            class="material-icons red-text tiny">error_outline</i></label>
                                </div>
                                <div class="input-field col s6">
                                    <input input-date
                                           type="text"
                                           placeholder="January/1/2016"
                                           id="ngDateTo"
                                           ng-model="vm.details.datTo"
                                           months-full="{{ vm.month }}"
                                           months-short="{{ vm.monthShort }}"
                                           weekdays-full="{{ vm.weekdaysFull }}"
                                           weekdays-short="{{ vm.weekdaysShort }}"
                                           weekdays-letter="{{ vm.weekdaysLetter }}"
                                           disable="disable"
                                           min=""
                                           max=""
                                           today="today"
                                           clear="clear"
                                           close="close"
                                           select-years="15"
                                           ng-change="vm.changeDatTo(vm.details.datTo)"/>
                                    <label for="ngDateTo" class="active">
                                        <b>Date To</b>
                                        <i class="material-icons red-text tiny">
                                            error_outline</i>
                                    </label>
                                </div>   
                                
                                <div class="col s12">
                                	<a class="z-depth-1 hoverable waves-effect waves-light btn purple darken-2 white-text"
				               ng-click="vm.sendFilters()" ><i
				                    class="material-icons">search</i></a>
                                </div> 
                                
                                
		                     </div>
		                     
		                     <div class="card-action">
				           
				            </div>
			          </div>
	          </div>
	      
	        	
	        	<div class="col s12">
	        		<div class="card darken-1">
			            <div class="card-content black-text">
			   				<h5>asdasd</h5>
			            </div>
		            <div class="card-action">
		              <a href="#">ACTIONS</a>
		              <a href="#">PRINT</a>
		            </div>
		          </div>
		       </div>
	           </div>
	        </div>
        </div>
            
	      <div class="row">
	        <div class="col s12">
	          <div class="card darken-1">
	            <div class="card-content white-text">
	             <div id="containerReservation" style="min-width: 99%; height: 99%; max-width: 100%; margin: 0 auto"></div>
	            </div>
	            <div class="card-action">
	              <a href="#">ACTIONS</a>
	              <a href="#">PRINT</a>
	            </div>
	          </div>
	        </div>
	      </div>
            
            <div class="col s12">
                
            </div>
            
            <div id="reservationListModal" class="modal"style = "width: 70% !important; height: 80% !important; border-radius: 10px">
        <div class="modal-content">
         	<a href="#!" class=" modal-action modal-close waves-effect waves-purple btn-flat right"><i
                    class="material-icons red-text" style="font-size: 30px ">highlight_off</i></a>
            <h3 class="purple-text text-darken-3 thin">Reservation List</h3>
            <table id=""
                   class="hoverable z-depth-1 cell-border row-border display responsive-table highlight"
                 
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="left-align">Customer Name</th>
                    <th class="left-align">Customer Type</th>
                    <th class="left-align">Date Created</th>
                    <th align="center-align">Amount</th>
                </tr>
                </thead>
                <tbody>
                <tr ng-repeat="customer in vm.customerList">
                    <td class="left-align">{{customer.strName}}</td>
                    <td class="left-align">{{customer.intReservationType }}</td>
                    <td class="right-align">{{customer.dtmDateTimeCreaetd | date: "MMMM/d/yyyy" }}</td>
                    <td class="left-align">{{customer.dblInvoiceAmount}}</td>
                    <td align="center-align">
                        <button class="waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Cancel"
                                ng-if="customer.intReservationType == 1"
                                ng-click="cancelHomeService(customer, $index)">
                            <i class='material-icons'>exit</i>
                        </button>
                        <button class="waves-effect waves-purple btn-flat transparent grey-text text-darken-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;" title="Accept"
                                ng-if="customer.intReservationType == 1"
                                ng-click="acceptHomeService(customer, $index)">
                            <i class='material-icons'>done</i>
                        </button>
                        <button class="btn-flat transparent red-text text-lighten-4"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                ng-if="customer.intReservationType == 2"
                                ng-disabled=true>
                            <i class='material-icons'>exit</i>
                        </button>
                        <button class="btn-flat transparent grey-text text-lighten-3"
                                style="padding-left: 10px;padding-right:10px; margin: 5px;"
                                ng-if="customer.intReservationType == 2"
                                ng-disabled=true>
                            <i class='material-icons'>done</i>
                        </button>
                    </td>
                </tr>
                </tbody>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="left-align">Customer Name</th>
                    <th class="left-align">Customer Type</th>
                    <th class="left-align">Date Created</th>
                    <th align="center-align">Amount</th>
                </tr>
                </tfoot>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
        </div>
    </div>



</div>