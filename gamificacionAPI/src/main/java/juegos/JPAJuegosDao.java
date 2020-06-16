package juegos;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class JPAJuegosDao implements JuegosDao{

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction tx = null;
	
	
	
	
	public JPAJuegosDao() {
		
		this.emf = Persistence.createEntityManagerFactory("postgresql_transaction");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	
	
	}

	@Override
	public Juego buscar(int id) {
		
		Juego j = em.find(Juego.class, id);
		
		return j;
		
	}

	@Override
	public boolean guardar(Juego j) {
		boolean error = false;
		
		String jpql = "select max(j.id_juego) from Juego j ";
		Query query = em.createQuery(jpql);
		List<Integer> lids = query.getResultList();
		int lastId = lids.get(lids.size()-1);
		j.setId_juego(lastId+1);
		
		
		tx.begin();
		try {
			em.persist(j);
			tx.commit();
	
		}catch(Exception e) {
			error = true;
			tx.rollback();
		}

		return error;
	}

	@Override
	public boolean modificar(Juego j) {
		boolean error = false;
		
		
		tx.begin();
		Juego juego = em.find(Juego.class, j.getId_juego());
		
		if(juego != null) {
			
			
			juego.setCargaCompleta(j.isCargaCompleta());
			juego.setDescripcion(j.getDescripcion());
			juego.setJsonEquipos(j.getJsonEquipos());
			juego.setEtapa(j.getEtapa());
			juego.setNombre(j.getNombre());
			juego.setNumFichMax(j.getNumFichMax());
			juego.setNumFichMin(j.getNumFichMin());
			juego.setJsonOtrosDatos(j.getJsonOtrosDatos());
			juego.setJsonParametrosConf(j.getJsonParametrosConf());
			juego.setPregIlimitadas(j.isPregIlimitadas());
			juego.setPregMin(j.getPregMin());
			juego.setJsonRespuestas(j.getJsonRespuestas());
			juego.setNombreZip(j.getNombreZip());
			juego.setRutaZip(j.getRutaZip());
			juego.setTipoFich(j.getTipoFich());
			juego.setTipoRespuesta(j.getTipoRespuesta());
		
			
		}else {
			error = true;
		}
		
		tx.commit();
		
		return error;
	}

	@Override
	public List<Juego> listar() {
		String jpql = "select j from Juego j";
		Query query = em.createQuery(jpql);
		List<Juego> resultados = query.getResultList();
		
		return resultados;
	}

	@Override
	public boolean eliminar(int id) {
		boolean error = false;
		Juego j = em.find(Juego.class,id);
		try {
			if (j != null) {
				tx.begin();
				em.remove(j);
				tx.commit();
			}else {
				
				error = true;
			}
	
		}catch(Exception e) {
			error = true;
			tx.rollback();
		}
		
		return error;
	}

	@Override
	public boolean eliminarLista(List<Integer> ids) {
		boolean error = false;
		for (int id : ids){
			
			tx.begin();
			try {
				Juego j = em.find(Juego.class,id);
				em.remove(j);
				tx.commit();
		
			}catch(Exception e) {
				error = true;
				tx.rollback();
			}
			
			
		}
		return error;
	}


}
