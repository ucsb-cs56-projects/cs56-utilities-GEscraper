package edu.ucsb.cs56.S13.GEscraper;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

/** GEscraper Class takes classes from a URL and displays them in a list.
    @author Brian Wan
    @version 2013.06.07
*/

public class GEscraper {
	
public static final String urlPrefix = "http://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/Area";
public static final String urlSuffix = ".aspx";
   
/** get course numbers of gen ed courses in a given area
 @param area one of "B", "C", "D", "E", "F", "G", "H"
 @return array of 13-space course numbers e.g. for area E {"ANTH 138TS","ANTH 176TS",
 "ARTHI 6A",etc}
 */

public static ArrayList<String> getAreaCourses(String area) {
	ArrayList<String> courses=new ArrayList<String>();
	String url = urlPrefix + area + urlSuffix; //URL with user input
	String contents = "";
        try{
            URL htmlcode = new URL(url);
            URLConnection hc = htmlcode.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(hc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null)
                contents=contents+inputLine;
            in.close();
        }
        catch (MalformedURLException e) {
            System.out.println("MalformedURLException occured");
            System.exit(1);
        }
        catch (IOException e) {
            System.out.println("IOException occured");
            System.exit(1);
        }

        String[] splitContents=contents.split("<p style=\"text-indent: -10px; margin-top: 0px; margin-bottom: 0px; padding: 0px; margin-left: 23px;\">"); //creates an array of strings that are separated by this string          

	String thisPart;

	for (int x=1; x<(splitContents.length); x++) { //we don't care about the first part
		thisPart=splitContents[x];
		thisPart=thisPart.replaceAll("- <i>", "break");
		String[] splitThisPart=thisPart.split("break"); //creates an array of strings that are separated by "break"
		String courseName=(splitThisPart[0].trim());
		courses.add(courseName);
	}

	return courses;

}//end getAreaCourses
		
	public static void main(String args[]){
	    Scanner scanner = new Scanner(System.in);
	    System.out.println("Enter a Subject Area (B-H):");
	    String s = scanner.next();
	    ArrayList<String> list = getAreaCourses(s);	//takes user input to view what Area they want to see
	    for(int i=0; i<list.size();i++){ System.out.println(list.get(i));}//print all the courses from arraylist
	}

}//end GEscraper
