<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="promoQueryController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Query - Promo</h3>
            <div class="row" style="margin-bottom: -100px !important; margin-top: 50px !important;">
                <div class="col s12">
                    <div class="input-field col s3">
                        <select ng-model="vm.selStatus" id="selectStatus"
                                ng-change="vm.selectStatusInTable()">
                            <option value="" selected>ALL</option>
                            <option ng-repeat="status in vm.status"
                                    value="{{status}}">{{status}}
                            </option>
                        </select>
                        <label for="selectStatus"><b>Status</b></label>
                    </div>
                    <div class="input-field col s5 offset-s4">
                        <nav class="right white hoverable  z-depth-1"
                             style="margin-top: -20px !important;">
                            <div class="nav-wrapper col s12">
                                <form>
                                    <div class="input-field">
                                        <input id="queryExtra" placeholder="Search" class="grey-text text-darken-4"
                                               type="search"
                                               ng-model="vm.queryExtraSearch"
                                               ng-change="vm.searchTable()">
                                        <label for="queryExtra">
                                            <i class="material-icons grey-text text-darken-4">search</i>
                                        </label>
                                    </div>
                                </form>
                            </div>
                        </nav>
                    </div>
                </div>
            </div>
            <div class="col s12">
                <table id="promoQueryTable" datatable="ng" dt-instance="vm.dtInstanceCallback"
                       class="table-barts row-border hoverable cell-border responsive z-depth-1"
                       style="margin-top: -20px !important;">
                    <thead>
                    <tr>
                        <th class="left-align">Name</th>
                        <th class="right-align">Price</th>
                        <th class="right-align">Expiration</th>
                        <th class="center">Status</th>
                    </tr>
                    </thead>
                    <tfoot>
                    <tr style="border: 1px solid #bdbdbd;">
                        <th class="left-align">Name</th>
                        <th class="right-align">Price</th>
                        <th class="right-align">Expiration</th>
                        <th class="center">Status</th>
                    </tr>
                    </tfoot>
                    <tbody>
                    <tr ng-repeat="promo in vm.promoList">
                        <td class="left-align">{{ promo.strPromoName | uppercase }}</td>
                        <td class="right-align">{{ promo.dblPromoPrice | currency: "Php " }}</td>
                        <td class="right-align" ng-if="promo.strPromoAvailability == 'NON-EXPIRY'">NON-EXPIRY</td>
                        <td class="right-align" ng-if="promo.strPromoAvailability != 'NON-EXPIRY'">
                            {{promo.strPromoAvailability}}
                        </td>
                        <td class="center" ng-if="promo.intStatus == 0">INACTIVE</td>
                        <td class="center" ng-if="promo.intStatus == 1">ACTIVE</td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>


</div>