function startTime() {
    var date = new Date();
    var n = date.toDateString();
    var time = date.toLocaleTimeString();

    document.getElementById('time').innerHTML = n + ' - ' + time;
    tt = display_c();
}
function display_c() {
    var refresh = 1000;
    mytime = setTimeout('startTime()', refresh);
}
function typeChange() {
    var selectBox = document.getElementById('selectBox');
    var inputBox = document.getElementById('inputBox');
    selectBox.value === 'date' ? inputBox.type = 'date' : inputBox.type = 'text';
}

document.getElementById('inputBox').addEventListener('change', typeChange);

function view() {
    var x = document.getElementById("view-div");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}
function find() {
    var x = document.getElementById("find-div");
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}

//reload iframe
function reload() {
    document.getElementById('results-frame').src += '';
}

//Led animation
$( function() {
  var $winHeight = $( window ).height()
  $( '.container' ).height( $winHeight );
});

//Buttons tooltip
$(document).ready(function(){
  $('[data-toggle="tooltip"]').tooltip(); 
});

