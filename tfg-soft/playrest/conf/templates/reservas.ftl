<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas</title>
</head>
<script>
function makePOSTRequest(url){


var xhr = new XMLHttpRequest();
xhr.open("POST", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioPOST");
var data = `{
                        "usuarioID":`+form.querySelector('input[name="usuarioID"]').value+`,
                    	"elementoReservableID": `+form.querySelector('input[name="elementoReservableID"]').value+`,
                    	"fecha": "`+form.querySelector('input[name="fecha"]').value+`"
             }`;
console.log(data)
xhr.send(data);
alert ("Reserva realizada correctamente");
location.reload();
}
</script>

<body>

<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>

<!-- <p >  Si quiere volver al <b>inicio</b> pinche <a href="/inicio"> aquí </a></p> <br>
<p >   Si quiere ver <b>todas las reservas</b> pinche <a href="/reservas">aquí </a></p> <br>
 <p >   Si quiere ver <b>todos las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br> -->

<div>
<!-- <b> La lista de todas las reservas es: </b><br> <br>
  <#list reservas as reserva>
     <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
    <p id="url">Si quieres saber más información de la reserva ${reserva.id} pulsa <a href="/reservas/${reserva.id}">aquí</a></p> <br>
<hr>
   </#list> -->
  </div>

<b> <p>Este formulario es para crear una reserva. Introduzca: </p> </b>
<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/reservas'); return false;" id="formularioPOST" >
    <div>
    <label for="reserva.usuarioID">- El ID del usuario que hace la reserva</label>
    <input name="usuarioID" id="usuarioID" value="">
  </div>
  <div>
      <label for="reserva.fecha">- La fecha de la reserva a realizar en el formato del ejemplo</label>
      <input name="fecha" id="fecha" value="aaaa-mm-dd hh:mm:ss">
    </div>
   <div>
       <label for="reserva.elementoReservableID">- El ID del elemento reservable que desea reservar</label>
       <input name="elementoReservableID" id="elementoReservableID" value="">
     </div>

  <div>
  <div style="margin-top:25px;">
    <button class="boton1" id="creacionReserva">Crear reserva</button>
  </div>
</form>

<hr>

<div >
<p><b> La lista de reservas es:</b></p>
<table border="1"style="left:50%;margin-left:-20%;position:absolute;">
            <tr>
                <th>ID</th>
                <th>URI</th>
            </tr>
  <#list reservas as reserva>
            <tr class="fila_impar">
                <td>${reserva.id}</td>
                <td><a href="${reserva.url}">${reserva.url} </a></td>
            </tr>
   </#list>

</table>

</div>
<style>
table {
    width:40%;
    font:normal 25px Arial;
    text-align:center;
    border-collapse:collapse;
}

table th {
    font:bold 25px Arial;
    background-color:lightblue;
}

.fila_impar {
    background-color:#c0c0c0;
}

.fila_par {
    background-color:#fffff;
}

.fila_resaltada {
    color:blue;
    background-color:red;
}
p {
    font-size:20pt;
}

h1 {
    font-size:30pt;
}
label {
    font-size:15pt;
}
.boton1 {
    font-size:15pt;
}
</style>



</body>
</html>