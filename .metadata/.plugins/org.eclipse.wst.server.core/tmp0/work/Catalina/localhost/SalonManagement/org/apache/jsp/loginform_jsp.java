/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2016-08-07 20:05:10 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class loginform_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("  <head>\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"./css/materialize.min.css\"  media=\"screen,projection\"/>\r\n");
      out.write("  <link type=\"text/css\" rel=\"stylesheet\" href=\"./css/materialize.css\"/>\r\n");
      out.write("  <link type=\"text/css\" rel=\"stylesheet\" href=\"./css/mystyle.css\"/>\r\n");
      out.write("    <!--Let browser know website is optimized for mobile-->\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"/>\r\n");
      out.write("  </head>\r\n");
      out.write("\r\n");
      out.write("  <body class=\" purple darken-4\">\r\n");
      out.write("  <!-- Start Page Loading -->\r\n");
      out.write("  <div id=\"loader-wrapper\">\r\n");
      out.write("      <div id=\"loader\"></div>\r\n");
      out.write("      <div class=\"loader-section section-left\"></div>\r\n");
      out.write("      <div class=\"loader-section section-right\"></div>\r\n");
      out.write("  </div>\r\n");
      out.write("  <!-- End Page Loading -->\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <div id=\"login-page\" class=\"row\">\r\n");
      out.write("      <div class=\"col s12 z-depth-4 card-panel\">\r\n");
      out.write("          <form class=\"login-form\" method=\"post\" action=login>\r\n");
      out.write("              <div class=\"row\">\r\n");
      out.write("                  <div class=\"input-field col s12 center\">\r\n");
      out.write("                      <img src=\"./img/logo.png\" style=\"height: 180px;\" alt=\"\" class=\"circle responsive-img valign profile-image-login\">\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"row margin\">\r\n");
      out.write("                  <div class=\"input-field col s12\">\r\n");
      out.write("                      <i class=\"mdi-social-person-outline prefix\"></i>\r\n");
      out.write("                      <input id=\"username\" type=\"text\" name=\"username\" class=\"validate\">\r\n");
      out.write("                      <label for=\"username\" class=\"center-align\">Username</label>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"row margin\">\r\n");
      out.write("                  <div class=\"input-field col s12\">\r\n");
      out.write("                      <i class=\"mdi-action-lock-outline prefix\"></i>\r\n");
      out.write("                      <input id=\"password\" type=\"password\" name=\"pass\" class=\"validate\">\r\n");
      out.write("                      <label for=\"password\">Password</label>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"row\">\r\n");
      out.write("                  <div class=\"input-field col s12 m12 l12  login-text\">\r\n");
      out.write("                      <input type=\"checkbox\" id=\"remember-me\" />\r\n");
      out.write("                      <label for=\"remember-me\">Remember me</label>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"row\">\r\n");
      out.write("                  <div class=\"input-field col s12\">\r\n");
      out.write("                      <button type=\"submit\" value=\"Submit\" class=\"btn waves-effect waves-light purple lighten-1 col s12\">Login</button>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("              <div class=\"row\">\r\n");
      out.write("                  <div class=\"input-field col s6 m6 l6\">\r\n");
      out.write("                      <p class=\"margin medium-small\"><a href=\"page-register.html\">Register Now!</a></p>\r\n");
      out.write("                  </div>\r\n");
      out.write("                  <div class=\"input-field col s6 m6 l6\">\r\n");
      out.write("                      <p class=\"margin right-align medium-small\"><a href=\"page-forgot-password.html\">Forgot password ?</a></p>\r\n");
      out.write("                  </div>\r\n");
      out.write("              </div>\r\n");
      out.write("\r\n");
      out.write("          </form>\r\n");
      out.write("      </div>\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<style type=\"text/css\">\\\r\n");
      out.write("html,\r\n");
      out.write("body {\r\n");
      out.write("    height: 100%;\r\n");
      out.write("}\r\n");
      out.write("html {\r\n");
      out.write("    display: table;\r\n");
      out.write("    margin: auto;\r\n");
      out.write("}\r\n");
      out.write("body {\r\n");
      out.write("    display: table-cell;\r\n");
      out.write("    vertical-align: middle;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    .wrapper {\r\n");
      out.write("      display: -webkit-box;\r\n");
      out.write("      display: -moz-box;\r\n");
      out.write("      display: -ms-flexbox;\r\n");
      out.write("      display: -webkit-flex;\r\n");
      out.write("      display: flex;\r\n");
      out.write("      flex-direction: row;\r\n");
      out.write("      \r\n");
      out.write("      -webkit-flex-flow: row wrap;\r\n");
      out.write("      flex-flow: row wrap;\r\n");
      out.write("      -moz-flex-flow: row wrap;\r\n");
      out.write("      -o-flex-flow: row wrap;\r\n");
      out.write("    }\r\n");
      out.write("    .wrapper > * {\r\n");
      out.write("      flex: 1 100%;\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    .aside-1{\r\n");
      out.write("      background: white;\r\n");
      out.write("      border-radius: 3px;\r\n");
      out.write("      margin: 10px;\r\n");
      out.write("      text-align: center;\r\n");
      out.write("      width: 50px;\r\n");
      out.write("      height: 30%;\r\n");
      out.write("    }\r\n");
      out.write("    .aside-2{\r\n");
      out.write("      background: white;\r\n");
      out.write("      border-radius: 3px;\r\n");
      out.write("      margin: 10px;\r\n");
      out.write("      text-align: center;\r\n");
      out.write("      width: 50px;\r\n");
      out.write("      height: 30%;\r\n");
      out.write("    }\r\n");
      out.write("    @media all and (min-width: 600px) {\r\n");
      out.write("      /* We tell both sidebars to share a row */\r\n");
      out.write("      .aside { flex: 1 auto; }\r\n");
      out.write("    }\r\n");
      out.write("\r\n");
      out.write("    /* Large screens */\r\n");
      out.write("    @media all and (min-width: 800px) {\r\n");
      out.write("       /*We invert order of first sidebar and main\r\n");
      out.write("       * And tell the main element to take twice as much width as the other two sidebars \r\n");
      out.write("       */\r\n");
      out.write("      .main { flex: 2 0px; }\r\n");
      out.write("      \r\n");
      out.write("      .aside-1 { order: 1; }\r\n");
      out.write("      .main    { order: 2; }\r\n");
      out.write("      .aside-2 { order: 3; }\r\n");
      out.write("      .footer  { order: 4; }\r\n");
      out.write("    }\r\n");
      out.write("</style>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <!--Import jQuery before materialize.js-->\r\n");
      out.write("<script type=\"text/javascript\" src=\"./js/jquery-2.1.1.min.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" src=\"./js/materialize.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write(" \r\n");
      out.write("\r\n");
      out.write("  </body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
