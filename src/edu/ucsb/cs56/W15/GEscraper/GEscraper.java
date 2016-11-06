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
	//main	
	public static void main(String args[]){
		boolean loop = true;
		ArrayList<String> list = new ArrayList<String>();
		String area, department;
		GetInfo getinfo = new GetInfo();
		while (loop == true) {

		    Scanner areaScanner = new Scanner(System.in);
		    Scanner departmentScanner = new Scanner(System.in);
		    
		    //System.out.println("Enter a Subject Area (B-H or WRT, EUR, NWC, QNT, ETH) or enter HELP for a list of all areas and courses");
		    //area = areaScanner.next();
		    String[] choices={"B", "C", "D", "E", "F", "G", "H", "WRT", "EUR", "QNT", "NWC", "ETH"};
		    //JOptionPane: pop up a standard dialog box
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
				
				ArrayList<ArrayList<String>> departments = getinfo.getDepartments();
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
				list = getinfo.getCourses(area);
			}
			else { 
				list = getinfo.getSpecificCourses(area, department);
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
