<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.gss.model.Employee" %>
<%@ page import="com.gss.model.EmployeeCategory" %>
<%@ page import="com.gss.model.Job" %>
<%@ page import="java.util.List" %>
<%!

String month = null;
String gender = null;
String position = null;
String granAccess = null;
%>


<!-- <div class="container"> -->
<div class="wrapper">
    <!--                     <div class="aside aside1 z-depth-barts">

                        </div> -->

    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text text-darken-1">Employee Maintenance</h3>
            <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped"
               href="#createEmployeeModal" style="margin-top: 50px; margin-left: 15px;" data-delay="30"
               data-position="bottom"
               data-tooltip="Create"><i class="material-icons">add</i></a>
            <a class="waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text tooltipped"
               href="#empArchive" style="margin-top: 50px; margin-left: 15px;" data-delay="30" data-position="bottom"
               data-tooltip="Archive"><i class="material-icons">archive</i></a>
            <table id="example" class="display centered responsive-table highlight" cellspacing="0" width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                <thead>
                <tr>
                    <th>
                        <center>Name</center>
                    </th>
                    <th>
                        <center>Position</center>
                    </th>
                    <th>
                        <center>Date Employed</center>
                    </th>
                    <th>
                        <center>Action</center>
                    </th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${empList}" var="employee">
                    <%! String str=null; %>
                    <% Employee emp = (Employee)pageContext.getAttribute("employee");
                    str=String.valueOf(emp.getIntEmpID());
                    String de = str;
                    %>
                    <tr>
                        <td style="padding:0; margin:0;">
                            <center><img name="empupdatedImg" id="empupdatedImg" class="circle left"
                                         style="width: 30px; height: 30px; margin-left: 15px !important;"
                                         src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                         alt="${employee.strEmpFirstName}"/>${employee.strEmpFirstName}
                                ${employee.strEmpLastName}
                            </center>
                        </td>
                        <td style="padding:0; margin:0;">
                            <center>NONE</center>
                        </td>
                        <td style="padding:0; margin:0;">
                            <center>03/17/16</center>
                        </td>
                        <td class="center" style="padding:0; margin:0;">
                            <a data-delay="30" data-position="bottom" data-tooltip="View"
                               class="tooltipped waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                               href="#view<%=str%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a data-delay="30" data-position="bottom" data-tooltip="Update"
                               class="tooltipped waves-effect waves-purple modal-trigger btn-flat transparent black-text"
                               href="#emp<%=str%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <a data-delay="30" data-position="bottom" data-tooltip="Deactivate"
                               class="tooltipped waves-effect waves-purple modal-trigger btn-flat transparent red-text text-accent-4"
                               href="#de<%=de%>" style="padding-left: 10px;padding-right:10px; margin: 5px;"
                               title="Deactivate"><i class="material-icons">delete</i></a>
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>

        </div>

        <!-- ARCHIVE BEGIN -->
        <div id="empArchive" class="modal modal-fixed-footer"
             style="width: 80% !important; height: 86% !important; max-height: 100% !important;">
            <div class="modal-content">
                <div class="col s12">
                    <h4 class="grey-text text-darken-1">Archive</h4>
                    <table id="empArchiveTable" class="display centered responsive-table highlight" cellspacing="0"
                           width="100%" style="border: 1px solid #bdbdbd; padding: 10px;" rowspan="10">
                        <thead>
                        <tr>
                            <th>
                                <center>Name</center>
                            </th>
                            <th>
                                <center>Position</center>
                            </th>
                            <th>
                                <center>Date Employed</center>
                            </th>
                            <th>
                                <center>Actions</center>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${empList}" var="employee">
                            <%! String str1=null; %>
                            <% Employee emp = (Employee)pageContext.getAttribute("employee");
                            str=String.valueOf(emp.getIntEmpID());
                            String de = str1;
                            %>
                            <tr>
                                <td style="padding:0; margin:0;">
                                    <center><img name="empupdatedImg" id="empupdatedImg" class="circle left"
                                                 style="width: 30px; height: 30px; margin-left: 15px !important;"
                                                 src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                                 alt="${employee.strEmpFirstName}"/>${employee.strEmpFirstName}
                                        ${employee.strEmpLastName}
                                    </center>
                                </td>
                                <td style="padding:0; margin:0;">
                                    <center>NONE</center>
                                </td>
                                <td style="padding:0; margin:0;">
                                    <center>03/17/16</center>
                                </td>
                                <td class="center" style="padding:0; margin:0;">
                                    <!-- ACTIVATE -->
                                    <a data-delay="30" data-position="bottom" data-tooltip="Activate"
                                       class="tooltipped waves-effect waves-purple modal-trigger btn-flat transparent green-text text-accent-4"
                                       href="#de<%=de%>" style="padding-left: 10px;padding-right:10px; margin: 5px;"><i
                                            class="material-icons">check</i></a>
                                    <!-- ACTIVATE END -->
                                </td>
                            </tr>

                        </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <a href="#!" class=" modal-action modal-close waves-effect waves-purple btn-flat">BACK</a>
            </div>
        </div>
        <!-- ARCHIVE END -->

        <!-- Modal Structure -->
        <div id="createEmployeeModal" class="modal modal-fixed-footer">
            <form id="createEmpForm" class="col s12 form" method="post" action="createEmployee"
                  enctype="multipart/form-data">
                <div class="modal-content">
                    <div class="wrapper">
                        <h4 class="center grey-text text-darken-1">Create Employee<a id="btnCreateExit"
                                                                                     class="modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        <div class="progress">
                            <div class="determinate active purple darken-4 white-text" role="progressbar"
                                 aria-valuemin="0"
                                 aria-valuemax="100" style="color: white;"></div>
                        </div>
                        <div class="errorcontainer input-field col s12 red darken-4 white-text z-depth-barts">

                        </div>
                        <div class="step well">
                            <div class="wrapper">
                                <div class="aside aside1 z-depth-0">
                                    <!-- first -->
                                    <div class="row">
                                        <!--<div class="input-field col s12">-->
                                        <!--<img name="image" id="employeeimg" class="circle"-->
                                        <!--style="width: 200px; height: 200px;" src="./img/anon.jpg" alt=""/>-->
                                        <!--</div>-->
                                        <div class="input-field col s12">
                                            <div class="file-field">
                                                <input name="upload" class="dropify z-depth-3" type="file"
                                                       data-allowed-file-extensions="png jpg jpeg"
                                                       data-default-file="./img/anon.jpg" data-height="250"
                                                       data-file-max-size="3M" data-width="200"
                                                       data-show-errors="true"/>
                                                <!--<div class="file-path-wrapper">-->
                                                <!--<input name="path" value="image" class="file-path validate"-->
                                                <!--type="text">-->
                                                <!--</div>-->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="aside aside2 z-depth-0">
                                    <div class="row form-group">
                                        <div class="input-field col s12">
                                            <input name="strEmpFirstName" id="strCrEmpFname" type="text"
                                                   class="validate" placeholder="Ex: Juan" required/>
                                            <label for="strCrEmpFname" class="active" id="label1"><b>First
                                                Name</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input name="strEmpMiddleName" id="strCrEmpMname" type="text"
                                                   class="validate" placeholder="Ex: Dela"/>
                                            <label for="strCrEmpMname" class="active"><b>Middle Name</b></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input name="strEmpLastName" id="strCrEmpLname" type="text"
                                                   class="validate" placeholder="Ex: Cruz"
                                                   required/>
                                            <label for="strCrEmpLname" class="active"><b>Last Name</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input type="date" name="strBirthdate" class="datepicker"
                                                   id="createBirthday" placeholder="Ex: 01/01/01"
                                                   required/>
                                            <label for="createBirthday"><b>Birthday</b><i
                                                    class="material-icons red-text tiny">error_outline</i></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input style="border-bottom-color: dimgrey;" type="text" name="createAge"
                                                   id="createAge" placeholder="Ex: 18" required disabled/>
                                            <label for="createAge" id="createAgeLabel"
                                                   class="active" style="color: #424242 !important;"><b>Age</b></label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="step well" style="margin-top: 15px;">
                            <div class="container">
                                <div class="row form-group">
                                    <div class="input-field col s12">
                                        <select name="strEmpGender" id="createGender" required class="required">
                                            <option value="0" disabled selected>Choose...</option>
                                            <option value="M">Male</option>
                                            <option value="F">Female</option>
                                        </select>
                                        <label for="createGender" class="active"
                                               style="margin-top: 20px !important;"><b>Gender</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input type="text" name="strEmpContactNo" id="createContact"
                                               placeholder="Ex: 09123456789"/>
                                        <label for="createContact"><b>Phone Number</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input type="email" name="strEmpEmail" id="createEmail"
                                               placeholder="Ex: salon@yahoo.com"/>
                                        <label for="createEmail" class="active"><b>Email</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input type="text" name="strEmpAddress" id="createAddress"
                                               placeholder="Ex: #20 Julian Eymard St. Sto.Nino Meycauayan, Bulacan"
                                               required/>
                                        <label for="createAddress" class="active"><b>Address</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s8">
                                        <select name="selectedJob" id="crSelectedJob" multiple="multiple" required
                                                class="required">
                                            <option value="default" disabled selected>Choose...</option>
                                            <c:forEach items="${empCategory}" var="name">
                                                <option value="${name.strCategoryName}">${name.strCategoryName }
                                                </option>
                                            </c:forEach>
                                        </select>
                                        <label for="crSelectedJob" class="active"
                                               style="margin-top: 25px !important;"><b>Position</b><i
                                                class="material-icons red-text tiny">error_outline</i></label>
                                    </div>
                                    <div class="input-field col s4">
                                        <button data-target="createAddOption"
                                                class="waves-effect waves-light btn-flat modal-option purple darken-3 white-text">
                                            <i class="material-icons">add</i></button>
                                    </div>
                                    <div class="input-field col s12" style="margin-top: -5px !important;">
                                        <input type="checkbox" name="chkGrantAccess" id="chkGrantAccess"
                                               class="filled-in" style="color: purple;"/>
                                        <label for="chkGrantAccess"><b>Grant Access</b></label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="red-text btn-flat transparent left" disabled
                            style="margin:0px !important; padding:0px !important;"><i
                            class="material-icons">error_outline</i>&nbspRequired field
                    </button>
                    <button type="button" class="action backform waves-effect waves-purple transparent btn-flat"
                            style="margin-left: 3px;margin-right:3px;">BACK
                    </button>
                    <button type="button" id="nextbtn"
                            class="action nextform waves-effect waves-light white-text btn-flat purple"
                            style="margin-left: 3px; margin-right:3px;">NEXT
                    </button>
                    <button type="submit" value="Submit" id="createSubmitForm"
                            class="action submitform waves-effect waves-light white-text btn-flat purple"
                            style="margin-left:3px; margin-right:3px;">CREATE
                    </button>
                </div>
            </form>
        </div>

        <!-- add option -->
        <div id="createAddOption" class="modal" style="margin-top: 30px;">
            <form id="createOption">
                <div class="modal-content">
                    <h4>Create Position</h4>
                    <div class="row">
                        <div class="errorCreateoption input-field col s12 red darken-4 white-text z-depth-barts">

                        </div>
                        <div class="col s12">
                            <div class="input-field col s8 offset-s2">
                                <select id="addOptionSelect" class="browser-default" size="10"
                                        style="height: 150px !important;">
                                    <c:forEach items="${empCategory}" var="name">
                                        <option value="${name.strCategoryName}">${name.strCategoryName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                <input type="text"
                                       placeholder="Ex: Manager" id="addOptionName" name="addOptionName" required>
                                <label for="addOptionName" class="active"><b>Position</b></label>
                            </div>
                            <div class="input-field col s8 offset-s2 center">
                                <a id="createAddPosition"
                                   class="modal-action waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                                </a>
                                <button type="reset" value="Reset"
                                        class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>

        <!-- add option end -->
        <c:forEach items="${empList}" var="employee">
            <div id="emp${employee.intEmpID}" class="modal modal-fixed-footer"
                 style="width: 80% !important; height: 86% !important; max-height: 100% !important;">
                <form class="col s12 updateEmpForm" id="updateEmpForm" method="post" action="updateEmployee"
                      enctype="multipart/form-data">
                    <div class="modal-content" style="padding-bottom: 0px !important;">

                        <div class="wrapper">
                            <div class="input-field col s12">
                                <h4 class="grey-text text-darken-1">Update Employee</h4>
                            </div>
                            <div class="aside aside1 z-depth-0">
                                <!-- first -->
                                <div class="row">
                                    <div class="input-field col s12">
                                        <img name="empupdatedImg" id="empupdatedImg" class="circle"
                                             style="width: 200px; height: 200px;"
                                             src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                             alt=""/>
                                    </div>
                                    <div class="input-field col s12">
                                        <div class="file-field">
                                            <div class="btn purple darken-1">
                                                <span class=""><i class="material-icons">add_a_photo</i></span>
                                                <input name="upload" type="file" accept="image/.jpg, image/.png"
                                                       onchange="loadUpdate(event)">
                                            </div>
                                            <div class="file-path-wrapper">
                                                <input name="imageName" id="imageName" value="image"
                                                       class="file-path validate" type="text">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!-- END ASIDE 1 -->


                            <div class="aside aside2 z-depth-0">
                                <!-- second -->
                                <div class="row">
                                    <div class="input-field col s12" style="margin-top: 39px !important;">
                                        <input type="hidden" name="intEmpID" value="${employee.intEmpID}">
                                        <input value="${employee.strEmpFirstName}" name="strEmpFirstName"
                                               placeholder="Ex: Benigno" id="strEmpFirstName" type="text"
                                               class="validate tooltipped specialname noSpace" data-position="bottom"
                                               data-delay="30"
                                               data-tooltip="Ex: Benigno<br/>( Atleast 2 or more characters )"
                                               pattern="^[a-zA-Z\-'`\s]{2,}$" maxlength="20">
                                        <label for="strEmpFirstName">First Name</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input value="${employee.strEmpMiddleName}" name="strEmpMiddleName"
                                               placeholder="Ex: Cojuangco" id="strEmpMiddleName" type="text"
                                               class="validate tooltipped specialname noSpace" data-position="bottom"
                                               data-delay="30"
                                               data-tooltip="Ex: Cojuangco <br/>( At least 2 or more characters)"
                                               pattern="^[a-zA-Z\-'`\s]{2,}$" maxlength="20">
                                        <label for="strEmpMiddleName">Middle Name</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input value="${employee.strEmpLastName}" name="strEmpLastName"
                                               placeholder="Ex: Aquino" id="strEmpLastName" type="text"
                                               class="validate tooltipped specialname noSpace" data-position="bottom"
                                               data-delay="30"
                                               data-tooltip="Ex: Aquino<br/> ( At least 2 or more characters )"
                                               pattern="^[a-zA-Z\-'`\s]{2,}$">
                                        <label for="strEmpLastName">Last Name</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input type="date" name="strBirthdate" placeholder="January 1, 1996"
                                               class="datepicker active tooltipped" id="updateBirthday"
                                               data-position="bottom" data-delay="30" data-tooltip="Ex: January/1/1996">
                                        <label for="updateBirthday" class="active">Birthday</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <label for="updateAge">Age</label>
                                        <input type="text" class="validate black-text tooltipped" disabled
                                               id="updateAge" placeholder="Ex: 18" data-position="bottom"
                                               data-delay="30"
                                               data-tooltip="Age 18 and above - Qualified<br/> Age 17 and below - Not Qualified">
                                    </div>
                                </div>
                            </div>
                            <!-- END ASIDE 2 -->
                            <%
                            Employee updateEmp = (Employee)pageContext.getAttribute("employee");
                            String gender = updateEmp.getStrEmpGender();
                            month = String.valueOf(updateEmp.getStrEmpGender());
                            String male = null;
                            String female = null;
                            List
                            <Job> job = updateEmp.getJobQualification();

                                if(month.equals("M")){
                                male = "selected"; female="";
                                }
                                else{
                                female = "selected"; male="";
                                }
                                %>

                                <div class="aside aside3 z-depth-0">
                                    <!-- third -->
                                    <div class="row">

                                        <div class="input-field col s12" style="margin-top: 40px !important;">
                                            <select class="browser-default" name="strEmpGender" id="strEmpGender">
                                                <option value="" disabled selected></option>
                                                <option value="M"
                                                <%out.println(male);%>>Male</option>
                                                <option value="F"
                                                <%out.println(female);%>>Female</option>
                                            </select>
                                            <label for="strEmpGender" class="active">Gender</label>
                                        </div>
                                        <div class="input-field col s1">
                                            <label style="margin-left: -3px; margin-top: 15px !important;"
                                                   for="contact">(+63)</label>
                                        </div>
                                        <div class="input-field col s10"
                                             style="margin-top: 28px !important; margin-left: 10px;">
                                            <input value="${employee.strEmpContactNo}" name="strEmpContactNo"
                                                   placeholder="Ex: 9268806979" type="text" id="updateContact"
                                                   class="validate tooltipped" maxlength="10" data-position="bottom"
                                                   data-delay="30" data-tooltip="Ex: 9268806979<br/>( 10 numbers only )"
                                                   pattern="^[0-9]{10,10}$">
                                            <label for="updateContact" style="margin-left: -35px;">Contact
                                                Number</label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input type="email" value="${employee.strEmpEmail}" name="strEmpEmail"
                                                   placeholder="Ex: salon@yahoo.com" class="validate tooltipped noSpace"
                                                   id="updateEmail" data-position="bottom" name="strEmpEmail"
                                                   data-delay="30" data-tooltip="Ex: salon@yahoo.com">
                                            <label for="updateEmail" class="active">Email</label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input value="${employee.strEmpAddress}" name="strEmpAddress"
                                                   placeholder="Ex: #20 Julian Eymard St. Sto.Nino Meycauayan, Bulacan"
                                                   type="text" id="updateAddress"
                                                   class="validate tooltipped specialaddress noSpace"
                                                   data-position="bottom" data-delay="30"
                                                   data-tooltip="Ex: #20 Julian Eymard St. Sto.Nino Meycauayan, Bulacan<br/>( At least 10 or more characters )"
                                                   pattern="[#+A-Za-z0-9\s.,-]{10,}">
                                            <label for="updateAddress" class="active">Address</label>
                                        </div>
                                        <div class="input-field col s8">
                                            <select class="slct2" id="slct2" name="selectedJob" multiple="multiple">
                                                <option value="" disabled></option>
                                                <c:forEach items="${empCategory}" var="name">
                                                    <%
                                                    Employee empJob = (Employee)pageContext.getAttribute("employee");
                                                    List
                                                    <Job> jobList = empJob.getJobQualification();
                                                        EmployeeCategory specificJob =
                                                        (EmployeeCategory)pageContext.getAttribute("name");
                                                        String jobName = specificJob.getStrCategoryName();
                                                        String empPosition1 = "";

                                                        for(int i = 0; i < jobList.size(); i++){
                                                        if(jobList.get(i).getStrJobDesc().equals(jobName)){
                                                        empPosition1 = "selected";
                                                        }
                                                        }
                                                        %>
                                                        <option value="${name.strCategoryName}"
                                                        <%out.println(empPosition1);%>>${name.strCategoryName }</option>
                                                </c:forEach>
                                            </select>
                                            <label for="slct2" class="active">Position</label>
                                        </div>
                                        <div class="input-field col s4">
                                            <button data-target="updateOption"
                                                    class="waves-effect waves-light btn-flat modal-option purple darken-1 white-text">
                                                <i class="material-icons">add</i></button>
                                        </div>

                                    </div>
                                </div>
                                <!-- END OF ASIDE3 -->

                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="reset" value="Reset"
                                class=" modal-action modal-close waves-effect waves-purple transparent btn-flat">CANCEL
                        </button>
                        <button class="waves-effect waves-light purple darken-3 white-text btn-flat" type="submit"
                                value="Submit">UPDATE
                        </button>
                    </div>
                </form>
            </div>
        </c:forEach>

        <c:forEach items="${empList}" var="employee">
            <div id="view${employee.intEmpID}" class="modal modal-fixed-footer transparent z-depth-0"
                 style="width: 70% !important; height: 88% !important; max-height: 100% !important;">
                <form class="col s12" enctype="multipart/form-data">
                    <div class="modal-content" style="padding-bottom: 0px !important;">

                        <div class="wrapper">
                            <div class="input-field col s12">
                                <h3 class="grey-text text-lighten-4">View Employee</h3>
                            </div>
                            <div class="aside aside1 z-depth-0">
                                <!-- first -->
                                <div class="row">
                                    <div class="input-field col s12">
                                        <!--<input type="file" class="dropify" data-default-file="url_of_your_file"-->
                                        <!--disabled="disabled"/>-->
                                        <img name="empupdatedImg" id="empupdatedImg"
                                             style="width: 300px; height: 300px;"
                                             src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                             alt=""/>
                                    </div>
                                </div>
                            </div>
                            <!-- END ASIDE 1 -->


                            <div class="aside aside2 z-depth-0">
                                <!-- second -->
                                <div class="row">
                                    <div class="input-field col s12" style="margin-top: 39px !important;">
                                        <input value="${employee.strEmpFirstName}" name="strEmpFirstName"
                                               placeholder="Ex: Benigno" id="strEmpFirstName" type="text"
                                               class="white-text" readonly style="border: none;">
                                        <label for="strEmpFirstName" class="purple-text text-lighten-3"><b>First
                                            Name</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input value="${employee.strEmpMiddleName}" name="strEmpMiddleName"
                                               placeholder="Ex: Cojuangco" id="strEmpMiddleName" type="text"
                                               class="white-text" readonly style="border: none;">
                                        <label for="strEmpMiddleName" class="purple-text text-lighten-3"><b>Middle
                                            Name</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input value="${employee.strEmpLastName}" name="strEmpLastName"
                                               placeholder="Ex: Aquino" id="strEmpLastName" type="text"
                                               class="white-text" readonly style="border: none;">
                                        <label for="strEmpLastName" class="purple-text text-lighten-3"><b>Last Name</b></label>
                                    </div>
                                    <div class="input-field col s12">
                                        <input type="date" name="strBirthdate" placeholder="January 1, 1996"
                                               class="datepicker tooltipped" id="createBirthday" data-position="bottom"
                                               data-delay="30" data-tooltip="Ex: January 1, 1996" disabled="disabled">
                                        <label for="createBirthday" class="active">Birthday</label>
                                    </div>
                                    <div class="input-field col s12">
                                        <label for="updateAge">Age</label>
                                        <input type="text" class="validate black-text tooltipped" disabled
                                               id="updateAge" placeholder="Ex: 18" data-position="bottom"
                                               data-delay="30"
                                               data-tooltip="Age 18 and above - Qualified<br/> Age 17 and below - Not Qualified">
                                    </div>
                                </div>
                            </div>
                            <!-- END ASIDE 2 -->
                            <%
                            Employee updateEmp = (Employee)pageContext.getAttribute("employee");
                            String gender = updateEmp.getStrEmpGender();
                            month = String.valueOf(updateEmp.getStrEmpGender());
                            String male = null;
                            String female = null;
                            List
                            <Job> job = updateEmp.getJobQualification();

                                if(month.equals("M")){
                                male = "selected"; female="";
                                }
                                else{
                                female = "selected"; male="";
                                }
                                %>

                                <div class="aside aside3 z-depth-0">
                                    <!-- third -->
                                    <div class="row">

                                        <div class="input-field col s12">
                                            <select class="white-text" name="strEmpGender" id="strEmpGender1"
                                                    disabled="disabled" style="border:none;">
                                                <option value="" disabled selected></option>
                                                <option value="M"
                                                <%out.println(male);%>>Male</option>
                                                <option value="F"
                                                <%out.println(female);%>>Female</option>
                                            </select>
                                            <label for="strEmpGender1" class="active purple-text text-lighten-3"><b>Gender</b></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input value="${employee.strEmpContactNo}" name="strEmpContactNo"
                                                   placeholder="Ex: 9268806979" type="text" id="contact"
                                                   class="white-text"  readonly
                                                   style="border:none;">
                                            <label for="contact" class="purple-text text-lighten-3"><b>Contact
                                                Number</b></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input type="email" name="strEmpEmail" value="${employee.strEmpEmail}"
                                                   placeholder="Ex: salon@yahoo.com" class="white-text" id="emailadd"
                                                   readonly style="border:none;">
                                            <label for="emailadd" class="active purple-text text-lighten-3"><b>Email</b></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <input value="${employee.strEmpAddress}" name="strEmpAddress"
                                                   placeholder="Ex: #20 Julian Eymard St. Sto.Nino Meycauayan, Bulacan"
                                                   type="text" id="address" class="white-text" readonly
                                                   style="border:none;">
                                            <label for="address"
                                                   class="purple-text text-lighten-3"><b>Address</b></label>
                                        </div>
                                        <div class="input-field col s12">
                                            <select id="slct3" name="selectedJob" class="white-text"
                                                    disabled="disabled" style="border: none;">
                                                <option value="" disabled selected></option>
                                                <c:forEach items="${empCategory}" var="name">
                                                    <%
                                                    String empPosition = null;
                                                    EmployeeCategory cate =
                                                    (EmployeeCategory)pageContext.getAttribute("name");
                                                    if(cate.getStrCategoryName().equals(position)){
                                                    empPosition = "selected";
                                                    }
                                                    else{
                                                    empPosition = "";
                                                    }
                                                    %>
                                                    <option value="${name.strCategoryName}"
                                                    <%out.println(empPosition);%>>${name.strCategoryName }</option>
                                                </c:forEach>
                                            </select>
                                            <label for="slct3" class="active purple-text text-lighten-3"
                                                   style="margin-top: 22px !important;"><b>Position</b></label>
                                        </div>

                                    </div>
                                </div>
                                <!-- END OF ASIDE3 -->

                        </div>
                    </div>
                    <div class="modal-footer transparent">
                        <a class="modal-close waves-effect waves-light transparent btn-flat white-text">BACK
                        </a>
                    </div>
                </form>
            </div>
        </c:forEach>

        <!-- update add option -->
        <div id="updateOption" class="modal" style="margin-top: 30px;">
            <form id="updateOptionForm">
                <div class="modal-content">
                    <h4>Add Another Position</h4>
                    <div class="row">
                        <div class="col s12">
                            <div class="input-field col s8 offset-s2">
                                <select id="updateAddOptionSelect" class="browser-default" size="10">
                                    <c:forEach items="${empCategory}" var="name">
                                        <option value="${name.strCategoryName}">${name.strCategoryName }</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                                <input type="text" class="validate tooltipped specialoption noSpace"
                                       placeholder="Ex: Cashier" id="updateAddOptionName" name="updateAddOptionName"
                                       data-position="bottom" data-delay="30"
                                       data-tooltip="Ex: Cashier<br/>( Atleast 5 or more characters )"
                                       pattern="^[A-Za-z-\s]{5,}$">
                                <label for="updateAddOptionName" class="active">Position</label>
                            </div>
                            <div class="input-field col s8 offset-s2 center">
                                <button type="submit" value="Submit" id="updateAddPosition"
                                        class="waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                                </button>
                                <button type="reset" value="Reset"
                                        class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
        <!-- update add option END -->

        <c:forEach items="${empList}" var="employee">
            <div id="de${employee.intEmpID}" class="modal" style="width: 30% !important;">
                <form method="get" action="deactivateEmployee" id="deleteEmpForm">
                    <div class="container">
                        <div class="modal-content">
                            <div class="row">
                                <h5 class="red-text">Warning!</h5>
                                <p class="center">Are you sure you want to deactivate</p>
                                <p class="center">${employee.strEmpFirstName} ${employee.strEmpLastName}?</p>
                                <input type="hidden" size="10" name="intEmpID" value="${employee.intEmpID}">
                            </div>
                        </div>
                        <div class="col s12 center" style="margin-bottom: 30px;">
                            <button class="waves-effect waves-light purple darken-3 btn-flat white-text">YES</button>
                            <a href="#"
                               class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>
                        </div>
                    </div>
                </form>
            </div>
        </c:forEach>


    </div>
</div>