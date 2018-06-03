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
  flag = checkProfile();
  return flag;
});

$("#form--change--password").submit(function () {
  flag = checkChangePassword();
  return flag;
})
function resetTextErrors() {
  $("#error--email").text("");
  $("#error--name").text("");
  $("#error--phone").text("");
  $("#error--gender").text("");
  $("#error--role").text("");
  $("#error--status").text("");

  var email = $("#u--mail").val();
  var name = $("#u--name").val();
  var role = $("#u--role").val();
  var alphaExp = /^([A-Z]{1})+([[A-Za-z]+[,.]?[ ]?|[A-Za-z]+[-]]?)+$/gm;
  var emailExp = /^[\w]([^@\s,;]+)@(([\w-]+\.)+(com|edu|org|net|gov|mil|vn|info))$/;

  if (email === "" || email === null) {
    $("#error--email").text("Email can't be blank!").css("color", "red");
    t = false;
  }
  if (name === "" || name === null) {
    $("#error--name").text("Name can't be blank!").css("color", "red");
    t = false;
  } else if (name.match(alphaExp)) {
    $("#error--name").text("Name is invaild!").css("color", "red");
    t = false;
  }
  if (role === "" || role === null) {
    $("#error--role").text("Role can't be blank!").css("color", "red");
    t = false;
  }
  return t;
}

function checkPassword() {
  resetTextErrors();
  var pass = $("#u--pass").val();
  var confirmPass = $("#u--confirm--pass").val();
  var t = true;
  if (pass === "" || pass === null) {
    $("#error--pass").text("Password can't be blank!").css("color", "red");
    t = false;
  } else if (pass.length < 6 || pass.length > 30) {
    $("#error--pass").text("Password is length in range [6,30]!").css("color", "red");
    t = false;
  }
  if (confirmPass === null || confirmPass === "") {
    $("#error--confirm--pass").text("Confirm password can't be blank!").css("color", "red");
    t = false;
  }
  if (confirmPass !== pass) {
    $("#error--confirm--pass").text("Confirm password not match!").css("color", "red");
    t = false;
  }
  return t;
}

function checkAddNAdmin() {
  resetTextErrors();
  var t = true;
  t = checkEmailNameRole();
  var pass = $("#u--pass").val();
  var confirmPass = $("#u--confirm--pass").val();
  if (pass === "" || pass === null) {
    $("#error--pass").text("Password can't be blank!").css("color", "red");
    t = false;
  } else if (pass.length < 6 || pass.length > 30) {
    $("#error--pass").text("Password is length in range [6,30]!").css("color", "red");
    t = false;
  }
  if (confirmPass === null || confirmPass === "") {
    $("#error--confirm--pass").text("Confirm password can't be blank!").css("color", "red");
    t = false;
  }
  if (confirmPass !== pass) {
    $("#error--confirm--pass").text("Confirm password not match!").css("color", "red");
    t = false;
  }
  return t;
}

function checkProfile() {
  resetTextErrors();
  var phone = $("#u--phone").val();
  var gender = document.getElementsByName("gender");
  var status = $("#u--status").val();
  var phoneExp = /^[0]\d{9,10}$/;

  var t = true;
  t = checkEmailNameRole();
  if (gender[0].checked !== true & gender[1].checked !== true) {
    $("#error--gender").text("Gender can't be blank!").css("color", "red");
    t = false;
  }
  if (phone === "" || phone === null) {
    $("#error--phone").text("Phone can't be blank!").css("color", "red");
    t = false;
  }
  if (!phone.match(phoneExp)) {
    $("#error--phone").text("Phone is invaild!").css("color", "red");
    t = false;
  }
  if (status === "" || status === null) {
    $("#error--status").text("Status can't be blank!").css("color", "red");
    t = false;
  }
  return t;
}

function checkLogin() {
  resetTextErrors();
  var email = $("#loginEmail").val();
  var pass = $("#loginPassword").val();
  var t = true;

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
