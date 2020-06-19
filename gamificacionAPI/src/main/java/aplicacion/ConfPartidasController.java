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
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import profesores.JPAProfesorDao;
import profesores.ProfesorDao;
import utilidades.CustomInternalServerErrorException;

@RestController
@RequestMapping("/confPartidas")
@Api(tags = "Configuración de Partidas")
public class ConfPartidasController {


	@ApiOperation(value = "Obtener una configuración de partida a partir de su identificador")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ConfPartida get(@ApiParam(value="Identificador de la configuación de partida", required=true) @PathVariable("id") int id) throws CustomInternalServerErrorException {
		
		
		ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
		ConfPartida confp = null;

		try {
			confp = cpDao.buscar(id);

			if (confp == null) {
				throw new CustomInternalServerErrorException("No existe la configuración de partida");
			}
		} catch (Exception exception) {
			throw new CustomInternalServerErrorException("Error interno");
		}

		return confp;

	}


	@ApiOperation(value = "Obtener la lista de configuraciones de partidas de un usuario")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping(value = "/listar/{usuario}")
	public List<ConfPartida> getLista(@ApiParam(value="Email del usuario", required=true) @PathVariable("usuario") String usuario) throws CustomInternalServerErrorException {

		ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
		ProfesorDao pDao = new JPAProfesorDao();

		List<ConfPartida> configuracion = new ArrayList<ConfPartida>();
		try {
			configuracion = cpDao.listar(pDao.buscarId(usuario));

		} catch (Exception e) {
			throw new CustomInternalServerErrorException("Error interno");
		}

		return configuracion;

	}

	@ApiOperation(value = "Insertar una nueva configuración de partida")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping
	public void registro(@ApiParam(value="Configuración de la partida", required=true) @RequestBody ConfPartida cp) throws CustomInternalServerErrorException {

		ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
		try {
			//Actualizamos la fecha de registro
			cp.registrarFecha();
			
			//Registramos la configuracion de la partida
			boolean error = cpDao.guardar(cp);
			if(error) {
				throw new CustomInternalServerErrorException("Error al guardar la configuración de partida");
			}
		}catch(Exception e) {
			throw new CustomInternalServerErrorException("Error interno");
		}
	}

	
	@ApiOperation(value = "Modificar una configuración de partida existente")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping
	public void modificar(@ApiParam(value="Configuración de la partida", required=true) @RequestBody ConfPartida cp) throws CustomInternalServerErrorException {

		ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
		boolean error = cpDao.modificar(cp);
		
		if (error) {
			throw new CustomInternalServerErrorException("Error al modificar la configuración de partida");
		}
		
	}

	
	@ApiOperation(value = "Eliminar una configuración de partida existente")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void eliminarID(@ApiParam(value="Identificador de la configuración de partida") @PathVariable("id") int id) throws CustomInternalServerErrorException {

		ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
		boolean error = cpDao.eliminar(id);
		
		if (error) {
			throw new CustomInternalServerErrorException("Error al eliminar la configuración de partida");
		}

	}

	@ApiOperation(value = "Eliminar un listado de configuraciones de partidas existentes")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping
	public void eliminarLista(@ApiParam(value="Lista de identificadores") @RequestBody List<Integer> ids) throws CustomInternalServerErrorException {
		
		ConfPartidasDAO cpDao = new JPAConfPartidasDAO();
		boolean error = cpDao.eliminarLista(ids);
		
		if (error) {
			throw new CustomInternalServerErrorException("Error al eliminar el listado de configuración de partida");
		}
	}

}
