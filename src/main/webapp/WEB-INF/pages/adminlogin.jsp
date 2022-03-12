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


    <section>
        <div class="container text-center main-content justify-content-md-center mb-5">
            <div class="row justify-content-md-center">

                <div class="col col-lg-4 col-md-8 col-sm-10">

                    <div class="card mb-4">
                        <div class="card-title mt-4">
                            <h4>Admin Login</h4>
                            <small>Login here if you are an admin.</small>
                        </div>
                        <div class="card-content">
                            <!-- Login form for doctor -->
                            <form action="processlogin" method="post" autocomplete="off">
                            	<!-- Hidden field for role -->
                            	<input type="hidden" value="admin" name="flag">
                                <div class="input-label text-start">Admin ID</div>
                                <div class="input-group mb-4">
                                    <div class="input-group-prepend">
                                        <span class="input-group-text h-100" id="basic-addon1"><i
                                        		class='bx bxs-user'></i></span>
                                    </div>
                                    <input type="number" class="form-control adminID" name="email" placeholder="Enter ID"
                                        aria-label="Admin Id" aria-describedby="basic-addon1" required>
                                </div>
                                

                                <div class="input-label text-start">Password</div>
                                <div class="input-group mb-4">
                                     <div class="input-group-prepend">
                                        <span class="input-group-text username h-100" id="basic-addon1"><i
                                                class='bx bx-key'></i></span>
                                    </div>
                                    <input type="password" class="form-control password" name="password"
                                        placeholder="Password" aria-label="Password" aria-describedby="basic-addon" required>
                                </div>
                                <c:if test="${error}">
                                	<div class="error-text">
                                    <small>The admin id or password entered by you is incorrect. Please try
                                        again!</small>
                                	</div>
								</c:if>
                           
                                <button type="submit" class="btn btn-login w-100 mt-2 mb-2">LOGIN</button>
                            </form>
                            <div class="card-bt">
                                <hr class="m-2">
                                <small>
                                    Not an admin?
                                </small>
                                <br>
                                <a href='<c:url value="/" />'>Go to home page</a>
                            </div>
                        </div>
                    </div>

                </div>

            </div>
        </div>
    </section>

    <!-- Footer -->
    <div class="container-fluid text-center text-white footer">
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