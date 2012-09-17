package org.aquamethods.fashbook.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Outfit")
public class Outfit {

	@Id
	@GeneratedValue
	@Column(name = "outfit_id")
	private int id;
	@Column(name = "OUTFIT_PICTURE")
	private String outfitPicture;
	@Column(name = "OUTFIT_DESCRIPTION")
	private String outfitDescription;
	@OneToMany(mappedBy="associatedOutfit", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<Tag> tags = new ArrayList<Tag>();
	
	@ManyToOne
	@JoinColumn(name = "PERSON_ID", nullable = false, insertable = true, updatable = false)
	private Person associatedPerson;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getOutfitPicture() {
		return outfitPicture;
	}
	public void setOutfitPicture(String outfitPicture) {
		this.outfitPicture = outfitPicture;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public String getOutfitDescription() {
		return outfitDescription;
	}
	public void setOutfitDescription(String outfitDescription) {
		this.outfitDescription = outfitDescription;
	}
	public Person getAssociatedPerson() {
		return associatedPerson;
	}
	public void setAssociatedPerson(Person associatedPerson) {
		this.associatedPerson = associatedPerson;
	}

	
}
