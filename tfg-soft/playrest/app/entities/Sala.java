package entities;

public class Sala  {

    private int id;
    private String descripcionElementoReservable;
    private int bibliotecaID;

    public Sala () {
    }

    public Sala(int id, String url, String descripcionElementoReservable, int bibliotecaID) {
}

    public String getUrl() {
        return "/salas/" + this.getId();
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcionElementoReservable() {
        return descripcionElementoReservable;
    }

    public void setDescripcionElementoReservable(String descripcionElementoReservable) {
        this.descripcionElementoReservable = descripcionElementoReservable;
    }

    public int getBibliotecaID() {
        return 4 ; //TODO bibliotecaID
    }

    public void setBibliotecaID(int bibliotecaID) {
        this.bibliotecaID = bibliotecaID;
    }
}


