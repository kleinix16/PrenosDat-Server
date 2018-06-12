<#-- @ftlvariable name="" type="sk.fri.uniza.microservice.Saying" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="refresh" content="15">
    <title>Chart</title>

    <link rel="stylesheet" type="text/css" href="/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/assets/css/main.css">
    <script type="text/javascript" src="/assets/js/jquery-3.3.1.min.js"></script>

    <style type="text/css">
        body {
            padding-top: 10%;
        }
    </style>

    <script src="/assets/chart/Chart.js"></script>
    <script src="/assets/chart/Chart.bundle.js"></script>

</head>

<body id="main_body">

    <nav class="navbar fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/data/list">List</a>
        <a class="navbar-brand" href="/data/chart">Chart</a>
        <a class="navbar-brand" href="/data/add">Add</a>
        <a class="navbar-brand" href="https://github.com/kleinix16/PrenosDat-Server">GITHUB</a>
    </nav>

    <div class="container">
        <div class='row'>
            <div class="col">
                <canvas id="tempChart"></canvas>
            </div>
            <div class="col">
                <canvas id="humChart"></canvas>
            </div>
        </div>
    </div>



    <script>
            var lables = [];
            var dataTemp = [];
            var dataHum = [];

       <#list datas>
            <#items as data>
                lables.push("${data.date}");
                dataTemp.push(${data.temp});
                dataHum.push(${data.hum});
            </#items>          
        </#list>

        if (lables.length > 10){
            console.log(lables.length);
            lables = lables.slice(Math.max(lables.length - 10, 1))
            dataTemp = dataTemp.slice(Math.max(dataTemp.length - 10, 1))
            dataHum = dataHum.slice(Math.max(dataHum.length - 10, 1))
        }

        var tempChart = new Chart(document.getElementById("tempChart"), {
            "type": "line",
            "data": {
                "labels": lables,
                "datasets": [{
                    "label": "Temperature °C",
                    "data": dataTemp,
                    "fill": true,
                    "backgroundColor": "rgba(255,7 , 7, 0.2)",
                    "borderColor": "rgb(200,7,7)",
                    "lineTension": 0.4
                }]
            },
            "options": {
                "animation": false
            }
        });

        var humChart = new Chart(document.getElementById("humChart"), {
            "type": "line",
            "data": {
                "labels": lables,
                "datasets": [{
                    "label": "Humidity %",
                    "data": dataHum,
                    "fill": true,
                    "backgroundColor": "rgba(12,141,184, 0.2)",
                    "borderColor": "rgb(12,141,184)",
                    "lineTension": 0.4
                }]
            },
            "options": {
                "animation": false
            }
        });

    </script>


</body>

</html>