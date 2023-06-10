package services;


import entities.*;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static consistency.ConexionJDBC.connect;

public class BibliotecaBD extends ConexionBD {

    private static BibliotecaBD instance;

    public static BibliotecaBD getInstance() {
        if (instance == null) {
            instance = new BibliotecaBD();
        }
        return instance;
    }


    public Biblioteca addBiblioteca(Biblioteca biblioteca) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;

        if (conector() == true) {

            try {

                String nombre = biblioteca.getNombre();
                String descripcion = biblioteca.getDescripcion();


                Statement st = cn.createStatement();
                st.executeUpdate("INSERT INTO biblioteca (nombre,descripcion) VALUES ('" + nombre + "', '" + descripcion + "');", Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/bibliotecas/";
                String urlNuevaBiblioteca=patronURL+identificador;
                //UPDATE de la biblioteca con id = id y actualizar la url con urlNuevaBiblioteca
                st.executeUpdate("UPDATE biblioteca set url = '"+urlNuevaBiblioteca+"' where id= '"+identificador+"';");
                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - addBiblioteca");
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getBiblioteca(identificador);
        //return url;
    }

    public Biblioteca getBiblioteca(int id) {
        Biblioteca biblioteca = new Biblioteca();

        try {
            if (conector() == true) {
                String queryBD = "select id, nombre, descripcion from biblioteca where id = '"+id+"';";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()){
                        biblioteca.setId(rS.getInt("id"));
                        biblioteca.setNombre(rS.getString("nombre"));
                        biblioteca.setDescripcion(rS.getString("descripcion"));

                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getBiblioteca");
                    ex.printStackTrace();
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (biblioteca.getId()==-1){
            return null;
        }
        else{
            return biblioteca;
        }
    }

    public Collection<BibliotecaShort> getAllBibliotecas() {
        List<BibliotecaShort> bibliotecas = new ArrayList();
        try {
            if (conector() == true) {
                String queryBD = "select id, url from biblioteca";
                try {
                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Biblioteca
                        BibliotecaShort biblioteca = new BibliotecaShort();

                        biblioteca.setId(rS.getInt("id"));
                        biblioteca.setUrl(rS.getString("url"));

                        bibliotecas.add(biblioteca);

                    }

                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllBibliotecas");
                    ex.printStackTrace();
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return bibliotecas;
    }

    public boolean deleteBiblioteca(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBD = "delete from biblioteca where id = '"+id+"';";

                try {
                    createStatement.executeUpdate(queryBD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {
                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - deleteBiblioteca");
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }



    public CambioBiblioteca modify(CambioBiblioteca cam, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                LocalDateTime franja = cam.getFranja();

                switch (cam.getTipo()) {

                    case APERTURA:
                        createStatement.executeUpdate("update biblioteca set apertura ='"+ franja+"'  where id = '"+id+"';");
                        break;

                    case CIERRE:
                        createStatement.executeUpdate("update biblioteca set cierre = '"+ franja+"'  where id = '"+id+"';");
                        break;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }

    public Biblioteca update(Biblioteca biblioteca, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                String nombre = biblioteca.getNombre();
                String descripcion= biblioteca.getDescripcion();


                String queryBD = "update biblioteca set nombre = '"+nombre+"', descripcion = '"+descripcion+"'  where id = '"+id+"';";

                try {
                    createStatement.executeUpdate(queryBD);
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getBiblioteca(id);
    }

}