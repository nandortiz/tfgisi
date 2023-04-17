package entities;

import java.util.ArrayList;

public class ElementoReservable extends RecursoWeb{


    private String descripcion = "";
    protected TipoElementoReservable tipo;
    private int bibliotecaID;


    //horas de apertura y cierre 01/01/2021-10:00 ?¿?¿?¿?¿?¿
    // private ArrayList<Puesto> listaPuesto = new ArrayList<>();
    //  private ArrayList<String> listaPuesto = new ArrayList<>();

    private ArrayList<SalaShort> salas = new ArrayList<>();

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
//TODO renovar jetbrains cuenta universitaria gratis
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoElementoReservable getTipo() {
        return tipo;
    }
/*
    public void setTipo(TipoElementoReservable tipo) {
        this.tipo = tipo;
    }
*/ //TODO mirar si se construyen las salas con la S y los puestos con la P
    public int getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID (int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }

    public ArrayList<SalaShort> getSalas() { return salas; }

    public void setSalas(ArrayList<SalaShort> salas) { this.salas = salas; }

    public void annadirListaSala(SalaShort sala){ salas.add(sala); }

    @Override
    public String toString () { //TODO Date para apertura y cierre?
        return "ElementoReservable{" +
                "id = '" + id +
                "', descripción ='" + descripcion +
                "', tipo ='" + tipo +
                "', bibliotecaID ='" + bibliotecaID +
                ", url = '" + getUrl() +//", horarios disponibles='" + listaDisponibilidadBiblioteca+
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/" + this.id;
    }

    public void setId ( int id){
        this.id = id;
    }

    public int getId () {
        return this.id;
    }


}
