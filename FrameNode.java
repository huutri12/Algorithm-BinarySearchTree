/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.awt.AWTEventMulticaster;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author ADMIN
 */
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
enum ACTIONNODE{INSERT, UPDATE, DELETE, SEARCH, NLR, LNR, LRN, MIN, MAX, RANDOM}
public class FrameNode extends JFrame{


    JTextField textf= null;
    PanelNode pnNode= null;
    ArrayList <Integer> arr;
    public static ACTIONNODE actionode= ACTIONNODE.INSERT;
    public static int tmp =0;
    public static int upd;
    private boolean flag= true;
    /**
     *
     */
    public FrameNode(){
        super("Binary Search Tree");
        addControl();
        
   //     setIconImage("Downloads\\image1.jpg");
        setSize(1650, 680);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public void addControl(){
        
       
        Container mainPane = getContentPane();
        mainPane.setLayout(new BorderLayout());
//        mainPane.setBackground(Color.white);
        JPanel p = new JPanel();
        JButton tc_but = new JButton("HOME");
        JButton upD_but = new JButton("Update");
        JButton ins_but = new JButton("Insert");
        JButton del_but = new JButton("Delete");
        JButton rad_but = new JButton("Random");
        JButton sea_but = new JButton("Search");
        JButton run_but= new JButton("Run");
        run_but.setBackground(Color.green);
        JComboBox combox = new JComboBox();
        JButton min_but = new JButton("MIN");
        JButton max_but = new JButton("MAX");
        combox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"NLR", "LNR", "LRN"} ));
        
        Font font_tb = new Font("Arial", Font.BOLD, 16);
        JLabel tb = new JLabel("HOME");
        tb.setFont(font_tb);
        tb.setFont(font_tb);
        textf= new JTextField(20);
    //    pnNode = new test();
 //       p1.add(pnNode);
        
        p.add(tc_but);p.add(rad_but);
        p.add(ins_but);
        p.add(del_but);
        p.add(sea_but);
        p.add(upD_but);
        p.add(min_but);
        p.add(max_but);
//       p.add(textf);
//        p.add(run_but);
        p.add(combox);
        
        mainPane.add(p, BorderLayout.NORTH);
        
        JPanel p1 =new JPanel();
        p1.add(tb);
        p1.add(textf);
        p1.add(run_but);
        mainPane.add(p1, BorderLayout.SOUTH);
        tc_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new FrameNode();
            }
        });
        
        max_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode=ACTIONNODE.MAX;
                tb.setText("MAX:");
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        min_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode= ACTIONNODE.MIN;
                tb.setText("MIN:");
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        ins_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode = ACTIONNODE.INSERT;
                tb.setText("INSERT:");
            }
        });
        del_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode = ACTIONNODE.DELETE;
                tb.setText("DELETE:");
            }
        });
        upD_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode = ACTIONNODE.UPDATE;
                tb.setText("UPDATE:");
            }
        });
        rad_but.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode= ACTIONNODE.RANDOM;
                tb.setText("RANDOM:");
            }
        });
        
        combox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = combox.getSelectedIndex();
                switch(n){
                    case 0: actionode=ACTIONNODE.NLR;
                    tb.setText("NLR:");
                    break;
                    case 1: actionode= ACTIONNODE.LNR;
                    tb.setText("LNR:");
                    break;
                    case 2: actionode= ACTIONNODE.LRN;
                    tb.setText("LRN:");
                    break;
                }
                
            }
        });
        
        run_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(actionode== ACTIONNODE.RANDOM){
                    if (!flag) {
                        flag = true;
                    } else {
                        flag = false;
                    }
                            if (!flag) {
                                try {
                                    int n = Integer.valueOf(textf.getText());
                                    System.out.println(n);
                                    pnNode = new PanelNode(n);
                                    pnNode.repaint();
                                    mainPane.add(pnNode, BorderLayout.CENTER);
                                } catch (NumberFormatException x) {
                                    System.out.println(x.toString());
                                    pnNode = new PanelNode(0);
                                    pnNode.repaint();
    //                            pnNode.setBackground(Color.white);
                                    mainPane.add(pnNode, BorderLayout.CENTER);
                                }
                                pnNode.updateUI();
                                textf.setText("");
                            } else {
                                mainPane.remove(pnNode);
                                rad_but.doClick();
                                run_but.doClick();
                            }
                }
                else{
                    try{
                        if(actionode==ACTIONNODE.UPDATE){
                            String s = (String)JOptionPane.showInputDialog(null, "cập nhật node");
                            upd= Integer.valueOf(s);
                            System.out.println(upd);
                        }
                        tmp= Integer.valueOf(textf.getText());
                        RunnableNodePanel run = new RunnableNodePanel(pnNode);
                        Thread th = new Thread(run);
                        pnNode.repaint();
                        th.start();
                        textf.setText("");
                    }
                    catch(NumberFormatException a){
                        if(actionode== ACTIONNODE.MAX|| actionode== ACTIONNODE.MIN|| actionode== ACTIONNODE.NLR|| actionode== ACTIONNODE.LNR|| actionode== ACTIONNODE.LRN){
                            try{
                            
                                RunnableNodePanel run = new RunnableNodePanel(pnNode);
                                Thread th = new Thread(run);
                                pnNode.repaint();
                                th.start();
                            
                            }catch(NullPointerException ex){
                            JOptionPane.showMessageDialog(null,"Mời bạn khởi tạo cây!");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Vui lòng kiểm tra dữ kiện đầu vào");
                        }
                    }catch(NullPointerException ex){
                        JOptionPane.showMessageDialog(null,"Mời bạn khởi tạo cây!");
                        
                    }
                } textf.setText("");   
            }
        });
        
        
        sea_but.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionode= ACTIONNODE.SEARCH;
                tb.setText("SEARCH:");
            }
        });
    }

    

}
