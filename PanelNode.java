/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;

import java.awt.Color;
import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author ADMIN
 */
public class PanelNode extends JPanel implements ActionListener{
    public Timer time = new Timer(250, this) ;
    public Node root;
    int cout=0;
    private String s;
//    int a[];
    public  ArrayList <Integer>a=new ArrayList<>();
 //   private boolean isFinished=false;

    
    public PanelNode(int n) {
        a.clear();
        Random r= new Random();
        if (n ==0 ){
            n = r.nextInt(7)+12;
        }
        int x = r.nextInt(1000);
        root = addLeftNode(null,x); 
        a.add(x);
        while(a.size()<n){
            x= (int)r.nextInt(1000);
            if(a.indexOf(x)==-1){
                setup(root, x);
                a.add(x);
            }
        }
        repaint();
//        root.NLR();
 
    }
    
    
    public Node getRoot(){
        return root;
    }
    
    protected void layoutNode(Node node, int x, int y) {
        
            if (node != null) {          
                int le = 0, ri=0;
                if (node.getParent() != null) {
                    Point p = new Point(x, y);

   //                 if (node.getLocation() == null || !p.equals(node.getLocation())) {
                        node.setLocation(p);
                        System.out.println("New Node to " + node);
//                   } 
                }
                for(int i =0 ; i<a.size(); i++){
                    if(node.getRight() != null &&a.get(i)>node.getNumber() && a.get(i)<node.getRight().getNumber()){
                        ri++;
                    }
                    if(node.getLeft()!= null && a.get(i)<node.getNumber()&& a.get(i)>node.getLeft().getNumber()){
                        le++;
                    }
                }
                layoutNode(node.getLeft(), x- (le+1)*50, y + 50);
                layoutNode(node.getRight(), x + (ri+1)*50, y + 50);
            }

        }
    @Override
        public void doLayout() {
            try{
            System.out.println("DoLayout");
            
            int tmp =0;
            for(int i = 0 ; i< a.size(); i ++){
                if(a.get(i)<root.getNumber())tmp++;
            }
            int x =  150+1100/a.size()*tmp;
            int y =70;
            if (root != null) {
                root.setLocation(new Point(x, y));
                layoutNode(root, x, y);
            }
            
            }
            catch(InternalError e){
                e.printStackTrace();
            }
       
        }

        public synchronized void doUpdate(int item, int upd){
            if(a.indexOf(upd)!= -1){
                JOptionPane.showMessageDialog(this, "node cập nhật đã có!");
            }
            else if (a.indexOf(item)==-1){
                JOptionPane.showMessageDialog(this, "node cần update không có!");
            }
            else{
                a.remove(a.indexOf(item));
                delete(root, item);
                insert(root, upd);
            }
        }
        
        
        public synchronized void doDelete(int item){
            try{
            a.remove(a.indexOf(item));
            }
            catch(IndexOutOfBoundsException e){
                System.out.println(e.toString());
            }
                delete(root, item);  
        }
        protected synchronized void delete(Node parent,int item){
            try{
                    move(parent, Color.yellow);
                    TimeUnit.SECONDS.sleep(1);
                    move(parent, Color.green);
                    if(item< parent.getNumber()){
                        delete(parent.getLeft(), item);
                    }
                    if(item > parent.getNumber()){
                        delete(parent.getRight(), item);
                    }
                    if(item== parent.getNumber()){
                        parent.setColor(Color.red);
                        repaint();
                        Node chillL= parent.getLeft();
                        Node chillR = parent.getRight();
                        if(chillL == null && chillR == null){
                            parent = parent.getParent();
                            if(parent.getLeft()!= null && parent.getLeft().getNumber() == item)
                            parent.setLeftNode(null);
                            else parent.setRightNode(null);
                            System.err.println("xoa node");
                            
                            updateUI();
                            
                        }
                        else if(chillL ==null){
                            parent = parent.getParent();
                            if(parent.getLeft()!= null && parent.getLeft().getNumber()==item)
                            parent.setLeftNode(chillR);
                            else parent.setRightNode(chillR);
                            chillR.setParent(parent);
                            repaint();
                            
                            System.err.println("xoa node");
                            updateUI();
                        }
                        else if(chillR == null){
                            parent = parent.getParent();
                            if(parent.getLeft()!= null && parent.getLeft().getNumber()== item)
                            parent.setLeftNode(chillL);
                            else parent.setRightNode(chillL);
                            chillL.setParent(parent);
                           
                            repaint();
                            System.err.println("xoa node");
                            updateUI();
                        }
                        else{
                            parent.setColor(Color.red);
                            Node tmp= min(parent.getRight());
  //                          movedelete(parent, tmp);
                            //delete(parent.getRight(),tmp.getNumber());
                            delete(parent.getRight(), tmp.getNumber());
                            Node chill = tmp.getRight();
                            parent.setColor(Color.green);
                            parent.setNumber(tmp.getNumber());
                            

                            updateUI();
                        }
                        
                    }
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(this, "Không tìm thấy node để xóa!");
            }
            
        }

        
//        public synchronized void doUpdate(int item, int upd){
//            delete(root, item);
//            iser
//        }

        public synchronized void doNLR(){
            cout=0;
            s="";
            NLR(root);
        }
        public synchronized void NLR(Node parent){
            try {
                s+=parent.getNumber()+" - ";
                cout++;
                move(parent, Color.yellow);
                TimeUnit.SECONDS.sleep(1);
                move(parent, Color.green);
                if(parent.getLeft()!= null)
                    NLR(parent.getLeft());
                if(parent.getRight()!= null)
                    NLR(parent.getRight());
                if(cout== a.size()){
                    JOptionPane.showMessageDialog(this, s);
                    cout=0;
                    return;
                }
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        public synchronized void doSearch(int item){
            search(root, item);
            
        }
        public synchronized void search(Node parent , int item){
            try {
                move(parent, Color.yellow);
                TimeUnit.SECONDS.sleep(1);
                move(parent, Color.green);
                if(parent.getNumber()==item) {
                    System.out.println("da tim thay"+ item);
                    JOptionPane.showMessageDialog(this, "Đã tìm thấy node: "+ item);
                    return ;
                }
                if(parent.getNumber()> item){
                    search(parent.getLeft(), item);
                }
                if( parent.getNumber()< item){
                    search(parent.getRight(), item);
                }
            } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }catch(NullPointerException ex){
                JOptionPane.showMessageDialog(this, "Không tìm thấy node: "+ item);
            }
        }
        public synchronized void doLNR(){
            cout=0;
            s="";
            LNR(root);
        }
        public synchronized void LNR(Node parent){
            try {
                if(parent.getLeft()!= null)
                LNR(parent.getLeft());
                cout++;
                s+= parent.getNumber()+" - ";
                move(parent, Color.yellow);
                TimeUnit.SECONDS.sleep(1);
                move(parent, Color.green);
                if(parent.getRight()!= null)
                LNR(parent.getRight());
                if(cout==a.size()){
                    JOptionPane.showMessageDialog(this, s);
                    cout=0;
                    return;
                }
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        public synchronized void doLRN(){
            cout=0;
            s="";
            LRN(root);
        }
        
        public synchronized void LRN(Node parent){
            try {

                if(parent.getLeft()!= null)
                LRN(parent.getLeft());
                if(parent.getRight()!= null)
                LRN(parent.getRight());
                cout++;
                move(parent, Color.yellow);
                TimeUnit.SECONDS.sleep(1);
                move(parent, Color.green);
                s+=parent.getNumber()+" - ";
                if(cout==a.size()){
                    cout=0;
                    JOptionPane.showMessageDialog(this, s);
                    return;
                }
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
        
        public synchronized Node min(Node parent){
            try{
                if(parent.getLeft()==null){
                    return parent;
                }           
            }
            catch (NullPointerException ex) {
            
        }
        return min(parent.getLeft());
        }
        public synchronized void doMin(){
            System.out.println("do min");
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    min1(root);
                }
            });
            t.start();
        }
        public synchronized void min1(Node parent){
            System.out.println("ham min");
            try{
                if(parent!= null){
                   
                    move(parent, Color.yellow);
                    TimeUnit.SECONDS.sleep(1);
                    move(parent, Color.green);
                    if(parent.getLeft()==null){
                        JOptionPane.showMessageDialog(this,"Node nhỏ nhất: "+ parent.getNumber());
                        return ;
                    }           
                }
            }
            catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        min1(parent.getLeft());
        }
        public synchronized void doMax(){
            max(root);
        }
        public synchronized Node max(Node parent){
            try{
                move(parent, Color.yellow);
                TimeUnit.SECONDS.sleep(1);
                move(parent, Color.green);
                if(parent.getRight()==null){
                    JOptionPane.showMessageDialog(this,"Node lớn nhất: "+ parent.getNumber());
                    return parent;
                }           
            }
            catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return max(parent.getRight());
        }
        
        
    public void setup(Node parent, int item){
            Node chill =null;
            if(parent == null){
                parent = addLeftNode(parent, item);
            }
            else{
                if(item <parent.getNumber()){
                    chill = parent.getLeft();
                    if(chill==null) addLeftNode(parent, item);
                    else setup(chill, item);
                }
                else{
                    chill = parent.getRight();
                    if(chill == null )addRightNode(parent, item);
                    else setup(chill , item);
                }
            }
        }
    public synchronized void move(Node node, Color color){
            node.setColor(color);
            repaint();
            
        }
    public synchronized void doInsert(int item){
        insert(root,item );
    }
    
    public synchronized void insert(Node parent, int item){

//        System.out.println("Node: "+ parent);
            
        try {
            move(parent, Color.yellow);
            TimeUnit.SECONDS.sleep(1);
            move(parent, Color.green);
            Node chill =null;
            if(item == parent.getNumber()){
                JOptionPane.showMessageDialog(this, "Node đã tồn tại!");
                return;
            }
            if(item >parent.getNumber()){
                chill= parent.getRight();
                if(chill == null) {
                    a.add(item);
                    addRightNode(parent, item);
                    repaint();
                    System.out.println("them node phai");
 //                   root.NLR();
                    updateUI();
                }
                else insert(chill, item);
                
            }
            else{
                chill = parent.getLeft();
                if(chill == null ){
                    a.add(item);
                    addLeftNode(parent, item);
                    System.out.println("them node trai");
                    repaint();
//                    root.NLR();
                    updateUI();
                }
                else insert(chill, item);
                
            } 
        }
            catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(PanelNode.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        }
 
    protected Node createNode(Node parent, int number) {
            DefaultNode child = new DefaultNode(number, parent);
            child.setColor(Color.green);
            System.out.println("Create new node " + child);
            return child;
        }

        protected Node addLeftNode(Node parent, int number) {
            Node node = createNode(parent, number);
            if (parent != null) {
                System.out.println("Add " + node + " to left of " + parent);
                parent.setLeftNode(node);
            }
            return node;
        }

        protected Node addRightNode(Node parent, int number) {
            Node node = createNode(parent, number);
            if (parent != null) {
                System.out.println("Add " + node + " to right of " + parent);
                parent.setRightNode(node);
            }
            return node;
        }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            if (root != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                paintConnectors(root, g2d);
                paintNode(root, g2d);
                repaint();
            }
            
    }
  
        protected void paintConnectors(Node node, Graphics2D g2d){
            if (node != null && node.getLocation() != null) {
                    Node parent = node.getParent();
                    if (parent != null) {
                        g2d.setColor(Color.GRAY);
                        if (parent.getLocation() != null && node.getLocation() != null) {
                            g2d.draw(new Line2D.Float(parent.getLocation(), node.getLocation()));
                        }
                    }
            paintConnectors(node.getLeft(), g2d);
            paintConnectors(node.getRight(), g2d);
            }      
        }
        protected void paintNode(Node node, Graphics2D g2d) {
            if (node != null && node.getLocation() != null) {
                node.paint(node, g2d);
                paintNode(node.getLeft(), g2d);
                paintNode(node.getRight(), g2d);
            }
            
        }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(cout <a.size()) cout++;
    }
   
}
