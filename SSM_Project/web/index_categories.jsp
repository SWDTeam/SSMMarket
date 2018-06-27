<%-- 
    Document   : index_categories
    Created on : Jun 10, 2018, 12:19:21 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="thupnm" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                                    <form action="SearchCategoryController" method="POST">
                                                        <div class="col-md-6">
                                                            <input type="text" name="txtSearch" placeholder="Name..." class="form-control border-input">
                                                        </div>
                                                        <div class="col-md-3" >
                                                            <button class="btn btn-success" type="submit">Filter</button>
                                                        </div>
                                                    </form>
                                                </div>
                                            </div>
                                            <thupnm:if test="${not empty requestScope.listCategory}">
                                                <table class="table table-striped" id="list">
                                                    <thead>
                                                        <tr>
                                                            <th>ID</th>
                                                            <th>Name</th>
                                                            <th>TotalOfProducts</th>
                                                            <th>Edit</th>
                                                            <th>Delete</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <thupnm:forEach items="${requestScope.listCategory}" var="dto" varStatus="counter">
                                                            <tr>
                                                                <td>${counter.count}</td>
                                                                <td>${dto.categoryName}</td>
                                                                <td align="center">${requestScope.total[counter.index]}</td>
                                                                <td>
                                                                    <form action="ViewInfoCategory" method="POST">
                                                                        <input type="hidden" value="${dto.categoryId}" name="cateId"/>
                                                                        <button type="submit" class="btn btn-info ti-pencil"/>
                                                                    </form>
                                                                </td>
                                                                <td>
                                                                    <form action="ChangeStatusCategory" method="POST">
                                                                        <input type="hidden" value="${dto.categoryId}" name="cateId"/>
                                                                        <button type="submit" onclick="return confirmation()" class="btn btn-danger ti-trash"/>
                                                                    </form>
                                                                </td>
                                                            </tr>
                                                        </thupnm:forEach>
                                                    </tbody>
                                                </table>
                                            </thupnm:if>

                                            <thupnm:if test="${empty requestScope.listCategory}">
                                                <strong><font color="red">No record</font></strong>
                                                </thupnm:if>
                                        </div>
                                    </div>
                                    <!--pagination-->
                                    <%@include file="layout/admin--pagination.jsp" %>
                                </div>
                                <!--add categories--> 
                                <div class="col-md-5">
                                    <div class="card">
                                        <div class="header">
                                            <h4 class="title">Category Form</h4>
                                        </div>
                                        <form id="form--add--cate" action="AddAndUpdateCategory" method="POST" enctype="multipart/form-data">
                                            <div class="content">
                                                <div class="row">
                                                    <div class="form-group col-md-10 col-md-offset-1">
                                                        <label>Name</label>
                                                        <input type="hidden" value="${VIEWCATE.categoryId}" name="cateId"/>
                                                        <input type="text" value="${VIEWCATE.categoryName}" name="cateName"
                                                               class="form-control border-input" id="c--name"/>
                                                        <span id="error--name"><strong><font color="red">${param.ERRORCATE}</font></strong></span>
                                                        <br/>
                                                        <label>Image</label> <br/>
                                                        <div id="M-btn-Img" class="col-md-11 btn" style="height: 15vw; position: absolute; background-image: url('img/${VIEWCATE.imgPic}'); background-size: cover; background-repeat: no-repeat;" class="btn"></div>
<!--                                                        <img id="M-btn-Img" alt="Choose an image" class="col-md-11 btn" style="height: 15vw; position: absolute; background-image: url('img/${VIEWCATE.imgPic}');" class="btn"/>-->
                                                        <input type="file" name="imgPic" class="col-md-12" id="c--pic" onchange="loadFile(event)" style=" top:0px; height: 15vw;opacity:0;"/>
                                                        <span id="error--pic"></span>
<!--                                                        <img src="img/${VIEWCATE.imgPic}"  />-->
                                                        <!--<input type="file" name="imgPic" class="form-control" id="c--pic">-->                     
                                                        <!--<span style="white-space: nowrap; text-overflow: ellipsis; width: 250px; display: block; overflow: hidden" id="error--pic"></span>-->                                                       
                                                    </div>
                                                    <div class="col-md-7 col-md-offset-4">
                                                        <input type="submit" value="Submit" class="btn btn-info btn-fill btn-wd"/>
                                                    </div>
                                                </div>
                                            </div>
                                            <strong><font color="red">${requestScope.RESULT}</font></strong>
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
        <script src="js/load_img.js"></script>
        <script>
            function confirmation() {
                return confirm("Are you sure to disable this category?");
            }
        </script>
    </body>
</html>
