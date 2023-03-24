package entities;

public class SalaShort extends RecursoWeb{
    private String descripcionElementoreservable;//TODO Era descripcionSala
    private int aforoSala;
    private int bibliotecaID;

    public SalaShort(){
        super();
    }

    public SalaShort(int id, String url, String descripcionElementoReservable, int aforoSala, int bibliotecaID) { //TODO Era descripcionElementoReservable
        super(id, url);
        this.descripcionElementoreservable =descripcionElementoReservable;
        this.bibliotecaID=bibliotecaID;
        this.aforoSala=aforoSala;
    }

    public String getDescripcionElementoreservable() {
        return descripcionElementoreservable;
    }

    public void setDescripcionElementoreservable(String descripcionElementoreservable) {
        this.descripcionElementoreservable = descripcionElementoreservable;
    }

    public int getAforoSala() {
        return aforoSala;
    }

    public void setAforoSala(int aforoSala) {
        this.aforoSala = aforoSala;
    }

    public int getBibliotecaID() {
        return bibliotecaID;
    }

    public void setBibliotecaID(int BibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }
}