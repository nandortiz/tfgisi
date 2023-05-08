package entities;

import java.util.ArrayList;

public class Ordenador extends RecursoExtra{

    private String numSerie;

    public Ordenador(String nombre, String descripcion, String numSerieOrdenador int bibliotecaID) {
        super (nombre, descripcion, TipoRecursoExtra.O, bibliotecaID);
        this.numSerie=numSerieOrdenador;
    }

    public Ordenador() {
        this.tipo=TipoRecursoExtra.O;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    @Override
    public String toString () {
        return "Ordenador{" +
                "id = '" + id +
                ", nombre = '" + getNombre() +
                "' ,numSerieOrdenador ='" + numSerie +
                "', descripcion ='" + getDescripcion() + '\'' +
                "', tipo ='" + tipo +
                "', bibliotecaID='" + getBibliotecaID() +
                ", url = '" + getUrl() +
                '}';
    }

    public String getUrl () {
        return "/bibliotecas/"+getBibliotecaID()+"/ordenadores/" + this.id; }

}


