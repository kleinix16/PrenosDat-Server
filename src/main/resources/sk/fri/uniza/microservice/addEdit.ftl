<#-- @ftlvariable name="" type="sk.fri.uniza.microservice.Data" -->
<!DOCTYPE html>
<html lang="en">

<head>
    <#if !(data??)>
        <#assign data = {"id":-1,"date":"null","temp":"null","hum":"null"}> 
        <title>ADD DATA</title>
    <#else>
        <title>EDIT DATA</title>
    </#if>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="icon" type="image/png" href="/assets/images/icons/favicon.ico" />

    <link rel="stylesheet" type="text/css" href="/assets/bootstrap/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">

    <link rel="stylesheet" type="text/css" href="/assets/animate/animate.css">

    <link rel="stylesheet" type="text/css" href="/assets/select2/select2.min.css">

    <link rel="stylesheet" type="text/css" href="/assets/perfect-scrollbar/perfect-scrollbar.css">

    <link rel="stylesheet" type="text/css" href="/assets/css/util.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/main.css">

    <!-- <link rel="stylesheet" type="text/css" href="css/view.css" media="all"> -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9"
        crossorigin="anonymous">

    <script type="text/javascript" src="/assets/js/view.js"></script>
    <script type="text/javascript" src="/assets/js/jquery-3.3.1.min.js"></script>

  <style type="text/css">
    body { padding-top: 15%; }
  </style>

</head>

<body>

    <nav class="navbar fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/data/list">List</a>
        <a class="navbar-brand" href="/data/chart">Chart</a>
        <a class="navbar-brand" href="/data/add">Add</a>
        <a class="navbar-brand" href="#" id="logout">Log out</a>
    </nav>


<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center text-gray mb-4">Sensor Data</h2>
            <div class="row">
                <div class="col-md-6 mx-auto">

                    <div class="card rounded-0">
                        <div class="card-header">
                            <#if data.id != -1>
                                <h3 class="mb-0">Edit data with id: ${data.id}</h3>
                            <#else>
                                <h3 class="mb-0">Add data</h3>
                            </#if>
                        </div>
                        <div class="card-body">
                            <#if data.id != -1>
                                <form class="form" id="form" method="post" action=".">
                            <#else>
                                <form class="form" id="form" method="post" action="./edit">
                            </#if>

                                <div class="form-group">
                                 <input name="id" class="element text medium" type="hidden" maxlength="255" value= "${data.id}"/> 
                                </div>

                                <div class="form-group">
                                    <label>Time</label>
                                    <input type="time" class="form-control input-lg rounded-0" name="date" id="time" value="${data.date}" >
                                </div>
                                <div class="form-group">
                                    <label>Temperature</label>
                                    <font color="lightgray" size="2"><i>Only integers in range 0 to 45</i></font>
                                    <input type="number" class="form-control input-lg rounded-0" name ="temp" id="temp" value="${data.temp}">
                                </div>
                                <div class="form-group">
                                    <label>Humidity</label>
                                    <font color="lightgray" size="2"><i>Only integers in range 40 to 100</i></font>
                                    <input type="number" class="form-control input-lg rounded-0" name="hum" id="hum" value="${data.hum}">
                                </div>
                            </form>
                            <button class="btn btn-success btn-lg btn-block" id="send_btn">Add to database</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    
<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
      
          <!-- Modal content-->
          <div class="modal-content">
            <div class="modal-header">
                    <h4 class="modal-title">Error value</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
              
            </div>
            <div class="modal-body">
                <label id="time_modal">Time</label><br>
                <label id="temp_modal">Temperature</label><br>
                <label id="hum_modal">Humidity</label>
            </div>
          </div>
      
        </div>
      </div>


    
    <script src="/assets/bootstrap/js/popper.js"></script>
    <script src="/assets/bootstrap/js/bootstrap.min.js"></script>

    <script src="/assets/select2/select2.min.js"></script>

    <script src="/assets/perfect-scrollbar/perfect-scrollbar.min.js"></script>
    <script>
        $('.js-pscroll').each(function () {
            var ps = new PerfectScrollbar(this);

            $(window).on('resize', function () {
                ps.update();
            })
        });

         $('#logout').on('click', function (e) {
             var d = new Date(); //Create an date object
             d.setTime(d.getTime() - (1000*60*60*24)); //Set the time to the past. 1000 milliseonds = 1 second
            var expires = "expires=" + d.toGMTString(); //Compose the expirartion date
            window.document.cookie = cname+"="+"; "+expires;//Set the cookie with name and the expiration date
            console.log("DELETE");
         });

        $('#send_btn').on('click', function (e) {

            var modal = document.getElementById('myModal');
            var correctData = 0;

            if ($("#time").val())
            {
                console.log("Cas: "+$("#time").val());
                $("#time").toggleClass('alert-danger', false);
                $("#time").toggleClass('alert-success', true);
                $("#time_modal").text("Time : "+ $("#time").val());
                correctData = correctData + 1;
            }else{
                console.log("Cas: nezadany");
                $("#time").toggleClass('alert-danger', true);
                $("#time").toggleClass('alert-success', false);
                $('#myModal').modal('toggle');
                $('#myModal').modal('show');
                $("#time_modal").text("Time : empty");
            }

            var temp = $("#temp").val();
            if (temp)
            {   
                temp = parseInt(temp);
                if(temp>=0 && temp <= 45)
                {
                    console.log("Teplota: "+temp);
                    $("#temp").toggleClass('alert-danger', false);
                    $("#temp").toggleClass('alert-warning', false);
                    $("#temp").toggleClass('alert-success', true);
                    $("#temp_modal").text("Temperature : "+temp);
                    correctData = correctData + 1;
                }else{
                    console.log("Teplota: nie je v rozsahu");
                    $("#temp").toggleClass('alert-danger', false);
                    $("#temp").toggleClass('alert-warning', true);
                    $("#temp").toggleClass('alert-success', false);
                    $('#myModal').modal('toggle');
                    $('#myModal').modal('show');
                    $("#temp_modal").text("Temperature : out of range");
                }
            }else{
                console.log("Teplota: nezadana");
                $("#temp").toggleClass('alert-danger', true);
                $("#temp").toggleClass('alert-warning', false);
                $("#temp").toggleClass('alert-success', false);
                $('#myModal').modal('toggle');
                $('#myModal').modal('show');
                $("#temp_modal").text("Temperature : empty");
            }

            var hum = $("#hum").val();
            if (hum)
            {   
                hum = parseInt(hum);
                if(hum>=40 && hum <= 100)
                {
                    console.log("Vlhkost: "+hum);
                    $("#hum").toggleClass('alert-danger', false);
                    $("#hum").toggleClass('alert-warning', false);
                    $("#hum").toggleClass('alert-success', true);
                    $("#hum_modal").text("Humidity : "+hum);
                    correctData = correctData + 1;
                }else{
                    console.log("Vlhkost: nie je v rozsahu");
                    $("#hum").toggleClass('alert-danger', false);
                    $("#hum").toggleClass('alert-warning', true);
                    $("#hum").toggleClass('alert-success', false);
                    $('#myModal').modal('toggle');
                    $('#myModal').modal('show');
                    $("#hum_modal").text("Humidity : out of range");
                }
            }else{
                console.log("Vlhkost: nezadana");
                $("#hum").toggleClass('alert-danger', true);
                $("#hum").toggleClass('alert-warning', false);
                $("#hum").toggleClass('alert-success', false);
                $('#myModal').modal('toggle');
                    $('#myModal').modal('show');
                    $("#hum_modal").text("Humidity : empty");
            }
            
            console.log("--------------------------");

            if(correctData === 3)
            {
                document.getElementById("form").submit();
            }
        })
    </script>

    <script src="/assets/js/main.js"></script>

</body>

</html>