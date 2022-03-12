<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/dashboard.css" />" rel="stylesheet"> 
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous">
        </script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script src="<c:url value="/resources/js/dashboard.js" />"></script>
    <title>Doctor Dashboard</title>
</head>


<body id="body-pd">

	<!-- Header -->
    <header class="header" id="header">
        <div class="container text-center">
            <div class="row h-100">
                <div class="col col-3"></div>
                <div class="col col-6 my-auto">
                    <h3 class="">Doctor Dashboard</h3>
                </div>
                <div class="col col-3 h-100 text-end">
                    <span class=""><i class='bx bxs-user m-1'></i>
                        ${user.name}
                    </span>
                    <br>
                    <small class="">Doctor ID: ${user.doc_id}</small>
                </div>
            </div>
        </div>
    </header>
    <!-- Sidebar -->
    <div class="l-navbar" id="nav-bar">
        <nav class="nav">
            <div>
                <div class="nav_list">
                    <a class="header_toggle nav_link"> <i class='bx bx-menu nav_icon' id="header-toggle"></i></a>
                    <a href="doctordashboard" class=" nav_link active"> <i class='bx bx-grid-alt nav_icon'></i>
                        <span class="nav_name">Dashboard</span> </a>
                    <a href="doctorprofile" class="nav_link"> <i class='bx bx-user nav_icon'></i> <span
                            class="nav_name">My
                            Profile</span> </a>
                    <a href="changepassworddoctor" class=" nav_link"> <i
						class='bx bx-edit nav_icon'></i> <span class="nav_name">Change
							Password</span>
					</a>
                </div>
            </div>
            <a href="logout" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> <span class="nav_name">Logout</span>
            </a>
        </nav>
    </div>
    <!-- Sidebar End -->


    <!--Container Main start-->
    <div class="container body-container text-center justify-content-center align-items-center">
        <h4>UPCOMING APPOINTMENTS</h4>
        <hr>
        <c:if test="${allAppointments == null || allAppointments.size()==0}">
        	<h5>Currently no appointments are scheduled for you</h5>
        </c:if>

		
        <!-- Appointments Table -->
        <c:if test="${allAppointments != null && allAppointments.size()>0}">
       <div class="table-responsive p-2 m-2 w-50 mx-auto">
			<table class="table table-style table-hover table-striped text-center">
				<thead class="thead-blue">
					<tr>
						<th scope="col">Date</th>
						<th scope="col">Slot</th>
						
						<th scope="col">Patient Name</th>
						
					</tr>
				</thead>
				<tbody>
					<tr>
						<c:forEach items="${allAppointments}" var="appointment">
							<tr>
								<td>${appointment.date }</td>
								<td>${appointment.slot}</td>
								
								<td>${appointment.patientName }</td>

							</tr>
						</c:forEach>
					</tr>
				</tbody>
			</table>

		</div>
		</c:if>
        
    </div>
    <!--Container Main end-->
</body>

</html>