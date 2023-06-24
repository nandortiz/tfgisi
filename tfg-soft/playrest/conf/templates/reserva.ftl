<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas/${reserva.id}</title>
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
var form = document.querySelector("#formularioPOSTEXTRA");
var data = `{
                       	"recursoExtraID": {"id": `+form.querySelector('input[name="recursoExtraID"]').value+`},
                    	"reservaID": {"id": `+form.querySelector('input[name="reservaID"]').value+`}

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
<p >  Si quiere volver a ver <b>todas las reservas</b> pinche <a href="/reservas"> aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>

  <p id="identificador">El ID de la reserva es ${reserva.id} </p> <br>
  <p id="url">La URI de la reserva es ${reserva.url} </p> <br>
  <p id="usuario">El ID del usuario de la reserva es ${reserva.usuarioID} </p> <br>
  <p id="elementoReservableID">El ID del elemento reservable de la reserva es ${reserva.elementoReservableID} </p> <br>
   <p id="fecha">La fecha de la reserva es ${reserva.fecha} </p> <br>


  <p >------------------------------------------------- </p> <br>
 <b><p>¿Quiere añadir un recurso extra a la reserva ${reserva.id} realizada? </p> </b>
<p> Si quiere tener preparado en su puesto o sala reservado un recurso extra, añada la siguiente información:</p>
<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/reservas'); return false;" id="formularioPOSTEXTRA" >
   <div>
    <input name="reservaID" id="reservaID" type="hidden" value="${reserva.id}">
  </div>
  <div>
      <label for="reserva.recursoExtraID">- El ID del recurso extra que quiere reservar</label>
      <input name="recursoExtraID" id="recursoExtraID" value="">
    </div>

  <div>
    <button id="creacionReservaExtra">Crear reserva extra</button>
  </div>
</form>

<b> <p> (Si quiere añadir más de un recurso extra, una vez hecha la primera reserva extra, refresque la pantalla y repita el proceso)</p> </b>
<p >------------------------------------------------- </p> <br>
<form action="#" onSubmit="makeDELETERequest('${reserva.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta reserva pulse el botón </p> </b>

  <div>
    <button id="borrarReserva">Borrar reserva</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>