package org.aquamethods.fashbook.services;

import org.apache.log4j.Logger;
import org.aquamethods.fashbook.domain.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonServiceImplTest {

	@Autowired
	private IPersonService personService;
	private Logger log = Logger.getLogger(this.getClass());
	
	@Test
	public void testGetDataById() {
		log.info("Test get data by id ==========================");
		Person p = personService.getById(2);
		log.info("Name : " + p.getFirstName() + " Age " + p.getAge());
		log.info("Outfit : " + p.getOutfits().get(0).getOutfitPicture() );
	}
}
