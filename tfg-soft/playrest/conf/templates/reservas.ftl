<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas </title>
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
                        "usuarioID": {"id": `+form.querySelector('input[name="usuarioID"]').value+`},
                    	"url": {"id": `+form.querySelector('input[name="url"]').value+`},
                    	"elementoReservableID": {"id": `+form.querySelector('input[name="elementoReservableID"]').value+`},
                    	"fecha": {"id": `+form.querySelector('input[name="fecha"]').value+`},

            }`;
console.log(data)
xhr.send(data);
}
</script>

<body>
<p >  Si quiere volver al <b>inicio</b> pinche <a href="/inicio"> aquí </a></p> <br>
<p >   Si quiere ver <b>todos los usuarios</b> pinche <a href="/usuarios">aquí </a></p> <br>
<p >   Si quiere ver <b>todos las bibliotecas</b> pinche <a href="/bibliotecas">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br>
<div> <b> La lista de todas las reservas es: </b><br> <br>
  <#list reservas as reserva>
 <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
    <p id="url">La URI de la reserva es <a href="${reserva.url}">${reserva.url} </a></p> <br>
    <p id="usuarioID">El usuario que ha hecho la reserva es ${reserva.usuarioID} </p> <br>
      <p id="elementoReservableID">El elemento reservable que se ha reservado es ${reserva.elementoReservableID} </p> <br>
   <p id="fecha">La fecha de la reserva es <a href="${reserva.url}">${reserva.url} </a></p> <br>
<p >------------------------------------------------- </p> <br>
   </#list>
  </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/reservas'); return false;" id="formulario" >
  <b> <p>Este formulario es para crear una reserva </p> </b>
  <div>
    <label for="reserva.usuarioID">Introduzca el ID del usuario</label>
    <input name="usuarioID" id="usuarioID" value="">
  </div>
  <div>
      <label for="reserva.fecha">Introduzca la fecha de la reserva en formato aaaa-mm-ddThh:mm:ss.xxxZ</label>
      <input name="fecha" id="fecha" value="2023-09-30T09:30:00">
    </div>
   <div>
       <label for="reserva.elementoReservableID">Introduzca el ID del elemento reservable que desea reservar</label>
       <input name="elementoReservableID" id="elementoReservableID" value="">
     </div>

  <div>
    <button id="creacionReserva">Crear reserva</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>


  </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/reservas'); return false;" id="formulario" >
  <b> <p>Este formulario es para crear una reserva de un recurso extra </p> </b>
  <div>
    <label for="reserva.reservaID">Introduzca el ID del la reserva a la que quiere añadir algún recurso extra</label>
    <input name="reservaID" id="reservaID" value="">
  </div>
  <div>
      <label for="reserva.recursoExtraID">Introduzca el ID del recurso extra que desea añadir a la reserva deseada</label>
      <input name="recursoExtraID" id="recursoExtraID" value="">
    </div>

  <div>
    <button id="creacionReserva">Crear reserva</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>
</body>
</html>