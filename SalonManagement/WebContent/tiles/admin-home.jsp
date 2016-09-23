<div class="wrapper" ng-controller="dashBoardController as vm">
    <div class="main transparent">
        <div class="row">
            <div class="col s4">
                <div class="card purple">
                    <div class="card-content white-text">
                        <span class="card-title">&#8369; Total Sales</span>
                        <p>I am a very simple card. I am good at containing small bits of information.
                            I am convenient because I require little markup to use effectively.</p>
                    </div>
                    <div class="card-action purple darken-3">
                        <a href="#">This is a link</a>
                        <a href="#">This is a link</a>
                    </div>
                </div>
            </div>
            <div class="col s4">
                <div class="card green">
                    <div class="card-content white-text">
                        <span class="card-title"><i
                                class="material-icons center-align">group_add</i>Total Clients</span>
                        <p>I am a very simple card. I am good at containing small bits of information.
                            I am convenient because I require little markup to use effectively.</p>
                    </div>
                    <div class="card-action green darken-3">
                        <a href="#">This is a link</a>
                        <a href="#">This is a link</a>
                    </div>
                </div>
            </div>
            <div class="col s4">
                <div class="card blue-grey darken-2">
                    <div class="card-content white-text">
                        <span class="card-title">{{vm.date}}</span>
                        <p>{{ clock  | date: 'h:mm:ss a'}}</p>
                    </div>
                </div>
            </div>
            <div class="col s12">
                <div></div>
            </div>
        </div>
    </div>

</div>