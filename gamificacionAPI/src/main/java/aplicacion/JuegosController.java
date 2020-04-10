package aplicacion;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import juegos.JPAJuegosDao;
import juegos.Juego;
import juegos.JuegosDao;


@RestController
@RequestMapping("/juegos")
public class JuegosController {
	
	private JuegosDao jDao = new JPAJuegosDao();
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Juego insetar(@PathVariable("id") int id) throws Exception {
	   
	   return jDao.buscar(id);
    	
    }
	
	@GetMapping
	   public List<Juego> listar() throws Exception {
		   
		   
		   List<Juego> juegos = jDao.listar();
		   /*Hashtable<Integer,String> idNombre = new Hashtable<Integer,String>();
		   
		   for (Juego j:juegos) {
			   idNombre.put(j.getIdJuego(),j.getNombre());
		   }*/
		   
		   return juegos;
	   	
	   }
	
	@PostMapping
    public Estado registro(@RequestBody Juego j) throws Exception {
    	
		
    	
    	return new Estado(jDao.guardar(j));
    	
    }
	
	@PostMapping ("/modificar")
    public Estado modificar(@RequestBody Juego j) throws Exception {
		
    	
    	return new Estado(jDao.modificar(j));
    	
    }
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void eliminarID(@PathVariable("id") int id) throws Exception {
    	jDao.eliminar(id);
    	
    }
	
	@DeleteMapping
    public Estado eliminarLista(@RequestBody List<Integer> ids) throws Exception {
		
    	
    	return new Estado(jDao.eliminarLista(ids));
    	
    }

}
