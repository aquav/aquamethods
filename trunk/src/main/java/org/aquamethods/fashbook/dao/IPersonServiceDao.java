package org.aquamethods.fashbook.dao;

import java.util.List;

import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Outfit;
import org.aquamethods.fashbook.domain.Tag;
import org.springframework.transaction.annotation.Transactional;



public interface IPersonServiceDao {
	public Person savePerson(Person person);
	public List<Person> getAll();
	public Person getById(int id);
	public Person getByName(String name);
	public boolean delete(Person person);
	public Person updatePerson(Person person);
	//public boolean saveOutfit(Outfit outfit);
	public Tag saveTag(Tag tag);
	public Outfit loadOutfit(int id) ;
	public List<Outfit> searchOutfit(List<Integer> outfitIdList, int personId);
}

