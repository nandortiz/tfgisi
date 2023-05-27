package entities;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CambioFecha extends Cambio{

    private LocalDateTime fecha;

    public CambioFecha(){
        super();

    }

    public CambioFecha(LocalDateTime fecha) {
        super();
        this.fecha = fecha;
    }

    public LocalDateTime getFecha() { //TODO disponibilidades
        LocalDateTime fecha_hour = fecha.truncatedTo(ChronoUnit.HOURS);
        System.out.println(fecha_hour);
        return fecha_hour;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

}


