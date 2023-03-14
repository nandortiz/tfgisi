package entities;

public class BibliotecaShort extends RecursoWeb{

    private String nombreBiblioteca;
    private String descripcionBiblioteca;

    public BibliotecaShort(){
        super();
    }

    public BibliotecaShort(int id, String url, String nombre, String descripcion) {
        super(id, url);
        this.nombreBiblioteca=nombre;
        this.descripcionBiblioteca =descripcion;
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

}