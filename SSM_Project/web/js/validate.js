//-------------login page-------------
var flag = true;
$("#form--login").submit(function () {
  flag = checkLogin();
  return flag;
});
//-------------forgot pass page-------------
$("#form--forgot--pass").submit(function () {
  flag = checkLogin();
  return flag;
});
//-------------profile page-------------
$("#form--profile").submit(function () {
  flag = checkForgotPassForm();
  return flag;
});

function checkForgotPassForm() {
  $("#error--email").text("");
  $("#error--name").text("");
  $("#error--phone").text("");
  $("#error--gender").text("");
  $("#error--role").text("");
  $("#error--status").text("");

  var email = $("#u--mail").val();
  var name = $("#u--name").val();
  var phone = $("#u--phone").val();
  var gender = document.getElementsByName("gender");
  var role = $("#u--role").val();
  var status = $("#u--status").val();

  var alphaExp = /^([A-Z]{1})+([[A-Za-z]+[,.]?[ ]?|[A-Za-z]+[-]]?)+$/gm;
  var emailExp = /^[\w]([^@\s,;]+)@(([\w-]+\.)+(com|edu|org|net|gov|mil|vn|info))$/;
  var phoneExp = /^[0]\d{9,10}$/;
  var t = true;

  if (gender[0].checked != true & gender[1].checked != true) {
    $("#error--gender").text("Gender can't be blank!").css("color", "red");
    t = false;
  }
  if (email === "" || email === null) {
    $("#error--email").text("Email can't be blank!").css("color", "red");
    t = false;
  }
  if (name === "" || name === null) {
    $("#error--name").text("Name can't be blank!").css("color", "red");
    t = false;
  }
  if (phone === "" || phone === null) {
    $("#error--phone").text("Phone can't be blank!").css("color", "red");
    t = false;
  }
  if (name.match(alphaExp)) {
    $("#error--name").text("Name is invaild!").css("color", "red");
    t = false;
  }
  if (email.match(emailExp)) {
    $("#error--email").text("Email is invaild!").css("color", "red");
    t = false;
  }
  if (!phone.match(phoneExp)) {
    $("#error--phone").text("Phone is invaild!").css("color", "red");
    t = false;
  }
  if (role === "" || role === null) {
    $("#error--role").text("Role can't be blank!").css("color", "red");
    t = false;
  }
  if (status === "" || status === null) {
    $("#error--status").text("Status can't be blank!").css("color", "red");
    t = false;
  }
  return t;
}


function checkLogin() {
  $("#error--email").text("");
  $("#error--pass").text("");
  var email = $("#loginEmail").val();
  var pass = $("#loginPassword").val();
  var t = true;
//  console.log(email);
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
