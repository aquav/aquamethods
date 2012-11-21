package org.aquamethods.fashbook.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aquamethods.fashbook.domain.Event;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.services.IPersonService;
import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("personService")
public class PersonServiceImpl implements IPersonService{

	@Autowired
	IPersonServiceDao personDao;
	private static final Logger logger = LoggerFactory
			.getLogger(PersonServiceImpl.class);
	
	/**
	 * 
	 */
	public Person getById(int id, boolean showArchived){
		Person p = personDao.getById(id);
		Person returnPerson = new Person();
		if (p!=null){
			
			returnPerson.setId(p.getId());
			returnPerson.setFirstName(p.getFirstName());
			returnPerson.setLastName(p.getLastName());
			returnPerson.setAge(p.getAge());
			returnPerson.setEmail(p.getEmail());
			returnPerson.setPassword(p.getPassword());
			
			ArrayList<Outfit> onlyNonArchivedOutfits = new ArrayList<Outfit>();
			ArrayList<Outfit> onlyArchivedOutfits = new ArrayList<Outfit>();
			
			for (Outfit outfit : p.getOutfits()){
				if (outfit.isArchived()==false){
					onlyNonArchivedOutfits.add(outfit);
				} else{
					onlyArchivedOutfits.add(outfit);
				}
			}
			
			if(showArchived){
				returnPerson.getOutfits().addAll(onlyArchivedOutfits);
				logger.debug("showArchived is "+showArchived+", Size of archived outfit ::"+onlyArchivedOutfits.size());
			} else {
				returnPerson.getOutfits().addAll(onlyNonArchivedOutfits);
				logger.debug("showArchived is "+showArchived+", Size of non archived outfit ::"+onlyNonArchivedOutfits.size());
			
			}
		}
		
		return returnPerson;
	}
	
	public Person savePerson(Person person){
		return personDao.savePerson(person);
		
	}
	
	public Person updatePerson(Person person){
		return personDao.updatePerson(person);
	}
	
	public Tag saveTag(Tag tag){
		return personDao.saveTag(tag);
	}
	
	public Outfit loadOutfit(int id) {
		return personDao.loadOutfit(id);
	}
	
	public boolean deleteOutfit(Outfit outfit){
		return personDao.deleteOutfit(outfit);
	}
	
	public boolean archiveOutfit(Outfit outfit) {
		return personDao.archiveOutfit(outfit);
	}
	
	public Person search (Person person, String searchString, boolean matchWordFlag){
		
		String regex = "\\s*(\\s|,)\\s*";
		String[] str = searchString.split(regex);
		List<String> tagList = Arrays.asList(str);
	
		List<Integer> outfitIdList = personDao.getTagPerson(tagList, person.getId(), matchWordFlag);
		person.getOutfits().clear();
		
		logger.debug("outfit id list ::"+outfitIdList.toString());
		//Tags are eager fetched so outfit list will have them
		person.getOutfits().addAll(personDao.searchOutfit(outfitIdList));
		
		return person;
	}
	
	public Person getByEmail(String email){
		return personDao.getByEmail(email);
	}
	
	public Event saveEvent(Event event){
		return personDao.saveEvent(event);
	}
	
	public List<Event> loadFutureEventsNoOutfitAssigned(int personId){
		
		return personDao.loadFutureEventsNoOutfitAssigned(personId);
	}
	
	public Event loadEventById(int eventId){
		return personDao.loadEventById(eventId);
	}
	public List<Event> loadEventsForCurrentOutfit(int outfitId){
		return personDao.loadEventsForCurrentOutfit(outfitId);
	}
}
