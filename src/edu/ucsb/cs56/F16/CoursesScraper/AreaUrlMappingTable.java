package edu.ucsb.cs56.F16.CourseScraper;

import java.util.*;


public class AreaUrlMappingTable {
	private Map table = new HashMap<String,String>();
	private final String urlPrefix = "https://my.sa.ucsb.edu/catalog/Current/CollegesDepartments/ls-intro/";
	private final String urlSuffix = ".aspx?DeptTab=Courses";
	AreaUrlMappingTable(){
		table.put("ANTH","anth");
		table.put("ART","art");
		table.put("ARTHI","arthi");
		table.put("AS AM","asam");
		table.put("ASTRO","phys");
		table.put("BL ST","blst");
		table.put("CH ST","chst");
		table.put("C LIT","complit");
		table.put("CHEM","chem");
		table.put("CHIN","ealcs");
		table.put("CLASS","class");
		table.put("CMPSC","compsci-engr");
		table.put("COMM","comm");
		table.put("DANCE","thtda");
		table.put("EACS","ealcs");
		table.put("EARTH","earthsci");
		table.put("EARTHW","earthsci");
		table.put("ECON","econ");
		table.put("EEMB","eemb");
		table.put("ENGL","engl");
		// table.put("ENV","");
		table.put("ENV S","envst");
		// table.put("FAMST","");
		table.put("FEMST","femst");
		table.put("FR","frit");
		table.put("GEOG","geog");
		table.put("GER","gersl");
		table.put("GLOBL","gis");
		table.put("GREEK","class");
		// table.put("HEB","");
		table.put("HIST","hist");
		table.put("ITAL","frit");
		table.put("JAPAN","ealcs");
		table.put("KOR","ealcs");
		table.put("LAIS","lais");
		table.put("LATIN","class");
		table.put("LING","ling");
		table.put("MATH","math");
		table.put("MATRL","matrl");
		table.put("MCDB","mcdb");
		table.put("MUS","music");
		// table.put("MS","");
		table.put("PHIL","phil");
		table.put("PHYS","phys");
		table.put("POL S","polsci");
		table.put("PORT","spanport");
		table.put("PSTAT","stats");
		table.put("PSY","psych");
		table.put("RG ST","relst");
		// table.put("SHS","");
		// table.put("SLAV","");
		table.put("SOC","soc");
		table.put("SPAN","spanport");
		table.put("THTR","thtda");
	}
	public String getUrl(String area){
		return urlPrefix + (String)table.get(area) + urlSuffix;
	}
}