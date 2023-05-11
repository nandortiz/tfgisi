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


public class UsuarioBD extends ConexionBD{


    private static UsuarioBD instance;

    public static UsuarioBD getInstance() {
        if (instance == null) {
            instance = new UsuarioBD();
        }
        return instance;
    }

    public Usuario addUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        Connection cn = connect();
        int identificador = -1;

        String url = ""; //TODO no se usa, borrar?
        if (conector() == true) {

            try {

                String nombre = usuario.getNombre();
                String apellidos = usuario.getApellidos();
                String grado = usuario.getGrado();

                Statement st = cn.createStatement();
                System.out.println("INSERT INTO usuario (nombre,apellidos, grado) VALUES ('" + nombre + "', '" + apellidos + "', '" + grado + "')");

                st.executeUpdate("INSERT INTO usuario (nombre,apellidos, grado) VALUES ('" + nombre + "', '" + apellidos + "', '" + grado + "')", Statement.RETURN_GENERATED_KEYS);

                // A la nueva entidad hay que "establecerla la URL"
                ResultSet keys = st.getGeneratedKeys();
                keys.next();
                identificador = keys.getInt(1);

                String patronURL="/usuarios/";
                String urlNuevoUsuario=patronURL+identificador;
                //UPDATE del usuario con id = id y actualizar la url con urlNuevoUsuario
                st.executeUpdate("UPDATE usuario set url = '"+urlNuevoUsuario+"' where id= '"+identificador+"';");
                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - addUsuario");
                    Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        //return biblioteca;
        return getUsuario(identificador);
        //return url;
    }

    public Usuario getUsuario(int id) {
        Usuario usuario = new Usuario();

        try {
            if (conector() == true) {
                String queryBD = "select id, nombre, apellidos, grado from usuario where id = '"+id+"';";
                try {

                    rS = createStatement.executeQuery(queryBD);
                    while (rS.next()){
                        usuario.setId(rS.getInt("id"));
                        usuario.setNombre(rS.getString("nombre"));
                        usuario.setApellidos(rS.getString("apellidos"));
                        usuario.setGrado(rS.getString("grado"));

                    }
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getUsuario");
                    ex.printStackTrace();
                    Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (usuario.getId()==-1){
            return null;
        }
        else{
            return usuario;
        }
    }

    public Collection<UsuarioShort> getAllUsuarios() {
        List<UsuarioShort> usuarios = new ArrayList();

        try {
            if (conector() == true) {
                String queryBD = "select id, url from usuario";

                try {
                    rS = createStatement.executeQuery(queryBD);

                    while (rS.next()) {
                        //Cada vuelta while es un línea del resultado de la consulta -> Usuario
                        UsuarioShort usuario = new UsuarioShort();
                        usuario.setId(rS.getInt("id"));
                        usuario.setUrl(rS.getString("url"));

                        usuarios.add(usuario);

                    }

                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - getAllUsuarios");
                    ex.printStackTrace();
                    Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    public boolean deleteUsuario(int id) throws SQLException, ClassNotFoundException {
        boolean valor= false;
        try {
            if (conector() == true) {

                String queryBD = "delete from usuario where id = '"+id+"';";

                try {
                    createStatement.executeUpdate(queryBD);
                    valor = true;
                    return valor;
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    System.out.println("Error acceso base de datos - deleteUsuario");
                    Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{

            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valor;
    }

    public CambioGrado modifyUsuario(CambioGrado cam, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector()) {

                CambioGrado cgu = (CambioGrado) cam;
                String grado = cgu.getGrado();

                    createStatement.executeUpdate("update usuario set grado = '" + grado + "'  where id = '" + id + "';");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cam;
    }

    public Usuario update(Usuario usuario, int id) throws SQLException, ClassNotFoundException {
        try {
            if (conector() == true) {
                //int id = biblioteca.getId();
                //String url = biblioteca.getUrl();
                String nombre = usuario.getNombre();
                String apellidos = usuario.getApellidos();
                String grado = usuario.getGrado();


                String queryBD = "update usuario set nombre = '"+nombre+"', apellidos = '"+apellidos+"', grado = '"+grado+"'  where id = '"+id+"';";

                try {
                    createStatement.executeUpdate(queryBD);
                } catch (SQLException ex) {
                    Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
                }

                try {

                    con.close();
                } catch (SQLException ex) {
                    Logger.getLogger(BibliotecaBD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                return null; //TODO cambiar todos nulls por exception
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UsuarioBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return getUsuario(id);
    }



}
