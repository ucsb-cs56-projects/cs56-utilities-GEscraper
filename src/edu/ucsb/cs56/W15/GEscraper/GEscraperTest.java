package edu.ucsb.cs56.W15.GEscraper;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import java.net.*;


/** test class for GEscraper

@author Brian Wan
@version 2013.06.07
@see GEscraper

*/

public class GEscraperTest {

    /** Test for getAreaCourses
	@see GEscraper
    */
    @Test
    public void test_getAreaCourses(){
	GEscraper g = new GEscraper();
       	ArrayList<String> s = g.getAreaCourses("B");
       	String a = s.get(0);
	String b = "CHIN      3";
      	assertEquals(a, b);
    }
    
}
