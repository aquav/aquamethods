package org.aquamethods.fashbook.web.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadOutfitForm {
	private int personId;
	private CommonsMultipartFile fileData;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}

}
