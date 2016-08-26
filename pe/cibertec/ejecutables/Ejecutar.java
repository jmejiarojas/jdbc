package pe.cibertec.ejecutables;

import pe.cibertec.dao.interfaces.UsuarioDao;
import pe.cibertec.dao.mysql.MySqlUsuario;
import pe.cibertec.dao.postgresql.PSQLUsuario;
import pe.cibertec.modelos.Usuario;

import java.util.List;
import java.util.Scanner;

public class Ejecutar {

    public static void main(String args[]) {

        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione la BD que va a usar: ");
        System.out.println("1. Postgresql");
        System.out.println("2. MySQL");
        int opcion = teclado.nextInt();

        UsuarioDao userDAO = null;

        switch (opcion){
            case 1:
                userDAO = new PSQLUsuario();
                break;
            case 2:
                userDAO = new MySqlUsuario();
                break;
            default:
                System.out.println("No es una opción válida");
        }

        try {
            List<Usuario> listado =  userDAO.listar();
            for (Usuario user : listado){
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /*
        Usuario user = new Usuario();
        user.setIdUsuario(3);
        user.setUsuario("miamor");
        user.setClave("miamor");
        user.setCorreo("jhanka@gmail.com");
        */
        /*
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
        */

    }
}
