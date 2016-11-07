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

public class GetEngInfo {
	

	public static final String urlPrefix = "https://my.sa.ucsb.edu/catalog/Current/CollegesDepartments/coe/";
	public static final String urlSuffix = ".aspx?DeptTab=Courses";

	/** create specific url for area of general ed
		@param Specific area "B", "C", "D", "E", "F", "G", "H"
		@return url for specified general area courses
	*/
	private static String getDeptCoursesURL(String dept) {
   		String url = urlPrefix + dept + urlSuffix;
    	return url;
	}

	/** Get all departments for ease searching
	  @return Arraylist of ArrayList holding the course acronym and course name 
	*/
	public static ArrayList<String> getDepartments(){

		String url = "https://my.sa.ucsb.edu/catalog/Current/CollegesDepartments/coe/AcademicDepartments.aspx";
	

		ArrayList<String> outer = new ArrayList<String>();
		
		try {
		    Document doc = Jsoup.connect(url).get(); 
		    Element departments = doc.getElementById("ctl00_ctl00_iisChildren_itemsInSectionContainer"); 
		    Elements dept_list = departments.getElementsByTag("a");
		    for(Element e : dept_list){
		    	outer.add(e.html());
		    }
		                         
		} catch(MalformedURLException e) {
		    System.out.println("Seems like the subject URL has moved");
			System.exit(1);
			
		} catch(IOException e){
		    System.out.println("Check yo internet yo!");
			
		}
		return outer;
	}

	/** get course numbers of gen/special subject courses in a given area
	  @param area as B->H or Special Subject Abbreviation: "WRT", "EUR", "NWC", "QNT", "ETH"
	  @return array list of all courses with { Course Abbreviation, Course Number, Full Course Name }
	*/
	public static ArrayList<String> getCourses(String area) {

		// Check if Gen Ed Course or Special Subject Ed Course
		String url = getDeptCoursesURL(area);

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

		Element container = doc.getElementById("ctl00_ctl00_ContentPlaceHolder1_ctl00_divLowerDivision");
		Elements course_list = container.getElementsByTag("b");
		for(int i = 0; i < course_list.size(); i += 2){
			courses.add(course_list.get(i).html());
		}
		
		Element containerupper = doc.getElementById("ctl00_ctl00_ContentPlaceHolder1_ctl00_divUpperDivision");
		Element containergrad = doc.getElementById("ctl00_ctl00_ContentPlaceHolder1_ctl00_divGrad");
		Elements course_list2 = containerupper.getElementsByTag("b");
		Elements course_list_grad = containergrad.getElementsByTag("b");
		course_list2.addAll(course_list_grad);
		String tmp = "";
		for(int i = 0; i < course_list2.size(); i ++){
			Element e = course_list2.get(i);
			// tmp = e.html();
			tmp=e.html().replaceAll("<span class=\"CourseIdAndTitle\"> ", "");
			tmp=tmp.replaceAll("<span class=\"CourseFullTitle\" name=\"CourseDisplay\">", "");
			tmp=tmp.replaceAll("</span>", "");
			tmp=tmp.replaceAll("<span class=\"CourseFullTitle\" name=\"CourseChildDisplay\">","");
			courses.add(tmp);
		}

		return courses;
	}
	
    
	
}//end GetInfo

