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
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#createEmployeeModal" style="margin-top: 30px; margin-left: 15px;"><i
                    class="material-icons">add</i></a>
            <a class="z-depth-1 hoverable waves-effect waves-light modal-trigger btn purple darken-2 left white-text"
               href="#empArchive" style="margin-top: 30px; margin-left: 15px;"><i class="material-icons">archive</i></a>
            <nav class="right white hoverable  z-depth-1" style="width: 300px; margin-right: 20px;">
                <div class="nav-wrapper col s4">
                    <form>
                        <div class="input-field">
                            <input id="empSearch" class="grey-text text-darken-4" type="search" required>
                            <label for="empSearch" style="margin-top: -20px !important;"><i
                                    class="material-icons grey-text text-darken-4"
                                    style="margin-top: 20px;">search</i></label>
                            <i class="material-icons grey-text text-darken-4">close</i>
                        </div>
                    </form>
                </div>
            </nav>

            <table id="emptbl"
                   class="hoverable z-depth-1 cell-border row-border display centered responsive-table highlight"
                   cellspacing="0"
                   width="100%"
                   style="border: 1px solid #bdbdbd; padding: 10px; margin-top: -30px !important;" rowspan="10">
                <thead>
                <tr>
                    <th class="no-sort"></th>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left left-align" align="left">Position</th>
                    <th class="dt-head-right">Contact</th>
                    <th class="dt-head-left">Email</th>
                    <th class="dt-head-left">Address</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </thead>
                <tfoot style="border: 1px solid #bdbdbd;">
                <tr>
                    <th class="no-sort"></th>
                    <th class="dt-head-left">Name</th>
                    <th class="dt-head-left left-align" align="left">Position</th>
                    <th class="dt-head-right">Contact</th>
                    <th class="dt-head-left">Email</th>
                    <th class="dt-head-left">Address</th>
                    <th align="center" class="no-sort">Action</th>
                </tr>
                </tfoot>
                <tbody>
                <c:forEach items="${empList}" var="employee">
                    <%! String str=null; %>
                    <% Employee emp = (Employee)pageContext.getAttribute("employee");
                    str=String.valueOf(emp.getIntEmpID());
                    String de = str;
                    %>
                    <tr>
                        <td style="padding:0; margin:0;" class="dt-body-center">
                            <center><img name="empupdatedImg" id="empupdatedImg" class="circle"
                                         style="width: 30px; height: 30px;"
                                         src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                         alt="${employee.strEmpFirstName}"/></center>
                        </td>
                        <td class="dt-body-left">${employee.strEmpFirstName} ${employee.strEmpLastName}</td>
                        <td style="padding-left: 10px !important; margin-left: 0px;" class="dt-body-left">NONE</td>
                        <td style="padding-right: 10px !important; margin-right: 0px;" class="dt-body-right ">
                            ${employee.strEmpContactNo}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left ">
                            ${employee.strEmpEmail}
                        </td>
                        <td style="padding-left: 10px !important; margin-left: 5px;" class="dt-body-left ">
                            ${employee.strEmpAddress}
                        </td>
                        <td class="center" style="padding:0; margin:0;">
                            <a data-delay="30" data-position="bottom" data-tooltip="View"
                               class="waves-effect waves-purple modal-viewall btn-flat transparent black-text"
                               href="#view<%=str%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">visibility</i>
                            </a>
                            <a data-delay="30" data-position="bottom" data-tooltip="Update"
                               class="waves-effect waves-purple modal-trigger btn-flat transparent black-text empUpdatebtn"
                               href="#emp<%=str%>" style="padding-left: 10px;padding-right:10px; margin: 5px;">
                                <i class="material-icons">edit</i>
                            </a>
                            <button class="empdeacbtn waves-effect waves-purple btn-flat transparent red-text text-accent-4"
                           style="padding-left: 10px;padding-right:10px; margin: 5px;" id="${employee.intEmpID}"
                               title="Deactivate" formaction="deactivateEmployee"><i class="material-icons">delete</i></button>
                            <!--href="#de<%=de%>"-->
                        </td>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>
    </div>

    <!-- ARCHIVE BEGIN -->

    <!-- ARCHIVE END -->

    <!-- Modal Structure -->
    <div id="createEmployeeModal" class="modal modal-fixed-footer">
        <form id="createEmpForm" class="col s12 form" method="post" action="createEmployee"
              enctype="multipart/form-data">
            <div class="modal-content">
                <div class="wrapper">
                    <h4 class="center grey-text text-darken-1">Create Employee<a id="btnCreateExit" type="reset"
                                                                                 value="Reset"
                                                                                 class="modal-action modal-close"><i
                            class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                    <div class="progress">
                        <div class="determinate center active purple darken-4 white-text" role="progressbar"
                             aria-valuemin="0"
                             aria-valuemax="100" style="color: white;"></div>
                    </div>
                    <!--<div id="card-alert" class="card red z-depth-barts">-->
                    <!--<div class="card-content center white-text errorcontainer">-->
                    <!--</div>-->
                    <!--<button type="button" class="close red-text" data-dismiss="alert" aria-label="Close">-->
                    <!--<span aria-hidden="true"><i class="material-icons">close</i></span>-->
                    <!--</button>-->
                    <!--</div>-->
                    <div class="errorcontainer card red center input-field col s12 white-text z-depth-barts">

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
                                <div class="input-field col s12" style="margin-top: -10px;">
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
                                <div class="input-field col s12" style="margin-top: -20px !important;">
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
                <button type="button" id="backbtn"
                        class="action backform waves-effect waves-purple transparent btn-flat"
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
                    <div class="errorCreateoption center input-field col s12 card red white-text z-depth-barts">

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
                            <button type="reset" value="Reset" id="crAddOptCancel"
                                    class="modal-close waves-effect waves-purple transparent btn-flat white">CANCEL
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <!-- add option end -->
    <!--UPDATE-->
    <c:forEach items="${empList}" var="employee">
        <div id="emp${employee.intEmpID}" class="empModalUpdate modal modal-fixed-footer">
            <form id="updateEmpForm" class="updateEmpForm col s12 form" method="post" action="updateEmployee"
                  enctype="multipart/form-data">
                <div class="modal-content">
                    <div class="wrapper">
                        <h4 class="center grey-text text-darken-1">Update Employee<a id="btnUpdateExit" type="reset"
                                                                                     value="Reset"
                                                                                     class="btnUpdateExit modal-action modal-close"><i
                                class="small material-icons right grey-text text-darken-4">close</i></a></h4>
                        <div class="updateerror center input-field col s12 card red white-text z-depth-barts" style="margin-top: -10px !important;">

                        </div>
                        <div class="row">
                            <div class="col s12">
                                <ul class="tabs tab-demo-active" style="width: 100%; background-color: #fafafa;">
                                    <li class="tab col s6"><a
                                            class="firsttab purple-text text-darken-2 active waves-effect waves-light"
                                            href="#emp${employee.intEmpID}A"><b>INFO 1</b></a></li>
                                    <li class="tab col s6"><a
                                            class="secondtab purple-text text-darken-2 waves-effect waves-light"
                                            href="#emp${employee.intEmpID}B"><b>INFO 2</b></a></li>
                                </ul>
                            </div>
                            <div id="emp${employee.intEmpID}A" class="ftab col s12">
                                <div class="wrapper" style="margin-top: 10px;">
                                    <div class="aside aside1 z-depth-0" style="margin-top: 5px;">
                                        <!-- first -->
                                        <div class="row">
                                            <!--<div class="input-field col s12">-->
                                            <!--<img name="image" id="employeeimg" class="circle"-->
                                            <!--style="width: 200px; height: 200px;" src="./img/anon.jpg" alt=""/>-->
                                            <!--</div>-->
                                            <div class="input-field col s12">
                                                <img name="empupdatedImg" id="empupdatedImg" class="empprevimg"
                                                     style="width: 200px; height: 250px;"
                                                     src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                                     alt=""/>
                                            </div>
                                            <div class="input-field col s12">
                                                <div class="file-field">
                                                    <div class="btn purple darken-1">
                                                            <span class=""><i
                                                                    class="material-icons">add_a_photo</i></span>
                                                        <input name="upload" type="file" class="empimgupload"
                                                               accept="image/.jpg, image/.png">
                                                    </div>
                                                    <div class="file-path-wrapper">
                                                        <input name="imageName" id="imageName" value="image"
                                                               class="file-path validate" type="text">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="aside aside2 z-depth-0"
                                         style="margin-bottom: -45px !important; overflow-y: hidden !important;">
                                        <div class="row form-group">
                                            <div class="input-field col s12">
                                                <input type="hidden" name="intEmpID" value="${employee.intEmpID}">
                                                <input name="strEmpFirstName" id="strUpEmpFname" type="text"
                                                       class="validate" placeholder="Ex: Juan" required
                                                       value="${employee.strEmpFirstName}"/>
                                                <label for="strUpEmpFname" class="active"><b>First
                                                    Name</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input name="strEmpMiddleName" id="strUpEmpMname" type="text"
                                                       class="validate" placeholder="Ex: Dela"
                                                       value="${employee.strEmpMiddleName}"/>
                                                <label for="strUpEmpMname" class="active"><b>Middle Name</b></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input name="strEmpLastName" id="strUpEmpLname" type="text"
                                                       class="validate" placeholder="Ex: Cruz"
                                                       required value="${employee.strEmpLastName}"/>
                                                <label for="strUpEmpLname" class="active"><b>Last Name</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input type="date" name="strBirthdate" id="upbday"
                                                       class="updateEmpBirthday" placeholder="Ex: 01/01/01"
                                                       required/>
                                                <label for="upbday"><b>Birthday</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input style="border-bottom-color: dimgrey;" type="text"
                                                       name="createAge" class="updateEmpAge"
                                                       id="updateEmpAge" placeholder="Ex: 18" disabled/>
                                                <label for="updateEmpAge" id="updateAgeLabel"
                                                       class="active"
                                                       style="color: #424242 !important;"><b>Age</b></label>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
                                <div id="emp${employee.intEmpID}B" class="col s12" style="margin-bottom: -30px;">
                                    <div class="container" style="margin-top: 15px;">
                                        <div class="row form-group">
                                            <div class="input-field col s12">
                                                <select name="strEmpGender" id="updateGender" required
                                                        class="required">
                                                    <option value="0" disabled selected>Choose...</option>
                                                    <option value="M"
                                                    <%out.println(male);%>>Male</option>
                                                    <option value="F"
                                                    <%out.println(female);%>>Female</option>
                                                </select>
                                                <label for="updateGender" class="active"
                                                       style="margin-top: 20px !important;"><b>Gender</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s12" style="margin-top: -10px;">
                                                <input type="text" name="strEmpContactNo" id="updateContact"
                                                       class="updateContact"
                                                       placeholder="Ex: 09123456789"
                                                       value="${employee.strEmpContactNo}"/>
                                                <label for="updateContact"><b>Phone Number</b></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input type="email" name="strEmpEmail" id="updateEmail"
                                                       placeholder="Ex: salon@yahoo.com"
                                                       value="${employee.strEmpEmail}"/>
                                                <label for="updateEmail" class="active"><b>Email</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s12">
                                                <input type="text" name="strEmpAddress" id="updateAddress"
                                                       placeholder="Ex: #20 Julian Eymard St. Sto.Nino Meycauayan, Bulacan"
                                                       required value="${employee.strEmpAddress}"/>
                                                <label for="updateAddress" class="active"><b>Address</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s8">
                                                <select name="selectedJob" id="upSelectedJob" multiple="multiple"
                                                        required
                                                        class="required upSelectedJob">
                                                    <option value="default" disabled>Choose...</option>
                                                    <c:forEach items="${empCategory}" var="name">
                                                        <%
                                                        Employee empJob =
                                                        (Employee)pageContext.getAttribute("employee");
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
                                                            <option value="${name.strCategoryName}" class="upOptJava"
                                                            <%out.println(empPosition1);%>>${name.strCategoryName
                                                            }</option>
                                                    </c:forEach>
                                                </select>
                                                <label for="upSelectedJob" class="active"
                                                       style="margin-top: 25px !important;"><b>Position</b><i
                                                        class="material-icons red-text tiny">error_outline</i></label>
                                            </div>
                                            <div class="input-field col s4">
                                                <button data-target="updateOption"
                                                        class="waves-effect waves-light btn-flat modal-option purple darken-3 white-text">
                                                    <i class="material-icons">add</i></button>
                                            </div>
                                            <div class="input-field col s12" style="margin-top: -15px !important;">
                                                <input type="checkbox" name="chkGrantAccess" id="chkupGrantAccess${employee.intEmpID}"
                                                       class="filled-in" style="color: purple;"/>
                                                <label for="chkupGrantAccess${employee.intEmpID}"><b>Grant Access</b></label>
                                            </div>
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
                    <button type="submit" value="Submit" id="updateSubmitForm"
                            class="updateSubmitForm waves-effect waves-light white-text btn-flat purple"
                            style="margin-left:3px; margin-right:3px;">UPDATE
                    </button>
                </div>
            </form>
        </div>
    </c:forEach>
    <!--UPDATE END-->


    <!-- update add option -->
    <div id="updateOption" class="modal" style="margin-top: 30px;">
        <form id="updateOptionForm" class="updateOptionForm">
            <div class="modal-content">
                <h4>Create Position</h4>
                <div class="row">
                    <div class="errorUpdateoption center input-field col s12 card red white-text z-depth-barts">

                    </div>
                    <div class="col s12">
                        <div class="input-field col s8 offset-s2">
                            <select id="updateAddOptionSelect" class="browser-default" size="10"
                                    style="height: 150px !important;">
                                <c:forEach items="${empCategory}" var="name">
                                    <option value="${name.strCategoryName}">${name.strCategoryName }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-field col s8 offset-s2" style="margin-top: 20px;">
                            <input type="text" class="updateAddOptionName"
                                   placeholder="Ex: Manager" id="updateAddOptionName" name="updateAddOptionName"
                                   required>
                            <label for="updateAddOptionName" class="active"><b>Position</b></label>
                        </div>
                        <div class="input-field col s8 offset-s2 center">
                            <a id="updateAddPosition"
                               class="updateAddPosition modal-action waves-effect waves-light purple darken-3 btn-flat white-text">SAVE
                            </a>
                            <button type="reset" value="Reset"
                                    class="upAddOptCancel modal-close waves-effect waves-purple transparent btn-flat white">
                                CANCEL
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </form>
    </div>


    <!-- update add option END -->
    <c:forEach items="${empList}" var="employee">
        <div id="view${employee.intEmpID}" class="modal transparent z-depth-0 viewEmpModal animated flipInY">
            <div class="modal-content">
                <div class="wrapper">
                    <h3 class="center grey-text text-lighten-2">View Employee</h3>
                    <div class="aside aside1 z-depth-0">
                        <!-- first -->
                        <div class="row">
                            <div class="input-field col s12">
                                <!--<input type="file" class="dropify" data-default-file="url_of_your_file"-->
                                <!--disabled="disabled"/>-->
                                <img name="empupdatedImg" id="empupdatedImg"
                                     style="width: 300px; height: 350px; border-radius: 10px;" class="z-depth-2"
                                     src="<s:url action='getImage'><s:param name='ImageID'>${employee.intEmpID}</s:param><s:param name='type'>employee</s:param></s:url>"
                                     alt=""/>
                            </div>
                        </div>
                    </div>
                    <div class="aside aside2 z-depth-0" style="margin-bottom: -30px !important;">
                        <div class="row">
                            <div class="input-field col s12">
                                <input type="text" name="viewEmpName" id="viewEmpName" disabled class="white-text"
                                       value="${employee.strEmpFirstName} ${employee.strEmpMiddleName} ${employee.strEmpLastName}"/>
                                <label for="viewEmpName" class="purple-text text-lighten-2"><b>Name</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="date" name="viewEmpBday" placeholder="January 1, 1996"
                                       class="datepicker white-text" disabled id="viewEmpBday" value="">
                                <label for="viewEmpBday"
                                       class="active purple-text text-lighten-2"><b>Birthday</b></label>
                            </div>
                            <div class="input-field col s6">
                                <input type="text" name="viewEmpAge" id="viewEmpAge" class="white-text" disabled
                                       placeholder="18"/>
                                <label for="viewEmpAge" class="purple-text text-lighten-2 active"><b>Age</b></label>
                            </div>
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
                                <div class="input-field col s6">
                                    <!--<select class="white-text" name="viewEmpGender" id="viewEmpGender"-->
                                    <!--disabled="disabled" style="border:none;">-->
                                    <!--<option value="" disabled selected></option>-->
                                    <!--<option style="color: white !important;" value="M"-->
                                    <!--<%out.println(male);%>>Male</option>-->
                                    <!--<option class="white-text" value="F"-->
                                    <!--<%out.println(female);%>>Female</option>-->
                                    <!--</select>-->
                                    <!--<label for="viewEmpGender" class="purple-text text-lighten-2"><b>Gender</b></label>-->
                                    <input type="text" name="viewEmpGender" class=white-text id="viewEmpGender" disabled
                                           value="${employee.strEmpGender}"/>
                                    <label for="viewEmpGender"
                                           class="purple-text text-lighten-2 active"><b>Gender</b></label>
                                </div>
                                <div class="input-field col s6">
                                    <input value="${employee.strEmpContactNo}" name="viewEmpContact"
                                           placeholder="+63 912-345-7897" type="text" id="viewEmpContact"
                                           class="white-text updateContact" disabled>
                                    <label for="viewEmpContact" class="purple-text text-lighten-2"><b>Contact
                                        Number</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input type="email" name="viewEmpEmail" value="${employee.strEmpEmail}"
                                           placeholder="Ex: salon@yahoo.com" class="white-text" id="viewEmpEmail"
                                           disabled>
                                    <label for="viewEmpEmail"
                                           class="active purple-text text-lighten-2"><b>Email</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <input value="${employee.strEmpAddress}" name="viewEmpAddress"
                                           placeholder="Ex: #20 Julian Eymard St. Sto.Nino Meycauayan, Bulacan"
                                           type="text" id="viewEmpAddress" class="white-text" disabled>
                                    <label for="viewEmpAddress"
                                           class="active purple-text text-lighten-2"><b>Address</b></label>
                                </div>
                                <div class="input-field col s12">
                                    <select id="viewEmpJob" name="viewEmpJob" class="white-text"
                                            disabled>
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
                                            <option class="whitetext" value="${name.strCategoryName}"
                                            <%out.println(empPosition);%>><span class="whitetext">${name.strCategoryName }</span></option>
                                        </c:forEach>
                                    </select>
                                    <label for="viewEmpJob" class="purple-text text-lighten-2"><b>Position</b></label>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer transparent">
                <a href="#!"
                   class="modal-action modal-close waves-effect waves-light purple-text text-lighten-1 btn-large z-depth-1 viewcloseempbtn"
                   style="background-color: transparent !important; border: 3px solid #ab47bc !important; border-radius: 10px !important; margin: 0 !important;"><span
                        style="padding-top: -10px !important;"><i
                        class="material-icons left"><b>close</b></i><b>Close</b></span></a>
            </div>
        </div>
    </c:forEach>

    <!--<c:forEach items="${empList}" var="employee">-->
        <!--<div id="de${employee.intEmpID}" class="modal" style="width: 30% !important;">-->
            <!--<form id="deac${employee.intEmpID}" method="get" action="deactivateEmployee" id="deleteEmpForm" name="empdeacform">-->
                <!--&lt;!&ndash;<div class="container">&ndash;&gt;-->
                    <!--&lt;!&ndash;<div class="modal-content">&ndash;&gt;-->
                        <!--&lt;!&ndash;<div class="row">&ndash;&gt;-->
                            <!--&lt;!&ndash;<h5 class="red-text">Warning!</h5>&ndash;&gt;-->
                            <!--&lt;!&ndash;<p class="center">Are you sure you want to deactivate</p>&ndash;&gt;-->
                            <!--&lt;!&ndash;<p class="center">${employee.strEmpFirstName} ${employee.strEmpLastName}?</p>&ndash;&gt;-->
                            <!--&lt;!&ndash;<input type="hidden" size="10" name="intEmpID" value="${employee.intEmpID}">&ndash;&gt;-->
                        <!--&lt;!&ndash;</div>&ndash;&gt;-->
                    <!--&lt;!&ndash;</div>&ndash;&gt;-->
                    <!--&lt;!&ndash;<div class="col s12 center" style="margin-bottom: 30px;">&ndash;&gt;-->
                        <!--&lt;!&ndash;<a class="waves-effect waves-light purple darken-3 btn-flat white-text">YES</a>&ndash;&gt;-->
                        <!--&lt;!&ndash;<a href="#"&ndash;&gt;-->
                           <!--&lt;!&ndash;class="modal-action modal-close waves-effect waves-purple transparent btn-flat black-text">NO</a>&ndash;&gt;-->
                    <!--&lt;!&ndash;</div>&ndash;&gt;-->
                <!--&lt;!&ndash;</div>&ndash;&gt;-->
            <!--</form>-->
        <!--</div>-->
    <!--</c:forEach>-->


</div>
</div>