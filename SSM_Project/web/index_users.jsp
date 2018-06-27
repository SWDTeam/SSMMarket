<%-- 
    Document   : index_users
    Created on : May 30, 2018, 8:57:53 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="thupnm" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SSM - Users</title>
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
                            <a class="navbar-brand" href="#">Users Manager</a>
                        </div>
                        <%@include file="layout/user--header.jsp" %>
                    </div>
                </nav>
                <!--End user Manager sub menu -->

                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="card">
                                <div class="header">
                                    <h4 class="title">View All Users</h4>
                                </div>                                
                                <div class="content table-responsive table-full-width">
                                    <div class="row">
                                        <div class="col-md-1"></div>
                                        <div class="col-md-3">

                                            <select class="form-control border-input">
                                                <option value="1">Role</option>
                                                <option value="user">user</option>
                                                <option value="admin">admin</option>                                                
                                            </select>


                                        </div>
                                        <div class="row col-md-8">
                                            <div class="col-md-3"></div>
                                            <form action="SearchController" method="POST">
                                                <div class="col-md-6" style="padding-bottom: 2%;">
                                                    <input type="text" placeholder="Name..." name="txtSearch" class="form-control border-input">
                                                </div>
                                                <div class="col-md-3 pull-right" >
                                                    <button class="btn btn-success" type="submit">Filter</button>
                                                </div>
                                            </form>
                                        </div>

                                    </div>
                                    <thupnm:if test="${not empty requestScope.listUser}">
                                        <table class="table table-striped" id="list">
                                            <thead>
                                                <tr>
                                                    <th>No</th>
                                                    <th>Name</th>
                                                    <th>Email</th>
                                                    <th>Gender</th>
                                                    <th>Role</th>
                                                    <th>Status</th>
                                                    <th>Show</th>
                                                    <th>Action</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <thupnm:forEach items="${requestScope.listUser}" var="dto" varStatus="counter">
                                                    <tr>
                                                        <td>${counter.count}</td>
                                                        <td>${dto.username}</td>
                                                        <td>${dto.email}</td>
                                                        <td>${dto.gender}</td>
                                                        <td>${dto.role}</td>
                                                        <td>
                                                            <thupnm:if test="${dto.status == 'active'}">
                                                                <font color='blue'>${dto.status}</font>
                                                            </thupnm:if>
                                                            <thupnm:if test="${dto.status == 'banned'}">
                                                                <font color='red'>${dto.status}</font>
                                                            </thupnm:if>
                                                        </td>
                                                        <td>
                                                            <form action="UpdateBasicInfo">
                                                                <input type="hidden" name="lastSearch" value="${requestScope.lastSearch}" />
                                                                <input type="hidden" name="userId" value="${dto.userId}" />
                                                                <input type="hidden" name="roleId" value="${dto.roleId}" />
                                                                <button type="submit" class="btn btn-info ti-zoom-in">Show</button>
                                                            </form>
                                                        </td>
                                                        <td>
                                                            <thupnm:if test="${dto.status == 'active'}">
                                                                <form action="BanAccountController">
                                                                    <input type="hidden" name="userId" value="${dto.userId}" />
                                                                    <button type="submit" class="btn btn-dark ti-lock" onclick="return confirmation()">Ban</button>
                                                                </form>
                                                            </thupnm:if>
                                                            <thupnm:if test="${dto.status == 'banned'}">
                                                                <form action="ActiveAccountController">
                                                                    <input type="hidden" name="userId" value="${dto.userId}" />
                                                                    <button type="submit" class="btn btn-success ti-unlock" onclick="return confirmation()">Active</button>
                                                                </form>
                                                            </thupnm:if>
                                                        </td>
                                                    </tr>

                                                </thupnm:forEach>
                                            </tbody>
                                        </table>
                                    </thupnm:if>

                                    <thupnm:if test="${empty requestScope.listUser}">
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
        <%@include file="layout/admin--script.jsp" %>
        <script>
            function confirmation() {
                return confirm("Are you sure?");
            }

        </script>
    </body>

</html>
