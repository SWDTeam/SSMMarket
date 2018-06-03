<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="thupnm" uri="http://java.sun.com/jsp/jstl/core" %>
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
                                <h4 class="title">Profile of ${UPDATEBASIC.username}</h4>
                            </div>
                            <div class="content">
                                <form id="form--profile" action="UpdateInfoController" method="POST">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Email</label>
                                                <input type="hidden" name="userId" value="${USERID}"/>
                                                <input type="hidden" name="roleId" value="${ROLEID}"/>
                                                <input type="email" class="form-control border-input" name="email"
                                                       readonly="true" value="${UPDATEBASIC.email}" id="u--mail">
                                            </div>
                                            <span id="error--email"></span>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>Name</label>
                                                <input type="text" class="form-control border-input" 
                                                       name="username" value="${UPDATEBASIC.username}" id="u--name">
                                            </div>
                                            <span id="error--name"></span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Phone</label>
                                                <input type="text" class="form-control border-input" 
                                                       name="phoneNumber" value="${UPDATEBASIC.phone}" id="u--phone">
                                            </div>
                                            <span id="error--phone"></span>
                                        </div>
                                        <div class="col-md-4 pull-right">
                                            <div class="form-group">
                                                <label>Gender</label><br>
                                                <div class="col-md-4">
                                                    <input type="radio" name="gender" value="male" <thupnm:if test="${UPDATEBASIC.gender eq 'male'}">checked="checked"</thupnm:if>>Male
                                                    </div>
                                                    <div class="col-md-5">
                                                        <input type="radio" name="gender" value="female" <thupnm:if test="${UPDATEBASIC.gender eq 'female'}">checked="checked"</thupnm:if>>Female
                                                    </div>
                                                </div><br/>
                                                <span id="error--gender"></span>
                                            </div>
                                            <div class="col-md-4">
                                                <div class="form-group">
                                                    <label>Role</label>
                                                    <input type="text" class="form-control border-input" disabled="true" value="${ROLE.roleName}" id="u--role">
                                            </div>
                                            <span id="error--role"></span>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-8">
                                            <div class="form-group">
                                                <label>Address</label>
                                                <input type="text" class="form-control border-input" 
                                                       name="address" required value="${UPDATEBASIC.address}">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <label>Status</label>
                                                <input type="text" class="form-control border-input" readonly="true" 
                                                       name="status" required value="${UPDATEBASIC.status}" id="u--status">
                                            </div>
                                            <span id="error--status"></span>
                                        </div>
                                    </div>
                                    <div class="text-center" style="padding-bottom: 2%;">
                                        <button type="submit" class="btn btn-info btn-fill btn-wd">Update Profile</button>         
                                    </div>
                                    <div class="clearfix"></div>
                                    <strong><font color="red">${requestScope.RESULT}</font></strong>
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
