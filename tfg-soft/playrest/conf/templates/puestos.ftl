<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${bibliotecaID}/puestos/</title>
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
                    "tipo": "`+form.querySelector('input[name="tipo"]').value+`"
                    "bibliotecaID": "`+form.querySelector('input[name="bibliotecaID"]').value+`"
                    "infoPuesto": "`+form.querySelector('input[name="infoPuesto"]').value+`"

            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>

<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >   Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br>
 <p >------------------------------------------------- </p><br> <br>
<div> <b> La lista de puestos es: </b><br> <br>
<#list puestos as puesto>
  <p id="identificador">El ID del puesto es ${puesto.id} </p> <br>
 <p >   Si quiere acceder a la <b>información relativa del puesto ${puesto.id}</b> pinche <a href="/bibliotecas/${bibliotecaID}/puestos/${puesto.id}">aquí </a></p> <br>

 <p >------------------------------------------------- </p> <br>
 </#list>
</div>


<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/${bibliotecaID}/puestos'); return false;" id="formulario" >

  <div>
    <label for="puesto.descripcion">Introduzca la descripción del puesto a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
  <div>
      <label for="puesto.tipo">Introduzca el tipo de elemento reservable a crear</label>
      <input name="tipo" id="tipo" value="">
    </div>
  <div>
      <label for="puesto.bibliotecaID">Introduzca el ID de la biblioteca a la que pertenece el puesto a crear</label>
      <input name="bibliotecaID" id="bibliotecaID" value="">
   </div>
    <div>
        <label for="puesto.infoPuesto">Introduzca la información relativa al puesto a crear</label>
          <input name="infoPuesto" id="infoPuesto" value="">
    </div>
  <div>
    <button id="creacionPuesto">Crear puesto</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


</body>
</html>