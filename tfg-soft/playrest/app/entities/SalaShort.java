package entities;

public class SalaShort extends RecursoWeb{
    private String descripcionSala;
    private int bibliotecaID;

    public SalaShort(){
        super();
    }

    public SalaShort(int id, String url, String descripcionSala, int bibliotecaID) {
        super(id, url);
        this.descripcionSala=descripcionSala;
        this.bibliotecaID=bibliotecaID;
    }

    public String getDescripcionSala() {
        return descripcionSala;
    }

    public void setDescripcionSala(String descripcionSala) {
        this.descripcionSala = descripcionSala;
    }


    public int getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID(int BibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }
}