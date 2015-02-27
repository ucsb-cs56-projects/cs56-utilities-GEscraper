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

    /** Test getCourses with General Course Input
    @see GEscraper
    */
    @Test
    public void test_getCoursesGeneral(){
      GEscraper g = new GEscraper();
      ArrayList<String> s = g.getCourses("B");

      String a = s.get(0);
      String b = "         ANTH      2   Introductory Cultural Anthropology";

      assertEquals(a, b);
    }

    /** Test getCourses with Special Course Subject Input"
    @see GEscraper
    */
    @Test
    public void test_getCoursesSpecial(){
      GEscraper g = new GEscraper();
      ArrayList<String> s = g.getCourses("QNT");

      String a = s.get(0);
      String b = "         ASTRO     1   Basic Astronomy";

      assertEquals(a, b);
    }
    
}
