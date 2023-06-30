<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas/${reserva.id}</title>
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
var form = document.querySelector("#formularioPOSTEXTRA");
var data = `{
                       	"recursoExtraID": `+form.querySelector('input[name="recursoExtraID"]').value+`,
                    	"id": `+form.querySelector('input[name="reservaID"]').value+`


            }`;
console.log(data)
xhr.send(data);
alert ("Reserva extra realizada correctamente");
location.reload();
}
</script>

<script>
function makeDELETERequest(url){


var xhr = new XMLHttpRequest();
xhr.open("DELETE", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioDELETE");
var data = `{

            }`;
console.log(data)
xhr.send(data);
alert ("Reserva eliminada correctamente");
window.location.replace(
  "/reservas"
);
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

<!-- <p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br> -->
<p >  Si quiere volver a ver <b>todas las reservas</b> pinche <a href="/reservas"> aquí </a></p> <br>

<hr>

<b> <p>La información de la reserva ${reserva.id} es la siguiente:</p> </b>

  <p id="identificador">El ID de la reserva es <b>${reserva.id}</b> </p>
  <p id="url">La URI de la reserva es <b>${reserva.url}</b> </p>
  <p id="usuario">El ID del usuario de la reserva es <b>${reserva.usuarioID}</b> </p>
  <p id="elementoReservableID">El ID del elemento reservable de la reserva es <b>${reserva.elementoReservableID}</b> </p>
   <p id="fecha">La fecha de la reserva es <b>${reserva.fecha}</b> </p>

<hr>

<div>  <b> <p> Si esta reserva tiene recurso(s) extra asociado(s) aparecerá(n) aquí listando el nombre, la descripción, el ID, y el tipo: </p> </b><br>

<#if nombre?has_content>
<#list 0..nombre?size-1 as i>
<p> ${nombre[i]} | ${descripcion[i]} | ${recursoExtraID[i]} | ${tipo[i]} <br></p>
<#else>
     Esta reserva no tiene ningún recurso extra asociado
</#list>
</#if>

<hr>
</div>

 <b><p>¿Quiere añadir un recurso extra a la reserva ${reserva.id} realizada? </p> </b>
<p> Si quiere tener preparado en su puesto o sala reservado un recurso extra, añada la siguiente información:</p>
<form action="#" onSubmit="makePOSTRequest('/reservas'); return false;" id="formularioPOSTEXTRA" >
   <div>
    <input name="reservaID" id="reservaID" type="hidden" value="${reserva.id}">
  </div>
 <div>
    <label for="reserva.recursoExtraID">- El ID del recurso extra que quiere reservar</label>
    <input name="recursoExtraID" id="recursoExtraID" value="">
  </div>
  <div style="margin-top:25px;">
    <button class="boton1" id="creacionReservaExtra">Crear reserva extra</button>
  </div>
</form>
<b> <p> Si quiere añadir más de un recurso extra, una vez hecha la primera reserva extra, repita el proceso con otro recursoExtraID </p> </b>

<hr>
<form action="#" onSubmit="makeDELETERequest('${reserva.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta reserva pulse el botón </p> </b>


  <div style="margin-top:25px;">
    <button class="boton1" id="borrarReserva">Borrar reserva</button>
  </div>
</form>


<hr>

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