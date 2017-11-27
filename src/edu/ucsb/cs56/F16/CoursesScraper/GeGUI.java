package edu.ucsb.cs56.F16.CourseScraper;

import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

import javax.swing.*;

public class GeGUI extends JPanel{

  private JList list;
  private JScrollPane courseList;
  private JTextArea text;
  private JComboBox subjectDropdown;
  private JComboBox departmentDropdown;
  private JComboBox quarterDropdown;
  private JButton getDescriptionButton;
  private AreaUrlMappingTable tb;
  private UCSBCurriculumSearch cssc;

  public GeGUI(){
    try {
      cssc = new UCSBCurriculumSearch();
      //Arrays for dropdown menus
      String[] choices = {"B", "C", "D", "E", "F", "G", "H", "WRT", "EUR", "QNT", "NWC", "ETH"};
      tb = new AreaUrlMappingTable();
      Map areas = tb.getGEAreas();
      String[] departments = new String[areas.size()+1];
      departments[0] = "None";
      Iterator it = areas.entrySet().iterator();
      int i = 1;
      while (it.hasNext()) {
        Map.Entry pair = (Map.Entry)it.next();
        departments[i] = (String) pair.getKey();
        it.remove();
        i++;
      }
      Object [] year = cssc.findQuarterAndYear(cssc.getMainPage()).toArray();

      JLabel subjectLabel = new JLabel("Select Subject Area: ");
      subjectDropdown = new JComboBox(choices);
      JLabel departmentLabel = new JLabel("Select Department: ");
      departmentDropdown = new JComboBox(departments);
      JLabel quarterLabel = new JLabel("Select Quarter: ");
      quarterDropdown = new JComboBox(year);

      JButton searchButton = new JButton("SEARCH");
      searchButton.addActionListener(new searchButtonListener());

      // set box layout for the frame
      this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      //set top part
      JPanel searchPane = new JPanel();
      searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.Y_AXIS));
      searchPane.add(subjectLabel);
      searchPane.add(subjectDropdown);
      searchPane.add(departmentLabel);
      searchPane.add(departmentDropdown);
      searchPane.add(quarterLabel);
      searchPane.add(quarterDropdown);
      searchPane.add(searchButton);

      //set bottom part
      JPanel foundPane = new JPanel();
      foundPane.setLayout(new BoxLayout(foundPane, BoxLayout.LINE_AXIS));

      text = new JTextArea("Course Description",9,25);
      text.setLineWrap(true);
      text.setWrapStyleWord(true);
      getDescriptionButton = new JButton("Get Description");
      getDescriptionButton.addActionListener(new descriptionButtonListener());
      list = new JList();
      courseList = new JScrollPane(list);

      foundPane.add(courseList);
      foundPane.add(getDescriptionButton);
      foundPane.add(new JScrollPane(text));

      //Add components to main panel
      add(searchPane);
      add(foundPane);
      setSize(600,600);

      setVisible(true);
    } catch(Exception e) {
      System.out.println(e);
      e.printStackTrace();
    }
  }

    class searchButtonListener implements ActionListener{
      public void actionPerformed(ActionEvent event){
        String subject = (String) subjectDropdown.getSelectedItem();
        String department = (String) departmentDropdown.getSelectedItem();
        ArrayList<String> courses;
        if(department.equals("None")){
          courses = GetGeInfo.getCourses(subject);
        }
        else{
          courses = GetGeInfo.getSpecificCourses(subject, department);
        }

        String[] data = courses.toArray(new String[courses.size()]);
        System.out.println(data[0]);
        list.setListData(data);

        courseList.setViewportView(list);
        text.setText("");

        String year = String.valueOf(quarterDropdown.getSelectedItem()).substring(String.valueOf(quarterDropdown.getSelectedItem()).length()-4, String.valueOf(quarterDropdown.getSelectedItem()).length());
        String quarter = String.valueOf(quarterDropdown.getSelectedItem());
        String quarter2 = "";
        if(quarter.substring(0,2).equals("FA")) {
          quarter2 = "4";
        }
        if(quarter.substring(0,2).equals("WI")) {
          quarter2 = "1";
        }
        if(quarter.substring(0,2).equals("SP")) {
          quarter2 = "2";
        }
        if(quarter.substring(0,2).equals("SU")) {
          quarter2 = "3";
        }

        String lev = "Undergraduate";
        String qtr = year + quarter2;
        //search with the corresponding selections in the gui
        try {
          cssc.loadCourses(department, qtr, lev);
          cssc.printLectures();
          ArrayList<UCSBLecture> lectureList = cssc.getLectures();
          ArrayList<String> lectureTitles = new ArrayList<String>();
          for(UCSBLecture l : lectureList) {
            String title = l.getCourseTitle();
            String titleMin = "";

            for(int i = 0; i < title.length(); i++) { //remove whitespace
              if(title.charAt(i) != ' ') {
                titleMin += title.charAt(i);
              }
            }

            lectureTitles.add(titleMin);
          }
        } catch(Exception e) {
          System.out.println(e);
          e.printStackTrace();
        }
      }
  }

  class descriptionButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      String s = (String) list.getSelectedValue();
      System.out.println("Value Selected: " + s);
      text.setText(GetDescription.getGECourseDescription(s));
    }
  }
}
