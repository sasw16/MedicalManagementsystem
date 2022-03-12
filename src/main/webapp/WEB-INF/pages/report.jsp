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
                    <a class="header_toggle nav_link"> <i class='bx bx-menu nav_icon' id="header-toggle"></i></a>
                    <a href="admindashboard" class=" nav_link"> <i class='bx bx-grid-alt nav_icon'></i>
                        <span class="nav_name">Dashboard</span> </a>
                    <a href="addadmin" class="nav_link"> <i class='bx bx-user-plus nav_icon'></i> <span
                            class="nav_name">Add Admin</span> </a>
                    <a href="adddoctor" class="nav_link"> <i class='bx bx-user-plus nav_icon'></i> <span
                            class="nav_name">Add Doctor</span> </a>
                    <a href="report" class="nav_link  active"> <i class='bx bxs-report'></i> <span
                            class="nav_name">Generate report</span> </a>
					</a> <a href="editdoctor" class=" nav_link"> <i class='bx bx-edit nav_icon'></i> <span 
							class="nav_name">Edit Doctor</span> </a>                            
                      <a href="changepassword" class=" nav_link"> <i
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
    <div class="container body-container text-center justify-content-md-center">
        <h4 class="mt-4">GENERATE REPORT</h4>
        <hr>

        <p class="lead mb-5">You can generate the weekly report or monthly of a particular doctor in Excel or PDF format.</p>

        <form action="exportdoctors" method="post" autocomplete="off">
            <div class="row d-flex justify-content-center form-row align-items-center mt-5">
                <div class="form-group col-lg-4 col-md-10 m-2">
                    <label for="doctor" class="form-label">Choose Doctor</label>
                    <select id="doctor" class="form-select" name="doc_id" required>
	                    <c:forEach var="doctor" items="${allDoctors}">
	                        <option value="${doctor.doc_id}">${doctor.doc_id} - ${doctor.name} - ${doctor.specialisation}</option>
	                    </c:forEach>
                    </select>
                </div>
                <div class="form-group col-lg-2 col-md-5 m-2">
                    <label for="interval" class="form-label">Report Interval</label>
                    <select id="interval" class="form-select" name="interval" required>
	                        <option value="Weekly">Weekly</option>
	                        <option value="Monthly">Monthly</option>
                    </select>
                </div>
                <div class="form-group col-lg-2  col-md-5 m-2">
                    <label for="type" class="form-label">Report Type</label>
                    <select id="type" class="form-select" name="type" required>
	                        <option value="Excel">Excel</option>
	                        <option value="PDF">PDF</option>
                    </select>
                </div>                
                <div class="form-group col-lg-2 col-md-5 col-sm-10 m-1">
                    <br>
                    <button type="submit" class="btn btn-primary search-btn mt-2">
                        <i class='bx bxs-download'></i> Download
                    </button>
                </div>
            </div>
        </form>
    </div>
    <!--Container Main end-->
</body>

</html>