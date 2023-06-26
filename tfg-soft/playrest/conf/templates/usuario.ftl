<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios/${usuario.id} </title>
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
var apellidos = form.querySelector('input[name="apellidos"]').value;
var grado = form.querySelector('input[name="grado"]').value;
var data = JSON.stringify({
  nombre: nombre,
  apellidos: apellidos,
  grado: grado
});
console.log(data);
xhr.send(data);
alert ("Usuario actualizado correctamente");
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
var grado = form.querySelector('input[name="grado"]').value;
var data = JSON.stringify({
   grado: grado
});
console.log(data)
xhr.send(data);
alert ("Usuario modificado correctamente");
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
alert ("Usuario borrado correctamente");
window.location.replace(
  "/usuarios"
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

<!-- CABECERA -->
<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>
<!---- FIN CABECERA -->


<b> <p>La información del usuario ${usuario.id} es la siguiente:</p> </b>
  <p id="identificador">El ID del usuario es <b>${usuario.id}</b></p>
  <p id="url">La URI del usuario es <b>${usuario.url}</b> </p>
  <p id="nombre">El nombre del usuario es <b>${usuario.nombre}</b> </p>
  <p id="apellidosUsuario">Los apellidos del usuario son <b>${usuario.apellidos}</b> </p>
  <p id="grado">El grado que cursa el usuario  es <b>${usuario.grado}</b> </p>

<hr>
<b> <p>Si quiere realizar una modificación en el usuario, tiene dos opciones: </p> </b>
<b> <p>1) Modificar toda la información del usuario. Introduzca: </p> </b>
 <form action="#" onSubmit="makePUTRequest('${usuario.url}'); return false;" id="formularioPUT" >
        <div>
          <label for="usuario.nombre">- El nuevo nombre del usuario</label>
          <input name="nombre" id="nombre" value="">
        </div>
        <div>
          <label for="usuario.apellidos">- Los nuevos apellidos del usuario</label>
          <input name="apellidos" id="apellidos" value="">
        </div>
        <div>
            <label for="usuario.grado">- El nuevo grado que cursa el usuario</label>
            <input name="grado" id="grado" value="">
        </div>

        <div style="margin-top:25px;">
          <button class="boton1" id="modificarUsuarioPut">Modificar usuario</button>
        </div>
 </form>

    <b> <p>2) Actualizar solamente el grado del usuario. Introduzca: </p> </b>
 <form action="#" onSubmit="makePATCHRequest('${usuario.url}'); return false;" id="formularioPATCH" >
       <div>
         <label for="usuario.grado">- El nuevo grado del usuario</label>
         <input name="grado" id="gradoPatch" value="">
       </div>
        <div style="margin-top:25px;">
         <button class="boton1" id="modificarUsuarioPatch">Modificar usuario</button>
       </div>
</form>


<hr>


<b> <p>Si quiere borrar este usuario pulse el botón </p> </b>
<form action="#" onSubmit="makeDELETERequest('${usuario.url}'); return false;" id="formularioDELETE" >
       <div style="margin-top:25px;">
         <button class="boton1" id="borrarUsuario">Borrar usuario</button>
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