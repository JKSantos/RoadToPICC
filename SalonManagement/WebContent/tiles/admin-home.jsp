
<div class="wrapper">

    <article>


        <div class="col s12 center" style="margin-top: 200px;">
            <h3>${ sessionScope.firstName } ${ sessionScope.lastName }, your is ${ sessionScope.id }</h3>
        </div>

        <div id="draggable" class="ui-widget-content fixed-action-btn horizontal transparent"
             style="border:0px !important; bottom: 45px; right: 24px; z-index: 100000px;">
            <a class="btn-floating btn-large red">
                <i class="large material-icons">mode_edit</i>
            </a>
            <ul>
                <li><a class="btn-floating red"><i class="material-icons">insert_chart</i></a></li>
                <li><a class="btn-floating yellow darken-1"><i class="material-icons">format_quote</i></a></li>
                <li><a class="btn-floating green"><i class="material-icons">publish</i></a></li>
                <li><a class="btn-floating blue"><i class="material-icons">attach_file</i></a></li>
            </ul>
        </div>

    </article>


</div>
