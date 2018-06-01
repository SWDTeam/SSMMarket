<%-- 
    Document   : new_admin
    Created on : Jun 1, 2018, 7:43:54 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSM - Add Admin</title>
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
                <span class="icon-bar bar1"></span>
                <span class="icon-bar bar2"></span>
                <span class="icon-bar bar3"></span>
              </button>
              <a class="navbar-brand" href="#">Add admin</a>
            </div>
            <%@include file="layout/user--header.jsp" %>
          </div>
        </nav>
        <!-- end sub menu top -->
        <div class="content">
          <div class="container-fluid col-md-offset-1 col-md-10">
            <div class="card">
              <div class="header">
                <h4 class="title">Add admin</h4>
              </div>
              <div class="content">
                <form id="form--add--admin">
                  <div class="row">
                    <div class="col-md-6">
                      <div class="form-group">
                        <label>Email</label>
                        <input type="email" class="form-control border-input" id="u--mail">
                      </div>
                      <span id="error--email"></span>
                    </div>
                    <div class="col-md-6">
                      <div class="form-group">
                        <label>Name</label>
                        <input type="text" class="form-control border-input" id="u--name">
                      </div>
                      <span id="error--name"></span>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control border-input" id="u--pass">
                      </div>
                      <span id="error--pass"></span>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Confirm Password</label>
                        <input type="password" class="form-control border-input" id="u--confirm--pass">
                      </div>
                      <span id="error--confirm--pass"></span>
                    </div>
                    <div class="col-md-4">
                      <div class="form-group">
                        <label>Role</label>
                        <input type="text" class="form-control border-input" disabled="true" id="u--role" value="admin">
                      </div>
                      <span id="error--role"></span>
                    </div>
                  </div>
                  <div class="text-center" style="padding-bottom: 2%;">
                    <button type="submit" class="btn btn-info btn-fill btn-wd">Add New Admin</button>
                  </div>

                  <div class="col-md-3"></div>
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
