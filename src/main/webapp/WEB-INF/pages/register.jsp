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
    <title>Register</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/register.css" />" rel="stylesheet"> 
</head>

<body>

    <section>
        <div class="banner">
            <div class="container">
                <div class="col col-lg-6">
                    <h1 class="display-5 text-white">Medical Management System</h1>
                </div>
                <div clas="col col-lg-6"></div>
            </div>
        </div>
    </section>


    <section>
        <div class="container text-center main-content justify-content-md-center">
            <div class="row justify-content-md-center">

                <div class="col col-lg-5 col-md-8 col-sm-10 intro-text">

                    <h3>Welcome User!</h3>
                    <p class="lead">Please register yourself to book appointments with our specialist doctors and manage
                        your
                        upcoming
                        appointments seamlessly.
                    </p>

                    <p>If you are already registered, please <a href='<c:url value="/" />'>login</a> to view your upcoming
                        appointments and book new appointments.</p>

                </div>

                <div class="col-lg-1"></div>


                <div class="col col-lg-6 col-md-8 col-sm-10">

                    <div class="card card-register">
                        <div class="card-title mt-4">
                            <h4>Patient Registration</h4>
                            <small>Register here if you are a new patient.</small>
                        </div>
                        <c:if test="${error}">
                                	<div class="error-text m-1">
                                    Patient already exists!
                                	</div>
								</c:if>
                        <div class="card-content">
                            <!-- Registration form for patient -->
                            <form action="registerpatient" method="post" autocomplete="off">
                                <div class="row form-row">
                                    <div class="form-group col">
                                        <label class="control-label" for="fname">Name</label>
                                        <input type="text" class="form-control" id="fname" name="name"
                                            placeholder="Enter your full name" pattern="[A-Za-z\s]{3,40}" 
                                            title="Name must contain a minimum of 3 alphabets and a maximum of 50 alphabets" required>
                                    </div>
                                </div>

                                <div class="row form-row">
                                    <div class="form-group col-md-6">
                                        <label class="control-label" for="email">Email</label>
                                        <input type="email" class="form-control" id="email" name="email"
                                            placeholder="Enter your email" 
                                            pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$" required>
                                    </div>
                                    <div class="form-group col-md-6">
                                        <label class="control-label" for="phone">Phone</label>
                                        <input type="tel" class="form-control" id="phone" name="phone"
                                        	pattern="[0-9]{10}" title="Phone number must contain 10 digits"
                                            placeholder="Enter your phone number" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label for="address">Address</label>
                                    <input type="textarea" class="form-control" id="address" name="address"
                                        placeholder="Enter your address" pattern=".{10,150}" 
                                        title="Address must contain a minimum of 10 characters and maximum of 150 character">
                                </div>

                                <div class="row form-row">
                                    <div class="form-group col-md-7">
                                        <label class="control-label" for="gender">Gender</label>
                                        <div class="row radio-group">
                                            <div class="form-check col-5">
                                                <input class="form-check-input" type="radio" name="gender" id="male"
                                                    value="Male" required>
                                                <label class="form-check-label" for="male">
                                                    Male
                                                </label>
                                            </div>
                                            <div class="form-check col-5">
                                                <input class="form-check-input" type="radio" name="gender" id="female"
                                                    value="Female" required>
                                                <label class="form-check-label" for="female">
                                                    Female
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-group col-md-5">
                                        <label class="control-label" for="age">Age</label>
                                        <input type="number" class="form-control" id="age" name="age" placeholder="Enter your age"
                                        	min="1" max="120" required>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label" for="password">Password</label>
                                    <input type="password" class="form-control" name="password" id="password" placeholder="Password"
                                        pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
  										title="Must contain at least one  number and one uppercase and lowercase letter, 
  										and at least 8 or more characters" required>
                                </div>
                                <button type="submit" class="btn btn-login w-100 mt-2 mb-2">REGISTER</button>
                            </form>
                            <div class="card-bt">
                                <hr class="m-2">
                                <small>
                                    Already have an account?
                                </small>
                                <br>
                                <a href='<c:url value="/" />'>Login Here</a>
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