<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${bibliotecaID}/ordenadores/</title>
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
                    "numSerie": "`+form.querySelector('input[name="numSerie"]').value+`"

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

<div> <b> La lista de ordenadores de la biblioteca ${bibliotecaID} es: </b><br> <br>
    <#list ordenadores as ordenador>
        <p id="identificador" >  El ID del ordenador es ${ordenador.id} </p> <br>
         <p id="url"> Si quiere obtener más información de este ordenador pinche <a href="/bibliotecas/${bibliotecaID}/ordenadores/${ordenador.id}">aquí</a></p> <br>

         <p >------------------------------------------------- </p> <br>
    </#list>
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/${bibliotecaID}/ordenadores'); return false;" id="formularioPOST" >
  <b> <p>Este formulario es para añadir un ordenador a la biblioteca. Introduzca: </p> </b>
  <div>
      <label for="ordenador.nombre">- Modelo del ordenador a crear</label>
      <input name="nombre" id="nombre" value="">
    </div>
  <div>
    <label for="ordenador.descripcion">- Descripción del ordenador a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
  <div>
     <input name="tipo" id="tipo" type="hidden" value="O">
   </div>
  <div>
     <label for="ordenador.bibliotecaID">- La biblioteca a la que pertenece el ordenador a crear</label>
     <input name="bibliotecaID" id="bibliotecaID" value="">
    </div>
  <div>
     <label for="ordenador.numSerie">- El número de serie del ordenador a crear (máximo 10 caracteres)</label>
     <input name="numSerie" id="numSerie" value="">
  </div>

  <div>
    <button id="creacion">Crear ordenador</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


</body>
</html>