package org.aquamethods.fashbook.web.form;

import java.util.Date;

public class EventForm {

	private int id;
	private String name;
	private String description;
	private String date;
	private String hour;
	private String minutes;
	private String ampm;
	private boolean master;
	private boolean allDayEvent;
	private Date derivedDate;
	private int eventOutfitId;
	private String eventOutfitImagePath;
	
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public String getMinutes() {
		return minutes;
	}
	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}
	public String getAmpm() {
		return ampm;
	}
	public void setAmpm(String ampm) {
		this.ampm = ampm;
	}
	public boolean isMaster() {
		return master;
	}
	public void setMaster(boolean master) {
		this.master = master;
	}
	public boolean isAllDayEvent() {
		return allDayEvent;
	}
	public void setAllDayEvent(boolean allDayEvent) {
		this.allDayEvent = allDayEvent;
	}
	public Date getDerivedDate() {
		return derivedDate;
	}
	public void setDerivedDate(Date derivedDate) {
		this.derivedDate = derivedDate;
	}
	public int getEventOutfitId() {
		return eventOutfitId;
	}
	public void setEventOutfitId(int eventOutfitId) {
		this.eventOutfitId = eventOutfitId;
	}
	public String getEventOutfitImagePath() {
		return eventOutfitImagePath;
	}
	public void setEventOutfitImagePath(String eventOutfitImagePath) {
		this.eventOutfitImagePath = eventOutfitImagePath;
	}
	

	
}
