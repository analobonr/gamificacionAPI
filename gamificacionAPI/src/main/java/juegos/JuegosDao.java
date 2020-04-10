package juegos;

import java.util.List;

public interface JuegosDao {

	public Juego buscar (int id);
	
	public boolean guardar(Juego j);
	
	public boolean modificar(Juego j);
	
	public List<Juego> listar();
	
	public boolean eliminar(int id);
	
	public boolean eliminarLista(List<Integer> ids);
	
}
