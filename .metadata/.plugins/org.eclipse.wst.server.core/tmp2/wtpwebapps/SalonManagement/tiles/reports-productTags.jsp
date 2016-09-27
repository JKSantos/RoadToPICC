<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="wrapper" ng-controller="productTagsReportController">
    <div class="main z-depth-barts" style="margin-left: 20px; margin-right: 20px;">
        <div class="col s12" style="margin-left: 20px; margin-right: 20px;">
            <h3 class=" center light">Product Tags Reports</h3>
            <!--<a class="btnshadow hoverable z-depth-1 waves-effect waves-light modal-trigger btn-flat purple darken-2 left white-text"-->
    
	      <div class="row">

	        <div class="col s7">
	          <div class="card darken-1">
	            <div class="card-content white-text">
	             <div id="containerMonthly" style="min-width: 100%; height: 100%; margin: 0 auto"></div>
	            </div>
	            <div class="card-action">
	              <a class="waves-effect waves-light btn right green darken-2" ng-click = "downloadMonthly()"><i class="material-icons left">file_download</i>download</a>
	            </div>
	          </div>
	        </div>

          <div class="col s5">
            <div class="card darken-1">
              <div class="card-content white-text">
               <div id="containerQuarterly" style="min-width: 100%; height: 100%; max-width: 100%; margin: 0 auto"></div>
              </div>
              <div class="card-action">
               <a class="waves-effect waves-light btn right green darken-2"><i class="material-icons left">file_download</i>download</a>
              </div>
            </div>
          </div>

	      </div>

         <div class="row">
          <div class="col s12">
            <div class="card darken-1">
              <div class="card-content">
              	<div class="row">
              	   <div class="input-field col s3">
                       <select ng-model = "select.yearFrom">
                         <option value="" disabled selected>Choose your option</option>
                         <option value="2010">2010</option>
                         <option value="2011">2011</option>
                         <option value="2012">2012</option>
                         <option value="2013">2013</option>
                         <option value="2014">2014</option>
                         <option value="2015">2015</option>
                         <option value="2016">2016</option>
                       </select>
                       <label>Year From</label>
                    </div>
                    <div class="input-field col s3">
                       <select ng-model = "select.yearTo">
                         <option value="" disabled selected>Choose your option</option>
                         <option value="2010">2010</option>
                         <option value="2011">2011</option>
                         <option value="2012">2012</option>
                         <option value="2013">2013</option>
                         <option value="2014">2014</option>
                         <option value="2015">2015</option>
                         <option value="2016">2016</option>
                       </select>
                       <label>Year To</label>
                    </div>
                    
                   	<div class = "col s2">
                   		<a class="waves-effect waves-light btn btn-large right" ng-click = "generate(select)"><i class="material-icons left">search</i>SHOW</a>
                   	</div>
              	</div>
               <div id="containerAnnually" style="min-width: 99%; height: 99%; max-width: 100%; margin: 0 auto"></div>
              </div>
              <div class="card-action">
                <a class="waves-effect waves-light btn right green darken-2"><i class="material-icons left">file_download</i>download</a>
              </div>
            </div>
          </div>
        </div>
            
        </div>
    </div>



</div>