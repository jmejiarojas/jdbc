package pe.cibertec.dao.mysql;

import com.sun.javafx.scene.control.skin.LabeledImpl;
import pe.cibertec.dao.interfaces.UsuarioDao;
import pe.cibertec.excepciones.ExcepcionGeneral;
import pe.cibertec.modelos.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlUsuario implements UsuarioDao{

    private final String INSERTAR = "INSERT INTO usuarios(idUsuario, usuario, clave, correo) VALUES(null, ?, md5(?), ?)";
    private final String MODIFICAR = "UPDATE usuarios SET usuario =?, clave=md5(?), correo=? WHERE idUsuario = ? ";
    private final String ELIMINAR = "DELETE FROM usuarios WHERE idUsuario=?";
    private final String OBTENER_POR_ID = "SELECT idUsuario, usuario, clave, correo FROM usuarios WHERE idUsuario=?";
    private final String OBTENER = "SELECT idUsuario, usuario, clave, correo FROM usuarios";

    private Connection conexion;
    private PreparedStatement sentencia;
    private ResultSet resultados;
    

    @Override
    public void insertar(Usuario usuario) {
        try {
            conexion = new MySqlConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            sentencia.setString(1,usuario.getUsuario());
            sentencia.setString(2,usuario.getClave());
            sentencia.setString(3,usuario.getCorreo());
            //executeUpdate = Insert, Update, Delete
            //executeQuery = Select
            // executeUpdate() --> Devuelve la cantidad de registros que fueron afectados.
            if(sentencia.executeUpdate() == 0){
                throw new ExcepcionGeneral("No se insertó el registro");
            }
            resultados = sentencia.getGeneratedKeys();
            if(resultados.next()){
                usuario.setIdUsuario(resultados.getInt(1));
            }
        } catch (SQLException e) {
            throw  new ExcepcionGeneral(e.getMessage());
        }finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(Usuario usuario) {
        try {
            conexion = new MySqlConexion().conectar();
            sentencia = conexion.prepareStatement(MODIFICAR);
            sentencia.setString(1,usuario.getUsuario());
            sentencia.setString(2,usuario.getClave());
            sentencia.setString(3,usuario.getCorreo());
            sentencia.setInt(4,usuario.getIdUsuario());

            if(sentencia.executeUpdate()==0){
                throw new ExcepcionGeneral("No se actualizó ningún registro");
            }

        } catch (Exception e) {
            throw new ExcepcionGeneral(e.getMessage());
        }finally {
            cerrarConexiones();
        }
    }

    @Override
    public void eliminar(Usuario usuario) {
        try {
            conexion = new MySqlConexion().conectar();
            sentencia = conexion.prepareStatement(ELIMINAR);
            sentencia.setInt(1,usuario.getIdUsuario());

            if(sentencia.executeUpdate() == 0){
                throw new ExcepcionGeneral("No se eliminó el registro");
            }
        } catch (SQLException e) {
            throw  new ExcepcionGeneral(e.getMessage());
        }finally {
            cerrarConexiones();
        }
    }

    @Override
    public Usuario obtenerPorId(Integer llave) {

        Usuario usuario = null;

        try {
            conexion = new MySqlConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER_POR_ID);
            resultados = sentencia.executeQuery();

            if(resultados.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(resultados.getInt("idUsuario"));
                usuario.setUsuario(resultados.getString("usuario"));
                usuario.setClave(resultados.getString("clave"));
                usuario.setCorreo(resultados.getString("correo"));
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral("No se obtuvo al usuario");
        }finally {
            cerrarConexiones();
        }

        return usuario;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listado = new ArrayList<>();

        try {
            conexion = new MySqlConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();

            while (resultados.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultados.getInt("idUsuario"));
                usuario.setUsuario(resultados.getString("usuario"));
                usuario.setClave(resultados.getString("clave"));
                usuario.setCorreo(resultados.getString("correo"));
                listado.add(usuario);
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        }finally {
            cerrarConexiones();
        }

        return listado;
    }
    
    private void cerrarConexiones(){

        try {
            if (resultados != null) resultados.close();
            if (sentencia != null) sentencia.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


















