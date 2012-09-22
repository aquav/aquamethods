package org.aquamethods.fashbook.services.impl;

import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Tag;
import org.aquamethods.fashbook.services.IPersonService;
import org.aquamethods.fashbook.dao.IPersonServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
public class PersonServiceImpl implements IPersonService{

	@Autowired
	IPersonServiceDao personDao;
	
	public Person getById(int id){
		Person p = personDao.getById(id);
		Person returnPerson ;
		if (p!=null){
			returnPerson = new Person();
			returnPerson.setFirstName(p.getFirstName());
			returnPerson.setLastName(p.getLastName());
			returnPerson.setAge(p.getAge());
			returnPerson.setEmail(p.getEmail());
			returnPerson.getOutfits().addAll(p.getOutfits());
	
			System.out.println("Name : " + returnPerson.getFirstName() + " Age " + returnPerson.getAge());
			//System.out.println("Outfit : " + returnPerson.getOutfits().get(0).getOutfitPicture() );
			//System.out.println("Tags : " + returnPerson.getOutfits().get(0).getTags().get(0).getTag() );

		}
		
		return p;
	}
	
	public Person savePerson(Person person){
		return personDao.savePerson(person);
		
	}
	
	public Person updatePerson(Person person){
		return personDao.updatePerson(person);
	}
	
	public Tag saveTag(Tag tag){
		return personDao.saveTag(tag);
	}
	
	public Outfit loadOutfit(int id) {
		return personDao.loadOutfit(id);
	}
}
