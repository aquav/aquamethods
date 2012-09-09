package org.aquamethods.fashbook.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.domain.Outfit;


public class PersonDaoSpringJpaTest {

	private ApplicationContext ctx;
	private IPersonServiceDao personService;
	private Logger log = Logger.getLogger(this.getClass());

	public PersonDaoSpringJpaTest() {
		log.info("Test Constructor====================================");
		BasicConfigurator.configure();
		// TODO Auto-generated constructor stub
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personService = (IPersonServiceDao) ctx.getBean("iPersonService");
		log.info("Application Context Loaded");
	}

	@Test
	public void testGetAll() {
		log.info("Test get all =================================");
		for (Person p : personService.getAll()) {
			log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		}
	}

	@Test
	public void testSaveOnlyPerson() {
		log.info("Test Save Data =================================");
		Person person = new Person();
		person.setFirstName("New");
		person.setLastName("Hopkins");
		person.setAge(23);
		person.setEmail("raven@fashbook.com");
		
		List<Outfit> listOutfit = new ArrayList<Outfit>();
		Outfit outfit = new Outfit();
		outfit.setOutfitPicture("pic3");
		outfit.setAssociatedPerson(person);
		listOutfit.add(outfit);
		
		
		Tag tag = new Tag();
		// imp for one to many save
		tag.setAssociatedOutfit(outfit);
		tag.setTag("formal");
		List<Tag> list = new ArrayList<Tag>();
		list.add(tag);
		outfit.setTags(list);
				
		person.setOutfits(listOutfit);
		personService.savePerson(person);
	}
	
	@Test
	public void testSavePersonOutfit() {
		log.info("Test Save Person and its outfit ====================");
		
		Person person = personService.getById(17);
		List<Outfit> listOutfit = new ArrayList<Outfit>();
		Outfit outfit = new Outfit();
		outfit.setOutfitPicture("pic2");
		outfit.setAssociatedPerson(person);
		listOutfit.add(outfit);
		
		Tag tag = new Tag();
		tag.setAssociatedOutfit(outfit);
		tag.setTag("formal");
		List<Tag> list = new ArrayList<Tag>();
		list.add(tag);
		outfit.setTags(list);
		
		person.setOutfits(listOutfit);
		personService.updatePerson(person);
	}

	
	@Test
	public void testGetDataById() {
		log.info("Test get data by id ==========================");
		Person p = personService.getById(2);
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		log.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
	}

	@Test
	public void testGetByName() {
		log.info("Test get data by Name ==========================");
		Person p = personService.getByName("Alex");
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		Assert.assertEquals("Alex", p.getFirstName());
		Assert.assertEquals("23", p.getAge());
	}
	

/*
	@Test
	public void testDeleteData() {
		log.info("Test delete all ==============================");
		Person p = personService.getById(1);
		personService.delete(p);

	}*/
}
