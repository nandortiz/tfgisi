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
}
</script>


<body>


<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todos los usuarios</b> pinche <a href="/usuarios">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>
<b> <p>La información del usuario ${usuario.id} es la siguiente:</p> </b>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url">La URI del usuario es ${usuario.url} </p> <br>
  <p id="nombre">El nombre del usuario es ${usuario.nombre} </p> <br>
  <p id="apellidosUsuario">Los apellidos del usuario son ${usuario.apellidos} </p> <br>
  <p id="grado">El grado que cursa el usuario  es ${usuario.grado} </p> <br> <br>

<p >------------------------------------------------- </p> <br><br>
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

        <div>
          <button id="modificarUsuarioPut">Modificar usuario</button>
        </div>
 </form>

    <b> <p>2) Actualizar solamente el grado del usuario. Introduzca: </p> </b>
 <form action="#" onSubmit="makePATCHRequest('${usuario.url}'); return false;" id="formularioPATCH" >
       <div>
         <label for="usuario.grado">- El nuevo grado del usuario</label>
         <input name="grado" id="gradoPatch" value="">
       </div>
        <div>
         <button id="modificarUsuarioPatch">Modificar usuario</button>
       </div>
</form>


     <p >------------------------------------------------- </p> <br>


<b> <p>Si quiere borrar este usuario pulse el botón </p> </b>
<form action="#" onSubmit="makeDELETERequest('${usuario.url}'); return false;" id="formularioDELETE" >
       <div>
         <button id="borrarUsuario">Borrar usuario</button>
       </div>
</form>
     <p >------------------------------------------------- </p> <br>

</body>
</html>