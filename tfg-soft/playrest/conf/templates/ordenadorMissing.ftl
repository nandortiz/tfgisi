<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>El ordenador ${ordenadorID} no existe </title>
</head>

<body>
<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>

<p> El ordenador ${ordenadorID} no existe o ha sido borrado </p>

<!-- <p> Si quiere volver al inicio pinche <a href="/inicio">aquí </a></p>
<p> Si quiere volver a ver todas las  bibliotecas pinche <a href="/bibliotecas/">aquí </a></p> -->
<p >  Si quiere ver <b>todos los ordenadores</b> de esta biblioteca pinche <a href="/bibliotecas/${bibliotecaID}/ordenadores/"> aquí </a></p> <br>


<p> <a href="javascript:history.back()">VOLVER</a></p>

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