/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

//import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketOption;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.*;

/**
 *
 * @author Huu Thanh
 */
public class DictionaryWindows extends JFrame implements ActionListener {

    //const
    public static final int JPANEL_LEFT_X = 0;
    public static final int JPANEL_LEFT_Y = 50;
    public static final int JPANEL_LEFT_WIDTH = 345;
    public static final int JPANEL_LEFT_HEIGHT = 650;
    public static final int JPANEL_RIGHT_X = 355;
    public static final int JPANEL_RIGHT_Y = 50;
    public static final int JPANEL_RIGHT_WIDTH = 345;
    public static final int JPANEL_RIGHT_HEIGHT = 650;

    private JPanel jpnRight;
    private JPanel jpnLeft;
    private JFrame jFrame;
    private JButton jbAdd;
    private JButton jbEdit;
    private JButton jbDelete;
    private JButton jbSearch;
    private JTextField jtxtSearch;
    private JCheckBox jcbNote;
    private JList jlWord;
    private JTextArea JtaMeanning;
    ArrayList<String> ListWord;
    private AddWindow addDic=null;
    private EditWindow EdiDic=null;

    public DictionaryWindows() {
        Init();
    }

    public void Init() {
        this.CreatAndSetProperties();
        this.setLayout(null);
//        this.jpnLeft.setLayout(null);
//        this.jpnRight.setLayout(null);
        //Set panel
        this.jpnLeft.setBackground(Color.BLUE);
        this.jpnLeft.setBounds(JPANEL_LEFT_X, JPANEL_LEFT_Y, JPANEL_LEFT_WIDTH, JPANEL_LEFT_HEIGHT);
        this.jpnRight.setBackground(Color.GRAY);
        this.jpnRight.setBounds(JPANEL_RIGHT_X, JPANEL_RIGHT_Y, JPANEL_RIGHT_WIDTH, JPANEL_RIGHT_HEIGHT);
        //Add in Frame
//        this.getContentPane().add(this.jpnLeft);
//        this.getContentPane().add(this.jpnRight);

        this.add(this.jlWord);
        this.jlWord.setBounds(10, 130, 320, 350);
        this.add(this.jtxtSearch);
        this.jtxtSearch.setBounds(125, 70, 100, 25);
        this.add(this.jbSearch);
        this.jbSearch.setBounds(130, 30, 80, 25);
        this.add(this.JtaMeanning);
        this.JtaMeanning.setBounds(365, 100, 320, 400);
        this.add(this.jcbNote);
        this.jcbNote.setBounds(650, 70, 80, 20);
        this.add(this.jbAdd);
        this.jbAdd.setBounds(360, 70, 80, 20);
        this.add(this.jbDelete);
        this.jbDelete.setBounds(460,70 , 80, 20);
        this.add(this.jbEdit);
        this.jbEdit.setBounds(560, 70, 80, 20);

        //Set action for button
        this.jbAdd.setActionCommand("Add");
        this.jbAdd.addActionListener(this);
        this.jbEdit.setActionCommand("Edit");
        this.jbEdit.addActionListener(this);
        this.jtxtSearch.setActionCommand("JTxtSearch");
        this.jtxtSearch.addActionListener(this);

        //this.AddAction();
        //Set Frame
        this.setTitle("Dictionary");
        this.setSize(700, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
    public void CreatAndSetProperties()
    {
        this.jbAdd = new JButton("Add");
        this.jbDelete = new JButton("Delete");
        this.jbEdit = new JButton("Edit" );
        this.jbSearch = new JButton("Search");
        this.jtxtSearch = new JTextField();
        this.jtxtSearch.setColumns(10);
        this.jcbNote = new JCheckBox("Note");
        this.JtaMeanning = new JTextArea();
        this.jlWord = new JList();
        this.jpnLeft = new JPanel();
        this.jpnRight = new JPanel();

    }
    public void AddAction()
    {
        this.jbEdit.addActionListener(this);
        this.jbAdd.addActionListener(this);
        this.jbDelete.addActionListener(this);
        this.jbSearch.addActionListener(this);
        this.jtxtSearch.addActionListener(this);
        //this.jlWord.addAncestorListener();

    }
    public void LoadWord() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DictionaryWindows Diction = new DictionaryWindows();
        Diction.setVisible(true);
//        ActionEvent a;
//        a = new ActionEvent();
//        this.actionPerformed(a);
        System.out.println("succcess");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if("Add".equals(e.getActionCommand()))
        {
            if (addDic==null)
            {
                addDic = new AddWindow();

            }
            addDic.setVisible(true);


        }
        else if("Edit".equals(e.getActionCommand()))
        {
            if(EdiDic==null)
            {
                EdiDic=new EditWindow();
            }
            EdiDic.setVisible(true);

        }
        else if("JTxtSearch".equals(e.getActionCommand()))
        {
            jtxtSearch.setText(new Scanner(System.in).nextLine());
            String a=jtxtSearch.getText();
            System.out.println(a);
        }
//        else if(jbAction==jbSearch)
//        {
//
//        }
    }
}
