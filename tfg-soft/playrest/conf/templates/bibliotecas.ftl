<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Bibliotecas</title>
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
<!-- var form = document.querySelector("#formulario"); -->
<!-- var disponibilidad= form.querySelector('input[name="disponibilidad"]').value -->
<!-- console.log(disponibilidad) -->
<!-- var arrayDisp = disponibilidad.split(','); -->
<!-- var arrayBienDisp= arrayDisp.join('","'); -->
<!-- console.log(arrayDisp); -->

var data = `{
               "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
              "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`",

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
        <p id="identificador" >  El ID de la biblioteca es ${Biblioteca.id} </p> <br>
         <p id="url"> La URI de la biblioteca es <a href="${Biblioteca.getUrl()}">${Biblioteca.getUrl()} </a></p> <br>
         <p id="nombre">EL nombre de la biblioteca es ${biblioteca.nombre} </p> <br>
         <p id="descripcion">La descripción de la biblioteca es ${biblioteca.descripcion} </p> <br>
         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas'); return false;" id="formulario" >
  <div>
    <label for="biblioteca.nombre">Introduzca el nombre de la biblioteca</label>
    <input name="nombre" id="nombre" value="">
  </div>
  <div>
    <label for="Biblioteca.descripcion">Introduzca la descripción de la biblioteca</label>
    <input name="descripcion" id="descripcion" value="">
  </div>

  <div>
    <button id="creacion">Crear biblioteca</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>




</body>
</html>