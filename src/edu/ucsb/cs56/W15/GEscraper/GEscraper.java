package edu.ucsb.cs56.W15.GEscraper;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

/** GEscraper Class takes classes from a URL and displays them in a list.
    @author Brent Kirkland 
    @author Dylan Lynch 
    @version 03.04.15
*/

public class GEscraper {
	

public static final String urlPrefix = 
	"http://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/";
public static final String urlSuffix = ".aspx";



/** create special subject url 
	@param special subject course abbreviations: "WRT", "EUR", "NWC", "QNT", "ETH"
	@return url with specified special subject courses
*/
       public static String getSpecialCoursesURL(String area) {
			
			String fullName = "";
				if (area.equals("WRT")) { fullName += "WritingReqCourses"; }
				else if (area.equals("EUR")) { fullName += "EurTradCourses"; }
				else if (area.equals("NWC")) { fullName += "WorldCulturesCourses"; }
				else if (area.equals("QNT")) { fullName += "QuantCourses"; }
				else if (area.equals("ETH")) { fullName += "EthnicityCourses"; }
				//TODO Error handling for incorrect abbreviation input

       		String url = urlPrefix + fullName + urlSuffix;
       	return url;
	
       }

/** create specific url for area of general ed
@param Specific area "B", "C", "D", "E", "F", "G", "H"
@return url for specified general area courses
*/
       public static String getAreaCoursesURL(String area) {
       		String url = urlPrefix + "Area" + area + urlSuffix;
       		return url;
       }


/** Gets all specified department courses from specified area
@param General/Special Area, Subject
@return ArrayList of all courses from specific area and department
*/

	public static ArrayList<String> getSpecificCourses(String area, String department) {
		GEscraper g = new GEscraper();
		ArrayList<String> areaCourses = g.getCourses(area);
		ArrayList<String> subjectCourses = new ArrayList<String>();

		for (int i = 0; i <= areaCourses.size() - 1; i++) {
			if (areaCourses.get(i).contains(department)) {
				subjectCourses.add(areaCourses.get(i));
			}
		}

		return subjectCourses;
	}

   
/** get course numbers of gen/special subject courses in a given area
 @param area as B->H or Special Subject Abbreviation: "WRT", "EUR", "NWC", "QNT", "ETH"
 @return array list of all courses with { Course Abbreviation, Course Number, Full Course Name }
 */

	public static ArrayList<String> getCourses(String area) {

		// Check if Gen Ed Course or Special Subject Ed Course
		String url = "";
		if (area.length() == 1) { 
			url = getAreaCoursesURL(area); 
		}
		else if (area.length() == 3) { 
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
            		System.out.println("Area does not exist.");
            		System.exit(1);
        	}
		//catch IOException
      		catch (IOException e) {
            		System.out.println("Incorrect area.");
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
		boolean loop = true;
		ArrayList<String> list = new ArrayList<String>();
		String area, subject;

		// While loop to keep running the program unless the user inputs No (N)
		while (loop == true) {
		//create new scanner
	    	Scanner scanner = new Scanner(System.in);
		//print command to ask for Subject Area
	    	System.out.println("Enter a Subject Area (B-H) or Special Subject Area (WRT, EUR, NWC, QNT, ETH)");
		//scan for input
	    	area = scanner.next();
	   	//List HELP information
	    	if (area.equals("HELP")) {
	    		System.out.println("General/Special Subject Area Inputs:");
	    		System.out.println("Input = Course List Result");
	    		System.out.println("B = Area B Courses");
	    		System.out.println("C = Area C Courses");
	    		System.out.println("D = Area D Courses");
	    		System.out.println("E = Area E Courses");
	    		System.out.println("F = Area F Courses");
	    		System.out.println("G = Area G Courses");
	    		System.out.println("H = Area H Courses");
	    		System.out.println("WRT = Writing Courses");
	    		System.out.println("EUR = European Courses");
	    		System.out.println("NWC = World Cultures Courses");
	    		System.out.println("QNT = Quantitative Courses");
	    		System.out.println("ETH = Ethnicity Courses");
	    	}
	    	else {
		//pass area and get area courses
	    		System.out.println(
	    			"Enter a specific department, or NO. Enter SUBJECTS for examples.");
	    		department = scanner.next();

	    		if (department.equals("SUBJECTS")) {
	    			System.out.println("Subject Inputs: TODO");	
	    			//TODO Scrape and add list of department translations from:
	    			//http://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/subj_area_trans.aspx
	    		}
	    		else if (department.equals("NO")) {
	    			list = getCourses(area);
	    		}
	    		else {
	    			list = getSpecificCourses(area, department);
	    		}
	    		if (list.size() == 0) {
	    		 // Incorrect department abbreviation error will be handled when scraper method
	    		 // for department translations is implemented
	    		 System.out.println("No courses fulfill selected area and requirement OR Incorrect Subject Abbreviation"); 
	    		}

	    		for(int i=0; i<list.size(); i++){ System.out.println(list.get(i)); }
	    	}

	    // Prompts user to Scrape again
	    System.out.println("Scrape? Y/N");
	    area = scanner.next();
	    if (area.equals("N")) { loop = false; }
	    }

	}//end main

}//end GEscraper
