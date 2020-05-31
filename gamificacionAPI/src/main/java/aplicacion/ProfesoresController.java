package aplicacion;

import java.util.List;

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
import profesores.JPAProfesorDao;
import profesores.Profesor;
import profesores.ProfesorDao;
import utilidades.CustomInternalServerErrorException;

@RestController
@RequestMapping("/usuarios")
@Api(tags = "Usuarios")
public class ProfesoresController {

	ProfesorDao pDao = new JPAProfesorDao();

	@ApiOperation(value = "Obtener el usuario a partir de su correo electronico y contraseña",
					notes="Devuelve la información del usuario si el usuario y contraseña son correctos")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{login}/{password}", method = RequestMethod.GET)
	public Profesor login(@ApiParam(value = "Correo electrónico") @PathVariable("login") String login,
			@ApiParam(value = "Contraseña") @PathVariable("password") String password)
			throws CustomInternalServerErrorException {

		System.out.println(login);
		System.out.println(password);
		Profesor p = null;
		Integer id = pDao.buscarId(login);

		if (id != null) {
			p = pDao.buscar(id, password);

			if (p == null) {
				throw new CustomInternalServerErrorException("Contraseña incorrecta");
			}
		} else {
			throw new CustomInternalServerErrorException("No existe el usuario");
		}

		return p;

	}

	@ApiOperation(value = "Generar una nueva contraseña para el usuario")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{login}", method = RequestMethod.GET)
	public Profesor getNuevaPass(@ApiParam(value = "Correo electrónico") @PathVariable("login") String login)
			throws CustomInternalServerErrorException {

		Profesor p = null;
		Integer id = pDao.buscarId(login);

		if (id != null) {
			p = pDao.nuevaPass(id);

			if (p == null) {
				throw new CustomInternalServerErrorException("Error al generar la contraseña");
			}
		} else {
			throw new CustomInternalServerErrorException("No existe el usuario");
		}

		return p;

	}

	@ApiOperation(value = "Obtener la lista de usuarios")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public List<Profesor> listar() throws CustomInternalServerErrorException {

		return pDao.listar();

	}

	
	@ApiOperation(value = "Insertar un nuevo usuario")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping
	public void registro(@ApiParam(value="Datos del usuario") @RequestBody Profesor p) throws CustomInternalServerErrorException {

		boolean error = pDao.guardar(p);
		
		if(error) {
			throw new CustomInternalServerErrorException("Error al insertar el usuario");
		}

	}

	@ApiOperation(value = "Modificar un usuario existente")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping
	public void modificar(@ApiParam(value="Datos del usuario") @RequestBody Profesor p) throws CustomInternalServerErrorException {
		
		boolean error = pDao.modificar(p);
		
		if(error) {
			throw new CustomInternalServerErrorException("Error al modificar el usuario");
		}
	}

	@ApiOperation(value = "Eliminar un usuario por su correo electronico")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
	public void eliminarID(@ApiParam(value="Correo electrónico del usuario") @PathVariable("login") String login) throws CustomInternalServerErrorException {
		
		Integer id = pDao.buscarId(login);
		
		if (id != null) {
			boolean error = pDao.eliminar(id);
			
			if(error) {
				throw new CustomInternalServerErrorException("Error al eliminar el usuario");
			}
		}
		
	}

	@ApiOperation(value = "Eliminar una lista de usuarios por su identificador")
	@ApiResponses(value = { @ApiResponse(code = 500, message = "Internal Server Error") })
	@RequestMapping(method = RequestMethod.DELETE)
	public void eliminarLista(@ApiParam(value="Lista de identificadores de usuario") @RequestBody List<Integer> ids) throws CustomInternalServerErrorException {
		
		boolean error = pDao.eliminarLista(ids);
		
		if(error) {
			throw new CustomInternalServerErrorException("Error al eliminar la lista de usuarios");
		}

	}
}