package partidas;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="partidas")
public class Partida {
	
	@Id
	private int id_partida;
	private int id_profesor;
	private int id_juego;
	private int id_fpreguntas;
	private String titulo;
	private int porcentaje_correccion;
	private String parametros;
	private String equipos;
	private boolean finalizada;
	
	
	public Partida() {
		
	}


	public int getId_partida() {
		return id_partida;
	}


	public int getEmail() {
		return id_profesor;
	}


	public int getId_juego() {
		return id_juego;
	}


	public int getId_fpreguntas() {
		return id_fpreguntas;
	}


	public String getTitulo() {
		return titulo;
	}


	public int getPorcentaje_correccion() {
		return porcentaje_correccion;
	}


	public String getParametros() {
		return parametros;
	}


	public String getEquipos() {
		return equipos;
	}


	public void setId_partida(int id_partida) {
		this.id_partida = id_partida;
	}


	public void setEmail(int id_profesor) {
		this.id_profesor = id_profesor;
	}


	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}


	public void setId_fpreguntas(int id_fpreguntas) {
		this.id_fpreguntas = id_fpreguntas;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public void setPorcentaje_correccion(int porcentaje_correccion) {
		this.porcentaje_correccion = porcentaje_correccion;
	}


	public void setParametros(String parametros) {
		this.parametros = parametros;
	}


	public void setEquipos(String equipos) {
		this.equipos = equipos;
	}


	public boolean isFinalizada() {
		return finalizada;
	}


	public void setFinalizada(boolean finalizada) {
		this.finalizada = finalizada;
	}
	
	

}
