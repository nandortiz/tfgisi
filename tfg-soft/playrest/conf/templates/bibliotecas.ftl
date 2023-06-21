<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/</title>
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
                   "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`"
            }`;

console.log(data)
xhr.send(data);
}
</script>

<body>
<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >  Si quiere ver <b>todas las reservas</b> pinche <a href="/reservas"> aquí </a></p> <br>
<p >   Si quiere ver <b>todos los usuarios</b> pinche <a href="/usuarios">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>

<div> <b> La lista de bibliotecas es: </b><br> <br>
    <#list bibliotecas as biblioteca>
        <p id="identificador" >  El ID de la biblioteca es ${biblioteca.id} </p> <br>
         <p id="url"> La URI de la biblioteca es <a href="${biblioteca.url}">${biblioteca.url} </a></p> <br>

         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/'); return false;" id="formulario" >
  <b> <p>Este formulario es para añadir una biblioteca. Introduzca: </p> </b>
  <div>
    <label for="biblioteca.nombre">- Nombre de la biblioteca a crear</label>
    <input name="nombre" id="nombre" value="">
  </div>
  <div>
    <label for="biblioteca.descripcion">- Descripción de la biblioteca a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>

  <div>
    <button id="creacion">Crear biblioteca</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>




</body>
</html>