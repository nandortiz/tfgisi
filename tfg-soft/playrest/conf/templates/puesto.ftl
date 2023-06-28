<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${puesto.bibliotecaID}/puestos/${puesto.id}</title>
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
var info = form.querySelector('input[name="info"]').value;
var data = JSON.stringify({
  descripcion: descripcion,
  info: info
});

console.log(data)
xhr.send(data);
alert ("Puesto actualizado correctamente");
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
var infoPuesto = form.querySelector('input[name="infoPuesto"]').value;
var data = JSON.stringify({
   infoPuesto: infoPuesto
});
console.log(data)
xhr.send(data);
alert ("Puesto modificado correctamente");
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
alert ("Puesto borrado correctamente");
window.location.replace(
  "/bibliotecas/${puesto.bibliotecaID}/puestos/"
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


<!-- <p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todas los puestos de la biblioteca</b> pinche <a href="/bibliotecas/${puesto.bibliotecaID}/puestos/">aquí </a></p> <br> -->

<b> <p>La información del puesto ${puesto.id} es la siguiente:</p> </b>

           <p >El id del puesto es <b>${puesto.id} </b></p>
           <p> La URI del puesto es <b>${puesto.url}</b> </p>
           <p>El tipo de elemento reservable es <b>${puesto.tipo} </b></p>
           <p>La descripción del puesto es <b>${puesto.descripcion} </b></p>
           <p >El puesto está en la biblioteca con ID <b><a href="/bibliotecas/${puesto.bibliotecaID}">${puesto.bibliotecaID} </a></b> </p>
           <p >La info del puesto es <b>${puesto.info} </b></p>
      <hr>
<b> <p>Si quiere realizar una modificación en el puesto, tiene dos opciones: </p> </b>
 <b> <p>1) Actualizar solamente la info del puesto. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/puestos/' + ${puesto.id}); return false;" id="formularioPATCH" >
       <div>
         <label for="puesto.infoPuesto">- La nueva info del puesto</label>
         <input name="infoPuesto" id="infoPuesto" value="">
       </div>
       <div>
       <div style="margin-top:25px;">
         <button  class="boton1" id="modificarPuesto">Modificar puesto</button>
       </div>
</form>

<b> <p>2) Modificar toda la información del puesto. Introduzca: </p> </b>
 <form action="#" onSubmit="makePUTRequest('/puestos/${puesto.id}'); return false;" id="formularioPUT" >
       <div>
         <label for="puesto.descripcion">- La nueva descripción del puesto</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="puesto.info">- La nueva info del puesto</label>
         <input name="info" id="info" value="">
       </div>
       <div>
       <div style="margin-top:25px;">
         <button  class="boton1" id="modificarPuesto">Modificar puesto</button>
       </div>
</form>

<hr>

<form action="#" onSubmit="makeDELETERequest('${puesto.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este puesto pulse el botón </p> </b>

  <div>
  <div style="margin-top:25px;">
    <button  class="boton1" id="borrarPuesto">Borrar puesto</button>
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