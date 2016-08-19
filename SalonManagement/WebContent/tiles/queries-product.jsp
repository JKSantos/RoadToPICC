<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="productQueryController as vm">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Query</h3>
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
               <!--href="#" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">add</i></a>-->
            <div class="row">
                <div class="input-field col s3">
                    <select ng-model="vm.selOption">
                        <option value selected>ALL</option>
                        <option ng-repeat="category in vm.category" value="{{category}}">{{category | uppercase}}</option>
                    </select>
                </div>
                <div class="input-field col s3">
                    <select ng-model="vm.selStatus">
                        <option ng-repeat="status in vm.status" value="{{status}}">{{status}}</option>
                    </select>
                </div>
            </div>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="querySearch" placeholder="Search" class="grey-text text-darken-4" type="search"
                                    ng-model="queryProductSearch">
                            <label for="querySearch"><i
                                    class="material-icons grey-text text-darken-4">search</i></label>
                        </div>
                    </form>
                </div>
            </nav>
            <div class="col s12">
                <table id="productQueryTable"
                   class="table-barts row-border hoverable cell-border responsive z-depth-1">
                <thead>
                <tr>
                    <th class="left-align">Name</th>
                    <th class="left-align">Category</th>
                    <th class="right-align">Detail</th>
                    <th class="left-align">Price</th>
                    <th class="left-align">Status</th>
                </tr>
                </thead>
                <tfoot>
                <tr style="border: 1px solid #bdbdbd;">
                    <th class="left-align">Name</th>
                    <th class="left-align">Category</th>
                    <th class="right-align">Detail</th>
                    <th class="left-align">Price</th>
                    <th class="left-align">Status</th>
                </tr>
                </tfoot>
                <tbody>
                <tr ng-repeat="product in vm.productList |
                               filter: queryProductSearch |
                               filter: vm.selOption">
                    <td class="left-align" style="width: 300px !important;"
                        title="{{ product.strProductName }}">
                        {{ product.strProductName | uppercase }}
                    </td>
                    <td class="left-align" style="width: 300px !important;">
                        <div class="col s12">
                            {{ product.strProductCategory | uppercase }}
                        </div>
                    </td>
                    <td class="left-align">{{ product.strProductDesc }}</td>
                    <td class="right-align">{{ product.dblProductPrice | currency: "Php " }}</td>
                    <td class="left-align" ng-if="product.intProductStatus == 0">INACTIVE</td>
                    <td class="left-align" ng-if="product.intProductStatus == 1">ACTIVE</td>
                </tr>
                </tbody>
            </table>
            </div>

        </div>
    </div>


</div>