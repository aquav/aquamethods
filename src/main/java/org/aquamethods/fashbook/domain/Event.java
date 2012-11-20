package org.aquamethods.fashbook.domain;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;

@Entity
@Table(name = "EVENT")
@NamedQueries({
	@NamedQuery(name = "Event.loadFutureEvent", query = "SELECT e FROM Event e where date > :date") }

)
public class Event {

	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private int id;
	private String name;
	private String description;
	private Date date;
	private int outfit_id;
	private int person_id;
	private boolean master;
	@OneToMany(mappedBy="masterEvent", cascade={CascadeType.ALL}, fetch=FetchType.EAGER)
	private List<SubEvent> subEvents = new ArrayList<SubEvent>();
	
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
	public boolean isMaster() {
		return master;
	}
	public void setMaster(boolean master) {
		this.master = master;
	}
	public int getPerson_id() {
		return person_id;
	}
	public void setPerson_id(int person_id) {
		this.person_id = person_id;
	}
	public List<SubEvent> getSubEvents() {
		return subEvents;
	}
	public void setSubEvents(List<SubEvent> subEvents) {
		this.subEvents = subEvents;
	}
	
}
