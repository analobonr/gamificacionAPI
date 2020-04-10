package partidas;

import java.util.List;


public interface PartidasDAO {

	public Partida buscar (int id);
	
	public boolean guardar(Partida cp);
	
	public boolean modificar(Partida cp);
	
	public List<Partida> listar(Integer id_profesor);
	
	public boolean eliminar(int id);
	
	public boolean eliminarLista(List<Integer> ids);
}
