<!DOCTYPE html>
<html>
  <head>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>

  <body class=" purple darken-4">
  <!-- Start Page Loading -->
  <div id="loader-wrapper">
      <div id="loader"></div>
      <div class="loader-section section-left"></div>
      <div class="loader-section section-right"></div>
  </div>
  <!-- End Page Loading -->



  <div id="login-page" class="row">
      <div class="col s12 z-depth-4 card-panel">
          <form class="login-form" action=login>
              <div class="row">
                  <div class="input-field col s12 center">
                      <img src="./img/logo.png" style="height: 180px;" alt="" class="circle responsive-img valign profile-image-login">
                  </div>
              </div>
              <div class="row margin">
                  <div class="input-field col s12">
                      <i class="mdi-social-person-outline prefix"></i>
                      <input id="username" type="text" name="username" class="validate">
                      <label for="username" class="center-align">Username</label>
                  </div>
              </div>
              <div class="row margin">
                  <div class="input-field col s12">
                      <i class="mdi-action-lock-outline prefix"></i>
                      <input id="password" type="password" name="pass" class="validate">
                      <label for="password">Password</label>
                  </div>
              </div>
              <div class="row">
                  <div class="input-field col s12 m12 l12  login-text">
                      <input type="checkbox" id="remember-me" />
                      <label for="remember-me">Remember me</label>
                  </div>
              </div>
              <div class="row">
                  <div class="input-field col s12">
                      <button type="submit" value="Submit" class="btn waves-effect waves-light purple lighten-1 col s12">Login</button>
                  </div>
              </div>
              <div class="row">
                  <div class="input-field col s6 m6 l6">
                      <p class="margin medium-small"><a href="page-register.html">Register Now!</a></p>
                  </div>
                  <div class="input-field col s6 m6 l6">
                      <p class="margin right-align medium-small"><a href="page-forgot-password.html">Forgot password ?</a></p>
                  </div>
              </div>

          </form>
      </div>
  </div>








<style type="text/css">\
html,
body {
    height: 100%;
}
html {
    display: table;
    margin: auto;
}
body {
    display: table-cell;
    vertical-align: middle;
}


    .wrapper {
      display: -webkit-box;
      display: -moz-box;
      display: -ms-flexbox;
      display: -webkit-flex;
      display: flex;
      flex-direction: row;
      
      -webkit-flex-flow: row wrap;
      flex-flow: row wrap;
      -moz-flex-flow: row wrap;
      -o-flex-flow: row wrap;
    }
    .wrapper > * {
      flex: 1 100%;
    }

    .aside-1{
      background: white;
      border-radius: 3px;
      margin: 10px;
      text-align: center;
      width: 50px;
      height: 30%;
    }
    .aside-2{
      background: white;
      border-radius: 3px;
      margin: 10px;
      text-align: center;
      width: 50px;
      height: 30%;
    }
    @media all and (min-width: 600px) {
      /* We tell both sidebars to share a row */
      .aside { flex: 1 auto; }
    }

    /* Large screens */
    @media all and (min-width: 800px) {
       /*We invert order of first sidebar and main
       * And tell the main element to take twice as much width as the other two sidebars 
       */
      .main { flex: 2 0px; }
      
      .aside-1 { order: 1; }
      .main    { order: 2; }
      .aside-2 { order: 3; }
      .footer  { order: 4; }
    }
</style>


  <!--Import jQuery before materialize.js-->
<script type="text/javascript" src="./js/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="./js/materialize.min.js"></script>


 

  </body>


</html>