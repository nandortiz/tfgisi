package entities;

import java.util.ArrayList;

public class Libro extends RecursoExtra{

    private int isbn;

    public Libro(String nombre, String descripcion, int isbnLibro, int bibliotecaID) {
        super(nombre, descripcion, TipoRecursoExtra.L, bibliotecaID);
        this.isbn = isbnLibro;
    }

    public Libro() {
        tipo=TipoRecursoExtra.L;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "'id = '" + id +
                "', nombre = '" + getNombre() +
                "', isbnLibro = '" + isbn +
                "', descripcion = '" + getDescripcion() + '\'' +
                "', tipo ='" + tipo +
                "', bibliotecaID = '" + getBibliotecaID() +
                "', url = '" + getUrl() +
                '}';
    }
    public String getUrl() {
        return "/bibliotecas/" + getBibliotecaID() + "/libros/" + this.id;
    }


}
