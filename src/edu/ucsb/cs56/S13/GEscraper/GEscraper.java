package edu.ucsb.cs56.S13.GEscraper;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

/** GEscraper Class takes classes from a URL and displays them in a list.
    @author Brent Kirkland, Dylan Lynch 
    @version 2015.02.24
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

		//create an ArrayList to store courses
		ArrayList<String> courses=new ArrayList<String>();
	
		//URL Concatination
		String url = urlPrefix + area + urlSuffix;

		//empty string to capture each line 
		String contents = "";
	
		//try to connect to URL
        	try{

            		URL htmlcode = new URL(url);
            		URLConnection hc = htmlcode.openConnection();
           	 	BufferedReader in = new BufferedReader(new InputStreamReader(hc.getInputStream()));
           	 	
			String read;

			while ((read = in.readLine()) != null)
               		 	contents=contents+read;
            		in.close();

        	}
		//Catch bad URL
        	catch (MalformedURLException e) {
            		System.out.println("Hmmmmm. This area does not exist.");
            		System.exit(1);
        	}
		//catch IOException
        	catch (IOException e) {
            		System.out.println("IOException occured. Please try to enter the correct Area");
            		System.exit(1);
        	}
	
		//create an array of string that are seperated by the html p tag
        	String[] splitContents = 
			contents.split("<p style=\"text-indent: -10px; margin-top: 0px; margin-bottom: 0px; padding: 0px; margin-left: 23px;\">");           
	
		//String variable to capture specific data
		String thisPart;
	
		//start for loop at x = 1 to skip the html we don't care about	
		for (int x=1; x<(splitContents.length); x++) { 
		
			//get string
			thisPart=splitContents[x];
	
			//replace unneeded data
			thisPart=thisPart.replaceAll("- <i>", "break");

			//create a new array of String split by the "break"
			String[] splitThisPart=thisPart.split("break"); 

			//the first part of array is the coureName
			String courseName=(splitThisPart[0].trim());

			courses.add(courseName);
		}

		return courses;

	}
	
	//main	
	public static void main(String args[]){

		//create new scanner
	    	Scanner scanner = new Scanner(System.in);

		//print command to ask for Subject Area
	    	System.out.println("Enter a Subject Area (B-H):");
		
		//scan for input
	    	String s = scanner.next();
	
		//pass area and get area courses
	    	ArrayList<String> list = getAreaCourses(s);

		//print all courses from the arraylist that was returned to list with a loop	
	    	for(int i=0; i<list.size();i++){ System.out.println(list.get(i));}
	}//end main

}//end GEscraper
