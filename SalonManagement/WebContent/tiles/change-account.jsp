<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<style>
    ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
        color: #757575;
    }

    :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
        color: #757575;
    }

    ::-moz-placeholder { /* Mozilla Firefox 19+ */
        color: #757575;
    }

    :-ms-input-placeholder { /* Internet Explorer 10-11 */
        color: #757575;
    }
</style>


<div class="wrapper">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class="grey-text center text-darken-1">Account Settings</h3>
            <div class="row">
                <div class="col s12">
                    <div class="container">
                        <ul class="collapsible" data-collapsible="accordion">
                            <li>
                                <div class="collapsible-header">
                                    Username
                                </div>
                                <div class="collapsible-body">
                                    <form action="">
                                        <div class="row">
                                            <div class="input-field col s4 offset-s4">
                                                <input type="text" id="empUserName" placeholder="Username">
                                            </div>
                                            <div class="input-field col s12">
                                                <button class="btn waves-effect waves-light purple" type="submit" name="action">
                                                    Save Changes
                                                </button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </li>
                            <li>
                                <div class="collapsible-header">
                                    Password
                                </div>
                                <div class="collapsible-body">
                                    <div class="row">
                                        <div class="input-field col s4 offset-s4">
                                            <input type="password" id="empCurrentPassword" placeholder="Current">
                                        </div>
                                        <div class="input-field col s4 offset-s4">
                                            <input type="password" id="empNewPassword" placeholder="New">
                                        </div>
                                        <div class="input-field col s4 offset-s4">
                                            <input type="password" id="empRetypePassword" placeholder="Re-type">
                                        </div>
                                        <div class="input-field col s12">
                                            <button class="btn waves-effect waves-light purple" type="submit" name="action">
                                                Save Changes
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col s12">
            </div>

        </div>
    </div>


</div>