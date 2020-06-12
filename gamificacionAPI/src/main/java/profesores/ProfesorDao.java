package profesores;

import java.util.List;

public interface ProfesorDao {

	public Profesor buscar (Integer id, String pass);
	
	public Integer buscarId (String mail);
	
	public Profesor nuevaPass (Integer id);
	
	public boolean guardar(Profesor p);
	
	public boolean modificar(Profesor p);
	
	public List<Profesor> listar();
	
	public boolean eliminar(Integer id);
	
	public void eliminarLista(List<Integer> ids);
	
}
