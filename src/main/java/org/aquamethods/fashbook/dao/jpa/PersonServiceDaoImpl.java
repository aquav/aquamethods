package org.aquamethods.fashbook.dao.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import org.aquamethods.fashbook.domain.Event;
import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.helper.QueryHelper;

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
	
	public Person getByEmail(String email){
		Query query = entityManager.createNamedQuery("Person.findByEmail");
		query.setParameter("email", email);
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
	public boolean deleteOutfit(Outfit outfit) {
		if (outfit == null){
			return false;
		}
		entityManager.remove(outfit);
		entityManager.flush();
		return true;
	}
	
	/**
	 * In future this method can be used to update outfit also
	 */
	@Override
	public boolean archiveOutfit(Outfit outfit){
		if (outfit == null){
			return false;
		}
		entityManager.merge(outfit);
		entityManager.flush();
		return true;
	}
	
	@Override
	public Tag saveTag(Tag tag) {
		entityManager.merge(tag);
		entityManager.flush();
		return tag;
	}
	
/*	public List<Integer> getTagPerson(List<String> tagList, int personId){
		Query query = entityManager.createQuery("select t.outfit_id from TagPerson t where t.person_id =:personId and " +
				" t.tag IN (:tagList)");
		query.setParameter("personId", personId);
		query.setParameter("tagList", tagList);
		List<Integer> outfitIdList = query.getResultList();
		return outfitIdList;
	}*/
	
	/**
	 * 
	 */
	@Override
	public List<Integer> getTagPerson(List<String> tagList, int personId, boolean matchWordFlag){
		String queryString;
		Query query=null;
		if (matchWordFlag){
			query = entityManager.createQuery("select t.outfit_id from TagPerson t where t.person_id =:personId and " +
					" t.tag IN (:tagList)");
			query.setParameter("personId", personId);
			query.setParameter("tagList", tagList);
		} else{
			queryString = QueryHelper.getQueryStringForSearch(tagList);
			query = entityManager.createQuery("select t.outfit_id from TagPerson t where t.person_id =:personId and " +
					"( t.tag LIKE " +queryString+")");
			query.setParameter("personId", personId);
		}
		List<Integer> outfitIdList = query.getResultList();
		return outfitIdList;
	}
	
	public List<Outfit> searchOutfit(List<Integer> outfitIdList){
		List<Outfit> result = new ArrayList<Outfit>();
		if (outfitIdList!=null && !outfitIdList.isEmpty()){
			Query query = entityManager.createQuery("select o from Outfit o where o.id IN (:outfitIdList)");
			query.setParameter("outfitIdList", outfitIdList);
			result =  query.getResultList();
		}

		return result;
	}
	
	public Event saveEvent(Event event){
		
		entityManager.persist(event);
		entityManager.flush();
		return event;
	}
	
	public List<Event> loadEventsNoOutfitAssigned(int personId) {
		Query query = entityManager.createQuery("select e from Event e where e.person_id=:personId and e.outfit_id=0");
		query.setParameter("personId",personId);
		//query.setParameter("date",new Date(System.currentTimeMillis()));
		List<Event> eventList = null;
		eventList = query.getResultList();
		return eventList;
	}
	public Event loadEventById(int eventId){
		
		return entityManager.find(Event.class, eventId);
	}
	
	public List<Event> loadEventsForCurrentOutfit(int outfitId){
		Query query = entityManager.createNamedQuery("Event.findEventForOutfit");
		query.setParameter("outfitId",outfitId);
		List<Event> events = null;
		events = query.getResultList();
		return events;
	}
	
	public List<Event> loadAllEventsForPerson(int personId){
		Query query = entityManager.createNamedQuery("Event.findAllEventsForPerson");
		query.setParameter("personId",personId);
		List<Event> events = null;
		events = query.getResultList();
		return events;
	}

}
