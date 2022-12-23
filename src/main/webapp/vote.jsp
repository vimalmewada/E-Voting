<%@page import="com.evoting.dto.Voter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.evoting.dto.CandidateDto"%>
<%@page import="java.util.List"%>
<%@page import="com.evoting.dto.CreateElectionDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>e-voting</title>
  <link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.0/css/bootstrap.min.css'>
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css'><link rel="stylesheet" href="css/aabc.css">
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
 <% 
 Voter voter = (session.getAttribute("currentSessionUser"))!= null ? (Voter)session.getAttribute("currentSessionUser") : null; 
	if(voter==null){
		 response.sendRedirect("index.html");
	}
 
 CreateElectionDto createElectionDto = request.getAttribute("electiondto") != null? (CreateElectionDto) request.getAttribute("electiondto"): new CreateElectionDto();
  List<CandidateDto> list = request.getAttribute("candidatedto") != null? ((List<CandidateDto>) request.getAttribute("candidatedto")): new ArrayList();
  System.out.println("hello*****"+list);
 // System.out.println("###"+list1);
  
  %>
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
 <br>
<!-- partial:index.partial.html -->
<section class="wrapper">
  <div class="container">
    <div class="row">
    
      <div class="col text-center mb-5">
         <h1 class="display-6">वोट करें वफ़ादारी से। चयन करें समझदारी से।।</h1> 
        
      </div>
    </div>
  <div class="row">
    <%
            for(CandidateDto candidateDto  :list){
            %>
 <div class="col-sm-12 col-md-6 col-lg-4 mb-4">
 <div class="card text-white card-has-bg click-col" style="background-image: /e-voting System/src/main/webapp/img/18637.jpg">
         <img class="card-img d-none" src="/e-voting System/src/main/webapp/img/18637.jpg" alt="Goverment Lorem Ipsum Sit Amet Consectetur dipisi?">
        <div class="card-img-overlay d-flex flex-column">
         <div class="card-body">
            <small class="card-meta mb-2">Election Name: <%=createElectionDto.getElection_name() %></small>
          
            <h4 class="card-title mt-0 "><a class="text-white" ><%=candidateDto.getName() %></a></h4>
           <small><i class="far fa-clock"></i> DOB:<%= candidateDto.getDob() %></small>
          </div>
          <div class="card-footer">
           <div class="media">
  <img class="mr-3 rounded-circle" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT_lZeKK8_bbldLaG16HxIQ-_nzhao3YyPMv4ndmXg9dnMv4DsU80sSyYR4o1hyjUKfc80&usqp=CAU" alt="Generic placeholder image" style="max-width:100px">
  <div class="media-body">
  <br>
    <a  href="Vote?method=vote&candidate_id=<%=candidateDto.getCandidate_id() %>&election_id=<%=createElectionDto.getElection_id() %>&voter_id=<%=voter.getVoter_id()%>"><button type="button"  class="btn btn-success btn-lg" > &nbsp;&nbsp;Vote&nbsp;&nbsp;&nbsp;</button>
  </a>
  </div>
</div>
        </div>
          
        </div>
        
      </div>
     
      </div>
       <%} %>
 
  
</div>
  
</div>
</section>
<!-- partial -->
  
</body>
</html>
