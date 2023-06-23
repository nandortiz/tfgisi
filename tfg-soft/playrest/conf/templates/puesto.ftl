<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${puesto.bibliotecaID}/puestos/${puesto.id}</title>
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
var info = form.querySelector('input[name="info"]').value;
var data = JSON.stringify({
  descripcion: descripcion,
  info: info
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
var infoPuesto = form.querySelector('input[name="infoPuesto"]').value;
var data = JSON.stringify({
   infoPuesto: infoPuesto
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
<p >   Si quiere volver a ver <b>todas los puestos de la biblioteca</b> pinche <a href="/bibliotecas/${puesto.bibliotecaID}/puestos/">aquí </a></p> <br>

    <p >------------------------------------------------- </p><br><br>
<b> <p>La información del puesto ${puesto.id} es la siguiente:</p> </b>

           <p >El id del puesto es ${puesto.id} </p> <br>
           <p> La URI del puesto es ${puesto.url} </p> <br>
           <p>El tipo de elemento reservable es ${puesto.tipo} </p> <br>
           <p>La descripción del puesto es ${puesto.descripcion} </p> <br>
           <p >El puesto está en la biblioteca con ID <a href="/bibliotecas/${puesto.bibliotecaID}">${puesto.bibliotecaID} </a> </p> <br>
           <p >La info del puesto es ${puesto.info} </p> <br>
      <p >------------------------------------------------- </p> <br>
<b> <p>Si quiere realizar una modificación en el puesto, tiene dos opciones: </p> </b>
 <b> <p>1) Actualizar solamente la info del puesto. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/puestos/' + ${puesto.id}); return false;" id="formularioPATCH" >
       <div>
         <label for="puesto.infoPuesto">- La nueva info del puesto</label>
         <input name="infoPuesto" id="infoPuesto" value="">
       </div>
       <div>
         <button id="modificarPuesto">Modificar puesto</button>
       </div>
</form>

<b> <p>2) Modificar toda la información del puesto. Introduzca: </p> </b>
 <form action="#" onSubmit="makePUTRequest('/puestos/${puesto.id}'); return false;" id="formularioPUT" >
       <div>
         <label for="puesto.descripcion">- La nueva descripción del puesto</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="puesto.info">- La nueva info del puesto</label>
         <input name="info" id="info" value="">
       </div>
       <div>
         <button id="modificarPuesto">Modificar puesto</button>
       </div>
</form>

<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makeDELETERequest('${puesto.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este puesto pulse el botón </p> </b>

  <div>
    <button id="borrarPuesto">Borrar puesto</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>