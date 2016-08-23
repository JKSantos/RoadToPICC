<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="salesReportController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Sales Report</h3>
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
               <!--href="#" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>-->
            <div class="row" style="margin-top: 40px !important;">
                <div class="col s3">
                    <input type="date" class="datepicker" id="filterDateFrom" ng-model="filter.DateFrom" placeholder="Date From"/>
                    <label for="filterDateFrom" class="active"><b>Date From</b></label>
                </div>
                <div class="col s3">
                    <input type="date" class="datepicker" id="filterDateTo" ng-model="filter.DateTo" placeholder="Date To"/>
                    <label for="filterDateTo" class="active"><b>Date To</b></label>
                </div>
            </div>        
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="salesReportSearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                    ng-model="queryEmployeeSearch">
                            <label for="salesReportSearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <div class="col s12" style="margin-top: -10px !important;">
              
            </div>

        </div>
    </div>



</div>