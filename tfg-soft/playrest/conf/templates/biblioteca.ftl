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
  <p >  Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todos los puestos</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/puestos/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todas las salas</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/salas/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todos los libros</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/libros/"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todos los ordenadores</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/ordenadores/"> aquí </a></p> <br>
  <p >------------------------------------------------- </p> <br><br>
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
    <button id="modificar">Modificar biblioteca</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>


<form action="#" onSubmit="makeDELETERequest('${biblioteca.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta biblioteca pulse el siguiente botón:</p> </b>

  <div>
    <button id="borrar">Borrar biblioteca</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>