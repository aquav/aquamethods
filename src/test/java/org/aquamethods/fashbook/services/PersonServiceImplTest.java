package org.aquamethods.fashbook.services;

import java.util.Calendar;

import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Role;
import org.aquamethods.fashbook.domain.Tag;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static final Logger logger = LoggerFactory
			.getLogger(PersonServiceImplTest.class);
	private ApplicationContext ctx;
	
	
	public PersonServiceImplTest() {
		logger.info("Test Constructor====================================");
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = (IPersonService) ctx.getBean("personService");
		logger.info("Application Context Loaded");
	}



	@Test
	public void testGetById() {
		logger.info("Test get data by id ==========================");
		Person p = personService.getById(1, false);
		logger.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		logger.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
		logger.info("Tags : " + p.getOutfits().get(0).getTags().get(0).getTag() );
	}
	
/*	@Test
	public void testSavePerson() {
		logger.info("Test Save Person ==========================");
		Person p = new Person();
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		p.setFirstName("TestUser-" + cal.getTime());
		p.setLastName("TestLastNm");
		p.setAge(23);
		p.setEmail("TestEmail@email.com");
		p.setPassword("abc");
		Role role = new Role();
		role.setRole(1);
		role.setPerson(p);
		p.setRole(role);

		personService.savePerson(p);

		logger.info("ID of saved Person : " + p.getId());
	}*/
	
/*	@Test
	public void testSaveCompleteHierarchy(){
		logger.info("Test Save Person ==========================");
		Person p = new Person();
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		p.setFirstName("TestUser-" + cal.getTime());
		p.setLastName("TestLastNm");
		p.setAge(23);
		p.setEmail("TestEmail1@email.com");
		p.setPassword("123");
		
		//Save only person entity
		Person savedPerson = personService.savePerson(p);

		logger.info("ID of saved Person : " + p.getId());
		
		Outfit outfit = new Outfit();
		outfit.setOutfitPicture("Pic12");
		outfit.setAssociatedPerson(savedPerson);
		outfit.setArchived(true);
		
		savedPerson.getOutfits().add(outfit);
		
		//Save Outfit for already saved Person
		Person updatedPerson = personService.updatePerson(savedPerson);
		
		logger.info("ID of Updated Person : " + updatedPerson.getId());
		logger.info("Putfit pic of Updated Person : " + updatedPerson.getOutfits().get(0).getOutfitPicture());
		
		
		Tag tag = new Tag();
		tag.setTag("Traditional");
		tag.setAssociatedOutfit(updatedPerson.getOutfits().get(0));
		updatedPerson.getOutfits().get(0).getTags().add(tag);
		
		Person updatedPerson2 = personService.updatePerson(updatedPerson);
		
		logger.info("ID of Updated Person : " + updatedPerson2.getId());
		logger.info("Outfit pic of Updated Person : " + updatedPerson2.getOutfits().get(0).getOutfitPicture());
		logger.info("Tag pic of Updated Person : " + updatedPerson2.getOutfits().get(0).getTags().get(0).getTag());
	
	}*/
/*	
	@Test
	public void deleteOutfit(){
		Outfit o = personService.loadOutfit(33);
		boolean b = personService.deleteOutfit(o);
		logger.info("Deleted ::"+b);
		
	}*/
}
