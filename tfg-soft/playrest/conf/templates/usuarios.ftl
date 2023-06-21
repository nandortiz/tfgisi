<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios</title>
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


var form = document.querySelector("#formulario");
var data = `{
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "apellidos": "`+form.querySelector('input[name="apellidos"]').value+`",
                    "grado": "`+form.querySelector('input[name="grado"]').value+`"
            }`;

console.log(data)
xhr.send(data);
}
</script>
<body>
<p >  Si quiere volver al <b>inicio</b> pinche <a href="/inicio"> aquí </a></p> <br>
<p >  Si quiere ver <b>todas las reservas</b> pinche <a href="/reservas">aquí </a></p> <br>
<p >  Si quiere ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br>
 <p >------------------------------------------------- </p> <br>

  <div> <b> La lista de usuarios es: </b><br> <br>
  <#list usuarios as usuario>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url"> La URI del usuario es <a href="${usuario.url}">${usuario.url} </a> </p> <br>

  <p >------------------------------------------------- </p> <br>
   </#list>
  </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/usuarios'); return false;" id="formulario" >
  <b> <p>Este formulario es añadir para añadir un usuario. Introduzca: </p> </b>
  <div>
    <label for="usuario.nombre">- El nombre del usuario a crear</label>
    <input name="nombre" id="nombre" value="">
  </div>
   <div>
      <label for="usuario.apellidos">- Los apellidos del usuario a crear</label>
      <input name="apellidos" id="apellidos" value="">
    </div>
  <div>
    <label for="usuario.grado">- El grado que cursa el usuario</label>
    <input name="grado" id="grado" value="">
  </div>
  <div>
    <button id="creacion">Crear usuario</button>
  </div>
</form>
<p >-------------------------------------------------------------------------------</p> <br>



</body>
</html>