<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>SSM - Admin page</title>
    <link rel="stylesheet" type="text/css" href="login_page/vendor/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="login_page/css/M_login_main.css">
    <link rel="stylesheet" type="text/css" href="login_page/css/M_login_util.css">
  </head>
  <body>
    <div class="limiter">
      <div class="container-login100">
        <div class="wrap-login100">
          <form class="login100-form validate-form" id="form--login">
            <span class="login100-form-title p-b-26">SSM Market</span>
            <span class="login100-form-title p-b-48">Login</span>

            <div class="wrap-input100 validate-input">
              <input class="input100" type="text" id="loginEmail">
              <span class="focus-input100" data-placeholder="Email"></span>
            </div>
            <span id="error--email"></span>

            <div class="wrap-input100 validate-input">
              <span class="btn-show-pass">
                <i class="zmdi zmdi-eye"></i>
              </span>
              <input class="input100" type="password" id="loginPassword">
              <span class="focus-input100" data-placeholder="Password"></span>
            </div>
            <span id="error--pass"></span>

            <div class="container-login100-form-btn">
              <div class="wrap-login100-form-btn">
                <div class="login100-form-bgbtn"></div>
                <button class="login100-form-btn">Login</button>
              </div>
            </div>
            <div class="text-center p-t-115">
              <a class="txt2" href="forgot_password.jsp">Forgot your password </a>
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>

  <script src="login_page/vendor/jquery/jquery-3.2.1.min.js"></script>
  <script src="login_page/vendor/bootstrap/js/popper.js"></script>
  <script src="login_page/vendor/bootstrap/js/bootstrap.min.js"></script>
  <script src="js/validate.js"></script>
</html>
