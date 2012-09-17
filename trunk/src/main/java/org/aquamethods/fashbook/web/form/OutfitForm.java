package org.aquamethods.fashbook.web.form;

import javax.persistence.Column;

public class OutfitForm {

	private String outfitPicture;
	private String outfitDescription;
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
	
	
}
