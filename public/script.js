// show/hide column in catalog
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

// show/hide row in check-in
function rowVis(element) {
  var row = document.getElementsByClassName(element);
  for (let counter = 0; counter < row.length; ++counter) {
    if (row[counter].hidden === true) {
      row[counter].hidden = false;
    } else {
      row[counter].hidden = true;
    }
  }
}

// reveal extra filters based on item type
function itemType() {
  if (document.getElementById("type").value == "game") {
    document.getElementById("game").style.height = "auto";
    document.getElementById("game").style.visibility = "visible";
    document.getElementById("hardware").style.height = "0";
    document.getElementById("hardware").style.visibility = "hidden";
    document.getElementById("media").style.height = "0";
    document.getElementById("media").style.visibility = "hidden";
  } else if (document.getElementById("type").value == "hardware") {
    document.getElementById("game").style.height = "0";
    document.getElementById("game").style.visibility = "hidden";
    document.getElementById("media").style.height = "0";
    document.getElementById("media").style.visibility = "hidden";
    document.getElementById("hardware").style.height = "auto";
    document.getElementById("hardware").style.visibility = "visible";
  } else if (document.getElementById("type").value == "media") {
    document.getElementById("game").style.height = "0";
    document.getElementById("game").style.visibility = "hidden";
    document.getElementById("hardware").style.height = "0";
    document.getElementById("hardware").style.visibility = "hidden";
    document.getElementById("media").style.height = "auto";
    document.getElementById("media").style.visibility = "visible";
  }
}

// make the application fullscreen
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

// login
function login() {
  // document.body.onclick
  if (document.getElementById("login").style.visibility === "visible") {
    document.body.style.backgroundColor = "LightCoral";
    document.getElementById("login").style.borderBottom = "none";
    document.getElementById("login").style.height = "0";
    document.getElementById("login").style.visibility = "hidden";
  } else {
    document.body.style.backgroundColor = "#CD5C5C";
    document.getElementsByTagName("a").disabled = true;
    document.getElementById("login").style.borderBottom = "solid";
    document.getElementById("login").style.height = "10%";
    document.getElementById("login").style.visibility = "visible";
  }
}
