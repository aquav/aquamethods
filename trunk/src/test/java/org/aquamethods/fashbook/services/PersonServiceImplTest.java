package org.aquamethods.fashbook.services;

import java.util.Calendar;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.aquamethods.fashbook.domain.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@TransactionConfiguration(transactionManager = "transactionManager")
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PersonServiceImplTest {

	@Autowired
	private IPersonService personService;
	private Logger log = Logger.getLogger(this.getClass());
	private ApplicationContext ctx;
	
	
	public PersonServiceImplTest() {
		log.info("Test Constructor====================================");
		BasicConfigurator.configure();
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = (IPersonService) ctx.getBean("personService");
		log.info("Application Context Loaded");
	}



	@Test
	public void testGetById() {
		log.info("Test get data by id ==========================");
		Person p = personService.getById(2);
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		log.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
		log.info("Tags : " + p.getOutfits().get(0).getTags().get(0).getTag() );
	}
	
	@Test
	public void testSavePerson() {
		log.info("Test Save Person ==========================");
		Person p = new Person();
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		p.setFirstName("TestUser-" + cal.getTime());
		p.setLastName("TestLastNm");
		p.setAge(23);
		p.setEmail("TestEmail@email.com");
		
		personService.savePerson(p);

		log.info("ID of saved Person : " + p.getId());
	}
}
