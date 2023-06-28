<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${sala.bibliotecaID}/salas/${sala.id}</title>
</head>

<script>
function makePUTRequest(url){


var xhr = new XMLHttpRequest();
xhr.open("PUT", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioPUT");
var descripcion = form.querySelector('input[name="descripcion"]').value;
var aforo = form.querySelector('input[name="aforo"]').value;
var data = JSON.stringify({
  descripcion: descripcion,
  aforo: aforo
});
console.log(data)
xhr.send(data);
alert ("Sala actualizado correctamente");
location.reload();
}
</script>


<script>
function makePATCHRequest(url){


var xhr = new XMLHttpRequest();
xhr.open("PATCH", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioPATCH");
var aforoSala = form.querySelector('input[name="aforoSala"]').value;
var data = JSON.stringify({
  aforoSala: aforoSala
});
console.log(data)
xhr.send(data);
alert ("Sala modificado correctamente");
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
alert ("Sala borrado correctamente");
window.location.replace(
  "/bibliotecas/${sala.bibliotecaID}/salas/"
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
<p >   Si quiere volver a ver <b>todas las salas de la biblioteca</b> pinche <a href="/bibliotecas/${sala.bibliotecaID}/salas/">aquí </a></p> <br>

    <hr>
<b> <p>La información de la sala ${sala.id} es la siguiente:</p> </b>

           <p >El id de la sala es<b> ${sala.id} </b></p>
             <p> La URI de la sala es <b><a href="${sala.url}">${sala.url} </a></b></p>
             <p>El tipo de elemento reservable es<b> ${sala.tipo} </b></p>
             <p>La descripción de la sala es <b>${sala.descripcion} </b></p>
             <p >La sala está en la biblioteca con ID <b><a href="/bibliotecas/${sala.bibliotecaID}">${sala.bibliotecaID} </a></b> </p>
             <p >El aforo de la sala es <b>${sala.aforo} </b></p>
             <hr>
<b> <p>Si quiere realizar una modificación en la sala, tiene dos opciones: </p> </b>
      <b> <p>1) Actualizar solamente el aforo de la sala. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/salas/' + ${sala.id}); return false;" id="formularioPATCH" >
         <div>
         <label for="sala.aforoSala">- El nuevo aforo de la sala</label>
         <input name="aforoSala" id="aforoSala" value="" type="number">
       </div>
       <div>
       <div style="margin-top:25px;">
         <button class="boton1" id="modificarSala">Modificar sala</button>
       </div>
</form>

    <b> <p>2) Modificar toda la información de la sala. Introduzca: </p> </b>
     <form action="#" onSubmit="makePUTRequest('/salas/${sala.id}'); return false;" id="formularioPUT" >
       <div>
         <label for="sala.descripcion">- La nueva descripción de la sala</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="sala.aforo">- El nuevo aforo de la sala</label>
         <input name="aforo" id="aforo" value="" type="number">
       </div>
       <div>
       <div style="margin-top:25px;">
         <button class="boton1" id="modificarSala">Modificar sala</button>
       </div>
     </form>

<hr>

<b> <p>Si quiere borrar esta sala pulse el botón </p> </b>
<form action="#" onSubmit="makeDELETERequest('${sala.url}'); return false;" id="formularioDELETE" >
  <div>
  <div style="margin-top:25px;">
    <button class="boton1" id="borrarSala">Borrar sala</button>
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