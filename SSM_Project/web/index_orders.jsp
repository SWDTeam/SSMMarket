<%-- 
    Document   : index_orders
    Created on : Jun 4, 2018, 1:01:23 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSM - Products</title>
    <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />
    <link href="admin_page/css/bootstrap.min.css" rel="stylesheet" />
    <link href="admin_page/css/paper-dashboard.css" rel="stylesheet" />
    <!--  Fonts and icons     -->
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
        <!-- user Manager sub menu -->
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle">
                <!-- <span class="sr-only">Toggle navigation</span> -->
                <span class="icon-bar bar1"></span>
                <span class="icon-bar bar2"></span>
                <span class="icon-bar bar3"></span>
              </button>
              <a class="navbar-brand" href="#">Order Manager</a>
            </div>
            <%@include file="layout/dashboard--header.jsp" %>
          </div>
        </nav>
        <!--End user Manager sub menu -->

        <div class="content">
          <div class="container-fluid">
            <div class="row">
              <div class="card">
                <div class="header">
                  <h4 class="title">View All Orders</h4>
                </div>
                <div class="content table-responsive table-full-width">
                  <div class="row">
                    <div class="col-md-1"></div>
                    <div class="col-md-2">
                      <select class="form-control border-input">
                        <option>Sort By Status</option>
                        <option>Accepted</option>
                        <option>Pending</option>
                        <option>Canceled</option>
                      </select>
                    </div>
                    <div class="row col-md-5 pull-right">
                      <!--<div class="col-md-3"></div>-->
                      <div class="col-md-6" style="padding-bottom: 2%;">
                        <input type="text" placeholder="ID Order..." class="form-control border-input">
                      </div>
                      <div class="col-md-3" >
                        <button class="btn btn-success">Filter</button>
                      </div>
                    </div>
                  </div>
                  <table class="table table-striped">
                    <thead>
                    <th>ID</th>
                    <th>Customer's Name</th>
                    <th>Total</th>
                    <th>Current Status</th>
                    <th>Change Status</th>
                    <th>Action</th>
                    </thead>
                    <tbody>
                      <tr>
                        <td><a href="#">1</a></td>
                        <td>Dakota Rice</td>
                        <td>$36,738</td>
                        <td>
                          <span class="label label-warning">Pending</span></td>
                        <td>
                          <select class="form-control border-input">
                            <option>Accepted</option>
                            <option>Pending</option>
                            <option>Canceled</option>
                          </select>
                        </td>
                        <td>
                          <button class="btn btn-danger">Submit</button>
                        </td>
                      </tr>
                      <tr>
                        <td><a href="#">2</a></td>
                        <td>Dakota Rice</td>
                        <td>$36,738</td>
                        <td>
                          <span class="label label-success">Accepted</span></td>
                        <td>
                          <select class="form-control border-input">
                            <option>Accepted</option>
                            <option>Pending</option>
                            <option>Canceled</option>
                          </select>
                        </td>
                        <td>
                          <button class="btn btn-danger">Submit</button>
                        </td>
                      </tr>
                      <tr>
                        <td><a href="#">3</a></td>
                        <td>Dakota Rice</td>
                        <td>$36,738</td>
                        <td>
                          <span class="label label-danger">Canceled</span></td>
                        <td>
                          <select class="form-control border-input">
                            <option>Accepted</option>
                            <option>Pending</option>
                            <option>Canceled</option>
                          </select>
                        </td>
                        <td>
                          <button class="btn btn-danger">Submit</button>
                        </td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
            <!--pagination-->
            <%@include file="layout/admin--pagination.jsp" %>
          </div>
        </div>
      </div>
    </div>
  </body>
  <%@include file="layout/admin--script.jsp" %>
</body>
</html>
