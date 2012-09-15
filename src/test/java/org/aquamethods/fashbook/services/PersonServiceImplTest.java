package org.aquamethods.fashbook.services;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.aquamethods.fashbook.domain.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonServiceImplTest {

	@Autowired
	private IPersonService personService;
	private Logger log = Logger.getLogger(this.getClass());
	private ApplicationContext ctx;
	
	
	public PersonServiceImplTest() {
		log.info("Test Constructor====================================");
		BasicConfigurator.configure();
		// TODO Auto-generated constructor stub
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = (IPersonService) ctx.getBean("personService");
		log.info("Application Context Loaded");
	}



	@Test
	public void testGetDataById() {
		log.info("Test get data by id ==========================");
		Person p = personService.getById(2);
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		log.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
	}
}
