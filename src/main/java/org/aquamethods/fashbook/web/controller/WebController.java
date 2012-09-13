package org.aquamethods.fashbook.web.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.aquamethods.fashbook.web.form.PersonForm;
import org.aquamethods.fashbook.web.form.UploadOutfitForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



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
	public String save(@ModelAttribute("uploadOutfit") UploadOutfitForm uploadOutfit,
			BindingResult result) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "/uploadfile";
		}

		// Some type of file processing...
		System.err.println("File processing-------------------------------------");
		try {
			MultipartFile file = uploadOutfit.getFileData();
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (file.getSize() > 0) {
				inputStream = file.getInputStream();
				if (file.getSize() > 10000) {
					System.out.println("File Size:::" + file.getSize());
					System.out.println("File Original Name:::" + file.getOriginalFilename());
					return "/uploadfile";
				}
				System.out.println("size ::" + file.getSize());
				fileName = //""+"/images/" +
						file.getOriginalFilename();
				outputStream = new FileOutputStream(fileName);
				System.out.println("fileName:" + file.getOriginalFilename());

				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}

			// ..........................................
			//session.setAttribute("uploadFile", file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/forms/uploadfileindex";
	}

}