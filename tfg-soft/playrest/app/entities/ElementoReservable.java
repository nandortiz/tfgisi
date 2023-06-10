package entities;

import services.ElementoReservableBD;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.time.LocalTime; //TODO disponibilidad
import java.util.Comparator;
import java.util.List;


public class ElementoReservable extends RecursoWeb{ //TODO public abstract class? disponibilidad

    private String descripcion = "";
    protected TipoElementoReservable tipo;
    private int bibliotecaID;



    public ElementoReservable() {
       super();
    }

    public ElementoReservable(String descripcionElementoReservable, TipoElementoReservable tipoElementoReservable, int bibliotecaID) {
        this.descripcion = descripcionElementoReservable;
        this.tipo = tipoElementoReservable;
        this.bibliotecaID = bibliotecaID;

    }

    //TODO cambiar en workbench s y p por S y P
    //TODO cambiar nulls por exceptions
//_____________________________________________
    //TODO buscar error ' ' sql PUT SalaBD
    //TODO delete y post salas y puestos

    //:______________________________
    //TODO Después de hacer put y patch de puestobd y salabd, conseguir que sólo haya EltoResBD, y borrar salaBd y puestoBD
    //TODO ...para ello añadir los instances que están en el modify en todos create, modufy, delete,...

    //____________________________________________
    //TODO                  DUDAS:
    //TODO              patch y put sala 200 pero en get no se actualiza;
    //TODO              si hago get idbib13 e idsa 24 debería dar error porque esa sala está en bib1 y no da error


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoElementoReservable getTipo() {
        return tipo;
    }

    public void setTipo(TipoElementoReservable tipo) {
        this.tipo = tipo;
    }

    //TODO mirar si se construyen las salas con la S y los puestos con la P
    public int getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID (int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }

    @Override
    public String toString () { //TODO Date para apertura y cierre?
        return "ElementoReservable{" +
                "id = '" + id +
                "', descripción ='" + descripcion +
                "', tipo ='" + tipo +
                "', bibliotecaID ='" + bibliotecaID +
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/" + this.id;
    }

    public void setId ( Integer id){
        this.id = id;
    }

    public Integer getId () {
        return this.id;
    }

}







