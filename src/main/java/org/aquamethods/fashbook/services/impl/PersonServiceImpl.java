package org.aquamethods.fashbook.services.impl;

import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.services.IPersonService;
import org.aquamethods.fashbook.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("personService")
public class PersonServiceImpl implements IPersonService{

	@Autowired
	IPersonServiceDao personDao;
	
	public Person getById(int id){
		
		return personDao.getById(id);
		
	}
}
