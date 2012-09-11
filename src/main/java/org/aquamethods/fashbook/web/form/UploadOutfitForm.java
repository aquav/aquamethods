package org.aquamethods.fashbook.web.form;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadOutfitForm {
	 private CommonsMultipartFile fileData;

	public CommonsMultipartFile getFileData() {
		return fileData;
	}

	public void setFileData(CommonsMultipartFile fileData) {
		this.fileData = fileData;
	}
	 
	 
}
