package org.aquamethods.fashbook.experiments;

import java.io.File;

public class Experiments {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
