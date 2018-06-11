<#-- @ftlvariable name="" type="sk.fri.uniza.microservice.Data" -->
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
   <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <meta http-equiv="refresh" content="15">
      <title>Table</title>
      <link rel="icon" type="image/png" href="/assets/images/icons/favicon.ico"/>
 
	<link rel="stylesheet" type="text/css" href="/assets/bootstrap/css/bootstrap.min.css">

	<link rel="stylesheet" type="text/css" href="/assets/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
 
	<link rel="stylesheet" type="text/css" href="/assets/animate/animate.css">
 
	<link rel="stylesheet" type="text/css" href="/assets/select2/select2.min.css">
 
	<link rel="stylesheet" type="text/css" href="/assets/perfect-scrollbar/perfect-scrollbar.css">
 
	<link rel="stylesheet" type="text/css" href="/assets/css/util.css">
	<link rel="stylesheet" type="text/css" href="/assets/css/main.css">
 
	<link rel="stylesheet" type="text/css" href="css/view.css" media="all">
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.12/css/all.css" integrity="sha384-G0fIWCsCzJIMAVNQPfjH08cyYaUtMwjJwqiRKxxE/rx96Uroj1BtIQ6MLJuheaO9" crossorigin="anonymous">

	<script type="text/javascript" src="/assets/js/view.js"></script>
	<script type="text/javascript" src="/assets/js/jquery-3.3.1.min.js"></script>
       
    <script type="text/javascript">
        function deleteData(id){
           $.ajax({
            url: '/data/'+id,
            type: 'DELETE',
            success: function(result) {
                alert('Item Deleted');
                location.reload();
            }
        }); 
        }
      </script>

    </head>
   <body id="main_body" >

    <nav class="navbar fixed-top navbar-dark bg-dark">
				<a class="navbar-brand" href="/data/list">List</a>
				<a class="navbar-brand" href="/data/chart">Chart</a>
				<a class="navbar-brand" href="/data/add">Add</a>
				<a class="navbar-brand" href="#">Log out</a>	
		</nav>

        <div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
				<div class="table100 ver1 m-b-110">
					<div class="table100-head">
						<table>
							<thead>
								<tr class="row100 head">
									<th class="cell100 column1">ID</th>
									<th class="cell100 column2">Time</th>
									<th class="cell100 column3">Temperature</th>
									<th class="cell100 column4">Humidity</th>
									<th class="cell100 column5">EDIT</th>
									
								</tr>
							</thead>
						</table>
					</div>

					<div class="table100-body js-pscroll">
						<table>
							<#list datas>
							<tbody>
								<#items as data>
								<tr class="row100 body">
									<td class="cell100 column1">${data.id}</td>
									<td class="cell100 column2">${data.date}</td>
									<td class="cell100 column3">${data.temp}</td>
									<td class="cell100 column4">${data.hum}</td>
									<td class="cell100 column5">
									<a href="/data/edit/${data.id}"><i class="fas fa-pencil-alt"></i></a>
									<a href="/data/${data.id}"><i class="fas fa-search"></i></a> 
									<a href="javascript:void(0);" onclick="deleteData(${data.id});"><i class="fas fa-trash-alt"></i></a></td>
								</tr>
								</#items>
							</tbody>
							<#else>
							</tbody>
								<tr class="row100 body">
									<td class="cell100 column1"></td>
									<td class="cell100 column2"></td>
									<td class="cell100 column3"></td>
									<td class="cell100 column4"></td>
									<td class="cell100 column5"></td>
								</tr>
							<tbody>
							</#list>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

    <script src="/assets/bootstrap/js/popper.js"></script>
	<script src="/assets/bootstrap/js/bootstrap.min.js"></script>
 
	<script src="/assets/select2/select2.min.js"></script>
 
	<script src="/assets/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script>
		$('.js-pscroll').each(function(){
			var ps = new PerfectScrollbar(this);

			$(window).on('resize', function(){
				ps.update();
			})
		});
			
		
	</script>
 
	<script src="/assets/js/main.js"></script>

   </body>
</html>