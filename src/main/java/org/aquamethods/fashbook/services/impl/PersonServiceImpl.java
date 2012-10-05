package org.aquamethods.fashbook.services.impl;

import java.util.Arrays;
import java.util.List;

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
	
	public boolean deleteOutfit(Outfit outfit){
		return personDao.deleteOutfit(outfit);
	}
	
	public Person search (Person person, String searchString, boolean matchWordFlag){
		
		String regex = "\\s*(\\s|,)\\s*";
		String[] str = searchString.split(regex);
		List<String> tagList = Arrays.asList(str);
	
		List<Integer> outfitIdList = personDao.getTagPerson(tagList, person.getId(), matchWordFlag);
		person.getOutfits().clear();
		
		//Tags are eager fetched so outfit list will have them
		person.getOutfits().addAll(personDao.searchOutfit(outfitIdList));
		
		return person;
	}
}
