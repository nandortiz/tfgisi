package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Biblioteca extends RecursoWeb {


    private String nombre = "";
    private String descripcion = "";
private ArrayList<ElementoReservableShort> listaElementosReservables = new ArrayList<>();   //TODO usado en interfaz
    public Biblioteca() {
        super();
    }


    public Biblioteca(String nombreBiblioteca, String descripcionBiblioteca) {
        this.nombre = nombreBiblioteca;
        this.descripcion = descripcionBiblioteca;

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<ElementoReservableShort> getListaElementosReservables() {
        return listaElementosReservables;
    }  //TODO usado en interfaz

    public void setListaElementosReservables(ArrayList<ElementoReservableShort> listaElementosReservables) {//TODO usado en interfaz
        this.listaElementosReservables = listaElementosReservables;//TODO usado en interfaz
    }
    public void annadirListaElementosReservables(ElementoReservableShort elementoReservable){//TODO usado en interfaz
        listaElementosReservables.add(elementoReservable);//TODO usado en interfaz
    }

        @Override
        public String toString () { //TODO Date para apertura y cierre?
            return "Biblioteca{" +
                    "id = '" + id +
                    "', nombre ='" + nombre + '\'' +
                    "', descripción ='" + descripcion +
                    ", url = '" + getUrl() +
                    '}';
        }

        public String getUrl () {
            return "/bibliotecas/" + this.id;
        }

        public void setId ( int id){
            this.id = id;
        }
        public Integer getId () {
            return this.id;
        }


    }
