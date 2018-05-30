<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSM - Profile</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport'/>
    <link href="admin_page/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="admin_page/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
    <link href="admin_page/css/paper-dashboard.css" rel="stylesheet"/>
    <link href="http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>
    <link href="admin_page/css/themify-icons.css" rel="stylesheet">
  </head>
  <body>
    <div class="wrapper">
      <!-- menu -->
      <%@include file="layout/admin--menu.jsp" %>
      <!-- end menu -->
      <div class="main-panel">
        <!-- sub menu top -->
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle">
                <!-- <span class="sr-only">Toggle navigation</span> -->
                <span class="icon-bar bar1"></span>
                <span class="icon-bar bar2"></span>
                <span class="icon-bar bar3"></span>
              </button>
              <a class="navbar-brand" href="#">Profile</a>
            </div>
            <%@include file="layout/user--header.jsp" %>
          </div>
        </nav>
        <!-- end sub menu top -->
        <div class="content">
          <div class="container-fluid">
            <div class="card">
              <div class="header">
                <h4 class="title">Profile of ACB</h4>
              </div>
              <div class="content">
                <form>
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label>Email</label>
                        <input type="text" class="form-control border-input" disabled placeholder="Company" value="Creative Code Inc.">
                      </div>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label for="exampleInputEmail1">Name</label>
                        <input type="email" class="form-control border-input" placeholder="Email">
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>DOB</label>
                        <input type="date" class="form-control border-input">
                      </div>
                    </div>

                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Phone</label>
                        <input type="text" class="form-control border-input" placeholder="Last Name" value="Faker">
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Gender</label><br>
                        <div class="col-md-4">
                          <input type="radio" name="gender">Male
                        </div>
                        <div class="col-md-5">
                          <input type="radio" name="gender">Female
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-12">
                      <div class="form-group">
                        <label>Address</label>
                        <input type="text" class="form-control border-input" placeholder="Home Address" value="Melbourne, Australia">
                      </div>
                    </div>
                  </div>

                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>DOB</label>
                        <input type="text" class="form-control border-input" placeholder="City" value="Melbourne">
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Role</label>
                        <input type="text" class="form-control border-input" placeholder="Country" value="Australia">
                      </div>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Status</label>
                        <input type="text" class="form-control border-input" disabled placeholder="Company" value="Creative Code Inc.">
                      </div>
                    </div>
                  </div>
                  <div class="text-center" style="padding-bottom: 2%;">
                    <button type="submit" class="btn btn-info btn-fill btn-wd">Update Profile</button>
                  </div>
                  <div class="clearfix"></div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <%@include file="layout/admin--script.jsp" %>
</body>
</html>
