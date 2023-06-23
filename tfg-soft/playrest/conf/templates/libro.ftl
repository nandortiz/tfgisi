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
  isbnLibro: isbnLibro
});

console.log(data)
xhr.send(data);
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
var isbn = form.querySelector('input[name="isbn"]').value;
var data = JSON.stringify({
   isbn: isbn
});
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
<p >   Si quiere volver a ver <b>todas los libros de la biblioteca</b> pinche <a href="/bibliotecas/${libro.bibliotecaID}/libros/">aquí </a></p> <br>

    <p >------------------------------------------------- </p><br><br>
<b> <p>La información del libro ${libro.id} es la siguiente:</p> </b>

           <p> El id del libro es ${libro.id} </p> <br>
           <p> La URI del libro es ${libro.url} </p> <br>
           <p> El título del libro es ${libro.nombre} </p> <br>
           <p El tipo del recurso extra es ${libro.tipo} </p> <br>
           <p> La descripción del libro es ${libro.descripcion} </p> <br>
           <p> El libro está en la biblioteca con ID <a href="/bibliotecas/${libro.bibliotecaID}">${libro.bibliotecaID} </a> </p> <br>
           <p> El ISBN del libro es ${libro.isbn} </p> <br>
      <p >------------------------------------------------- </p> <br>
<b> <p>Si quiere realizar una modificación en el libro, tiene dos opciones: </p> </b>
 <b> <p>1) Actualizar solamente el ISBN del libro. Introduzca: </p> </b>

<form action="#" onSubmit="makePATCHRequest('/libros/' + ${libro.id}); return false;" id="formularioPATCH" >
       <div>
         <label for="libro.isbn">- La nueva info del puesto</label>
         <input name="isbn" id="isbn" value="">
       </div>
       <div>
         <button id="modificarLibro">Modificar libro</button>
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
         <label for="libro.isbnLibro">- El nuevo ISBN del libro</label>
         <input name="isbnLibro" id="isbnLibro" value="">
       </div>
       <div>
         <button id="modificarLibro">Modificar libro</button>
       </div>
</form>

<p >------------------------------------------------- </p> <br>

<form action="#" onSubmit="makeDELETERequest('${libro.url}'); return false;" id="formularioDELETE" >
 <b> <p>Si quiere borrar este libro pulse el botón </p> </b>

  <div>
    <button id="borrarLibro">Borrar libro</button>
  </div>
</form>
<p >------------------------------------------------- </p> <br>

</body>
</html>