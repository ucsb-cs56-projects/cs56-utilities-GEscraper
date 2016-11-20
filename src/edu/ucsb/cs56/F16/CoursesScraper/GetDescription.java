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
		String[] words = course.split("[\\s\\.]+");
		String area = words[0];
		String courseId = area;
		try{
			int number = Integer.parseInt(words[1]);
			courseId = courseId + " " + words[1] + '.';
		}
		catch (NumberFormatException e){
			area = area + " " + words[1];
			courseId = area + " " + words[2] + '.';
		}
		AreaUrlMappingTable table = new AreaUrlMappingTable();
		String url = table.getUrl(area);
		Document doc=Jsoup.parse("");
		try{
			doc = Jsoup.connect(url).get();
		}
		//Catch bad URL
		catch (MalformedURLException e) {
			System.out.println("Area does not exist.");
			// System.exit(1);
		}
		//catch IOException
		catch (IOException e) {
			System.out.println("Check Internet.");
			// System.exit(1);
		}
		Elements content = doc.getElementsContainingText(courseId);
		Element panel = content.get(content.size() - 2);
		Elements des = panel.select("div[style=padding-left: 2px;]");
		return des.get(1).html();
	}

}

