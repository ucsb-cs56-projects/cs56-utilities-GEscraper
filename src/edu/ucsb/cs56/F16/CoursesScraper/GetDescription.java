package edu.ucsb.cs56.F16.CourseScraper;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class GetDescription {

	public static String getCourseDescription(String course) {

		String[] words = course.split(" - ");
		String[] words2 = words[0].split(" [0-9]+");
		String area = words2[0];
		String courseId = words[0] + ".";
		AreaUrlMappingTable table = new AreaUrlMappingTable();
		String url = table.getUrl(area);
		Document doc=Jsoup.parse("");
		try{
			doc = Jsoup.connect(url).get();
		}
		//Catch bad URL
		catch (MalformedURLException e) {
			System.out.println("Area does not exist.");
			return "Course description not found";
		}
		//catch IOException
		catch (IOException e) {
			System.out.println("Check Internet.");
			return "Course description not found";
		}
		Elements content = doc.getElementsContainingText(courseId);
		if(content.size() == 0)
			return "Course description not found";
		else{
			Element panel = content.get(content.size() - 2);
			Elements des = panel.select("div[style=padding-left: 2px;]");
			if(des.size() == 0)
				return "Course description not found";
			else
				return des.get(1).html();			
		}
	}

}

