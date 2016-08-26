package pe.cibertec.dao.postgresql;

import pe.cibertec.dao.interfaces.UsuarioDao;
import pe.cibertec.dao.mysql.MySqlConexion;
import pe.cibertec.excepciones.ExcepcionGeneral;
import pe.cibertec.modelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PSQLUsuario implements UsuarioDao{

    private final String INSERTAR = "INSERT INTO usuarios(idUsuario, usuario, clave, correo) VALUES(null, ?, md5(?), ?) RETURNING idUsuario";
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
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(INSERTAR);
            sentencia.setString(1,usuario.getUsuario());
            sentencia.setString(2, usuario.getCorreo());
            sentencia.setString(2, usuario.getClave());
            resultados = sentencia.executeQuery();
            if(resultados.next()){
                usuario.setIdUsuario(resultados.getInt(1));
            }else{
                throw new ExcepcionGeneral("No se insertó ningún registro");
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        }finally {
            cerrarConexiones();
        }
    }

    @Override
    public void modificar(Usuario usuario) {

    }

    @Override
    public void eliminar(Usuario usuario) {

    }

    @Override
    public Usuario obtenerPorId(Integer llave) {
        return null;
    }

    @Override
    public List<Usuario> listar() {
        List<Usuario> listado = new ArrayList<>();
        try {
            conexion = new PSQLConexion().conectar();
            sentencia = conexion.prepareStatement(OBTENER);
            resultados = sentencia.executeQuery();
            while (resultados.next()){
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultados.getInt(1));
                usuario.setUsuario(resultados.getString(2));
                usuario.setCorreo(resultados.getString(3));
                usuario.setClave(resultados.getString(4));
                listado.add(usuario);
            }
        } catch (SQLException e) {
            throw new ExcepcionGeneral(e.getMessage());
        }finally {
            cerrarConexiones();
        }

        return  listado;
    }

    private void cerrarConexiones(){

        try {
            if(resultados != null) resultados.close();
            if(sentencia != null) sentencia.close();
            if(conexion != null) conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
