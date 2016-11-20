package edu.ucsb.cs56.F16.CourseScraper;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GetGeInfoGUI extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JList list;
    private JButton button;
    private JTextArea text;

    public GetGeInfoGUI(String[] data) {

        // set flow layout for the frame
        this.getContentPane().setLayout(new FlowLayout());
        button = new JButton("Check");
        list = new JList(data);
        text = new JTextArea("Coures Description",5, 10);
        button.addActionListener(this);

        add(new JScrollPane(list));
        add(button);
        add(new JScrollPane(text));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Check")) {
            
            String s = (String) list.getSelectedValue();
            System.out.println("Value Selected: " + s);
            text.setText(s);
        }
    }

    public static void createAndShowGUI(String[] data) {

        //Create and set up the window.
        JFrame frame = new GetGeInfoGUI(data);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}