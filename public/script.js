// side nav
function nav() {
  if (document.getElementById("nav").style.display === "block") {
    document.getElementById("nav").style.display = "none";
    document.getElementsByClass("body").style.marginLeft = "0";
  } else {
    document.getElementById("nav").style.display = "block";
    document.getElementByClass("body").style.marginLeft = "250px";
  }
}

// login
function logIn(username, password) {
  // check if username or password matches anything in the database
  // username and password should not equal true in final
  if (username === true) {
    if (password === true) {
      return true;
    } else {
      alert("invalid password");
    }
  } else {
    alert("invalid username");
  }
}

// sending query from homepage to catalog searchbar
function catalog() {
  document.getElementById("searchQ").textContent;
  location.href = "catalog.html";
}

// live search in searchbar
function liveSearch(search, table) {
  var input = document.getElementById(search);
  var searchUpCase = input.ariaValueMax.toUpperCase();
  var table = document.getElementById(table);
  var tr = table.getElementsByTagName("tr");
  for (var row = 0; row < tr.length; row++) {
    var td = tr[row].getElementsByTagName("td")[0];
    if (td) {
      var txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(searchUpCase) > -1) {
        tr[row].style.display = "";
      } else {
        tr[row].style.display = "none";
      }
    }
  }
}

// show/hide checkboxes
function boxDispl() {
  if (document.getElementById("boxes").style.display === "none") {
    document.getElementById("boxes").style.display = "block";
  } else {
    document.getElementById("boxes").style.display = "none";
  }
}

// the itemName & ItemDescription boxes are checked by default
function check() {
  document.getElementById("itemName").checked = true;
  document.getElementById("itemDescription").checked = true;
}

// show/hide column
function columnVis(element) {
  if (document.getElementById(element).style.visibility === "visible") {
    document.getElementById(element).style.visibility = "collapse";
  } else {
    document.getElementById(element).style.visibility = "visible";
  }
}
