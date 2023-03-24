package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Biblioteca extends RecursoWeb {


    private String nombre = "";
    private String descripcion = "";
    private LocalDateTime apertura;
    private LocalDateTime cierre;
    //horas de apertura y cierre 01/01/2021-10:00 ?¿?¿?¿?¿?¿
    // private ArrayList<Puesto> listaPuesto = new ArrayList<>();
    //  private ArrayList<String> listaPuesto = new ArrayList<>();

    private ArrayList<SalaShort> salas = new ArrayList<>();

    public Biblioteca() {
        super();
    }


    public Biblioteca(String nombreBiblioteca, String descripcionBiblioteca, LocalDateTime aperturaBiblioteca, LocalDateTime cierreBiblioteca) {
        this.nombre = nombreBiblioteca;
        this.descripcion = descripcionBiblioteca;
        this.apertura  = aperturaBiblioteca;
        this.cierre  = cierreBiblioteca;
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

    public LocalDateTime getApertura() {
        return apertura;
    }

    public void setApertura(LocalDateTime apertura) {
        this.apertura = apertura;
    }

    public LocalDateTime getCierre() {
        return cierre;
    }

    public void setCierre(LocalDateTime cierre) {
        this.cierre = cierre;
    }

    public ArrayList<SalaShort> getSalas() { return salas; }

    public void setSalas(ArrayList<SalaShort> salas) { this.salas = salas; }

    public void annadirListaSala(SalaShort sala){ salas.add(sala); }

        @Override
        public String toString () { //TODO Date para apertura y cierre?
            return "Biblioteca{" +
                    "id = '" + id +
                    "', nombre ='" + nombre + '\'' +
                    "', descripción ='" + descripcion +
                   // "', apertura ='" + apertura +
                  //  "', cierre ='" + cierre +
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
