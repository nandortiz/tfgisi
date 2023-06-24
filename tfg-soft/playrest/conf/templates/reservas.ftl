<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/reservas</title>
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
var form = document.querySelector("#formularioPOST");
var data = `{
                        "usuarioID": {"id": `+form.querySelector('input[name="usuarioID"]').value+`},
                    	"elementoReservableID": {"id": `+form.querySelector('input[name="elementoReservableID"]').value+`},
                    	"fecha": {"id": `+form.querySelector('input[name="fecha"]').value+`}
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
    <p id="url">Si quieres saber más información de la reserva ${reserva.id} pulsa <a href="/reservas/${reserva.id}">aquí</a></p> <br>

<p >------------------------------------------------- </p> <br>
   </#list>
  </div>

<b> <p>Este formulario es para crear una reserva. Introduzca: </p> </b>
<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/reservas'); return false;" id="formularioPOST" >
    <div>
    <label for="reserva.usuarioID">- El ID del usuario que hace la reserva</label>
    <input name="usuarioID" id="usuarioID" value="">
  </div>
  <div>
      <label for="reserva.fecha">- La fecha de la reserva a realizar en el formato del ejemplo</label>
      <input name="fecha" id="fecha" value="">
    </div>
   <div>
       <label for="reserva.elementoReservableID">- El ID del elemento reservable que desea reservar</label>
       <input name="elementoReservableID" id="elementoReservableID" value="">
     </div>

  <div>
    <button id="creacionReserva">Crear reserva</button>
  </div>
</form>

<p >-------------------------------------------------------------------------------</p> <br>

</body>
</html>