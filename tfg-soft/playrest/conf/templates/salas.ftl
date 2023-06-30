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
alert ("Sala creada correctamente");
location.reload();
}
</script>

<body>

<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>

<!-- <p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br> -->
<p >  Si quiere volver a ver <b>la información de la biblioteca ${bibliotecaID}</b> pinche <a href="/bibliotecas/${bibliotecaID}"> aquí </a></p> <br>
<!-- <p >   Si quiere volver a ver <b>todas las bibliotecas</b> pinche <a href="/bibliotecas/">aquí </a></p> <br> -->
 <hr>
<div>
<!--<b> La lista de salas de la biblioteca ${bibliotecaID} es:</b><br> <br>
<#list salas as sala>
  <p id="identificador">El ID de la sala es ${sala.id} </p> <br>
  <p id="url"> Si quiere obtener más información de esta sala pinche <a href="/bibliotecas/${bibliotecaID}/salas/${sala.id}">aquí</a></p> <br>

<hr>
 </#list>-->
</div>

<b> <p>Este formulario es para añadir una sala a la biblioteca. Introduzca: </p> </b>
<form action="#" onSubmit="makePOSTRequest('/bibliotecas/${bibliotecaID}/salas'); return false;" id="formulario" >
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
  <div style="margin-top:25px;">
    <button  class="boton1" id="creacionSala">Crear sala</button>
  </div>
</form>

<hr>

<div >
<p><b> La lista de salas de la biblioteca ${bibliotecaID} es:</b></p>
<table border="1"style="left:50%;margin-left:-20%;position:absolute;">
            <tr>
                <th>ID</th>
                <th>URI</th>
            </tr>
  <#list salas as sala>
            <tr class="fila_impar">
                <td>${sala.id}</td>
                <td><a href="/bibliotecas/${bibliotecaID}/salas/${sala.id}"> ${sala.url}</a></td>
            </tr>
   </#list>

</table>

</div>
<style>
table {
    width:40%;
    font:normal 25px Arial;
    text-align:center;
    border-collapse:collapse;
}

table th {
    font:bold 25px Arial;
    background-color:lightblue;
}

.fila_impar {
    background-color:#c0c0c0;
}

.fila_par {
    background-color:#fffff;
}

.fila_resaltada {
    color:blue;
    background-color:red;
}
p {
    font-size:20pt;
}

h1 {
    font-size:30pt;
}
label {
    font-size:15pt;
}
.boton1 {
    font-size:15pt;
}
</style>

</body>
</html>