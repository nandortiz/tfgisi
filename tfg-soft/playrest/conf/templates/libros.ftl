<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${bibliotecaID}/libros/</title>
</head>

<script>
function makePOSTRequest(url){
console.log("Iniciamos la request POST");

var xhr = new XMLHttpRequest();
xhr.open("POST", url);

xhr.setRequestHeader("Accept", "application/json");
xhr.setRequestHeader("Content-Type", "application/json");

xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.status);
      console.log(xhr.responseText);
   }};
var form = document.querySelector("#formularioPOST");
var data = `{
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "tipo": "`+form.querySelector('input[name="tipo"]').value+`",
                    "bibliotecaID": "`+form.querySelector('input[name="bibliotecaID"]').value+`",
                    "isbn": "`+form.querySelector('input[name="isbn"]').value+`"

            }`;
console.log(data);
xhr.send(data);
}
</script>

<body>

<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >  Si quiere volver a ver <b>la información de la biblioteca ${bibliotecaID}</b> pinche <a href="/bibliotecas/${bibliotecaID}"> aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>

<div> <b> La lista de libros de la biblioteca ${bibliotecaID} es: </b><br> <br>
    <#list libros as libro>
        <p id="identificador" >  El ID del libro es ${libro.id} </p> <br>
         <p id="url"> Si quiere obtener más información de este libro pinche <a href="/bibliotecas/${bibliotecaID}/libros/${libro.id}">aquí</a></p> <br>

         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/${bibliotecaID}/libros'); return false;" id="formularioPOST" >
  <b> <p>Este formulario es para añadir un libro a la biblioteca. Introduzca: </p> </b>
  <div>
      <label for="libro.nombre">- Título del libro a crear</label>
      <input name="nombre" id="nombre" value="">
    </div>
  <div>
    <label for="libro.descripcion">- Descripción del libro a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
  <div>
     <input name="tipo" id="tipo" type="hidden" value="L">
   </div>
  <div>
     <label for="libro.bibliotecaID">- La biblioteca a la que pertenece el libro a crear</label>
     <input name="bibliotecaID" id="bibliotecaID" value="">
    </div>
  <div>
     <label for="libro.isbn">- La información del libro a crear</label>
     <input name="isbn" id="isbn" value="">
  </div>

  <div>
    <button id="creacion">Crear libro</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


</body>
</html>