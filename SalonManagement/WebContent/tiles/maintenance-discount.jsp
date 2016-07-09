<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Discount" %>

<body class="purple lighten-5">
<div class="wrapper">
    
    <article>
        <!-- <div class="container"> -->
        <div class="wrapper">
            <!--                     <div class="aside aside1 z-depth-barts">

                                </div> -->

            <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
                <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
                    <h3 class="grey-text text-darken-1">Discount Maintenance</h3>
                    <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text"
                       href="#create" style="margin-top: 50px; margin-left: 15px;" data-delay="30"
                       data-position="bottom" data-tooltip="Create"><i class="material-icons">add</i></a>
                    <a class="tooltipped waves-effect waves-light modal-trigger btn-flat purple darken-3 left white-text"
                       href="#discountArchive" style="margin-top: 50px; margin-left: 15px;" data-delay="30"
                       data-position="bottom" data-tooltip="Archive"><i class="material-icons">archive</i></a>
                    <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%"
                           style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                        <thead>
                        <tr>
                            <th>
                                <center>Discount Name</center>
                            </th>
                            <th>
                                <center>Price (Php/%)</center>
                            </th>
                            <th>
                                <center>Action</center>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${discountList}" var="discount">
                            <%
                            Discount discount1 = (Discount)pageContext.getAttribute("discount");
                            String id = String.valueOf(discount1.getIntDiscountID());
                            String percet = null;
                            String fixed = null;

                            if(discount1.getIntDiscountType() == 1){
                            percet = "%";
                            fixed = "";
                            }
                            else{
                            fixed = "Php ";
                            percet = "";
                            }
                            %>
                            <tr>
                                <td style="padding:0; margin:0;">
                                    <center>${discount.strDiscountName}</center>
                                </td>
                                <td style="padding:0; margin:0;">
                                    <center><%=fixed%>${discount.dblDiscountAmount}<%=percet%></center>
                                </td>
                                <td class="center" style="padding:0; margin:0;">
                                    <center>
                                        <a data-delay="30" data-position="bottom" data-tooltip="View"
                                           class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                                           href="#discountView<%=id%>"
                                           style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                            <i class="material-icons">visibility</i>
                                        </a>
                                        <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent black-text"
                                           title="Update" href="#dis<%=id%>"
                                           style="padding-left: 10px;padding-right:10px; margin: 5px;" data-delay="30"
                                           data-position="bottom" data-tooltip="Update"><i
                                                class="material-icons">edit</i></a>
                                        <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent red-text text-accent-4"
                                           data-delay="30" data-position="bottom" data-tooltip="Deactivate"
                                           href="#del<%=id%>" title="Deactivate"
                                           style="padding-left: 10px;padding-right:10px; margin: 5px;"><i
                                                class="material-icons">delete</i></a></center>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                </div>

                <div id="discountArchive" class="modal modal-fixed-footer"
                     style="width: 80% !important; height: 86% !important; max-height: 100% !important;">
                    <div class="modal-content">
                        <div class="col s12">
                            <h4 class="grey-text text-darken-1">Archive</h4>
                            <table id="discArchTable" class="display centered responsive-table highlight"
                                   cellspacing="0" width="100%" style="border: 1px solid #bdbdbd; padding: 10px;"
                                   rowspan="10">
                                <thead>
                                <tr>
                                    <th>
                                        <center>Discount Name</center>
                                    </th>
                                    <th>
                                        <center>Price (Php/%)</center>
                                    </th>
                                    <th>
                                        <center>Action</center>
                                    </th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${discountList}" var="discount">
                                    <%
                                    Discount discount1 = (Discount)pageContext.getAttribute("discount");
                                    String id = String.valueOf(discount1.getIntDiscountID());
                                    String percet = null;
                                    String fixed = null;

                                    if(discount1.getIntDiscountType() == 1){
                                    percet = "%";
                                    fixed = "";
                                    }
                                    else{
                                    fixed = "Php ";
                                    percet = "";
                                    }
                                    %>
                                    <tr>
                                        <td style="padding:0; margin:0;">
                                            <center>${discount.strDiscountName}</center>
                                        </td>
                                        <td style="padding:0; margin:0;">
                                            <center><%=fixed%>${discount.dblDiscountAmount}<%=percet%></center>
                                        </td>
                                        <td class="center" style="padding:0; margin:0;">
                                            <a class="tooltipped waves-effect waves-light modal-trigger btn-flat transparent green-text text-accent-4"
                                               data-delay="30" data-position="bottom" data-tooltip="Activate"
                                               href="#del<%=id%>"
                                               style="padding-left: 10px;padding-right:10px; margin: 5px;"><i
                                                    class="material-icons">check</i></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <a href="#!" class="modal-action modal-close waves-effect waves-purple btn-flat ">Back</a>
                    </div>
                </div>

                <!-- Modal Structure -->
                <div id="create" class="modal modal-fixed-footer">
                    <form class="col s12" method="get" action="createDiscount" id="createDiscountForm">
                        <div class="modal-content">
                            <!-- <div class="container"> -->
                            <div class="wrapper">
                                <h4 class="grey-text text-darken-1">Create Discount</h4>
                                <div class="row">
                                    <div class="input-field col s12" style="margin-bottom: 30px;">
                                        <label class="red-text"> (*) Indicates required field</label>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: 20px !important;">
                                        <input type="text" name="strDiscountName"
                                               class="validate tooltipped specialname noSpace" id="discountName"
                                               required data-delay="30" data-position="bottom"
                                               data-tooltip="Ex: Student Discount<br/>( At least 5 or more characters )"
                                               pattern="^[a-zA-Z\-'`\s]{5,}$" minlength="5" maxlength="20"
                                               placeholder="Discount Name">
                                        <label for="discountName" class="active">Discount Name<span
                                                class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <textarea id="discountDesc" name="strDiscountDetails"
                                                  class="tooltipped materialize-textarea noSpace" maxlength="30"
                                                  data-delay="30" data-position="bottom"
                                                  data-tooltip="Ex: Valid ID is required<br/>( At least 5 or more characters )"
                                                  minlength="5" placeholder="Description"></textarea>
                                        <label for="discountDesc">Description</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <select class="browser-default" required id="createDiscAmtType"
                                                name="strDiscountType">
                                            <option value="" selected disabled></option>
                                            <option value="1">Percentage</option>
                                            <option value="2">Fixed Amount</option>
                                        </select>
                                        <label for="createDiscAmtType" class="active">Type<span
                                                class="red-text">*</span></label>
                                    </div>
                                    <div class="input-field col s4 offset-s2">
                                        <input type="text"
                                               class="validate right-align tooltipped specialprice noSpace amountFormat"
                                               id="createDiscPrice" required name="dblDiscountPrice" data-delay="30"
                                               data-position="bottom" data-tooltip="Ex: 99.99<br/>( Numbers only )"
                                               placeholder="Discount Amount" pattern="^[0-9]$" maxlength="10">
                                        <label for="createDiscPrice">Amount<span class="red-text">*</span></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="reset" value="Reset"
                                    class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">
                                CANCEL
                            </button>
                            <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                                    value="Submit">CREATE
                            </button>
                        </div>
                    </form>
                </div>

                <!-- VIEW -->
                <c:forEach items="${discountList}" var="discount">
                    <%
                    Discount discount2 = (Discount)pageContext.getAttribute("discount");
                    String id2 = String.valueOf(discount2.getIntDiscountID());

                    %>
                    <div id="discountView<%=id2%>" class="modal modal-fixed-footer"
                         style="width: 45% !important; height: 80% !important; max-height: 100% !important;">
                        <form class="col s12">
                            <div class="modal-content">
                                <!-- <div class="container"> -->
                                <div class="wrapper">
                                    <h4 class="grey-text text-darken-1" style="margin-bottom: 50px;">View Discount</h4>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input type="hidden" name="intDiscountID" value="${discount.intDiscountID}">
                                            <input value="${discount.strDiscountName}" readonly name="strDiscountName"
                                                   type="text" class="tooltipped" id="discountName" data-delay="30"
                                                   data-position="bottom" data-tooltip="${discount.strDiscountName}"
                                                   placeholder="Discount Name">
                                            <label for="discountName">Discount Name</label>
                                        </div>
                                        <div class="input-field col s12">
                                            <textarea id="discountDesc" readonly name="strDiscountDetails"
                                                      class="materialize-textarea tooltipped" length="120"
                                                      data-delay="30" data-position="bottom"
                                                      data-tooltip="${discount.strDiscountDesc}"
                                                      placeholder="Description">${discount.strDiscountDesc}</textarea>
                                            <label for="discountDesc">Description</label>
                                        </div>
                                        <div class="input-field col s6" id="discAmountType">
                                            <select class="browser-default" disabled>
                                                <option value="" selected disabled></option>
                                                <option value="percent">Percentage</option>
                                                <option value="fixed">Fixed Amount</option>
                                            </select>
                                            <label for="discAmountType" class="active">Type</label>
                                        </div>
                                        <div class="input-field col s4 offset-s2">
                                            <input type="text" value="${discount.dblDiscountAmount}" readonly
                                                   class="tooltipped right-align" id="createDiscPrice"
                                                   name="dblDiscountPrice" data-delay="30" data-position="bottom"
                                                   data-tooltip="${discount.dblDiscountAmount}"
                                                   placeholder="Discount Amount">
                                            <label for="createDiscPrice" class="active">Discount Amount</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">
                                    BACK
                                </button>
                            </div>
                        </form>
                    </div>
                </c:forEach>
                <!-- VIEW END -->

                <c:forEach items="${discountList}" var="discount">
                    <%
                    Discount discount2 = (Discount)pageContext.getAttribute("discount");
                    String id2 = String.valueOf(discount2.getIntDiscountID());
                    int type = discount2.getIntDiscountType();
                    String type1 = "";
                    String type2 = "";

                    if(type == 1){
                    type1 = "selected";
                    }
                    else{
                    type1 = "selected";
                    }

                    %>
                    <div id="dis<%=id2%>" class="modal modal-fixed-footer"
                         style="width: 45% !important; height: 80% !important; max-height: 100% !important;">
                        <form class="col s12 updateDiscountForm" method="get" action="updateDiscount"
                              id="updateDiscountForm">
                            <div class="modal-content">
                                <!-- <div class="container"> -->
                                <div class="wrapper">
                                    <h4 class="grey-text text-darken-1" style="margin-bottom: 50px;">Update
                                        Discount</h4>
                                    <div class="row">
                                        <div class="input-field col s12">
                                            <input type="hidden" name="intDiscountID" value="${discount.intDiscountID}">
                                            <input value="${discount.strDiscountName}" name="strDiscountName"
                                                   type="text" class="validate tooltipped specialname noSpace"
                                                   id="discountName" data-delay="30" data-position="bottom"
                                                   data-tooltip="Ex: Student Discount<br/>( At least 5 or more characters )"
                                                   pattern="^[a-zA-Z\-'`\s]{5,}$" minlength="5" maxlength="20"
                                                   placeholder="Discount Name">
                                            <label for="discountName" class="active">Discount Name</label>
                                        </div>
                                        <div class="input-field col s12">
                                            <textarea id="discountDesc" name="strDiscountDetails"
                                                      class="materialize-textarea tooltipped noSpace" maxlength="30"
                                                      data-delay="30" data-position="bottom"
                                                      data-tooltip="Ex: Valid ID is required<br/>( At least 5 or more characters )"
                                                      minlength="5" placeholder="Description">${discount.strDiscountDesc}</textarea>
                                            <label for="discountDesc" class="active">Description</label>
                                        </div>
                                        <div class="input-field col s6">
                                            <select class="browser-default" required id="createDiscAmtType"
                                                    name="strDiscountType">
                                                <option value="" selected disabled></option>
                                                <option value="1"
                                                <%out.print(type1);%>>Percentage</option>
                                                <option value="2"
                                                <%out.print(type2);%>>Fixed Amount</option>
                                            </select>
                                            <label for="updateDiscAmtType" class="active">Type</label>
                                        </div>
                                        <div class="input-field col s4 offset-s2">
                                            <input type="text" value="${discount.dblDiscountAmount}"
                                                   class="validate right-align tooltipped specialprice noSpace amountFormat"
                                                   id="updateDiscPrice" name="dblDiscountPrice" data-delay="30"
                                                   data-position="bottom" data-tooltip="${discount.dblDiscountAmount}"
                                                   placeholder="Discount Amount">
                                            <label for="createDiscPrice">Amount</label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <button type="reset" value="Reset"
                                        class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">
                                    CANCEL
                                </button>
                                <button class="waves-effect waves-light purple darken-3 white-text btn-flat"
                                        type="submit" value="Submit">UPDATE
                                </button>
                            </div>
                        </form>
                    </div>
                </c:forEach>

                <c:forEach items="${discountList}" var="discount">
                    <%
                    Discount dis = (Discount)pageContext.getAttribute("discount");
                    String disID = String.valueOf(dis.getIntDiscountID());
                    %>
                    <div id="del<%=disID%>" class="modal" style="width: 30% !important;">
                        <form action="deactivateDiscount" method="get">
                            <div class="container">
                                <div class="modal-content">
                                    <div class="row">
                                        <h5 class="red-text">Warning!</h5>
                                        <p class="center">Are you sure you want to deactivate <b>${discount.strDiscountName}</b>?
                                        </p>
                                    </div>
                                </div>
                                <div class="col s12 center" style="margin-bottom: 30px;">
                                    <input type="hidden" value="${discount.intDiscountID}" name="intDiscountID">
                                    <button class="waves-effect waves-light orange btn-flat white-text">YES</button>
                                    <a href="#"
                                       class="modal-action modal-close waves-effect waves-light transparent btn-flat black-text">CANCEL</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </c:forEach>
                <!--                     <div class="aside aside2 z-depth-barts">

                                    </div> -->
            </div>
        </div>
        <!-- </div> -->
    </article>


</div>
</body>


</html>