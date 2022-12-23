<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Collections"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="com.evoting.dto.resultDto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>E-Voting Result</title>
  <meta content="" name="description">

  <meta content="" name="keywords">

  <!-- Favicons -->
  <link href="assets/img/favicon.png" rel="icon">
  <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

  <!-- Vendor CSS Files -->
  <link href="assets/vendor/aos/aos.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
  <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
  <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
  <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

  <!-- Template Main CSS File -->
  <link href="assets/css/style.css" rel="stylesheet">
</head>

<body>
		  <!-- ======= Header ======= -->
  <header id="header" class="header fixed-top">
    <div class="container-fluid container-xl d-flex align-items-center justify-content-between">

      <a href="result.jsp" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span>E-Voting Result</span>
      </a>
<!-- .navbar -->
      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="getstarted scrollto" href="index.html">Home</a></li>
        </ul>
        
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav> </div>
      </header>
      
      
    <% Map<Integer, List<resultDto>> list = request.getAttribute("result") != null ? ((Map<Integer, List<resultDto>>) request.getAttribute("result"))
    		: new HashMap(); 	

    		%>
      <br> <br> <br><br>
       <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
        <% if(request.getAttribute("message")!=null){%>
      <h4 class=" container-fluid container-xl d-flex align-items-center" > <%=request.getAttribute("message") %></h4><%} %></div>
      <%
      for ( Map.Entry<Integer, List<resultDto> > entry : list.entrySet()) {
    	  Integer key = entry.getKey();
    	    List<resultDto> resultDtoList = entry.getValue();
    	    // do something with key and/or tab
    	
      
      %>
  
      <div class="container-fluid container-xl d-flex align-items-center justify-content-between">
      
      <div class="card" style="width: 18rem;">
  <!--<img class="card-img-top" src="..." alt="Card image cap">-->
  

  <div class="card-body"><% List <resultDto> employeesSortedList1 = resultDtoList.stream().sorted((o1, o2) -> 
  (int)(o2.getVote_count() - o1.getVote_count())).collect(Collectors.toList());
       
 %>

 
  		
    <h5 class="card-title">Election Name:<%=resultDtoList.get(0).getElection_name() %> </h5>
    
    <p class="card-text"> 
    		Winner: <i class="bi bi-trophy"><%=resultDtoList.get(0).getName() %></i> </p>
  </div>
  
   
  <ul class="list-group list-group-flush">
  <%for(resultDto dto:resultDtoList){
    	  
    	  %>
    <li class="list-group-item"><%=dto.getName() %> &emsp;&emsp; <%=dto.getVote_count() %></li><%} %>
  </ul> <div class="card-body">
    <a href="Election?method=result" class="card-link">Refresh</a>
    
  </div>
</div>
    <% }%>
      </div>
     
        <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="js/demo/chart-area-demo.js"></script>
    <script src="js/demo/chart-pie-demo.js"></script>

</body>

</html>
      
      
      
      
      