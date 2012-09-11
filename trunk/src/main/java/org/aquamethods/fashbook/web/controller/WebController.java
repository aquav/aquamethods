package org.aquamethods.fashbook.web.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.web.form.*;



@Controller
@RequestMapping("/person")
public class WebController {

/*	private ApplicationContext ctx;
	private IPersonServiceDao personService;*/
	

	public WebController(){
/*		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = (IPersonServiceDao) ctx.getBean("iPersonService");*/

	}
	
	@RequestMapping(value = "person/{name}", method = RequestMethod.GET)
	public String getPerson(@PathVariable String name, ModelMap model) {

		//Person person = personService.getByName(name);

		model.addAttribute("name", "Vishal");

		// name of jsp - list.jsp
		return "list";

	}

	@RequestMapping(value="/newuser", method = RequestMethod.POST)
	public String save(@ModelAttribute("person") PersonForm person,
			BindingResult result) {

		ModelMap map = new ModelMap();
		map.addAttribute("name", person.getFirstName() );

		// name of jsp - list.jsp
		return "list";
	}

	/**
	 * Create new user
	 * @return
	 */
	@RequestMapping(value = "/newuser", method = RequestMethod.GET)
	public ModelAndView newUser() {
		PersonForm form = new PersonForm();
		ModelMap map = new ModelMap();
		map.put("person", form);
		return new ModelAndView("user", map);
	}

	@RequestMapping(value="/outfit", method = RequestMethod.GET)
	public ModelAndView uploadOutfit() {
		
		UploadOutfitForm form = new UploadOutfitForm();
		ModelMap map = new ModelMap();
		map.put("uploadOutfit", form);
		return new ModelAndView("uploadfile", map);
	}
	
	@RequestMapping(value="/outfit", method = RequestMethod.POST)
	public String save(@ModelAttribute("UploadItemForm") UploadOutfitForm uploadItem,
			BindingResult result) {

		ModelMap map = new ModelMap();
		//map.addAttribute("name", person.getFirstName() );

		// name of jsp - list.jsp
		return "list";
	}
}