package org.aquamethods.fashbook.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TAG")
public class Tag {

	@Id
	@GeneratedValue
	@Column(name = "tag_id")
	private int id;
	private String tag;

	@ManyToOne
	@JoinColumn(name = "OUTFIT_ID", nullable = false, insertable = true, updatable = false)
	private Outfit associatedOutfit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Outfit getAssociatedOutfit() {
		return associatedOutfit;
	}

	public void setAssociatedOutfit(Outfit associatedOutfit) {
		this.associatedOutfit = associatedOutfit;
	}

}
