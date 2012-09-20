package org.aquamethods.fashbook.web.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aquamethods.fashbook.web.form.OutfitForm;
import org.aquamethods.fashbook.web.form.PersonForm;
import org.aquamethods.fashbook.web.form.TagForm;
import org.aquamethods.fashbook.web.form.UploadOutfitForm;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.services.IPersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	private String uploadFolderPath;
	private static final Logger logger = LoggerFactory.getLogger(WebController.class);
	
	@Autowired
	private IPersonService personService;
	

	public WebController(){
	}
	
	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
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
	/**
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/newuser", method = RequestMethod.POST)
	public String save(@ModelAttribute("person") PersonForm personForm,
			BindingResult result) {

		Person personEntity = convertToDaoEntity(personForm);
		
		personService.savePerson(personEntity);
		int id = personEntity.getId();
	
		personForm.setId(id);

		// name of jsp - list.jsp
		return "redirect:/person/"+ id+"/outfit";

	}

	@RequestMapping(value="{personId}/outfit", method = RequestMethod.GET)
	public String getOutfit(@PathVariable("personId") int personId, Model model) {
		
		Person person = personService.getById(personId);
		PersonForm form = convertToWebForm(person);
		model.addAttribute("person", form);
		return "mypage";
	}
	
	@RequestMapping(value="{personId}/outfit/upload", method = RequestMethod.GET)
	public String uploadOutfit(@PathVariable("personId") int personId, Model model) {
		
		UploadOutfitForm form = new UploadOutfitForm();
		
		form.setPersonId(personId);
		model.addAttribute("uploadOutfit", form);
		
		return "/uploadfile";
	}
	
	@RequestMapping(value="{personId}/outfit/upload", method = RequestMethod.POST)
	public String saveOutfit(@ModelAttribute("uploadOutfit") UploadOutfitForm uploadOutfit,
			BindingResult result, HttpServletRequest request, HttpServletResponse response ) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error in uploading: " + error.getCode() + " - "
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
				if (file.getSize() > 100000) {
					System.out.println("File Size increases the limit:::" + file.getSize());
					System.out.println("File Original Name:::" + file.getOriginalFilename());
					return "/uploadfile";
				}
				System.out.println("size ::" + file.getSize());
				String filePath = "C://fashbook//images//person//"+uploadOutfit.getPersonId();
				fileName = filePath +"//"+ file.getOriginalFilename();
				outputStream = new FileOutputStream(fileName);
				System.out.println("fileName:" + fileName);

				int readBytes = 0;
				byte[] buffer = new byte[100000];
				while ((readBytes = inputStream.read(buffer, 0, 100000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
			
			Person personEntity = personService.getById(uploadOutfit.getPersonId());
			
			Outfit outfit = new Outfit();
			outfit.setOutfitPicture(fileName);
			personEntity.getOutfits().add(outfit);
			personService.updatePerson(personEntity);

			logger.info("Person id after uploading pic ::" + personEntity.getId());
			// ..........................................
			//session.setAttribute("uploadFile", file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return "/uploadfile";
	}
	
	/**
	 * 
	 * @param person
	 * @return
	 */
	private PersonForm convertToWebForm(Person person){
		PersonForm form = new PersonForm();
		form.setId(person.getId());
		form.setFirstName(person.getFirstName());
		form.setLastName(person.getLastName());
		form.setEmail(person.getEmail());
		form.setAge(person.getAge());
		List<OutfitForm> outfitFormList = new ArrayList<OutfitForm>();
		for (Outfit outfit : person.getOutfits()){
			OutfitForm outfitForm = new OutfitForm();
			outfitForm.setOutfitPicture(outfit.getOutfitPicture());
			outfitForm.setOutfitDescription(outfit.getOutfitDescription());
			List<TagForm> tagFormList = new ArrayList<TagForm>();
			for (Tag tag : outfit.getTags()){
				TagForm tagForm = new TagForm();
				tagForm.setTag(tag.getTag());
				tagFormList.add(tagForm);
			}
			outfitForm.setTags(tagFormList);
			outfitFormList.add(outfitForm);
		}
		form.setOutfits(outfitFormList);
		return form;
	}
	
	private Person convertToDaoEntity(PersonForm personForm){
		Person person = new Person();
		person.setFirstName(personForm.getFirstName());
		person.setLastName(personForm.getLastName());
		person.setAge(personForm.getAge());
		person.setEmail(personForm.getEmail());
		
		return person;
	}

}