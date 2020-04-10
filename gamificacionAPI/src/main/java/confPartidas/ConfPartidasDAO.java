package confPartidas;

import java.util.List;


public interface ConfPartidasDAO {

	public ConfPartida buscar (int id);
	
	public boolean guardar(ConfPartida cp);
	
	public boolean modificar(ConfPartida cp);
	
	public List<ConfPartida> listar(Integer id_usuario);
	
	public boolean eliminar(int id);
	
	public boolean eliminarLista(List<Integer> ids);
}
