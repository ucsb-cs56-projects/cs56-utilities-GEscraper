package edu.ucsb.cs56.F16.CourseScraper;

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

public class Test {
	//main	
	public static void main(String args[]){
		GetEngInfo getenginfo = new GetEngInfo();
		// ArrayList<String> departments = getenginfo.getDepartments();
		// for (int i = 0; i < departments.size(); i++) {
		//     System.out.println(departments.get(i));
		// }
		ArrayList<String> courses = getenginfo.getCourses("tmp");
		for (int i = 0; i < courses.size(); i++) {
		    System.out.println(courses.get(i));
		}
	}//end main
    
}