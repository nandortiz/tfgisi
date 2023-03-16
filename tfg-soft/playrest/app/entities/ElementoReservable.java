package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ElementoReservable {
    private String descripcion = "";
    private String tipo = "";
    private String bibliotecaID = "";


    //horas de apertura y cierre 01/01/2021-10:00 ?¿?¿?¿?¿?¿
    // private ArrayList<Puesto> listaPuesto = new ArrayList<>();
    //  private ArrayList<String> listaPuesto = new ArrayList<>();

    private ArrayList<SalaShort> salas = new ArrayList<>();

    public ElementoReservable() {
        super();
    }


    public ElementoReservable (String descripcionER, String tipoER, String bibliotecaIDER) {
        this.descripcion = descripcionER;
        this.tipo = tipoER;
        this.bibliotecaID  = bibliotecaIDER;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID (String bibliotecaID) {
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
