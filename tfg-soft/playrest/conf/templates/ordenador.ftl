<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${ordenador.bibliotecaID}/ordenadores/${ordenador.id}</title>
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
var nombre = form.querySelector('input[name="nombre"]').value;
var descripcion = form.querySelector('input[name="descripcion"]').value;
var numSerie = form.querySelector('input[name="numSerie"]').value;
var data = JSON.stringify({
  nombre: nombre,
  descripcion: descripcion,
  numSerie: numSerie
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
var numSerieOrdenador = form.querySelector('input[name="numSerieOrdenador"]').value;
var data = JSON.stringify({
   numSerieOrdenador: numSerieOrdenador
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
<p >   Si quiere volver a ver <b>todas los ordenadores de la biblioteca</b> pinche <a href="/bibliotecas/${ordenador.bibliotecaID}/ordenadores/">aquí </a></p> <br>

    <p >------------------------------------------------- </p><br><br>
<b> <p>La información del ordenador ${ordenador.id} es la siguiente:</p> </b>

           <p> El id del ordenador es ${ordenador.id} </p> <br>
           <p> La URI del ordenador es ${ordenador.url} </p> <br>
           <p> El modelo del ordenador es ${ordenador.nombre} </p> <br>
           <p El tipo del recurso extra es ${ordenador.tipo} </p> <br>
           <p> La descripción del ordenador es ${ordenador.descripcion} </p> <br>
           <p> El ordenador está en la biblioteca con ID <a href="/bibliotecas/${ordenador.bibliotecaID}">${ordenador.bibliotecaID} </a> </p> <br>
           <p> El número de serie del ordenador es ${ordenador.numSerie} </p> <br>
      <p >------------------------------------------------- </p> <br>
<b> <p>Si quiere realizar una modificación en el ordenador, tiene dos opciones: </p> </b>
 <b> <p>1) Actualizar solamente el número de serie del ordenador. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/ordenadores/' + ${ordenador.id}); return false;" id="formularioPATCH" >
       <div>
         <label for="ordenador.numSerieOrdenador">- El nuevo número de serie del ordenador (máximo 10 dígitos)</label>
         <input name="numSerieOrdenador" id="numSerieOrdenador" value="">
       </div>
       <div>
         <button id="modificarOrdenador">Modificar ordenador</button>
       </div>
</form>

<b> <p>2) Modificar toda la información del ordenador. Introduzca: </p> </b>
 <form action="#" onSubmit="makePUTRequest('/ordenadores/${ordenador.id}'); return false;" id="formularioPUT" >
        <div>
          <label for="ordenador.nombre">- El nuevo modelo de ordenador</label>
          <input name="nombre" id="nombre" value="">
        </div>
       <div>
         <label for="ordenador.descripcion">- La nueva descripción del ordenador</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="ordenador.numSerie">- El nuevo número de serie del ordenador (máximo 10 dígitos)</label>
         <input name="numSerie" id="numSerie" value="">
       </div>
       <div>
         <button id="modificarOrdenador">Modificar ordenador</button>
       </div>
</form>

<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makeDELETERequest('${ordenador.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este libro pulse el botón </p> </b>

  <div>
    <button id="borrarLibro">Borrar libro</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>