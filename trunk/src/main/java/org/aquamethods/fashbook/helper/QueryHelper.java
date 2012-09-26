package org.aquamethods.fashbook.helper;

import java.util.List;

public class QueryHelper {
	public static String getQueryStringForSearch(List<String> tagList){
		String queryParam = "";
		for (int i = 0; i < tagList.size(); i++) {
			//System.out.println(":" + str[i]);

			if (i == tagList.size() - 1) {
				queryParam = queryParam + "'%" + tagList.get(i) + "%'";
				break;
			}
			queryParam = queryParam + "'%" + tagList.get(i) + "%'" + " OR t.tag LIKE ";

		}
		System.out.println(queryParam);
		return queryParam;
	}
}
