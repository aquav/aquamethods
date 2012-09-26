package org.aquamethods.fashbook.web.form;

public class SearchForm {
 private int personId;
 private String searchString;
 private boolean matchWordFlag;

 public int getPersonId() {
	return personId;
}
public void setPersonId(int personId) {
	this.personId = personId;
}
public String getSearchString() {
	return searchString;
}
public void setSearchString(String searchString) {
	this.searchString = searchString;
}
public boolean isMatchWordFlag() {
	return matchWordFlag;
}
public void setMatchWordFlag(boolean matchWordFlag) {
	this.matchWordFlag = matchWordFlag;
}
 
 
 
}
