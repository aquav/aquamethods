package org.aquamethods.fashbook.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.aquamethods.fashbook.domain.Event;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.services.IPersonService;
import org.aquamethods.fashbook.web.form.EventForm;
import org.aquamethods.fashbook.web.form.PersonForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/person/{personId}/getready")
public class GetReadyController {

	private static final Logger logger = LoggerFactory
			.getLogger(GetReadyController.class);
	
	@Autowired
	private IPersonService personService;
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String getEvent(@PathVariable("personId") int personId, Model model) {
		
		EventForm event = new EventForm();
		
		model.addAttribute("event", event);
		
		return "getready-tile";
	}
	
	@RequestMapping(value = "/eventlog", method = RequestMethod.GET)
	public String getEventLog(@PathVariable("personId") int personId, Model model) {
		
		List<Event> events = personService.loadAllEventsForPerson(personId);
		
		List<EventForm> eventFormList = new ArrayList<EventForm>();
		
		for (Event event : events){
		
			EventForm eventForm = new EventForm();
			eventForm.setName(event.getName());
			eventForm.setDerivedDate(event.getDate());
			
			eventFormList.add(eventForm);
		}
		model.addAttribute("eventFormList", eventFormList);
		return "getreadylog-tile";
	}
	/**
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public String save(@ModelAttribute("event") EventForm eventForm,@PathVariable("personId") int personId,
			BindingResult result, Model model) throws Exception {
		
		Event eventEntity = new Event();
		eventEntity.setName(eventForm.getName());
		eventEntity.setDescription(eventForm.getDescription());
		eventEntity.setDate(getEventFormDate(eventForm));
		eventEntity.setMaster(eventForm.isMaster());
		eventEntity.setPerson_id(personId);
		
		Event eventSaved = personService.saveEvent(eventEntity);
		if (eventSaved.getId() != 0){
			model.addAttribute("event", eventForm);
		} 
		return "getready-tile";

	}
	
	@RequestMapping(value="/event/{eventId}/outfit/{outfitId}", method=RequestMethod.POST)
	public String assignOutfitToEvent(@PathVariable("personId") int personId,@PathVariable("eventId") int eventId,@PathVariable("outfitId") int outfitId){
		
		Event event = personService.loadEventById(eventId);
		
		event.setOutfit_id(outfitId);
		personService.saveEvent(event);
		
		//personService.saveEvent(event);
		return "redirect:/person/"+personId+"/outfit/"+outfitId;
	}
	
	
	private Date getEventFormDate(EventForm eventForm) throws Exception{
		String date = eventForm.getDate();
		String hour = eventForm.getHour();
		String min = eventForm.getMinutes();
		String ampm = eventForm.getAmpm();
		
		String dateString =  date +" "+hour+":"+min+":"+00+" "+ampm;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd h:mm:ss a", Locale.US);
		
		Date returnDate = format.parse(dateString);
		
		return returnDate;
	}
}
