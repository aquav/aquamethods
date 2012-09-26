package org.aquamethods.fashbook.experiments;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Experiments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//String searchString = "black formal, semi-formal traditional    winter_black";
		String searchString = "formal office";
		
		String regex = "\\s*(\\s|,)\\s*";
		String[] str = searchString.split(regex);
		
		List<String> tagList = Arrays.asList(str);
		String queryParam = "";
		for (int i = 0; i < tagList.size(); i++) {
			//System.out.println(":" + str[i]);

			if (i == tagList.size() - 1) {
				queryParam = queryParam + "'%" + tagList.get(i) + "%'";
				break;
			}
			queryParam = queryParam + "'%" + tagList.get(i) + "%'" + " OR LIKE ";

		}
		System.out.println(queryParam);
	}
	public void getImagePath(){
		String tomcatWebappsDir = "C:/Tools/apache-tomcat-6.0.33/webapps";
		int personId = 55;
		String filePath = tomcatWebappsDir 
						+ "/"
						+ "resource/fashbook/images/person" 
						+ "/"
						+ personId;
		
		String fileName = filePath + "/" + "DSC_123.JPG";

		int index = fileName.lastIndexOf("resource");
		 String dbFilePath = fileName.substring(index);
		 
		 System.out.print(dbFilePath);
	}

}
