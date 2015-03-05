package edu.ucsb.cs56.W15.GEscraper;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import java.net.*;


/** test class for GEscraper

@author Brian Wan
@author Dylan Lynch
@version 03.04.15
@see GEscraper

*/

public class GEscraperTest {

    /** Test getSpecificCourses with General Course Input
    @see GEscraper
    */
    @Test
    public void test_getSpecificCourses() {
      GEscraper g = new GEscraper();
      // ArrayList with only Area B French courses
      ArrayList<String> f = g.getSpecificCourses("B", "FR");
      // ArrayList with all Area B Courses
      ArrayList<String> a = g.getCourses("B");
      System.out.println("getCourses:         " + a.get(7));
      System.out.println("getSpecificCourses: " + f.get(0));
      assertEquals(f.get(0), a.get(7));
    }

    // /** Test getCourses with General Course Input
    // @see GEscraper
    // */
    // @Test
    // public void test_getCoursesGeneral(){
    //   GEscraper g = new GEscraper();
    //   ArrayList<String> s = g.getCourses("B");

    //   String a = s.get(0);
    //   assertEquals(a, b);
    // }

    // * Test getCourses with Special Course Subject Input"
    // @see GEscraper
    
    // @Test
    // public void test_getCoursesSpecial(){
    //   GEscraper g = new GEscraper();
    //   ArrayList<String> s = g.getCourses("QNT");

    //   String a = s.get(0);
    //   String b = "         ASTRO     1   Basic Astronomy";

    //   assertEquals(a, b);
    // }
    
}
