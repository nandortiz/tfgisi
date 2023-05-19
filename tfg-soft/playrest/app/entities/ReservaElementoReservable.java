package entities;


import java.time.LocalDateTime;

public class ReservaElementoReservable extends RecursoWeb {

    private int usuarioID;
    private LocalDateTime fecha;
    private int elementoReservableID;


    public ReservaElementoReservable() {
        super();
    }

    public ReservaElementoReservable(int id, String url, int usuarioID, int elementoReservableID, LocalDateTime fecha) {
        super(id, url);
        this.usuarioID = usuarioID;
        this.elementoReservableID = elementoReservableID;
        this.fecha = fecha;

    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public LocalDateTime setFecha (LocalDateTime fecha) {
        this.fecha = fecha;
        return fecha;
    }


    public int getElementoReservableID() {
        return elementoReservableID;
    }

    public void setElementoReservableID(int elementoReservableID) {
        this.elementoReservableID = elementoReservableID;
    }





    /*public LocalDateTime getDisponibilidadReserva() {
        return DisponibilidadReserva;
    }

    public void setDisponibilidadReserva(LocalDateTime disponibilidadReserva) {
        DisponibilidadReserva = disponibilidadReserva;
    }

*/

    @Override
    public String toString () {
        return "ReservaElementoReservable{" +
                "id = '" + id +
                "', usuarioID ='" + usuarioID+
                "', elementoReservableID ='" + elementoReservableID +
                ", url = '" + getUrl() +
                ", fecha = '" + fecha +
                '}';
    }

    public String getUrl () {
        return "/reservas/" + this.id;
    }

    public void setId ( int id){
        this.id = id;
    }

    public int getId () {
        return this.id;
    }


}

