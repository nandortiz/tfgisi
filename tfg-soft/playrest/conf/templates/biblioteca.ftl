<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/Bibliotecas/${biblioteca.id}</title>
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
var form = document.querySelector("#formulario");
var data = `{
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`"
            }`;
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
var data = `{
                    "type": "`+form.querySelector('input[name="tipo"]').value+`",
                 <!--   "franja": "`+form.querySelector('input[name="franja"]').value+`"  -->
            }`;
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
  <p >  Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todos los puestos</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/puestos"> aquí </a></p> <br>
  <p >  Si quiere ver <b>todas las salas</b> de esta biblioteca pinche <a href="/bibliotecas/${biblioteca.id}/salas"> aquí </a></p> <br>
  <p >------------------------------------------------- </p> <br><br>
  <p id="identificador" >  El ID de la biblitoeca es ${Biblioteca.id} </p> <br>
  <p id="url">La URI del biblioteca es ${Biblioteca.getUrl()} </p> <br>
  <p id="nombre">EL nombre de la biblioteca es ${Biblioteca.nombre} </p> <br>
  <p id="descripcion">La descripción de la biblioteca es ${Biblioteca.descripcion} </p> <br>

<!-- <div> <b>La disponibilidad del laboratorio es: </b>  <br> -->
 <!-- <#list listaDisponibilidadLaboratorio as horario> -->
   <!-- <p> ${horario} -->
   <!-- <#else> NO tiene disponiblidad asignada -->
  <!-- </#list> -->
 <!-- </div> <br> -->

  <div> <b> La lista de elementos reservables de la biblioteca es: </b><br> <br>
    <#list listaElementosReservables as elementoReservable>
       <p >El id del elementoReservable es ${ElementoReservable.id} </p> <br>
         <p> La URI del puesto es <a href="${Puesto.getUrl()}">${Puesto.getUrl()} </a></p> <br>
         <p> La URI de la sala es <a href="${Sala.getUrl()}">${Sala.getUrl()} </a></p> <br>
         <p>La descripción del elementoReservable es ${ElementoReservable.descripcion} </p> <br>
         <p >El ID del biblioteca del elementoReservable es ${ElementoReservable.bibliotecaID} </p> <br>
         <p >------------------------------------------------- </p> <br>
    <#else> No tiene elementos reservables asignados
    </#list>
    </div>

<form action="#" onSubmit="makePUTRequest('${Biblioteca.getUrl()}'); return false;" id="formulario" >
 <b> <p>Este formulario es para modificar la información del nombre y la descripción de esta biblioteca </p> </b>
  <div>
    <label for="Biblioteca.nombre">Introduzca el nombre de la biblioteca</label>
    <input name="nombre" id="nombre" value="">
  </div>
  <div>
    <label for="Biblioteca.descripcion">Introduzca la descripción de la biblioteca</label>
    <input name="descripcion" id="descripcion" value="">
  </div>

  <div>
    <button id="modificar">Modificar biblioteca</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

 <!--<form action="#" onSubmit="makePATCHRequest('${laboratorio.url}'); return false;" id="formularioPATCH" > -->
 <!-- <b> <p>Este formulario es para modificar la disponibilidad de este laboratorio </p> </b> -->
 <!--  <div> -->
    <!-- <label for="laboratorio.nombre">Introduzca ADD si quiere añadir o REMOVE si quiere eliminar una franja de disponibilidad</label> -->
  <!--   <input name="tipo" id="Tipo" value=""> -->
  <!-- </div> -->
   <!--<div> -->
   <!--  <label for="laboratorio.descripcion">Introduzca la hora que quiera modificar</label> -->
   <!--  <input name="franja" id="Franja" value="2021-05-01T09:30:00"> -->
  <!-- </div> -->

  <!-- <div> -->
   <!--  <button id="modificarDisponibilidad">Modificar disponiblidad</button> -->
  <!-- </div> -->
 <!--</form> -->
 <!-- <p >------------------------------------------------- </p> <br> -->

<form action="#" onSubmit="makeDELETERequest('${biblioteca.getUrl()}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta biblioteca pulse el botón </p> </b>

  <div>
    <button id="borrar">Borrar biblioteca</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>