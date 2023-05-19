package entities;

import java.time.LocalDateTime;

public class CambioFecha extends Cambio{

    private LocalDateTime fecha;

    public CambioFecha(){
        super();

    }

    public CambioFecha(LocalDateTime fecha) {
        super();
        this.fecha = fecha;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


}


