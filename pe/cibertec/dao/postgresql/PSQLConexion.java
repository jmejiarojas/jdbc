package pe.cibertec.dao.postgresql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PSQLConexion {

    public Connection conectar(){
        Connection conexion = null;
        String url = "jdbc:postgresql://127.0.0.1:5432/BD_JDBC";
        Properties propiedades = new Properties();
        propiedades.setProperty("user","usuario_postgresql");
        propiedades.setProperty("password","pwd_postregsql");

        try {
            conexion = DriverManager.getConnection(url, propiedades);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conexion;
    }


}
