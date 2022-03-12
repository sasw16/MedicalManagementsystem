<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/dashboard.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/register.css" />"
	rel="stylesheet">



<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
	
	

 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" 
crossorigin="anonymous">
	
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
	
<script type="text/javascript" src="<c:url value="/resources/js/dashboard.js" />"></script>

<title>Patient Dashboard</title>
</head>


<body id="body-pd">



   <h1 id="flag" style="display:none;">${message}</h1>

	<!-- Header -->
    <header class="header" id="header">
        <div class="container text-center">
            <div class="row h-100">
                <div class="col col-3"></div>
                <div class="col col-6 my-auto">
                    <h3 class="">Patient Dashboard</h3>
                </div>
                <div class="col col-3 h-100 text-end">
                    <span class=""><i class='bx bxs-user m-1'></i>
                        ${user.name}
                    </span>
                    <br>
                    <small class="">Patient ID: ${user.pid}</small>
                </div>
            </div>
        </div>
    </header>
	<!-- Sidebar -->
	<div class="l-navbar" id="nav-bar">
		<nav class="nav">
			<div>
				<div class="nav_list">
					<a class="header_toggle nav_link"> <i
						class='bx bx-menu nav_icon' id="header-toggle"></i></a> <a
						href="patdash" class=" nav_link active"> <i
						class='bx bx-grid-alt nav_icon'></i> <span class="nav_name">Dashboard</span>
					</a> <a href="patientprofile" class="nav_link"> <i
						class='bx bx-user nav_icon'></i> <span class="nav_name">My
							Profile</span>
					</a> <a href="editpatient" class=" nav_link"> <i
						class='bx bx-edit nav_icon'></i> <span class="nav_name">Edit
							Profile</span>
					</a> <a href="doctorlist" class="nav_link"> <i
						class='bx bx-group nav_icon'></i> <span class="nav_name">Doctors</span>
					</a> <a href="addappointment" class="nav_link"> <i
						class='bx bx-calendar nav_icon'></i> <span class="nav_name">Book
							Appointment</span>
					</a>
				</div>
			</div>
			<a href="logout" class="nav_link"> <i
				class='bx bx-log-out nav_icon'></i> <span class="nav_name">Logout</span>
			</a>
		</nav>
	</div>
	<!-- Sidebar End -->

	<!--Container Main start-->
	<div
		class="container body-container text-center justify-content-md-center pt-3">
		

		<h4 class="">UPCOMING APPOINTMENTS</h4>
		<hr>

		<small>You can not cancel an appointment which is scheduled in
			the next 24 hours.</small>
		<c:if test="${error}">
			<div class="error-text">
				<small>Cannot Cancel</small>
			</div>
		</c:if>
		<c:if test="${success}">
			<div class="error-text bg-success mt-2">
				<small>Cancelled Successfully</small>
			</div>
		</c:if>

		<!-- Appointments Table -->
		<div class="table-responsive p-2 m-2">
			<table class="table table table-style table-hover table-striped text-center">
				<thead class="thead-blue">
					<tr>
						<th scope="col">Date</th>
						<th scope="col">Slot(hh-mm)</th>
						<th scope="col">Doctor name</th>
						<th scope="col">Specialisation</th>
						<th scope="col">Fees</th>
						<th scope="col">Cancel</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${allAppointments}" var="appointment">
							<tr>
								<td>${appointment.date }</td>
								<td>${appointment.slot}</td>
								<td>${appointment.doctorName }</td>
								<td>${appointment.specialisation}</td>
								<td>Rs ${appointment.fees}</td>
								
								<td>
									<form action="cancelappointment">
										<div class="form-group d-none">
											<input type="hidden" value="${appointment.ap_id }"
												name="ap_id">
										</div>
										<button type="submit" class="btn-sm btn-danger"><i class='bx bxs-trash'></i> Cancel</button>
									</form>
								</td>

							</tr>
						</c:forEach>
					</tr>
				</tbody>
			</table>

		</div>

	</div>
	
	
	
	
		<!-- Button trigger modal -->
<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal" id="button1" style="display:none;">
  Launch demo modal
</button>

		<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Review your previous appointment</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p>Review your appointment with ${reviewappointment.doctorName }</p>
          <span  onmouseover="starmark(this)" onclick="starmark(this)" id="1one" style="font-size:40px;cursor:pointer;"  class="fa fa-star checked"></span>
            <span onmouseover="starmark(this)" onclick="starmark(this)" id="2one"  style="font-size:40px;cursor:pointer;" class="fa fa-star "></span>
            <span onmouseover="starmark(this)" onclick="starmark(this)" id="3one"  style="font-size:40px;cursor:pointer;" class="fa fa-star "></span>
            <span onmouseover="starmark(this)" onclick="starmark(this)" id="4one"  style="font-size:40px;cursor:pointer;" class="fa fa-star"></span>
            <span onmouseover="starmark(this)" onclick="starmark(this)" id="5one"  style="font-size:40px;cursor:pointer;" class="fa fa-star"></span>
      </div>
      <div class="modal-footer">
      <form action="editreview" method="post" autocomplete="off">
		<input type="hidden" id="myField" value="" name="review">
		<input type="hidden" value="${reviewappointment.ap_id }" name="ap_id">
		<button type="submit" class="btn btn-primary" onclick="result()">Save changes</button>
	  </form>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
       
      </div>
    </div>
  </div>
</div>
	
	

 <script type="text/javascript">


        var count;
        var num=document.getElementById("flag").innerHTML;
        
        function starmark(item)
        {
        count=item.id[0];
        sessionStorage.starRating = count;
        var subid= item.id.substring(1);
        for(var i=0;i<5;i++)
        {
        if(i<count)
        {
        document.getElementById((i+1)+subid).style.color="orange";
        }
        else
        {
        document.getElementById((i+1)+subid).style.color="black";
        }
        
        
        }
        
        }
        
        function result()
        {
        //Rating : Count
        //Review : Comment(id)
        alert("You have reviewed: "+count);
        document.getElementById('myField').value = count;
        }
        setTimeout("CallButton(num)",2000)
        function CallButton(a)
        {
           if(a==1)
           document.getElementById("button1").click();   
        }
        </script>

</body>

</html>