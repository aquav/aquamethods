package org.aquamethods.fashbook.web.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.aquamethods.fashbook.web.form.EventForm;
import org.aquamethods.fashbook.web.form.OutfitForm;
import org.aquamethods.fashbook.web.form.PersonForm;
import org.aquamethods.fashbook.web.form.SearchForm;
import org.aquamethods.fashbook.web.form.TagForm;
import org.aquamethods.fashbook.web.form.UploadOutfitForm;
import org.aquamethods.fashbook.common.UserSessionData;
import org.aquamethods.fashbook.domain.Event;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Role;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.services.IPersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/person")
public class WebController {

	private String uploadFolderPath;
	private static final Logger logger = LoggerFactory
			.getLogger(WebController.class);
	
	//need to move this in properties
	public static final String tomcatWebappsDir = "C:/Tools/apache-tomcat-6.0.33/webapps";

	@Autowired
	private IPersonService personService;
	
	@Autowired
	private UserSessionData userSessionData;

	public WebController() {
	}

	public String getUploadFolderPath() {
		return uploadFolderPath;
	}

	public void setUploadFolderPath(String uploadFolderPath) {
		this.uploadFolderPath = uploadFolderPath;
	}

	@RequestMapping
	public String getHomePage() {
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();
		logger.info("userName ::" + userName);
		int personId = 0;

		if (userSessionData.getPersonId() == 0) {
			Person person = personService.getByEmail(userName);
			personId = person.getId();
			// Set person id in session scoped bean
			userSessionData.setPersonId(personId);
			userSessionData.setPersonFirstName(person.getFirstName());
			userSessionData.setPersonLastName(person.getLastName());
		} else {
			personId = userSessionData.getPersonId();
		}
		return "redirect:/person/" + personId + "/outfit";
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
		return new ModelAndView("newuser-tile", map);
	}

	/**
	 * 
	 * @param person
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/newuser", method = RequestMethod.POST)
	public String save(@ModelAttribute("person") PersonForm personForm,
			BindingResult result, Model model) {

		Person personEntity = convertToDaoEntity(personForm);

		personService.savePerson(personEntity);
		int id = personEntity.getId();
		logger.info("ID of newly created person ::" + id);
		personForm.setId(id);

		// name of jsp - list.jsp
		return "redirect:/person/" + id + "/outfit";

	}

	@RequestMapping(value = "{personId}/outfit", method = RequestMethod.GET)
	public String getOutfit(
			@PathVariable("personId") int personId,
			@RequestParam(value = "searchString", required = false) String searchString,
			@RequestParam(value="matchWordFlag", required=false) boolean matchWordFlag,
			Model model) {

		logger.debug("person Id :"+personId);
		Person person = personService.getById(personId, false);
		SearchForm search = new SearchForm();
		
		if (searchString != null && !searchString.trim().isEmpty()) {
			logger.debug("Search String ::" + searchString + ":: for person "+person.getId());
			// search logic, search will only return outfits which are not archived
			 person = personService.search(person, searchString, matchWordFlag);
			
			search.setSearchString(searchString);
		}

		PersonForm form = convertToWebForm(person);
		model.addAttribute("person", form);
		
		
		model.addAttribute("search", search);

		return "mywardrobe-tile";
	}
	
	@RequestMapping(value = "{personId}/outfit/archived", method = RequestMethod.GET)
	public String getArchivedOutfit(
			@PathVariable("personId") int personId, Model model) {

		Person person = personService.getById(personId, true);
		
		PersonForm form = convertToWebForm(person);
		model.addAttribute("person", form);
		
		return "mywardrobe-tile";
	}

/*	@RequestMapping(value = "{personId}/outfit/search", method = RequestMethod.GET)
	public String searchOutfit(@ModelAttribute("search") SearchForm search,
			BindingResult result, Model model) {
		int personId = search.getPersonId();

		Person person = personService.getById(personId);

		return "mywardrobe-tile";

	}*/

	@RequestMapping(value = "{personId}/outfit/upload", method = RequestMethod.GET)
	public String uploadOutfit(@PathVariable("personId") int personId,
			Model model) {

		UploadOutfitForm form = new UploadOutfitForm();

		form.setPersonId(personId);
		model.addAttribute("uploadOutfit", form);

		return "uploadfile-tile";
	}

	/**
	 * This method upload the outfit picture on local drive and save the image
	 * path in DB for a person
	 * 
	 * @param uploadOutfit
	 * @param result
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "{personId}/outfit/upload", method = RequestMethod.POST)
	public String saveOutfit(
			@ModelAttribute("uploadOutfit") UploadOutfitForm uploadOutfit,
			BindingResult result, Model model) throws Exception {

		int personId = uploadOutfit.getPersonId();
		logger.debug("personId ::" + personId);

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				logger.error("Error in uploading: " + error.getCode() + " - "
						+ error.getDefaultMessage());
			}
			return "uploadfile-tile";
		}

		// Some type of file processing...
		logger.debug("File processing-------------------------------------");
		try {
			MultipartFile multipartFile = uploadOutfit.getFileData();
			String fileName = null;

			if (multipartFile.getSize() > 0) {
				/*if (multipartFile.getSize() > 1000000) {
					logger.error("File Size increases the limit:::"
							+ multipartFile.getSize());
					logger.error("File Original Name:::"
							+ multipartFile.getOriginalFilename());
					return "uploadfile-tile";
				}*/
				logger.debug("size ::" + multipartFile.getSize());
				

				String filePath = tomcatWebappsDir + "/"
						+ "resources/fashbook/images/person" + "/" + personId;
				/*Calendar cal = Calendar.getInstance();
				fileName = filePath + "/" + personId+"_"+cal.getTimeInMillis() + ".jpg"; */
				//multipartFile.getOriginalFilename();

				
				File dir = new File(filePath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				
				fileName = filePath + "/" + personId +"_temp.jpg";

				logger.debug("Path of directory ::" + dir.getAbsolutePath());

				File dest = new File(fileName);

				try {
					multipartFile.transferTo(dest);
				} catch (IllegalStateException e) {
					logger.error("File uploaded failed:" + e);
					// return "File uploaded failed:";
				} catch (IOException e) {
					logger.error("File uploaded failed:" + e);
					// return "File uploaded failed:";
				}
				
				BufferedImage image=resizeImagewithType(fileName);
				ImageIO.write(image,"jpg",new File(fileName));
			}


			
		
			/*Person personEntity = personService.getById(personId, false);

			Outfit outfit = new Outfit();
			outfit.setOutfitPicture(dbFilePath);
			outfit.setAssociatedPerson(personEntity);
			outfit.setArchived(false);

			personEntity.getOutfits().add(outfit);
			personService.updatePerson(personEntity);

			logger.debug("Person id after updating person ::"
					+ personEntity.getId());
*/
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in upload file");
			throw e;
		}
		//return "uploadfile-tile";
		return "edituploadedfile-tile";
	}
	
	@RequestMapping(value = "/{personId}/outfit/saveimage", method = RequestMethod.POST)
	public String saveOutfitImage( @PathVariable("personId") int personId, HttpServletRequest request, 
            HttpServletResponse response ) {
		
		//Get all the parameters which were populated by JCrop
	    int x1=Integer.parseInt(request.getParameter("X1"));
	    int y1=Integer.parseInt(request.getParameter("Y1"));
	    int x2=Integer.parseInt(request.getParameter("X2"));
	    int y2=Integer.parseInt(request.getParameter("Y2"));
	    int w=Integer.parseInt(request.getParameter("W"));
	    int h=Integer.parseInt(request.getParameter("H"));
	   
	    logger.debug(" image dimensions:: "+x1+" "+y1+" "+x2+" "+y2+" "+w+" "+" "+h);
	    
		String baseFilePath = tomcatWebappsDir+"/resources/fashbook/images/person/"+personId;
		
		try{

			
		File srcFile = new File(baseFilePath+"/"+personId+"_temp.jpg");
			
		BufferedImage image=ImageIO.read(srcFile);
			
		BufferedImage out=image.getSubimage(x1,y1,w,h);
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat ft = new SimpleDateFormat ("yyMMddHHmmssZ");
			
		String newFileName = baseFilePath + "/" + personId+"_"+ft.format(cal.getTime()) + ".jpg";
		
		ImageIO.write(out,"jpg",new File(newFileName));
		
		logger.debug("file name after cropping :: "+newFileName);
		
		//File destFile = new File(newFileName);
		//FileUtils.copyFile(srcFile, destFile);
		
		Person personEntity = personService.getById(personId, false);

		int index = newFileName.lastIndexOf("resource");
		String dbFilePath = newFileName.substring(index);

		logger.debug("DB File Path of image for Person id " + personId
				+ " after uploading pic ::" + dbFilePath);
		
		Outfit outfit = new Outfit();
		outfit.setOutfitPicture(dbFilePath);
		outfit.setAssociatedPerson(personEntity);
		outfit.setArchived(false);

		personEntity.getOutfits().add(outfit);
		personService.updatePerson(personEntity);

		logger.debug("Person id after updating person ::"
				+ personEntity.getId());
		}catch(Exception e){
			logger.error("Error in saving/copying file"+e);
		}
		return "redirect:/person/"+personId+"/outfit";
	}
	
	@RequestMapping(value = "/{personId}/outfit/{outfitId}", method = RequestMethod.GET)
	public String getOutfit(@PathVariable("personId") int personId,
			@PathVariable("outfitId") int outfitId, Model model) {

		Outfit outfit = personService.loadOutfit(outfitId);
		OutfitForm outfitForm = new OutfitForm();
		
		List<Event> futureEventList = personService.loadFutureEventsNoOutfitAssigned(personId);
		List<EventForm> eventFormList = new ArrayList<EventForm>();
		
		for (Event event : futureEventList) {
			EventForm eventForm = new EventForm();
			eventForm.setName(event.getName());
		
			eventFormList.add(eventForm);
		}
		
		outfitForm.setFutureEvents(eventFormList);
		outfitForm.setId(outfit.getId());
		outfitForm.setPersonId(personId);
		outfitForm.setOutfitPicture(outfit.getOutfitPicture());
		outfitForm.setOutfitDescription(outfit.getOutfitDescription());
		outfitForm.setArchived(outfit.isArchived());
		List<TagForm> tagFormList = new ArrayList<TagForm>();
		for (Tag tag : outfit.getTags()) {
			TagForm tagForm = new TagForm();
			tagForm.setTag(tag.getTag());
			tagFormList.add(tagForm);
		}
		outfitForm.setTags(tagFormList);
	
		TagForm tagForm = new TagForm();
		model.addAttribute("outfit", outfitForm);
		model.addAttribute("tag", tagForm);
		return "myoutfit-tile";
	}

	@RequestMapping(value = "/{personId}/outfit/{outfitId}/tag", method = RequestMethod.POST)
	public String saveTag(@ModelAttribute("tag") TagForm tagForm,
			BindingResult result, HttpServletRequest request,
			HttpServletResponse response) {

		int outfitId = tagForm.getOutfitId();
		Outfit outfit = personService.loadOutfit(outfitId);
		int personId = outfit.getAssociatedPerson().getId();

		logger.debug("Person Id in saveTag ::" + personId);
		Tag tagEntity = new Tag();
		tagEntity.setAssociatedOutfit(outfit);
		tagEntity.setTag(tagForm.getTag());

		Tag savedTag = personService.saveTag(tagEntity);

		OutfitForm outfitForm = new OutfitForm();

		outfitForm.setPersonId(personId);
		outfitForm.setOutfitPicture(outfit.getOutfitPicture());
		outfitForm.setOutfitDescription(outfit.getOutfitDescription());

		List<TagForm> tagFormList = new ArrayList<TagForm>();
		for (Tag tag : outfit.getTags()) {
			TagForm newTagForm = new TagForm();
			newTagForm.setTag(tag.getTag());
			tagFormList.add(newTagForm);
		}
		outfitForm.setTags(tagFormList);

		// TagForm tagForm = new TagForm();
		ModelMap map = new ModelMap();

		map.addAttribute("outfit", outfitForm);
		map.addAttribute("tag", tagForm);

		// return "mytags";
		return "redirect:/person/" + personId + "/outfit/" + outfitId;
	}

	@RequestMapping(value = "/{personId}/outfit/{outfitId}", method = RequestMethod.DELETE)
	public String deleteOutfit(@PathVariable("outfitId") int outfitId, @PathVariable("personId") int personId ) {

		boolean b = personService.deleteOutfit(personService.loadOutfit(outfitId));
		logger.info("Outfit "+outfitId+" Deleted :: "+b);
		return "redirect:/person/"+personId+"/outfit";
	}
	
	@RequestMapping(value = "/{personId}/outfit/{outfitId}/archive", method = RequestMethod.POST)
	public String archiveOutfit(@PathVariable("outfitId") int outfitId, @PathVariable("personId") int personId ) {

		Outfit outfit = personService.loadOutfit(outfitId);
		outfit.setArchived(true);
		boolean b = personService.archiveOutfit(outfit);
		logger.info("Outfit "+outfitId+" Archived :: "+b);
		return "redirect:/person/"+personId+"/outfit";
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
			outfitForm.setId(outfit.getId());
			outfitForm.setPersonId(person.getId());
			outfitForm.setOutfitPicture(outfit.getOutfitPicture());
			outfitForm.setOutfitDescription(outfit.getOutfitDescription());
			outfitForm.setArchived(outfit.isArchived());
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
		person.setPassword(personForm.getPassword());
		Role role = new Role();
		role.setRole(1);
		role.setPerson(person);
		person.setRole(role);
			

		return person;
	}


	private static BufferedImage resizeImagewithType(String fileName) throws Exception{
		
		BufferedImage originalImage = ImageIO.read(new File(fileName));
		int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
		
		int origWidth =  originalImage.getWidth();
		int origHeight = originalImage.getHeight();
		int IMG_WIDTH = 0;
		int IMG_HEIGHT = 0;
		
		//Need to improve this logic -----------------------or use different APIs
		//horizontal pic (aspect ratio is 1.5)
		if (origWidth > origHeight){
			IMG_WIDTH = 1200;
			IMG_HEIGHT = 800;
		} else if (origWidth == origHeight) {
			IMG_WIDTH = 1200;
			IMG_HEIGHT = 1200;
		} else{
			IMG_WIDTH = 800;
			IMG_HEIGHT = 1200;
		}
		
        BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
        g.dispose();

        return resizedImage;
    }
}