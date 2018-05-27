<#-- @ftlvariable name="" type="sk.fri.uniza.microservice.Data" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Date from Sensor</title>

    <link rel="stylesheet" type="text/css" href="../../../../assets/bootstrap/css/bootstrap.min.css">

    <link rel="stylesheet" type="text/css" href="../../../../assets/css/main.css">

    <script type="text/javascript" src="../../../../assets/js/jquery-3.3.1.min.js"></script>

    <link rel="stylesheet" href="../../../../assets/css/material-design-iconic-font.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../../../assets/css/jquery.circliful.css">

    <style type="text/css">
        body {
            padding-top: 10%;
        }
    </style>

</head>

<body id="main_body">

    <nav class="navbar fixed-top navbar-dark bg-dark">
        <a class="navbar-brand" href="/data/list">List</a>
        <a class="navbar-brand" href="/data/chart">Chart</a>
        <a class="navbar-brand" href="/data/add">Add</a>
        <a class="navbar-brand" href="#">Log out</a>
    </nav>

    <section class="container">

        <font size="10"><center> ${data.date}</center></font>
        <font size="5" color="gray"><center>ID: ${data.id}</center></font>

        <div class="row">
            <div class="col-lg-6">
                <div id="temp"></div>
            </div>
            <div class="col-lg-6">
                <div id="hum"></div>
            </div>
        </div>

    </section>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="../../../../assets/js/jquery.circliful.js"></script>
    <script>

        var temp = ${data.temp};
        var hum = ${data.hum};

        $(document).ready(function () { // 6,32 5,38 2,34
            $("#temp").circliful({
                animationStep: 10,
                foregroundBorderWidth: 12,
                backgroundBorderWidth: 15,
                backgroundColor: "#E0E0E0",
                foregroundColor: "#FF3333",
                percent: temp * 2.2,
                icon: 'f185',
                fontColor: "#202020",
                replacePercentageByText: temp + " °C",
                halfCircle: 1,
            });
            $("#hum").circliful({
                animationStep: 10,
                foregroundBorderWidth: 12,
                backgroundBorderWidth: 15,
                backgroundColor: "#E0E0E0",
                foregroundColor: "#0080FF",
                percent: (hum-40) * 1.66,
                icon: 'f043',
                fontColor: "#202020",
                replacePercentageByText: hum + " %",
                halfCircle: 1,
            });

        });
    </script>



</body>

</html>