package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.mysql.jdbc.*;



public class ConexionBD {

    protected static Connection con;
    protected static final String driver = "com.mysql.jdbc.Driver";
    protected static final String user = "admin";
    protected static final String pass = "12345678";
    protected static final String url ="jdbc:mysql://database.c1berg3jrqwq.eu-west-1.rds.amazonaws.com:3306/gestion?useSSL=false";


    protected Statement createStatement;

    protected ResultSet rS;

    protected boolean conector() throws SQLException, ClassNotFoundException {
        //Reseteo la conexión a la base de datos a NULL
        con = null;
        boolean valor = false;
        try {
            int i = 0; //----String valor="fallo";
            Class.forName(driver);
            //Nos conectamos a BD
            con= (com.mysql.cj.jdbc.JdbcConnection) DriverManager.getConnection(url, user, pass);
            //con= DriverManager.getConnection(url, user, pass);
            //Si se conecta correctamente, se muestra mensaje de conexión establecida
            if (con != null) ;
            {

                createStatement = con.createStatement();
                  String crear = "create database if not exists gestion;";
                String uso = "use gestion;";
                String crearUsuario = "create table if not exists usuario(\n" +
                        "id int(100) primary key auto_increment not null, \n" +
                        "url varchar (40), \n" +
                        "nombre varchar (40) not null, \n" +
                        "apellidos varchar (40) not null, \n" +
                        "grado varchar (20) not null\n" +
                        ");";

                String crearBiblioteca = "create table if not exists biblioteca(\n" +
                        "id int(100) primary key auto_increment not null,\n" +
                        "url varchar (40) default null,\n" +
                        "nombre varchar (40) not null,\n" +
                        "descripcion varchar (100) not null\n" +
                        ");";

                String crearElementoReservable = "create table if not exists elementoreservable(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "url varchar (50) default null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "tipo varchar (1) default null, \n" +
                        "bibliotecaID int default null,\n" +
                        "aforoSala int (2) default null, \n" +
                        "infoPuesto varchar(30) default null, \n" +
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade\n" +
                        ");";


                String crearRecursoExtra = "create table if not exists recursoextra(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "nombre varchar (100) default null, \n" +
                        "url varchar (50) default null,\n" +
                        "descripcion varchar (100) not null,\n" +
                        "tipo varchar (1) default null, \n" +
                        "bibliotecaID int default null,\n" +
                        "numSerie varchar(30) default null, \n" +
                        "ISBN int default null, \n" +
                        "foreign key (bibliotecaID) references biblioteca (id) on delete cascade\n" +
                        ");";

                String crearReserva = "create table if not exists reserva(\n" +
                        "id int (100) primary key auto_increment not null,\n" +
                        "url varchar (40) default null,\n" +
                        "usuarioID int default null,\n" +
                        "elementoReservableID int,\n" +
                        "fecha datetime,\n" +
                        "foreign key (usuarioID) references usuario (id) on delete cascade,\n" +
                        "foreign key (elementoReservableID) references elementoReservable(id) on delete cascade \n" +
                        ");";

                String crearReservaExtra = "create table if not exists reservaextra(\n" +
                        "RecursoExtraID int (100) not null,\n" +
                        "reservaID int (100) not null,\n" +
                        "foreign key (RecursoExtraID) references recursoextra (id) on delete cascade,\n" +
                        "foreign key (reservaID) references reserva(reservaID) on delete cascade, \n" +
                        "primary key (reservaID, RecursoExtraID)\n" +
                        ");";



                createStatement.executeUpdate(crear);
                createStatement.executeUpdate(uso);
                createStatement.executeUpdate(crearBiblioteca);
                createStatement.executeUpdate(crearElementoReservable);
                createStatement.executeUpdate(crearRecursoExtra);
                createStatement.executeUpdate(crearUsuario);
                createStatement.executeUpdate(crearReserva);
                createStatement.executeUpdate(crearReservaExtra);

                valor = true;
            }
        }
        //Si no se realiza correctamente la conexión a la BD, se muestra el mensaje:
        catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return valor;


    }
}