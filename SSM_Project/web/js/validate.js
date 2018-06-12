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

//-------------add admin page-------------
$("#form--add--admin").submit(function () {
  flag = checkAddNAdmin();
  return flag;
});
//-------------change password page-------------
$("#form--change--password").submit(function () {
  flag = checkChangePassword();
  return flag;
});
//-------------new product page-------------
$("#form--new--product").submit(function () {
  flag = checkNewProduct();
  return flag;
});

//-------------new cate-------------
flag = true;
$("#form--add--cate").submit(function () {
  flag = checkNewCate();
  return flag;
});
function checkNewCate() {
  $("#error--name").text("").css("color", "red");
  $("#error--pic").text("").css("color", "red");
  var t = true;
  var name = $("#c--name").val();
//  console.log(name);
  var img = $("#c--pic").val();
  if (name === "" || name === null) {
    $("#error--name").text("Name can't be blank!");
    t = false;
  } else if (name.length > 50) {
    $("#error--name").text("Name maximum is 50 characters!");
    t = false;
  }
  if (img === null || img === "") {
    $("#error--pic").text("Image can't empty!");
    t = false;
  }
  return t;
}
function resetTextErrors() {
  $("#error--email").text("");
  $("#error--name").text("");
  $("#error--phone").text("");
  $("#error--gender").text("");
  $("#error--role").text("");
  $("#error--status").text("");
  $("#error--pass").text("");
  $("#error--confirm--pass").text("");
  $("#error--old--pass").text("");
  $("#error--manufacturer").text("").css("color", "red");
  $("#error--price").text("").css("color", "red");
  $("#error--quantity").text("").css("color", "red");
  $("#error--date--manufacture").text("").css("color", "red");
  $("#error--date--expiration").text("").css("color", "red");
  $("#error--pic").text("").css("color", "red");
  $("#error--editor").text("").css("color", "red");
  $("#error--description").text("").css("color", "red");
}
function checkNewProduct() {
  resetTextErrors();
  var t = true;
  var name = $("#p--name").val();
  var price = $("#p--price").val();
  var amount = $("#p--quantity").val();
  var manu = $("#p--manufacturer").val();
  var dateManu = $("#p--date--manufacture").val();
  var endManu = $("#p--date--expiration").val();
  var img = $("#p--pic").val();
  var editor = CKEDITOR.instances.editor1.document.getBody().getChild(0).getText();

  var numberExp = /^[0-9]*$/

  if (editor.trim() === null || editor.trim() === "") {
    $("#error--editor").text("Description can't be blank");
    t = false;
  } else if (editor.length > 1000) {
    $("#error--editor").text("Description length maxium 1000 characters");
    t = false;
  }
  if (name === "" || name === null) {
    $("#error--name").text("Name can't be blank!").css("color", "red");
    t = false;
  } else if (name.length > 100) {
    $("#error--name").text("Name maximum is 100 characters!").css("color", "red");
    t = false;
  }
  if (price === "" || price === null) {
    $("#error--price").text("Price can't be blank");
    t = false;
  } else if (!price.match(numberExp)) {
    $("#error--price").text("Price is invaild");
    t = false;
  } else if (price.length > 6) {
    $("#error--price").text("Max price is $999.999");
    t = false;
  }
  if (manu === null || manu === "") {
    $("#error--manufacturer").text("Manufacturer can't be blank");
    t = false;
  } else if (manu.length > 50) {
    $("#error--manufacturer").text("Manufacturer max length is 50 characters");
    t = false;
  }
  if (amount === null || amount === "") {
    $("#error--quantity").text("Quantity can't be blank");
    t = false;
  } else if (!amount.match(numberExp)) {
    $("#error--quantity").text("Quantity is vaild");
    t = false;
  } else if (amount.length > 4) {
    $("#error--quantity").text("Max quantity is 9999 item");
    t = false;
  }
  if (dateManu === null || dateManu === "") {
    $("#error--date--manufacture").text("Date of manufacture can't be blank");
    t = false;
  }
  if (endManu === null || endManu === "") {
    $("#error--date--expiration").text("Expiration date can't be blank");
    t = false;
  }
  dateManu = new Date(dateManu);
  endManu = new Date(endManu);
  if (endManu < dateManu) {
    $("#error--date--expiration").text("Expiration date invaild!");
    t = false;
  }
  if (img === null || img === "") {
    $("#error--pic").text("At least having one image!");
    t = false;
  }
  return t;
}


function checkChangePassword() {
  resetTextErrors();
  var t = true;
  var oldPass = $("#u--old--pass").val();

  t = checkPassword();
  if (oldPass === "" || oldPass === null) {
    $("#error--old--pass").text("Current password is invaild!").css("color", "red");
    t = false;
  }
  return t;
}

function checkEmailNameRole() {
  resetTextErrors();
  var t = true;
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
  } else if (!name.match(alphaExp)) {
    $("#error--name").text("First character must uppercase! Don't use speacial characters").css("color", "red");
    t = false;
  } else if (name.length > 50) {
    $("#error--name").text("Name maximum is 50 characters!").css("color", "red");
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
