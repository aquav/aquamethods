package org.aquamethods.fashbook.experiments.algo;

public class ReverseString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ReverseString().revesreLetter();
	}

	public void revesreLetter(){
		String str= "reverse";
		char[] charAr = str.toCharArray();
		char[] reverseAr = new char[7];
		for (int i = charAr.length ; i > 0 ; i--){
			int j=0;
			reverseAr[j] = charAr[i-1];
			System.out.println(i+" "+charAr[i-1]);

		}
		String reverseStr = new String(reverseAr);
		System.out.println(reverseStr);
	}
}
