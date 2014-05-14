package kb.queries;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kb.entities.Idea;

public class NewIdeaQueries {

	private EntityManager em = Persistence.createEntityManagerFactory("Memory_Palace").createEntityManager();
	
	public NewIdeaQueries(){
		
	}

	public void saveIdea(Idea idea) {
		EntityTransaction et = em.getTransaction();
		
		try{
			et.begin();
			em.persist(idea);
			et.commit();
		}catch(Exception e){
			
		}
	}
}
