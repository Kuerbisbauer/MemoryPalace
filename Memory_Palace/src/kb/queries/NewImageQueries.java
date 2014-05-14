package kb.queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kb.entities.Image;

public class NewImageQueries {

	private EntityManager em = Persistence.createEntityManagerFactory("Memory_Palace").createEntityManager();

	
	public void newImage(Image img) {
		EntityTransaction et = em.getTransaction();
		
		try{
			et.begin();
			em.persist(img);
			et.commit();
		}catch(Exception e){
			
		}
	}
}
