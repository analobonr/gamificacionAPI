package partidas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;




public class JPAPartidasDAO implements PartidasDAO {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction tx = null;
	
	public JPAPartidasDAO() {
		
		this.emf = Persistence.createEntityManagerFactory("postgresql_transaction");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	
	
	}

	@Override
	public Partida buscar(int id) {
		
		Partida cp = em.find(Partida.class, id);
		
		return cp;
	}

	@Override
	public boolean guardar(Partida cp) {
		boolean done = true;
		
		Partida configuracion = em.find(Partida.class, cp.getId_partida());
		
		
		if(configuracion == null) {
				tx.begin();
				try {
					em.persist(cp);
					tx.commit();
			
			}catch(Exception e) {
				done = false;
				tx.rollback();
			}

		}else {
			done = false;
		}
		
		return done;
	}

	@Override
	public boolean modificar(Partida cp) {

		boolean done = true;
		
		
		Partida configuracion = em.find(Partida.class, cp.getId_partida());
		
		if(configuracion != null) {
			
			try {
				tx.begin();
				
				em.remove(configuracion);
				
				tx.commit();
				tx.begin();
				em.persist(cp);
				tx.commit();
		
			}catch(Exception e) {
				done = false;
				tx.rollback();
			}
			
		
		}else {
			done = false;
		}
		
		return done;
	}

	@Override
	public List<Partida> listar(Integer id_profesor) {

		String jpql = "select p from Partida p where p.id_profesor=?1 and p.finalizada=false";
		Query query = em.createQuery(jpql);
		query.setParameter(1, id_profesor);
		List<Partida> resultados = query.getResultList();
		
		return resultados;
	}

	@Override
	public boolean eliminar(int id) {

		boolean done = true;
		tx.begin();
		try {
			Partida cp = em.find(Partida.class,id);
			em.remove(cp);
			tx.commit();
	
		}catch(Exception e) {
			done = false;
			tx.rollback();
		}
		
		return done;
	}

	@Override
	public boolean eliminarLista(List<Integer> ids) {
		boolean done = true;
		for (int id : ids){
			
			tx.begin();
			try {
				Partida cp = em.find(Partida.class,id);
				em.remove(cp);
				tx.commit();
		
			}catch(Exception e) {
				done = false;
				tx.rollback();
			}
			
			
		}
		return done;
	}
	
	

}
