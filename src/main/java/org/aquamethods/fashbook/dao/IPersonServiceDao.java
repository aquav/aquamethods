package org.aquamethods.fashbook.dao;

import java.util.List;

import org.aquamethods.fashbook.domain.Person;
import org.aquamethods.fashbook.domain.Outfit;
import org.springframework.transaction.annotation.Transactional;



public interface IPersonServiceDao {
	public boolean savePerson(Person person);
	public List<Person> getAll();
	public Person getById(int id);
	public Person getByName(String name);
	public boolean delete(Person person);
	public boolean updatePerson(Person person);
	//public boolean saveOutfit(Outfit outfit);
}

