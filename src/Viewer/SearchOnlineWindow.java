package Viewer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchOnlineWindow extends JFrame implements ActionListener {
    JButton jbSearch;
    JButton jbExit;
    JTextField jtxtSearch;
    JTextArea jtaMeanning;
    public SearchOnlineWindow()
    {
        Init();
    }
    public void SetBounds()
    {

    }
    public void Init()
    {
        //new
        jbExit=new JButton("Edit");
        jbSearch=new JButton("Search");
        //Set Frame
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setbounds
        this.SetBounds();
        //setaction
        this.SetAction();
    }
    public void SetAction()
    {

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
