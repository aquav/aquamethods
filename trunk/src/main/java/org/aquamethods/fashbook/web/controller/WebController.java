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

import org.springframework.beans.factory.annotation.Autowired;
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

import com.mysql.jdbc.log.Log;



@Controller
@RequestMapping("/person")
public class WebController {

	private String uploadFolderPath;

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
	 * 
	 * @param name
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getPerson(@PathVariable String name,  ModelMap modelMap) {

		Person person = personService.getById(8);
		PersonForm form = convertToWebForm(person);
		modelMap.addAttribute("person", form);
		return "mypage";
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
		
		personForm = convertToWebForm(personService.savePerson(personEntity));
	
		ModelMap map = new ModelMap();
		map.addAttribute("person", personForm );

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
				String filePath = "C://fashbook//images//person";
				fileName = filePath + file.getOriginalFilename();
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
			 
			

			// ..........................................
			//session.setAttribute("uploadFile", file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
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