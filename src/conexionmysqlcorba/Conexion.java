package conexionmysqlcorba;
import java.sql.*;

public class Conexion{
    public Connection conex;
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conex = DriverManager.getConnection("jdbc:mysql://localhost/conexionMysqlCorba","root","");
            System.out.println("Conexion establecida.");
        } catch (Exception e) {
        }
        return conex;
    }
}
