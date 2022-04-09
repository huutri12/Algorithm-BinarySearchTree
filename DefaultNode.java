/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doan;


import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.text.NumberFormat;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author ADMIN
 */
public class DefaultNode implements Node {

        private int number;
        private Node parent;
        private Node left;
        private Node right;
        private Point location;
        private Color color;

        public DefaultNode(int number, Node parent) {
            this.parent = parent;
            color = Color.green;
            this.number = number;
        }

        public DefaultNode() {
        }

        public void setLeftNode(Node left) {
            this.left = left;
        }

        public void setRightNode(Node right) {
            this.right = right;
        }
        public int getNumber(){
            return this.number;
        }
        
        public void addNode(int item){
            if(item < this.number){
                if(this.left!= null)
                this.left.addNode(item);
                else this.left = new DefaultNode(item, this);
            }
            if(item > this.number){
                if(this.right != null)
                this.right.addNode(item);
                else this.right = new DefaultNode(item, this);
            }
        }

    @Override
    public void setParent(Node parent) {
        this.parent= parent;
    }
        
        public Node getParent() {
            return parent;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        @Override
        public Point getLocation() {
            return location;
        }

        @Override
        public void setLocation(Point location) {
            this.location = location;
        }
        
        @Override
        public void paint(Node parent, Graphics2D g2d) {

            FontMetrics fm = g2d.getFontMetrics();
            int radius = fm.getHeight();
            Point p = getLocation();

            int x = p.x - (radius / 2);
            int y = p.y - (radius / 2);

            Ellipse2D node = new Ellipse2D.Float(x-10, y-10, 40, 40);

            g2d.setColor(getColor());
            g2d.fill(node);

            g2d.setColor(Color.blue);
            g2d.draw(node);
            g2d.setColor(Color.black);
            String text = String.valueOf(number);
            x = x + ((radius - fm.stringWidth(text)) / 2);
            y = y + (((radius - fm.getHeight()) / 2) + fm.getAscent());

            g2d.drawString(text, x, y);
        }

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public Color getColor() {
            return color;
        }

        @Override
        public String toString() {
            return number + " @ " + getLocation();
        }

    @Override
    public void setNumber(int number) {
        this.number= number;
    }

    
    

    }
        