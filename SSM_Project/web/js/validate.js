//-------------login page, forgot pass validate-------------
var flag = true;
$("#form--login").submit(function () {
  flag = checkLogin();
  return flag;
});

$("#form--forgot--pass").submit(function () {
  flag = checkLogin();
  return flag;
});


function checkLogin() {
  $("#error--email").text("");
  $("#error--pass").text("");
  var email = $("#loginEmail").val();
  var pass = $("#loginPassword").val();
  var t = true;
  console.log(email);
  if (email === "" || email === null) {
    $("#error--email").text("Email can't be blank!").css("color", "red");
    t = false;
  }
  if (pass === "" || pass === null) {
    $("#error--pass").text("Password can't be blank!").css("color", "red");
    t = false;
  }
  return t;
}

//-------------login page, forgot pass validate-------------