<!DOCTYPE html>
<html>
  <head>
  <link rel="stylesheet" href="./css/materialize.min.css"  media="screen,projection"/>
  <link type="text/css" rel="stylesheet" href="./css/materialize.css"/>
  <link type="text/css" rel="stylesheet" href="./css/mystyle.css"/>
  <link rel="stylesheet" type="text/css" href="./css/datatables.min.css"/>
  <link rel="stylesheet" type="text/css" href="./css/datatables.material.min.css"/>
  <link rel="stylesheet" type="text/css" href="./css/material.min.css">
  <link type="text/css" rel="stylesheet" href="./css/trans-reservation.css"/><!--Let browser know website is optimized for mobile-->
  <!-- full calendar declrations -->
  <link href='./css/fullcalendar.css' rel='stylesheet' />
  <link href='./css/fullcalendar.print.css' rel='stylesheet' media='print' />
  <script src='./js/moment.min.js'></script>
  <script src='./js/jquery.min.js'></script>
  <script src='./js/fullcalendar.min.js'></script>

  <script>

   $(document).ready(function() {
     
     $('#calendar').fullCalendar({
       header: {
         left: 'prev,next today',
         center: 'title',
         right: 'month,basicWeek,basicDay'
       },
       defaultDate: '2016-01-12',
       editable: true,
       eventLimit: true, // allow "more" link when too many events
       events: [
         {
           title: 'All Day Event',
           start: '2016-01-01'
         },
         {
           title: 'Long Event',
           start: '2016-01-07',
           end: '2016-01-10'
         },
         {
           id: 999,
           title: 'Repeating Event',
           start: '2016-01-09T16:00:00'
         },
         {
           id: 999,
           title: 'Repeating Event',
           start: '2016-01-16T16:00:00'
         },
         {
           title: 'Conference',
           start: '2016-01-11',
           end: '2016-01-13'
         },
         {
           title: 'Meeting',
           start: '2016-01-12T10:30:00',
           end: '2016-01-12T12:30:00'
         },
         {
           title: 'Lunch',
           start: '2016-01-12T12:00:00'
         },
         {
           title: 'Meeting',
           start: '2016-01-12T14:30:00'
         },
         {
           title: 'Happy Hour',
           start: '2016-01-12T17:30:00'
         },
         {
           title: 'Dinner',
           start: '2016-01-12T20:00:00'
         },
         {
           title: 'Birthday Party',
           start: '2016-01-13T07:00:00'
         },
         {
           title: 'Click for Google',
           url: 'http://google.com/',
           start: '2016-01-28'
         }
       ]
     });
     
   });

  </script>
<style>

  #calendar {
    max-width: 900px;
    margin: 0 auto;
  }

</style>

  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  </head>

  <body class="blue-grey lighten-5">
    <nav class="z-depth-0">
      <div class="nav-wrapper purple">
        <a href="#!" class="brand-logo white-text" style="margin-left: 30px;">Salon Management System</a>
      </div>
    </nav>
    <div class="container white z-depth-barts">
      <h1 class="thin center purple-text text-darken-4">
        Calendar View
      </h1>
      <div class="col s3" style="margin-left: 28px; margin-bottom: 5px;">
      <a href="transactions-reservation.jsp" class=" z-depth-barts waves-effect waves-light purple btn-flat white-text">Back</a>
      </div>
      <div class="wrapper">
                 <div id='calendar'></div>  
          </div>
    </div>
          
  </body>

</html>