// sending query from homepage to catalog searchbar
function catalog() {
  var search = document.getElementById("searchQ").value;
  location.href = "catalog.html";
  document.getElementById("catalog").value = search;
}

// the itemName & ItemDescription boxes are checked by default
function check() {
  document.getElementById("itemNameBox").checked = true;
  document.getElementById("itemDescriptionBox").checked = true;
}

// show/hide column
function columnVis(element) {
  if (document.getElementById(element).style.visibility === "visible") {
    document.getElementById(element).style.visibility = "collapse";
  } else {
    document.getElementById(element).style.visibility = "visible";
  }
}

function columnVis2(element) {
  if (document.getElementById(element).style.visibility === "collapse") {
    document.getElementById(element).style.visibility = "visible";
  } else {
    document.getElementById(element).style.visibility = "collapse";
  }
}

// copy id number
function copyThis(number) {
   navigator.clipboard.writeText(number);
}

function addDaysToDate(objDate, intDays) {
   var numberOfMlSeconds = objDate;
   var addMlSeconds = ((intDays * 24) * 60) * 60 * 1000;
   var newDateObj = new Date(numberOfMlSeconds + addMlSeconds);
   return newDateObj;
}

function setTime() {
   document.getElementById("coDate").innerHTML = Date();
   document.getElementById("ciDate").innerHTML = addDaysToDate(Date(), 14);
}

// determines date of expected check-in
function twoWeeks(coDate) {
   document.getElementById("ciDate").innerHTML = addDaysToDate(document.getElementById(coDate), 14);
   // figure out how to read school calendar in case it hits an invalid date
}
function full() {
  if (document.documentElement.requestFullscreen) {
    document.documentElement.requestFullscreen();
  } else if (document.documentElement.webkitRequestFullscreen) {
    document.documentElement.webkitRequestFullscreen();
  } else if (document.documentElement.msRequestFullscreen) {
    document.documentElement.msRequestFullscreen();
  }
  if (document.exitFullscreen) {
    document.exitFullscreen();
  } else if (document.webkitExitFullscreen) {
    document.webkitExitFullscreen();
  } else if (document.msExitFullscreen) {
    document.msExitFullscreen();
  }
}

// live search in searchbar
function liveSearch(search, table) {
  var input = document.getElementById(search);
  var filter = input.value.toUpperCase();
  var table = document.getElementById(table);
  var tr = table.getElementsByTagName("tr");
  for (var counter = 0; counter < tr.length; counter++) {
    var td = tr[counter].getElementsByTagName("td")[0];
    if (td) {
      var txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[counter].style.display = "";
      } else {
        tr[counter].style.display = "none";
      }
    }
  }
}

function name() {
  // retrives and fills in user information such as name & ID
}

// login
function login() {
  // document.body.onclick
  if (document.getElementById("login").style.height === "25%") {
    document.body.style.backgroundColor = "LightCoral";
    document.getElementById("login").style.borderBottom = "none";
    document.getElementById("login").style.height = "0";
  } else {
    document.body.style.backgroundColor = "#CD5C5C";
    document.getElementById("login").style.borderBottom = "solid";
    document.getElementById("login").style.height = "25%";
  }
}
