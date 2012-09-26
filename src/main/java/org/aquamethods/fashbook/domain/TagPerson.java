package org.aquamethods.fashbook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TAG_PERSON")
public class TagPerson {

	@Id
	private int tag_id;
	private String tag;
	private int outfit_id;
	private String outfit_picture;
	private String outfit_description;
	private int person_id;
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getOutfit_id() {
		return outfit_id;
	}
	public void setOutfit_id(int outfit_id) {
		this.outfit_id = outfit_id;
	}
	public String getOutfit_picture() {
		return outfit_picture;
	}
	public void setOutfit_picture(String outfit_picture) {
		this.outfit_picture = outfit_picture;
	}
	public String getOutfit_description() {
		return outfit_description;
	}
	public void setOutfit_description(String outfit_description) {
		this.outfit_description = outfit_description;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	
	
}
