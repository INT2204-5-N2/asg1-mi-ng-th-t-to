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
public final class AddWindow extends JFrame implements ActionListener {
    private JButton jbAdd;
    private JButton jbCancel;
    private JTextField jtxtVietnamese;
    private JTextField jtxtEnglish;
    private JLabel jlVietnamese;
    private JLabel jlEnglish;
    public AddWindow()
    {
        Init();
    }
    public  void Init()
    {
        jbAdd=new JButton("Add");
        jbCancel=new JButton("Cancel");
        jtxtEnglish=new JTextField(null);
        jtxtVietnamese=new JTextField(null);
        jlEnglish=new JLabel("English");
        jlVietnamese=new JLabel("Vietnamese");

        //SetBounds
        this.jbCancel.setBounds(400, 400, 80, 20);
        this.jbAdd.setBounds(200, 400, 80, 20);
        this.jlEnglish.setBounds(200, 10, 80, 20);
        this.jlVietnamese.setBounds(400, 10, 80, 20);
        this.jtxtEnglish.setBounds(10, 40, 300, 330);
        this.jtxtVietnamese.setBounds(360, 40, 300, 330);
        //Add in Frame
        this.add(this.jbAdd);
        this.add(this.jbCancel);
        this.add(this.jlEnglish);
        this.add(this.jlVietnamese);
        this.add(this.jtxtEnglish);
        this.add(this.jtxtVietnamese);

        //set Frame
        this.setTitle("Add Word");
        this.setLayout(null);
        this.setSize(700, 500);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args)
    {
        AddWindow Add= new AddWindow();
        Add.setVisible(true);
    }
}