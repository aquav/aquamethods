package org.aquamethods.fashbook.web.form;

import java.util.ArrayList;
import java.util.List;

public class OutfitForm {

	private int id;
	private int personId;
	private String outfitPicture;
	private String outfitDescription;
	private List<TagForm> tags = new ArrayList<TagForm>();
	private List<EventForm> futureEvents = new ArrayList<EventForm>();
	private List<EventForm> outfitEvents = new ArrayList<EventForm>();
	private boolean archived;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getOutfitPicture() {
		return outfitPicture;
	}
	public void setOutfitPicture(String outfitPicture) {
		this.outfitPicture = outfitPicture;
	}
	public String getOutfitDescription() {
		return outfitDescription;
	}
	public void setOutfitDescription(String outfitDescription) {
		this.outfitDescription = outfitDescription;
	}
	public List<TagForm> getTags() {
		return tags;
	}
	public void setTags(List<TagForm> tags) {
		this.tags = tags;
	}
	public boolean isArchived() {
		return archived;
	}
	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	public List<EventForm> getFutureEvents() {
		return futureEvents;
	}
	public void setFutureEvents(List<EventForm> futureEvents) {
		this.futureEvents = futureEvents;
	}
	public List<EventForm> getOutfitEvents() {
		return outfitEvents;
	}
	public void setOutfitEvents(List<EventForm> outfitEvents) {
		this.outfitEvents = outfitEvents;
	}
	
	
}
