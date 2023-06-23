<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${bibliotecaID}/salas/</title>
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
                    "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "tipo": "`+form.querySelector('input[name="tipo"]').value+`",
                    "bibliotecaID": "`+form.querySelector('input[name="bibliotecaID"]').value+`",
                    "aforo": "`+form.querySelector('input[name="aforo"]').value+`"

            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>

<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >  Si quiere volver a ver <b>la información de la biblioteca ${bibliotecaID}</b> pinche <a href="/bibliotecas/${bibliotecaID}"> aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br>
 <p >------------------------------------------------- </p><br> <br>
<div> <b> La lista de salas de la biblioteca ${bibliotecaID} es:</b><br> <br>
<#list salas as sala>
  <p id="identificador">El ID de la sala es ${sala.id} </p> <br>
  <p id="url"> Si quiere obtener más información de esta sala pinche <a href="/bibliotecas/${bibliotecaID}/salas/${sala.id}">aquí</a></p> <br>

 <p >------------------------------------------------- </p> <br>
 </#list>
</div>

<b> <p>Este formulario es para añadir una sala a la biblioteca. Introduzca: </p> </b>
<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/${bibliotecaID}/salas'); return false;" id="formulario" >
  <div>
    <label for="sala.descripcion">- La descripción de la sala a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
 <div>
      <input name="tipo" id="tipo" type="hidden" value="S">
    </div>
  <div>
      <label for="sala.bibliotecaID">- El ID de la biblioteca a la que pertenece la sala a crear</label>
      <input name="bibliotecaID" id="bibliotecaID" value="">
   </div>
    <div>
        <label for="sala.aforo">- El número de personas que caben en la sala sala a crear</label>
          <input name="aforo" id="aforo" value="" type="number">
    </div>
  <div>
    <button id="creacionSala">Crear sala</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


</body>
</html>