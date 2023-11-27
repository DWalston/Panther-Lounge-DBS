function checkIn() {}

function name() {
  document.getElementById("name").textContent = name;
}

// copy id number
function copyThis(number) {
  navigator.clipboard.writeText(number);
}

// date autofill
function theDate() {
  var now = new Date();
  var day = ("0" + now.getDate()).slice(-2);
  var today =
    now.getFullYear() + "-" + (now.getMonth() + 1).slice(-2) + "-" + day;
  document.getElementById("coDate").textContent = today;
  day = ("14" + now.getDate()).slice(-2);
  document.getElementById("ciDate").textContent = today;
}

function seeItem() {}
