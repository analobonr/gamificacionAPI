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


import partidas.JPAPartidasDAO;
import partidas.Partida;
import partidas.PartidasDAO;
import profesores.JPAProfesorDao;
import profesores.ProfesorDao;



@RestController
@RequestMapping("/partidas")
public class PartidasController {
	
	private PartidasDAO partidaDao = new JPAPartidasDAO();
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Partida insetar(@PathVariable("id") int id) throws Exception {
	   
	   return partidaDao.buscar(id);
    	
    }
	
	@GetMapping(value="listar/{user}")
	   public List<Partida> listar(@PathVariable("user") String user) throws Exception {
		   
			ProfesorDao pDao = new JPAProfesorDao();
			List<Partida> configuracion = partidaDao.listar(pDao.buscarId(user));
		   /*Hashtable<Integer,String> idNombre = new Hashtable<Integer,String>();
		   
		   for (Partida j:configuracion) {
			   idNombre.put(j.getIdJuego(),j.getNombre());
		   }*/
		   
		   return configuracion;
	   	
	   }
	
	@PostMapping
    public Estado registro(@RequestBody Partida partida) throws Exception {
    	
		//Actualizamos las fechas de inicio y update
		partida.registrarFecha(true);		
    	
    	return new Estado(partidaDao.guardar(partida));
    	
    }
	
	@PutMapping
    public Estado modificar(@RequestBody Partida j) throws Exception {
		
    	
    	return new Estado(partidaDao.modificar(j));
    	
    }
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public Estado eliminarID(@PathVariable("id") int id) throws Exception {
		
    	
    	return new Estado(partidaDao.eliminar(id));
    	
    }
	
	@DeleteMapping
    public Estado eliminarLista(@RequestBody List<Integer> ids) throws Exception {
		
    	
    	return new Estado(partidaDao.eliminarLista(ids));
    	
    }

}
