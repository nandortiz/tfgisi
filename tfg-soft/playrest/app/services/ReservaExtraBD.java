/*
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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;
import static java.sql.Statement.*;

public class ReservaExtraBD extends ConexionBD{

    private static ReservaBD instance;

    public static ReservaBD getInstance() {
        if (instance == null) {
            instance = new ReservaBD();
        }
        return instance;
    }

    public Reserva addReservaExtra(Reserva reserva) throws SQLException, ClassNotFoundException {
        int recursoExtraID = 0;
        int reservaID = 0;
        int identificador = -1;
        boolean disponibilidad;

        if (conector() == true) {
            try {

                recursoExtraID = reserva.getRecursoExtraID();
                reservaID = reserva.getId();
                disponibilidad = comprobarDisponibilidad(reserva);


                if (disponibilidad == true) {
                    String query = "INSERT INTO reservaextra (RecursoExtraID, reservaID) VALUES ('" + recursoExtraID + "','" + reservaID + "');";
                    //System.out.println(query);
                    // createStatement.executeUpdate("set foreign_key_checks = 0;");
                    createStatement.executeUpdate(query, RETURN_GENERATED_KEYS);
                    ResultSet prueba = createStatement.getGeneratedKeys();
                    prueba.next();
                    identificador = prueba.getInt(1);
                    String url = "/reservaextra/" + identificador;
                    createStatement.executeUpdate("UPDATE  reservaextra set url ='" + url + "' where reservaExtraID = " + identificador + ";");
                } else {
                    System.out.println("No está disponible, prueba con otros datos");
                    return getReservaExtra(identificador);
                }
                con.close();
            } catch (SQLException e) {
                con.close();
                e.printStackTrace();
            }

        }
        return getReservaExtra(identificador);
    }

    public boolean comprobarDisponibilidad(Reserva reserva) {
        int cuenta;
        int id;
        LocalDateTime fecha;
        boolean disponible = false;
        String tabla;
        String primarykey;
        try {
            if (reserva instanceof RecursoExtra) {
                id = reserva.getId();
                fecha = reserva.getFecha();
                tabla = "reservaextra";
                primarykey = "recursoExtraID";
                try {
                    if (conector() == true) {                                       //TODO no sería así porque tabla reservaextra no tiene columna fecha
                        String queryBD = "select count(*) as disponibilidad from '" + tabla + "' where '" + primarykey + "' = '" + id + "' ;";
                        try {
                            rS = createStatement.executeQuery(queryBD);
                            while (rS.next()) {
                                System.out.println("prueba");
                                cuenta = rS.getInt("disponibilidad");
                                System.out.println(cuenta);
                                if (cuenta > 0) {
                                    disponible = false;
                                } else {
                                    disponible = true;
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println("Error de disponibilidad");
                            e.printStackTrace();
                            Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else if (reserva instanceof RecursoExtra){
                id = reserva.getId();
                fecha = reserva.getFecha();
                tabla = "reservaextra";
                primarykey = "recursoExtraID";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disponible;
    }

    public Reserva getReserva ( int reservaID){
        Reserva reserva = new Reserva();
        String query = "";
        String queryExtra = "";
        try {
            if (conector() == true) {
                try {

                    while (rS.next()) { //TODO mal ??¿?¿¿
                        TipoElementoReservable tipo = TipoElementoReservable.valueOf(rS.getString("tipo"));
                        TipoRecursoExtra tipoRecursoExtra = TipoRecursoExtra.valueOf(rS.getString("tipo"));
                        if (tipo.equals(TipoElementoReservable.S) || tipo.equals(TipoElementoReservable.P)) {
                            Reserva reservaNormal = new Reserva();
                            reservaNormal.setElementoReservableID(rS.getInt("elementoReservableID"));
                            reservaNormal.setId(rS.getInt("reservaID"));
                            reservaNormal.setUsuarioID(rS.getInt("usuarioID"));
                            reservaNormal.setFecha(rS.getObject("fecha", LocalDateTime.class));
                            reserva = reservaNormal;
                            query = "select url, usuarioID, elementoReservableID, fecha from reserva where reservaID = '" + reservaID + "';";
                            rS = createStatement.executeQuery(query);
                        } else if (tipoRecursoExtra.equals(TipoRecursoExtra.O) || tipoRecursoExtra.equals(TipoRecursoExtra.L)) {
                            Reserva reservaExtra = new Reserva();
                            reservaExtra.setRecursoExtraID(rS.getInt("recursoExtraID"));
                            reservaExtra.setId(rS.getInt("reservaID"));
                            reserva = reservaExtra;
                            queryExtra = "select RecursoExtraID, url from reservaextra where reservaID = '" + reservaID + "';";
                            rS = createStatement.executeQuery(queryExtra);

                        }

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

    public Collection<ReservaShort> getAllReservasExtra () {
        List<ReservaShort> reservas = new ArrayList();
        Reserva reserva = new Reserva();
        Integer reservaID = reserva.getId();
        String query = "";
        String queryExtra = "";
        try {
            if (conector() == true) {
                String queryBD = "select reservaID, url from reserva";
                String queryBD2 = "select reservaID, RecursoExtraID from reservaextra";

                try {
                    rS = createStatement.executeQuery(queryBD);

                    while (rS.next()) {
                        TipoElementoReservable tipo = TipoElementoReservable.valueOf(rS.getString("tipo"));
                        TipoRecursoExtra tipoRecursoExtra = TipoRecursoExtra.valueOf(rS.getString("tipo"));
                        if (tipo.equals(TipoElementoReservable.S) || tipo.equals(TipoElementoReservable.P)) {
                            Reserva reservaNormal = new Reserva();
                            reservaNormal.setElementoReservableID(rS.getInt("elementoReservableID"));
                            reservaNormal.setId(rS.getInt("reservaID"));
                            reservaNormal.setUsuarioID(rS.getInt("usuarioID"));
                            reservaNormal.setFecha(rS.getObject("fecha", LocalDateTime.class));
                            reserva = reservaNormal;
                            query = "select url, reservaID from reserva;";
                            rS = createStatement.executeQuery(query);
                        } else if (tipoRecursoExtra.equals(TipoRecursoExtra.O) || tipoRecursoExtra.equals(TipoRecursoExtra.L)) {
                            Reserva reservaExtra = new Reserva();
                            reservaExtra.setRecursoExtraID(rS.getInt("recursoExtraID"));
                            reservaExtra.setId(rS.getInt("reservaID"));
                            reserva = reservaExtra;
                            queryExtra = "select RecursoExtraID, url from reservaextra where reservaID = '" + reservaID + "';";
                            rS = createStatement.executeQuery(queryExtra);

                        }


                        //Cada vuelta while es un línea del resultado de la consulta -> Reserva
                        ReservaShort reserva = new ReservaShort();
                        reserva.setId(rS.getInt("reservaID"));
                        reserva.setUrl(rS.getString("url"));

                        reservas.add(reserva);

                    }

                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllReservasExtra");
                    ex.printStackTrace();
                    Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return reservas;
    }

    public boolean deleteReservaExtra ( int reservaExtraID) throws SQLException, ClassNotFoundException {
        boolean valor = false;
        try {
            if (conector() == true) {

                String queryBD = "delete from reserva extra where reservaID = '" + reservaExtraID + "';"; //TODO where PK = RecExID+ResID

                try {
                    createStatement.executeUpdate(queryBD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - deleteReservaExtra");
                    Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public Cambio modifyReservaExtra (CambioFecha cam,int reservaExtraID) throws
            SQLException, ClassNotFoundException {
        try {
            if (conector()) {

                CambioFecha cfr = cam;
                LocalDateTime fecha = cfr.getFecha();

                createStatement.executeUpdate("update reserva extra set fecha = '" + fecha + "'  where reservaID = '" + reservaExtraID + "';"); //TODO PK no es ReservaExtraID
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }

    public Reserva update (Reserva reserva,int reservaExtraID) throws
            SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                //int id = biblioteca.getId();
                //String url = biblioteca.getUrl();

                int recursoExtraID = reserva.getRecursoExtraID();
                int reservaID = reserva.getId();

                                                                                                                                     //TODO where PK????
                String queryBD = "update reservaextra set reservaID = '" + reservaID + "', recursoExtraID = '" + recursoExtraID + "' where reservaExtraID = '" + reservaExtraID + "';";

                try {
                    createStatement.executeUpdate(queryBD);
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                return null; //TODO cambiar todos nulls por exception
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaExtraBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getReservaExtra(reservaExtraID);
    }

}


 */