package edu.ucsb.cs56.F16.CourseScraper;

import java.util.*;


public class AreaUrlMappingTable {
	private Map table_GE = new HashMap<String,String>();
	private Map table_Eng = new HashMap<String,String>();
	private final String urlPrefix_GE = "https://my.sa.ucsb.edu/catalog/Current/CollegesDepartments/ls-intro/";
	private final String urlPrefix_Eng = "https://my.sa.ucsb.edu/catalog/Current/CollegesDepartments/coe/";
	private final String urlSuffix = ".aspx?DeptTab=Courses";
	AreaUrlMappingTable(){
		table_GE.put("ANTH","anth");
		table_GE.put("ART","art");
		table_GE.put("ARTHI","arthi");
		table_GE.put("AS AM","asam");
		table_GE.put("ASTRO","phys");
		table_GE.put("BL ST","blst");
		table_GE.put("CH ST","chst");
		table_GE.put("C LIT","complit");
		table_GE.put("CHEM","chem");
		table_GE.put("CHIN","ealcs");
		table_GE.put("CLASS","class");
		table_GE.put("CMPSC","compsci-engr");
		table_GE.put("COMM","comm");
		table_GE.put("DANCE","thtda");
		table_GE.put("EACS","ealcs");
		table_GE.put("EARTH","earthsci");
		table_GE.put("EARTHW","earthsci");
		table_GE.put("ECON","econ");
		table_GE.put("EEMB","eemb");
		table_GE.put("ENGL","engl");
		// table_GE.put("ENV","");
		table_GE.put("ENV S","envst");
		// table_GE.put("FAMST","");
		table_GE.put("FEMST","femst");
		table_GE.put("FR","frit");
		table_GE.put("GEOG","geog");
		table_GE.put("GER","gersl");
		table_GE.put("GLOBL","gis");
		table_GE.put("GREEK","class");
		// table_GE.put("HEB","");
		table_GE.put("HIST","hist");
		table_GE.put("ITAL","frit");
		table_GE.put("JAPAN","ealcs");
		table_GE.put("KOR","ealcs");
		table_GE.put("LAIS","lais");
		table_GE.put("LATIN","class");
		table_GE.put("LING","ling");
		table_GE.put("MATH","math");
		table_GE.put("MATRL","matrl");
		table_GE.put("MCDB","mcdb");
		table_GE.put("MUS","music");
		// table_GE.put("MS","");
		table_GE.put("PHIL","phil");
		table_GE.put("PHYS","phys");
		table_GE.put("POL S","polsci");
		table_GE.put("PORT","spanport");
		table_GE.put("PSTAT","stats");
		table_GE.put("PSY","psych");
		table_GE.put("RG ST","relst");
		// table_GE.put("SHS","");
		// table_GE.put("SLAV","");
		table_GE.put("SOC","soc");
		table_GE.put("SPAN","spanport");
		table_GE.put("THTR","thtda");

		table_Eng.put("CH E", "chemengr");
		table_Eng.put("CMPSC", "compsci-engr");
		table_Eng.put("ECE", "ece");
		table_Eng.put("ENGR", "engrsci");
		table_Eng.put("MATRL", "matrl");
		table_Eng.put("ME", "mechengr");
		table_Eng.put("TMP", "tmp");
	}
	public String getUrl_GE(String area){
		return urlPrefix_GE + (String)table_GE.get(area) + urlSuffix;
	}
	public String getUrl_Eng(String area){
		return urlPrefix_Eng + (String)table_Eng.get(area) + urlSuffix;
	}
}