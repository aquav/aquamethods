package org.aquamethods.fashbook.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.dao.jpa.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PersonDaoSpringJpaTest {

	//private ApplicationContext ctx;
	@Autowired
	private IPersonServiceDao personServiceDao;
	private Logger log = Logger.getLogger(this.getClass());

	public PersonDaoSpringJpaTest() {
		log.info("Test Constructor====================================");
		BasicConfigurator.configure();
/*		// TODO Auto-generated constructor stub
		//ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//personServiceDao = (IPersonServiceDao) ctx.getBean("personServiceDao");
		log.info("Application Context Loaded");*/
	}

	@Test
	public void testGetAll() {
		log.info("Test get all =================================");
		for (Person p : personServiceDao.getAll()) {
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
		personServiceDao.savePerson(person);
	}
	
	@Test
	public void testSavePersonOutfit() {
		log.info("Test Save Person and its outfit ====================");
		
		Person person = personServiceDao.getById(17);
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
		personServiceDao.updatePerson(person);
	}

	
	@Test
	public void testGetById() {
		log.info("Test get data by id ==========================");
		Person p = personServiceDao.getById(2);
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		log.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
		log.info("Tags : " + p.getOutfits().get(0).getTags().get(0) );
	}

	@Test
	public void testGetByName() {
		log.info("Test get data by Name ==========================");
		Person p = personServiceDao.getByName("Alex");
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		log.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
		Assert.assertEquals("Alex", p.getFirstName());
		Assert.assertEquals("23", p.getAge());
	}
	
	@Test
	public void searchOutfitTest(){
		
		List<Integer> outfitIdList = Arrays.asList(2,3,4,24,25, 26);
		List<Outfit> outfits = personServiceDao.searchOutfit(outfitIdList, 61);
		for (Outfit o:outfits){
			log.info(" Outfit Id "+ o.getId() + " outfit pic ::"+o.getOutfitPicture());
			log.info("Tag ::"+o.getTags().get(0).getTag());
		}
	}
/*
	@Test
	public void testDeleteData() {
		log.info("Test delete all ==============================");
		Person p = personService.getById(1);
		personService.delete(p);

	}*/
}
