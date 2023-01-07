package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Biblioteca {
    public int id;
    private String nombreBiblioteca="";
    private String descripcionBiblioteca="";

    //horas de apertura y cierre 01/01/2021-10:00 ?¿?¿?¿?¿?¿
    // private ArrayList<Puesto> listaPuesto = new ArrayList<>();
    //  private ArrayList<String> listaPuesto = new ArrayList<>();

    private ArrayList<SalaShort> listaSala = new ArrayList<>();

    public Biblioteca(){
    }


    public Biblioteca(String nombreBiblioteca, String descripcionBiblioteca) {
        this.nombreBiblioteca=nombreBiblioteca;
        this.descripcionBiblioteca=descripcionBiblioteca;

    }

    public String getNombreBiblioteca() {
        return nombreBiblioteca;
    }

    public void setNombreBiblioteca(String nombreBiblioteca) {
        this.nombreBiblioteca = nombreBiblioteca;
    }

    public String getDescripcionBiblioteca() {
        return descripcionBiblioteca;
    }

    public void setDescripcionBiblioteca(String descripcionBiblioteca) {
        this.descripcionBiblioteca = descripcionBiblioteca;
    }




    public ArrayList<SalaShort> getListaSala() {
        return listaSala;
    }


    public void setListaSala(ArrayList<SalaShort> listaSala) {
        this.listaSala = listaSala;
    }


    public void annadirListaSala(SalaShort sala){
        listaSala.add(sala);
    }
    @Override
    public String toString() {
        return "Biblioteca{" +
                "id de la biblioteca= 'B." + id +
                ", nombre de la biblioteca='" + nombreBiblioteca + '\'' +
                ", descripción de la biblioteca ='" + descripcionBiblioteca +
              //", horarios disponibles='" + listaDisponibilidadBiblioteca+
                '}';
    }

    public String getUrl() {
        return "/bibliotecas/" + this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId(){
        return this.id;
    }
}