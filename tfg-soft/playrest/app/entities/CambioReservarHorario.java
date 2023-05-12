package entities;

public class CambioReservarHorario extends Cambio {

    private String reservarHorario;

    public CambioReservarHorario(String reservarHorario) {
        this.reservarHorario = reservarHorario;
    }

    public String getReservarHorario() {
        return reservarHorario;
    }

    public void setReservarHorario(String reservarHorario) {
        this.reservarHorario = reservarHorario;
    }
}
