package edu.ucsb.cs56.F16.CourseScraper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Component;

import javax.swing.*;

public class ScraperGUI extends JFrame {

    private static final long serialVersionUID = 1L;

    public ScraperGUI() {
      this.setTitle("UCSB Course Finder");
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));

      JPanel buttonPane = new JPanel();
      buttonPane.setLayout(new BoxLayout(buttonPane, BoxLayout.LINE_AXIS));

      JLabel choices = new JLabel("Choose which area to display classes from");
      choices.setAlignmentX(Component.CENTER_ALIGNMENT);

      JButton engBtn = new JButton("Engineering");
      engBtn.addActionListener(new EngListener());
      JButton genBtn = new JButton("General Education");
      genBtn.addActionListener(new GenListener());

      buttonPane.add(genBtn);
      buttonPane.add(Box.createRigidArea(new Dimension(20, 0)));
      buttonPane.add(engBtn);

      add(Box.createRigidArea(new Dimension(0, 130)));
      add(choices);
      add(Box.createRigidArea(new Dimension(0, 20)));
      add(buttonPane);

      this.setSize(800, 420);
      this.setVisible(true);
    }

    private class EngListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        getContentPane().removeAll();
        add(new EngGUI());
        add(Box.createRigidArea(new Dimension(0, 30)));
        getContentPane().repaint();
        validate();
      }
    }

    private class GenListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
        getContentPane().removeAll();
        add(new GeGUI());
        getContentPane().repaint();
        validate();
      }
    }
}
