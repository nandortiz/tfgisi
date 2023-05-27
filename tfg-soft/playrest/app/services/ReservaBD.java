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
        Integer recursoExtraID = null;
        Integer identificador = -1;
        LocalDateTime fecha;
        boolean disponibilidad;

        if (conector() == true) {
            try {

                elementoReservableID = reserva.getElementoReservableID();
                usuarioID = reserva.getUsuarioID();
                fecha = reserva.getFecha();
                disponibilidad = comprobarDisponibilidad(reserva);

                System.out.println(disponibilidad);

                if (disponibilidad == true) {
                    String query = "INSERT INTO reserva (usuarioID, elementoReservableID, fecha) VALUES ('" + usuarioID + "','" + elementoReservableID + "', '" + fecha + "');";
                    //System.out.println(query);
                    // createStatement.executeUpdate("set foreign_key_checks = 0;");
                    createStatement.executeUpdate(query, RETURN_GENERATED_KEYS);
                    ResultSet prueba = createStatement.getGeneratedKeys();
                    prueba.next();
                    identificador = prueba.getInt(1);
                    String url = "/reserva/" + identificador;
                    createStatement.executeUpdate("UPDATE  reserva set url ='" + url + "' where reservaID = " + identificador + ";");
                } else {
                    System.out.println("No está disponible, prueba con otros datos");
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

    public boolean comprobarDisponibilidad(Reserva reserva) {
        int cuenta;
        int id;
        LocalDateTime fecha;
        boolean disponible = false;
        String tabla;
        String primarykey;
        String tipoReserva = "indefinido";
        int idRecursoExtra;

        if (reserva.getRecursoExtraID() != null){
            System.out.println(reserva.getRecursoExtraID());
            tipoReserva = "reservaExtra";
            System.out.println(tipoReserva);
        }else{
            tipoReserva = "elementoReservable";
        }

        try {
            if (tipoReserva == "elementoReservable") {
                id = reserva.getElementoReservableID();
                fecha = reserva.getFecha();
                tabla = "reserva";
                primarykey = "elementoReservableID";
                System.out.println(fecha);
                System.out.println(id);
                try {
                    if (conector() == true) {
                        String queryBD = "select count(*) as disponibilidad from " + tabla +  " where "  + primarykey + " = '" + id + "' and fecha = '" + fecha + "';";
                        System.out.println(queryBD);
                        try {
                            System.out.println(tipoReserva);
                            rS = createStatement.executeQuery(queryBD);
                            while (rS.next()) {
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
                            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, e);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {

                    id = reserva.getId();
                    idRecursoExtra = reserva.getRecursoExtraID();

                    tabla = "recursoextra";
                    primarykey = "recursoExtraID";
                    try {
                        if (conector() == true) {
                            String queryBD = "select count(*) as disponibilidad from " + tabla + " where " + primarykey + " = '" + idRecursoExtra + "' and reservaID =  '" + id +  "';";
                            try {
                                rS = createStatement.executeQuery(queryBD);
                                while (rS.next()) {
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
                                Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, e);
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disponible;
    }
            public Reserva getReserva ( int reservaID){
                Reserva reserva = new Reserva();
                try {
                    if (conector() == true) {
                        String queryBD = "select reservaID, usuarioID, elementoReservableID, fecha from reserva where reservaID = '" + reservaID + "';";
                        try {
                            rS = createStatement.executeQuery(queryBD);
                            while (rS.next()) {
                                reserva.setId(rS.getInt("reservaID"));
                                reserva.setUsuarioID(rS.getInt("usuarioID"));
                                reserva.setElementoReservableID(rS.getInt("elementoReservableID"));
                                reserva.setFecha(rS.getObject("fecha", LocalDateTime.class));
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

            public Collection<ReservaShort> getAllReservas () {
                List<ReservaShort> reservas = new ArrayList();

                try {
                    if (conector() == true) {
                        String queryBD = "select reservaID, url from reserva";

                        try {
                            rS = createStatement.executeQuery(queryBD);

                            while (rS.next()) {
                                //Cada vuelta while es un línea del resultado de la consulta -> Reserva
                                ReservaShort reserva = new ReservaShort();
                                reserva.setId(rS.getInt("reservaID"));
                                reserva.setUrl(rS.getString("url"));

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

            public boolean deleteReserva ( int reservaID) throws SQLException, ClassNotFoundException {
                boolean valor = false;
                try {
                    if (conector() == true) {

                        String queryBD = "delete from reserva where reservaID = '" + reservaID + "';";

                        try {
                            createStatement.executeUpdate(queryBD);
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

            public Cambio modifyReserva (CambioFecha cam,int reservaID) throws
            SQLException, ClassNotFoundException {
                try {
                    if (conector()) {

                        CambioFecha cfr = cam;
                        LocalDateTime fecha = cfr.getFecha();

                        createStatement.executeUpdate("update reserva set fecha = '" + fecha + "'  where reservaID = '" + reservaID + "';");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                return cam;
            }

            public Reserva update (Reserva reserva,int reservaID) throws
            SQLException, ClassNotFoundException {
                try {
                    if (conector() == true) {
                        //int id = biblioteca.getId();
                        //String url = biblioteca.getUrl();
                        int usuarioID = reserva.getUsuarioID();
                        int elementoReservableID = reserva.getElementoReservableID();
                        LocalDateTime fecha = reserva.getFecha();


                        String queryBD = "update reserva set usuarioID = '" + usuarioID + "', elementoReservableID = '" + elementoReservableID + "', fecha = '" + fecha + "' where reservaID = '" + reservaID + "';";

                        try {
                            createStatement.executeUpdate(queryBD);
                        } catch (SQLException ex) {
                            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            con.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        return null; //TODO cambiar todos nulls por exception
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                return getReserva(reservaID);
            }


        }

