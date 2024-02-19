function checkIn() {
  // warn the user that the item will not be officially "checked in"
  alert(
    "Thank you for checking in this item. This item will not be officially marked as 'checked in' until an officer confirms the item is returned"
  );
}

function checkedOut() {
  // if the user has checked out an item, the item's information will be filled out automatically
  // otherwise, a message stating that the member has not checked out anything will be displayed
}

function item() {
  // retrives and fills in item information such as name, ID, & condition
}


// copy id number
function copyThis(number) {
  navigator.clipboard.writeText(number);
}

// date autofill
function dates() {
  var full = new Date();
  var day = full.getDate();
  var month = full.getMonth() + 1;
  document.getElementById("coDate").value =
    full.getFullYear() + "-" + month + "-" + day;
  // Jan, Mar, May, Jul, Aug, Oct, & Dec
  if (
    (day + 14 > 31 && month === 1) ||
    month === 3 ||
    month === 5 ||
    month === 8 ||
    month === 10
  ) {
    day = day - 17;
    if (month === 12) {
      month = month - 11;
    } else {
      month = month + 1;
    }
  }
  // Apr, Jun, Sept, & Nov
  else if (
    (day + 14 > 30 && month === 2) ||
    month === 4 ||
    month === 6 ||
    month === 9 ||
    month === 11
  ) {
    day = day - 16;
    month = month + 1;
  }
  // Feb, leap
  else if (full.getFullYear() % 2 && day + 14 > 29 && month === 2) {
    day = day - 15;
    month = month + 1;
  }
  // Feb
  else {
    day = day - 14;
    month = month++;
  }
  document.getElementById("ciDate").value =
    full.getFullYear() + "-" + month + "-" + day;
}
