package services;



import akka.actor.ProviderSelection;
import entities.*;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;
import static java.sql.Statement.*;

public class ReservaBD extends ConexionBD {

    private static ReservaBD instance;

    public static ReservaBD getInstance() {
        if (instance == null) {
            instance = new ReservaBD();
        }
        return instance;
    }

    public Reserva addReserva(Reserva reserva) throws SQLException, ClassNotFoundException {
        Integer usuarioID = null;
        Integer elementoReservableID = null;
        Integer recursoExtraID = reserva.getRecursoExtraID();
        Integer identificador = -1;
        LocalDateTime fecha;
        boolean disponibilidad;
        Connection cn = connect();
        Integer reservaID = reserva.getId();
        //String tipoReserva = comprobarTipoReserva(reserva);
        TipoReserva tipoReserva = TipoReserva.N; //Valor predeterminado

        // Obtener el tipo de reserva
        if (reserva.getRecursoExtraID() != null) {
            tipoReserva = TipoReserva.E;
        }

        if (conector() == true) {

            try {
                disponibilidad = comprobarDisponibilidad(reserva, tipoReserva);

                if (disponibilidad && tipoReserva == TipoReserva.N) {
                    elementoReservableID = reserva.getElementoReservableID();
                    usuarioID = reserva.getUsuarioID();
                    fecha = reserva.getFecha();
                    String query = "INSERT INTO reserva (usuarioID, elementoReservableID, fecha) VALUES ('" + usuarioID + "','" + elementoReservableID + "', '" + fecha + "');";
                    createStatement.executeUpdate(query, RETURN_GENERATED_KEYS);
                    ResultSet prueba = createStatement.getGeneratedKeys();
                    prueba.next();
                    identificador = prueba.getInt(1);
                    String url = "/reservas/" + identificador;
                    createStatement.executeUpdate("UPDATE  reserva set url ='" + url + "' where reservaID = " + identificador + ";");
                } else if (disponibilidad && tipoReserva == TipoReserva.E) {
                    String query = "INSERT INTO reservaextra (reservaID, RecursoExtraID) VALUES ('" + reservaID + "','" + recursoExtraID + "');";
                    createStatement.executeUpdate(query, RETURN_GENERATED_KEYS);
                    identificador = reservaID;
                    //String url = "/reserva/" + identificador;
                  //  createStatement.executeUpdate("UPDATE  reservaextra set url ='" + url + "' where reservaID = " + identificador + ";");
                    //System.out.println(identificador);
                } else {
                    System.out.println("Error al realizar la reserva");
                    return getReserva(identificador);
                }
                con.close();
            } catch (SQLException e) {
                con.close();
                e.printStackTrace();
            }
        }
        return getReserva(identificador);
    }

/*
    public String comprobarTipoReserva (Reserva reserva){
        String tipoReserva = "indefinido";
        if (reserva.getRecursoExtraID() != null){
            System.out.println(reserva.getRecursoExtraID());
            tipoReserva = "reservaExtra";
            System.out.println(tipoReserva);
        }else{
            tipoReserva = "reservaNormal";
        }
        return tipoReserva;
    }
*/

    public boolean comprobarDisponibilidad(Reserva reserva, TipoReserva tipoReserva) { //TODO añadido parámetro tipoReserva
        int cuenta;
        int id;
        LocalDateTime fecha;
        boolean disponible = false;
        String tabla;
        String primarykey;
        // String tipoReserva = "indefinido";
        int idRecursoExtra;
        // tipoReserva = comprobarTipoReserva(reserva); //TODO añadido como parámetro en comprobarDisponibilidad

        try {

            if (tipoReserva == TipoReserva.N) {
                id = reserva.getElementoReservableID();
                fecha = reserva.getFecha();
                tabla = "reserva";
                primarykey = "elementoReservableID";

                try {
                    if (conector() == true) {
                        String queryBD = "select count(*) as disponibilidad from " + tabla + " where " + primarykey + " = '" + id + "' and fecha = '" + fecha + "';";

                        try {

                            rS = createStatement.executeQuery(queryBD);
                            while (rS.next()) {
                                cuenta = rS.getInt("disponibilidad");

                                if (cuenta > 0) {
                                    disponible = false;
                                } else {
                                    disponible = true;
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Error de disponibilidad");
                            e.printStackTrace();
                            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else if (tipoReserva == TipoReserva.E) {

                String mifecha = null;
                id = reserva.getId();
                idRecursoExtra = reserva.getRecursoExtraID();

                tabla = "reservaextra";
                primarykey = "RecursoExtraID";
                try {
                    if (conector() == true) {
                        //String queryBD = "select count(*) as disponibilidad from " + tabla + " where " + primarykey + " = '" + idRecursoExtra + "' and reservaID =  '" + id + "';";
                        String queryBD= "select fecha from reserva where reservaID = '" + id + "' ;";
                        try {
                            rS = createStatement.executeQuery(queryBD);
                            while (rS.next()) {
                                mifecha = rS.getString("fecha");
                            }
                        String queryBD2 = "select count(*) as cuenta from reservaextra inner join reserva on reservaextra.reservaID = reserva.reservaID " +
                                " where recursoExtraID = '"+ idRecursoExtra +"' and fecha = '" + mifecha +"'    ;" ;
                            try {
                                rS = createStatement.executeQuery(queryBD2);
                                System.out.println(queryBD2);
                                while (rS.next()) {
                                    cuenta = rS.getInt("cuenta");
                                    System.out.println(cuenta);
                                    if (cuenta > 0) {
                                        disponible = false;
                                    } else {
                                        disponible = true;
                                    }
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                        } catch (SQLException e) {
                            System.out.println("Error de disponibilidad");
                            e.printStackTrace();
                            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                return disponible;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disponible;
    }

    //TODO Una reserva única, getReserva muestra tabla reserva, y si hay reserva extra, muestra reserva+columna recurosExtraID --> innerjoin
    public Reserva getReserva(int reservaID) { //todo tipo
        Reserva reserva = new Reserva();

        Integer RecursoExtraID = reserva.getRecursoExtraID();
        try {
            if (conector() == true) {
                try {
                    String query;
                        query =" select reserva.reservaID, usuarioID, elementoReservableID, fecha, RecursoExtraID from reserva left join reservaextra on reserva.reservaID = reservaextra.reservaID where reserva.reservaID =" +reservaID+";";
                        rS = createStatement.executeQuery(query);
                        while (rS.next()) {
                            reserva.setId(rS.getInt("reservaID"));
                            reserva.setUsuarioID(rS.getInt("usuarioID"));
                            reserva.setElementoReservableID(rS.getInt("elementoReservableID"));
                            reserva.setFecha(rS.getObject("fecha", LocalDateTime.class));
                            reserva.setRecursoExtraID(rS.getInt("RecursoExtraID"));
                        }

                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getReserva");
                    ex.printStackTrace();
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (reserva.getId() == -1) {
            return null;

        } else {
            return reserva;
        }
    }


    public Collection<ReservaShort> getAllReservas() { //TODO tipo, select* reserva extra --> DONE, verify?
        List<ReservaShort> reservas = new ArrayList();
        // Reserva reserva = new Reserva();
        // String tipoReserva = comprobarTipoReserva(reserva);
        try {
            if (conector() == true) {
                String queryBD = "select reservaID, url from reserva";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Reserva
                        int reservaID = rS.getInt("reservaID");
                        String url = rS.getString("url");
                        ReservaShort reserva = new ReservaShort();
                        reserva.setId(reservaID);
                        reserva.setUrl(url);
                        reservas.add(reserva);
                    }

                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllReservas");
                    ex.printStackTrace();
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public boolean deleteReserva(int reservaID) throws SQLException, ClassNotFoundException {
        boolean valor = false;
        try {

            if (conector() == true) {

                String queryBDReservaExtra = "delete from reservaextra where reservaID= '"+ reservaID +"';";
                String queryBDReservaNormal = "delete from reserva where reservaID = '" + reservaID + "';";

                try {
                    createStatement.executeUpdate(queryBDReservaExtra);
                    createStatement.executeUpdate(queryBDReservaNormal);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - deleteReserva");
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

}

