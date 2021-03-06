package org.aquamethods.fashbook.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.aquamethods.fashbook.domain.Event;
import org.aquamethods.fashbook.domain.Outfit;
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
	
	// GET for URL /eventlog
	/**
	 * 
	 * @param personId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/eventlog", method = RequestMethod.GET)
	public String getEventLog(@PathVariable("personId") int personId, Model model) {
		
		List<Event> events = personService.loadAllEventsForPerson(personId);
		//sort the collection
		Collections.sort(events);
		Collections.reverse(events);
		List<EventForm> eventFormList = new ArrayList<EventForm>();
		
		for (Event event : events){
			logger.debug("event date :"+event.getDate());
			EventForm eventForm = new EventForm();
			eventForm.setId(event.getId());
			eventForm.setName(event.getName());
			eventForm.setDerivedDate(event.getDate());
			eventForm.setEventOutfitId(event.getOutfit_id());
			eventForm.setEventOutfitImagePath(getEventOutfitImagePath(event.getOutfit_id()));
			eventFormList.add(eventForm);
		}
		model.addAttribute("eventFormList", eventFormList);
		return "eventlog-tile";
	}
	
	
	//GET and POST for URL /event
	/**
	 * 
	 * @param personId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String getEvent(@PathVariable("personId") int personId, Model model) {
		
		EventForm event = new EventForm();
		
		model.addAttribute("event", event);
		
		return "getready-tile";
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
		eventEntity.setHour(eventForm.getHour());
		eventEntity.setMinute(eventForm.getMinute());
		eventEntity.setAmpm(eventForm.getAmpm());
		
		Event eventSaved = personService.saveEvent(eventEntity);
		if (eventSaved.getId() != 0){
			model.addAttribute("event", eventForm);
		} 
		return "getready-tile";

	}
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.PUT)
	public String editEvent(@ModelAttribute("event") EventForm eventForm,@PathVariable("personId") int personId,
			@PathVariable("eventId") int eventId,
			BindingResult result, Model model) throws Exception {
		
		Event eventEntity = personService.loadEventById(eventId);
		//eventEntity.setName(eventForm.getName());
		eventEntity.setDescription(eventForm.getDescription());
		eventEntity.setDate(getEventFormDate(eventForm));
		eventEntity.setMaster(eventForm.isMaster());
		//eventEntity.setPerson_id(personId);
		eventEntity.setHour(eventForm.getHour());
		eventEntity.setMinute(eventForm.getMinute());
		eventEntity.setAmpm(eventForm.getAmpm());
		
		Event eventSaved = personService.saveEvent(eventEntity);
		if (eventSaved.getId() != 0){
			model.addAttribute("event", eventForm);
		} 
		return "redirect:/person/"+personId+"/getready/eventlog";

	}
	// GET and POST for URL /event/{eventId}/outfit/{outfitId}
	
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET)
	public String getSingleEventForEdit(@PathVariable("personId") int personId, @PathVariable("eventId") int eventId, Model model) {
		
		EventForm eventForm = new EventForm();
		

		
		if (eventId != 0) {
			Event event = personService.loadEventById(eventId);
			eventForm.setId(event.getId());
			eventForm.setName(event.getName());
			eventForm.setDescription(event.getDescription());
			eventForm.setDerivedDate(event.getDate());
		
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			String dateStr = format.format(event.getDate());
			eventForm.setDate(dateStr);
			
			eventForm.setHour(event.getHour());
			eventForm.setMinute(event.getMinute());
			eventForm.setAmpm(event.getAmpm());
			eventForm.setEventOutfitId(event.getOutfit_id());
			eventForm.setEventOutfitImagePath(getEventOutfitImagePath(event
					.getOutfit_id()));
		}
		model.addAttribute("event", eventForm);
		
		return "getsingleevent-tile";
	}
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.DELETE)
	public String deleteSingleEvent(@PathVariable("personId") int personId, @PathVariable("eventId") int eventId){
		personService.deleteEvent(eventId);
		return "redirect:/person/"+personId+"/getready/eventlog";
	}
	
	@RequestMapping(value="/event/{eventId}/outfit/{outfitId}", method=RequestMethod.DELETE)
	public String unAssignOutfitFromEvent(@PathVariable("personId") int personId,@PathVariable("eventId") int eventId,@PathVariable("outfitId") int outfitId){
		
		Event event = personService.loadEventById(eventId);
		outfitId=0;
		event.setOutfit_id(outfitId);
		personService.saveEvent(event);

		return "redirect:/person/"+personId+"/getready/event/"+eventId;
	}
	
	private Date getEventFormDate(EventForm eventForm) throws Exception{
		String date = eventForm.getDate();
		String hour = eventForm.getHour();
		String min = eventForm.getMinute();
		String ampm = eventForm.getAmpm();
		if (date == null || date ==""){
			return null;
		}
		String dateString =  date +" "+hour+":"+min+":"+00+" "+ampm;
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a", Locale.US);
		
		Date returnDate = format.parse(dateString);
		
		return returnDate;
	}
	private String getEventOutfitImagePath(int outfitId){
		Outfit outfit = personService.loadOutfit(outfitId);
		if (outfit == null){
			return "/fashbook/static/images/nooutfitfound.png";
		}else{
			return outfit.getOutfitPicture();
		}
				
	}
}
