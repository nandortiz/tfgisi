package entities;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Reserva extends RecursoWeb {

    private int usuarioID;
    private LocalDateTime fecha;
    private Integer elementoReservableID;
    private Integer recursoExtraID;                                 //TODO recursoExtra


    public Reserva() {
        super();
    }

    public Reserva(Integer id, String url, int usuarioID, Integer elementoReservableID, Integer recursoExtraID, LocalDateTime fecha) {   //TODO recursoExtra
        super(id, url);
        this.usuarioID = usuarioID;
        this.elementoReservableID = elementoReservableID;
        this.recursoExtraID = recursoExtraID;                                    //TODO recursoExtra
        this.fecha = fecha;

    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public LocalDateTime getFecha() {
        LocalDateTime fecha_hour = fecha.truncatedTo(ChronoUnit.HOURS);
        System.out.println(fecha_hour);
        return fecha_hour;
    }

    public LocalDateTime setFecha (LocalDateTime fecha) {
        this.fecha = fecha;
        return fecha;
    }


    public Integer getElementoReservableID() {
        return elementoReservableID;
    }

    public void setElementoReservableID(int elementoReservableID) {
        this.elementoReservableID = elementoReservableID;
    }

    public Integer getRecursoExtraID() {
        return recursoExtraID;
    }               //TODO recursoExtra

    public void setRecursoExtraID(int recursoExtraID) {                     //TODO recursoExtra
        this.recursoExtraID = recursoExtraID;
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
        return "Reserva{" +
                "id = '" + id +
                "', usuarioID ='" + usuarioID+
                "', elementoReservableID ='" + elementoReservableID +
                "', recursoExtraID = '" + recursoExtraID +                  //TODO recursoExtra
                ", url = '" + getUrl() +
                ", fecha = '" + fecha +
                '}';
    }

    public String getUrl () {
        return "/reservas/" + this.id;
    }

    public void setId ( Integer id){
        this.id = id;
    }

    public Integer getId () {
        return this.id;
    }


}

