package business.repository;

import javax.persistence.EntityManager;

import utils.JPAUtil;


public class GenericRepository {
	
	protected EntityManager entityManager;
	
	public <T> T merge(final Object object){
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
		Object objectMerge = (T) entityManager.merge(object);
		entityManager.getTransaction().commit();
		entityManager.close();
		return (T) objectMerge;
	}
	
	public <T> void remove(final Class<T> type, final long id){
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
	    Object object = (T) entityManager.find(type, id);
		entityManager.remove(object);
		entityManager.getTransaction().commit();
		entityManager.close();
	}
	
	public <T> T find(final Class<T> type, final long id){
		entityManager = new JPAUtil().getEntityManager();
		entityManager.getTransaction().begin();
	    Object object = (T) entityManager.find(type, id);
		entityManager.getTransaction().commit();
		entityManager.close();
	    return (T) object;
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
