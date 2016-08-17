/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2016-08-17 08:55:38 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.tiles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class queries_002dproduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"wrapper\" ng-controller=\"productQueryController as vm\">\r\n");
      out.write("    <div class=\"main z-depth-barts\" style=\"margin-left: 20px; margin-right: 20px;\">\r\n");
      out.write("        <div class=\"col s12\" style=\"margin-left: 20px; margin-right: 20px;\">\r\n");
      out.write("            <h3 class=\"grey-text center text-darken-1\">Query</h3>\r\n");
      out.write("            <!--<a class=\"btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text\"-->\r\n");
      out.write("               <!--href=\"#\" style=\"margin-top: 30px; margin-left: 15px;\"><i class=\"material-icons\">add</i></a>-->\r\n");
      out.write("            <div class=\"row\">\r\n");
      out.write("                <div class=\"input-field col s3\">\r\n");
      out.write("                    <select ng-model=\"vm.selOption\">\r\n");
      out.write("                        <option ng-repeat=\"category in vm.category\" value=\"{{category}}\">{{category}}</option>\r\n");
      out.write("                    </select>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("            <nav class=\"right white hoverable  z-depth-1\" style=\"width: 300px; margin-right: 20px;\">\r\n");
      out.write("                <div class=\"nav-wrapper col s4\">\r\n");
      out.write("                    <form>\r\n");
      out.write("                        <div class=\"input-field\">\r\n");
      out.write("                            <input id=\"querySearch\" placeholder=\"Search\" class=\"grey-text text-darken-4\" type=\"search\"\r\n");
      out.write("                                    ng-model=\"queryProductSearch\">\r\n");
      out.write("                            <label for=\"querySearch\"><i\r\n");
      out.write("                                    class=\"material-icons grey-text text-darken-4\">search</i></label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </nav>\r\n");
      out.write("            <div class=\"col s12\">\r\n");
      out.write("                <table id=\"productQueryTable\" datatable=\"ng\" dt-options=\"vm.dtOptions\" dt-column-defs=\"vm.dtColumnDefs\"\r\n");
      out.write("                   class=\"row-border hoverable cell-border responsive z-depth-1\" rowspan=\"10\"\r\n");
      out.write("                   style=\"margin-top: -20px !important;\">\r\n");
      out.write("                <thead>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th class=\"left-align\">Name</th>\r\n");
      out.write("                    <th class=\"left-align\">Category</th>\r\n");
      out.write("                    <th class=\"right-align\">Detail</th>\r\n");
      out.write("                    <th class=\"left-align\">Price</th>\r\n");
      out.write("                    <th class=\"left-align\">Status</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </thead>\r\n");
      out.write("                <tfoot>\r\n");
      out.write("                <tr style=\"border: 1px solid #bdbdbd;\">\r\n");
      out.write("                    <th class=\"left-align\">Name</th>\r\n");
      out.write("                    <th class=\"left-align\">Category</th>\r\n");
      out.write("                    <th class=\"right-align\">Detail</th>\r\n");
      out.write("                    <th class=\"left-align\">Price</th>\r\n");
      out.write("                    <th class=\"left-align\">Status</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </tfoot>\r\n");
      out.write("                <tbody>\r\n");
      out.write("                <tr ng-repeat=\"product in vm.productList | filter: queryProductSearch\">\r\n");
      out.write("                    <td class=\"left-align\" style=\"width: 300px !important;\"\r\n");
      out.write("                        title=\"{{ product.strProductName }}\">\r\n");
      out.write("                        {{ product.strProductName }}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"left-align\" style=\"width: 300px !important;\">\r\n");
      out.write("                        {{ product.strProductCategory }},\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"left-align\">{{ product.strProductDesc }}</td>\r\n");
      out.write("                    <td class=\"right-align\">{{ product.dblProductPrice | currency: \"Php \" }}</td>\r\n");
      out.write("                    <td class=\"left-align\" ng-if=\"product.intProductStatus == 0\">INACTIVE</td>\r\n");
      out.write("                    <td class=\"left-align\" ng-if=\"product.intProductStatus == 1\">ACTIVE</td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </tbody>\r\n");
      out.write("            </table>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</div>");
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
