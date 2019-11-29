function vypis(udaje){
    console.log(udaje);
  }
  
  $.getJSON( "http://api.openweathermap.org/data/2.5/weather",
             {lat:48.7557,lon:21.9184,units:"metric",APPID:"8641355d0bdfa52a49f4e9a42560adf0"},
             spracuj);
             
function spracuj(udaje){
    const pocasieText = `Teplota: ${udaje.main.temp}, Tlak: ${udaje.main.pressure}, Oblačnosť: ${udaje.clouds.all}`;
    $("#pocasie").html(pocasieText);
   }


function initate() {
    var style1 = document.getElementById("Mod1");
    var style2 = document.getElementById("Mod2");

    style1.onclick = function () {
           swapStyleSheet("style.css");
     };
    style2.onclick = function () {
            swapStyleSheet("print.css");
     };
}