package aplicacion;

import java.util.ArrayList;
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

import confPartidas.ConfPartida;
import confPartidas.ConfPartidasDAO;
import confPartidas.JPAConfPartidasDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import profesores.JPAProfesorDao;
import profesores.ProfesorDao;



@RestController
@RequestMapping("/confPartidas")
@Api(tags = "Plantillas")
public class ConfPartidasController {
	
	private ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
	
	@ApiOperation(value = "Obtener una configuración de partida a partir de su identificador")
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
    public ConfPartida insertar(@PathVariable("id") int id) throws Exception {
	   
	   return cpDao.buscar(id);
    	
    }
	
	@GetMapping (value="/listar/{usuario}")
	   public List<ConfPartida> listar(@PathVariable("usuario") String usuario) throws Exception {
		   
		 	ProfesorDao pDao = new JPAProfesorDao();
		 	
		 	List<ConfPartida> configuracion = new ArrayList<ConfPartida>();
		 	try {
		 		configuracion = cpDao.listar(pDao.buscarId(usuario));
		 		
		 	}catch(Exception e) {
		 		System.err.println(e.toString());
		 	}
		 	
		   /*Hashtable<Integer,String> idNombre = new Hashtable<Integer,String>();
		   
		   for (ConfPartida j:configuracion) {
			   idNombre.put(j.getIdJuego(),j.getNombre());
		   }*/
		   
		   return configuracion;
	   	
	   }
	
	@PostMapping
    public Estado registro(@RequestBody ConfPartida j) throws Exception {
		
		System.out.println("Registrar configuracion partida");
    	
		j.registrarFecha();
    	
    	return new Estado(cpDao.guardar(j));
    	
    }
	
	@PostMapping ("/modificar")
    public Estado modificar(@RequestBody ConfPartida j) throws Exception {
		
    	return new Estado(cpDao.modificar(j));
    	
    }
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void eliminarID(@PathVariable("id") int id) throws Exception {
		
		cpDao.eliminar(id);
    	
    }
	
	@DeleteMapping
    public Estado eliminarLista(@RequestBody List<Integer> ids) throws Exception {
		
    	
    	return new Estado(cpDao.eliminarLista(ids));
    	
    }

}
