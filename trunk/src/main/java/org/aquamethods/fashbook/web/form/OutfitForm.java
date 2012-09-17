package org.aquamethods.fashbook.web.form;

import java.util.ArrayList;
import java.util.List;

public class OutfitForm {

	private String outfitPicture;
	private String outfitDescription;
	private List<TagForm> tags = new ArrayList<TagForm>();
	
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
	
	
}
