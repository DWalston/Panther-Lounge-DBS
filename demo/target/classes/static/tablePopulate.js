function populate () {
   const xhttp = new XMLHttpRequest();
   xhttp.onload = function() {
      let table = document.getElementById("catalog");
      //table.insertRow(table.rows.length).insertCell(0).innerHTML = this.responseText;
      let obj = $.parseJSON(this.responseText);
      $.each(obj, function (key, value) { 
         let newRow = table.insertRow(table.rows.length); 
         newRow.insertCell(0).innerHTML = value.name;
         newRow.insertCell(1).innerHTML = value.type;
         newRow.insertCell(2).innerHTML = value.series;
         newRow.insertCell(3).innerHTML = "n/a";
         newRow.insertCell(4).innerHTML = value.status;
         newRow.insertCell(5).innerHTML = value.rating;
         newRow.insertCell(6).innerHTML = "n/a";
         newRow.insertCell(7).innerHTML = value.desc;
         newRow.insertCell(8).innerHTML = value.id;
         
      });
   }
   xhttp.open("GET", "/findAll?table=items");
   xhttp.send();
}