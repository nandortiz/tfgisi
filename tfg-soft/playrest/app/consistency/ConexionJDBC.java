package consistency;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionJDBC {
    static String driver = "com.mysql.jdbc.Driver";
    static String url ="jdbc:mysql://localhost:3306/gestion?useSSL=false";
    static String username = "root";
    static String pass = "root";
    public ResultSet closeCn(ResultSet rs, Connection cn) throws SQLException {
        try{
            return rs;
        }
        catch (Exception e){

        }finally {
            cn.close();
        }
        return null;
    }
    public static Connection connect(){
        try {
            // make the connection
            Class.forName(driver);
            Connection cn = DriverManager.getConnection(url, username, pass);
            return cn;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
