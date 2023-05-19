package services;



import akka.actor.ProviderSelection;
import entities.*;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;

public class ReservaBD extends ConexionBD{

    private static ReservaBD instance;
    public static ReservaBD getInstance() {
        if (instance == null) {
            instance = new ReservaBD();
        }
        return instance;
    }

    public ReservaElementoReservable addReservaElementoReservable(ReservaElementoReservable reservaElementoReservable) throws SQLException, ClassNotFoundException {
        int usuarioID = 0;
        int bibliotecaID = 0;
        int elementoReservableID = 0;
        int identificador = -1;
        LocalDateTime fecha;

        if (conector() == true) {
            try {

                elementoReservableID = reservaElementoReservable.getElementoReservableID();
                usuarioID = reservaElementoReservable.getUsuarioID();
                fecha = reservaElementoReservable.getFecha();
               // Usuario usuario = reservaElementoReservable.getUsuario();
                //usuarioID = usuario.getId();
               // elementoReservableID = reservaElementoReservable.getId();
                //TODO fecha
                String query = "INSERT INTO reserva (usuarioID, elementoReservableID, fecha) VALUES ('" + usuarioID + "','" + elementoReservableID + "', '"+ fecha +"');";
                //System.out.println(query);
               // createStatement.executeUpdate("set foreign_key_checks = 0;");
                createStatement.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
                ResultSet prueba = createStatement.getGeneratedKeys();
                prueba.next();
                identificador = prueba.getInt(1);
                String url = "/reserva/" + identificador;
                createStatement.executeUpdate("UPDATE  reserva set url ='" + url + "' where reservaID = " + identificador + ";");

                con.close();
            } catch (SQLException e) {
                con.close();
                e.printStackTrace();
            }

        }
        return getReserva(identificador);
    }



    public ReservaElementoReservable getReserva(int reservaID) {
        ReservaElementoReservable reservaElementoReservable = new ReservaElementoReservable();

        try {
            if (conector() == true) {
                String queryBD = "select reservaID, usuarioID, elementoReservableID, fecha from reserva where reservaID = '"+reservaID+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()){
                        reservaElementoReservable.setId(rS.getInt("reservaID"));
                        reservaElementoReservable.setUsuarioID(rS.getInt("usuarioID"));
                        reservaElementoReservable.setElementoReservableID(rS.getInt("elementoReservableID"));
                        reservaElementoReservable.setFecha(rS.getObject("fecha",LocalDateTime.class));
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
        if (reservaElementoReservable.getId()==-1){
            return null;

        }
        else{
            return reservaElementoReservable;
        }
    }

    public Collection<ReservaElementoReservableShort> getAllReservas() {
        List<ReservaElementoReservableShort> reservasElementosReservables = new ArrayList();

        try {
            if (conector() == true) {
                String queryBD = "select reservaID, url from reserva";

                try {
                    rS = createStatement.executeQuery(queryBD);

                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> ReservaElementoReservable
                        ReservaElementoReservableShort reservaElementoReservable = new ReservaElementoReservableShort();
                        reservaElementoReservable.setId(rS.getInt("reservaID"));
                        reservaElementoReservable.setUrl(rS.getString("url"));

                        reservasElementosReservables.add(reservaElementoReservable);

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

        return reservasElementosReservables;
    }

    public boolean deleteReservaElementoReservable(int reservaID) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBD = "delete from reserva where reservaID = '"+reservaID+"';";

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
                    System.out.println("Error acceso base de datos - deleteReservaElementoReservable");
                    Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ReservaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public Cambio modifyReservaElementoReservable(CambioFecha cam, int reservaID) throws SQLException, ClassNotFoundException {
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

    public ReservaElementoReservable update(ReservaElementoReservable reservaElementoReservable, int reservaID) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                //int id = biblioteca.getId();
                //String url = biblioteca.getUrl();
                int usuarioID = reservaElementoReservable.getUsuarioID();
                int elementoReservableID = reservaElementoReservable.getElementoReservableID();
                LocalDateTime fecha = reservaElementoReservable.getFecha();


                String queryBD = "update reserva set usuarioID = '"+usuarioID+"', elementoReservableID = '"+elementoReservableID+"', fecha = '"+fecha+"' where reservaID = '"+reservaID+"';";

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
            }
            else{
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
