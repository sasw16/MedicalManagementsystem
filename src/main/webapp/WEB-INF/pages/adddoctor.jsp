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
<link href="<c:url value="/resources/css/register.css" />"
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
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<!-- Header -->
	<header class="header" id="header">
		<div class="container text-center card-register-2">
			<div class="row h-100">
				<div class="col col-3"></div>
				<div class="col col-6 my-auto">
					<h3 class="">Admin Dashboard</h3>
				</div>
				<div class="col col-3 h-100 text-end">
					<span class=""><i class='bx bxs-user m-1'></i>
						${user.userName} </span> <br> <small class="">Admin
						ID: ${user.a_id}</small>
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
					</a> <a href="adddoctor" class="nav_link active"> <i
						class='bx bx-user-plus nav_icon'></i> <span class="nav_name">Add
							Doctor</span>
					</a> <a href="report" class="nav_link"> <i class='bx bxs-report'></i>
						<span class="nav_name">Generate report</span>
					</a> <a href="editdoctor" class=" nav_link"> <i
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
	<div class="container row d-flex text-center justify-content-md-center">
		<div class="col col-lg-7 col-md-9 col-sm-10 mt-4">
			<div class="card  p-2">
				<div class="card-title mt-4">
					<h4>Add Doctor</h4>
					<small>You can add a new doctor here.</small>
				</div>
				<c:if test="${success}">
					<div class="text-success">
						<small>Added Successfully</small>
					</div>
				</c:if>
				<c:if test="${error}">
					<div class="error-text">
						<small>Unable to add doctor. Doctor with same email already exists.</small>
					</div>
				</c:if>
				<div class="card-content text-start">
					<!-- Doctor addition form -->
					<form action="addnewdoctor" method="post" autocomplete="off"
						enctype="multipart/form-data">
						<div class="form-group mb-3 col-md-6">
							<label class="control-label" for="name">Name</label> <input
								type="text" class="form-control" id="name" name="name"
								placeholder="Enter the name of the doctor"
								pattern="[A-Za-z\s\.]{3,40}"
								title="Name must contain a minimum of 3 alphabets and a maximum of 50 alphabets"
								required>
						</div>
						<div class="row form-row">
							<div class="form-group mb-3 col-md-6">
								<label class="control-label" for="email">Email</label> <input
									type="email" class="form-control" id="email" name="email"
									placeholder="Enter your email"
									pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
							</div>
							<div class="form-group col-md-6">
								<label class="control-label" for="phone">Phone</label> <input
									type="tel" class="form-control" id="phone" name="phone"
									pattern="[0-9]{10}" title="Phone number must contain 10 digits"
									placeholder="Enter your phone number" required>
							</div>
						</div>
						<div class="row form-row mb-2">
							<div class="form-group mb-2 col-md-5">
								<label class="control-label" for="specialisation">Specialisation</label>
								<div>
									<select name="specialisation" id="specialisation" required>
										<option value="General Physician">General Physician</option>
										<option value="ENT">ENT</option>
										<option value="Cardiologist">Cardiologist</option>
										<option value="Neurologist">Neurologist</option>
									</select>
								</div>
							</div>
							<div class="col-md-1"></div>
							<div class="form-group mb-2 col-md-6">
								<label class="control-label" for="fees">Fees</label> <input
									type="number" min="1" class="form-control" id="fees" name="fees"
									placeholder="Enter the fees of the doctor" required>
							</div>
						</div>
						
						<div class="row form-row">
							<div class="form-group col-md-4">
								<label class="control-label" for="age">Age</label> <input
									type="number" class="form-control" id="age" name="age"
									placeholder="Enter your age" min="1" max="120" required>
							</div>
							<div class="col-md-2"></div>
							<div class="form-group col-md-6">
								<label class="control-label" for="gender">Gender</label>
								<div class="row radio-group">
									<div class="form-check col-5">
										<input class="form-check-input" type="radio" name="gender"
											id="male" value="Male" required> <label
											class="form-check-label" for="male"> Male </label>
									</div>
									<div class="form-check col-5">
										<input class="form-check-input" type="radio" name="gender"
											id="female" value="Female" required> <label
											class="form-check-label" for="female"> Female </label>
									</div>
								</div>
							</div>
						</div>

						<div class="row form-row">
							<div class="form-group mb-2 col-md-6">
								<label for="image">Image</label> <input type="file"
									accept=".png, .jpg, .jpeg" class="form-control" id="image"
									name="image">
							</div>
	
	
							<div class="form-group mb-2 col-md-6">
								<label class="control-label" for="password">Password</label> <input
									type="password" class="form-control" name="password"
									id="password" placeholder="Password"
									pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
									title="Must contain at least one  number and one uppercase and lowercase letter, 
	  								and at least 8 or more characters"
									required>
							</div>
						</div>

							
							<div class="form-group d-none">
								<input type="hidden" value="${user.a_id }" name="user_id">
							</div>

							<button type="submit" class="btn btn-login w-100 mt-4 mb-2">ADD
								DOCTOR</button>
					</form>
				</div>
			</div>

		</div>


	</div>
	<!--Container Main end-->
</body>

</html>