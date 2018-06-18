<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
                            <a class="navbar-brand" href="#">Show Detail Product</a>
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
                                    <h4 class="title">Show Detail Product</h4>
                                </div>
                                <div class="content">
                                    <div class="row">
                                        <div class="col-lg-10 col-md-10 col-md-offset-1">
                                            <div class="card card-user">
                                                <div class="col-md-12 row" id="M--img--product">
                                                    <!--                          <img src="img/23e3241530edda42484f3006dcff524ea4e7ee992af55-dHcf6g_fw658.png" id="M--img--detail"/>-->
                                                    <img src="img/${requestScope.IMG}" id="M--img--detail"/>
                                                </div>
                                                <div class="row">
                                                    <h4 class="title" style="text-align: center;">${PRODUCT.productName}<br/>
                                                        <a href="#">
                                                            <small>table - desk</small>
                                                        </a>
                                                    </h4>
                                                    <p class="description" style="margin: 4%;">
                                                        ${PRODUCT.description}
<!--                                                        Expansive Touch Display
                                                        The Pano supports 40 simultaneous touch points, while the contiguous dual 4K Ultra High Definition (UHD) displays provide a unique 7680 x 2160 pixel surface.
                                                        Powerful Processing
                                                        The Pano comes with a powerful desktop Quad Core i7 computer system, 16GB of RAM, 512GB SSD, and a GTX 1070 graphics card. The system includes a built-in sound system with four 90W Yamaha speakers.-->
                                                    </p>
                                                </div>
                                                <hr>
                                                <div class="text-center">
                                                    <div class="row">
                                                        <div class="col-md-3">
                                                            <h5><fmt:formatDate pattern="dd/MM/yyyy" value="${PRODUCT.manuDate}"/><br/>
                                                                <small>Date Of Manufacture</small>
                                                            </h5>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <h5><fmt:formatDate pattern="dd/MM/yyyy" value="${PRODUCT.expiredDate}"/><br/>
                                                                <small>Expiration Date</small>
                                                            </h5>
                                                        </div>
                                                        <div class="col-md-3">
                                                            <h5>${PRODUCT.manufacturer}<br/>
                                                                <small>Manufacturer</small>
                                                            </h5>
                                                        </div>
                                                        <div class="col-md-1">
                                                            <h5>${PRODUCT.price}<br/>
                                                                <small>Price</small>
                                                            </h5>
                                                        </div>
                                                        <div class="col-md-1">
                                                            <h5>${PRODUCT.quantity}<br/>
                                                                <small>Quantity</small>
                                                            </h5>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
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
