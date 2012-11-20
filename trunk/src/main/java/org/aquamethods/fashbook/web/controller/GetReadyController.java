package org.aquamethods.fashbook.web.controller;

import java.util.Date;

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
	
	/**
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public String save(@ModelAttribute("event") EventForm eventForm,@PathVariable("personId") int personId,
			BindingResult result, Model model) {
		
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
	
	private Date getEventFormDate(EventForm eventForm){
		String date = eventForm.getDate();
		int hour = eventForm.getHour();
		int min = eventForm.getMinutes();
		String ampm = eventForm.getAmpm();
		
		
		return new Date();
	}
}
