/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.70
 * Generated at: 2016-08-17 11:06:16 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.tiles;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class transactions_002dpayment_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<div class=\"wrapper\" ng-controller=\"paymentCtrl as vm\">\r\n");
      out.write("    <div class=\"main z-depth-barts\" style=\"margin-left: 20px; margin-right: 20px;\">\r\n");
      out.write("        <div class=\"col s12\" style=\"margin-left: 20px; margin-right: 20px;\">\r\n");
      out.write("            <h3 class=\"grey-text center text-darken-1\">Payment</h3>\r\n");
      out.write("            <!--<a class=\"btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text\"-->\r\n");
      out.write("               <!--href=\"#\" style=\"margin-top: 30px; margin-left: 15px;\"><i class=\"material-icons\">add</i></a>-->\r\n");
      out.write("            <nav class=\"right white hoverable  z-depth-1\" style=\"width: 300px; margin-right: 20px;\">\r\n");
      out.write("                <div class=\"nav-wrapper col s4\">\r\n");
      out.write("                    <form>\r\n");
      out.write("                        <div class=\"input-field\">\r\n");
      out.write("                            <input id=\"paymentSearch\" placeholder=\"Search\" class=\"grey-text text-darken-4\" type=\"search\">\r\n");
      out.write("                            <label for=\"paymentSearch\"><i\r\n");
      out.write("                                    class=\"material-icons grey-text text-darken-4\">search</i></label>\r\n");
      out.write("                        </div>\r\n");
      out.write("                    </form>\r\n");
      out.write("                </div>\r\n");
      out.write("            </nav>\r\n");
      out.write("            <table id=\"paymentTable\" datatable=\"ng\" dt-options=\"vm.dtOptions\" dt-column-defs=\"vm.dtColumnDefs\"\r\n");
      out.write("                   class=\"row-border hoverable cell-border z-depth-1\" rowspan=\"10\"\r\n");
      out.write("                   style=\"margin-top: -20px !important;\">\r\n");
      out.write("                <thead>\r\n");
      out.write("                <tr>\r\n");
      out.write("                    <th class=\"left-align\">Customer Name</th>\r\n");
      out.write("                    <th class=\"left-align\">Transaction Name</th>\r\n");
      out.write("                    <th class=\"right-align\">Transaction Date</th>\r\n");
      out.write("                    <th class=\"left-align\">Transaction Type</th>\r\n");
      out.write("                    <th class=\"right-align\">Total Balance</th>\r\n");
      out.write("                    <th class=\"right-align\">Remaining Balance</th>\r\n");
      out.write("                    <th class=\"center-align\">Action</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </thead>\r\n");
      out.write("                <tfoot>\r\n");
      out.write("                <tr style=\"border: 1px solid #bdbdbd;\">\r\n");
      out.write("                    <th class=\"left-align\">Customer Name</th>\r\n");
      out.write("                    <th class=\"left-align\">Transaction Name</th>\r\n");
      out.write("                    <th class=\"right-align\">Transaction Date</th>\r\n");
      out.write("                    <th class=\"left-align\">Transaction Type</th>\r\n");
      out.write("                    <th class=\"right-align\">Total Balance</th>\r\n");
      out.write("                    <th class=\"right-align\">Remaining Balance</th>\r\n");
      out.write("                    <th class=\"center-align\">Action</th>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </tfoot>\r\n");
      out.write("                <tbody>\r\n");
      out.write("                <tr ng-repeat=\"payment in vm.paymentList\"\r\n");
      out.write("                    ng-if=\"payment.strStatus != 'COMPLETE'\">\r\n");
      out.write("                    <td ng-if=\"payment.strName\" class=\"left-align\">{{ payment.strName }}</td>\r\n");
      out.write("                    <td ng-if=\"payment.customer\" class=\"left-align\">{{ payment.customer.strName }}</td>\r\n");
      out.write("                    <td ng-if=\"payment.strName\" class=\"left-align\">Product Order</td>\r\n");
      out.write("                    <td ng-if=\"payment.customer\" class=\"left-align\">Reservation</td>\r\n");
      out.write("                    <td ng-if=\"payment.strName\" class=\"right-align\">{{ payment.datCreated }}</td>\r\n");
      out.write("                    <td ng-if=\"payment.customer\" class=\"right-align\">{{ payment.datCreated }}</td>\r\n");
      out.write("                    <td ng-if=\"payment.strName\" class=\"left-align\">\r\n");
      out.write("                        <span ng-if=\"payment.intType==1\">DELIVERY</span>\r\n");
      out.write("                        <span ng-if=\"payment.intType==2\">PICK UP</span>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td ng-if=\"payment.customer\" class=\"left-align\">\r\n");
      out.write("                        <span ng-if=\"reserve.intReservationType==1\">HOME SERVICE</span>\r\n");
      out.write("                        <span ng-if=\"reserve.intReservationType==2\">EVENT</span>\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td ng-if=\"payment.strName\" class=\"right-align\">\r\n");
      out.write("                        {{ payment.invoice.dblTotalPrice | currency: \"Php \" }}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td ng-if=\"payment.customer\" class=\"right-align\">\r\n");
      out.write("                        {{ payment.invoice.dblTotalPrice | currency: \"Php \" }}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td ng-if=\"payment.strName\" class=\"right-align\">\r\n");
      out.write("                        {{ payment.invoice.dblRemainingBalance | currency: \"Php \" }}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td ng-if=\"payment.customer\" class=\"right-align\">\r\n");
      out.write("                        {{ payment.invoice.dblRemainingBalance | currency: \"Php \" }}\r\n");
      out.write("                    </td>\r\n");
      out.write("                    <td class=\"center-align\">\r\n");
      out.write("                        <button class=\"waves-effect waves-purple btn-flat transparent black-text\"\r\n");
      out.write("                                style=\"padding-left: 10px;padding-right:10px; margin: 5px;\" title=\"Payment\"\r\n");
      out.write("                                ng-click=\"vm.createPOPayment(payment, $index, vm.type[0].option1)\"\r\n");
      out.write("                                ng-if=\"payment.strName\">\r\n");
      out.write("                            <i class='material-icons medium'>payment</i>\r\n");
      out.write("                        </button>\r\n");
      out.write("                        <button class=\"waves-effect waves-purple btn-flat transparent black-text\"\r\n");
      out.write("                                style=\"padding-left: 10px;padding-right:10px; margin: 5px;\" title=\"Payment\"\r\n");
      out.write("                                ng-click=\"vm.createPOPayment(payment, $index, vm.type[0].option3)\"\r\n");
      out.write("                                ng-if=\"payment.customer\">\r\n");
      out.write("                            <i class='material-icons medium'>payment</i>\r\n");
      out.write("                        </button>\r\n");
      out.write("                    </td>\r\n");
      out.write("                </tr>\r\n");
      out.write("                </tbody>\r\n");
      out.write("            </table>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div id=\"paymentModal\" class=\"modal modal-fixed-footer\">\r\n");
      out.write("        <div class=\"modal-content\">\r\n");
      out.write("            <h4 class=\"grey-text center text-darken-1\"\r\n");
      out.write("                ng-if=\"vm.paymentDetails.type == 'order'\">\r\n");
      out.write("                Payment for {{vm.paymentDetails.strName}}\r\n");
      out.write("            </h4>\r\n");
      out.write("            <h4 class=\"grey-text center text-darken-1\"\r\n");
      out.write("                ng-if=\"vm.paymentDetails.type == 'reservation'\">\r\n");
      out.write("                Payment for {{vm.paymentDetails.customer.strName}}\r\n");
      out.write("            </h4>\r\n");
      out.write("            <div class=\"container\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                    <div class=\"input-field col s6\">\r\n");
      out.write("                        <input type=\"hidden\"\r\n");
      out.write("                            ng-model=\"vm.paymentDetails.index\"\r\n");
      out.write("                            ng-if=\"vm.paymentDetails.type == 'order'\"/>\r\n");
      out.write("                        <input type=\"hidden\"\r\n");
      out.write("                            ng-model=\"vm.paymentDetails.index\"\r\n");
      out.write("                            ng-if=\"vm.paymentDetails.type == 'reservation'\"/>\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.name\" placeholder=\"Customer Name\" readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.strName\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'order'\">\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.name\" placeholder=\"Customer Name\" readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.customer.strName\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'reservation'\">\r\n");
      out.write("                        <label for=\"paymentDetails.name\" class=\"active\"><b>Customer Name</b></label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field col s6\">\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.date\" placeholder=\"Payment Date\" readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.paymentCreated\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'order'\"/>\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.date\" placeholder=\"Payment Date\" readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.paymentCreated\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'reservation'\"/>\r\n");
      out.write("                        <label for=\"paymentDetails.date\" class=\"active\"><b>Payment Date</b></label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field col s6\">\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.totalBalance\" class=\"right-align\" placeholder=\"Total Balance\"readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.totalBalance\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'order'\"/>\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.totalBalance\" class=\"right-align\" placeholder=\"Total Balance\"readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.totalBalance\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'reservation'\"/>\r\n");
      out.write("                        <label for=\"paymentDetails.totalBalance\" class=\"active\"><b>Total Balance</b></label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field col s6\">\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.remainingBalance\" class=\"right-align\" placeholder=\"Total Balance\" readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.remainingBalance\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'order'\"/>\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.remainingBalance\" class=\"right-align\" placeholder=\"Total Balance\" readonly\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.remainingBalance\"\r\n");
      out.write("                               ng-if=\"vm.paymentDetails.type == 'reservation'\"/>\r\n");
      out.write("                        <label for=\"paymentDetails.remainingBalance\" class=\"active\"><b>Remaining Balance</b></label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field col s6\">\r\n");
      out.write("                        <select id=\"paymentDetails.type\"\r\n");
      out.write("                                ng-model=\"vm.paymentDetails.paymentType\"\r\n");
      out.write("                                ng-if=\"vm.paymentDetails.type == 'order'\"\r\n");
      out.write("                                ng-options=\"pay.name for pay in vm.paymentType track by pay.id\">\r\n");
      out.write("                        </select>\r\n");
      out.write("                        <select id=\"paymentDetails.type\"\r\n");
      out.write("                                ng-model=\"vm.paymentDetails.paymentType\"\r\n");
      out.write("                                ng-if=\"vm.paymentDetails.type == 'reservation'\"\r\n");
      out.write("                                ng-options=\"pay.name for pay in vm.paymentType track by pay.id\">\r\n");
      out.write("                        </select>\r\n");
      out.write("                        <label><b>Payment Type</b>\r\n");
      out.write("                            <i class=\"material-icons tiny red-text\">error_outline</i>\r\n");
      out.write("                            </label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    <div class=\"input-field col s6\">\r\n");
      out.write("                        <input type=\"text\" id=\"paymentDetails.paymentAmount\" class=\"right-align\" placeholder=\"Payment Amount\" required\r\n");
      out.write("                               ng-model=\"vm.paymentDetails.paymentAmount\"\r\n");
      out.write("                               format=\"number\"/>\r\n");
      out.write("                        <label for=\"paymentDetails.paymentAmount\" class=\"active\"><b>Payment Amount</b>\r\n");
      out.write("                            <i class=\"material-icons tiny red-text\">error_outline</i>\r\n");
      out.write("                        </label>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <div class=\"modal-footer\">\r\n");
      out.write("            <button class=\"red-text btn-flat transparent left\" disabled\r\n");
      out.write("                    style=\"margin:0px !important; padding:0px !important;\"><i\r\n");
      out.write("                    class=\"material-icons\">error_outline</i>&nbspRequired field\r\n");
      out.write("            </button>\r\n");
      out.write("            <button type=\"submit\" value=\"Submit\" id=\"paymentDetails.submit\"\r\n");
      out.write("                    class=\"waves-effect waves-light white-text btn-flat purple\"\r\n");
      out.write("                    style=\"margin-left:3px; margin-right:3px;\"\r\n");
      out.write("                    ng-click=\"vm.paymentSubmit(vm.paymentDetails)\">CREATE\r\n");
      out.write("            </button>\r\n");
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
