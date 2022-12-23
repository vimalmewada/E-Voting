<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.evoting.dto.CreateElectionDto"%>
<%@page import="java.util.List"%>
<%@page import="com.evoting.dto.Voter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta content="width=device-width, initial-scale=1.0" name="viewport">

  <title>FlexStart Bootstrap Template - Index</title>
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

      <a href="evoting.jsp" class="logo d-flex align-items-center">
        <img src="assets/img/logo.png" alt="">
        <span>E-Voting</span>
      </a>

      <nav id="navbar" class="navbar">
        <ul>
          <li><a class="nav-link scrollto active" href="#hero">Home</a></li>
           <li><a class="getstarted scrollto" href="LogOut">LogOut</a></li>
        </ul>
        <i class="bi bi-list mobile-nav-toggle"></i>
      </nav><!-- .navbar -->

    </div>
  </header><!-- End Header -->
  
  <%
	 Voter voter = (session.getAttribute("currentSessionUser"))!= null ? (Voter)session.getAttribute("currentSessionUser") : null; 
	if(voter==null){
		 response.sendRedirect("index.html");
	}
	Map <Integer,Boolean> duplicateCheckMap=request.getAttribute("duplicateCheckMap")!=null? (Map <Integer,Boolean>)request.getAttribute("duplicateCheckMap"):null;
  
  
  List<CreateElectionDto> list = request.getAttribute("electionList") != null? ((List<CreateElectionDto>) request.getAttribute("electionList")): new ArrayList();
 
  
  %>
  
  <br><br><br><br>
   <h3  style="color:darkcolor;" class="container-fluid container-xl d-flex align-items-center justify-content-between" >Election Name</h3>
  <div class="container-fluid container-xl d-flex align-items-center justify-content-between">

  <div class="row">
 	
  <%
		for (CreateElectionDto createElectionDto : list) {
			%>
  <div class="col-sm-6">
    <div class="card" style="width: 18rem;" >
      <div class="card-body">
        <h5 class="card-title"><%=createElectionDto.getElection_name()%> </h5>
        <p class="card-text">Result Date: <%=createElectionDto.getElection_date()%><br></p>
          <%if (duplicateCheckMap.get(createElectionDto.getElection_id())) { %>
           <a href="#" class="btn btn-primary disabled" >Already Voted </a><%} else { %>
        <a href="Election?method=get_election&election_id=<%=createElectionDto.getElection_id()%>&voter_id=<%=voter.getVoter_id() %>" class="btn btn-primary">Cast Vote </a>
 <%} %>
      </div>
    </div>
  </div>
 
 <%} %>
</div>

</div>

</body>
</html>