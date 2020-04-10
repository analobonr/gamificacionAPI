package profesores;



import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import utilidades.CustomerDateAndTimeDeserialize;

@Entity
@Table(name="profesores" )
public class Profesor{
	
	
	private enum rol_t {USER,ADMIN,SUPER};

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	private String password;
	private String nombre;
	private String apellidos;
	
	@Enumerated(value = EnumType.ORDINAL)
	private rol_t rol;
	
	private String pais;
	
	//@JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
	//@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_registro;
	
	//@JsonDeserialize(using= CustomerDateAndTimeDeserialize.class)
	//@Temporal(TemporalType.TIMESTAMP)
	private Date f_ultimo_acceso;
	
	

	public Profesor() {
		
	}
	
	public Profesor(Integer id,String email, String nombre, String apellidos,
			String pais, rol_t rol, Date fecha_registro, Date f_ultimo_acceso) {
		
		this.setId(id);
		this.setEmail(email);
		this.setNombre(nombre);
		this.setApellidos(apellidos);
		this.setPais(pais);
		this.setRol(rol);
		this.setFecha_registro(fecha_registro);
		this.setF_ultimo_acceso(f_ultimo_acceso);
		
	}
	
	
	public String getEmail() {
		return email;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
		
	}
	
	
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public rol_t getRol() {
		return rol;
	}

	public void setRol(rol_t rol) {
		this.rol = rol;
	}

	public Date getF_ultimo_acceso() {
		return f_ultimo_acceso;
	}

	public void setF_ultimo_acceso(Date f_ultimo_acceso) {
		this.f_ultimo_acceso = f_ultimo_acceso;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
}