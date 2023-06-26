<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Bibliotecas/${biblioteca.id}</title>
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
var data = `{
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`"
            }`;
console.log(data)
xhr.send(data);
alert ("Biblioteca actualizada correctamente");
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
alert ("Biblioteca borrada correctamente");
window.location.replace(
  "/bibliotecas/"
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

  <!-- <p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
  <p >  Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/"> aquí </a></p> <br> -->
  <p >  Si quiere ver <b>todos los puestos</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/puestos/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todas las salas</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/salas/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todos los libros</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/libros/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todos los ordenadores</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/ordenadores/"> aquí </a></p> <br>
<hr>
   <b> <p>Esta es la información de la biblioteca ${biblioteca.id}:</p> </b>
  <p id="identificador" >  El ID de la biblioteca es ${biblioteca.id} </p> <br>
  <p id="url">La URI de la biblioteca es ${biblioteca.url} </p> <br>
  <p id="nombre">EL nombre de la biblioteca es ${biblioteca.nombre} </p> <br>
  <p id="descripcion">La descripción de la biblioteca es ${biblioteca.descripcion} </p> <br>








<form action="#" onSubmit="makePUTRequest('${biblioteca.url}'); return false;" id="formularioPUT" >
 <b> <p>Este formulario es para modificar el nombre y la descripción de la biblioteca en cuestión. Introduzca: </p> </b>
  <div>
    <label for="biblioteca.nombre">- El nuevo nombre de la biblioteca</label>
    <input name="nombre" id="nombre" value="">
  </div>
  <div>
    <label for="biblioteca.descripcion">- La nueva descripción de la biblioteca</label>
    <input name="descripcion" id="descripcion" value="">
  </div>

  <div>
    <button class="boton1" id="modificar">Modificar biblioteca</button>
  </div>
</form>
<hr>


<form action="#" onSubmit="makeDELETERequest('${biblioteca.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta biblioteca pulse el siguiente botón:</p> </b>

  <div>
    <button class="boton1" id="borrar">Borrar biblioteca</button>
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