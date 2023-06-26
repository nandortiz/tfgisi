<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${ordenador.bibliotecaID}/ordenadores/${ordenador.id}</title>
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
var nombre = form.querySelector('input[name="nombre"]').value;
var descripcion = form.querySelector('input[name="descripcion"]').value;
var numSerie = form.querySelector('input[name="numSerie"]').value;
var data = JSON.stringify({
  nombre: nombre,
  descripcion: descripcion,
  numSerie: numSerie
});

console.log(data)
xhr.send(data);
alert ("Ordenador actualizado correctamente");
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
var numSerieOrdenador = form.querySelector('input[name="numSerieOrdenador"]').value;
var data = JSON.stringify({
   numSerieOrdenador: numSerieOrdenador
});
console.log(data)
xhr.send(data);
alert ("Ordenador modificado correctamente");
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
alert ("Ordenador borrado correctamente");
window.location.replace(
 "/bibliotecas/${ordenador.bibliotecaID}/ordenadores/"
);
}
</script>

<body>

<style>
.negrita{
font-weight:bold;
font-size:23px;
}
</style>

<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>

<!-- <p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br> -->
<p >   Si quiere volver a ver <b>todas los ordenadores de la biblioteca</b> pinche <a href="/bibliotecas/${ordenador.bibliotecaID}/ordenadores/">aquí </a></p> <br>

   <hr>
<b> <p>La información del ordenador ${ordenador.id} es la siguiente:</p> </b>

           <p> El id del ordenador es<b> ${ordenador.id} </b></p>
           <p> La URI del ordenador es<b> ${ordenador.url}</b> </p>
           <p> El modelo del ordenador es <b>${ordenador.nombre}</b> </p>
           <p El recurso extra es de tipo <b> ${ordenador.tipo}</b> </p>
           <p> La descripción del ordenador es <b>${ordenador.descripcion}</b> </p>
           <p> El ordenador está en la biblioteca con ID <b><a href="/bibliotecas/${ordenador.bibliotecaID}">${ordenador.bibliotecaID} </a> </b></p>
           <p> El número de serie del ordenador es <b>${ordenador.numSerie}</b> </p>
      <hr>
<b> <p>Si quiere realizar una modificación en el ordenador, tiene dos opciones: </p> </b>
 <b> <p>1) Actualizar solamente el número de serie del ordenador. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/ordenadores/' + ${ordenador.id}); return false;" id="formularioPATCH" >
       <div>
         <label for="ordenador.numSerieOrdenador">- El nuevo número de serie del ordenador (máximo 10 dígitos)</label>
         <input name="numSerieOrdenador" id="numSerieOrdenador" value="">
       </div>
       <div>
       <div style="margin-top:25px;">
         <button class="boton1" id="modificarOrdenador">Modificar ordenador</button>
       </div>
</form>

<b> <p>2) Modificar toda la información del ordenador. Introduzca: </p> </b>
 <form action="#" onSubmit="makePUTRequest('/ordenadores/${ordenador.id}'); return false;" id="formularioPUT" >
        <div>
          <label for="ordenador.nombre">- El nuevo modelo de ordenador</label>
          <input name="nombre" id="nombre" value="">
        </div>
       <div>
         <label for="ordenador.descripcion">- La nueva descripción del ordenador</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="ordenador.numSerie">- El nuevo número de serie del ordenador (máximo 10 dígitos)</label>
         <input name="numSerie" id="numSerie" value="">
       </div>
       <div>
       <div style="margin-top:25px;">
         <button class="boton1" id="modificarOrdenador">Modificar ordenador</button>
       </div>
</form>

<hr>

<form action="#" onSubmit="makeDELETERequest('${ordenador.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este libro pulse el botón </p> </b>

  <div>
  <div style="margin-top:25px;">
    <button class="boton1" id="borrarOrdenador">Borrar ordenador</button>
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