<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${bibliotecaID}/puestos/</title>
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
var form = document.querySelector("#formulario");
var data = `{
                    "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "tipo": "`+form.querySelector('input[name="tipo"]').value+`",
                    "bibliotecaID": "`+form.querySelector('input[name="bibliotecaID"]').value+`",
                    "info": "`+form.querySelector('input[name="info"]').value+`"

            }`;
console.log(data);
xhr.send(data);
}
</script>

<body>

<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>

<div> <b> La lista de puestos de la biblioteca ${bibliotecaID} es: </b><br> <br>
    <#list puestos as puesto>
        <p id="identificador" >  El ID del puesto es ${puesto.id} </p> <br>
         <p id="url"> Pinche en la URI del puesto para obtener más información <a href="${puesto.url}">${puesto.url} </a></p> <br>

         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/${bibliotecaID}/puestos'); return false;" id="formulario" >
  <b> <p>Este formulario es para añadir un puesto a la biblioteca. Introduzca: </p> </b>
  <div>
    <label for="puesto.descripcion">- Descripción del puesto a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
  <div>
     <input name="tipo" id="tipo" type="hidden" value="P">
   </div>
  <div>
     <label for="puesto.bibliotecaID">- La biblioteca a la que pertenece el puesto a crear</label>
     <input name="bibliotecaID" id="bibliotecaID" value="">
    </div>
  <div>
     <label for="puesto.info">- La información del puesto a crear</label>
     <input name="info" id="info" value="">
  </div>

  <div>
    <button id="creacion">Crear puesto</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


</body>
</html>