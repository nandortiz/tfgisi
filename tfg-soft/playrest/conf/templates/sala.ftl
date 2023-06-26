<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${sala.bibliotecaID}/salas/${sala.id}</title>
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
var descripcion = form.querySelector('input[name="descripcion"]').value;
var aforo = form.querySelector('input[name="aforo"]').value;
var data = JSON.stringify({
  descripcion: descripcion,
  aforo: aforo
});
console.log(data)
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
var aforoSala = form.querySelector('input[name="aforoSala"]').value;
var data = JSON.stringify({
  aforoSala: aforoSala
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
<p >   Si quiere volver a ver <b>todas las salas de la biblioteca</b> pinche <a href="/bibliotecas/${sala.bibliotecaID}/salas/">aquí </a></p> <br>

    <p >------------------------------------------------- </p><br><br>
<b> <p>La información de la sala ${sala.id} es la siguiente:</p> </b>

           <p >El id de la sala es ${sala.id} </p> <br>
             <p> La URI de la sala es <a href="${sala.url}">${sala.url} </a></p> <br>
             <p>El tipo de elemento reservable es ${sala.tipo} </p> <br>
             <p>La descripción de la sala es ${sala.descripcion} </p> <br>
             <p >La sala está en la biblioteca con ID <a href="/bibliotecas/${sala.bibliotecaID}">${sala.bibliotecaID} </a> </p> <br>
             <p >El aforo de la sala es ${sala.aforo} </p> <br>
             <p >------------------------------------------------- </p> <br>
<b> <p>Si quiere realizar una modificación en la sala, tiene dos opciones: </p> </b>
      <b> <p>1) Actualizar solamente el aforo de la sala. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/salas/' + ${sala.id}); return false;" id="formularioPATCH" >
         <div>
         <label for="sala.aforoSala">- El nuevo aforo de la sala</label>
         <input name="aforoSala" id="aforoSala" value="" type="number">
       </div>
       <div>
         <button id="modificarSala">Modificar sala</button>
       </div>
</form>

    <b> <p>2) Modificar toda la información de la sala. Introduzca: </p> </b>
     <form action="#" onSubmit="makePUTRequest('/salas/${sala.id}'); return false;" id="formularioPUT" >
       <div>
         <label for="sala.descripcion">- La nueva descripción de la sala</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="sala.aforo">- El nuevo aforo de la sala</label>
         <input name="aforo" id="aforo" value="" type="number">
       </div>
       <div>
         <button id="modificarSala">Modificar sala</button>
       </div>
     </form>

<p >------------------------------------------------- </p> <br>

<b> <p>Si quiere borrar esta sala pulse el botón </p> </b>
<form action="#" onSubmit="makeDELETERequest('${sala.url}'); return false;" id="formularioDELETE" >
  <div>
    <button id="borrarSala">Borrar sala</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>