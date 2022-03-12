<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page isELIgnored="false"%>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/boxicons@latest/css/boxicons.min.css" />
    <link href="<c:url value="/resources/css/index.css" />" rel="stylesheet"> 
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <style>
    	body {
    		/* background-image: url('/MedicalManagementSystemMMS/resources/images/error-bg.png');
    		background-attachment: relative;
		    background-repeat: no-repeat;
		    background-size: 100%; */
    	}
    </style>
    <title>Error ${errorCode}</title>
</head>
<body>

    <div class="container h-100">
    
	    <div class="row d-flex justify-content-center align-items-center">
	    		<div class="col col-9 text-center m-5">
	    			<h1 class="display-1 m-5">Oops!</h1>
	    			<h3>Error ${errorCode}</h3>
		    		<h5>${errorMsg}</h5>
		    		
		    		<a class="btn btn-lg btn-blue btn-blue-l m-5" href='<c:url value="/" />'>Go Back to Home Page</a>
		    		
		    		<div class="img-fluid text-end">
	        			<img src="<c:url value="/resources/images/robot.gif"/>" style="height:250px; margin-top: -3rem;">
	        		</div>

	    		</div>
	    </div>
	    
	</div>
</body>
</html>