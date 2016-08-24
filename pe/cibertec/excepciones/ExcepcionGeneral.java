package pe.cibertec.excepciones;


public class ExcepcionGeneral extends RuntimeException{

    public ExcepcionGeneral(){
        this("Ha ocurrido una excepcion");
    }

    public ExcepcionGeneral(String mensaje){
        super(mensaje);
    }

}
