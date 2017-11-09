package edu.ucsb.cs56.F16.CourseScraper;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.awt.FlowLayout;


/** GEscraper Class takes classes from a URL and displays them in a list.
    @author Brent Kirkland
    @author Dylan Lynch
    @version 03.04.15
*/

public class CourseScraper {
	//main
	public static void main(String args[]){
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());

		frame.setVisible(true);
		boolean loop = true;
		ArrayList<String> list = new ArrayList<String>();
		String area, department;
		GetGeInfo getgeinfo = new GetGeInfo();
		while (loop == true) {

		    Scanner areaScanner = new Scanner(System.in);
		    Scanner departmentScanner = new Scanner(System.in);

		    Object[] options = {"Engineering",
                    "General Subject"};
			int engrOrGe = JOptionPane.showOptionDialog(null,
			    "Choose to display courses from engineering or general subject",
			    "Menu",
			    JOptionPane.YES_NO_OPTION,
			    JOptionPane.QUESTION_MESSAGE,
			    null,
			    options,
			    options[0]);
			if(engrOrGe==JOptionPane.YES_OPTION){
			    String[] departments={"Chemical Engineering","Computer Engineering", "Computer Science - Engineering", "Electrical and Computer Engineering", "Engineering Sciences", "Materials", "Mechanical Engineering", "Technology Management Program"};
			    department = (String) JOptionPane.showInputDialog(null, "Enter a Department Name:", "Menu", JOptionPane.QUESTION_MESSAGE, null, departments, departments[0]);
				String dept = "";
				switch(department){
					case "Chemical Engineering": dept = "chemengr";break;
					case "Computer Engineering": dept = "compengr";break;
					case "Computer Science - Engineering": dept = "compsci-engr";break;
					case "Electrical and Computer Engineering": dept = "ece";break;
					case "Engineering Sciences": dept = "engrsci";break;
					case "Materials": dept = "matrl";break;
					case "Mechanical Engineering": dept = "mechengr";break;
					case "Technology Management Program": dept = "tmp";break;
				}
				ArrayList<String> courses = GetEngInfo.getCourses(dept);

				String[] data = courses.toArray(new String[courses.size()]);
			    javax.swing.SwingUtilities.invokeLater(new Runnable() {
            		public void run() {
                		JPanel engGUI = new EngGUI();

										frame.add(engGUI);
										engGUI.setVisible(true);
            		}
        		});

			    System.out.println("Scrape? Y/N");
			    area = areaScanner.next();
			    if (area.equals("N")) { loop = false; }
			}
			else if(engrOrGe==JOptionPane.NO_OPTION){
				//System.out.println("Enter a Subject Area (B-H or WRT, EUR, NWC, QNT, ETH) or enter HELP for a list of all areas and courses");
			    //area = areaScanner.next();
			    String[] choices={"B", "C", "D", "E", "F", "G", "H", "WRT", "EUR", "QNT", "NWC", "ETH"};
			    // JOptionPane: pop up a standard dialog box
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

					ArrayList<ArrayList<String>> departments = getgeinfo.getDepartments();
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
					list = getgeinfo.getCourses(area);
				}
				else {
					list = getgeinfo.getSpecificCourses(area, department);
			    }

			    String[] data = list.toArray(new String[list.size()]);


               	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            		public void run() {
                		GetGeInfoGUI.createAndShowGUI(data);
            		}
        		});

			    System.out.println("Scrape? Y/N");
			    area = areaScanner.next();
			    if (area.equals("N")) { loop = false; }
			}
		}
	}//end main

}//end GEscraper
