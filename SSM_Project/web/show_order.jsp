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
    <link rel="stylesheet" href="admin_page/css/M-style.css"/>
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
                <span class="icon-bar bar1"></span>
                <span class="icon-bar bar2"></span>
                <span class="icon-bar bar3"></span>
              </button>
              <a class="navbar-brand" href="#">Show Detail Order</a>
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
                  <h4 class="title">Invoice #001</h4>
                </div>
                <div class="content">
                  <div class="row" style="margin-bottom: 2%;">
                    <div class="text-center"><p style="font-size: 2.5em;">SSMarket</p></div>
                    <div class="pull-left col-md-offset-1">
                      <span>Customer's Name : </span> <span>Minh Bác Ái</span> <br/>
                      <span>Phone : </span> <span>0913313434</span> <br/>
                      <span>Store Address : </span> <span>Quang Trung Software Center</span><br/>
                    </div>
                    <div class="col-md-3 col-md-offset-4" style="text-align: right;">
                      <span>Order Date : </span> <span>12:00, 06/24/2018</span> <br/>
                      <span>Status : </span> <span class="label label-danger">Payment Checked</span> <br/>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-lg-10 col-md-10 col-md-offset-1">
                      <div class="row">
                        <table class="table table-striped text-center" style="border: 1px solid black">
                          <thead style="background-color: #f7ecb5; font-weight: bold;">
                          <th class="text-center">PID</th>
                          <th class="text-center">Name</th>
                          <th class="text-center">Quantity</th>
                          <th class="text-center">Unit Price</th>
                          <th class="text-center">Subtotal</th>
                          </thead>
                          <tbody>
                            <tr>
                              <td>1</td>
                              <td class="table-bordered">Banana</td>
                              <td class="table-bordered">3</td>
                              <td class="table-bordered">$36,738</td>
                              <td>110,214</td>
                            </tr>
                            <tr>
                              <td>1</td>
                              <td class="table-bordered">Banana</td>
                              <td class="table-bordered">3</td>
                              <td class="table-bordered">$36,738</td>
                              <td>110,214</td>
                            </tr>
                            <tr>
                              <td>1</td>
                              <td class="table-bordered">Banana</td>
                              <td class="table-bordered">3</td>
                              <td class="table-bordered">$36,738</td>
                              <td>110,214</td>
                            </tr>
                          </tbody>
                          <tfoot style="background-color: #f7ecb5; font-weight: bold;">
                          <th></th>
                          <th></th>
                          <th></th>
                          <th class="text-center">Total :</th>
                          <th class="text-center">$392,333</th>
                          </tfoot>
                        </table>
                      </div>
                      <hr>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  <%@include file="layout/admin--script.jsp" %>
</body>
</html>
