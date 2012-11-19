package org.aquamethods.fashbook.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SUBEVENT")
public class SubEvent {

	@Id
	@GeneratedValue
	@Column(name = "subevent_id")
	private int id;
	private String name;
	private String description;
	private Date date;
	private int outfit_id;
	
	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = false, insertable = true, updatable = false)
	private Event masterEvent;

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getOutfit_id() {
		return outfit_id;
	}
	public void setOutfit_id(int outfit_id) {
		this.outfit_id = outfit_id;
	}
	public Event getMasterEvent() {
		return masterEvent;
	}
	public void setMasterEvent(Event masterEvent) {
		this.masterEvent = masterEvent;
	}

	
}
