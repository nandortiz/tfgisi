<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas/${reserva.id} </title>
</head>
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

  <p id="identificador">El ID de la reserva es ${reserva.reservaID} </p> <br>
  <p id="url">La URI de la reserva es ${reserva.url} </p> <br>
  <p id="usuario">El ID del usuario de la reserva es ${usuario.usuarioID} </p> <br>
  <p id="elementoReservableID">El ID del elemento reservable de la reserva es ${reserva.elementoReservableID} </p> <br>
   <p id="fecha">La fecha de la reserva es ${reserva.fecha} </p> <br>

  <div> <b> La lista de recursos extra asociados a la reserva es: </b><br> <br>
          <#list recursosExtra as recursoExtra>
             <p >El id del recurso extra asociado a la reserva es ${recursoExtra.recursoExtraID} </p> <br>
             <p >La URI del recurso extra de la reserva es <a href="${recursoExtra.url}">${recursoExtra.url} </a></p> <br>
               <p >------------------------------------------------- </p> <br>
          <#else>
          <p>No tiene recursos asignados<p>
          </#list>
          </div>

<form action="#" onSubmit="makeDELETERequest('${reserva.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar esta reserva pulse el botón </p> </b>

  <div>
    <button id="borrarReserva">Borrar reserva</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>