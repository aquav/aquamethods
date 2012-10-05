package org.aquamethods.fashbook.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.Assert;


import org.apache.log4j.PropertyConfigurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.aquamethods.fashbook.dao.interceptor.DaoInterceptor;
import org.aquamethods.fashbook.dao.jpa.*;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class PersonDaoSpringJpaTest {

	//private ApplicationContext ctx;
	@Autowired
	private IPersonServiceDao personServiceDao;
	private static final Logger logger = LoggerFactory
			.getLogger(PersonDaoSpringJpaTest.class);

	public PersonDaoSpringJpaTest() {
		logger.info("Test Constructor====================================");
		//BasicConfigurator.configure();
		 // BasicConfigurator replaced with PropertyConfigurator.
	     //PropertyConfigurator.configure("resources/log4j.properties");
	     
/*		// TODO Auto-generated constructor stub
		//ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		//personServiceDao = (IPersonServiceDao) ctx.getBean("personServiceDao");
		log.info("Application Context Loaded");*/
	}

	@Test
	public void testGetAll() {
		logger.info("Test get all =================================");
		for (Person p : personServiceDao.getAll()) {
			logger.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		}
	}

	@Test
	public void testSaveOnlyPerson() {
		logger.info("Test Save Data =================================");
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
		logger.info("Test Save Person and its outfit ====================");
		
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
		logger.info("Test get data by id ==========================");
		Person p = personServiceDao.getById(2);
		logger.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		logger.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
		logger.info("Tags : " + p.getOutfits().get(0).getTags().get(0) );
	}

	@Test
	public void testGetByName() {
		logger.info("Test get data by Name ==========================");
		Person p = personServiceDao.getByName("Alex");
		logger.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		logger.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
		Assert.assertEquals("Alex", p.getFirstName());
		Assert.assertEquals("23", p.getAge());
	}
	
	@Test
	public void searchOutfitTest(){
		
		//List<Integer> outfitIdList = Arrays.asList(2,3,4,24,25, 26);
		List<String> tagList = Arrays.asList("black", "formal");
		int personId = 61;
		List<Integer> outfitIdList = personServiceDao.getTagPerson(tagList, personId, true);
		
		List<Outfit> outfits = personServiceDao.searchOutfit(outfitIdList);
		for (Outfit o:outfits){
			logger.info(" Outfit Id "+ o.getId() + " outfit pic ::"+o.getOutfitPicture());
			for(Tag t : o.getTags()){
				logger.info("Tag ::"+t.getTag());
			}
		}
	}

	@Test
	public void testDeleteOutfit() {
		logger.info("Test delete Outfit ==============================");
		Outfit o = personServiceDao.loadOutfit(33);
		boolean deleted = personServiceDao.deleteOutfit(o);
		logger.info("Deleted ::"+deleted);
	}
}
