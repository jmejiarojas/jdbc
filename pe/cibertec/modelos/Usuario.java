package pe.cibertec.modelos;

public class Usuario {

    private int idUsuario;
    private String usuario;
    private String clave;
    private String correo;

    public Usuario(){

    }

    public Usuario(int idUsuario, String usuario, String clave, String correo) {
        this.setIdUsuario(idUsuario);
        this.setUsuario(usuario);
        this.setClave(clave);
        this.setCorreo(correo);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String toString(){
        return "Usuario{" + "idUsuario="+idUsuario+",usuario="+usuario+",clave="+clave+",correo="+correo;
    }
}
