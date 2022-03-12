


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/resources/css/bootstrap.min.css" />"
	rel="stylesheet">
<link href="<c:url value="/resources/css/dashboard.css" />"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />





<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
	crossorigin="anonymous">
	
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>

<title>Patient Dashboard</title>
</head>


<body id="body-pd">
	<!-- Header -->
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
						${user.name} </span> <br> <small class="">Patient
						ID: ${user.pid}</small>
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
						href="patdash" class=" nav_link"> <i
						class='bx bx-grid-alt nav_icon'></i> <span class="nav_name">Dashboard</span>
					</a> <a href="patientprofile" class="nav_link"> <i
						class='bx bx-user nav_icon'></i> <span class="nav_name">My
							Profile</span>
					</a> <a href="editpatient" class=" nav_link"> <i
						class='bx bx-edit nav_icon'></i> <span class="nav_name">Edit
							Profile</span>
					</a> <a href="doctorlist" class="nav_link"> <i
						class='bx bx-group nav_icon'></i> <span class="nav_name">Doctors</span>
					</a> <a href="addappointment" class="nav_link active"> <i
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
		class="container body-container text-center justify-content-md-center">
		<div class="row justify-content-md-center">

			<div class="col col-lg-8 col-md-8 col-sm-10">

				<div class="card mb-4">
					<div class="card-title mt-4">
						<h4>Appointment Details</h4>
						<br>
						<h5>${doctor.name }</h5>
						<small>${doctor.specialisation }</small>
					</div>
					<c:if test="${error}">
						<div class="error-text">
							<small>You already have an appointment</small>
						</div>
					</c:if>
					<c:if test="${error1}">
						<div class="error-text">
							<small>You already have an appointment</small>
						</div>
					</c:if>
					<c:if test="${error2}">
						<div class="error-text">
							<small>Slot Not available</small>
						</div>
					</c:if>
					<c:if test="${success}">
						<div class="text-success">
							<h5>Appointment booked successfully</h5>
						</div>
					</c:if>

					<div class="card-content">
						<!-- Login form for doctor -->
						<form action="processappointment" method="post" autocomplete="off">
							
							<c:if test="${show }">
								<h6>Slots Available for ${date }</h6>
								<hr>
								
								    <div class="row d-flex justify-content-center align-items-center">
								        <c:forEach items="${listOfSlots}" var="slots">
								
								            <c:if test="${not empty notAvailable }">
								                <c:if test="${fn:contains(notAvailable,slots) }">
								                    <div class="col col-2" id="${slots}">
								                        <label class="btn btn-danger disabled rounded mb-3 slot-btn-style w-100">
								                            <input type="radio" name="slot" class="d-none" value="${slots }">${slots}
								                        </label>
								                    </div>
								                </c:if>
								                <c:if test="${not fn:contains(notAvailable,slots) }">
								                    <div class="col col-2" id="${slots }">
								                        <label class="btn btn-outline-success rounded slot mb-3 slot-btn-style w-100">
								                            <input type="radio" name="slot" class="d-none" value="${slots }">${slots }
								                        </label>
								                    </div>
								                </c:if>
								            </c:if>
								
								            <c:if test="${empty notAvailable }">
								                <div class="col col-2" id="${slots }">
								                        <label class="btn btn-outline-success rounded slot mb-3 slot-btn-style w-100">
								                            <input type="radio" name="slot" class="d-none" value="${slots }">${slots }
								                        </label>
								                    </div>
								            </c:if>
								
								        </c:forEach>
								
								    </div>

								<div class="form-group d-none">
									<input type="hidden" value="${doctor.doc_id }" name="doc_id">
								</div>
								<div class="form-group d-none">
									<input type="hidden" value="${date }" name="date">
								</div>
								<button type="submit" class="btn btn-login w-100 mt-2 mb-2">Confirm</button>
							</c:if>
						</form>
						<c:if test="${bookanother }">
							<form action="addappointment">
								<input type="submit" class="btn btn-primary px-3"
									value="Book Another Appointment">
							</form>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!--Container Main end-->
	<!-- <script type="text/javascript">
	function selectbtn(id){
		let x = document.getElementById(id);
		let y = document.getElementsByClassName("func");
		
		
		x.classList.add("act");
		y.classList.remove("act");
		console.log(x);
	}
	</script> -->
	<script type="text/javascript"
	src="<c:url value="/resources/js/dashboard.js" />"></script>

</body>

</html>
