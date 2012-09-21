package org.aquamethods.fashbook.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
	private static final Logger logger = LoggerFactory
			.getLogger(WebController.class);

	@Autowired
	private IPersonService personService;

	public WebController() {
	}

	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
	}

	/**
	 * Create new user
	 * 
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
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String save(@ModelAttribute("person") PersonForm personForm,
			BindingResult result) {

		Person personEntity = convertToDaoEntity(personForm);

		personService.savePerson(personEntity);
		int id = personEntity.getId();
		logger.info("ID of newly created person ::"+id);
		personForm.setId(id);

		// name of jsp - list.jsp
		return "redirect:/person/" + id + "/outfit";

	}

	@RequestMapping(value = "{personId}/outfit", method = RequestMethod.GET)
	public String getOutfit(@PathVariable("personId") int personId, Model model) {

		Person person = personService.getById(personId);
		PersonForm form = convertToWebForm(person);
		model.addAttribute("person", form);
		return "mypage";
	}

	@RequestMapping(value = "{personId}/outfit/upload", method = RequestMethod.GET)
	public String uploadOutfit(@PathVariable("personId") int personId,
			Model model) {

		UploadOutfitForm form = new UploadOutfitForm();

		form.setPersonId(personId);
		model.addAttribute("uploadOutfit", form);

		return "/uploadfile";
	}

	@RequestMapping(value = "{personId}/outfit/upload", method = RequestMethod.POST)
	public String saveOutfit (
			@ModelAttribute("uploadOutfit") UploadOutfitForm uploadOutfit,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		int personId = uploadOutfit.getPersonId();
		logger.debug("personId ::"+personId);
		
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.error("Error in uploading: " + error.getCode()
						+ " - " + error.getDefaultMessage());
			}
			return "/uploadfile";
		}

		// Some type of file processing...
		logger.debug("File processing-------------------------------------");
		try {
			MultipartFile multipartFile = uploadOutfit.getFileData();
			String fileName = null;
			InputStream inputStream = null;
			OutputStream outputStream = null;
			if (multipartFile.getSize() > 0) {
				inputStream = multipartFile.getInputStream();
				if (multipartFile.getSize() > 100000) {
					logger.error("File Size increases the limit:::"
							+ multipartFile.getSize());
					logger.error("File Original Name:::"
							+ multipartFile.getOriginalFilename());
					return "/uploadfile";
				}
				logger.debug("size ::" + multipartFile.getSize());
				String filePath = "C://fashbook//images//person//"
						+ personId;
				fileName = filePath + "//"
						+ multipartFile.getOriginalFilename();

				File dir = new File(filePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				
				logger.debug("Path of directory ::" + dir.getAbsolutePath());
				
				File dest = new File(fileName);

				try {
					multipartFile.transferTo(dest);
				} catch (IllegalStateException e) {
					logger.error("File uploaded failed:"+e);
					//return "File uploaded failed:";
				} catch (IOException e) {
					logger.error("File uploaded failed:"+e);
					//return "File uploaded failed:";
				}

				/*
				 * outputStream = new FileOutputStream(fileName);
				 * System.out.println("fileName:" + fileName);
				 * 
				 * int readBytes = 0; byte[] buffer = new byte[100000]; while
				 * ((readBytes = inputStream.read(buffer, 0, 100000)) != -1) {
				 * outputStream.write(buffer, 0, readBytes); }
				 * outputStream.close();
				 */
				inputStream.close();
			}

			logger.debug("Person id after uploading pic ::"
					+ personId);
			Person personEntity = personService.getById(personId);

			Outfit outfit = new Outfit();
			outfit.setOutfitPicture(fileName);
			outfit.setAssociatedPerson(personEntity);
			
			personEntity.getOutfits().add(outfit);
			personService.updatePerson(personEntity);

			logger.debug("Person id after updating person ::"
					+ personEntity.getId());
			// ..........................................
			// session.setAttribute("uploadFile", file.getOriginalFilename());
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in upload file");
			throw e;
		}
		return "/uploadfile";
	}

	/**
	 * 
	 * @param person
	 * @return
	 */
	private PersonForm convertToWebForm(Person person) {
		PersonForm form = new PersonForm();
		form.setId(person.getId());
		form.setFirstName(person.getFirstName());
		form.setLastName(person.getLastName());
		form.setEmail(person.getEmail());
		form.setAge(person.getAge());
		List<OutfitForm> outfitFormList = new ArrayList<OutfitForm>();
		for (Outfit outfit : person.getOutfits()) {
			OutfitForm outfitForm = new OutfitForm();
			outfitForm.setOutfitPicture(outfit.getOutfitPicture());
			outfitForm.setOutfitDescription(outfit.getOutfitDescription());
			List<TagForm> tagFormList = new ArrayList<TagForm>();
			for (Tag tag : outfit.getTags()) {
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

	private Person convertToDaoEntity(PersonForm personForm) {
		Person person = new Person();
		person.setFirstName(personForm.getFirstName());
		person.setLastName(personForm.getLastName());
		person.setAge(personForm.getAge());
		person.setEmail(personForm.getEmail());

		return person;
	}

}