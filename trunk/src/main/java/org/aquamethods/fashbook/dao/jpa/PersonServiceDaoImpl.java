package org.aquamethods.fashbook.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;

/**
 * 
 * @author vgupta
 *
 */


@Repository("personServiceDao")
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
	public Person savePerson(Person person) {
		entityManager.persist(person);
		entityManager.flush();
		return person;
	}

	@Override
	public Person updatePerson(Person person) {
		entityManager.merge(person);
		entityManager.flush();
		return person;
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
	
	@Override
	public Outfit loadOutfit(int id) {
		return entityManager.find(Outfit.class, id);
	}
	
	@Override
	public Tag saveTag(Tag tag) {
		entityManager.merge(tag);
		entityManager.flush();
		return tag;
	}
	
	public List<Integer> getTagPerson(List<String> tagList, int personId){
		Query query = entityManager.createNativeQuery("select t.outfit_id from tag_person t where t.person_id :personId" +
				"and t.tag IN (:tagList)");
		query.setParameter("personId", personId);
		query.setParameter("tagList", tagList);
		List<Integer> outfitIdList = query.getResultList();
		return outfitIdList;
	}
	
	public List<Outfit> searchOutfit(List<Integer> outfitIdList, int personId){
		Query query = entityManager.createQuery("select o from Outfit o where o.id IN (:outfitIdList)" +
				"								and o.associatedPerson.id =:personId");
		query.setParameter("outfitIdList", outfitIdList);
		query.setParameter("personId", personId);
		List<Outfit> result =  query.getResultList();
		
		return result;
	}
}
