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
  String[] departments;

  public GeGUI(){
    try {
      cssc = new UCSBCurriculumSearch();
      //Arrays for dropdown menus
      String[] choices = {"B", "C", "D", "E", "F", "G", "H", "WRT", "EUR", "QNT", "NWC", "ETH"};
      tb = new AreaUrlMappingTable();
      Map areas = tb.getGEAreas();
      departments = new String[areas.size()+1];
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
      public ArrayList<String> removeExcess(ArrayList<String> courses){
        ArrayList<String> newArr = new ArrayList<String>();

        for (int i = 0; i < courses.size(); i++){
          String title = courses.get(i).substring(0,courses.get(i).indexOf('-'));
          title.trim();
          String newTitle = "";
          for (int j = 0; j < title.length(); j++){
            if(title.charAt(j) != ' '){
              newTitle += title.charAt(j);
            }
          }
          newArr.add(newTitle);
        }

        return newArr;
      }

      public ArrayList<String> removeWhitespace(ArrayList<UCSBLecture> lectureList) {
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

        return lectureTitles;
      }

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

        ArrayList<String> newCourseTitles = removeExcess(courses);
        //search with the corresponding selections in the gui
        ArrayList<String> lectureTitles = new ArrayList<String>();
        try {
          if(!department.equals("None")){
            cssc.loadCourses(department, qtr, lev);
            lectureTitles = removeWhitespace(cssc.getLectures());
          }
          else{
            for(int i = 0; i < departments.length; i++){
              department = departments[i];
              System.out.println("Searching Department: " + department);
              cssc.loadCourses(department, qtr, lev);
              lectureTitles.addAll(removeWhitespace(cssc.getLectures()));
            }
          }
          cssc.printLectures();

          //CREATE LIST OF COURSE TITLES RETURNED

          //MATCH COURSE TITLES TO GET FINAL LIST TO DISPLAY
          ArrayList<String> toDisplay = new ArrayList<String>();
          for(int i = 0; i < newCourseTitles.size(); i++) {
            if(lectureTitles.indexOf(newCourseTitles.get(i)) != -1) {
              toDisplay.add(courses.get(i));
            }
          }

          String[] data = toDisplay.toArray(new String[toDisplay.size()]);
          System.out.println(data[0]);
          list.setListData(data);

          courseList.setViewportView(list);
          text.setText("");
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
