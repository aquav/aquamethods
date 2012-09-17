package org.aquamethods.fashbook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PERSON")
@NamedQueries({
		@NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p"),
		@NamedQuery(name = "Person.findByName", query = "SELECT p FROM Person p where first_name = :name") }

)
public class Person {
	@Id
	// if we dont give @GeneratedValue annotation, insert will fail with Caused by:
	// com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException:
	// Cannot add or update a child row: a foreign key constraint fails
	// (`fashbookdb`.`outfit`, CONSTRAINT `PERSON_ID_FK1` FOREIGN KEY
	// (`PERSON_ID`) REFERENCES `person` (`PERSON_ID`))
	@GeneratedValue 
	@Column(name = "person_id")
	private int id;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;

	private String email;
	private int age;
	
	@OneToMany(mappedBy="associatedPerson", cascade={CascadeType.ALL}, fetch=FetchType.LAZY)
	private List<Outfit> outfits = new ArrayList<Outfit>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Outfit> getOutfits() {
		return outfits;
	}

	public void setOutfits(List<Outfit> outfits) {
		this.outfits = outfits;
	}



	
}
