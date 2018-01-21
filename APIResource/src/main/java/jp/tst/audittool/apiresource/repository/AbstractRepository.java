package jp.tst.audittool.apiresource.repository;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

public abstract class AbstractRepository<PK extends Serializable, T> {

	private final Class<T> persistentClass;

	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * This method is used to get current session connect to database
	 * 
	 * @return
	 * @return Session
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * This method is used to get entity by ID
	 * 
	 * @param key
	 * @return
	 * @return T
	 */
	@SuppressWarnings("unchecked")
	public T getByKey(PK key) {
		return (T) getSession().get(persistentClass, key);
	}

	/**
	 * This method is used to save entity
	 * 
	 * @param entity
	 * @return void
	 */
	public void persist(T entity) {
		getSession().persist(entity);
	}

	/**
	 * This method is used to update entity
	 * 
	 * @param entity
	 * @return void
	 */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * This method is used to delete entity
	 * 
	 * @param entity
	 * @return void
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}

	/**
	 * This method is used to create criteria
	 * 
	 * @return
	 * @return Criteria
	 */
	protected Criteria createEntityCriteria() {
		return getSession().createCriteria(persistentClass);
	}

	/**
	 * This method is used to save if entity not exist, update if entity exist
	 * 
	 * @param entity
	 * @return void
	 */
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

}
