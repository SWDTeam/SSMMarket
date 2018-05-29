<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SSM - Dashboard</title>
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
              <a class="navbar-brand" href="#">Dashboard</a>
            </div>
            <%@include file="layout/user--header.jsp" %>
          </div>
        </nav>
        <!-- end sub menu top -->
        <div class="content">
          <div class="container-fluid">
            <!-- Row 4 card -->
            <div class="row">
              <div class="col-lg-3 col-sm-6">
                <div class="card">
                  <div class="content">
                    <div class="row">
                      <div class="col-xs-5">
                        <div class="icon-big icon-warning text-center">
                          <i class="ti-shopping-cart-full"></i>
                        </div>
                      </div>
                      <div class="col-xs-7">
                        <div class="numbers">
                          <p>Products</p>
                          105
                        </div>
                      </div>
                    </div>
                    <div class="footer">
                      <hr/>
                      <div class="stats">
                        <i class="ti-face-smile"></i> Last time
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-3 col-sm-6">
                <div class="card">
                  <div class="content">
                    <div class="row">
                      <div class="col-xs-5">
                        <div class="icon-big icon-success text-center">
                          <i class="ti-wallet"></i>
                        </div>
                      </div>
                      <div class="col-xs-7">
                        <div class="numbers">
                          <p>Revenue</p>
                          $1,345
                        </div>
                      </div>
                    </div>
                    <div class="footer">
                      <hr/>
                      <div class="stats">
                        <i class="ti-calendar"></i> Last day
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-3 col-sm-6">
                <div class="card">
                  <div class="content">
                    <div class="row">
                      <div class="col-xs-5">
                        <div class="icon-big icon-danger text-center">
                          <i class="ti-timer"></i>
                        </div>
                      </div>
                      <div class="col-xs-7">
                        <div class="numbers">
                          <p>Orders</p>
                          23
                        </div>
                      </div>
                    </div>
                    <div class="footer">
                      <hr/>
                      <div class="stats">
                        <i class="ti-timer"></i> In the last hour
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-lg-3 col-sm-6">
                <div class="card">
                  <div class="content">
                    <div class="row">
                      <div class="col-xs-5">
                        <div class="icon-big icon-info text-center">
                          <i class="ti-heart"></i>
                        </div>
                      </div>
                      <div class="col-xs-7">
                        <div class="numbers">
                          <p>Users</p>
                          +45
                        </div>
                      </div>
                    </div>
                    <div class="footer">
                      <hr/>
                      <div class="stats">
                        <i class="ti-reload"></i> Updated now
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- End Row 4 card -->

            <!-- Tabel best seller -->
            <div class="row">
              <div class="col-md-12">
                <div class="card">
                  <div class="header">
                    <h4 class="title">Best seller</h4>
                    <p class="category">In a week</p>
                  </div>
                  <div class="content table-responsive table-full-width">
                    <table class="table table-striped">
                      <thead>
                      <th></th>
                      <th>Name</th>
                      <th>Category</th>
                      <th>Sold</th>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>Dakota Rice</td>
                          <td>Table</td>
                          <td>$36,738</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Minerva Hooper</td>
                          <td>Table</td>
                          <td>$23,789</td>
                        </tr>
                        <tr>
                          <td>3</td>
                          <td>Sage Rodriguez</td>
                          <td>Table</td>
                          <td>$56,142</td>
                        </tr>
                        <tr>
                          <td>4</td>
                          <td>Philip Chaney</td>
                          <td>Table</td>
                          <td>$38,735</td>
                        </tr>
                        <tr>
                          <td>5</td>
                          <td>Doris Greene</td>
                          <td>Table</td>
                          <td>$63,542</td>
                        </tr>
                      </tbody>
                    </table>

                  </div>
                </div>
              </div>
            </div>
            <!-- End Tabel best seller -->
            <div class="row">
              <!-- Tabel customers -->
              <div class="col-md-6">
                <div class="card">
                  <div class="header">
                    <h4 class="title">Vip Customers</h4>
                    <p class="category">Top 3 customers</p>
                  </div>
                  <div class="content table-responsive table-full-width">
                    <table class="table table-striped">
                      <thead>
                      <th></th>
                      <th>Name</th>
                      <th>Category</th>
                      <th>Sold</th>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>Dakota Rice</td>
                          <td>Table</td>
                          <td>$36,738</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Minerva Hooper</td>
                          <td>Table</td>
                          <td>$23,789</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Minerva Hooper</td>
                          <td>Table</td>
                          <td>$23,789</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>
              </div>
              <!-- End Tabel customers -->
              <div class="col-md-6">
                <div class="card ">
                  <div class="header">
                    <h4 class="title">2015 Sales</h4>
                    <p class="category">All products including Taxes</p>
                  </div>
                  <div class="content table-responsive table-full-width">
                    <table class="table table-striped">
                      <thead>
                      <th></th>
                      <th>Name</th>
                      <th>Category</th>
                      <th>Sold</th>
                      </thead>
                      <tbody>
                        <tr>
                          <td>1</td>
                          <td>Dakota Rice</td>
                          <td>Table</td>
                          <td>$36,738</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Minerva Hooper</td>
                          <td>Table</td>
                          <td>$23,789</td>
                        </tr>
                        <tr>
                          <td>2</td>
                          <td>Minerva Hooper</td>
                          <td>Table</td>
                          <td>$23,789</td>
                        </tr>
                      </tbody>
                    </table>
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
