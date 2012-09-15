package org.aquamethods.fashbook.web.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aquamethods.fashbook.web.form.PersonForm;
import org.aquamethods.fashbook.web.form.UploadOutfitForm;
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

	
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getPerson(@PathVariable String name,  ModelMap modelMap) {


		modelMap.addAttribute("person", personService.getById(8));
		return "mypage";
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
				String filePath = "C://VG_DATA//TECH_MAT//POCs//fashbook//images//";
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

}