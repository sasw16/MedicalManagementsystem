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
    <title>Home</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet"> 
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
        <c:if test="${error}">
    	<script>
			var loc = window.location.href;
			window.location.href= loc+"#patient-login";
		</script>
    </c:if>  
</head>

<body>

    <section>
        <div class="banner">
            <div class="container row d-flex align-items-center">
                <div class="col col-lg-4 col-md-3 col-sm-2">
                    <div class="logo text-end">
                        <img src="<c:url value="/resources/images/logo.png"/>" />
                    </div>
                </div>
                <div class="col text-start">
                    <h1 class="display-5 text-white">Medical Management System</h1>
                </div>
            </div>
        </div>
    </section>
	
    <div class="row d-flex float-end home-btn-group">
        <div class="col-5">
            <a href="register" class="btn btn-home">REGISTER</a>
        </div>
        <div class="col-5">
            <a href="#patient-login" class="btn btn-home">LOGIN</a>
        </div>
    </div>  


    <section>

        <div class="carousel">
            <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                <ol class="carousel-indicators">
                    <li data-target="#carouselExampleControls" data-slide-to="0" class="active"></li>
                    <li data-target="#carouselExampleControls" data-slide-to="1"></li>
                    <li data-target="#carouselExampleControls" data-slide-to="2"></li>
                </ol>
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <a href="#patient-login">
                            <img class="d-block w-100" src="<c:url value="/resources/images/slide-1.png"/>" alt="First slide">
                        </a>
                    </div>
                    <div class="carousel-item">
                        <a href="register">
                            <img class="d-block w-100" src="<c:url value="/resources/images/slide-2.png"/>" alt="Second slide">
                        </a>
                    </div>
                    <div class="carousel-item">
                        <a href="#patient-login">
                            <img class="d-block w-100" src="<c:url value="/resources/images/slide-3.png"/>" alt="Third slide">
                        </a>
                    </div>
                </div>
                <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon"></span>
                </a>
                <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                    <span class="carousel-control-next-icon"></span>
                </a>
            </div>
        </div>

        <div class="row text-center m-5">
            <a href="#patient-login">
                <i class='bx bxs-chevrons-down bx-flashing bx-lg'></i>
            </a>
        </div>

		<div class="m-5"><hr></div>



        <div class="container text-center main-content">
            <div class="row d-flex justify-content-center align-items-center" id="patient-login">

                <div class="col col-lg-4 col-md-8 col-sm-10">
                    <!-- Card containing the patient login form -->
                    <div class="card">
                        <div class="card-title mt-4">
                            <h4>Patient Login</h4>
                            <small>Login here if you are a registered patient.</small>
                        </div>
                        <div class="card-content">
                            <!-- Patient login form -->
                            <form action="processlogin" method="post" autocomplete="off">
                            	<!-- Hidden field for role -->
                            	<input type="hidden" value="patient" name="flag">
                            	
                            	
                                <div class="d-none">
                                    
                                    <input type="numeric" class="form-control adminID" name="id" placeholder="Enter ID"
                                        aria-label="Admin Id" aria-describedby="basic-addon1">
                                </div>
                                
                                <div class="input-label text-start">Email</div>
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text h-100" id="basic-addon1"><i
                                        		class='bx bxs-user'></i></span>
                                    </div>
                                    <input type="email" class="form-control username" name="email" placeholder="Email"
                                        aria-label="Email" aria-describedby="basic-addon1" required>
                                </div>
                                <div class="input-label text-start">Password</div>
                                <div class="input-group mb-3">
                                     <div class="input-group-prepend">
                                        <span class="input-group-text username h-100" id="basic-addon1"><i
                                                class='bx bx-key'></i></span>
                                    </div>
                                    <input type="password" class="form-control password" name="password"
                                        placeholder="Password" aria-label="Password" aria-describedby="basic-addon" required> 
                                </div>
                                <!-- Error Message -->
                                <c:if test="${error}">
                                	<div class="error-text">
                                    <small>The email or password entered by you is incorrect. Please try
                                        again!</small>
                                	</div>
								</c:if>
                                <button type="submit" class="btn btn-login w-100 mt-2 mb-2">LOGIN</button>
                            </form>
                            <div class="card-bt">
                                <hr class="m-2">
                                <small>
                                    Don't have an account?
                                </small>
                                <br>
                                <a href="register">Register Here</a>
                            </div>
                        </div>
                    </div>

                </div>


                <div class="col-lg-2"></div>


                <div class="col col-lg-5 col-md-8 col-sm-10 mt-2">
                    <!-- Link to doctor login form -->
                    <div class="row m-4 justify-content-md-center">
                        <h4>Are you a doctor?</h4>
                        <p>Login here if you are a doctor:</p>
                        <a href="doctor" class="btn btn-lg btn-blue mt-3 mb-3">Doctor
                            Login</a>
                    </div>

                    <hr>

                    <!-- Link to doctor login form -->
                    <div class="row m-4 justify-content-md-center">
                        <h4>Are you an admin?</h4>
                        <p>Login here if you are an admin:</p>
                        <a href="admin" class="btn btn-lg btn-blue btn-blue-l mt-3">Admin Login</a>
                    </div>
                </div>

            </div>
        </div>
    </section>

    <!-- Footer -->
    <div class="container-fluid text-center text-white home-footer">
        <div class="row p-2">
            <div class="col-md-4 col-lg-4">
                <small>NRIFT Hospitals Private Limited</small>
            </div>
            <div class="col-md-4 col-lg-4">
                <small>Contact: 1-800-613-8840</small>
            </div>
            <div class="col-md-4 col-lg-4">
                <small>Email: contact@nrifthospitals.org</small>
            </div>
        </div>
    </div>

</body>

</html>