package aplicacion;


import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import profesores.JPAProfesorDao;
import profesores.Profesor;
import profesores.ProfesorDao;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuarios")
public class ProfesoresController {

   @RequestMapping(value="/{login}/{password}",method=RequestMethod.GET)
    public Profesor login(@PathVariable("login") String login, @PathVariable("password") String password ) throws Exception {
	   ProfesorDao pDao = new JPAProfesorDao();
	   Integer id = pDao.buscarId(login);
	   
	   return pDao.buscar(id,password);
    	
    }
   
   @RequestMapping(value="/{login}",method=RequestMethod.GET)
   public Profesor login(@PathVariable("login") String login) throws Exception {
	   ProfesorDao pDao = new JPAProfesorDao();
	   Integer id = pDao.buscarId(login);	
	   return pDao.nuevaPass(id);
   	
   }
   
   @RequestMapping(value="/listar",method=RequestMethod.GET)
   public List<Profesor> listar() throws Exception {
	   ProfesorDao pDao = new JPAProfesorDao();
	   
	   return pDao.listar();
   	
   }
    
    @RequestMapping(method=RequestMethod.POST)
    public Estado registro(@RequestBody Profesor p) throws Exception {
    	
    	ProfesorDao pDao = new JPAProfesorDao();
    	
    	return new Estado(pDao.guardar(p));
    	
    }
    
    @PostMapping ("/modificar")
    public Estado modificar(@RequestBody Profesor p) throws Exception {
    	ProfesorDao pDao = new JPAProfesorDao();
    	
    	return new Estado(pDao.modificar(p));
    	
    }
  
    @ApiOperation(value = "Eliminar un usuario por su email de login")
    @RequestMapping(value="/{login}",method=RequestMethod.DELETE)
    public Estado eliminarID(@PathVariable("login") String login) throws Exception {
    	ProfesorDao pDao = new JPAProfesorDao();
    	Integer id = pDao.buscarId(login);
    	return new Estado(pDao.eliminar(id));
    	
    }
    
    
    @ApiOperation(value = "Eliminar una lista de usuarios por su identificador")
    @RequestMapping(method=RequestMethod.DELETE)
    public Estado eliminarLista(@RequestBody List<Integer> ids) throws Exception {
    	ProfesorDao pDao = new JPAProfesorDao();
    	
    	return new Estado(pDao.eliminarLista(ids));
    	
    }
    
    
    /*
    @RequestMapping(value="/confPartidas" ,method=RequestMethod.PUT)
    public ConfPartida insertar(@RequestParam(value="login") String login,
    						@RequestParam(value="id_juego") int idJuego,
    						@RequestParam(value="titulo") String titulo,
    						@RequestParam(value="fichero") String fPreguntas,
    						@RequestParam(value="num_grupos") int numGrupos,
    						@RequestParam(value="aleatorio") boolean aleatorio,
    						@RequestParam(value="act_tiempo") boolean activarTiempo,
    						@RequestParam(value="tiempo") int tiempo,
    						@RequestParam(value="porcentaje_OK") float porcentajeOK,
    						@RequestParam(value="velocidad") int velocidad,
    						@RequestParam(value="parametrosExtra") String parametrosExtra) throws Exception {
		
		ConfPartida cp = new ConfPartida(login, idJuego, titulo, fPreguntas,
			numGrupos, aleatorio, activarTiempo,tiempo, porcentajeOK, velocidad,parametrosExtra);
		
		ConfPartidasDAO dao = new JPAConfPartidasDAO();
		
		dao.putCpartida(cp);
		
        return cp;
    }
    
    @RequestMapping(value="/confPartidas" ,method=RequestMethod.GET)
    public ConfPartida obtener(@RequestParam(value="id_partida") int id) throws Exception {
    	
    	ConfPartidasDAO dao = new JPAConfPartidasDAO();
    	ConfPartida cp = dao.getCpartida(id);
    	
    	return cp;
    }
    */
}