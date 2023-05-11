package entities;



    import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

    public class DisponibilidadElementoReservable extends RecursoWeb{
        private List<LocalTime> horariosDisponibles;

        public DisponibilidadElementoReservable() {
            horariosDisponibles = new ArrayList<>();
            // inicializar la lista con los horarios disponibles
            horariosDisponibles.add(LocalTime.of(8, 30));
            horariosDisponibles.add(LocalTime.of(9, 30));
            horariosDisponibles.add(LocalTime.of(10, 30));
            horariosDisponibles.add(LocalTime.of(11, 30));
            horariosDisponibles.add(LocalTime.of(12, 30));
            horariosDisponibles.add(LocalTime.of(13, 30));
            horariosDisponibles.add(LocalTime.of(14, 30));
            horariosDisponibles.add(LocalTime.of(15, 30));
            horariosDisponibles.add(LocalTime.of(16, 30));
            horariosDisponibles.add(LocalTime.of(17, 30));
            horariosDisponibles.add(LocalTime.of(18, 30));
            horariosDisponibles.add(LocalTime.of(19, 30));
        }

        public void reservarHorario(LocalTime horario) {
            horariosDisponibles.remove(horario);
        }

        public void liberarHorario(LocalTime horario) {
            horariosDisponibles.add(horario);
        }

        // getters y setters
        public List<LocalTime> getHorariosDisponibles() {
            return horariosDisponibles;
        }

        public void setHorariosDisponibles(List<LocalTime> horariosDisponibles) {
            this.horariosDisponibles = horariosDisponibles;
        }
    }


