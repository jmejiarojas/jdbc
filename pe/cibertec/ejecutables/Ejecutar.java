package pe.cibertec.ejecutables;

import pe.cibertec.dao.mysql.MySqlUsuario;
import pe.cibertec.modelos.Usuario;

import java.util.List;

public class Ejecutar {

    public static void main(String args[]) {
        /*
        Usuario user = new Usuario();
        user.setIdUsuario(3);
        user.setUsuario("miamor");
        user.setClave("miamor");
        user.setCorreo("jhanka@gmail.com");
        */
        MySqlUsuario mysqlUser = new MySqlUsuario();
        try {
            List<Usuario> listado;
            listado = mysqlUser.listar();
            for (Usuario user: listado){
                System.out.println(user);
            }
            //System.out.println("Usuario registrado con el id: " + user.getIdUsuario());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }
}
