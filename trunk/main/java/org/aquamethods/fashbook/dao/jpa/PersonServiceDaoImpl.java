package org.aquamethods.fashbook.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.aquamethods.fashbook.domain.Person;




@Repository("iPersonService")
@Transactional(readOnly = true)
public class PersonServiceDaoImpl implements IPersonServiceDao {

	private EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public Person getById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Person.class, id);
	}

	public Person getByName(String name){
		Query query = entityManager.createNamedQuery("Person.findByName");
		query.setParameter("name", name);
		Person person = (Person)query.getSingleResult();
		return person;
	}
	
	public List<Person> getAll() {
		Query query = entityManager.createNamedQuery("Person.findAll");
		List<Person> persons = null;
		persons = query.getResultList();
		return persons;
	}

	@Override
	public boolean savePerson(Person person) {
		entityManager.persist(person);
		entityManager.flush();
		return true;
	}

	@Override
	public boolean updatePerson(Person person) {
		entityManager.merge(person);
		entityManager.flush();
		return true;
	}

	@Override
	public boolean delete(Person person) {
		person = entityManager.getReference(Person.class, person.getId());
		if (person == null)
			return false;
		entityManager.remove(person);
		entityManager.flush();
		return true;
	}
	
}
