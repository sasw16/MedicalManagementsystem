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
        <div class="container text-center main-content justify-content-md-center">
            <div class="row justify-content-md-center mt-5">

			<div>
			
				<h4>Logged Out Successfully...</h4>
				<h6 class="mt-2"><a href='<c:url value="/" />'>Click here</a> to login again</h6>
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