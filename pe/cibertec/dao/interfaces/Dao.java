package pe.cibertec.dao.interfaces;

import java.util.List;

public interface Dao<Key, Objeto> {

    public void insertar(Objeto objeto);
    public void modificar(Objeto objeto);
    public void eliminar(Objeto objeto);
    public Objeto obtenerPorId(Key llave);
    public List<Objeto> listar();
}
