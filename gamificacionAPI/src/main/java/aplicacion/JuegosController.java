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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import juegos.JPAJuegosDao;
import juegos.Juego;
import juegos.JuegosDao;
import utilidades.CustomInternalServerErrorException;

@RestController
@RequestMapping("/juegos")
@Api(tags = "Juegos")
public class JuegosController {

	

	@ApiOperation(value = "Obtener un juego a partir de su identificador")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Juego get(@ApiParam(value = "Identificador del juego") @PathVariable("id") int id)
			throws CustomInternalServerErrorException {

		JuegosDao jDao = new JPAJuegosDao();
		Juego j = jDao.buscar(id);

		if (j == null) {
			throw new CustomInternalServerErrorException("No existe el juego");
		}

		return j;

	}

	@ApiOperation(value = "Obtener la lista de juegos existentes")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping
	public List<Juego> listar() throws CustomInternalServerErrorException {

		JuegosDao jDao = new JPAJuegosDao();
		List<Juego> juegos = jDao.listar();

		return juegos;

	}

	@ApiOperation(value = "Insertar un nuevo juego")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping
	public void registro(@ApiParam(value = "Datos del juego") @RequestBody Juego j)
			throws CustomInternalServerErrorException {

		JuegosDao jDao = new JPAJuegosDao();
		boolean error = jDao.guardar(j);

		if (error) {
			throw new CustomInternalServerErrorException("Error al insertar el juego");
		}

	}

	@ApiOperation(value = "Modificar un juego existente")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping
	public void modificar(@ApiParam(value = "Datos del juego") @RequestBody Juego j)
			throws CustomInternalServerErrorException {
		
		JuegosDao jDao = new JPAJuegosDao();
		boolean error = jDao.modificar(j);

		if (error) {
			throw new CustomInternalServerErrorException("Error al modificar el juego");
		}

	}

	@ApiOperation(value = "Eliminar un juego")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void eliminarID(@ApiParam(value = "Identificador del juego") @PathVariable("id") int id)
			throws CustomInternalServerErrorException {
		
		JuegosDao jDao = new JPAJuegosDao();
		boolean error = jDao.eliminar(id);

		if (error) {
			throw new CustomInternalServerErrorException("Error al eliminar el juego");
		}

	}

	@ApiOperation(value = "Eliminar un listado de juegos")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping
	public void eliminarLista(@ApiParam(value = "Lista de identificadores de juego") @RequestBody List<Integer> ids)
			throws CustomInternalServerErrorException {

		JuegosDao jDao = new JPAJuegosDao();
		boolean error = jDao.eliminarLista(ids);

		if (error) {
			throw new CustomInternalServerErrorException("Error al eliminar la lista de juegos");
		}


	}

}
