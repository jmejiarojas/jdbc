package pe.cibertec.dao.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConexion {

    public Connection conectar() throws SQLException{
        
        Connection conexion = null;
        String url = "jdbc:mysql://localhost:3306/BD_JDBC";
        String user = "root";
        String password= "Oracle11g";

        try {
            conexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
                throw e;
        }
        
        return conexion;
    }

}
