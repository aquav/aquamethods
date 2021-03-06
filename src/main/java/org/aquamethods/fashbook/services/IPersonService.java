package org.aquamethods.fashbook.services;

import java.util.List;

import org.aquamethods.fashbook.domain.Event;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.springframework.transaction.annotation.Transactional;

public interface IPersonService {
	
	@Transactional
	public Person getById(int id, boolean showArchived);
	
	@Transactional
	public Person savePerson(Person person);
	
	@Transactional
	public Person updatePerson(Person person);
	
	@Transactional
	public Tag saveTag(Tag tag);
	
	@Transactional
	public Outfit loadOutfit(int id) ;
	
	@Transactional 
	public Person search(Person person, String searchString, boolean matchWordFlag);
	
	@Transactional
	public boolean deleteOutfit(Outfit outfit);
	
	@Transactional
	public boolean archiveOutfit(Outfit outfit);
	
	@Transactional
	public Person getByEmail(String email);
	
	@Transactional
	public Event saveEvent(Event event);
	
	@Transactional
	public List<Event> loadEventsNoOutfitAssigned(int personId);
	
	@Transactional
	public Event loadEventById(int eventId);
	
	@Transactional
	public List<Event> loadEventsForCurrentOutfit(int outfitId);
	
	@Transactional
	public List<Event> loadAllEventsForPerson(int personId);
	
	@Transactional
	public boolean deleteEvent(int eventId);
}
