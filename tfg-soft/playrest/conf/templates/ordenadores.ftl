<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${bibliotecaID}/ordenadores/</title>
</head>

<script>
function makePOSTRequest(url){
console.log("Iniciamos la request POST");

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
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`",
                    "tipo": "`+form.querySelector('input[name="tipo"]').value+`",
                    "bibliotecaID": "`+form.querySelector('input[name="bibliotecaID"]').value+`",
                    "numSerie": "`+form.querySelector('input[name="numSerie"]').value+`"

            }`;
console.log(data);
xhr.send(data);
alert ("Usuario creado correctamente");
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
<!-- <b> La lista de ordenadores de la biblioteca ${bibliotecaID} es: </b><br> <br>
    <#list ordenadores as ordenador>
        <p id="identificador" >  El ID del ordenador es ${ordenador.id} </p> <br>
         <p id="url"> Si quiere obtener más información de este ordenador pinche <a href="/bibliotecas/${bibliotecaID}/ordenadores/${ordenador.id}">aquí</a></p> <br>

<hr>
    </#list> -->
    </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas/${bibliotecaID}/ordenadores'); return false;" id="formularioPOST" >
  <b> <p>Este formulario es para añadir un ordenador a la biblioteca. Introduzca: </p> </b>
  <div>
      <label for="ordenador.nombre">- Modelo del ordenador a crear</label>
      <input name="nombre" id="nombre" value="">
    </div>
  <div>
    <label for="ordenador.descripcion">- Descripción del ordenador a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
  <div>
     <input name="tipo" id="tipo" type="hidden" value="O">
   </div>
  <div>
     <label for="ordenador.bibliotecaID">- La biblioteca a la que pertenece el ordenador a crear</label>
     <input name="bibliotecaID" id="bibliotecaID" value="">
    </div>
  <div>
     <label for="ordenador.numSerie">- El número de serie del ordenador a crear (máximo 10 caracteres)</label>
     <input name="numSerie" id="numSerie" value="">
  </div>

  <div>
  <div style="margin-top:25px;">
    <button class="boton1" id="creacion">Crear ordenador</button>
  </div>
</form>

<hr>
<div >
<p><b> La lista de ordenadores de la biblioteca ${bibliotecaID} es:</b></p>
<table border="1"style="left:50%;margin-left:-20%;position:absolute;">
            <tr>
                <th>ID</th>
                <th>URI</th>
            </tr>
  <#list ordenadores as ordenador>
            <tr class="fila_impar">
                <td>${ordenador.id}</td>
                <td><a href="/bibliotecas/${bibliotecaID}/ordenadores/${ordenador.id}"> ${ordenador.url}</a></td>
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