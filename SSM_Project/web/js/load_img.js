var loadFile = function (event) {
  var output = document.getElementById('M-btn-Img');
  output.style.backgroundImage = 'url("' + URL.createObjectURL(event.target.files[0]) + '")';
  output.style.backgroundSize = "cover";
  output.style.backgroundRepeat = "no-repeat";
};
