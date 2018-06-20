<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="thupnm" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <a class="navbar-brand" href="#">Products Manager</a>
                        </div>
                        <%@include file="layout/product_header.jsp" %>
                    </div>
                </nav>
                <!--End user Manager sub menu -->

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">View All Products</h4>
                                </div>
                                <div class="content table-responsive table-full-width">
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-2">
                                            <select class="form-control border-input">
                                                <option>Sort By Price</option>
                                                <option>Price(Ascending)</option>
                                                <option>Price(Descending) </option>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <select class="form-control border-input">
                                                <option>Sort By Quantity</option>
                                                <option>Quantity(Ascending)</option>
                                                <option>Quantity(Descending) </option>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <select class="form-control border-input">
                                                <option>Sort By Category</option>
                                                <option>Milk</option>
                                                <option>Snack </option>
                                            </select>
                                        </div>
                                        <div class="row col-md-5">
                                            <div class="col-md-3"></div>
                                            <form action="SearchProductController" method="POST">
                                                <div class="col-md-6" style="padding-bottom: 2%;">
                                                    <input type="text" name="txtSearch" placeholder="Name..." class="form-control border-input">
                                                </div>
                                                <div class="col-md-3 pull-right" >
                                                    <button type="submit" class="btn btn-success">Filter</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <thupnm:if test="${not empty requestScope.listProduct}">
                                        <table class="table table-striped">
                                            <thead>
                                            <th>No</th>
                                            <th>Name</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                            <th>Status</th>
                                            <th>Show</th>
                                            <th>Action</th>
                                            </thead>
                                            <tbody>
                                                <thupnm:forEach items="${requestScope.listProduct}" var="dto" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>${dto.productName}</td>
                                                        <td>${dto.price}</td>
                                                        <td>${dto.quantity}</td>
                                                        <td>${dto.status}</td>
                                                        <td>
                                                            <form action="ShowProductDetail" method="POST">
                                                                <input type="hidden" name="productId" value="${dto.productId}" />
                                                                <button class="btn btn-info ti-zoom-in" type="submit">Show</button>
                                                            </form>
                                                        </td>
                                                        <td><button class="btn btn-dark ti-close" type="submit">Ban</button></td>                                                       
                                                    </tr>
                                                </thupnm:forEach>
                                            </tbody>
                                        </table>
                                    </thupnm:if>
                                    
                                    <thupnm:if test="${empty requestScope.listProduct}">
                                        <strong><font color="red">No record</font></strong>
                                    </thupnm:if>
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
