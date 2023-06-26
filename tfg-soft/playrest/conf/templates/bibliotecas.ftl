<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/</title>
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
                   "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                   "descripcion": "`+form.querySelector('input[name="descripcion"]').value+`"
            }`;

console.log(data)
xhr.send(data);
alert ("Biblioteca creada correctamente");
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


<!--<p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br>
<p >  Si quiere ver <b>todas las reservas</b> pinche <a href="/reservas"> aquí </a></p> <br>
<p >   Si quiere ver <b>todos los usuarios</b> pinche <a href="/usuarios">aquí </a></p> <br>
<p >------------------------------------------------- </p> <br><br>-->

 <div>
 <!--<b> La lista de bibliotecas es: </b><br> <br>
    <#list bibliotecas as biblioteca>
        <p id="identificador" >  El ID de la biblioteca es ${biblioteca.id} </p> <br>
         <p id="url"> La URI de la biblioteca es <a href="${biblioteca.url}">${biblioteca.url} </a></p> <br>

    </#list>-->
    </div>
 <hr>

<b> <p>Este formulario es para añadir una biblioteca. Introduzca: </p> </b>
<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/bibliotecas'); return false;" id="formularioPOST" >
  <div>
    <label for="biblioteca.nombre">- Nombre de la biblioteca a crear</label>
    <input name="nombre" id="nombre" value="">
  </div>
  <div>
    <label for="biblioteca.descripcion">- Descripción de la biblioteca a crear</label>
    <input name="descripcion" id="descripcion" value="">
  </div>
  <div>
    <button id="creacion" class="boton1">Crear biblioteca</button>
  </div>
</form>

<hr>
<div >
<p><b> La lista de bibliotecas es:</b></p>
<table border="1"style="left:50%;margin-left:-20%;position:absolute;">
            <tr>
                <th>ID</th>
                <th>URI</th>
            </tr>
  <#list bibliotecas as biblioteca>
            <tr class="fila_impar">
                <td>${biblioteca.id}</td>
                <td><a href="${biblioteca.url}">${biblioteca.url} </a></td>
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