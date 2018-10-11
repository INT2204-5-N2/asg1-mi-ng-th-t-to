/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Huu Thanh
 */
public final class EditWindow extends JFrame implements ActionListener {
    private JButton jbOk;
    private JButton jbCancel;
    private JTextField jtxtVietnamese;
    private JTextField jtxtEnglish;
    private JLabel jlVietnamese;
    private JLabel jlEnglish;
    public EditWindow()
    {
        Init();
    }
    public void Init()
    {
        
        jbOk=new JButton("OK");
        jbCancel=new JButton("Cancel");
        jtxtVietnamese=new JTextField();
        jtxtEnglish=new JTextField();
        jlVietnamese=new JLabel("Vietnamese");
        jlEnglish=new JLabel("English");
        
        //Set Bounds
//        this.getContentPane().add(jbOk);
//        this.getContentPane().add(jbCancel);
        this.jbCancel.setBounds(400, 400, 80, 20);
        this.jbOk.setBounds(200, 400, 80, 20);
        this.jlEnglish.setBounds(200, 10, 80, 20);
        this.jlVietnamese.setBounds(400, 10, 80, 20);
        this.jtxtEnglish.setBounds(10, 40, 300, 330);
        this.jtxtVietnamese.setBounds(360, 40, 300, 330);
        
        this.add(this.jbOk);
        this.add(this.jbCancel);
        this.add(this.jlEnglish);
        this.add(this.jlVietnamese);
        this.add(this.jtxtEnglish);
        this.add(this.jtxtVietnamese);
        
        //set Frame
        this.setTitle("Edit Word");
        this.setLayout(null);
        this.setSize(700, 500);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
    }
    public static void main(String []argv)
    {
        EditWindow a=new EditWindow();
        a.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton jbAction=(JButton) e.getSource();
        if(jbAction==jbOk)
        {
        }
    }
}
