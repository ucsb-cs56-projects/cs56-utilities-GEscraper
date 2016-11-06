package edu.ucsb.cs56.W15.GEscraper;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

/** GEscraper Class takes classes from a URL and displays them in a list.
    @author Brent Kirkland 
    @author Dylan Lynch 
    @version 03.04.15
*/

public class GetInfo {
	

	public static final String urlPrefix = 
		"https://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/";
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
		ArrayList<String> areaCourses = GetInfo.getCourses(area);
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
		
		//JSOUP Version
		Document doc=Jsoup.parse("");
		try{
		    doc = Jsoup.connect(url).get();
		    
		}
		//Catch bad URL
  		catch (MalformedURLException e) {
      		System.out.println("Area does not exist.");
      		System.exit(1);
  		}
		//catch IOException
		catch (IOException e) {
      		System.out.println("Check Internet.");
      		System.exit(1);
  		}

		Elements p=doc.getElementsByTag("p");
		String tmp="";
		for(Element e : p){

		    tmp=e.html().replaceAll("<i>", "");
		    tmp=tmp.replaceAll("</i>", "");
		    courses.add(tmp);
		}
		
		return courses;
	}
	

	/** Get all departments for ease searching
	  @return Arraylist of ArrayList holding the course acronym and course name 
	*/
	public static ArrayList<ArrayList<String>> getDepartments(){
		//url for all subjects
		String url = "http://my.sa.ucsb.edu/catalog/current/UndergraduateEducation/subj_area_trans.aspx";
	
		//Create an ArrayList of Arraylist to store values
		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();

		//empty string to capture each line
		String contents = "";
		
		try {
		    Document doc = Jsoup.connect(url).get(); 
		    Elements departments = doc.getElementsByTag("p"); 
		    Element body = departments.first();
		    contents = body.html();  
		    String dp[] = contents.split("<br>"); 
		    //now we have an array of "Anthropology - ANTH" 
		    //go through each string and split them again
		    for(int i = 0; i < dp.length; i++){
			ArrayList<String> inner = new ArrayList<String>(); 
			String[] splitThisPart = dp[i].split(" - "); 
			inner.add(splitThisPart[0]);
			inner.add(splitThisPart[1]); 
			outer.add(inner); 
		    }
		                         
		} catch(MalformedURLException e) {
		    System.out.println("Seems like the subject URL has moved");
			System.exit(1);
			
		} catch(IOException e){
		    System.out.println("Check yo internet yo!");
			
		}
		return outer;
	}
    
	
}//end GetInfo
