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
<script src="<c:url value="/resources/js/dashboard.js" />"></script>
<title>Admin Dashboard</title>
</head>


<body id="body-pd">

	<!-- Header -->
    <header class="header" id="header">
        <div class="container text-center">
            <div class="row h-100">
                <div class="col col-3"></div>
                <div class="col col-6 my-auto">
                    <h3 class="">Admin Dashboard</h3>
                </div>
                <div class="col col-3 h-100 text-end">
                    <span class=""><i class='bx bxs-user m-1'></i>
                        ${user.userName}
                    </span>
                    <br>
                    <small class="">Admin ID: ${user.a_id}</small>
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
						href="admindashboard" class=" nav_link"> <i
						class='bx bx-grid-alt nav_icon'></i> <span class="nav_name">Dashboard</span>
					</a> <a href="addadmin" class="nav_link"> <i
						class='bx bx-user-plus nav_icon'></i> <span class="nav_name">Add
							Admin</span>
					</a> <a href="adddoctor" class="nav_link"> <i
						class='bx bx-user-plus nav_icon'></i> <span class="nav_name">Add
							Doctor</span>
					</a> <a href="report" class="nav_link"> <i class='bx bxs-report'></i>
						<span class="nav_name">Generate report</span>
					</a> <a href="editdoctor" class=" nav_link active"> <i
						class='bx bx-edit nav_icon'></i> <span class="nav_name">Edit
							Doctor</span>
					</a>
					<a href="changepassword" class=" nav_link"> <i
						class='bx bx-edit nav_icon'></i> <span class="nav_name">Change
							Password</span>
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
		<div
		class="container body-container text-center row d-flex justify-content-center">
		<h4>ALL DOCTORS</h4>
		<hr>

		<!-- Appointments Table -->
		<div class="table-responsive p-2 m-2">
			<table class="table table-style table-hover table-striped text-center">
					<thead class="thead-blue">
						<tr>
							<th scope="col">Doctor ID</th>
							<th scope="col">Doctor name</th>
							<th scope="col">Specialization</th>
							<th scope="col">Fees</th>
							<th scope="col">Status</th>
							<th scope="col">Edit</th>
							
						</tr>
					</thead>
					<tbody>
						<tr>
							<c:forEach items="${allDoctors}" var="doctor">
								<tr>
									<td>${doctor.doc_id }</td>
									<td>${doctor.name }</td>
									<td>${doctor.specialisation}</td>
									<td>Rs ${doctor.fees }</td>
									<td>${doctor.status }</td>
									<td>
									<form action="editform">
										<div class="form-group d-none">
											<input type="hidden" value="${doctor.doc_id }" name="doc_id">
										</div>
										<button type="submit" class="btn-sm search-btn text-white"><i class='bx bxs-edit'></i> Edit</button>
									</form>
								</td>
								</tr>
							</c:forEach>
						</tr>
					</tbody>
				</table>

		</div>
	</div>

	</div>
	<!--Container Main end-->
</body>

</html>