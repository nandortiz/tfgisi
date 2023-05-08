package entities;

import java.util.ArrayList;

public class RecursoExtra extends RecursoWeb {

    private String nombre = "";
    private String descripcion = "";
    protected TipoRecursoExtra tipo;
    private int bibliotecaID;

   // private ArrayList<OrdenadorShort> ordenadores = new ArrayList<>();

    public RecursoExtra() {
        super();
    }

    public RecursoExtra(String nombreRecursoExtra, String descripcionRecursoExtra, TipoRecursoExtra tipoRecursoExtra, int bibliotecaID) {
        this.nombre = nombreRecursoExtra;
        this.descripcion = descripcionRecursoExtra;
        this.tipo = tipoRecursoExtra;
        this.bibliotecaID = bibliotecaID;

    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

        public String getDescripcion() { return descripcion; }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public TipoRecursoExtra getTipo() {
            return tipo;
        }

        public void setTipo(TipoRecursoExtra tipo) {
            this.tipo = tipo;
        }

        public int getBibliotecaID() {
            return bibliotecaID;
        }

        public void setBibliotecaID (int bibliotecaID) {
            this.bibliotecaID = bibliotecaID;
        }

       // public ArrayList<OrdenadorShort> getOrdenadores() { return ordenadores; }

       // public void setOrdenadores(ArrayList<OrdenadorShort> ordenadores) { this.ordenadores = ordenadores; }

       // public void annadirListaOrdenador(OrdenadorShort ordenador){ ordenadores.add(ordenador); }

    @Override
    public String toString () { //TODO Date para apertura y cierre?
        return "RecursoExtra{" +
                "id = '" + id +
                "nombre = '" + nombre +
                "', descripción ='" + descripcion +
                "', tipo ='" + tipo +
                "', bibliotecaID ='" + bibliotecaID +
                ", url = '" + getUrl() +//", horarios disponibles='" + listaDisponibilidadBiblioteca+
                '}';
    }

}
