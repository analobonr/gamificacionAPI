package juegos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="juegos")
public class Juego {
	
	public enum estilosJuego_t {CLASIC,TV,SPORT,AJDA};
	
	@Id
	private int id_juego;
	private String nombre;
	private String descripcion;
	private String etapa;
	private int tipoRespuesta;
	@Enumerated(value = EnumType.ORDINAL)
	private estilosJuego_t estiloJuego;
	private boolean pregIlimitadas;
	private boolean cargaCompleta;
	private int tipoFich;
	private int pregMin;
	private int numFichMin;
	private int numFichMax;
	@Column(name="JSONrespuestas")
	private String jsonRespuestas;
	private String jsonParametrosConf;
	private String jsonEquipos;
	private String jsonOtrosDatos;
	private String nombreZip;
	private String rutaZip;
	
	public String getNombre() {
		return nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	
	
	public String getEtapa() {
		return etapa;
	}
	
	public void setEtapa(String etapa) {
		this.etapa = etapa;
	}
	
	public int getTipoFich() {
		return tipoFich;
	}
	public int getPregMin() {
		return pregMin;
	}
	public boolean isPregIlimitadas() {
		return pregIlimitadas;
	}
	public int getNumFichMin() {
		return numFichMin;
	}
	public int getNumFichMax() {
		return numFichMax;
	}
	public boolean isCargaCompleta() {
		return cargaCompleta;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setTipoFich(int tipoFich) {
		this.tipoFich = tipoFich;
	}
	public void setPregMin(int pregMin) {
		this.pregMin = pregMin;
	}
	public void setPregIlimitadas(boolean pregIlimitadas) {
		this.pregIlimitadas = pregIlimitadas;
	}
	public void setNumFichMin(int numFichMin) {
		this.numFichMin = numFichMin;
	}
	public void setNumFichMax(int numFichMax) {
		this.numFichMax = numFichMax;
	}
	public void setCargaCompleta(boolean cargaCompleta) {
		this.cargaCompleta = cargaCompleta;
	}
	
	public int getTipoRespuesta() {
		return tipoRespuesta;
	}
	public void setTipoRespuesta(int tipoRespuesta) {
		this.tipoRespuesta = tipoRespuesta;
	}
	public int getId_juego() {
		return id_juego;
	}
	public void setId_juego(int id_juego) {
		this.id_juego = id_juego;
	}
	public estilosJuego_t getEstiloJuego() {
		return estiloJuego;
	}
	public void setEstiloJuego(estilosJuego_t estiloJuego) {
		this.estiloJuego = estiloJuego;
	}
	
	public String getNombreZip() {
		return nombreZip;
	}
	public void setNombreZip(String nombreZip) {
		this.nombreZip = nombreZip;
	}
	public String getRutaZip() {
		return rutaZip;
	}
	public void setRutaZip(String rutaZip) {
		this.rutaZip = rutaZip;
	}
	
	public String getJsonRespuestas() {
		return jsonRespuestas;
	}
	public void setRespuestas(String respuestas) {
		this.jsonRespuestas = respuestas;
	}
	public String getJsonParametrosConf() {
		return jsonParametrosConf;
	}
	public void setJsonParametrosConf(String jsonParametrosConf) {
		this.jsonParametrosConf = jsonParametrosConf;
	}
	public String getJsonEquipos() {
		return jsonEquipos;
	}
	public void setJsonEquipos(String jsonEquipos) {
		this.jsonEquipos = jsonEquipos;
	}
	public String getJsonOtrosDatos() {
		return jsonOtrosDatos;
	}
	public void setJsonOtrosDatos(String jsonOtrosDatos) {
		this.jsonOtrosDatos = jsonOtrosDatos;
	}
	public void setJsonRespuestas(String jsonRespuestas) {
		this.jsonRespuestas = jsonRespuestas;
	}
	
	

}
