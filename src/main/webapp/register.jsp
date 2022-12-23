<!DOCTYPE html>
<%@page import="com.evoting.dto.Voter"%>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Register</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
</head>

<body class="bg-gradient-primary">
	<% Voter voter = (request.getAttribute("voter"))!= null ? (Voter)request.getAttribute("voter") : new Voter(); %>
    <div class="container">

        <div class="card o-hidden border-0 shadow-lg my-5">
            <div class="card-body p-0">
                <!-- Nested Row within Card Body -->
                <div class="row">
                    <div class="col-lg-5 d-none d-lg-block bg-register-image"></div>
                    <div class="col-lg-7">
                        <div class="p-5">
                            <div class="text-center">
                                <h1 class="h4 text-gray-900 mb-4">Voter Registration</h1>
                            </div>
							<div class="text-center">
								<%
								if(request.getAttribute("error") != null) {
								%>
								<font color="red">Error: <%=request.getAttribute("error")%></font>
								<%
								}else if(request.getAttribute("sucess") != null){
									
									%>
								<font color="green"></font><font color="green"> <%=request.getAttribute("sucess")%></font>
								<%
								}
								%>
							</div>
							<form class="user" action="Voterregistration"  method="post">
                          
                            <input type="hidden" name="method" value="save">
                                <div class="form-group">
                                        <input type="text" class="form-control form-control-user"  placeholder="Name" id="name" name="name" 
                                        value='<%=voter.getName()!=null?voter.getName():""%>' required>
                                </div>
                                 <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                         <input type="email" title="Example xyz@abc.com" class="form-control form-control-user" id="email" name="email"
                                        placeholder="Email Address" value='<%=voter.getEmail()!=null?voter.getEmail():""%>' required pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,}$">
                                    </div>
                                    <div class="col-sm-6">
                                         <input type="text" title="Please enter vaild mobile number"  class="form-control form-control-user" id="mobile" name="mobile"
                                        placeholder="Mobile Number "  value='<%=voter.getMobile()!=null?voter.getMobile():""%>' required pattern="[1-9]{1}[0-9]{9}">
                                    </div>
                                </div>
                                 <div class="form-group">
                                    <input type="text" class="form-control form-control-user" id="address" name="address"
                                        placeholder="Address" value='<%=voter.getAddress()!=null?voter.getAddress():""%>' required>
                                </div>
                                <div class="form-group row">
                                    <div class="col-sm-6 mb-3 mb-sm-0">
                                        <input type="date" class="form-control form-control-user" id="dob" name="dob"
                                        placeholder="Date of Birth "  value='<%= voter.getDob()!=null?voter.getDob():"" %>'  required>
                                        
                                    </div>
                                    <div class="col-sm-6">
                                       <input type="password" title="Password must contain at least 6 characters, including UPPER/lowercase and numbers." class="form-control form-control-user"
                                            id="password" name="password" placeholder="Password"  value='<%=voter.getPassword()!=null?voter.getPassword():""%>' required pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}" >
                                    </div>
                                </div>
                                <button type="submit"  class="btn btn-primary btn-user btn-block">
                                    Register Account
                                </button>
                                <hr>
                               
                            </form>
                            <hr>
                            <div class="text-center">
                                <a class="small" href="forgot-password.html">Forgot Password?</a>
                            </div>
                            <div class="text-center">
                                <a class="small" href="login.jsp">Already have an account? Login!</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>

</body>

</html>