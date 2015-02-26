package edu.ucsb.cs56.W15.GEscraper;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

/** GEscraper Class takes classes from a URL and displays them in a list.
    @author Brent Kirkland, Dylan Lynch 
    @version 2015.02.24
*/

public class GEscraper {
	

public static final String urlPrefix = "http://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/";
public static final String urlSuffix = ".aspx";



/** replace special subject course abbreviations with their URL counterpart, then create URL
@param special subject course abbreviations: "WRT", "EUR", "NWC", "QNT", "ETH"
@return URL with special subject course appended
*/
       public static String getSpecialCoursesURL(String area) {
			String fullName = "";
					if (area == "WRT") { return fullName += "WritingReqCourses"; }
					else if (area == "EUR") { return fullName += "EurTradCourses"; }
					else if (area == "NWC") { return fullName += "WorldCulturesCourses"; }
					else if (area == "QNT") { return fullName += "QuantCourses"; }
					else if (area == "ETH") { return fullName += "EthnicityCourses"; }
				//TODO Error handling for incorrect abbreviation input

       		String url = urlPrefix + fullName + urlSuffix;
       	return url;

       }

/** create specific url for area of general ed
@param Specific area "B", "C", "D", "E", "F", "G", "H"
@return URL with specific area appended into it
*/
       public static String getAreaCoursesURL(String area) {
       		String url = urlPrefix + "Area" + area + urlSuffix;
       		return url;
       }


   
/** get course numbers of gen ed courses in a given area
 @param area as B->H or Special Subject Abbreviation: "WRT", "EUR", "NWC", "QNT", "ETH"
 @return array of 13-space course numbers e.g. for area E {"ANTH 138TS","ANTH 176TS",
 "ARTHI 6A",etc}
 */

	public static ArrayList<String> getCourses(String area) {

		// Check if Gen Ed Course or Special Subject Ed Course
		String url = "";
		if (area.length() == 1) { 
			url = getAreaCoursesURL(area); 
		}
		else if (area.length() > 1) { 
			url = getSpecialCoursesURL(area); 
		}


		//create an ArrayList to store courses
		ArrayList<String> courses = new ArrayList<String>();

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

			//ensure room for course title
			//the space before </i> cut down the length before the </i>
			thisPart= thisPart.replaceAll("                                                                                                                                           </i>","break");

			//create a new array of String split by the "break"
			String[] splitThisPart=thisPart.split("break");

			//String courseTitle;
			//for (int j = 2; j < splitThisPart.length; j++){
			//	if (splitThisPart[j] != null)
			//} 

			//System.out.println(thisPart);
			//the first part of array is the coureName
			String courseName=(splitThisPart[0] + splitThisPart[1]);

			courses.add(courseName);
		}

		return courses;

	}
	
	//main	
	public static void main(String args[]){

		//create new scanner
	    	Scanner scannerAreaCourses = new Scanner(System.in);

		//print command to ask for Subject Area
	    	System.out.println("Enter a Subject Area (B-H):");
		
		//scan for input
	    	String s = scannerAreaCourses.next();
	
		//pass area and get area courses
	    	ArrayList<String> list = getCourses(s);

		//print all courses from the arraylist that was returned to list with a loop	
	    	for(int i=0; i<list.size();i++){ System.out.println(list.get(i));}

	    	System.out.println("");


	    	Scanner scannerSpecialCourses = new Scanner(System.in);
	    	System.out.println("Enter a Special Subject Area (WRT, EUR, NWC, QNT, ETH):");
	    	s = scannerSpecialCourses.next();
	    	ArrayList<String> list2 = getCourses(s);
	    	for(int i=0; i<list2.size();i++){ System.out.println(list2.get(i));}

	}//end main

}//end GEscraper
