package confPartidas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;




public class JPAConfPartidasDAO implements ConfPartidasDAO {

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction tx = null;
	
	public JPAConfPartidasDAO() {
		
		this.emf = Persistence.createEntityManagerFactory("postgresql_transaction");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	
	
	}

	@Override
	public ConfPartida buscar(int id) {
		
		ConfPartida cp = em.find(ConfPartida.class, id);
		
		return cp;
	}

	@Override
	public boolean guardar(ConfPartida cp) {
		boolean done = true;
		
		System.out.println("Estamos en guardar conf partida");
		
		if (cp.getId_configuracion() == -1) {
			String jpql = "select max(cp.id_configuracion) from ConfPartida cp ";
			Query query = em.createQuery(jpql);
			List<Integer> lids = query.getResultList();
			int lastId = lids.get(lids.size()-1);
			cp.setId_configuracion(lastId+1);
			
			tx.begin();
			try {
				em.persist(cp);
				tx.commit();
		
			}catch(Exception e) {
				done = false;
				tx.rollback();
			}
			
		}else {
			ConfPartida configuracion = em.find(ConfPartida.class, cp.getId_configuracion());
			
			if(configuracion == null) {
					System.out.println("No se ha encontrado la configuracion dada");
					tx.begin();
					try {
						em.persist(cp);
						tx.commit();
				
				}catch(Exception e) {
					System.out.println("Ha saltado una excepcion al añadir: "+ e);
					done = false;
					tx.rollback();
				}
	
			}else {
				System.out.println("La configuracion ya existe --> devuelvo false");
				done = false;
			}
			
		}
		return done;
	}

	@Override
	public boolean modificar(ConfPartida cp) {

		boolean done = true;
		
		
		ConfPartida configuracion = em.find(ConfPartida.class, cp.getId_configuracion());
		
		if(configuracion != null) {
			cp.setFecha_creacion(configuracion.getFecha_creacion());
			configuracion.setAsignatura(cp.getAsignatura()); 
			configuracion.setCurso(cp.getCurso());
			configuracion.setEquipos(cp.getEquipos());
			configuracion.setEtapa(cp.getEtapa());
			configuracion.setId_fpreguntas(cp.getId_fpreguntas());
			configuracion.setId_juego(cp.getId_juego());
			configuracion.setParametros(cp.getParametros());
			configuracion.setPorcentaje_correccion(cp.getPorcentaje_correccion());
			configuracion.setTema(cp.getTema());
			configuracion.setTitulo(cp.getTitulo());	
		
		}else {
			done = false;
		}
		
		return done;
	}

	@Override
	public List<ConfPartida> listar(Integer id_usuario) {

		String jpql = "select cp from ConfPartida cp where cp.id_profesor=?1";
		Query query = em.createQuery(jpql);
		query.setParameter(1, id_usuario);
		List<ConfPartida> resultados = (List<ConfPartida>) query.getResultList();
		
		return resultados;
	}

	@Override
	public boolean eliminar(int id) {

		boolean done = true;
		ConfPartida cp = em.find(ConfPartida.class, id);
		
		try {
			if (cp != null) {
				tx.begin();
				em.remove(cp);
				//String jpql = "delete from configuracion_partidas where id_configuracion="+id;
				//Query query = em.createNativeQuery(jpql);
				//query.executeUpdate();
				
				tx.commit();
			}else {
				
				done = false;
			}
			
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
				ConfPartida cp = em.find(ConfPartida.class,id);
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
