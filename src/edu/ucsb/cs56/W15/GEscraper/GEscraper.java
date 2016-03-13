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

public class GEscraper {
	

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
 *  @return Arraylist of ArrayList holding the course acronym and course name 
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
    
    //main	
    public static void main(String args[]){
	boolean loop = true;
	ArrayList<String> list = new ArrayList<String>();
	String area, department;

	

	
	while (loop == true) {
	    Scanner areaScanner = new Scanner(System.in);
	    Scanner departmentScanner = new Scanner(System.in);
	    
	    //System.out.println("Enter a Subject Area (B-H or WRT, EUR, NWC, QNT, ETH) or enter HELP for a list of all areas and courses");
	    //area = areaScanner.next();
	    String[] choices={"B", "C", "D", "E", "F", "G", "H", "WRT", "EUR", "QNT", "NWC", "ETH"};
	    area = (String) JOptionPane.showInputDialog(null, "Enter a Subject Area:", "Menu", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
	    System.out.println(area);
	    
	    if (area.equals("HELP")) {
		System.out.println("General/Special Subject Area Inputs:");
		System.out.println("Input = Course List Result");
		System.out.println("Area B Courses = B");
		System.out.println("Area C Courses = C");
		System.out.println("Area D Courses = D");
		System.out.println("Area E Courses = E");
		System.out.println("Area F Courses = F");
		System.out.println("Area G Courses = G");
		System.out.println("Area H Courses = H");
		System.out.println("Writing Courses = WRT");
		System.out.println("European Courses = EUR");
		System.out.println("World Cultures Courses = NWC");
		System.out.println("Quantitative Courses = QNT");
		System.out.println("Ethnicity Courses = ETH");
		
		ArrayList<ArrayList<String>> departments = getDepartments();
		ArrayList<String> departmentt;
		for (int i = 0; i < departments.size(); i++) {
		    departmentt = departments.get(i);
		    System.out.println(departmentt.get(0) + " = " + departmentt.get(1));
		}
		
		//System.out.println("Enter a Subject Area: ");
		
		
		
		//area = areaScanner.next();
	    }
	    
	    //System.out.println("Enter a specific department abbrevation or N (No): ");
	    
	    
	    
	    department = (String) JOptionPane.showInputDialog("Enter a specific department abbrevation or N (No): ");
	    
	    //department = departmentScanner.next();
	    
	    
	    if (department.equals("N")) {
		list = getCourses(area);
	    }
	    else { 
		list = getSpecificCourses(area, department);
		
	    }
	    
	    //for(int i=0; i<list.size(); i++){ System.out.println(list.get(i)); }
	    JFrame f = new JFrame("RESULTS");
	    f.setSize(400, 800);
	    
	    String[] data = list.toArray(new String[list.size()]);
	    f.add(new JScrollPane(new JList(data)));
	    
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setLocationRelativeTo(null);
	    f.setVisible(true);
	    
	    System.out.println("Scrape? Y/N");
	    area = areaScanner.next();
	    if (area.equals("N")) { loop = false; }
	    
	}	    
	
	
	
	
    }//end main
    
}//end GEscraper
