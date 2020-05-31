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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import partidas.JPAPartidasDAO;
import partidas.Partida;
import partidas.PartidasDAO;
import profesores.JPAProfesorDao;
import profesores.ProfesorDao;
import utilidades.CustomInternalServerErrorException;



@RestController
@RequestMapping("/partidas")
@Api(tags = "Partidas")
public class PartidasController {
	
	private PartidasDAO partidaDao = new JPAPartidasDAO();
	
	
	@ApiOperation(value = "Obtener una partida a partir de su identificador")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
    public Partida get(@ApiParam(value="Identificador de la partida") @PathVariable("id") int id) throws CustomInternalServerErrorException {
	   
		Partida partida = null;

		try {
			partida = partidaDao.buscar(id);

			if (partida == null) {
				throw new CustomInternalServerErrorException("No existe la partida");
			}
		} catch (Exception exception) {
			throw new CustomInternalServerErrorException("Error interno: " + exception.getMessage());
		}

		return partida;
    	
    }
	
	@ApiOperation(value = "Obtener la lista de partidas de un usuario")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value="listar/{usuario}")
	   public List<Partida> listar(@ApiParam(value="Email del usuario") @PathVariable("usuario") String user) throws CustomInternalServerErrorException {
		   
			ProfesorDao pDao = new JPAProfesorDao();
			
			List<Partida> configuracion = new ArrayList<Partida>();
			try {
				configuracion = partidaDao.listar(pDao.buscarId(user));

			} catch (Exception e) {
				throw new CustomInternalServerErrorException("Error interno");
			}
		   
		   return configuracion;
	   	
	   }
	
	
	@ApiOperation(value = "Insertar una nueva partida")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping
    public void registro(@ApiParam(value="Partida") @RequestBody Partida partida) throws CustomInternalServerErrorException {
    	
		
		try {
			//Actualizamos las fechas de inicio y update
			partida.registrarFecha(true);
			
			//Registramos la configuracion de la partida
			boolean error = partidaDao.guardar(partida);
			if(error) {
				throw new CustomInternalServerErrorException("Error al guardar la partida");
			}
		}catch(Exception e) {
			throw new CustomInternalServerErrorException("Error interno");
		}
	}
    
	
	@ApiOperation(value = "Modificar una partida existente")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping
    public void modificar(@ApiParam(value="Partida") @RequestBody Partida j) throws CustomInternalServerErrorException {
		
    	boolean error = partidaDao.modificar(j);
    	
    	if (error) {
			throw new CustomInternalServerErrorException("Error al modificar la partida");
		}
    	
    }
	
	
	@ApiOperation(value = "Eliminar una partida existente")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public void eliminarID(@ApiParam(value="Identificador de la partida") @PathVariable("id") int id) throws CustomInternalServerErrorException {
		
    	boolean error = partidaDao.eliminar(id);
		
    	if (error) {
			throw new CustomInternalServerErrorException("Error al eliminar la partida");
		}
   	
    }
	
	@ApiOperation(value = "Eliminar un listado de partidas existentes")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping
    public void  eliminarLista(@ApiParam(value="Lista de identificadores") @RequestBody List<Integer> ids) throws CustomInternalServerErrorException {
		
		boolean error = partidaDao.eliminarLista(ids);
    	
		if (error) {
			throw new CustomInternalServerErrorException("Error al eliminar el listado partidas");
		}
    	
    }

}
