<div class="wrapper" ng-controller="dashBoardController as vm">
    <div class="main transparent">
        <div class="row">
            <div class="col s4">
                <div class="card purple">
                    <div class="card-content white-text">
                        <span class="card-title">&#8369; Total Sales</span>
                        <div ng-if="vm.week == false">
                            <span class="card-title"><b>{{vm.totalSalesThisWeek | currency: "Php "}}</b></span><br> for
                            this <b>WEEK</b>.
                        </div>
                        <div ng-if="vm.week == true">
                            <span class="card-title"><b>{{vm.totalSalesThisMonth | currency: "Php "}}</b></span><br> for
                            this <b>MONTH</b>.
                        </div>
                    </div>
                    <div class="card-action purple darken-3">
                        <div class="switch">
                            <label>
                                <b>WEEK</b>
                                <input type="checkbox" ng-model="vm.week">
                                <span class="lever"></span>
                                <b>MONTH</b>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col s4">
                <div class="card green">
                    <div class="card-content white-text">
                        <span class="card-title">
                            <i class="material-icons center-align">group_add</i>Total Clients</span>
                        <div ng-if="vm.client == false">
                            <span class="card-title"><b>{{vm.totalClientsThisWeek | number: 0}}</b></span><br> for
                            this <b>WEEK</b>.
                        </div>
                        <div ng-if="vm.client == true">
                            <span class="card-title"><b>{{vm.totalClientsThisMonth | number: 0}}</b></span><br> for
                            this <b>MONTH</b>.
                        </div>
                    </div>
                    <div class="card-action green darken-3">
                        <div class="switch">
                            <label>
                                <b>WEEK</b>
                                <input type="checkbox" ng-model="vm.client">
                                <span class="lever"></span>
                                <b>MONTH</b>
                            </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col s4">
                <div class="card blue-grey darken-2">
                    <div class="card-content white-text">
                        <span class="card-title">{{vm.date}}</span>
                        <h3>{{ clock | date: 'h:mm:ss a'}}</h3>
                    </div>
                </div>
            </div>
            <div class="col s12">
                <div>
                    <div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
                </div>
            </div>
        </div>
    </div>

</div>