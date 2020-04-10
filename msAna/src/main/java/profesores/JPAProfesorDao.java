package profesores;


import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class JPAProfesorDao implements ProfesorDao{

	private EntityManagerFactory emf = null;
	private EntityManager em = null;
	private EntityTransaction tx = null;
	
	
	
	
	public JPAProfesorDao() {
		
		this.emf = Persistence.createEntityManagerFactory("postgresql_transaction");
		this.em = emf.createEntityManager();
		this.tx = em.getTransaction();
	
	
	}

	@Override
	public Profesor buscar(Integer id, String pass) {
		
		Profesor p = em.find(Profesor.class, id);
		
		if (p != null && (!pass.equals(p.getPassword()))) {
			p = null;
		}else {
	
			p.setF_ultimo_acceso(new Date());
			
			tx.begin();
			try {
				em.persist(p);
				tx.commit();
		
			}catch(Exception e) {
				tx.rollback();
			}
			
			
			String passOculta = "";
			for (int i=1; i <= p.getPassword().length();i++) {
				passOculta = passOculta + "*";	
			}
			
			p.setPassword(passOculta);
		
		}
		
		return p;
		
	}
	
	@Override
	public Profesor nuevaPass(Integer id) {
		
		Profesor p = em.find(Profesor.class, id);
		
		//Si existe el usuario
		if (p != null) {
			//Autogeneramos una nueva password	                
	        String NUMEROS = "0123456789";
	    	String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	    	String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	    	String ESPECIALES = "ñÑ@#=";
	   
	    	String newPass = "";
	    	String key = NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES;
	    	 
			for (int i = 0; i < 8; i++) {
				newPass+=(key.charAt((int)(Math.random() * key.length())));
			}
			
			//Update de la nueva pass
			tx.begin();
			p.setPassword(newPass);
			tx.commit();
		}
		
		return p;
		
	}

	@Override
	public boolean guardar(Profesor p) {
		boolean done = true;
		
		if (p.getId() == -1) {
			Integer id = this.buscarId(p.getEmail());
			
			if(id == null) {
					p.setId(null);
					tx.begin();
					try {
						em.persist(p);
						tx.commit();
				
				}catch(Exception e) {
					done = false;
					tx.rollback();
				}
	
			}else {
				done = false;
			}
		}else {
			done = false;
		}
		
		return done;
	}

	@Override
	public boolean modificar(Profesor p) {
		boolean done = true;
		
		
		Profesor profesor = em.find(Profesor.class, p.getId());
		
		if(profesor != null) {
			tx.begin();
			profesor.setEmail(p.getEmail());
			profesor.setNombre(p.getNombre());
			profesor.setApellidos(p.getApellidos());
			profesor.setPais(p.getPais());
			profesor.setRol(p.getRol());
			tx.commit();
		
		}else {
			done = false;
		}
		
		return done;
	}

	@Override
	public List<Profesor> listar() {
		String jpql = "select p from Profesor p";
		Query query = em.createQuery(jpql);
		List<Profesor> resultados = query.getResultList();
		
		for (Profesor p : resultados) {
			String passOculta = "";
			for (int i=1; i <= p.getPassword().length();i++) {
				passOculta = passOculta + "*";	
			}
			
			int indice = resultados.indexOf(p);
			p.setPassword(passOculta);
			resultados.set(indice, p);
			
		}
		
		return resultados;
	}

	@Override
	public boolean eliminar(Integer id) {
		boolean done = true;
		tx.begin();
		try {
			Profesor p = em.find(Profesor.class,id);
			em.remove(p);
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
		for (Integer id : ids){
			
			tx.begin();
			try {
				Profesor p = em.find(Profesor.class,id);
				em.remove(p);
				tx.commit();
		
			}catch(Exception e) {
				done = false;
				tx.rollback();
			}
			
			
		}
		return done;
	}

	@Override
	public Integer buscarId(String mail) {
		String jpql = "select p.id from Profesor p where p.email=:email";
		Query query = em.createQuery(jpql);
		query.setParameter("email", mail);
		Integer id = null;
		try {
			id = (Integer) query.getSingleResult();
		}catch(Exception e) {
			System.err.println(e);
		}
		return id;
	}


}
