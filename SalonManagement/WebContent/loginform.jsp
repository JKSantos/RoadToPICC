<!DOCTYPE html>
<html>
  <head>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
    <!--Let browser know website is optimized for mobile-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>

  <body style="background: url(./img/login.jpg) no-repeat; background-size: 100%; " class="responsive-img">
    
        <header>  
          <div class="navbar-fixed">
            <nav class="white z-depth-barts">
              <div class="container">
                  <div class="nav-wrapper">
                    <a href="#" class="brand-logo grey-text">Generic Salon System</a>
                    <ul class="right hide-on-med-and-down">
                    </ul>
                  </div>
              </div>
            </nav>
          </div>
        </header>
  <div class="container">
    <div class="wrapper">
            <aside class="aside aside-1 z-depth-barts">
                <div class="row">
                        <form class="col s12" action="login">
                            <div class="row">
                                <div class="center">
                                  <h3>Login</h3>
                                </div>
                                <div class="input-field col s8 offset-s2">
                                  <input type="text" name="username" id="username" class="validate">
                                  <label for="id">Username</label>
                                </div>
                                <div class="input-field col s8 offset-s2">
                                  <input type="password" name="pass" id="pass" class="validate">
                                  <label for="pass">Password</label>
                                </div>
                                <div class="col s12" style="margin-top: 50px;">
                                  <button class="waves-effect waves-light orange btn-flat" type="submit" value="Submit" id="submitbtn">Login</button>
                                  <a href="#" class="waves-effect waves-orange btn-flat" onclick="loginform.jsp">Back</a>
                                </div>
                            </div>
                        </form>
                </div>
            </aside>
            <!-- <main class="main">
            </main> -->
            <aside class="aside aside-2 z-depth-barts">
              <div class="row">
                <div class="col s12" style="margin-top: 50px;">
                    <h1>Welcome</h1>
                    <p>MEHEHEHE</p>
                </div>
                <div class="col s12" style="margin-top: 50px; margin-bottom: 60px;">
                    <a href="#" class="waves-effect waves-light orange btn">Continue as Guest</a>
                </div>
              </div>
            </aside>

      </div>
    </div>








<style type="text/css">\
    body, html, footer {
      margin: 0;
      padding: 0;
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

  <footer class="footer page-footer white z-depth-barts">
        <div class="container">
            <div class="row">
              <div class="col l6 s12">
              <h5 class="orange-text text-darken-3">Follow us!</h5>
              <p class="orange-text text-darken-3">Like, share, learn!</p>
              </div>
              <div class="col l4 offset-l2 s12">
              <h5>Links</h5>
              <ul>
                <li><a class="orange-text text-darken-3" href="#!">www.facebook.com/GenericSalonSystem</a></li>
                <li><a class="orange-text text-darken-3" href="#!">www.twitter.com/@GenericSalonSystem</a></li>
                <li><a class="orange-text text-darken-3" href="#!">www.GenericSalonSystem.com</a></li>
                <li><a class="orange-text text-darken-3" href="#!">www.instagram.com/@GenericSalonSystem</a></li>
              </ul>
              </div>
            </div>
            </div>
            <div class="footer-copyright">
            <div class="container">
            <span class="orange-text text-darken-3">(c) 2015 Copyright</span>
            <a class="orange-text text-darken-3 right" href="#!">More Links</a>
            </div>
          </div>
      </div>
    </footer>


</html>