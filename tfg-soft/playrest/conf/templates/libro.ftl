<!DOCTYPE html>
<meta charset="UTF-8">
<html>
<head>
  <title>/bibliotecas/${libro.bibliotecaID}/libros/${libro.id}</title>
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
var form = document.querySelector("#formularioPUT");
var nombre = form.querySelector('input[name="nombre"]').value;
var descripcion = form.querySelector('input[name="descripcion"]').value;
var isbn = form.querySelector('input[name="isbn"]').value;
var data = JSON.stringify({
  nombre: nombre,
  descripcion: descripcion,
  isbn: isbn
});

console.log(data)
xhr.send(data);
alert ("Libro actualizado correctamente");
location.reload();
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
var isbnLibro = form.querySelector('input[name="isbnLibro"]').value;
var data = JSON.stringify({
   isbnLibro: isbnLibro
});
console.log(data)
xhr.send(data);
alert ("Libro modificado correctamente");
location.reload();
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
alert ("Libro borrado correctamente");
window.location.replace(
 "/bibliotecas/${libro.bibliotecaID}/libros/"
);
}
</script>

<body>

<style>
.negrita{
font-weight:bold;
font-size:23px;
}
</style>

<div class="fondo position-relative overflow-hidden p-3 p-md-5 m-md-3 text-center bg-light" style="text-align:center;" >
  <div class="col-md-5 p-lg-5 mx-auto my-5">
    <h1 class="display-4 font-weight-normal blanco">SGB</h1>
    <p class="lead font-weight-normal blanco">Sistema de Gestión de Bibliotecas USP CEU</p>
</div>

<p>   <a href="/inicio">INICIO </a> | <a href="/usuarios">USUARIOS </a> | <a href="/bibliotecas/">BIBLIOTECAS </a> | <a href="/reservas">RESERVAS </a></p>
<br>
<hr> <br><br>

<!-- <p >   Si quiere volver al <b>inicio</b> pinche <a href="/inicio">aquí </a></p> <br> -->
<p >   Si quiere volver a ver <b>todas los libros de la biblioteca</b> pinche <a href="/bibliotecas/${libro.bibliotecaID}/libros/">aquí </a></p> <br>

<hr>
<b> <p>La información del libro ${libro.id} es la siguiente:</p> </b>

           <p> El id del libro es<b> ${libro.id} </b> </p>
           <p> La URI del libro es<b>${libro.url} </b> </p>
           <p> El título del libro es <b>${libro.nombre}  </b></p>
           <p El recurso extra es de tipo <b>${libro.tipo} </b> </p>
           <p> La descripción del libro es <b>${libro.descripcion} </b> </p>
           <p> El libro está en la biblioteca con ID <b><a href="/bibliotecas/${libro.bibliotecaID}">${libro.bibliotecaID} </a></b> </p>
           <p> El ISBN del libro es <b>${libro.isbn}</b> </p>
<hr>
<b> <p>Si quiere realizar una modificación en el libro, tiene dos opciones: </p> </b>
 <b> <p>1) Actualizar solamente el ISBN del libro. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/libros/' + ${libro.id}); return false;" id="formularioPATCH" >
       <div>
         <label for="libro.isbnLibro">- El nuevo ISBN del libro (4 dígitos)</label>
         <input name="isbnLibro" id="isbnLibro" value="">
       </div>
       <div>
        <div style="margin-top:25px;">
         <button class="boton1" id="modificarLibro">Modificar libro</button>
       </div>
</form>

<b> <p>2) Modificar toda la información del libro. Introduzca: </p> </b>
 <form action="#" onSubmit="makePUTRequest('/libros/${libro.id}'); return false;" id="formularioPUT" >
        <div>
          <label for="libro.nombre">- El nuevo título del libro</label>
          <input name="nombre" id="nombre" value="">
        </div>
       <div>
         <label for="libro.descripcion">- La nueva descripción del libro</label>
         <input name="descripcion" id="descripcion" value="">
       </div>
       <div>
         <label for="libro.isbn">- El nuevo ISBN del libro (4 dígitos)</label>
         <input name="isbn" id="isbn" value="">
       </div>
       <div>
        <div style="margin-top:25px;">
         <button class="boton1" id="modificarLibro">Modificar libro</button>
       </div>
</form>

<hr>


<form action="#" onSubmit="makeDELETERequest('${libro.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este libro pulse el botón </p> </b>
  <div>
   <div style="margin-top:25px;">
    <button class="boton1" id="borrarLibro">Borrar libro</button>
  </div>
</form>
<hr>


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