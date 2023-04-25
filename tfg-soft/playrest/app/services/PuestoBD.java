package services;

import entities.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;

public class PuestoBD extends ConexionBD {

    private static PuestoBD instance;
    public static PuestoBD getInstance() {
        if (instance == null) {
            instance = new PuestoBD();
        }
        return instance;
    }


    public Puesto addPuesto(Puesto puesto, int bibliotecaID) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;
        String url = ""; //TODO no se usa, borrar?
        if (conector() == true) {
            try {

                String descripcion = puesto.getDescripcion();
                TipoElementoReservable tipo = puesto.getTipo();
                String infoPuesto = puesto.getInfo();

                Statement st = cn.createStatement();
                System.out.println("INSERT INTO elementoReservable (descripcion, tipo, bibliotecaID, infoPuesto) VALUES ('"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+infoPuesto+"');");
                st.executeUpdate("INSERT INTO elementoReservable (descripcion, tipo, bibliotecaID, infoPuesto) VALUES ('"+descripcion+"','"+tipo+"','"+bibliotecaID+"', '"+infoPuesto+"');", Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/biblioteca/"+bibliotecaID+"/puestos/";
                String urlNuevoPuesto=patronURL+identificador;

                //UPDATE del puesto con id = id y actualizar la url con urlNuevoPuesto
                st.executeUpdate("UPDATE elementoReservable set url='" + urlNuevoPuesto+ "' where id= "+ identificador +";");

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - addSala");
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getPuesto(identificador);
        //return url;
    }

    public Puesto getPuesto(int id) {Puesto puesto = new Puesto();

        try {
            if (conector() == true) {
                String queryBD = "select id, descripcion, tipo, bibliotecaID, infoPuesto from elementoReservable where id="+id+";";
                try {

                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()){
                        puesto.setId(rS.getInt("id"));
                        puesto.setDescripcion(rS.getString("descripcion"));
                        //puesto.setTipo(TipoElementoReservable.valueOf(rS.getString("tipo")));
                        puesto.setBibliotecaID(rS.getInt("bibliotecaID"));
                        puesto.setInfo(rS.getString("infoPuesto"));
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getPuesto");
                    ex.printStackTrace();
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (puesto.getId()==-1){
            return null;
        }
        else {
            return puesto;
        }

    }

    public Collection<PuestoShort> getAllPuestos(int bibliotecaID) {
        List<PuestoShort> puestos = new ArrayList();

        try {
            if (conector() == true) {
                String queryBD = "select id, url from elementoreservable where bibliotecaID = '"+bibliotecaID+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Biblioteca
                        PuestoShort puesto = new PuestoShort();
                        puesto.setId(rS.getInt("id"));
                        puesto.setUrl(rS.getString("url"));

                        puestos.add(puesto);
                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllPuestos");
                    ex.printStackTrace();
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return puestos;
    }

    public boolean deletePuesto(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBD = "delete from elementoReservable where id = '"+id+"';";

                try {
                    createStatement.executeUpdate(queryBD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - deletePuesto"); //TODO estaba deleteBiblioteca
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }
    public Puesto modify (Puesto cam, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector()) {
                          }
        } catch (SQLException ex) {
            Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }

    public Puesto update(Puesto puesto, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {

                String info = puesto.getInfo(); //TODO sólo cambiar
                String queryBD = "update elementoreservable set infoPuesto = '"+info+"' where id = '"+id+"' ;";
                try {
                    createStatement.executeUpdate(queryBD);
                } catch (SQLException ex) {
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                return null; //TODO cambiar todos nulls por exception
            }
        } catch (SQLException ex) {
            Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PuestoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getPuesto(id);
    }

}
