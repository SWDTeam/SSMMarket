<%-- 
    Document   : index_categories
    Created on : Jun 10, 2018, 12:19:21 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSM - Categories</title>
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
      <div class="main-panel" style="overflow-x: hidden;">
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <div class="navbar-header">
              <button type="button" class="navbar-toggle">
                <span class="icon-bar bar1"></span>
                <span class="icon-bar bar2"></span>
                <span class="icon-bar bar3"></span>
              </button>
              <a class="navbar-brand" href="#">Categories Manager</a>
            </div>
            <%@include file="layout/dashboard--header.jsp" %>
          </div>
        </nav>
        <div class="content">
          <div class="container-fluid">
            <div class="row">
              <div class="card">                            
                <div class="col-md-7">
                  <div class="card">
                    <div class="header">
                      <h4 class="title">View All Categories</h4>
                    </div>
                    <div class="content table-responsive table-full-width">
                      <div class="row">
                        <div class="col-md-12">
                          <form action="" method="POST">
                            <div class="col-md-6">
                              <input type="text" placeholder="Name..." class="form-control border-input">
                            </div>
                            <div class="col-md-3" >
                              <button class="btn btn-success" type="submit">Filter</button>
                            </div>
                          </form>
                        </div>
                      </div>
                      <table class="table table-striped" id="list">
                        <thead>
                          <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Total Of Products</th>
                            <th>Delete</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr>
                            <td>1</td>
                            <td>aa</td>
                            <td>3</td>
                            <td><i class="btn btn-danger ti-trash"></i></td>
                          </tr>
                        </tbody>
                      </table>
                    </div>
                  </div>
                  <!--pagination-->
                  <%@include file="layout/admin--pagination.jsp" %>
                </div>
                <!--add categories--> 
                <div class="col-md-5">
                  <div class="card">
                    <div class="header">
                      <h4 class="title">Add New Category</h4>
                    </div>
                    <form id="form--add--cate">
                      <div class="content">
                        <div class="row">
                          <div class="form-group col-md-10 col-md-offset-1">
                            <label>Name</label>
                            <input type="text" class="form-control border-input" id="c--name"/>
                            <span id="error--name"></span>
                            <br/>
                            <label>Description</label> <br/>
                            <textarea class="form-control border-input" style="height: 100px;" id="c--des"> 
                            </textarea>
                            <span id="error--description"></span>
                          </div>
                          <div class="col-md-7 col-md-offset-4">
                            <button type="submit" class="btn btn-info btn-fill btn-wd">Add New Category</button>
                          </div>
                        </div>
                      </div>
                    </form>
                  </div>
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
