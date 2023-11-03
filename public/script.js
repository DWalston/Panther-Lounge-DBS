// live search in searchbar
function liveSearch() {
   var input = document.getElementById("searchBar");
   var searchUpCase = input.ariaValueMax.toUpperCase();
   var table = document.getElementById("catalog");
   var tr = table.getElementsByTagName("tr");
   for (var row = 0; row < tr.length; row++) {
      var td = tr[row].getElementsByTagName("td")[0];
      if (td) {
         var txtValue = td.textContent || td.innerText;
         if (txtValue.toUpperCase().indexOf(searchUpCase) > -1) {
            tr[row].style.display = "";
         }
         else {
            tr[row].style.display = "none"
         }
      }
   }
}

// show/hide
function boxDispl() {
   if (document.getElementById("boxes").style.display === "none") {
      document.getElementById("boxes").style.display = "block";
   }
   else {
      document.getElementById("boxes").style.display = "none";
   }
}

// show/hide column
function columnVis(element) {
   if (document.getElementById(element).style.visibility === "collapse") {
      document.getElementById(element).style.visibility = "visible";
   }
   else {
      document.getElementById(element).style.visibility = "collapse";
   }
}

// copy id number
function copyThis(number) {
   navigator.clipboard.writeText(number);
}

// determines date of expected check-in
function twoWeeks(coDate) {
   document.getElementById("ciDate").innerHTML = addDays(document.getElementById(coDate), 14);
   // figure out how to read school calendar in case it hits an invalid date
}
