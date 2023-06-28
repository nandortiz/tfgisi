<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/usuarios</title>

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
                    "nombre": "`+form.querySelector('input[name="nombre"]').value+`",
                    "apellidos": "`+form.querySelector('input[name="apellidos"]').value+`",
                    "grado": "`+form.querySelector('input[name="grado"]').value+`"
            }`;

console.log(data)
xhr.send(data);
alert ("Usuario creado correctamente");
location.reload();
}
</script>


<body>
<!-- CABECERA -->
<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>
<!---- FIN CABECERA -->

  <div>
    <!--<#list usuarios as usuario>
  <p id="identificador">El ID del usuario es ${usuario.id} </p> <br>
  <p id="url"> La URI del usuario es <a href="${usuario.url}">${usuario.url} </a> </p> <br>

  <p >------------------------------------------------- </p> <br>
   </#list>-->
  </div>

<form action="#" onSubmit="makePOSTRequest('http://localhost:9000/usuarios'); return false;" id="formulario" >
  <b> <p>Este formulario es añadir para añadir un usuario. Introduzca: </p> </b>
  <div>
    <label for="usuario.nombre">- El nombre del usuario a crear</label>
    <input name="nombre" id="nombre" value="">
  </div>
   <div>
      <label for="usuario.apellidos">- Los apellidos del usuario a crear</label>
      <input name="apellidos" id="apellidos" value="">
    </div>
  <div>
    <label for="usuario.grado">- El grado que cursa el usuario</label>
    <input name="grado" id="grado" value="">
  </div>
  <div style="margin-top:25px;">
    <button id="creacion" class="boton1">Crear usuario</button>
  </div>
</form>
<hr>


<!-----ESTILOS --->
<div >
<p><b> La lista de usuarios es:</b></p>
<table border="1"style="left:50%;margin-left:-20%;position:absolute;">
            <tr>
                <th>ID</th>
                <th>URI</th>
            </tr>
  <#list usuarios as usuario>
            <tr class="fila_impar">
                <td>${usuario.id}</td>
                <td><a href="${usuario.url}">${usuario.url} </a></td>
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


<! FIN ESTILOS --->
</body>
</html>