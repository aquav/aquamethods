package org.aquamethods.fashbook.services;

import java.util.List;

import org.aquamethods.fashbook.domain.Person;
import org.springframework.transaction.annotation.Transactional;

public interface IPersonService {
	
	@Transactional
	public Person getById(int id);

}
