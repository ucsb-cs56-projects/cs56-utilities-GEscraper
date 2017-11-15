package edu.ucsb.cs56.F16.CourseScraper;

import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.util.*;

import javax.swing.*;

public class EngGUI extends JPanel{

  private JList list;
  private JScrollPane courseList;
  private JTextArea text;
  private JComboBox departmentDropdown;
  private JButton getDescriptionButton;

  public EngGUI(){
    String[] departments = {"Chemical Engineering", "Computer Engineering", "Computer Science - Engineering",
                            "Electrical and Computer Engineering", "Engineering Sciences", "Materials",
                            "Mechanical Engineering", "Technology Management Program"};

    departmentDropdown = new JComboBox(departments);
    JButton searchButton = new JButton("SEARCH");
    searchButton.addActionListener(new searchButtonListener());

    // set box layout for the frame
    this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    //set top part
    JPanel searchPane = new JPanel();
    searchPane.setLayout(new BoxLayout(searchPane, BoxLayout.LINE_AXIS));
    searchPane.add(departmentDropdown);
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

    foundPane.add(Box.createRigidArea(new Dimension(30, 0)));
    foundPane.add(courseList);
    foundPane.add(getDescriptionButton);
    foundPane.add(new JScrollPane(text));
    foundPane.add(Box.createRigidArea(new Dimension(30, 0)));

    //Add components to main panel
    add(searchPane);
    add(foundPane);
    setSize(800, 400);

    setVisible(true);
  }

  class searchButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      String selected = (String) departmentDropdown.getSelectedItem();

      String dept = "";
      switch(selected){
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
      list.setListData(data);

      courseList.setViewportView(list);
      text.setText("");
    }
  }

  class descriptionButtonListener implements ActionListener{
    public void actionPerformed(ActionEvent event){
      String s = (String) list.getSelectedValue();

      System.out.println("Value Selected: " + s);
      text.setText(GetDescription.getEngCourseDescription(s));
    }
  }

  //Do scraping
}
