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
                    <a href="doctordashboard" class=" nav_link"> <i class='bx bx-grid-alt nav_icon'></i>
                        <span class="nav_name">Dashboard</span> </a>
                    <a href="doctorprofile" class="nav_link active"> <i class='bx bx-user nav_icon'></i> <span
                            class="nav_name">My
                            Profile</span> </a>
                </div>
            </div>
            <a href="logout" class="nav_link"> <i class='bx bx-log-out nav_icon'></i> <span class="nav_name">Logout</span>
            </a>
        </nav>
    </div>
    <!-- Sidebar End -->


    <!--Container Main start-->
    <div class="container row d-flex body-container text-center justify-content-md-center">
        <h4>YOUR PROFILE</h4>
        <hr>

        <div class="card col-4 m-2 profile-card text-white">
            <div class="card-body mt-4">
                <svg xmlns="http://www.w3.org/2000/svg" width="84" height="84" viewBox="0 0 24 24" style="fill: #fff">
                    <path
                        d="M7.5 6.5C7.5 8.981 9.519 11 12 11s4.5-2.019 4.5-4.5S14.481 2 12 2 7.5 4.019 7.5 6.5zM20 21h1v-1c0-3.859-3.141-7-7-7h-4c-3.86 0-7 3.141-7 7v1h17z">
                    </path>
                </svg>

                <h4 class="card-title mt-2 mb-4">${user.name }</h4>
                <div class="card-text mb-5">
                    <div class="mt-2">Doctor ID: ${user.doc_id }</div>
                    <div class="mt-2">Specialisation: ${user.specialisation }</div>
                    <div class="mt-2">Fees: ${user.fees }</div>
                </div>
            </div>
        </div>

    </div>
    <!--Container Main end-->
</body>

</html>